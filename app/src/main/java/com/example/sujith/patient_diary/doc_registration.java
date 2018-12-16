package com.example.sujith.patient_diary;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;

public class doc_registration extends AppCompatActivity {

    AsyncHttpClient client_doc_re;
    RequestParams para_doc_re;
    JSONObject obj_doc;

    int dday,dmonth,dyear,temp,age;

    TextInputEditText doc_re_namep,doc_re_emailp,doc_re_agep,doc_re_php,doc_re_dobp,doc_re_passp;

    String urlr="http://srishti-systems.info/projects/patient_diary/api/patient_register.php";


    Button doc_re_buttonp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_registration);

        doc_re_buttonp=(Button)findViewById(R.id.doc_re_button);
        doc_re_namep=(TextInputEditText)findViewById(R.id.doc_re_name);
        doc_re_emailp=(TextInputEditText)findViewById(R.id.doc_re_email);
        doc_re_agep=(TextInputEditText)findViewById(R.id.doc_re_age);
        doc_re_php=(TextInputEditText)findViewById(R.id.doc_re_ph);
        doc_re_dobp=(TextInputEditText)findViewById(R.id.doc_re_dob);
        doc_re_passp=(TextInputEditText)findViewById(R.id.doc_re_pass);

        doc_re_dobp.setFocusable(false);

        client_doc_re=new AsyncHttpClient();
        para_doc_re=new RequestParams();

        doc_re_dobp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calendar ca=Calendar.getInstance();
                dday=ca.get(Calendar.DAY_OF_MONTH);
                dmonth=ca.get(Calendar.MONTH);
                dyear=ca.get(Calendar.YEAR);
                temp=ca.get(Calendar.YEAR);

                DatePickerDialog DP=new DatePickerDialog(doc_registration.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        doc_re_dobp.setText(dayOfMonth+" : "+(month+1)+" : "+year);
                        int flag=year;
                        age=temp-year;
                        doc_re_agep.setText(""+age);


                    }
                },dyear,dmonth,dday);


                DP.show();


            }
        });


        doc_re_buttonp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (doc_re_namep.getText().toString().equals(""))
                {
                    doc_re_namep.setError("Input details");
                }
                else if (doc_re_emailp.getText().toString().equals(""))
                {
                    doc_re_emailp.setError("Input details");
                }
                else if (doc_re_agep.getText().toString().equals(""))
                {
                    doc_re_agep.setError("Input details");
                }
                else if (doc_re_php.getText().toString().equals(""))
                {
                    doc_re_php.setError("Input details");
                }
                else if (doc_re_dobp.getText().toString().equals(""))
                {
                    doc_re_dobp.setError("Input details");
                }
                else if (doc_re_passp.getText().toString().equals(""))
                {
                    doc_re_passp.setError("Input details");
                }
                else {

                    para_doc_re.put("name", doc_re_namep.getText().toString());
                    para_doc_re.put("email", doc_re_emailp.getText().toString());
                    para_doc_re.put("age", doc_re_agep.getText().toString());
                    para_doc_re.put("phone", doc_re_php.getText().toString());
                    para_doc_re.put("dob", doc_re_dobp.getText().toString());
                    para_doc_re.put("password", doc_re_passp.getText().toString());

                    client_doc_re.get(urlr, para_doc_re, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);

                            try {
                                obj_doc = new JSONObject(content);

                                if (obj_doc.getString("status").equals("success")) {
                                    Toast.makeText(doc_registration.this, "" + obj_doc.getString("status"), Toast.LENGTH_SHORT).show();

                                    Intent l = new Intent(doc_registration.this, doctor_login.class);
                                    startActivity(l);
                                } else {
                                    Toast.makeText(doc_registration.this, "" + obj_doc.getString("status"), Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {

                            }


                        }
                    });
                }
            }
        });

    }
}
