package com.example.youssef.o6uresgisteration;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import static com.example.youssef.o6uresgisteration.Toast.*;

public class AdminRegisterationActivity extends AppCompatActivity {

    private EditText etUserName ;
    private EditText etPassword;
    // data base
    private MyHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registeration);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPass);
        // data base
        helper = new MyHelper(this);
        db = helper.getWritableDatabase();

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater convertor = getMenuInflater();
        convertor.inflate(R.menu.menu_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Exit :
                finish();
                break;
        }
        return true;
    }
    public void Register(View view) {
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        if (userName.equals("") || password.equals(""))
            Toast("Please insert Values",this);
        else {

            Cursor pointer = db.rawQuery("SELECT username FROM admin WHERE username = '" + userName + "'", null);
            String name1 = "";

            if (pointer.moveToNext() == true)
                name1 = pointer.getString(0);

            if (userName.equals(name1))
                Toast("Please Change User Name",this);


            else {
                ContentValues row = new ContentValues();
                row.put("username", userName);
                row.put("password", password);
                //long i = db.insert("data", null, row);
                db.insert("admin", null, row);
                Toast("Your Data has been saved successfully",this);
                etUserName.setText("");
                etPassword.setText("");
                startActivity( new Intent(this,MainActivity.class) );

            }
        }

    }
}
