package com.example.sujith.patient_diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class patient_profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    TextView Nav_p_namep,Nav_p_idp;
    SharedPreferences sp_pat_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                f_pat_update_profile pup=new f_pat_update_profile();
                loadfragment(pup);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sp_pat_reg=getApplicationContext()
                .getSharedPreferences("k1",MODE_PRIVATE);

        View hold=navigationView.getHeaderView(0);
        Nav_p_idp=(TextView)hold.findViewById(R.id.Nav_p_id);
        Nav_p_namep=(TextView)hold.findViewById(R.id.Nav_p_name);


        Nav_p_namep.setText("Name : "+sp_pat_reg.getString("sna",null));
        Nav_p_idp.setText("Id : "+sp_pat_reg.getString("sid",null));



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==R.id.pat_ch_pass)
        {
            f_pat_chage_pass fcp=new f_pat_chage_pass();
            loadfragment(fcp);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pat_details)
        {
            f_patient_details fpd=new f_patient_details();
            loadfragment(fpd);

        } else if (id == R.id.menu_prescriptions)
        {
            f_patient_prescriptions fpp=new f_patient_prescriptions();
            loadfragment(fpp);
        }
        else if (id == R.id.pat_allowed_food)
        {
            f_pat_allowed_food fpaf=new f_pat_allowed_food();
            loadfragment(fpaf);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadfragment(Fragment fr)
    {
        android.support.v4.app.FragmentTransaction fragT=
                getSupportFragmentManager().beginTransaction();
        fragT.replace(R.id.fram_e,fr);
        fragT.commit();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        f_patient_details fpd=new f_patient_details();
        loadfragment(fpd);

    }
}
