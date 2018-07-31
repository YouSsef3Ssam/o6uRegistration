package com.example.youssef.o6uresgisteration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import static com.example.youssef.o6uresgisteration.Toast.*;

public class MainActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPass;
    private Button bLogin;
    SharedPreferences pref;
    SharedPreferences.Editor edit;
    Boolean saveLogin;
    CheckBox checkBox;
    // Data Base
    private MyHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        pref = getSharedPreferences("loginpref",MODE_PRIVATE);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        edit = pref.edit();
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
        saveLogin = pref.getBoolean("savelogin",true);
        if (saveLogin){
            etUser.setText(pref.getString("username",null));
            etPass.setText(pref.getString("password",null));
        }
        // Data Base
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

    public void signIn(View view) {
        String name = etUser.getText().toString();
        String pass = etPass.getText().toString();

        if (name.equals("") || pass.equals("") )
            Toast("Please insert Values",this);
        else {
            //Cursor pointer = db.rawQuery("SELECT username , password FROM admin WHERE username = '"+name+"'" +" AND password = '"+pass + "'",null);
            Cursor pointer = db.rawQuery("SELECT username FROM admin WHERE username = '" + name + "'", null);
            String name1 = "";

            if (pointer.moveToNext() == true)
                name1 = pointer.getString(0);

            pointer = db.rawQuery("SELECT password FROM admin WHERE password = '" + pass + "'", null);
            String passw1 = "";

            if (pointer.moveToNext() == true)
                passw1 = pointer.getString(0);

            if (name1.equals(name) && passw1.equals(pass)) {
                Intent intent = new Intent(MainActivity.this, MainScreenActivity.class);

                if (checkBox.isChecked()){
                    edit.putBoolean("savelogin",true);
                    edit.putString("username",name1);
                    edit.putString("password",passw1);
                    edit.commit();
                }

                intent.putExtra("user", name1);
                startActivity(intent);

                //SharedPreferences.Editor editor = pref.edit();
                //editor.putString("Username",name1); // Storing string
                //editor.putString("Password", passw1);
                //editor.apply();
            }
            else if (!name1.equals(name) && !passw1.equals(pass))
                Toast("Incorrect User Name And Password Please Select Admin Registration",this);
            else if (!name1.equals(name))
                Toast("Incorrect User Name",this);

            else if (!passw1.equals(pass))
                Toast("Incorrect Password",this);
        }

    }
    public void studentRegistration(View view) {
        etUser.setText("");
        etPass.setText("");
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void adminRegistration(View view) {
        etUser.setText("");
        etPass.setText("");
        startActivity(new Intent(this,AdminRegisterationActivity.class));
    }
}
