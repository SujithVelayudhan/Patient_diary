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

public class register_now extends AppCompatActivity {

    AsyncHttpClient client_re;
    RequestParams para;
    JSONObject objr;

    int dday,dmonth,dyear,temp,age;

    TextInputEditText pat_re_namep,pat_re_emailp,pat_re_agep,pat_re_php,pat_re_dobp,pat_re_passp;

    String urlr="http://srishti-systems.info/projects/patient_diary/api/patient_register.php";


    Button pat_re_buttonp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_now);

        pat_re_buttonp=(Button)findViewById(R.id.pat_re_button);
        pat_re_namep=(TextInputEditText)findViewById(R.id.pat_re_name);
        pat_re_emailp=(TextInputEditText)findViewById(R.id.pat_re_email);
        pat_re_agep=(TextInputEditText)findViewById(R.id.pat_re_age);
        pat_re_php=(TextInputEditText)findViewById(R.id.pat_re_ph);
        pat_re_dobp=(TextInputEditText)findViewById(R.id.pat_re_dob);
        pat_re_passp=(TextInputEditText)findViewById(R.id.pat_re_pass);

        pat_re_dobp.setFocusable(false);

        client_re=new AsyncHttpClient();
        para=new RequestParams();

        pat_re_dobp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calendar ca=Calendar.getInstance();
                dday=ca.get(Calendar.DAY_OF_MONTH);
                dmonth=ca.get(Calendar.MONTH);
                dyear=ca.get(Calendar.YEAR);
                temp=ca.get(Calendar.YEAR);

                DatePickerDialog DP=new DatePickerDialog(register_now.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        pat_re_dobp.setText(dayOfMonth+" : "+(month+1)+" : "+year);
                        int flag=year;
                        age=temp-year;
                        pat_re_agep.setText(""+age);


                    }
                },dyear,dmonth,dday);


                DP.show();


            }
        });


        pat_re_buttonp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (pat_re_namep.getText().toString().equals(""))
                {
                    pat_re_namep.setError("Input details");
                }
                else if (pat_re_emailp.getText().toString().equals(""))
                {
                    pat_re_emailp.setError("Input details");
                }
                else if (pat_re_agep.getText().toString().equals(""))
                {
                    pat_re_agep.setError("Input details");
                }
                else if (pat_re_php.getText().toString().equals(""))
                {
                    pat_re_php.setError("Input details");
                }
                else if (pat_re_dobp.getText().toString().equals(""))
                {
                    pat_re_dobp.setError("Input details");
                }
                else if (pat_re_passp.getText().toString().equals(""))
                {
                    pat_re_passp.setError("Input details");
                }
                else {

                    para.put("name", pat_re_namep.getText().toString());
                    para.put("email", pat_re_emailp.getText().toString());
                    para.put("age", pat_re_agep.getText().toString());
                    para.put("phone", pat_re_php.getText().toString());
                    para.put("dob", pat_re_dobp.getText().toString());
                    para.put("password", pat_re_passp.getText().toString());

                    client_re.get(urlr, para, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);

                            try {
                                objr = new JSONObject(content);

                                if (objr.getString("status").equals("success")) {
                                    Toast.makeText(register_now.this, "" + objr.getString("status"), Toast.LENGTH_SHORT).show();

                                    Intent l = new Intent(register_now.this, patient_registration.class);
                                    startActivity(l);
                                } else {
                                    Toast.makeText(register_now.this, "" + objr.getString("status"), Toast.LENGTH_SHORT).show();
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
