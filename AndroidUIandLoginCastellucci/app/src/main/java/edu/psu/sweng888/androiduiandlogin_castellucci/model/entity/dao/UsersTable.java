package edu.psu.sweng888.androiduiandlogin_castellucci.model.entity.dao;

/**
 * @author ezt157
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the same
 * package. Therefore, you can change a column name in one place and have it propagate throughout
 * your code.
 */
public class UsersTable {

    /** Defining the Table Content **/
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_FIRST_NAME = "firstName";
    public static final String COLUMN_NAME_LAST_NAME = "lastName";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_BIRTHDAY = "birthday";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_FIRST_NAME + " TEXT," +
                COLUMN_NAME_LAST_NAME + " TEXT," +
                COLUMN_NAME_USERNAME  + " TEXT," +
                COLUMN_NAME_BIRTHDAY + " TEXT," +
                COLUMN_NAME_PHONE + " TEXT," +
                COLUMN_NAME_EMAIL + " TEXT," +
                COLUMN_NAME_PASSWORD + " TEXT)" );
    }

    public static String select(){
        return new String("SELECT * FROM "+ TABLE_NAME);

    }

    public static final String delete(){
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}




