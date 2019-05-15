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
import com.wetrig.dev.wetrig.POJO.ratting;
import com.wetrig.dev.wetrig.R;

import java.util.List;

/**
 * Created by darkangel on 16/06/16.
 */
public class RattingAdapter extends BaseAdapter {
    Context c;
    List<ratting> ratting;

    public RattingAdapter(Context c, List<ratting> rating) {
        this.c = c;
        this.ratting = rating;


    }

    @Override
    public int getCount() {
        return  ratting.size();

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
        TextView txtTitle;
        RatingBar rb;
        TextView txtReview;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.ratting_fragment, null);






            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
           holder.rb = (RatingBar)   convertView.findViewById(R.id.rattingBar);
            holder.txtReview= (TextView) convertView.findViewById(R.id.txtReview);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }



        String value = ratting.get(position).getRatting();
        String Img = ratting.get(position).getUserPhoto();

        holder.txtTitle.setText(ratting.get(position).getUserName());
        Picasso.with(this.c).load("http://dev.wetrig.com/"+ Img).into(holder.icon);
        holder.rb.setRating(Float.parseFloat((value)));
        holder.txtReview.setText(ratting.get(position).getReview());


        return convertView;
    }



}