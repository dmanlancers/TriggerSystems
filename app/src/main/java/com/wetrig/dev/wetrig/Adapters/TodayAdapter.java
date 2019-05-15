package com.wetrig.dev.wetrig.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.POJO.Today;
import com.wetrig.dev.wetrig.POJO.ratting;
import com.wetrig.dev.wetrig.R;

import java.util.List;

/**
 * Created by darkangel on 16/06/16.
 */
public class TodayAdapter extends BaseAdapter {
    Context c;
    List<Today> today;

    public TodayAdapter(Context c, List<Today> today) {
        this.c = c;
        this.today = today;


    }

    @Override
    public int getCount() {
        return  today.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        ImageView icon;
        TextView programName;
        TextView noProgram;
      //  RatingBar rb;
        //TextView txtReview;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.today_fragment, null);






            //holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.noProgram = (TextView) convertView.findViewById(R.id.noPrograms);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            /*holder.rb = (RatingBar)   convertView.findViewById(R.id.rattingBar);
            holder.txtReview= (TextView) convertView.findViewById(R.id.txtReview);*/

            convertView.setTag(holder);
        }





        int value = today.get(position).getSno();
        String programName = today.get(position).getProgram();

        if (programName == ""){


            holder.noProgram.setText("No schedule programs for today");

}

        return convertView;
    }



}