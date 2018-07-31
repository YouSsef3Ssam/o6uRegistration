package com.example.youssef.o6uresgisteration;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import static com.example.youssef.o6uresgisteration.Toast.*;

public class UpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText uId, name, gpa, major, level, credit;
    String type;
    ContentValues contentValues;
    String name1, major1;
    float gpa1;
    int level1, credit1;

    private MyHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        uId = (EditText) findViewById(R.id.uId);
        helper = new MyHelper(this);
        db = helper.getWritableDatabase();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] years = {"All","Name","GPA","Level","Major","Credit Hour"};
        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(UpdateActivity.this, R.layout.spinner_text, years );
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner.setAdapter(langAdapter);
        spinner.setOnItemSelectedListener(UpdateActivity.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear);
        type = parent.getItemAtPosition(position).toString();
        switch (type)
        {
            case "All":
                name = new EditText(this);
                gpa = new EditText(this);
                major = new EditText(this);
                level = new EditText(this);
                credit = new EditText(this);

                getReady(name,"Username");
                getReady(gpa,"GPA");
                getReady(level,"Level");
                getReady(major,"Major");
                getReady(credit,"Credit Hour");

                linearLayout.removeAllViews();
                name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                gpa.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                major.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                level.setInputType(InputType.TYPE_CLASS_NUMBER);
                credit.setInputType(InputType.TYPE_CLASS_NUMBER);

                linearLayout.addView(name);
                linearLayout.addView(gpa);
                linearLayout.addView(level);
                linearLayout.addView(major);
                linearLayout.addView(credit);

                break;

            case "Name":
                name = new EditText(this);
                getReady(name,"Username");
                linearLayout.removeAllViews();
                name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                linearLayout.addView(name);
                break;

            case "GPA":
                gpa = new EditText(this);
                getReady(gpa,"GPA");
                linearLayout.removeAllViews();
                gpa.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                linearLayout.addView(gpa);
                break;

            case "Major":
                major = new EditText(this);
                getReady(major,"Major");
                linearLayout.removeAllViews();
                major.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                linearLayout.addView(major);
                break;

            case "Level":
                level = new EditText(this);
                getReady(level,"Level");
                linearLayout.removeAllViews();
                level.setInputType(InputType.TYPE_CLASS_NUMBER);
                linearLayout.addView(level);
                break;

            case "Credit Hour":
                credit = new EditText(this);
                getReady(credit,"Credit Hour");
                linearLayout.removeAllViews();
                credit.setInputType(InputType.TYPE_CLASS_NUMBER);
                linearLayout.addView(credit);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void getReady(EditText editText, String name){
        editText.setTextColor(Color.parseColor("#FFED7733"));
        editText.setHint(name);
        editText.setHintTextColor(Color.parseColor("#a12000"));
        editText.getBackground().setColorFilter(getResources().getColor(R.color.toast),
                PorterDuff.Mode.SRC_ATOP);

        if (name == "Username")
        {
            editText.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_person_outline_black_24dp, 0, 0, 0);
        }
        else
        {
            editText.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_create_black_24dp, 0, 0, 0);
        }
    }

    public void save1(View view) {
        String id = uId.getText().toString();

        String name2 = name.getText().toString();
        String major2 = major.getText().toString();
        String gpa2 = gpa.getText().toString();
        String level2 = level.getText().toString();
        String credit2 = credit.getText().toString();

        if (id.equals("") )
            Toast("Please Insert ID",this);
        else
        {
            switch (type){
                case "All":
                    if (name2.equals("") || gpa2.equals("") || level2.equals("") || major2.equals("") || credit2.equals(""))
                        Toast("Please Insert All Values",UpdateActivity.this);
                    else
                        update(id);
                    break;

                case "Name":
                    if (name2.equals(""))
                        Toast("Please Insert Username",UpdateActivity.this);
                    else
                        update(id);
                    break;

                case "GPA":
                    if (gpa2.equals(""))
                        Toast("Please Insert GPA",UpdateActivity.this);
                    else
                        update(id);
                    break;

                case "Level":
                    if (level2.equals(""))
                        Toast("Please Insert Level",UpdateActivity.this);
                    else
                        update(id);
                    break;

                case "Major":
                    if (major2.equals(""))
                        Toast("Please Insert Major",UpdateActivity.this);
                    else
                        update(id);
                    break;

                case "Credit Hour":
                    if (credit2.equals(""))
                        Toast("Please Insert Credit Hour",UpdateActivity.this);
                    else
                        update(id);
                    break;
            }
        }

    }
    public void update(String id){

        String []id1  = {uId.getText().toString()};

        String query = "Select * from registration where id = '"+ id +"'" ;
        Cursor pointer  = db.rawQuery(query,null);
        if(pointer.moveToNext())
        {

            switch (type){
                case "All":
                    contentValues = new ContentValues();
                    name1 = name.getText().toString();
                    major1 = major.getText().toString();
                    gpa1 = Float.parseFloat(String.valueOf(gpa.getText().toString()));
                    level1 = Integer.parseInt(String.valueOf(level.getText().toString()));
                    credit1 = Integer.parseInt(String.valueOf(credit.getText().toString()));

                    contentValues.put("username",name1);
                    contentValues.put("GPA",gpa1);
                    contentValues.put("major",major1);
                    contentValues.put("Credit_Hour",credit1);
                    contentValues.put("level",level1);
                    db.update("registration", contentValues, "id = ?",id1);
                    uId.setText("");
                    name.setText("");
                    major.setText("");
                    level.setText("");
                    gpa.setText("");
                    credit.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;

                case "Name":
                    contentValues = new ContentValues();
                    name1 = name.getText().toString();
                    contentValues.put("username",name1);
                    db.update("registration", contentValues, "id = ?",id1);
                    name.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;

                case "GPA":
                    contentValues = new ContentValues();
                    gpa1 = Float.parseFloat(String.valueOf(gpa.getText().toString()));
                    contentValues.put("GPA",gpa1);
                    db.update("registration", contentValues, "id = ?",id1);
                    gpa.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;

                case "Level":
                    contentValues = new ContentValues();
                    level1 = Integer.parseInt(String.valueOf(level.getText().toString()));
                    contentValues.put("level",level1);
                    db.update("registration", contentValues, "id = ?",id1);
                    level.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;

                case "Major":
                    contentValues = new ContentValues();
                    major1 = major.getText().toString();
                    contentValues.put("major",major1);
                    db.update("registration", contentValues, "id = ?",id1);
                    major.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;

                case "Credit Hour":
                    contentValues = new ContentValues();
                    credit1 = Integer.parseInt(String.valueOf(credit.getText().toString()));
                    contentValues.put("Credit_Hour",credit1);
                    db.update("registration", contentValues, "id = ?",id1);
                    credit.setText("");
                    Toast("Your Data Has Been Updated Successfully",this);
                    break;
            }


        }
        else
            Toast("This ID doesn't Exist",UpdateActivity.this);
    }

}
