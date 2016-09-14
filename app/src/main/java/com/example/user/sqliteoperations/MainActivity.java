package com.example.user.sqliteoperations;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName, password, name;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);

        name = (EditText) findViewById(R.id.editText3);

        databaseAdapter = new DatabaseAdapter(this);
    }

    public void addUser(View view) {

        String user = userName.getText().toString();
        String pass = password.getText().toString();

        long id = databaseAdapter.insertData(user,pass);

        if(id < 0)
        {
            Toast.makeText(this,"unsuccessfull",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Successfully added the user to db with id ",Toast.LENGTH_SHORT).show();
        }
    }

    public void viewDetails(View view) {

        String data = databaseAdapter.getAllData();
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }

    public void getDetails(View view) {
        String s1 = name.getText().toString();

        String s2 = databaseAdapter.getData(s1);

        if(s2.isEmpty())
        {
            Toast.makeText(this,"not found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,s2,Toast.LENGTH_SHORT).show();
        }

    }
}
