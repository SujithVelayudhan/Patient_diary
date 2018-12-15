package com.example.sujith.patient_diary;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class f_pat_update_profile extends Fragment {



    TextInputEditText pat_up_namep,pat_up_php,pat_up_idp,pat_up_agep;
    EditText pat_up_dobp;

    int upday,upmonth,upyear,uptemp;


    public f_pat_update_profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vup=inflater.inflate(R.layout.fragment_f_pat_update_profile, container, false);

        pat_up_namep=(TextInputEditText)vup.findViewById(R.id.pat_up_name);
        pat_up_php=(TextInputEditText)vup.findViewById(R.id.pat_up_ph);
        pat_up_idp=(TextInputEditText)vup.findViewById(R.id.pat_up_id);
        pat_up_dobp=vup.findViewById(R.id.pat_up_dob);
        pat_up_agep=(TextInputEditText)vup.findViewById(R.id.pat_up_age);

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

        return vup;

    }

}
