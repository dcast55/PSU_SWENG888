package edu.psu.sweng888.androiduiandlogin_castellucci.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.psu.sweng888.androiduiandlogin_castellucci.R;
import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class UserAdapter extends ArrayAdapter {

    public UserAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_list_item, parent, false);
        }

        // Get the object located at this position in the list
        UserProfile userProfile = (UserProfile) getItem(position);

        // Find the TextView in the xml layout wit the ID
        TextView textViewUsername = (TextView) listItemView.findViewById(R.id.text_view_username);

        // Get the username from the current UserProfile object and
        // set this text on the name TextView
        textViewUsername.setText(userProfile.getUsername());

        TextView textViewName = (TextView) listItemView.findViewById(R.id.text_view_name);
        textViewName.setText(userProfile.getFirstName() + userProfile.getLastName());

        return listItemView;
    }
}
