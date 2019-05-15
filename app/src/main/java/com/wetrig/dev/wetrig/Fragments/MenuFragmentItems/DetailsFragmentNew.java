package com.wetrig.dev.wetrig.Fragments;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.Utils;

/**
 * Created by darkangel on 23/07/16.
 */
public class DetailsFragmentNew extends Fragment   {

    private ImageView commandContainer, shareContainer,logoContainer;
    private TextView title,type, description,address,owner;
    private MainActivity mActivity;
    private String detailsTitle, detailsType,detailsDescription,detailsAddress,detaislOwner,detailsImg, returnId, detailsLat, detailsLon;
    private   String imgUrl = "http://dev.wetrig.com/img/";
    private  OnDataPassDetails dataPasser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, parentViewGroup, false);
        Bundle args = getArguments();
        mActivity = (MainActivity) getActivity();
        commandContainer = (ImageView) view.findViewById(R.id.commandContainer);
        shareContainer = (ImageView) view.findViewById(R.id.shareContainer);
        logoContainer = (ImageView) view.findViewById(R.id.logoContainer);
        type = (TextView) view.findViewById(R.id.txtType);
        description = (TextView) view.findViewById(R.id.txtDescription);
        address = (TextView) view.findViewById(R.id.txtAddress);
        owner = (TextView) view.findViewById(R.id.txtOwner);
        returnId = args.getString("s_id");
        detailsTitle =  args.getString("s_name");
        detailsImg = args.getString("s_img");
        detailsType = args.getString("sub_cat_name");
        detailsDescription= args.getString("sub_cat_desc");
        detailsAddress= args.getString("s_address");
        detaislOwner = args.getString("user_email");
        detailsLat = args.getString("s_latitude", detailsLat);
        detailsLon = args.getString("s_longitude", detailsLon);
        type.setText(detailsType);
        description.setText(detailsDescription);
        address.setText(detailsAddress);
        owner.setText(detaislOwner);

        Picasso.with(getActivity())
                .load(imgUrl+detailsImg)
                .into(logoContainer);


        commandContainer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //
                dataPasser.onDataPassDetails(returnId,detailsTitle, detailsImg,detailsType,detailsDescription, detailsAddress
                        , detaislOwner,detailsLat,detailsLon);

                //Utils.addFragment(mActivity, R.id.mainFragment, new MenuFragment());
            }
        });

        return view;
    }
    public interface OnDataPassDetails {
        public void onDataPassDetails(String id, String name, String image, String type, String desc, String location, String owner, String lat, String lon);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dataPasser = (OnDataPassDetails) activity;
    }
    public void passDataDetails(String id,String name, String image, String type, String desc,String location, String owner, String lat, String lon) {

        dataPasser.onDataPassDetails(id,name, image, type, desc, location, owner,lat, lon);
    }

}
