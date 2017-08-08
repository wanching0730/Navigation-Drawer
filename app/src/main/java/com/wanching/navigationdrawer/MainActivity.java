package com.wanching.navigationdrawer;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener {

    private String[] mPlanetTitles;
    private DrawerLayout myDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout, R.string.app_name, R.string.app_name){

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("halo");
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }
        };

        myDrawerLayout.setDrawerListener(mDrawerToggle);

        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPlanetTitles);
        mDrawerList.setAdapter(adapter);
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectItem(i);


            }
        });

    }

    private void selectItem(int position){
        Fragment fragment = new PlanetFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle("halo");
        myDrawerLayout.closeDrawer(mDrawerList);

    }

    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
