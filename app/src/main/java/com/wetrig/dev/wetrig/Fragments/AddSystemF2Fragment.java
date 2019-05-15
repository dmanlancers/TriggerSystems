package com.wetrig.dev.wetrig.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.Adapters.PlacesAutoCompleteAdapter;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;


/**
 * Created by darkangel on 27/07/16.
 */
public class AddSystemF2Fragment extends Fragment{

    private PlacePicker.IntentBuilder builder;
    private PlacesAutoCompleteAdapter mPlacesAdapter;
    private Button pickerBtn;
    private AutoCompleteTextView myLocation;
    private static final int PLACE_PICKER_FLAG = 1;
    private EditText inputName, inputDesc, inputLocal;
    private CheckBox Spublic;
    private Button submit;
    private ImageView pinner;
    private TextView localization,inputLayoutLocation;
    private String imgUrl ="http://dev.wetrig.com/images/device_on.png";
    private String checkboxValue,placeAuto,placePicker,name,description,email,Lat,Lon,typeId;
    private int PLACE_PICKER_REQUEST = 1;
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private PlaceAutocompleteFragment autocompleteFragment;
    private LinearLayout placeFrag;
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private LatLng coord;
    private  double Latitude, Longitude;
    private TextInputLayout inputLayoutName;

    protected GoogleApiClient mGoogleApiClient;
    private MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_systems_f2_fragment, parentViewGroup, false);


        mActivity = (MainActivity) getActivity();
        inputName = (EditText) view.findViewById(R.id.input_name);
        inputDesc = (EditText) view.findViewById(R.id.input_desc);
        Spublic = (CheckBox) view.findViewById(R.id.public_checkbox);
        submit = (Button) view.findViewById(R.id.systemSubmit);
        pinner = (ImageView) view.findViewById(R.id.pinner);
        placeFrag = (LinearLayout) view.findViewById(R.id.fragLayout);
        inputLocal = (EditText) view.findViewById(R.id.placePicker);
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        //inputLayoutLocation = (TextView) view.findViewById(R.id.txtLocation);
        pinner.setClickable(true);
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);
        email = settings.getString("key", "");
        Bundle args = getArguments();
        typeId = args.getString("sys_type_id");
        Picasso.with(getActivity()).load(imgUrl).into(pinner);


            /* autocompleteFragment = (PlaceAutocompleteFragment)
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
                Log.i("val",""+typeId+name+description+placePicker+checkboxValue+email+Lat+Lon);
                if (name.isEmpty() ) {
                    inputLayoutName.setError(getString(R.string.empty_name));
                    requestFocus(inputName);

                } else if(placePicker.isEmpty() ) {
                    inputLayoutLocation.setError(getString(R.string.localization_error));
                    requestFocus(inputLocal);

                }else{

                    mActivity.addSystem(email,name,typeId,checkboxValue,description,placePicker,Lat,Lon);

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


}



