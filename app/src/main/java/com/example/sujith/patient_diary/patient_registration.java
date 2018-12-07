package com.example.sujith.patient_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class patient_registration extends AppCompatActivity {

    AsyncHttpClient client;
    RequestParams params;
    JSONObject obj1;

    EditText patemailp,patpassp;
    Button patlogBp;

    String url="http://srishti-systems.info/projects/patient_diary/api/patient_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        client=new AsyncHttpClient();
        params=new RequestParams();


        patemailp=(EditText)findViewById(R.id.pat_email);
        patpassp=(EditText)findViewById(R.id.pat_password);
        patlogBp=(Button)findViewById(R.id.pat_logB);



        patlogBp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email_pat_st=patemailp.getText().toString();
                String pass_pat_st=patpassp.getText().toString();

                params.put("email",email_pat_st);
                params.put("password",pass_pat_st);

                Log.e("innn","out");

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
                            Toast.makeText(patient_registration.this, ""+s,
                                    Toast.LENGTH_SHORT).show();
                            if (s.equals("Success"))
                            {
                                Intent a = new Intent(patient_registration.this,
                                        patient_profile.class);
                                startActivity(a);
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
