package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSuccessActivity extends AppCompatActivity {

    private Button mViewUsersBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        mViewUsersBtn = findViewById(R.id.view_all_users_btn);

        mViewUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSuccessActivity.this, ViewAllUsersActivity.class));
            }
        });
    }
}
