package com.example.sujith.patient_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class doctor_login extends AppCompatActivity {

    AsyncHttpClient client;
    RequestParams params;
    JSONObject obj1;

    EditText doc_emailp,doc_passp;

    TextView reg_herep,pat_forgot_passp;

    String url="http://srishti-systems.info/projects/patient_diary/api/patient_login.php";


    TextView doc_reg_herep;
    Button doc_logBp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        client=new AsyncHttpClient();
        params=new RequestParams();


        doc_reg_herep=findViewById(R.id.doc_reg_here);
        doc_logBp=findViewById(R.id.doc_logB);
        doc_emailp=findViewById(R.id.doc_email);
        doc_passp=findViewById(R.id.doc_password);

        doc_reg_herep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent d_r=new Intent(doctor_login.this,doc_registration.class);
                startActivity(d_r);

            }
        });


        doc_logBp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                params.put("email",doc_emailp.getText().toString());
                params.put("password",doc_passp.getText().toString());

                client.get(url,params,new AsyncHttpResponseHandler()
                {

                    @Override
                    public void onSuccess(String content)
                    {
                        super.onSuccess(content);

                        try
                        {
                            Log.e("innn","in");

                            obj1=new JSONObject(content);
                            String s=obj1.getString("status");

                            Toast.makeText(doctor_login.this, ""+s,
                                    Toast.LENGTH_SHORT).show();
                            if (s.equals("Success"))
                            {
                                JSONObject obj2=obj1.getJSONObject("Patient_data");
                                SharedPreferences sp_doc_log=getApplicationContext()
                                        .getSharedPreferences("d1",MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp_doc_log.edit();
                                ed.putString("did",obj2.getString("pat_id"));
                                ed.putString("dna",obj2.getString("pat_name"));
                                ed.putString("dage",obj2.getString("pat_age"));
                                ed.putString("ddob",obj2.getString("pat_dob"));
                                ed.putString("dem",obj2.getString("pat_email"));
                                ed.putString("dph",obj2.getString("pat_phone"));
                                ed.putString("dpass",obj2.getString("pat_password"));
                                ed.commit();

                                Intent i=new Intent(doctor_login.this,doc_profile.class);
                                startActivity(i);


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
