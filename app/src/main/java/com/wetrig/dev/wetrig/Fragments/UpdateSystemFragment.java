package com.wetrig.dev.wetrig.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.Adapters.PlacesAutoCompleteAdapter;
import com.wetrig.dev.wetrig.Fragments.MenuFragmentItems.LocationFragment;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;
import com.wetrig.dev.wetrig.Utils;

/**
 * Created by darkangel on 27/07/16.
 */
public class UpdateSystemFragment extends Fragment{

    private PlacePicker.IntentBuilder builder;
    private PlacesAutoCompleteAdapter mPlacesAdapter;
    private Button pickerBtn;
    private AutoCompleteTextView myLocation;
    private static final int PLACE_PICKER_FLAG = 1;
    private EditText inputName, inputDesc, inputLocal;
    private CheckBox Spublic;
    private Button submit;
    private ImageView pinner;
    private String imgUrl ="http://dev.wetrig.com/images/device_on.png";
    private String checkboxValue,s_id,placePicker,name,description,email,Lat,Lon,typeId;
    private int PLACE_PICKER_REQUEST = 1;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private PlaceAutocompleteFragment autocompleteFragment;
    private LinearLayout placeFrag;
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private LatLng coord;
    private  double Latitude, Longitude;
    private TextInputLayout inputLayoutName,inputLayoutLocation;
    private static final LatLngBounds BOUNDS_GREATER_SYDNEY = new LatLngBounds(
            new LatLng(-34.041458, 150.790100), new LatLng(-33.682247, 151.383362));
    protected GoogleApiClient mGoogleApiClient;
    private MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_system_fragment, parentViewGroup, false);


        mActivity = (MainActivity) getActivity();
        inputName = (EditText) view.findViewById(R.id.input_name);
        inputDesc = (EditText) view.findViewById(R.id.input_desc);
        Spublic = (CheckBox) view.findViewById(R.id.public_checkbox);
        submit = (Button) view.findViewById(R.id.systemSubmit);
        pinner = (ImageView) view.findViewById(R.id.pinner);
        placeFrag = (LinearLayout) view.findViewById(R.id.fragLayout);
        inputLocal = (EditText) view.findViewById(R.id.placePicker);
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        inputLayoutLocation = (TextInputLayout) view.findViewById(R.id.input_layout_placePicker);
        pinner.setClickable(true);
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        email = settings.getString("key", "");
        Bundle args = getArguments();
        s_id=args.getString("s_id");
        typeId= args.getString("s_type_id");
        name=args.getString("s_name");
        description=args.getString("s_desc");
        placePicker=args.getString("s_address");
        checkboxValue=args.getString("s_public" );
        Lat=args.getString("s_latitude");
        Lon=args.getString("s_longitude");
        Log.e("TESTES:",""+s_id+email+typeId+name+description+checkboxValue+placePicker+Lat+Lon);
        Picasso.with(getActivity()).load(imgUrl).into(pinner);


      /*  autocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                coord=place.getLatLng();
                Latitude = coord.latitude;
                Longitude = coord.longitude;
                Lat  = Double.toString(Latitude);
                Lon  = Double.toString(Longitude);

                placePicker= String.format(" %s", place.getAddress());
                // TODO: Get info about the selected place.
                Log.i("", "AUTO: " + place.getName()+ coord);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("", "An error occurred: " + status);
            }
        });*/
        inputName.setText(name);
        inputDesc.setText(description);
        inputLocal.setText(placePicker);
        if(checkboxValue.equals("Yes")){


            Spublic.setChecked(true);
        }

        pinner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //Toast.makeText(mActivity.getApplicationContext(), "System",  Toast.LENGTH_SHORT).show();


                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }

        });


        Spublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // update your model (or other business logic) based on isChecked


                if(Spublic.isChecked()){

                    checkboxValue = "Yes";

                }
                else{
                    checkboxValue="No";

                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = inputName.getText().toString().trim();
                description= inputDesc.getText().toString().trim();
                //Toast.makeText(mActivity.getApplicationContext(), "Val:",checkboxValue+placePicker+ Toast.LENGTH_SHORT).show();
                Log.i("val",""+typeId+name+description+placePicker+checkboxValue+email+Lat+Lon);
                if (name.isEmpty() ) {
                    inputLayoutName.setError(getString(R.string.empty_name));
                    requestFocus(inputName);

                } else if(placePicker.isEmpty() ) {
                    inputLayoutLocation.setError(getString(R.string.localization_error));
                    requestFocus(inputLocal);


                }
                else{

                    mActivity.updateSystem(email,name,typeId,checkboxValue,description,placePicker,Lat,Lon,s_id);

                }
            }

        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == getActivity().RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                placePicker= String.format(" %s", place.getAddress());
                placeFrag.setVisibility(View.GONE);
                Toast.makeText(getActivity(), placePicker, Toast.LENGTH_LONG).show();
                inputLocal.setText(placePicker);

                Log.i("ONDE",""+ placePicker);


                coord= place.getLatLng();
                Latitude = coord.latitude;
                Longitude = coord.longitude;
                Lat  = Double.toString(Latitude);
                Lon  = Double.toString(Longitude);
            }


        }




    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        AddSystemF2Fragment f = (AddSystemF2Fragment) getFragmentManager()
                .findFragmentById(R.id.autocomplete_fragment);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }*/










}



