package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import edu.psu.sweng888.androiduiandlogin_castellucci.adapter.UserAdapter;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class ViewAllUsersActivity extends AppCompatActivity {

    private ListView listView;
    private UserAdapter userAdapter;
    private ArrayList<UserProfile> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        listView = (ListView) findViewById(R.id.list_view_users);

        PersistenceUsers persistenceUsers = new PersistenceUsers(this);
        persistenceUsers.insert(new UserProfile(
                "Dan", "Cas", "dcast55", "77", "77", "@gmail", "pass"));
        users = persistenceUsers.getDataFromDB();

        userAdapter = new UserAdapter(getApplicationContext(), R.layout.custom_list_item, users);

        listView.setAdapter(userAdapter);
    }
}
