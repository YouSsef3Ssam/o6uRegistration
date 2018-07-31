package com.example.youssef.o6uresgisteration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import static com.example.youssef.o6uresgisteration.Toast.Toast;

public class AllStudentsActivity extends AppCompatActivity {

    TextView id, name, gpa, major, level, credit;
    private MyHelper helper;
    private SQLiteDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        helper = new MyHelper(this);
        db = helper.getWritableDatabase();
        TableLayout table = (TableLayout) findViewById(R.id.tAllStudent);

        Cursor  cursor = db.rawQuery("select * from registration",null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                TableRow row = new TableRow(this);
                id = new TextView(this);
                name = new TextView(this);
                gpa = new TextView(this);
                level = new TextView(this);
                major = new TextView(this);
                credit = new TextView(this);
                getReady();

                id.setText(""+cursor.getInt(0));
                name.setText(cursor.getString(1));
                gpa.setText(""+cursor.getFloat(2));
                level.setText(""+cursor.getInt(3));
                major.setText(cursor.getString(4));
                credit.setText(""+cursor.getInt(5));

                table.addView(row);
                row.addView(id);
                row.addView(name);
                row.addView(gpa);
                row.addView(level);
                row.addView(major);
                row.addView(credit);

                cursor.moveToNext();
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void getReady(){
        id.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        id.setTextColor(Color.parseColor("#FFED7733"));
        id.setTextSize(14);

        name.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        name.setTextColor(Color.parseColor("#FFED7733"));
        name.setTextSize(14);

        gpa.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        gpa.setTextColor(Color.parseColor("#FFED7733"));
        gpa.setTextSize(14);

        major.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        major.setTextColor(Color.parseColor("#FFED7733"));
        major.setTextSize(14);

        level.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        level.setTextColor(Color.parseColor("#FFED7733"));
        level.setTextSize(14);

        credit.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        credit.setTextColor(Color.parseColor("#FFED7733"));
        credit.setTextSize(14);


    }

}
