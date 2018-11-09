package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class MainActivity extends AppCompatActivity {

    private Button mLoginBtn = null;
    private Button mSignUpBtn = null;

    private TextView mUserTextView = null;
    private TextView mPassTextView = null;

    private FirebaseAuth mAuth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserTextView = (TextView) findViewById(R.id.editTextUser);
        mPassTextView = (TextView) findViewById(R.id.editTextPassword);

        mLoginBtn = (Button) findViewById(R.id.button_login);
        mSignUpBtn = (Button) findViewById(R.id.button_signup);

        mAuth = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mUserTextView.getText().toString();
                String password = mPassTextView.getText().toString();

                signIn(email, password);
            }
        });

        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            PersistenceUsers persistenceUsers = new PersistenceUsers(getApplicationContext());
                            UserProfile userProfile = persistenceUsers.getUserFromDb(user.getEmail());

                            Intent intent = new Intent(MainActivity.this, LoginSuccessActivity.class);
                            intent.putExtra("USER", userProfile);

                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
