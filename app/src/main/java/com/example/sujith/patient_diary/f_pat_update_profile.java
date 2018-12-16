package com.example.sujith.patient_diary;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class f_pat_update_profile extends Fragment {

    AsyncHttpClient clientup;
    RequestParams paramsup;
    JSONObject obj1up;

    String urlup="http://srishti-systems.info/projects/patient_diary/api/patient_updateprofile.php";

    TextInputEditText pat_up_namep,pat_up_php,pat_up_idp,pat_up_agep;
    EditText pat_up_dobp;
    Button pat_up_Bp;

    int upday,upmonth,upyear,uptemp;


    public f_pat_update_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vup=inflater.inflate(R.layout.fragment_f_pat_update_profile, container, false);


        clientup=new AsyncHttpClient();
        paramsup=new RequestParams();





        pat_up_namep=(TextInputEditText)vup.findViewById(R.id.pat_up_name);
        pat_up_php=(TextInputEditText)vup.findViewById(R.id.pat_up_ph);
        pat_up_idp=(TextInputEditText)vup.findViewById(R.id.pat_up_id);
        pat_up_dobp=vup.findViewById(R.id.pat_up_dob);
        pat_up_agep=(TextInputEditText)vup.findViewById(R.id.pat_up_age);
        pat_up_Bp=vup.findViewById(R.id.pat_up_button);

        pat_up_dobp.setFocusable(false);

        SharedPreferences sp_pat_reg=getActivity().getSharedPreferences("k1",MODE_PRIVATE);

        pat_up_idp.setText(sp_pat_reg.getString("sid",null));
        pat_up_namep.setText(sp_pat_reg.getString("sna",null));
        pat_up_agep.setText(sp_pat_reg.getString("sage",null));
        pat_up_dobp.setText(sp_pat_reg.getString("sdob",null));
        pat_up_php.setText(sp_pat_reg.getString("sph",null));


        pat_up_dobp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calendar ca=Calendar.getInstance();
                upday=ca.get(Calendar.DAY_OF_MONTH);
                upmonth=ca.get(Calendar.MONTH);
                upyear=ca.get(Calendar.YEAR);
                uptemp=ca.get(Calendar.YEAR);

                DatePickerDialog DP=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        pat_up_dobp.setText(dayOfMonth+" : "+(month+1)+" : "+year);
                        int flag=year;
                        int upage=uptemp-year;
                        pat_up_agep.setText(""+upage);


                    }
                },upyear,upmonth,upday);


                DP.show();


            }
        });

        pat_up_Bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                paramsup.put("name",pat_up_namep.getText().toString());
                paramsup.put("phone",pat_up_php.getText().toString());
                paramsup.put("pat_id",pat_up_idp.getText().toString());
                paramsup.put("age",pat_up_agep.getText().toString());
                paramsup.put("dob",pat_up_dobp.getText().toString());



                clientup.get(urlup,paramsup,new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        try
                        {
                            obj1up=new JSONObject(content);

                            if (obj1up.getString("status").equals("success"))
                            {
                                Toast.makeText(getContext(), ""+obj1up.getString("status"),
                                        Toast.LENGTH_SHORT).show();

                                Intent i=new Intent(getContext(),patient_registration.class);
                                startActivity(i);

//                                f_patient_details upfd=new f_patient_details();
//
//                                android.support.v4.app.FragmentTransaction fragT=
//                                        getFragmentManager().beginTransaction();
//                                fragT.replace(R.id.fram_e,upfd);
//                                fragT.commit();

                            }
                            else
                            {
                                Toast.makeText(getContext(), ""+obj1up.getString("status"),
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });

            }
        });



        return vup;

    }

}
