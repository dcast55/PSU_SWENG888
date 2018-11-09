package edu.psu.sweng888.androiduiandlogin_castellucci.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.psu.sweng888.androiduiandlogin_castellucci.R;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private String[] mDataset;
    private ArrayList<UserProfile> mUsers;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mLayout;
        TextView textViewUsername, textViewName, textViewEmail, textViewPhone;

        public MyViewHolder(LinearLayout layout) {
            super(layout);
            mLayout = layout;
            textViewUsername = (TextView) layout.findViewById(R.id.text_view_username);
            textViewName = (TextView) layout.findViewById(R.id.text_view_name);
            textViewEmail = (TextView) layout.findViewById(R.id.text_view_email);
            textViewPhone = (TextView) layout.findViewById(R.id.text_view_phone);
        }
    }

//    public UserAdapter(Context context, int resource, List objects) {
//        super(context, resource, objects);
//    }
    public UserAdapter(String[] myDataSet) {
        mDataset = myDataSet;
    }

    public UserAdapter(ArrayList<UserProfile> users) {
        mUsers = users;
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        UserProfile userProfile = mUsers.get(position);

        holder.textViewUsername.setText("User: " + userProfile.getUsername());
        holder.textViewName.setText("Full Name: " + userProfile.getFirstName() + " " + userProfile.getLastName());
        holder.textViewEmail.setText("Email: " + userProfile.getEmail());
        holder.textViewPhone.setText("Phone: " + userProfile.getPhone());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
