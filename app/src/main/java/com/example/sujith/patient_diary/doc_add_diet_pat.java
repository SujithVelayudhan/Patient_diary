package com.example.sujith.patient_diary;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class doc_add_diet_pat extends Fragment {

    AsyncHttpClient clienta;
    RequestParams paramsa;
    JSONObject obj;

    TextInputEditText add_diet_pat_idp,add_diet_doc_idp,add_diet_dayp,add_diet_mornp,
            add_diet_aftp,add_diet_nigp;

    Button add_diet_buttonp;

    String url="http://srishti-systems.info/projects/patient_diary/api/doc_adddiet.php";




    public doc_add_diet_pat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doc_add_diet_pat, container, false);

        clienta=new AsyncHttpClient();
        paramsa=new RequestParams();

        add_diet_pat_idp=v.findViewById(R.id.add_diet_pat_id);
        add_diet_doc_idp=v.findViewById(R.id.add_diet_doc_id);
        add_diet_dayp=v.findViewById(R.id.add_diet_day);
        add_diet_mornp=v.findViewById(R.id.add_diet_morn);
        add_diet_aftp=v.findViewById(R.id.add_diet_aft);
        add_diet_nigp=v.findViewById(R.id.add_diet_nig);

        add_diet_buttonp=v.findViewById(R.id.add_diet_button);

        add_diet_buttonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                paramsa.put("pat_id",add_diet_pat_idp.getText().toString());
                paramsa.put("doc_id",add_diet_doc_idp.getText().toString());
                paramsa.put("day",add_diet_dayp.getText().toString());
                paramsa.put("morning",add_diet_mornp.getText().toString());
                paramsa.put("afternoon",add_diet_aftp.getText().toString());
                paramsa.put("night",add_diet_nigp.getText().toString());

                clienta.get(url,paramsa,new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(String content)
                    {
                        super.onSuccess(content);

                        try
                        {
                            obj=new JSONObject(content);

                            if (obj.getString("status").equals("success"))
                            {
                                Toast.makeText(getContext(), ""+obj.getString("status"),
                                        Toast.LENGTH_SHORT).show();


                                doc_view_pat_by_id dv=new doc_view_pat_by_id();
                                android.support.v4.app.FragmentTransaction fragT=
                                        getFragmentManager().beginTransaction();
                                fragT.replace(R.id.frame_doc,dv);
                                fragT.commit();
                            }
                            else
                            {
                                Toast.makeText(getContext(), ""+obj.getString("status"),
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



        return v;

    }

}
