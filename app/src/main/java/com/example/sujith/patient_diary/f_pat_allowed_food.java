package com.example.sujith.patient_diary;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class f_pat_allowed_food extends Fragment {

    RecyclerView rec_pat_allowed_foodp;



    ArrayList<String> pat_diet_idA,pat_pat_idA,pat_doc_idA,pat_diet_dayA,pat_diet_morA,pat_diet_aftA,
            pat_diet_eveA,pat_update_timeA,pat_update_dateA;

    adapter_allo ada;

    AsyncHttpClient client_allo;
    RequestParams params_allo;
    JSONObject obj1_allo;
    JSONArray jarry;

    String url_allo="http://srishti-systems.info/projects/patient_diary/api/patient_allowedfood.php";






    public f_pat_allowed_food() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View Va=inflater.inflate(R.layout.fragment_f_pat_allowed_food, container, false);

        client_allo=new AsyncHttpClient();
        params_allo=new RequestParams();

        params_allo.put("pat_id","1");

        rec_pat_allowed_foodp=(RecyclerView)Va.findViewById(R.id.pat_f_allowed_food);

        pat_diet_idA=new ArrayList<>();
        pat_pat_idA=new ArrayList<>();
        pat_doc_idA=new ArrayList<>();
        pat_diet_dayA=new ArrayList<>();
        pat_diet_morA=new ArrayList<>();
        pat_diet_aftA=new ArrayList<>();
        pat_diet_eveA=new ArrayList<>();
        pat_update_timeA=new ArrayList<>();
        pat_update_dateA=new ArrayList<>();


        client_allo.get(url_allo,params_allo,new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(String content)
            {
                super.onSuccess(content);

                try
                {
                    obj1_allo=new JSONObject(content);

                    if (obj1_allo.getString("status").equals("success"))
                    {
                        jarry=obj1_allo.getJSONArray("Diet_details");
                        for (int i=0;i<jarry.length();i++)
                        {
                            JSONObject obj2_allo=jarry.getJSONObject(i);
                            pat_diet_idA.add("Diet id : "+obj2_allo.getString("diet_id"));
                            pat_pat_idA.add("Patient Id : "+obj2_allo.getString("pat_id"));
                            pat_doc_idA.add("Doctor Id : "+obj2_allo.getString("doc_id"));
                            pat_diet_dayA.add("Diet Id:"+obj2_allo.getString("diet_day"));
                            pat_diet_morA.add("Morning Diet : "+obj2_allo.getString("diet_morning"));
                            pat_diet_aftA.add("Afternoon Diet : "+obj2_allo.getString("diet_afternoon"));
                            pat_diet_eveA.add("Evening Diet : "+obj2_allo.getString("diet_evening"));
                            pat_update_timeA.add("Update time : "+obj2_allo.getString("update_time"));
                            pat_update_dateA.add("Update Date : "+obj2_allo.getString("update_date"));

                        }

                    }

                    ada=new adapter_allo(pat_diet_idA);
                    LinearLayoutManager lm_allo=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

                    rec_pat_allowed_foodp.setAdapter(ada);
                    rec_pat_allowed_foodp.setLayoutManager(lm_allo);

                }
                catch (Exception e)
                {

                }

            }
        });


        return Va;
    }

    class adapter_allo extends RecyclerView.Adapter<adapter_allo.vh_allo>
    {
        List<String> l_allo;

        adapter_allo(List<String> l_allo)
        {
            this.l_allo=pat_diet_idA;
        }

        class vh_allo extends RecyclerView.ViewHolder
        {
            TextView pat_diet_idp,pat_pat_idp,pat_doc_idp,pat_diet_dayp,pat_diet_morp,pat_diet_aftp,
                    pat_diet_evep,pat_update_timep,pat_update_datep;

            public vh_allo(@NonNull View itemView)
            {
                super(itemView);

                pat_diet_idp=(TextView)itemView.findViewById(R.id.pat_diet_id);
                pat_pat_idp=(TextView)itemView.findViewById(R.id.pat_pat_id);
                pat_doc_idp=(TextView)itemView.findViewById(R.id.pat_doc_id);
                pat_diet_dayp=(TextView)itemView.findViewById(R.id.pat_diet_day);
                pat_diet_morp=(TextView)itemView.findViewById(R.id.pat_diet_mor);
                pat_diet_aftp=(TextView)itemView.findViewById(R.id.pat_diet_aft);
                pat_diet_evep=(TextView)itemView.findViewById(R.id.pat_diet_eve);
                pat_update_timep=(TextView)itemView.findViewById(R.id.pat_update_time);
                pat_update_datep=(TextView)itemView.findViewById(R.id.pat_update_date);


            }
        }


        @NonNull
        @Override
        public vh_allo onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
        {
            View v_allo=LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.pat_allowed_food,viewGroup,false);


            return new vh_allo(v_allo);
        }

        @Override
        public void onBindViewHolder(@NonNull vh_allo v_allo, int i)
        {

            v_allo.pat_diet_idp.setText(pat_diet_idA.get(i));
            v_allo.pat_pat_idp.setText(pat_pat_idA.get((i)));
            v_allo.pat_doc_idp.setText(pat_doc_idA.get(i));
            v_allo.pat_diet_dayp.setText(pat_diet_dayA.get(i));
            v_allo.pat_diet_morp.setText(pat_diet_morA.get(i));
            v_allo.pat_diet_aftp.setText(pat_diet_aftA.get(i));
            v_allo.pat_diet_evep.setText(pat_diet_eveA.get(i));
            v_allo.pat_update_timep.setText(pat_update_timeA.get(i));
            v_allo.pat_update_datep.setText(pat_update_dateA.get(i));

        }

        @Override
        public int getItemCount() {
            return pat_diet_idA.size();
        }







    }





}
