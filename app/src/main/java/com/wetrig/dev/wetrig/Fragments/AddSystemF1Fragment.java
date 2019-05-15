package com.wetrig.dev.wetrig.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
public class AddSystemF1Fragment extends Fragment {
    private String url = "http://dev.wetrig.com/web_services/list_category";
    private String imgUrl = "http://dev.wetrig.com/img/";
    private String dirImg ="http://dev.wetrig.com/images/directory_icon.png";
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private ExpandListAdapter ExpAdapter;
    private ExpandableListView ExpandList;
    private MainActivity mActivity;
    private List<String> data;
    private Group gru;
    private ArrayList<Group> list;
    private ArrayList<Child> ch_list;
    private String  returnid,sname,image,desc,ratting;
    private String tag_json_obj = "json_obj_req";
    private  String email,comment, username,   answerValue;
    private Child ch;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_systems_f1_fragment, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();
        data= new ArrayList<>();
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        email = settings.getString("key", "");
        ExpandList = (ExpandableListView) view.findViewById(R.id.expCat);


        ExpandList.setClickable(true);
        getCategories();

        ExpandList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                for(int i=0;i < ExpandList.getExpandableListAdapter().getChildrenCount(groupPosition);i++) {

                    returnid = list.get(groupPosition).getItems().get(childPosition).getId();
                    sname =list.get(groupPosition).getItems().get(childPosition).getName();
                    image = list.get(groupPosition).getItems().get(childPosition).getImgUrl();
                    desc = list.get(groupPosition).getItems().get(childPosition).getDesc();
                    ratting = list.get(groupPosition).getItems().get(childPosition).getRattingAvg();
                }

                AddSystemF2Fragment addF2 = new  AddSystemF2Fragment();
                Bundle bundle = new Bundle();
                bundle.putString("sys_type_id", returnid);
                addF2.setArguments(bundle);

                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "");

                mActivity.getRattingList(email,returnid);

                return false;
            }
        });

        return view;
    }

    private void getCategories( ) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url,
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
                        Log.e("RESPONSE",""+ gru.getName());
                        gru.setImage(dirImg);
                        ch_list = new ArrayList<Child>();

                        JSONArray ja = response.getJSONArray(k);


                        for (int i = 0; i < ja.length(); i++) {

                            JSONObject jo = ja.getJSONObject(i);
                            ch = new Child();
                            ch.setId(jo.getString("sub_id"));
                            ch.setName(jo.getString("sub_sys_name"));
                            ch.setImgUrl(jo.getString("sub_cat_icon"));
                            ch.setDesc(jo.getString("sub_cat_desc"));
                            ch.setRattingAvg(jo.getString("ratting_avg"));
                            String icon;
                            icon = jo.getString("sub_cat_icon");
                            ch.setImage(imgUrl + icon);
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

    public class ViewDialog {

        public void showDialog(Activity activity, final String msg){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.category_fragment);

            TextView title = (TextView) dialog.findViewById(R.id.catTitle);
            TextView dc = (TextView) dialog.findViewById(R.id.txtCatDescription);
            RatingBar rb = (RatingBar) dialog.findViewById(R.id.dialog_ratingbar);
            title.setText(sname);
            rb.setRating(Float.parseFloat((ratting)));
            dc.setText(desc);

            Button review = (Button) dialog.findViewById(R.id.review);
            review.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for(int i=0;i<mActivity.getRatting().size();i++){
                        username= mActivity.getRatting().get(i).getUserEmail();

                        data.add(mActivity.getRatting().get(i).getUserEmail());

                        Log.e("for",""+username);

                    }
                    Log.e("DADOS", data+email);


                    if(data.contains(email)  ){

                        Log.e("TRUE",""+data.contains(email));
                        Utils.addFragment(mActivity, R.id.mainFragment, new RattingFragment());
                        dialog.dismiss();
                    }
                    else {

                        final Dialog rankDialog = new Dialog(getActivity(), R.style.FullHeightDialog);
                        rankDialog.setContentView(R.layout.dialog_ratting_fragment);
                        rankDialog.setCancelable(true);
                        RatingBar ratingBar = (RatingBar)rankDialog.findViewById(R.id.addRattingBar);
                        ratingBar.setRating((Float.parseFloat((ratting))));
                        EditText review = (EditText)rankDialog.findViewById(R.id.input_review);
                        comment =review.getText().toString().trim();
                        Button updateButton = (Button) rankDialog.findViewById(R.id.submit);
                        updateButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                rankDialog.dismiss();
                            }
                        });

                        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                            public void onRatingChanged(RatingBar ratingBar,
                                                        float rating, boolean fromUser) {

                                answerValue = String.valueOf(ratingBar.getRating());

                                mActivity.AddRating(email,answerValue,comment,returnid);

                            }
                        });


                        rankDialog.show();
                    }
                    dialog.dismiss();
                }



            });

            Button next = (Button) dialog.findViewById(R.id.next);
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActivity.onDataPassF2(returnid);
                    dialog.dismiss();
                }
            });

            ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });


            dialog.show();

        }

    }

}
