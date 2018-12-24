package com.example.sujith.patient_diary;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class doc_profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer_doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);


        Toolbar toolbar_d = (Toolbar) findViewById(R.id.doc_toolbar);
        setSupportActionBar(toolbar_d);

        FloatingActionButton fabb = (FloatingActionButton) findViewById(R.id.fabD);
        fabb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        drawer_doc = (DrawerLayout) findViewById(R.id.doc_drawer_layout);
        ActionBarDrawerToggle toggle_doc = new ActionBarDrawerToggle(
                this, drawer_doc, toolbar_d,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_doc.addDrawerListener(toggle_doc);
        toggle_doc.syncState();

        NavigationView docnavigationView = (NavigationView) findViewById(R.id.doc_nav_view);
        docnavigationView.setNavigationItemSelectedListener(this);


    }

    public void onBackPressed() {

        if (drawer_doc.isDrawerOpen(GravityCompat.START)) {
            drawer_doc.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();

        if (id == R.id.doc_view_pat)
        {
            doc_view_pat_by_id dv=new doc_view_pat_by_id();
            loadfragment(dv);

        }
        else if (id==R.id.view_prescribed_patient)
        {
            doc_view_prescribed_patient dvp=new doc_view_prescribed_patient();
            loadfragment(dvp);

        }


        drawer_doc.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadfragment(Fragment df)
    {

        android.support.v4.app.FragmentTransaction fragT=
                getSupportFragmentManager().beginTransaction();
        fragT.replace(R.id.frame_doc,df);
        fragT.commit();
    }
}
