package com.wetrig.dev.wetrig.Fragments.MenuFragmentItems;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;

/**
 * Created by darkangel on 21/07/16.
 */
public class LocationFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MainActivity mActivity;
    private String name, latitude, longitude;


    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        mActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.location_fragment, parentViewGroup, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle args = getArguments();

        name = args.getString("s_name");
        latitude = args.getString("s_latitude");
        longitude = args.getString("s_longitude");

        Log.e("NO MAPS",""+name+latitude+longitude);

        return view;

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


            double Slatitude = Double.parseDouble(latitude);
            double Slongitude = Double.parseDouble(longitude);
            Log.e("NO DOUBLE",""+Slatitude+Slongitude);

            // Add a marker in Sydney, Australia, and move the camera.
            LatLng place = new LatLng(Slatitude,Slongitude);



            mMap.addMarker(new MarkerOptions().position(place).title(name).alpha(0.7f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


            mMap.moveCamera(CameraUpdateFactory.newLatLng(place));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(place)      // Sets the center of the map to Mountain View
                    .zoom(12)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

