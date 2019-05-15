package com.wetrig.dev.wetrig.Fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;


import com.wetrig.dev.wetrig.Adapters.MenuListViewAdapter;


import com.wetrig.dev.wetrig.Fragments.MenuFragmentItems.LocationFragment;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.RowItem;
import com.wetrig.dev.wetrig.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by darkangel on 28/06/16.
 */
public class MenuFragment extends Fragment  implements AdapterView.OnItemClickListener {


    private ListView menuListView ;
    private List<RowItem> rowItems;
    private MainActivity mActivity;
    private  static final String PREFS_NAME = "MyApp_Settings";
    private SharedPreferences settings;
    private String returnId,name,image,type,desc,address, owner, latitude, longitude;

    private OnDataPassLocation dataPasserL;

    public static final String[] titles = new String[] { "General","Location",
            "Program", "Next","Log", "Configuration" };

    public static final Integer[] images = { R.drawable.hambutton,R.drawable.device_on,
            R.drawable.schedulle_icon1, R.drawable.next_icon1, R.drawable.visualization_icon1, R.drawable.conf_general1  };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();
        menuListView = (ListView) view.findViewById(R.id.menuListView);

        Bundle args = getArguments();

    name = args.getString("s_name");
        returnId = args.getString("s_id");
        Log.e("RETURN",""+ returnId);
        image = args.getString("s_img");
        type = args.getString("sub_cat_name");
        desc= args.getString("sub_cat_desc");
        address= args.getString("s_address");
        owner= args.getString("user_email");
        latitude= args.getString("s_latitude");
        longitude= args.getString("s_longitude");
        //mActivity.getResponse(returnId);
    Log.e("Inicio", "" + returnId + name + latitude+longitude);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i]/*, descriptions[i]*/);
            rowItems.add(item);
        }
        menuListView = (ListView) view.findViewById(R.id.menuListView);
        MenuListViewAdapter adapter = new MenuListViewAdapter(getActivity(), R.layout.menu_fragment, rowItems);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(this);
        menuListView.setClickable(true);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if ( (position + 1) == 1){

          //  dataPasser.onDataPassDetails(returnId,name, image,type,desc, address, owner);
            mActivity.programRunStop(owner,returnId,name,image);


        }

       if ( (position + 1) == 2){
           //Utils.addFragment(mActivity, R.id.mainFragment, new LocationFragment());
           dataPasserL.onDataPassLocation(name, latitude, longitude);


       }

        if ( (position + 1) == 3){

            Toast.makeText(mActivity.getApplicationContext(), "NO API...", Toast.LENGTH_SHORT).show();

        }

        if ( (position + 1) == 4){


            mActivity.getNextData(name,image);





        }
        if ( (position + 1) == 5){

            Toast.makeText(mActivity.getApplicationContext(), "NO API...", Toast.LENGTH_SHORT).show();


        }
        if ( (position + 1) == 6){

            Toast.makeText(mActivity.getApplicationContext(), "NO API...", Toast.LENGTH_SHORT).show();

        }
    }







    public interface OnDataPassLocation {
        public void onDataPassLocation( String name, String latitude, String longitude);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasserL = (OnDataPassLocation) context;
    }
    public void passDataLocation(String name, String latitude, String longitude) {

        dataPasserL.onDataPassLocation(name, latitude, longitude);
    }




}
