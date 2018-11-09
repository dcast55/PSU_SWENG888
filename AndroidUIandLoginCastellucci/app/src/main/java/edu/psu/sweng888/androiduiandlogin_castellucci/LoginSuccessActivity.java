package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class LoginSuccessActivity extends AppCompatActivity {

    private Button mViewUsersBtn = null;
    private TextView mTextView = null;
    private TextView mTextViewUserDetails = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        mTextView = (TextView) findViewById(R.id.login_success_textView);
        mTextViewUserDetails = (TextView) findViewById(R.id.login_success_user_details_textview);
        mViewUsersBtn = (Button) findViewById(R.id.view_all_users_btn);

        Intent intent = getIntent();
        UserProfile user = (UserProfile) intent.getSerializableExtra("USER");

        String message = mTextView.getText().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append(" " + user.getFirstName());
        sb.append(" " + user.getLastName());
        mTextView.setText(sb.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("USER DETAILS");
        sb2.append("\nFirst Name: " + user.getFirstName());
        sb2.append("\nLastName: " + user.getLastName());
        sb2.append("\nUsername: " + user.getUsername());
        sb2.append("\nBirthday: " + user.getBirthday());
        sb2.append("\nPhone: " + user.getPhone());
        sb2.append("\nEmail: " + user.getEmail());

        mTextViewUserDetails.setText(sb2.toString());

        mViewUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSuccessActivity.this, ViewAllUsersActivity.class));
            }
        });
    }
}
