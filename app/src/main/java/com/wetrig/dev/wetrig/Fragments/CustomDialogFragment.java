package com.wetrig.dev.wetrig.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wetrig.dev.wetrig.MainActivity;
import com.wetrig.dev.wetrig.R;


/**
 * Created by darkangel on 29/07/16.
 */
public class CustomDialogFragment extends DialogFragment {

    private MainActivity mActivity;
    private ImageView edit, delete,cancel;
    private TextView  title;
    private SharedPreferences settings;
    public static final String PREFS_NAME = "MyApp_Settings";
    private String email ,name,s_id,typeId, description, placePicker,checkboxValue,Lat, Lon ;
    private  UpdateSystem  dataPasserS;
    public interface UpdateSystem {


        public void UpdateSystem( String id, String typeId,String name, String descripiton, String Address, String s_public,
                                  String longitude, String latitude);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasserS = (UpdateSystem) context;
    }
    public void passUpdateSystem(String id, String typeId,String name, String descripiton, String Address, String s_public,
                                 String longitude, String latitude) {

        dataPasserS.UpdateSystem(id, typeId,name, descripiton, Address,s_public,
                longitude, latitude);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample_dialog, container, false);


        edit = (ImageView) view.findViewById(R.id.edit);
        delete= (ImageView) view.findViewById(R.id.delete);
        title = (TextView) view.findViewById(R.id.title);
        mActivity = (MainActivity)getActivity();
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
        title.setText(name);
        Log.e("TESTES:",""+email+s_id+typeId+name+description+checkboxValue+placePicker+Lat+Lon);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mActivity.UpdateSystem(s_id,typeId,name,description,placePicker,checkboxValue,Lat,Lon);
                dismiss();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Delete System");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();

                        mActivity.deleteSystem(email,s_id);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        return view;
    }
}
