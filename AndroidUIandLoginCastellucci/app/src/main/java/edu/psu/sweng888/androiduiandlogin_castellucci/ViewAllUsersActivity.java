package edu.psu.sweng888.androiduiandlogin_castellucci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.psu.sweng888.androiduiandlogin_castellucci.adapter.UserAdapter;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao.PersistenceUsers;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class ViewAllUsersActivity extends AppCompatActivity {

    private ArrayList<UserProfile> users;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_users);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        PersistenceUsers persistenceUsers = new PersistenceUsers(this);
        users = persistenceUsers.getDataFromDB();


        mAdapter = new UserAdapter(users);
        mRecyclerView.setAdapter(mAdapter);
    }
}
