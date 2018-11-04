package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;

public class MainActivity extends AppCompatActivity {

    private Button mLoginBtn = null;
    private Button mSignUpBtn = null;

    private TextView mUserTextView = null;
    private TextView mPassTextView = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserTextView = (TextView) findViewById(R.id.editTextUser);
        mPassTextView = (TextView) findViewById(R.id.editTextPassword);

        mLoginBtn = (Button) findViewById(R.id.button_login);
        mSignUpBtn = (Button) findViewById(R.id.button_signup);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginSuccessActivity.class));
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
