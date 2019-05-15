package com.wetrig.dev.wetrig.Fragments;



import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Color;
import android.graphics.PorterDuff;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;


import com.squareup.picasso.Picasso;

import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;
import java.util.ArrayList;

public class DeviceFragment extends Fragment {

    private MainActivity mActivity;
    private LinearLayout mDevicesInfo;
    private SwitchCompat switchCompat ;
    private String valueOnOff, command, sId;
    private int items;
    private ArrayList<SwitchCompat> switchList;
    private TextView text ;
    private ImageView image;
    private EditText budget;
    private String email,Img,sysName,status;
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private String Url= "http://dev.wetrig.com/img/";
    private Button run, stop;
    private int  vBudget;
    private  DeviceFragment dev;
    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.switch_fragment, parentViewGroup, false);
        mDevicesInfo = (LinearLayout) view.findViewById(R.id.devices_info);
        mActivity = (MainActivity) getActivity();
        switchList= new ArrayList<>();
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        email = settings.getString("key", "");
        image =(ImageView) view.findViewById(R.id.sysLogo);
        text = (TextView) view.findViewById(R.id.sysTitle);
        budget = (EditText) view.findViewById(R.id.inputBudget) ;
        run = (Button) view.findViewById(R.id.run);
        stop = (Button) view.findViewById(R.id.stop);
        Drawable d = view.findViewById(R.id.run).getBackground();

        Bundle args = getArguments();
        sId = args.getString("s_id");
        sysName =  args.getString("s_name");
        Img = args.getString("s_img");
        vBudget= args.getInt("d_budget");
        status = args.getString("d_status");
        Log.e("Vstatus","" + status+vBudget);
        Picasso.with(getActivity())
                .load(Url+Img)
                .into(image);
        text.setText(sysName);
        budget.setText(String.valueOf(vBudget));
        getResponse();


        if(status.equals("stopped")){

            run.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.triggerSys), PorterDuff.Mode.MULTIPLY);
            stop.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.MULTIPLY);

            stop.setEnabled(false);

        }else{

            stop.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.triggerSys), PorterDuff.Mode.MULTIPLY);
            run.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.MULTIPLY);

            run.setEnabled(false);

        }


        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                command = "run";


                run.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.MULTIPLY);
               stop.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.triggerSys), PorterDuff.Mode.MULTIPLY);
                run.setEnabled(false);
                stop.setEnabled(true);
                mActivity.RunStopSchedule(sId,command);




            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragmentManager.beginTransaction().replace(R.id.mainFragment, "Your_Fragment_TAG").commitAllowingStateLoss();

                command ="stop";
                stop.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.MULTIPLY);
                run.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.triggerSys), PorterDuff.Mode.MULTIPLY);
                stop.setEnabled(false);
                run.setEnabled(true);
                mActivity.RunStopSchedule(sId,command);

            }
        });

        return view;

    }



    public void getResponse() {




        Log.e("ITEMS","u"+mActivity.getDevice().size());
        for (int i = 0; i < mActivity.getDevice().size(); i++) {



            mDevicesInfo.addView(addSwitch(mActivity, mActivity.getDevice().get(i).getDName(), mActivity.getDevice().get(i).getDDigitalVal(), mActivity.getDevice().get(i).getDId(), mActivity.getDevice().get(i).getChannelId(), mActivity.getDevice().get(i).getGModelNo()));
            switchCompat = addSwitch(mActivity, mActivity.getDevice().get(i).getDName(), mActivity.getDevice().get(i).getDDigitalVal(), mActivity.getDevice().get(i).getDId(), mActivity.getDevice().get(i).getChannelId(), mActivity.getDevice().get(i).getGModelNo());
            switchList.add(switchCompat);

        }

    }

    public  SwitchCompat addSwitch(Context context, String title,final  String status, final String Id, final String channelId, final String controllerId ) {
        switchCompat = new SwitchCompat(context);
        switchCompat.setText(title);

        if (status.equals("On")) {
            switchCompat.setChecked(true);

        }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchCompat.setTag(channelId);
                switchCompat.setTag(Id);
                switchCompat.setTag(controllerId);

                if (!isChecked) {
                    valueOnOff="0";
                }  if (isChecked) {
                    valueOnOff="5";
                }

                mActivity.getSwitchStatus(Id, channelId,controllerId , valueOnOff);
            }
        });
        return switchCompat;
    }
}


