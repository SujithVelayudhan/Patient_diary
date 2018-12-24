package com.example.sujith.patient_diary;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class doc_view_prescribed_pat_frag extends Fragment {

    RecyclerView vp_recyclerp;

    AsyncHttpClient clientv;
    RequestParams paramsv;
    JSONObject obj1v;
    JSONArray jarry;

    ArrayList<String> dvp_pres_idA,dvp_doc_idA,dvp_pat_idA,dvp_pres_medicine_morA,
                        dvp_pres_medicine_aftA,dvp_pres_medicine_eveA,dvp_pres_dateA,
                        dvp_pres_timeA,dvp_pres_reportA,dvp_pres_time_eveA,dvp_pres_medicine_time_morA,
                        dvp_pres_medicine_time_aftA,dvp_pres_medicine_time_eveA,dvp_pat_nameA,
                        dvp_pat_ageA,dvp_pat_dobA,dvp_pat_emailA,dvp_pat_phoneA;

    String urlv="http://srishti-systems.info/projects/patient_diary/api/doc_viewprescribedpatient.php";

    Verticalad adapt;
    LinearLayoutManager l;


    public doc_view_prescribed_pat_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_doc_view_prescribed_pat_frag, container,
                false);

        vp_recyclerp=v.findViewById(R.id.vp_recycler);

        clientv=new AsyncHttpClient();
        paramsv=new RequestParams();


        dvp_pres_idA=new ArrayList<>();
        dvp_doc_idA=new ArrayList<>();
        dvp_pat_idA=new ArrayList<>();
        dvp_pres_medicine_morA=new ArrayList<>();
        dvp_pres_medicine_aftA=new ArrayList<>();
        dvp_pres_medicine_eveA=new ArrayList<>();
        dvp_pres_dateA=new ArrayList<>();
        dvp_pres_timeA=new ArrayList<>();
        dvp_pres_reportA=new ArrayList<>();
        dvp_pres_time_eveA=new ArrayList<>();
        dvp_pres_medicine_time_morA=new ArrayList<>();
        dvp_pres_medicine_time_aftA=new ArrayList<>();
        dvp_pres_medicine_time_eveA=new ArrayList<>();
        dvp_pat_nameA=new ArrayList<>();
        dvp_pat_ageA=new ArrayList<>();
        dvp_pat_dobA=new ArrayList<>();
        dvp_pat_emailA=new ArrayList<>();
        dvp_pat_phoneA=new ArrayList<>();

        SharedPreferences sp_doc_d=getContext().getSharedPreferences("dd",MODE_PRIVATE);

        paramsv.put("pat_id",sp_doc_d.getString("vp_pat_id",null));
        paramsv.put("doc_id",sp_doc_d.getString("vp_doc_id",null));


        clientv.get(urlv,paramsv,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
                super.onSuccess(content);

                try
                {
                    obj1v=new JSONObject(content);

                    if (obj1v.getString("status").equals("success"))
                    {
                        jarry=obj1v.getJSONArray("PrescribedPatient_details");
                        for (int i=0;i<jarry.length();i++)
                        {
                            JSONObject obj2v=jarry.getJSONObject(i);

                            dvp_pres_idA.add("Prescription Id : "+obj2v.getString("pres_id"));
                            dvp_doc_idA.add("Doctor Id : "+obj2v.getString("doc_id"));
                            dvp_pat_idA.add("Patient Id : "+obj2v.getString("pat_id"));
                            dvp_pres_medicine_morA.add("Medicine Morning : "+obj2v.getString("pres_medicine_mor"));
                            dvp_pres_medicine_aftA.add("Medicine Afternoon : "+obj2v.getString("pres_medicine_aft"));
                            dvp_pres_medicine_eveA.add("Medicine Evening : "+obj2v.getString("pres_medicine_eve"));
                            dvp_pres_dateA.add("Prescription Date : "+obj2v.getString("pres_date"));
                            dvp_pres_timeA.add("Prescription Time : "+obj2v.getString("pres_time"));
                            dvp_pres_reportA.add("Precription Repory"+obj2v.getString("pres_report"));
                            dvp_pres_time_eveA.add("Precription Time Evening : "+obj2v.getString("pres_time_eve"));
                            dvp_pres_medicine_time_morA.add("Medicine Time Morning : "+obj2v.getString("pres_medicine_time_mor"));
                            dvp_pres_medicine_time_aftA.add("Medicine Time Afternoon : "+obj2v.getString("pres_medicine_time_aft"));
                            dvp_pres_medicine_time_eveA.add("Medicine Time Evening : "+obj2v.getString("pres_medicine_time_eve"));
                            dvp_pat_nameA.add("Patient Name : "+obj2v.getString("pat_name"));
                            dvp_pat_ageA.add("Patirnt Age : "+obj2v.getString("pat_age"));
                            dvp_pat_dobA.add("Patient DOB : "+obj2v.getString("pat_dob"));
                            dvp_pat_emailA.add("Patiecnt email : "+obj2v.getString("pat_email"));
                            dvp_pat_phoneA.add("Patient Phone No : "+obj2v.getString("pat_phone"));



                        }
                    }

                    adapt=new Verticalad(dvp_pres_idA);
                    l=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

                    vp_recyclerp.setAdapter(adapt);
                    vp_recyclerp.setLayoutManager(l);

                }
                catch (Exception e)
                {

                }

            }
        });

            return v;
    }


    class Verticalad extends RecyclerView.Adapter<Verticalad.vh>
    {
        List<String> l;

        Verticalad(List<String> l)
        {
            this.l=dvp_pres_idA;
        }



        class vh extends RecyclerView.ViewHolder
        {
            TextView vp_pre_idp,vp_doc_idp,vp_pat_idp,vp_med_morp,vp_med_aftp,vp_med_evep,
                    vp_pre_datep,vp_pre_timep,vp_pre_repp,vp_pre_time_evep,vp_med_time_morp,
                    vp_med_time_aftp,vp_med_time_eveningp,vp_namep,vp_agep,vp_dobp,vp_emailp,
                    vp_phonep;

            public vh(@NonNull View itemView)
            {
                super(itemView);

                vp_pre_idp=itemView.findViewById(R.id.vp_pre_id);
                vp_doc_idp=itemView.findViewById(R.id.vp_doc_id);
                vp_pat_idp=itemView.findViewById(R.id.vp_pat_id);
                vp_med_morp=itemView.findViewById(R.id.vp_med_mor);
                vp_med_aftp=itemView.findViewById(R.id.vp_med_aft);
                vp_med_evep=itemView.findViewById(R.id.vp_med_eve);
                vp_pre_datep=itemView.findViewById(R.id.vp_pre_date);
                vp_pre_timep=itemView.findViewById(R.id.vp_pre_time);
                vp_pre_repp=itemView.findViewById(R.id.vp_pre_rep);
                vp_pre_time_evep=itemView.findViewById(R.id.vp_pre_time_eve);
                vp_med_time_morp=itemView.findViewById(R.id.vp_med_time_mor);
                vp_med_time_aftp=itemView.findViewById(R.id.vp_med_time_aft);
                vp_med_time_eveningp=itemView.findViewById(R.id.vp_med_time_evening);
                vp_namep=itemView.findViewById(R.id.vp_name);
                vp_agep=itemView.findViewById(R.id.vp_age);
                vp_dobp=itemView.findViewById(R.id.vp_dob);
                vp_emailp=itemView.findViewById(R.id.vp_email);
                vp_phonep=itemView.findViewById(R.id.vp_phone);




            }
        }
        @NonNull
        @Override
        public vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            View V=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.doc_view_prescribed_pat_exp,viewGroup,false);
            return new vh(V) ;
        }

        @Override
        public void onBindViewHolder(@NonNull vh vh, int i)
        {
            vh.vp_pre_idp.setText(dvp_pres_idA.get(i));
            vh.vp_doc_idp.setText(dvp_doc_idA.get(i));
            vh.vp_pat_idp.setText(dvp_pat_idA.get(i));
            vh.vp_med_morp.setText(dvp_pres_medicine_morA.get(i));
            vh.vp_med_aftp.setText(dvp_pres_medicine_aftA.get(i));
            vh.vp_med_evep.setText(dvp_pres_medicine_eveA.get(i));
            vh.vp_pre_datep.setText(dvp_pres_dateA.get(i));
            vh.vp_pre_timep.setText(dvp_pres_timeA.get(i));
            vh.vp_pre_repp.setText(dvp_pres_reportA.get(i));
            vh.vp_pre_time_evep.setText(dvp_pres_time_eveA.get(i));
            vh.vp_med_time_morp.setText(dvp_pres_medicine_time_morA.get(i));
            vh.vp_med_time_aftp.setText(dvp_pres_medicine_time_aftA.get(i));
            vh.vp_med_time_eveningp.setText(dvp_pres_medicine_time_eveA.get(i));
            vh.vp_namep.setText(dvp_pat_nameA.get(i));
            vh.vp_agep.setText(dvp_pat_ageA.get(i));
            vh.vp_dobp.setText(dvp_pat_dobA.get(i));
            vh.vp_emailp.setText(dvp_pat_emailA.get(i));
            vh.vp_phonep.setText(dvp_pat_phoneA.get(i));

        }

        @Override
        public int getItemCount() {
            return dvp_pres_idA.size();
        }

    }


}
