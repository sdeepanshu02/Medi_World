package com.example.tanishka.medicare;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class enterDiagnosisDetails extends AppCompatActivity {
    Bundle detailsImported;
    TextInputEditText symptoms, medicalTest, medicinesPrescribed;
    String admNoImportedString = "", symptomsString = "", medicalTestString = "", medicinesPrescribedString = "";
    String userIdImportedString = "",doctorNameImportedString = "";
    boolean isDataValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_diagnosis_details);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        detailsImported = getIntent().getExtras();
        admNoImportedString = detailsImported.getString("ADM_NO_PATIENT");
        userIdImportedString = detailsImported.getString("DOCTOR_ID");
        doctorNameImportedString = detailsImported.getString("DOCTOR_NAME");

        symptoms = (TextInputEditText) findViewById(R.id.enterSymptoms);
        medicalTest = (TextInputEditText) findViewById(R.id.enterMedicalTest);
        medicinesPrescribed = (TextInputEditText) findViewById(R.id.enterMedicinesPrescribed);
    }

    private boolean validateDetails() {

        boolean validateData = true;
        // Reset errors.
        symptoms.setError(null);
        medicalTest.setError(null);
        medicinesPrescribed.setError(null);

        // Store values at the time of the login attempt.
        String symptomsString = symptoms.getText().toString();
        String medicalTestString = medicalTest.getText().toString();
        String medicinesPrescribedString = medicinesPrescribed.getText().toString();
        View focusView = null;

        // Check for a valid medicinesPrescribed, if the user entered one.
        if (TextUtils.isEmpty(medicinesPrescribedString)) {
            medicinesPrescribed.setError("This field is required");
            validateData = false;
            focusView = medicinesPrescribed;
        }
        // Check for a valid medicalTest, if the user entered one.
        if (TextUtils.isEmpty(medicalTestString)) {
            medicalTest.setError("This field is required");
            validateData = false;
            focusView = medicalTest;
        }
        // Check for a valid symptoms, if the user entered one.
        if (TextUtils.isEmpty(symptomsString)) {
            symptoms.setError("This field is required");
            validateData = false;
            focusView = symptoms;
        }
        if (validateData == false) {
            focusView.requestFocus();
        }

        return validateData;
    }

    public void submitDiagnosisDetails(View view) {
        isDataValid = validateDetails();
        if (isDataValid == true) {
            symptomsString = symptoms.getText().toString();
            medicalTestString = medicalTest.getText().toString();
            medicinesPrescribedString = medicinesPrescribed.getText().toString();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormatShow = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String dateTimeShow = simpleDateFormatShow.format(calendar.getTime());
            SimpleDateFormat simpleDateFormatStore = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateTimeStore = simpleDateFormatStore.format(calendar.getTime());

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(admNoImportedString, symptomsString, medicalTestString, medicinesPrescribedString, dateTimeShow, dateTimeStore);
        }
    }

    public class BackgroundTask extends AsyncTask<String, Void, String> {
        String enterDiagnosticDetailsURL;
        Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            enterDiagnosticDetailsURL = "http://mediworld.orgfree.com/mediworld/enterDiagnosticDetails.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String PATIENT_MEDICAL_HISTORY_ADM_NO = params[0];
            String PATIENT_MEDICAL_HISTORY_SYMPTOMS = params[1];
            String PATIENT_MEDICAL_HISTORY_MEDICAL_TEST = params[2];
            String PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED = params[3];
            String PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW = params[4];
            String PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE = params[5];
            try {
                URL url = new URL(enterDiagnosticDetailsURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("PATIENT_MEDICAL_HISTORY_ADM_NO", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_ADM_NO, "UTF-8") + "&" +
                        URLEncoder.encode("PATIENT_MEDICAL_HISTORY_SYMPTOMS", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_SYMPTOMS, "UTF-8") + "&" +
                        URLEncoder.encode("PATIENT_MEDICAL_HISTORY_MEDICAL_TEST", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_MEDICAL_TEST, "UTF-8") + "&" +
                        URLEncoder.encode("PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_MEDICINES_PRESCRIBED, "UTF-8") + "&" +
                        URLEncoder.encode("PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_DATE_TIME_SHOW, "UTF-8") + "&" +
                        URLEncoder.encode("PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE", "UTF-8") + "=" + URLEncoder.encode(PATIENT_MEDICAL_HISTORY_DATE_TIME_STORE, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "";
                response = bufferedReader.readLine();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "Not Registered!!!";
        }

        @Override
        protected void onProgressUpdate(Void... avoid) {
            super.onProgressUpdate(avoid);
        }

        @Override
        protected void onPostExecute(String result) {
            Message.message(context, result);
            Intent intent = new Intent(context, diagnosisScreen.class);
            intent.putExtra("ADM_NO_PATIENT", admNoImportedString);
            intent.putExtra("DOCTOR_ID",userIdImportedString);
            intent.putExtra("DOCTOR_NAME",doctorNameImportedString);
            symptoms.setText("");
            medicalTest.setText("");
            medicinesPrescribed.setText("");
            startActivity(intent);
        }
    }
}
