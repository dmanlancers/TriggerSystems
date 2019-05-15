package com.wetrig.dev.wetrig.Fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wetrig.dev.wetrig.Authentication.RegisterActivity;
import com.wetrig.dev.wetrig.ImageDownloader;
import com.wetrig.dev.wetrig.Profile.ProfileActivity;
import com.wetrig.dev.wetrig.R;

import org.apache.http.entity.StringEntity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by darkangel on 09/06/16.
 */
public class ProfileFragment extends Fragment {

    private ProfileActivity pActivity;
    private CoordinatorLayout profileLayout;
    private EditText inputGender, inputFirstName, inputLastName, inputEmail, inputBirthdate, inputBio;
    private TextInputLayout  inputLayoutGender, inputLayoutFirstName, inputLayoutLastName, inputLayoutEmail,inputLayoutBirthDAte,inputLayoutBio;
    private Button  upload, save;
    private ImageView imgUser;
    private TextView uploadTextView;
    private SharedPreferences settings;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    public static final String PREFS_NAME = "MyApp_Settings";
    private static final int SELECT_PICTURE = 1;
    private static final int SELECT_PHOTO = 100;
    private String imageUser, fileName,gender, firstName, lastName, email, birthDate, bio, gender_value, stringPicture ;
    private int mYear, mMonth, mDay;
    private ProgressDialog PD;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_fragment, parentViewGroup, false);
        profileLayout = (CoordinatorLayout) view.findViewById(R.id.profile);
        pActivity = (ProfileActivity) getActivity();
        PD = new ProgressDialog(getActivity());
        PD.setMessage(getResources().getString(R.string.msg_update_profile_progressBar));
        PD.setCancelable(false);

        inputLayoutGender = (TextInputLayout) view.findViewById(R.id.input_layout_gender);
        radioMale = (RadioButton) view.findViewById(R.id.radioMale);
        radioFemale = (RadioButton) view.findViewById(R.id.radioFemale);
        inputLayoutFirstName = (TextInputLayout) view.findViewById(R.id.input_layout_first_name);
        inputLayoutLastName = (TextInputLayout) view.findViewById(R.id.input_layout_last_name);
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputLayoutBirthDAte = (TextInputLayout) view.findViewById(R.id.input_layout_birth_date);
        inputLayoutBio = (TextInputLayout) view.findViewById(R.id.input_layout_bio);

        uploadTextView = (TextView) view.findViewById(R.id.uploadImageView);
        inputFirstName= (EditText) view.findViewById(R.id.first_name);
        inputLastName = (EditText) view.findViewById(R.id.last_name);
        inputEmail = (EditText) view.findViewById(R.id.email);
        inputBirthdate = (EditText) view.findViewById(R.id.birth_date);
        inputBio = (EditText) view.findViewById(R.id.bio);

        save = (Button) view.findViewById(R.id.btn_save);
        upload = (Button) view.findViewById(R.id.uploadImage);
        settings = getActivity().getSharedPreferences(PREFS_NAME, getActivity().MODE_PRIVATE);


        imageUser = pActivity.getUserData().get(0).getUserPhoto();;
        gender = pActivity.getUserData().get(0).getUserGender();
        firstName =  pActivity.getUserData().get(0).getUserName();

        lastName = pActivity.getUserData().get(0).getUserSurname();

        birthDate = pActivity.getUserData().get(0).getUserBirthdate();
        bio = pActivity.getUserData().get(0).getUserBio();;

        imgUser = ( ImageView) view.findViewById(R.id.profileImageView);
Log.e("Valor",""+ imageUser);


        new ImageDownloader(imgUser).execute("http://dev.wetrig.com/"+ imageUser);
        email = pActivity.getUserData().get(0).getUserEmail();
        inputFirstName.setText(firstName);
        inputLastName.setText(lastName);
        inputEmail.setText(email);
        inputBirthdate.setText(birthDate);
        inputBio.setText(bio);


        if (gender.equalsIgnoreCase("male"))
        {
            radioMale.setChecked(true);
            gender_value ="male";

        }

        if (gender.equalsIgnoreCase("female"))
        {
            radioFemale.setChecked(true);
            gender_value ="female";
        }

        upload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);

            }
        });

        inputBirthdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate=Calendar.getInstance();
                mYear=mcurrentDate.get(Calendar.YEAR);
                mMonth=mcurrentDate.get(Calendar.MONTH);
                mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(pActivity, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int year, int month, int day) {
                        // TODO Auto-generated method stub
                            /*      Your code   to get date and time    */
                        inputBirthdate.setText(year + "/" + month + "/" + day);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();



            }
        });



      save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                submitForm();
                //PD.show();
                String image = stringPicture;
                Log.i("PICTURE", " " + stringPicture);
            String gender = gender_value;
                String first_name = inputLayoutFirstName.getEditText().getText().toString().trim();
                String last_name = inputLayoutLastName.getEditText().getText().toString().trim();
                String birth_date = inputLayoutBirthDAte.getEditText().getText().toString().trim();
                String bio = inputLayoutBio.getEditText().getText().toString().trim();
                String email = inputLayoutEmail.getEditText().getText().toString().trim();

                pActivity.getProfileUpdater(image,gender,first_name, last_name,email, birth_date,bio );

                Log.i("PICTURE", " " + email);
            }


        });

        return view;

    }



  public  String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();

        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);

        fileName = picturePath.substring(picturePath.lastIndexOf('/') + 1);

        cursor.close();


        return picturePath;
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, this.getActivity());
            Log.i("PICTURE", "Path: " + path);
            Bitmap bm = BitmapFactory.decodeFile(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//added lines
            bm.recycle();
            bm = null;
//added lines
            ;
            byte[] b = baos.toByteArray();
            stringPicture=Base64.encodeToString(b, Base64.DEFAULT);

            if (path != null) {
                uploadTextView.setText(fileName);
                // setFullImageFromFilePath(upload, path);
            }
        }
    }
    private void submitForm() {




        if (!validateFirstName()) {
            return;
        }
        if (!validateLastName()) {
            return;
        }

        if (!validateBirthDate()) {
            return;
        }


    }


    private boolean validateFirstName() {
        if (inputFirstName.getText().toString().trim().isEmpty()) {
            inputLayoutFirstName.setError(getString(R.string.err_msg_password));
            requestFocus(inputFirstName);
            return false;
        } else {
            inputLayoutFirstName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLastName() {
        if (inputLastName.getText().toString().trim().isEmpty()) {
            inputLayoutLastName.setError(getString(R.string.err_msg_password));
            requestFocus(inputLastName);
            return false;
        } else {
            inputLayoutLastName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateBirthDate() {
        if (inputBirthdate.getText().toString().trim().isEmpty()) {
            inputLayoutBirthDAte.setError(getString(R.string.err_msg_password));
            requestFocus(inputBirthdate);
            return false;
        } else {
            inputLayoutBirthDAte.setErrorEnabled(false);
        }

        return true;
    }








    private void requestFocus(View view) {
        if (view.requestFocus()) {
            pActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {

            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {


                case R.id.first_name:
                    validateFirstName();
                    break;
                case R.id.last_name:
                    validateLastName();
                    break;
                case R.id.birth_date:
                    validateBirthDate();
                    break;




            }

        }
    }
}
