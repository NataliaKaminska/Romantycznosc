package com.example.romantycznosc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout mlayaut;
    private ActionBarDrawerToggle mtoggle;


    private Button button2;
    private Button button3;
    private Button button5;
    // private Toolbar mToolbar;
    private ScrollView glowna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2=(Button)findViewById(R.id.buttonMain);
        button2.setOnClickListener(this);

        button3=(Button)findViewById(R.id.buttonMain2);
        button3.setOnClickListener(this);


        button5=(Button)findViewById(R.id.buttonMain5);
        button5.setOnClickListener(this);

        glowna=findViewById(R.id.glownaa);


        mlayaut = (DrawerLayout) findViewById(R.id.main);
        mtoggle = new ActionBarDrawerToggle(this, mlayaut,R.string.open, R.string.close);
        mlayaut.addDrawerListener(mtoggle);
        NavigationView mnawgacja = (NavigationView) findViewById(R.id.nawigacja);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ustawContent(mnawgacja);
    }

    //public ActionBarDrawerToggle (Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes)

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void ustawContent (NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                wybranyElemnentMenu(item);
                return false;
            }
        });

    }

    private void wybranyElemnentMenu(MenuItem menuItem) {
        Fragment mfragment=null;

        Class fragmentClass;
        switch (menuItem.getItemId()){


            case R.id.o_nas:
                fragmentClass=O_nas.class;
                break;
            case R.id.menu:
                fragmentClass=Menu.class;
                break;
            case R.id.galeria:
                fragmentClass=Galeria.class;
                break;
            case R.id.kontakt:
                fragmentClass=MapsFragment.class;
                break;
            default:
                fragmentClass=MainActivity2.class;
        }

        try {
            mfragment=(Fragment) fragmentClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer,mfragment).commit();
        menuItem.setChecked(true);//moze nie zadzialac
        mlayaut.closeDrawers();


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonMain){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new O_nas()).commit();
        }
        if(view.getId()==R.id.buttonMain2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new Menu()).commit();
        }

        if(view.getId()==R.id.buttonMain5){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new MainActivity2()).commit();
            mlayaut.closeDrawers();


            glowna.fullScroll(ScrollView.FOCUS_UP);

        }
    }

}

