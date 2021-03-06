package com.wetrig.dev.wetrig.Fragments.MenuFragmentItems;


import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.Adapters.RattingAdapter;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;

/**
 * Created by darkangel on 02/08/16.
 */
public class NextFragment extends Fragment implements AdapterView.OnItemLongClickListener{


    private MainActivity mActivity;
    private TextView txtWarning, title;
    private ImageView logo;
    private Button today,tomorrow,after;
    private String txtTitle,logoUrl;
    private String imgUrl = "http://dev.wetrig.com/img/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.next_main_fragment, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();
        logo = (ImageView) view.findViewById(R.id.logo);
        title = (TextView) view.findViewById(R.id.txtTitle);
        today = (Button) view.findViewById(R.id.today);
        tomorrow = (Button) view.findViewById(R.id.tomorrow);
        after = (Button) view.findViewById(R.id.after);
        txtWarning = (TextView) view.findViewById(R.id.txtWarning);
        Bundle args = getArguments();
        txtTitle = args.getString("s_name");
        logoUrl = args.getString("s_image");


        Picasso.with(getActivity())
                .load(imgUrl+logoUrl)
                .into(logo);
        title.setText(txtTitle);
        txtWarning.setText(getResources().getString(R.string.scheduleP));


        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackgroundColor(Color.parseColor("#1BB5A9"));
                tomorrow.setBackgroundColor(Color.parseColor("#CCCCCC"));
                after.setBackgroundColor(Color.parseColor("#CCCCCC"));
                mActivity.todayData( txtTitle, logoUrl);



            }
        });

        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tomorrow.setBackgroundColor(Color.parseColor("#1BB5A9"));
                today.setBackgroundColor(Color.parseColor("#CCCCCC"));
                after.setBackgroundColor(Color.parseColor("#CCCCCC"));
                mActivity.tomorrowData( txtTitle, logoUrl);


            }
        });

        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                after.setBackgroundColor(Color.parseColor("#1BB5A9"));
                today.setBackgroundColor(Color.parseColor("#CCCCCC"));
                tomorrow.setBackgroundColor(Color.parseColor("#CCCCCC"));
                mActivity.afterData( txtTitle, logoUrl);

            }
        });





        return view;
    }







    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        String  Id = mActivity.getRatting().get(position).getRatting();
        Log.i("HelloListView", "You clicked Item: " + Id + " at position:" + position);
        return false;
    }
}
