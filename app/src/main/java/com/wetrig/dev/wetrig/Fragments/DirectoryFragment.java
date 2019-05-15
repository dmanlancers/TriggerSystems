package com.wetrig.dev.wetrig.Fragments;

import android.app.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
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


public class DirectoryFragment extends Fragment{


    private String url = "http://dev.wetrig.com/web_services/android_get_systemswsh?email=";
    private ProgressDialog PD;
    private String imgUrl = "http://dev.wetrig.com/img/";
    private String dirImg ="http://dev.wetrig.com/images/directory_icon.png";
    private String unfilledImg ="http://dev.wetrig.com/images/unfield.png";
    private String Share = "http://dev.wetrig.com/images/sharedir.png";
    private String gatImg = "http://dev.wetrig.com/img/hardware_101.png";
    private String sysImg = "http://dev.wetrig.com/img/system_5.png";
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private ExpandListAdapter ExpAdapter;
    private ExpandableListView ExpandList;
    private MainActivity mActivity;
    private  Group gru;
    private ArrayList<Group> list;
    private ArrayList<Child> ch_list;
    private  OnDataPass dataPasser;
    private String  email,returnid,sname,image,type,desc,address,owner,latitude, longitude, TypeId,Spublic;
    private FloatingActionButton fab, item_system,item_gateway;
    private String tag_json_obj = "json_obj_req";
    private Child ch;
    private HashMap<String, List<String>> listDataChild;
    private ImageView system, gateway;

    public interface OnDataPass {
        public void onDataPass(String data, String name, String image, String type,
                               String desc, String location, String owner, String latitude, String longitude);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
    public void passData(String data, String name, String image, String type, String desc, String address, String owner, String latitude, String longitude) {

        dataPasser.onDataPass(data, name, image,type, desc, address, owner, latitude,longitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.directory_fragment, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();
        ExpandList = (ExpandableListView) view.findViewById(R.id.mainList);
        ExpandList.setClickable(true);
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        email = settings.getString("key", "");
        system = (ImageView) view.findViewById(R.id.sys);
        gateway = (ImageView) view.findViewById(R.id.gat);
        system.setClickable(true);
        gateway.setClickable(true);
       system.setBackgroundColor(Color.parseColor("#CCCCCC"));


        //system.getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.grey), PorterDuff.Mode.MULTIPLY);
        item_system = (FloatingActionButton) view.findViewById(R.id.system);
        item_gateway = (FloatingActionButton) view.findViewById(R.id.gateway);
        Picasso.with(getActivity())
                .load(gatImg)
                .into(gateway);
        Picasso.with(getActivity())
                .load(sysImg)
                .into(system);

        getSystems(email);

       item_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.addFragment(mActivity, R.id.mainFragment, new AddSystemF1Fragment());


            }

        });


        item_gateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Utils.addFragment(mActivity, R.id.mainFragment, new GatewayFragment());
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
                system.setBackgroundColor(Color.parseColor("#CCCCCC"));
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
                dataPasser.onDataPass(returnid, sname, image, type,desc,address, owner,latitude, longitude);

                return false;
            }
        });


        ExpandList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
                    int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    int childPosition = ExpandableListView.getPackedPositionChild(id);
                    returnid = list.get(groupPosition).getItems().get(childPosition).getId();
                    sname =list.get(groupPosition).getItems().get(childPosition).getName();
                    TypeId=list.get(groupPosition).getItems().get(childPosition).getTypeId();
                    desc = list.get(groupPosition).getItems().get(childPosition).getDesc();
                    address = list.get(groupPosition).getItems().get(childPosition).getAddress();
                    latitude =list.get(groupPosition).getItems().get(childPosition).getLatitude();
                    longitude = list.get(groupPosition).getItems().get(childPosition).getLongitude();
                    Spublic =  list.get(groupPosition).getItems().get(childPosition).getSpublic();

                    mActivity.onNamePass(returnid,TypeId,sname,desc,address,Spublic,latitude,longitude);

                    return true;
                }

                return false;
            }
        });


        return view;
    }

    private void getSystems( String email) {


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
                        if(k.equals("Unfiled")){

                            gru.setImage(unfilledImg);

                        }else if(k.equals("Shared")){

                            gru.setImage(Share);
                        }else {

                            gru.setImage(dirImg);
                        }

                        ch_list = new ArrayList<Child>();

                        JSONArray ja = response.getJSONArray(k);

                        for (int i = 0; i < ja.length(); i++) {

                            JSONObject jo = ja.getJSONObject(i);

                            Log.e("",""+jo);

                            ch = new Child();
                            ch.setId(jo.getString("s_id"));
                            ch.setName(jo.getString("s_name"));
                            ch.setImgUrl(jo.getString("sub_cat_icon"));
                            ch.setType(jo.getString("sys_type"));
                            ch.setDesc(jo.getString("s_description"));
                            ch.setAddress(jo.getString("s_address"));
                            ch.setOwner(jo.getString("user_email"));
                            ch.setLatitude(jo.getString("s_latitude"));
                            ch.setLongitude(jo.getString("s_longitude"));
                            ch.setTypeId(jo.getString("s_type_id"));
                            ch.setSpublic(jo.getString("s_public"));
                            String icon ;
                            icon = jo.getString("sub_cat_icon");
                            ch.setImage(imgUrl+icon);
                            ch_list.add(ch);


                        }
                        gru.setItems(ch_list);

                        list.add(gru);
                    }

                    ExpAdapter = new ExpandListAdapter(
                            getActivity(), list);
                    ExpandList.setAdapter(ExpAdapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().addToReqQueue(jsonObjReq, "jreq");
    }



}



