package com.example.sujith.patient_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class patient_forgot_password extends AppCompatActivity {

    Button pat_for_Bp;

    EditText pat_for_emailp;

    String urlf="http://srishti-systems.info/projects/patient_diary/api/patient_forget.php";

    AsyncHttpClient clientf;
    RequestParams paramsf;
    JSONObject objf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_forgot_password);

        pat_for_Bp=(Button)findViewById(R.id.pat_for_B);
        pat_for_emailp=(EditText)findViewById(R.id.pat_for_email);

        clientf=new AsyncHttpClient();
        paramsf=new RequestParams();


        pat_for_Bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                paramsf.add("email",pat_for_emailp.getText().toString());

                clientf.get(urlf,paramsf,new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);

                        try
                        {
                            objf=new JSONObject(content);

                            if (objf.getString("Status").equals("success"))
                            {
                                Toast.makeText(patient_forgot_password.this,
                                        ""+objf.getString("Status"), Toast.LENGTH_SHORT).show();

                                Intent tof=new Intent(patient_forgot_password.this,
                                        patient_registration.class);
                                startActivity(tof);
                            }
                            else
                            {
                                Toast.makeText(patient_forgot_password.this,
                                        ""+objf.getString("Status"), Toast.LENGTH_SHORT).show();
                            }

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });







            }
        });




    }
}
