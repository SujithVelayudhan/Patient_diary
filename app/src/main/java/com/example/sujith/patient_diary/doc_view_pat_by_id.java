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
public class doc_view_pat_by_id extends Fragment {

    EditText doc_view_pat_idp;
    Button doc_view_pat_nBp;


    public doc_view_pat_by_id() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vd=inflater.inflate(R.layout.fragment_doc_view_pat_by_id, container, false);

        doc_view_pat_idp=vd.findViewById(R.id.doc_view_pat_id);
        doc_view_pat_nBp=vd.findViewById(R.id.doc_view_pat_nB);

        doc_view_pat_nBp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                SharedPreferences sp_doc_d=getContext()
                        .getSharedPreferences("dd",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp_doc_d.edit();
                ed.putString("patid",doc_view_pat_idp.getText().toString());
                ed.commit();

                doc_view_pat_frag d=new doc_view_pat_frag();
                android.support.v4.app.FragmentTransaction fragT=
                        getFragmentManager().beginTransaction();
                fragT.replace(R.id.frame_doc,d);
                fragT.commit();

            }
        });



        return vd;
    }

}
