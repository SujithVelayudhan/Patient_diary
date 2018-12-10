package com.example.sujith.patient_diary;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class f_patient_details extends Fragment {

    TextView f_pat_namtvp,f_pat_idtvp,f_pat_agetvp,f_pat_dobtvp,f_pat_emailtvp,f_pat_phtvp,
            f_pat_passtvp;


    public f_patient_details() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_f_patient_details, container, false);

        f_pat_idtvp=(TextView)v.findViewById(R.id.f_pat_idtv);
        f_pat_namtvp=(TextView)v.findViewById(R.id.f_pat_namtv);
        f_pat_agetvp=(TextView)v.findViewById(R.id.f_pat_agetv);
        f_pat_dobtvp=(TextView)v.findViewById(R.id.f_pat_dobtv);
        f_pat_emailtvp=(TextView)v.findViewById(R.id.f_pat_emailtv);
        f_pat_phtvp=(TextView)v.findViewById(R.id.f_pat_phtv);
        f_pat_passtvp=(TextView)v.findViewById(R.id.f_pat_passtv);





        SharedPreferences sp_pat_reg=getActivity().getSharedPreferences("k1",MODE_PRIVATE);

        String pa_id=sp_pat_reg.getString("sid",null);
        String pa_name=sp_pat_reg.getString("sna",null);
        String pa_age=sp_pat_reg.getString("sage",null);
        String pa_dob=sp_pat_reg.getString("sdob",null);
        String pa_email=sp_pat_reg.getString("sem",null);
        String pa_ph=sp_pat_reg.getString("sph",null);
        String pa_pass=sp_pat_reg.getString("spass",null);

        f_pat_idtvp.setText("Id : "+pa_id);
        f_pat_namtvp.setText("Patient Name : "+pa_name);
        f_pat_agetvp.setText("Age : "+pa_age);
        f_pat_dobtvp.setText("DOB : "+pa_dob);
        f_pat_emailtvp.setText("email id : "+pa_email);
        f_pat_phtvp.setText("Phone no : "+pa_ph);
        f_pat_passtvp.setText("Password : "+pa_pass);



//        ed.putString("sid",obj2.getString("pat_id"));

//        ed.putString("sage",obj2.getString("pat_age"));
//        ed.putString("sdob",obj2.getString("pat_dob"));
//        ed.putString("sem",obj2.getString("pat_email"));
//        ed.putString("sph",obj2.getString("pat_phone"));
//        ed.putString("spass",obj2.getString("pat_password"));


        return v;
    }

}
