package com.example.tanishka.medicare;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Tanishka on 28-03-2016.
 */
public class Message {
    public static  void message(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
