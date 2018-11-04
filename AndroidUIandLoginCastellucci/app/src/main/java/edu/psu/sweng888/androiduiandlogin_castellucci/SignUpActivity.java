package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class SignUpActivity extends AppCompatActivity {

    private EditText mFirstNameEditText = null;
    private EditText mLastNameEditText = null;
    private EditText mUsernameEditText = null;
    private EditText mBirthdayEditText = null;
    private EditText mPhoneEditText = null;
    private EditText mEmailEditText = null;
    private EditText mPasswordEditText = null;

    private Button mConfirmBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstNameEditText = (EditText) findViewById(R.id.editTextFirstName);
        mLastNameEditText = (EditText) findViewById(R.id.editTextLastName);
        mUsernameEditText = (EditText) findViewById(R.id.editTextUsername);
        mBirthdayEditText = (EditText) findViewById(R.id.editTextBirthday);
        mPhoneEditText = (EditText) findViewById(R.id.editTextPhone);
        mEmailEditText = (EditText) findViewById(R.id.editTextEmail);
        mPasswordEditText = (EditText) findViewById(R.id.editTextPasswordSignup);

        mConfirmBtn = (Button) findViewById(R.id.confirmBtn);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile userProfile = new UserProfile(mFirstNameEditText.getText().toString(),
                        mLastNameEditText.getText().toString(),
                        mUsernameEditText.getText().toString(),
                        mBirthdayEditText.getText().toString(),
                        mPhoneEditText.getText().toString(),
                        mEmailEditText.getText().toString(),
                        mPasswordEditText.getText().toString());

                PersistenceUsers persistenceUsers = new PersistenceUsers(getApplicationContext());
                persistenceUsers.insert(userProfile);

                Toast.makeText(getApplicationContext(), R.string.signup_success, Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

    }
}
