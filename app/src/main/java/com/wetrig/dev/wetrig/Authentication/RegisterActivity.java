    package com.wetrig.dev.wetrig.Authentication;

    import android.app.Dialog;
    import android.app.ProgressDialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.design.widget.CoordinatorLayout;
    import android.support.design.widget.Snackbar;
    import android.support.v7.app.AppCompatActivity;
    import android.support.v7.widget.Toolbar;
    import android.util.Log;
    import android.view.Window;
    import android.widget.Toast;

    import com.wetrig.dev.wetrig.Fragments.RegisterFragment;
    import com.wetrig.dev.wetrig.MainActivity;
    import com.wetrig.dev.wetrig.POJO.Login;
    import com.wetrig.dev.wetrig.POJO.Register;
    import com.wetrig.dev.wetrig.R;
    import com.wetrig.dev.wetrig.Retrofit.RestInterface;
    import com.wetrig.dev.wetrig.Retrofit.ServiceGenerator;
    import com.wetrig.dev.wetrig.Utils;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    /**
     * Created by darkangel on 08/06/16.
     */
    public class RegisterActivity extends AppCompatActivity {
        private Toolbar toolbar;
        private ProgressDialog mainProgress;
        private ProgressDialog progressBar;
        private int percentageStatus = 0;
        private CoordinatorLayout coordinatorLayout;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            toolbar = (Toolbar) findViewById(R.id.toolbar_register);
            toolbar.setContentInsetsAbsolute(toolbar.getContentInsetLeft(), 300);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.wetrig_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);



            Utils.addFragment(RegisterActivity.this, R.id.fragmentRegister, new RegisterFragment());
        }


        public void getRegisterCallback(final String user, String password, String reference, String other_ref, String reason, String other_reason) {

            progressBar = new ProgressDialog(RegisterActivity.this);
            progressBar.setCancelable(true);
            progressBar.setMessage(getString(R.string.msg_register_progressBar));
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.show();

            //Login call method
            RestInterface restInterface = ServiceGenerator.createService(RestInterface.class);
            Call<Register> call = restInterface.UserRegister(user, password,reference, other_ref,reason, other_reason);
            call.enqueue(new Callback<Register>() {
                @Override
                public void onResponse(Call<Register> call, Response<Register> response) {
                    int ExistsResponse = response.body().getExists();

                    if (response.body() != null && ExistsResponse != 1 ) {
                        //Log.e("Login:", "teste"+ ExistsResponse);
                        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.register);
                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, getString(R.string.msg_sucess_registration), Snackbar.LENGTH_LONG);
                        snackbar.show();

                        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(i);

                    }else
                    {
                        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.register);

                        Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, getString(R.string.err_msg_email_exists), Snackbar.LENGTH_LONG);

                        snackbar.show();



                    }


                }



                @Override
                public void onFailure(Call<Register> call, Throwable t) {
                    Log.e("error login", t.getMessage());
                    progressBar.dismiss();
                    coordinatorLayout = (CoordinatorLayout) findViewById(R.id.register);
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, getString(R.string.msg_error_register), Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
            });
        }


    }




