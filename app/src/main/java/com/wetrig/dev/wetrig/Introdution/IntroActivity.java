package com.wetrig.dev.wetrig.Introdution;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.wetrig.dev.wetrig.Authentication.LoginActivity;
import com.wetrig.dev.wetrig.R;

/**
 * Created by darkangel on 13/07/16.
 */
public class IntroActivity extends AppCompatActivity {

    private VideoView videoView;
    private String path;
    private int id;
    private String uri;
    private RelativeLayout videoviewLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

        videoView = (VideoView) findViewById(R.id.videoview);
        videoviewLayout = (RelativeLayout) findViewById(R.id.videoviewLayout);
        //uri = "android.resource://" + getPackageName() + "/" + R.raw.flowers;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.start();

        //View view = inflater.inflate(R.layout.login_fragment, parentViewGroup, false);


        videoviewLayout= (RelativeLayout) findViewById(R.id.videoviewLayout);
        videoviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(IntroActivity.this, LoginActivity.class), 0);
            }
        });
    }
}
