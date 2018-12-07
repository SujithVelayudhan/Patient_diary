package com.example.sujith.patient_diary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class patient_registration extends AppCompatActivity {

    AsyncHttpClient client;
    RequestParams params;

    EditText patemailp,patpassp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        client=new AsyncHttpClient();
        params=new RequestParams();

        patemailp=(EditText)findViewById(R.id.pat_email);
        patpassp=(EditText)findViewById(R.id.pat_password);
    }
}
