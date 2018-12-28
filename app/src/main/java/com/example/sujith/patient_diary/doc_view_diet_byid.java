package com.example.sujith.patient_diary;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class doc_view_diet_byid extends Fragment {

    TextInputEditText diet_pat_idp,diet_doc_idp;
    Button diet_sub_bp;


    public doc_view_diet_byid() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doc_view_diet_byid, container, false);

        diet_pat_idp=v.findViewById(R.id.diet_pat_id);
        diet_doc_idp=v.findViewById(R.id.diet_doc_id);
        diet_sub_bp=v.findViewById(R.id.diet_sub_button);

        diet_sub_bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                SharedPreferences sp_doc_d=getContext().getSharedPreferences("dd",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp_doc_d.edit();
                ed.putString("diet_doc_id",diet_doc_idp.getText().toString());
                ed.putString("diet_pat_id",diet_pat_idp.getText().toString());
                ed.commit();

                doc_view_pat_diet_exp dfe=new doc_view_pat_diet_exp();
                android.support.v4.app.FragmentTransaction fragT=
                        getFragmentManager().beginTransaction();
                fragT.replace(R.id.frame_doc,dfe);
                fragT.commit();

            }
        });

        return v;
    }

}
