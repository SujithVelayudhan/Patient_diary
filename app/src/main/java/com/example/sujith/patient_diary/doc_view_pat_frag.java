package com.example.sujith.patient_diary;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class doc_view_pat_frag extends Fragment {

    TextView d_pat_namtvp,d_pat_idtvp,d_pat_agetvp,d_pat_dobtvp,d_pat_emailtvp,d_pat_phtvp;

    AsyncHttpClient clientd;
    RequestParams paramsd;
    JSONObject obj1d;

    String urll="http://srishti-systems.info/projects/patient_diary/api/doc_viewpatientbyid.php";


    public doc_view_pat_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doc_view_pat_frag, container, false);

        d_pat_namtvp=v.findViewById(R.id.d_pat_namtv);
        d_pat_idtvp=v.findViewById(R.id.d_pat_idtv);
        d_pat_agetvp=v.findViewById(R.id.d_pat_agetv);
        d_pat_dobtvp=v.findViewById(R.id.d_pat_dobtv);
        d_pat_emailtvp=v.findViewById(R.id.d_pat_emailtv);
        d_pat_phtvp=v.findViewById(R.id.d_pat_phtv);

        clientd=new AsyncHttpClient();
        paramsd=new RequestParams();

        SharedPreferences sp_doc_d=getContext().getSharedPreferences("dd",MODE_PRIVATE);


        paramsd.put("pat_id",sp_doc_d.getString("patid",null));

        clientd.get(urll,paramsd,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);

                try
                {
                    obj1d=new JSONObject(content);
                    if (obj1d.getString("status").equals("success"))
                    {
                        JSONObject obj2d=obj1d.getJSONObject("PatientDeatils");
                        d_pat_namtvp.setText("Patient Name : "+obj2d.getString("pat_name"));
                        d_pat_idtvp.setText("Patient Id : "+obj2d.getString("pat_id"));
                        d_pat_agetvp.setText("Patient Age : "+obj2d.getString("pat_age"));
                        d_pat_dobtvp.setText("Patient DOB : "+obj2d.getString("pat_dob"));
                        d_pat_emailtvp.setText("Patient email id : "
                                +obj2d.getString("pat_email"));
                        d_pat_phtvp.setText("Phone Number : "+obj2d.getString("pat_phone"));

                    }
                    else
                    {
                        Toast.makeText(getContext(), ""+obj1d.getString("status"),
                                Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {

                }
            }
        });



        return v;


    }


}
