package edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.entity.UserProfile;

public class PersistenceUsers implements IPersistence {

    public DatabaseAccess databaseAccess;

    public PersistenceUsers(Context context){
        this.databaseAccess = new DatabaseAccess(context);
    }

    @Override
    public void insert(Object o) {

        // Cast the generic object to have access to the user info.
        UserProfile userProfile = (UserProfile) o;

        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

        // The ContentValues object create a map of values, where the columns are the keys
        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersTable.COLUMN_NAME_FIRST_NAME, userProfile.getFirstName());
        contentValues.put(UsersTable.COLUMN_NAME_LAST_NAME, userProfile.getLastName());
        contentValues.put(UsersTable.COLUMN_NAME_USERNAME, userProfile.getUsername());
        contentValues.put(UsersTable.COLUMN_NAME_BIRTHDAY, userProfile.getBirthday());
        contentValues.put(UsersTable.COLUMN_NAME_PHONE, userProfile.getPhone());
        contentValues.put(UsersTable.COLUMN_NAME_EMAIL, userProfile.getEmail());
        contentValues.put(UsersTable.COLUMN_NAME_PASSWORD, userProfile.getPassword());

        // Insert the ContentValues into the users table.
        sqLiteDatabase.insert(UsersTable.TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();
    }

    @Override
    public void delete(Object o) {

        UserProfile userProfile = (UserProfile) o;

        // Define which column will be the parameter for deleting the record.
        String selection = UsersTable.COLUMN_NAME_USERNAME + "LIKE ? ";

        // Arguments must be identified in the placehold order
        String [] selectionArgs = { userProfile.getUsername().trim() };

        // Get database instance
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();
        sqLiteDatabase.delete(UsersTable.TABLE_NAME, selection, selectionArgs);
    }

    @Override
    public void edit(Object o) {
        // TODO for the students to practice
    }

    @Override
    public ArrayList getDataFromDB() {

        // Create ArrayList of users
        ArrayList<UserProfile> users = null;

        // Instantiate the database.
        SQLiteDatabase sqLiteDatabase = databaseAccess.getWritableDatabase();

        // Gather all the records found for the USERS table.
        Cursor cursor = sqLiteDatabase.rawQuery(UsersTable.select(), null);

        // It will iterate since the first record gathered from the database.
        cursor.moveToFirst();

        // Check if there exist other records in the cursor
        users = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {

            do {
                String firstName = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_LAST_NAME));
                String userName = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_USERNAME));
                String birthday = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_BIRTHDAY));
                String phone = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_EMAIL));
                String password = cursor.getString(cursor.getColumnIndex(UsersTable.COLUMN_NAME_PASSWORD));

                UserProfile userProfile = new UserProfile(firstName, lastName, userName, birthday, phone, email, password);
                users.add(userProfile);
            }
            while (cursor.moveToNext());
        }

        return users;
    }
}
