package com.example.android.medi;

/**
 * Created by Deepanshu on 3/24/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        button = (Button) findViewById(R.id.proceedButton);
        textView = (TextView) findViewById(R.id.connection);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            textView.setVisibility(View.INVISIBLE);
        } else {
            textView.setText("No Internet Connection");
            button.setEnabled(false);
        }
    }

    public void Proceed(View view){
        Intent intent = new Intent(this,loginAsPatient.class);
        startActivity(intent);
    }
}