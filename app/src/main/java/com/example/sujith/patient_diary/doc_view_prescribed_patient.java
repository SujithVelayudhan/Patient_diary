package com.example.sujith.patient_diary;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class doc_view_prescribed_patient extends Fragment {

    EditText vp_doc_idp,vp_pat_idp;
    Button vp_buttp;


    public doc_view_prescribed_patient() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doc_view_prescribed_patient, container, false);

        vp_doc_idp=v.findViewById(R.id.vpp_doc_id);
        vp_pat_idp=v.findViewById(R.id.vpp_pat_id);
        vp_buttp=v.findViewById(R.id.vp_butt);

        vp_buttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sp_doc_d=getContext().getSharedPreferences("dd",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp_doc_d.edit();
                ed.putString("vp_doc_id",vp_doc_idp.getText().toString());
                ed.putString("vp_pat_id",vp_pat_idp.getText().toString());
                ed.commit();

                doc_view_prescribed_pat_frag df=new doc_view_prescribed_pat_frag();
                android.support.v4.app.FragmentTransaction fragT=
                        getFragmentManager().beginTransaction();
                fragT.replace(R.id.frame_doc,df);
                fragT.commit();

            }
        });



        return v;
    }

}
