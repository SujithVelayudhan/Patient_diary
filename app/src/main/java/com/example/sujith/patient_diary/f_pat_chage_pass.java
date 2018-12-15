package com.example.sujith.patient_diary;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class f_pat_chage_pass extends Fragment {

    TextView pat_ch_idp;
    EditText pat_ch_oldp,pat_ch_newp;
    Button pat_ch_nBp;

    AsyncHttpClient clientcp;
    RequestParams paramscp;
    JSONObject objcp;



    String urlcp="http://srishti-systems.info/projects/patient_diary/api/patient_changepassword.php";


    public f_pat_chage_pass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vc=inflater.inflate(R.layout.fragment_f_pat_chage_pass, container, false);

        pat_ch_idp=(TextView)vc.findViewById(R.id.pat_ch_id);
        pat_ch_oldp=(EditText)vc.findViewById(R.id.pat_ch_oldpass);
        pat_ch_newp=(EditText)vc.findViewById(R.id.pat_ch_newpass);
        pat_ch_nBp=(Button)vc.findViewById(R.id.pat_ch_nB);

        clientcp=new AsyncHttpClient();
        paramscp=new RequestParams();


        SharedPreferences sp_pat_reg=getContext()
                .getSharedPreferences("k1",MODE_PRIVATE);

        pat_ch_idp.setText(""+sp_pat_reg.getString("sid",null));

        pat_ch_nBp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                paramscp.put("pat_id",pat_ch_idp.getText().toString());
                paramscp.put("oldpsd",pat_ch_oldp.getText().toString());
                paramscp.put("newpsd",pat_ch_newp.getText().toString());

                clientcp.get(urlcp,paramscp,new AsyncHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(String content)
                    {
                        super.onSuccess(content);

                        try
                        {
                            objcp=new JSONObject(content);

                            if (objcp.getString("status").equals("success"))
                            {
                                Toast.makeText(getContext(), ""+objcp.getString("status"),
                                        Toast.LENGTH_SHORT).show();

                                f_patient_details fm=new f_patient_details();

                                android.support.v4.app.FragmentTransaction fragT=
                                        getFragmentManager().beginTransaction();
                                fragT.replace(R.id.fram_e,fm);
                                fragT.commit();


                            }
                            else
                            {
                                Toast.makeText(getContext(), ""+objcp.getString("status"),
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






        return vc;
    }

}
