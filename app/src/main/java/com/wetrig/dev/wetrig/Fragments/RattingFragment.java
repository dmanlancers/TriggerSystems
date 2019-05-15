package com.wetrig.dev.wetrig.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import com.wetrig.dev.wetrig.Adapters.RattingAdapter;

import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;

public class RattingFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ratting_listview, parentViewGroup, false);
        mActivity = (MainActivity) getActivity();
        listView = (ListView) view.findViewById(R.id.rattingListView);
        listView.setAdapter(new RattingAdapter(mActivity, mActivity.getRatting()));
        listView.setOnItemClickListener(this);
       // listView.setClickable(true);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String  Id = mActivity.getRatting().get(position).getRatting();
        Log.i("HelloListView", "You clicked Item: " + Id + " at position:" + position);



    }
}


