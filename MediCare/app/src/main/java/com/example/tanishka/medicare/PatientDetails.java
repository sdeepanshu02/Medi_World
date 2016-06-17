package com.example.tanishka.medicare;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

/**
 * Created by Deepanshu on 3/30/2016.
 */
public class PatientDetails {
    String JSON_STRING = "";
    JSONObject jsonObject;
    JSONArray jsonArray;
    Context context;

    PatientDetails(Context context){
        this.context = context;
    }

    public  String[] getDetails(String admNoString){
        Message.message(context,admNoString);
        String[] details = {"","","","","","","","","","",""};
        BackgroundTask backgroundTask = new BackgroundTask(context);
        backgroundTask.execute(admNoString);

        try {
            Message.message(context,JSON_STRING);
            jsonObject = new JSONObject(JSON_STRING);
            jsonArray = jsonObject.getJSONArray("Patient_Details_From_Server");


                JSONObject JO = jsonArray.getJSONObject(0);
                details[0] = JO.getString("_id");
                details[1] = JO.getString("Name");
                details[2] = JO.getString("Password");
                details[3] = JO.getString("Contact_No");
                details[4] = JO.getString("Email");
                details[5] = JO.getString("Gender");
                details[6] = JO.getString("Dob");
                details[7] = JO.getString("BloodGroup");
                details[8] = JO.getString("Height");
                details[9] = JO.getString("Weight");
                details[10] = JO.getString("Metabolic_Disorders");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return details;
    }

    public class BackgroundTask extends AsyncTask<String,Void,String> {
        String jsonGetPatientDetailsURL;
        Context context;

        BackgroundTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute(){
            jsonGetPatientDetailsURL = "http://mediworld.orgfree.com/mediworld/jsonGetPatientDetails.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String PATIENT_ADM_NO=params[0];

            try {
                URL url = new URL(jsonGetPatientDetailsURL);
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
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING=bufferedReader.readLine())!=null){

                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

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
            JSON_STRING = result;
            Message.message(context,JSON_STRING);
        }
    }
}
