package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

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
                String username = mUserTextView.getText().toString();
                String password = mPassTextView.getText().toString();

                PersistenceUsers persistenceUsers = new PersistenceUsers(getApplicationContext());
                UserProfile user = persistenceUsers.getUserFromDb(username, password);

                if(user != null) {
                    Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                    intent.putExtra("FIRST_NAME", user.getFirstName());
                    intent.putExtra("LAST_NAME", user.getLastName());
                    intent.putExtra("USER", user);

                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_LONG).show();
                }
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
}
