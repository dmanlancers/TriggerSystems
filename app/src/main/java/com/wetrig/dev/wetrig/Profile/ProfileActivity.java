package com.wetrig.dev.wetrig.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wetrig.dev.wetrig.Authentication.LoginActivity;
import com.wetrig.dev.wetrig.Authentication.RegisterActivity;
import com.wetrig.dev.wetrig.Fragments.ProfileFragment;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.POJO.Profile;
import com.wetrig.dev.wetrig.POJO.Register;
import com.wetrig.dev.wetrig.POJO.UserData;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.Retrofit.RestInterface;
import com.wetrig.dev.wetrig.Retrofit.ServiceGenerator;
import com.wetrig.dev.wetrig.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by darkangel on 09/07/16.
 */
public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ProgressDialog mainProgress;
    private ProgressDialog PD;
    private int percentageStatus = 0;
    private CoordinatorLayout coordinatorLayout;
    private ActionBar actionBar;
    private DrawerLayout dl;
    private List<UserData> userData;
    private List<Profile> updateData;
    private SharedPreferences settings;
    private String email;
    public static final String PREFS_NAME = "MyApp_Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(R.mipmap.wetrig_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        dl = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(toolbar.getContentInsetLeft(), 300);

        userData = new ArrayList<>();
        updateData = new ArrayList<>();
        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        email = settings.getString("key", "");
        getUserData(email);
        Log.e("EmailOut", "" + email);

    }

    public void getUserData(final String email) {
        RestInterface restInterface = ServiceGenerator.createService(RestInterface.class);

        //GET USER DATA
        Call<UserData> call = restInterface.getUserData(email);
        Log.e("EmailInside", "" + email);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (response.body() != null) {


                    userData.add(response.body());

                }
                Utils.addFragment(ProfileActivity.this, R.id.profileFragment, new ProfileFragment());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("error devices", t.getMessage());
            }
        });

    }

    public List<UserData> getUserData() {
        return userData;
    }


    public void getProfileUpdater(final String image, final String gender, final String first_name, final String last_name, final String email,
                                  final String birth_date, final String bio) {


        RestInterface restInterface = ServiceGenerator.createService(RestInterface.class);

        //UPDATE USER DATA
        Call<Profile> call = restInterface.getProfileUpdater(image, gender, first_name, last_name, email, birth_date, bio);

        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {


                if (response.body() != null) {

                    //SharedPreferences settings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                  //  SharedPreferences.Editor editor = settings.edit();
                    //editor.remove("user_image");
                  //  editor.remove("first_name");
                   // editor.putString("user_image", image);
                   // editor.putString("first_name", first_name);
                   // editor.commit();
                    updateData.add(response.body());

                    CoordinatorLayout coordinatorLayout;

                    coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                            .profile);
                    final Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, getString(R.string.msg_update_success_profile), Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.GREEN);
                    snackbar.show();

                    /*Intent myIntent = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivityForResult(myIntent, 0);*/


                }

            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e("error devices", t.getMessage());

                CoordinatorLayout coordinatorLayout;

                coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                        .profile);
                final Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, getString(R.string.msg_update_error_profile), Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView tv = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.RED);
                snackbar.show();
            }
        });

    }

    public List<Profile> getUprofile() {
        return updateData;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            dl.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();



        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(i);
        finish();

    }
}
