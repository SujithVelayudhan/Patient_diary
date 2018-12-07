package com.example.sujith.patient_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class page1 extends AppCompatActivity {

    LinearLayout patient_layoutp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        patient_layoutp=(LinearLayout)findViewById(R.id.patient_layout);

        patient_layoutp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(page1.this,patient_registration.class);
                startActivity(i);
            }
        });


    }
}
