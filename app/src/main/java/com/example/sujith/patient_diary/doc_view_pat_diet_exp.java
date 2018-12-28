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
public class doc_view_pat_diet_exp extends Fragment {

    RecyclerView diet_doc_view_patp;

    AsyncHttpClient clientd;
    RequestParams paramsd;
    JSONObject obj1d;
    JSONArray jarryd;

    ArrayList<String> diet_diet_idA,diet_pat_idA,diet_doc_idA,diet_diet_dayA,
            diet_morA,diet_aftA,diet_eveA,diet_upd_timeA,diet_upd_dateA,diet_pat_nameA,
            diet_pat_ageA,diet_pat_dobA,diet_pat_emailA,diet_pat_phoneA;

    String urld="http://srishti-systems.info/projects/patient_diary/api/doc_viewpatientdietbyid.php";

    Vertic adaptd;
    LinearLayoutManager ld;


    public doc_view_pat_diet_exp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View dv=inflater.inflate(R.layout.fragment_doc_view_pat_diet_exp, container, false);

        diet_doc_view_patp=dv.findViewById(R.id.rec_diet_doc_view_pat);

        clientd=new AsyncHttpClient();
        paramsd=new RequestParams();


        diet_diet_idA=new ArrayList<>();
        diet_pat_idA=new ArrayList<>();
        diet_doc_idA=new ArrayList<>();
        diet_diet_dayA=new ArrayList<>();
        diet_morA=new ArrayList<>();
        diet_aftA=new ArrayList<>();
        diet_eveA=new ArrayList<>();
        diet_upd_timeA=new ArrayList<>();
        diet_upd_dateA=new ArrayList<>();
        diet_pat_nameA=new ArrayList<>();
        diet_pat_ageA=new ArrayList<>();
        diet_pat_dobA=new ArrayList<>();
        diet_pat_emailA=new ArrayList<>();
        diet_pat_phoneA=new ArrayList<>();




        SharedPreferences sp_doc_d=getContext().getSharedPreferences("dd",MODE_PRIVATE);

        paramsd.put("pat_id",sp_doc_d.getString("diet_pat_id",null));
        paramsd.put("doc_id",sp_doc_d.getString("diet_doc_id",null));


        clientd.get(urld,paramsd,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
                super.onSuccess(content);

                try
                {
                    obj1d=new JSONObject(content);

                    if (obj1d.getString("status").equals("success"))
                    {
                        jarryd=obj1d.getJSONArray("PatientDiet_details");
                        for (int i=0;i<jarryd.length();i++)
                        {
                            JSONObject obj2d=jarryd.getJSONObject(i);

                            diet_diet_idA.add("Diet Id : "+obj2d.getString("diet_id"));
                            diet_pat_idA.add("Patient Id : "+obj2d.getString("pat_id"));
                            diet_doc_idA.add("Doctor Id : "+obj2d.getString("doc_id"));
                            diet_diet_dayA.add("Diet Day : "+obj2d.getString("diet_day"));
                            diet_morA.add("Diet Morning : "+obj2d.getString("diet_morning"));
                            diet_aftA.add("Diet Afternon : "+obj2d.getString("diet_afternoon"));
                            diet_eveA.add("Diet Evening : "+obj2d.getString("diet_evening"));
                            diet_upd_timeA.add("Update Time : "+obj2d.getString("update_time"));
                            diet_upd_dateA.add("Update Date : "+obj2d.getString("update_date"));
                            diet_pat_nameA.add("Patient Name : "+obj2d.getString("pat_name"));
                            diet_pat_ageA.add("Patient Age : "+obj2d.getString("pat_age"));
                            diet_pat_dobA.add("Patient DOB : "+obj2d.getString("pat_dob"));
                            diet_pat_emailA.add("Patient email : "+obj2d.getString("pat_email"));
                            diet_pat_phoneA.add("Patient Phone : "+obj2d.getString("pat_phone"));


                        }
                    }

                    adaptd=new Vertic(diet_diet_idA);
                    ld=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

                    diet_doc_view_patp.setAdapter(adaptd);
                    diet_doc_view_patp.setLayoutManager(ld);

                }
                catch (Exception e)
                {

                }

            }
        });

        return dv;
    }


    class Vertic extends RecyclerView.Adapter<Vertic.vhd>
    {
        List<String> l;

        Vertic(List<String> l)
        {
            this.l=diet_diet_idA;
        }




        class vhd extends RecyclerView.ViewHolder
        {
            TextView diet_diet_idp,diet_pat_idp,diet_doc_idp,diet_diet_dayp,diet_diet_morp,
                    diet_diet_aftp,diet_diet_evep,diet_upd_timep,diet_upd_datep,diet_pat_namep,
                    diet_pat_agep,diet_pat_dobp,diet_pat_emailp,diet_pat_php;

            public vhd(@NonNull View itemView)
            {
                super(itemView);

                diet_diet_idp=itemView.findViewById(R.id.diet_diet_id);
                diet_pat_idp=itemView.findViewById(R.id.diet_pati_id);
                diet_doc_idp=itemView.findViewById(R.id.diet_doct_id);
                diet_diet_dayp=itemView.findViewById(R.id.diet_diet_day);
                diet_diet_morp=itemView.findViewById(R.id.diet_diet_mor);
                diet_diet_aftp=itemView.findViewById(R.id.diet_diet_aft);
                diet_diet_evep=itemView.findViewById(R.id.diet_diet_eve);
                diet_upd_timep=itemView.findViewById(R.id.diet_upd_time);
                diet_upd_datep=itemView.findViewById(R.id.diet_upd_date);
                diet_pat_namep=itemView.findViewById(R.id.diet_pat_name);
                diet_pat_agep=itemView.findViewById(R.id.diet_pat_age);
                diet_pat_dobp=itemView.findViewById(R.id.diet_pat_dob);
                diet_pat_emailp=itemView.findViewById(R.id.diet_pat_email);
                diet_pat_php=itemView.findViewById(R.id.diet_pat_ph);






            }
        }

        @NonNull
        @Override
        public vhd onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View vv=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.doc_view_diet_exp_view,viewGroup,false);
            return new vhd(vv) ;
        }

        @Override
        public void onBindViewHolder(@NonNull vhd vh, int i)
        {

            vh.diet_diet_idp.setText(diet_diet_idA.get(i));
            vh.diet_pat_idp.setText(diet_pat_idA.get(i));
            vh.diet_doc_idp.setText(diet_doc_idA.get(i));
            vh.diet_diet_dayp.setText(diet_diet_dayA.get(i));
            vh.diet_diet_morp.setText(diet_morA.get(i));
            vh.diet_diet_aftp.setText(diet_aftA.get(i));
            vh.diet_diet_evep.setText(diet_eveA.get(i));
            vh.diet_upd_timep.setText(diet_upd_timeA.get(i));
            vh.diet_upd_datep.setText(diet_upd_dateA.get(i));
            vh.diet_pat_namep.setText(diet_pat_nameA.get(i));
            vh.diet_pat_agep.setText(diet_pat_ageA.get(i));
            vh.diet_pat_dobp.setText(diet_pat_dobA.get(i));
            vh.diet_pat_emailp.setText(diet_pat_emailA.get(i));
            vh.diet_pat_php.setText(diet_pat_phoneA.get(i));



        }

        @Override
        public int getItemCount() {
            return diet_diet_idA.size();
        }




    }

}
