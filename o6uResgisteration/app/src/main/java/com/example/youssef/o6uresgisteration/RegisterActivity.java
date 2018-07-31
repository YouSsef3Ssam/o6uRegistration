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

public class RegisterActivity extends AppCompatActivity {

    private EditText etName ;
    private EditText etID;
    private EditText etGPA;
    private EditText etLevel;
    private EditText etCreditHour;
    private EditText etMajor;
    // data base
    private MyHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = (EditText) findViewById(R.id.etName);
        etID = (EditText) findViewById(R.id.etId);
        etGPA = (EditText) findViewById(R.id.etGPA);
        etLevel = (EditText) findViewById(R.id.etLevel);
        etCreditHour = (EditText) findViewById(R.id.etCreditHour);
        etMajor = (EditText) findViewById(R.id.etMajor);
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
    public void studentRegister(View view) {

        if (etID.getText().toString().equals("") || etName.getText().toString().equals("") || etGPA.getText().toString().equals("") || etLevel.getText().toString().equals("") || etCreditHour.getText().toString().equals("") || etMajor.getText().toString().equals("") )
            Toast("Please Insert All Values",this);

        else {
            String name = etName.getText().toString();
            String major = etMajor.getText().toString();
            int id = Integer.parseInt(String.valueOf(etID.getText().toString()));
            float gpa = Float.parseFloat(String.valueOf(etGPA.getText().toString()));
            int level = Integer.parseInt(String.valueOf(etLevel.getText().toString()));
            int creditHour = Integer.parseInt(String.valueOf(etCreditHour.getText().toString()));


            Cursor pointer = db.rawQuery("SELECT username FROM registration WHERE username = '" + name + "'", null);
            String name1 = "";
            if (pointer.moveToNext() == true)
                name1 = pointer.getString(0);

            pointer = db.rawQuery("SELECT id FROM registration WHERE id = '" + id + "'", null);
            int id1 = 0;
            if (pointer.moveToNext() == true)
                id1 = pointer.getInt(0);

            if ( name.equals(name1)  && id == id1 )
                Toast("Please Change User Name And E-mail",this);

            else if ( name.equals(name1))
                Toast("Please choose another User Name",this);

            else if ( id == id1 )
                Toast("Please Change id",this);

            else {
                ContentValues row = new ContentValues();
                row.put("id", id);
                row.put("username", name);
                row.put("GPA", gpa);
                row.put("level", level);
                row.put("major", major);
                row.put("Credit_Hour", creditHour);
                db.insert("Registration", null, row);
                Toast("Your Data has been saved successfully",this);
                etName.setText("");
                etID.setText("");
                etMajor.setText("");
                etLevel.setText("");
                etCreditHour.setText("");
                etGPA.setText("");
                startActivity( new Intent(this,MainActivity.class) );

            }
        }

    }
}