package com.example.tanishka.medicare;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HomeScreenDoctor extends AppCompatActivity {

    Bundle detailsImported;
    String userIdImportedString = "",adm_no_patient_String = "",doctorNameImportedString = "";
    TextInputEditText adm_no_patient;
    TextView welcomeDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_doctor);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        detailsImported = getIntent().getExtras();
        userIdImportedString = detailsImported.getString("DOCTOR_ID");
        doctorNameImportedString = detailsImported.getString("DOCTOR_NAME");

        adm_no_patient = (TextInputEditText) findViewById(R.id.adm_no_home_screen_doctor);
        welcomeDoc = (TextView) findViewById(R.id.welocome_doc);
        welcomeDoc.setText("Welcome,    Mr. " + doctorNameImportedString);
    }

    public void logout(View view){
        Intent intent = new Intent(this,loginAsDoctor.class);
        startActivity(intent);
    }

    public void checkIfAdmNoExist(View view){
        adm_no_patient_String = adm_no_patient.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(adm_no_patient_String);

    }

    public class BackgroundTask extends AsyncTask<String,Void,String> {
        String checkIfAdmNoExistURL;
        Context context;

        BackgroundTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute(){
            checkIfAdmNoExistURL = "http://mediworld.orgfree.com/mediworld/checkIfAdmNoExist.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String PATIENT_ADM_NO=params[0];

            try {
                URL url = new URL(checkIfAdmNoExistURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("PATIENT_ADM_NO", "UTF-8") +"="+URLEncoder.encode(PATIENT_ADM_NO,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line=bufferedReader.readLine())!=null){
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response.trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Void... avoid){
            super.onProgressUpdate(avoid);
        }

        @Override
        protected void onPostExecute(String result){

            Boolean loginCheck = result.equals("ADM NO EXIST");
            if(loginCheck)
            {
                Intent intent = new Intent(context,diagnosisScreen.class);
                intent.putExtra("ADM_NO_PATIENT", adm_no_patient_String);
                intent.putExtra("DOCTOR_ID",userIdImportedString);
                intent.putExtra("DOCTOR_NAME",doctorNameImportedString);
                adm_no_patient.setText("");
                startActivity(intent);
            }
            else
            {
                Message.message(context, result);
                adm_no_patient.setText("");
            }
        }
    }
}