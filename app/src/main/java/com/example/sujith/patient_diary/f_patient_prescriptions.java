package com.example.sujith.patient_diary;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class f_patient_prescriptions extends Fragment
{
    RecyclerView recy_view_pat_prescriptionp;

    ArrayList<String> pre_idA,doc_idA,pat_idA,pre_med_morA,pre_med_aftA,pre_med_eveA,
                        pre_dateA,pre_timeA,pre_reportA,pre_time_eveA,pre_med_time_morA,
                        pre_med_time_aftA,pre_med_time_eveA;
    Verticadapt adapt;

    String urlpre="http://srishti-systems.info/projects/patient_diary/api/patient_viewprescription.php";

    AsyncHttpClient clientpre;
    RequestParams params;
    JSONObject objpre1;
    JSONArray japre;

    LinearLayoutManager lm;

    public f_patient_prescriptions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View Vp=inflater.inflate(R.layout.fragment_f_patient_prescriptions, container,
                false);

        clientpre=new AsyncHttpClient();
        params=new RequestParams();

        recy_view_pat_prescriptionp=(RecyclerView)Vp.findViewById(R.id.recy_view_pat_prescription);

        params.put("doc_id","1");
        params.put("pat_id","1");



        pre_idA=new ArrayList<String>();
        doc_idA=new ArrayList<String>();
        pat_idA=new ArrayList<String>();
        pre_med_morA=new ArrayList<String>();
        pre_med_aftA=new ArrayList<String>();
        pre_med_eveA=new ArrayList<String>();
        pre_dateA=new ArrayList<String>();
        pre_timeA=new ArrayList<String>();
        pre_reportA=new ArrayList<String>();
        pre_time_eveA=new ArrayList<String>();
        pre_med_time_morA=new ArrayList<String>();
        pre_med_time_aftA=new ArrayList<String>();
        pre_med_time_eveA=new ArrayList<String>();

        Log.e("innn","outside");

        clientpre.get(urlpre,params,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
                super.onSuccess(content);

                try
                {
                    objpre1=new JSONObject(content);

                    Log.e("innn","inside");

                    if (objpre1.getString("status").equals("success"))
                    {
                        japre=objpre1.getJSONArray("Prescription_details");
                        for (int i=0;i<japre.length();i++)
                        {
                            JSONObject objpre2=japre.getJSONObject(i);

                            pre_idA.add("Prescription id : "+objpre2.getString("pres_id"));
                            doc_idA.add("Doctor id : "+objpre2.getString("doc_id"));
                            pat_idA.add("Patient Id : "+objpre2.getString("pat_id"));
                            pre_med_morA.add("Prescribed Medicine morning : "+objpre2.getString("pres_medicine_mor"));
                            pre_med_aftA.add("Prescription Medicine Afternoon : "+objpre2.getString("pres_medicine_aft"));
                            pre_med_eveA.add("Precription Medicine Evening : "+objpre2.getString("pres_medicine_eve"));
                            pre_dateA.add("Prescription Date : "+objpre2.getString("pres_date"));
                            pre_timeA.add("Prescription Time : "+objpre2.getString("pres_time"));
                            pre_reportA.add("Prescription Report : "+objpre2.getString("pres_report"));
                            pre_time_eveA.add("Prescription time eve : "+objpre2.getString("pres_time_eve"));
                            pre_med_time_morA.add("Medicine Time Morning : "+objpre2.getString("pres_medicine_time_mor"));
                            pre_med_time_aftA.add("Medicine Time Afternoon : "+objpre2.getString("pres_medicine_time_aft"));
                            pre_med_time_eveA.add("Medicine Time Evening : "+objpre2.getString("pres_medicine_time_eve"));


                        }
                    }



                    adapt=new Verticadapt(pre_idA);
                    lm=new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL,false);

                    recy_view_pat_prescriptionp.setAdapter(adapt);
                    recy_view_pat_prescriptionp.setLayoutManager(lm);
                }


                catch (Exception e)
                {

                }



            }
        });






        return Vp;
    }

    class Verticadapt extends RecyclerView.Adapter<Verticadapt.VH>
    {
        List<String> li;

        Verticadapt(List<String> li)
        {
            this.li=pre_idA;

        }

        class VH extends RecyclerView.ViewHolder
        {
            TextView pre_idAtvp,doc_idAtvp,pat_idAtvp,pre_med_morAtvp,pre_med_aftAtvp,pre_med_eveAtvp,
                    pre_dateAtvp,pre_timeAtvp,pre_reportAtvp,pre_time_eveAtvp,pre_med_time_morAtvp,
                    pre_med_time_aftAtvp,pre_med_time_eveAtvp;


            public VH(@NonNull View itemView)
            {
                super(itemView);

                pre_idAtvp=(TextView)itemView.findViewById(R.id.pre_id);
                doc_idAtvp=(TextView)itemView.findViewById(R.id.doc_id);
                pat_idAtvp=(TextView)itemView.findViewById(R.id.pat_id);
                pre_med_morAtvp=(TextView)itemView.findViewById(R.id.med_mor);
                pre_med_aftAtvp=(TextView)itemView.findViewById(R.id.med_aft);
                pre_med_eveAtvp=(TextView)itemView.findViewById(R.id.med_eve);
                pre_dateAtvp=(TextView)itemView.findViewById(R.id.pre_date);
                pre_timeAtvp=(TextView)itemView.findViewById(R.id.pre_time);
                pre_reportAtvp=(TextView)itemView.findViewById(R.id.pre_rep);
                pre_time_eveAtvp=(TextView)itemView.findViewById(R.id.pre_time_eve);
                pre_med_time_morAtvp=(TextView)itemView.findViewById(R.id.med_time_mor);
                pre_med_time_aftAtvp=(TextView)itemView.findViewById(R.id.med_time_aft);
                pre_med_time_eveAtvp=(TextView)itemView.findViewById(R.id.med_time_evening);

            }
        }


        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            View vv=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.pat_prescription,viewGroup,false);
            return new VH(vv);
        }

        @Override
        public void onBindViewHolder(@NonNull VH vh, int i)
        {

            vh.pre_idAtvp.setText(pre_idA.get(i));
            vh.doc_idAtvp.setText(doc_idA.get(i));
            vh.pat_idAtvp.setText(pat_idA.get(i));
            vh.pre_med_morAtvp.setText(pre_med_morA.get(i));
            vh.pre_med_aftAtvp.setText(pre_med_aftA.get(i));
            vh.pre_med_eveAtvp.setText(pre_med_eveA.get(i));
            vh.pre_dateAtvp.setText(pre_dateA.get(i));
            vh.pre_timeAtvp.setText(pre_timeA.get(i));
            vh.pre_reportAtvp.setText(pre_reportA.get(i));
            vh.pre_time_eveAtvp.setText(pre_time_eveA.get(i));
            vh.pre_med_time_morAtvp.setText(pre_med_time_morA.get(i));
            vh.pre_med_time_aftAtvp.setText(pre_med_time_aftA.get(i));
            vh.pre_med_time_eveAtvp.setText(pre_med_time_eveA.get(i));


        }

        @Override
        public int getItemCount() {
            return pre_idA.size();
        }



    }

}
