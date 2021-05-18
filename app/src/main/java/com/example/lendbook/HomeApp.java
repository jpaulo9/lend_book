package com.example.lendbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.lendbook.objetos.User;
import com.google.android.material.tabs.TabLayout;

public class HomeApp extends AppCompatActivity  {

    User usuario;

    private static final int REQUEST_CODE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        Bundle daddos = getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("User"))) {
            usuario = (User) daddos.getSerializable("User");

        }else {
            Intent intent = new Intent(HomeApp.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabMen);
        final ViewPager pag =  (ViewPager) findViewById(R.id.viewpage);

        final Opt_Fragment tabLa = new Opt_Fragment(getSupportFragmentManager(),tabLayout.getTabCount());
        pag.setAdapter(tabLa);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                pag.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 ){
                    tabLa.notifyDataSetChanged();

                }else if (tab.getPosition() == 1 ){
                    tabLa.notifyDataSetChanged();

                }else if(tab.getPosition() == 2 ){
                    tabLa.notifyDataSetChanged();

                }else if (tab.getPosition() == 3){
                    tabLa.notifyDataSetChanged();

                }else if (tab.getPosition()== 4){
                    tabLa.notifyDataSetChanged();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        pag.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



    }


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.optM_locale:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(
                            HomeApp.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_CODE
                    );


                }


                Intent intent = new Intent(HomeApp.this, MapsLend.class);
                intent.putExtra("User", usuario);
                startActivity(intent);
                break;
            case R.id.optMsair:
                Intent intentS = new Intent(HomeApp.this, MainActivity.class);
                startActivity(intentS);
                finish();
                break;

        }


        return super.onOptionsItemSelected(item);
    }
}

