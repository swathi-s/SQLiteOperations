package com.example.user.sqliteoperations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.Toast;

/**
 * Created by user on 9/14/2016.
 */
public class DatabaseAdapter {

    DatabaseHelper helper;
    public DatabaseAdapter (Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public long insertData(String name, String password)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,name);
        contentValues.put(DatabaseHelper.PASSWORD,password);
        long id = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public String getAllData(){
        SQLiteDatabase db= helper.getWritableDatabase();

        String[] columns = {DatabaseHelper.UID,DatabaseHelper.NAME,DatabaseHelper.PASSWORD};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            int index1 = cursor.getColumnIndex(DatabaseHelper.UID);
            int cid = cursor.getInt(index1);

            int index2 = cursor.getColumnIndex(DatabaseHelper.NAME);
            String name = cursor.getString(index2);

            int index3 = cursor.getColumnIndex(DatabaseHelper.PASSWORD);
            String password = cursor.getString(index3);

           buffer.append(cid+" "+name+" "+password+"\n");
        }
        return buffer.toString();
    }

    public String getData(String name)
    {
        SQLiteDatabase db= helper.getWritableDatabase();

        String[] columns = {DatabaseHelper.NAME,DatabaseHelper.PASSWORD};
        String[] selectionArgs = {name};
        //Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,DatabaseHelper.NAME+" = '"+name+"'",null,null,null,null);

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,columns,DatabaseHelper.NAME+" =? ",selectionArgs,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext())
        {

            int index2 = cursor.getColumnIndex(DatabaseHelper.NAME);
            String personName = cursor.getString(index2);

            int index3 = cursor.getColumnIndex(DatabaseHelper.PASSWORD);
            String password = cursor.getString(index3);

            buffer.append(personName+" "+password+"\n");
        }
        return buffer.toString();
    }
    static class DatabaseHelper  extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + PASSWORD + " VARCHAR(255))";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context, "Constructor method is called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                Toast.makeText(context, "onCreate method called", Toast.LENGTH_SHORT).show();
                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                Toast.makeText(context, "exception is " + e, Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "onUpdate method called", Toast.LENGTH_SHORT).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Toast.makeText(context, "exception is " + e, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
