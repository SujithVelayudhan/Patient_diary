package com.example.sujith.patient_diary;

import android.content.SharedPreferences;
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
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class doc_profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawer_doc;
    int flag=0;
    TextView Nav_doc_idp,Nav_doc_namep;

    SharedPreferences sp_doc_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_profile);

        NavigationView navigationv = (NavigationView) findViewById(R.id.doc_nav_view);
        navigationv.setNavigationItemSelectedListener(this);


        Toolbar toolbar_d = (Toolbar) findViewById(R.id.doc_toolbar);
        setSupportActionBar(toolbar_d);

        sp_doc_log=getApplicationContext()
                .getSharedPreferences("d1",MODE_PRIVATE);


        View holdd=navigationv.getHeaderView(0);
        Nav_doc_idp=(TextView)holdd.findViewById(R.id.Nav_doc_id);
        Nav_doc_namep=(TextView)holdd.findViewById(R.id.Nav_doc_name);

        Nav_doc_idp.setText("Doc Id : "+sp_doc_log.getString("did",null));
        Nav_doc_namep.setText("Doc Name : "+sp_doc_log.getString("dna",null));

        final FloatingActionButton fabdd = (FloatingActionButton) findViewById(R.id.fabd);
        fabdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                PopupMenu pop=new PopupMenu(doc_profile.this,fabdd);
                pop.getMenuInflater().inflate(R.menu.pop_up_floating_menu,pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {

                        int id=menuItem.getItemId();

                        if (id==R.id.doc_add_diet)
                        {
                            doc_add_diet_pat dadd=new doc_add_diet_pat();
                            loadfragment(dadd);
                        }
                       return false;
                    }
                });
                pop.show();




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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.doc_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();

        if (id==R.id.doc_change_password)
        {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        doc_view_pat_by_id dv=new doc_view_pat_by_id();
        loadfragment(dv);


    }

    public void onBackPressed()
    {
        flag++;

        if (drawer_doc.isDrawerOpen(GravityCompat.START)) {
            drawer_doc.closeDrawer(GravityCompat.START);
        } else
            {
            //super.onBackPressed();

                doc_view_pat_by_id dv=new doc_view_pat_by_id();
                loadfragment(dv);

        }
        if (flag==2)
        {
            flag=0;
            finish();
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
        else if (id==R.id.view_diet)
        {
            doc_view_diet_byid dvpd=new doc_view_diet_byid();
            loadfragment(dvpd);

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
