package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class SignUpActivity extends AppCompatActivity {

    private TextView mFirstNameTextView = null;
    private TextView mLastNameTextView = null;
    private TextView mUsernameTextView = null;
    private TextView mBirthdayTextView = null;
    private TextView mPhoneTextView = null;
    private TextView mEmailTextView = null;
    private TextView mPasswordTextView = null;

    private Button mConfirmBtn = null;

    private ConstraintLayout mMainView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirstNameTextView = findViewById(R.id.editTextFirstName);
        mLastNameTextView = findViewById(R.id.editTextLastName);
        mUsernameTextView = findViewById(R.id.editTextUsername);
        mBirthdayTextView = findViewById(R.id.editTextBirthday);
        mPhoneTextView = findViewById(R.id.editTextPhone);
        mEmailTextView = findViewById(R.id.editTextEmail);
        mPasswordTextView = findViewById(R.id.editTextPasswordSignup);

        mConfirmBtn = findViewById(R.id.confirmBtn);

        mMainView = findViewById(R.id.imageViewSplash);

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile userProfile = new UserProfile(mFirstNameTextView.getText().toString(),
                        mLastNameTextView.getText().toString(),
                        mUsernameTextView.getText().toString(),
                        mBirthdayTextView.getText().toString(),
                        mPhoneTextView.getText().toString(),
                        mEmailTextView.getText().toString(),
                        mPasswordTextView.getText().toString());

                PersistenceUsers persistenceUsers = new PersistenceUsers(getApplicationContext());
                persistenceUsers.insert(userProfile);

                Toast.makeText(getApplicationContext(), R.string.signup_success, Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });

    }
}
