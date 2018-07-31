package com.example.youssef.o6uresgisteration;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import static com.example.youssef.o6uresgisteration.Toast.*;

public class MainScreenActivity extends AppCompatActivity {

    private TextView id,name,gpa,level,major,credit;
    private EditText etSearch;
    // Data Base
    private MyHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        etSearch = (EditText) findViewById(R.id.etSearch);
        id = (TextView) findViewById(R.id.id);
        name = (TextView) findViewById(R.id.name);
        gpa = (TextView) findViewById(R.id.gpa);
        level = (TextView) findViewById(R.id.level);
        major = (TextView) findViewById(R.id.major);
        credit = (TextView) findViewById(R.id.credit);
        helper = new MyHelper(this);
        db = helper.getWritableDatabase();

        Intent intent = getIntent();
        String hello = intent.getStringExtra("user");
        Toast("Welcome " + hello,MainScreenActivity.this);

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
    public void find(View view) {
        String ID = etSearch.getText().toString();
        if (!(ID.equals(""))) {
            String query = "select * from registration where id='" + ID + "'";
            Cursor pointer = db.rawQuery(query, null);
            if (pointer.moveToNext() == true) {
                id.setText("" + pointer.getInt(0));
                name.setText(pointer.getString(1));
                gpa.setText("" + pointer.getFloat(2));
                level.setText("" + pointer.getInt(3));
                major.setText(pointer.getString(4));
                credit.setText("" + pointer.getInt(5));
            } else {
                Toast("No data To Show", this);
                id.setText("");
                name.setText("");
                gpa.setText("");
                level.setText("");
                major.setText("");
                credit.setText("");
            }
        }
        else
            Toast("Please Insert Values",MainScreenActivity.this);
    }

    public void allStudents(View view) {
        Intent intent = new Intent(MainScreenActivity.this,AllStudentsActivity.class);
        startActivity(intent);
    }

    public void delete(View view) {

        final String ID = etSearch.getText().toString();

        if (!(ID.equals(""))) {
            String query = "select * from registration where id='" + ID + "'";
            Cursor pointer = db.rawQuery(query, null);
            if (pointer.moveToNext() == true) {

                String message = "Do You Want To Delete This Student";
                new AlertDialog.Builder(this).
                setIcon(R.drawable.warning1).
                setTitle( Html.fromHtml("<font color='#FF7F27'>Delete</font>")).
                setMessage(message).
                setNegativeButton("No",null).
                        setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        String query = "delete from registration where id='" + ID + "'";
                                        db.execSQL(query);
                                        Toast("Deleted", MainScreenActivity.this);
                                    }
                                }).create().show();
            }
            else {
                Toast("No data To Delete", this);
            }
        }
        else
            Toast("Please Insert Value",MainScreenActivity.this);
    }

    public void update(View view) {
        Intent intent = new Intent(MainScreenActivity.this, UpdateActivity.class);
        startActivity(intent);
    }
}


