package com.example.sujith.patient_diary;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    ImageView splap;
    TextView tvsplashp;
    Typeface myfont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splap=(ImageView)findViewById(R.id.spla);
        tvsplashp=(TextView)findViewById(R.id.tv_splash);
        myfont=Typeface.createFromAsset(this.getAssets(),"fonts/font_2.ttf");
        tvsplashp.setTypeface(myfont);

        Thread T=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Animation fad=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                    splap.startAnimation(fad);

                    Animation fadtex=AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_text);
                    tvsplashp.startAnimation(fadtex);

                    sleep(5000);

                    Intent i=new Intent(splash.this,page1.class);
                    startActivity(i);
                    finish();

                }
                catch (Exception e)
                {

                }
            }
        };
        T.start();
    }
}
