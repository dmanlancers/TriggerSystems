package com.wetrig.dev.wetrig.Fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.wetrig.dev.wetrig.Authentication.RegisterActivity;
import com.wetrig.dev.wetrig.R;

/**
 * Created by darkangel on 09/06/16.
 */
public class RegisterFragment extends Fragment {

    private RegisterActivity rActivity;
    private CoordinatorLayout registerLayout;
    private EditText inputEmail, inputPassword, inputRepeatePassword, inputFirstQuestion,inputSecondQuestion;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword, inputLayoutRepeatePassword, inputLayoutFirstQuestion, inputLayoutSecondQuestion;
    private Button btnSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.register_fragment, parentViewGroup, false);
        registerLayout = (CoordinatorLayout) view.findViewById(R.id.register);
        rActivity = (RegisterActivity) getActivity();
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);
        inputLayoutRepeatePassword = (TextInputLayout) view.findViewById(R.id.input_layout_RepeatePassword);
        inputLayoutFirstQuestion = (TextInputLayout)    view.findViewById(R.id.input_layout_first_question) ;
        inputLayoutSecondQuestion = (TextInputLayout)    view.findViewById(R.id.input_layout_second_question) ;
        inputEmail = (EditText) view.findViewById(R.id.input_email);
        inputPassword = (EditText) view.findViewById(R.id.input_password);
        inputRepeatePassword = (EditText)   view.findViewById(R.id.input_RepeatePassword);
        inputFirstQuestion =   (EditText)   view.findViewById(R.id.input_firstQuestion);
        inputSecondQuestion =   (EditText)   view.findViewById(R.id.input_secondQuestion);
        btnSignUp = (Button) view.findViewById(R.id.btn_signup);

        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword));
        inputRepeatePassword.addTextChangedListener(new MyTextWatcher(inputRepeatePassword));
        inputFirstQuestion.addTextChangedListener( new MyTextWatcher(inputFirstQuestion));
        inputSecondQuestion.addTextChangedListener( new MyTextWatcher(inputSecondQuestion));

        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String user = inputLayoutEmail.getEditText().getText().toString().trim();
                String password = inputLayoutPassword.getEditText().getText().toString().trim();
                String reason = inputLayoutFirstQuestion.getEditText().getText().toString().trim();
                String other_reason = inputLayoutSecondQuestion.getEditText().getText().toString().trim();
                String reference = "";
                String other_ref = "";
                submitForm();
                rActivity.getRegisterCallback(user,password, reference, other_ref,reason, other_reason);
            }

        });

        return view;

    }

    private void submitForm() {


        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }
        if (!validateConfirmPassword()) {
            return;
        }

        if (!validateFirstQuestion()) {
            return;
        }
        if (!validateSecondQuestion()) {
            return;
        }

    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRepeatePassword() {
        if (inputPassword.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_Repeatepassword));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateConfirmPassword() {

        String strPass1 = inputPassword.getText().toString();
        String strPass2 = inputRepeatePassword.getText().toString();
        Boolean validate = strPass1.equals(strPass2);
        if (!validate) {
            inputLayoutPassword.setError(getString(R.string.err_msg_ConfirmPassword));
            requestFocus(inputRepeatePassword);
            return false;
        } else {
            inputLayoutRepeatePassword.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateFirstQuestion() {
        if (inputFirstQuestion.getText().toString().trim().isEmpty()) {
            inputLayoutFirstQuestion.setError(getString(R.string.err_msg_first_question));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutFirstQuestion.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSecondQuestion() {
        if (inputSecondQuestion.getText().toString().trim().isEmpty()) {
            inputLayoutSecondQuestion.setError(getString(R.string.err_msg_second_question));
            requestFocus(inputPassword);
            return false;
        } else {
            inputLayoutSecondQuestion.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            rActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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

                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_layout_RepeatePassword:
                    validateRepeatePassword();
                    break;
                case R.id.input_layout_first_question:
                    validateFirstQuestion();
                    break;

                case R.id.input_layout_second_question:
                    validateSecondQuestion();
                    break;


            }

        }
    }
}
