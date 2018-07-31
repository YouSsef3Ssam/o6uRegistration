package com.example.youssef.o6uresgisteration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class Toast extends AppCompatActivity {

    public static void Toast(String message, Context context){
        android.widget.Toast toast = android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.toast);

        toast.show();
    }

}
