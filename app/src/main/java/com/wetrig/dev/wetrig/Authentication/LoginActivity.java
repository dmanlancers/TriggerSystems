package com.wetrig.dev.wetrig.Authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.wetrig.dev.wetrig.Fragments.LoginFragment;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.POJO.GetSystemId;
import com.wetrig.dev.wetrig.POJO.Login;
import com.wetrig.dev.wetrig.POJO.systemId;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.Retrofit.RestInterface;
import com.wetrig.dev.wetrig.Retrofit.ServiceGenerator;
import com.wetrig.dev.wetrig.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by darkangel on 08/06/16.
 */
public class LoginActivity  extends AppCompatActivity {

    private List<Login> login;

    private List<systemId> SystemId;
    private ProgressDialog progressBar;
    private int percentageStatus = 0;
    public static final String PREFS_NAME = "MyApp_Settings";
    private ProgressDialog progress;
    private String  value, LoginId, urlImage,name,firstName, lastName,birthDate, bio ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Toolbar toolbar;
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        toolbar.setContentInsetsAbsolute(toolbar.getContentInsetLeft(), 300);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.wetrig_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        value = settings.getString("key", "");
        if (value != "") {
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(myIntent, 0);
        }
        Utils.addFragment(LoginActivity.this, R.id.fragmentLogin, new LoginFragment());

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void getLoginCallback(final String user, String password){


        //Login call method
        RestInterface restInterface = ServiceGenerator.createService(RestInterface.class);
        Call<Login> call = restInterface.UserLogin(user, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.body() != null) {

                    SharedPreferences settings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = settings.edit();
                    urlImage = response.body().getUserPhoto();
                    firstName = response.body().getUserName();

                    editor.putString("user_image", urlImage);
                    editor.putString("key", user);
                    editor.putString("first_name", firstName);
                    editor.commit();

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(i);
                    finish();
                    LoginId = response.body().getID();

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("error login", t.getMessage());

                CoordinatorLayout coordinatorLayout;

                coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                        .login);
                final Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, getString(R.string.msg_error_login_access), Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                sbView.setBackgroundColor(Color.RED);
                snackbar.show();

            }
        });

    }
    public String getLoginId() {
        return LoginId;
    }

    //GET SYSTEM ID
    public void getSystemID( final String user){

        RestInterface restInterface = ServiceGenerator.createService(RestInterface.class);
        Call<GetSystemId> call = restInterface.GetEmail(user);
        call.enqueue(new Callback<GetSystemId>() {

            @Override
            public void onResponse(Call<GetSystemId> call, Response<GetSystemId> response) {

                if(response.body() != null) {
                    finish();
                     SystemId = response.body().getData();
                }

            }

            @Override
            public void onFailure(Call<GetSystemId> call_devices, Throwable t) {
                Log.e("error devices", t.getMessage());
            }
        });
    }

    public List<systemId> getSystemId() {
        return SystemId;
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    }






