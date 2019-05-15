package com.wetrig.dev.wetrig.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.clans.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.Adapters.ExpandListAdapter;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.MyApplication;
import com.wetrig.dev.wetrig.POJO.Child;
import com.wetrig.dev.wetrig.POJO.Group;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by darkangel on 25/07/16.
 */
public class GatewayFragment extends Fragment{

    private ListView listView;
    private String url = "http://dev.wetrig.com/web_services/gateway_list?email=";
    private ProgressDialog PD;
    private String imgUrl = "http://dev.wetrig.com/images/gate.png";
    private String dirImg ="http://dev.wetrig.com/images/directory_icon.png";
    private String gatImg = "http://dev.wetrig.com/img/hardware_101.png";
    private String sysImg = "http://dev.wetrig.com/img/system_5.png";
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private ExpandListAdapter ExpAdapter;
    private ExpandableListView ExpandList;
    private MainActivity mActivity;
    private Group gru;
    private ArrayList<Group> list;
    private ArrayList<Child> ch_list;
    private String  returnid,sname,image,type,desc,address,owner,latitude, longitude, folderName;
    private FloatingActionButton fab,item_system,item_gateway;
    private static FragmentManager fragmentManager;
    private String tag_json_obj = "json_obj_req";
    private  String email;
    private List<String> gatewayName,gatewayId, systemsImg,systemsType,systemsDesc, systemsAddress,systemsOwner, systemsLatitude,systemsLongitude;
    private Child ch;
    private HashMap<String, List<String>> listDataChild;
    private ImageView system, gateway;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gateway_fragment, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();

        fragmentManager = getActivity().getSupportFragmentManager();

        ExpandList = (ExpandableListView) view.findViewById(R.id.expGat);
        fragmentManager = getActivity().getSupportFragmentManager();
        //ExpandList.setOnItemClickListener();
        ExpandList.setClickable(true);
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        system = (ImageView) view.findViewById(R.id.sys);
        gateway = (ImageView) view.findViewById(R.id.gat);
        system.setClickable(true);
        gateway.setClickable(true);
        gateway.setBackgroundColor(Color.parseColor("#CCCCCC"));
        Picasso.with(getActivity())
                .load(gatImg)
                .into(gateway);
        Picasso.with(getActivity())
                .load(sysImg)
                .into(system);


        item_system = (FloatingActionButton) view.findViewById(R.id.system);
        item_gateway = (FloatingActionButton) view.findViewById(R.id.gateway);


        email = settings.getString("key", "");




        getGateway(email);


        item_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.addFragment(mActivity, R.id.mainFragment, new AddSystemF1Fragment());
            }
        });

        item_gateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gateway.setBackgroundColor(Color.parseColor("#CCCCCC"));
                Toast.makeText(mActivity.getApplicationContext(), "NO API",  Toast.LENGTH_SHORT).show();
            }
        });


        system.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utils.addFragment(mActivity, R.id.mainFragment, new DirectoryFragment());
                system.setBackgroundColor(Color.parseColor("#CCCCCC"));

            }

        });

        gateway.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Utils.addFragment(mActivity, R.id.mainFragment, new GatewayFragment());
                gateway.setBackgroundColor(Color.parseColor("#CCCCCC"));

            }

        });



        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                for(int i=0;i < ExpandList.getExpandableListAdapter().getChildrenCount(groupPosition);i++) {

                    returnid = list.get(groupPosition).getItems().get(childPosition).getId();
                    sname =list.get(groupPosition).getItems().get(childPosition).getName();
                    image = list.get(groupPosition).getItems().get(childPosition).getImgUrl();
                    type = list.get(groupPosition).getItems().get(childPosition).geType();
                    desc = list.get(groupPosition).getItems().get(childPosition).getDesc();
                    address = list.get(groupPosition).getItems().get(childPosition).getAddress();
                    owner = list.get(groupPosition).getItems().get(childPosition).getOwner();
                    latitude =list.get(groupPosition).getItems().get(childPosition).getLatitude();
                    longitude = list.get(groupPosition).getItems().get(childPosition).getLongitude();




                }

                Toast.makeText(mActivity.getApplicationContext(), "TESTE"+sname,  Toast.LENGTH_SHORT).show();

                //dataPasser.onDataPass(returnid, sname, image, type,desc,address, owner,latitude, longitude);
                //Utils.addFragment(mActivity, R.id.mainFragment, new DetailsFragment());

                return false;
            }
        });

        return view;
    }

    private void getGateway(String email ) {
        //PD.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url+email,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                list = new ArrayList<Group>();
                ch_list =     new ArrayList<Child>();

                try {
                    Iterator<String> key = response.keys();
                    while (key.hasNext()) {
                        String k = key.next();

                        Group gru = new Group();
                        gru.setName(k);
                        gru.setImage(dirImg);
                        ch_list = new ArrayList<Child>();

                        JSONArray ja = response.getJSONArray(k);

                        //Log.e("RESPONSE",""+response.getJSONArray(k));

                        for (int i = 0; i < ja.length(); i++) {

                            JSONObject jo = ja.getJSONObject(i);
                            Log.e("RESPONSE",""+jo.getString("g_name"));


                            ch = new Child();
                            //ch.setId(jo.getString("s_id"));
                            ch.setName(jo.getString("g_name"));
                           /* ch.setImgUrl(jo.getString("sub_cat_icon"));
                            ch.setType(jo.getString("sys_type"));
                            ch.setDesc(jo.getString("sub_cat_desc"));
                            ch.setAddress(jo.getString("s_address"));
                            ch.setOwner(jo.getString("user_email"));
                            ch.setLatitude(jo.getString("s_latitude"));
                            ch.setLongitude(jo.getString("s_longitude"));*/
                            ch.setImage(imgUrl);
                            ch_list.add(ch);


                        } // for loop end
                        gru.setItems(ch_list);

                        list.add(gru);
                    } // while loop end

                    ExpAdapter = new ExpandListAdapter(
                            getActivity(), list);
                    ExpandList.setAdapter(ExpAdapter);




                    //PD.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //PD.dismiss();
            }
        });
        MyApplication.getInstance().addToReqQueue(jsonObjReq, "jreq");
    }
}
