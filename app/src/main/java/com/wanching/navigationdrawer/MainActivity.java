package com.wanching.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
  //  private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
  //  CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList<DrawerItem>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    //    mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.search,
                GravityCompat.START);

        dataList.add(new DrawerItem("Message", R.drawable.search));
        dataList.add(new DrawerItem("Message", R.drawable.search));
        dataList.add(new DrawerItem("Message", R.drawable.search));

     //   adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_layout, dataList);
     //   mDrawerList.setAdapter(adapter);
    //    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.search, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if(savedInstanceState == null){
            SelectItem(0);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }



    public void SelectItem(int pos) {
        android.app.Fragment fragment = null;
        Bundle args = new Bundle();

        switch (pos) {
            case 0:
                fragment = new FragmentOne();
                args.putString(FragmentOne.ITEM_NAME, dataList.get(pos)
                        .getItemName());
                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(pos)
                        .getImgResID());
                break;
            case 1:
                fragment = new FragmentTwo();
                args.putString(FragmentTwo.ITEM_NAME, dataList.get(pos)
                        .getItemName());
                args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(pos)
                        .getImgResID());
                break;
            case 2:
                fragment = new FragmentThree();
                args.putString(FragmentThree.ITEM_NAME, dataList.get(pos)
                        .getItemName());
                args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(pos)
                        .getImgResID());
                break;
            default:
                break;
        }

        fragment.setArguments(args);
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, fragment).commit();

    //    mDrawerList.setItemChecked(pos, true);
        setTitle(dataList.get(pos).getItemName());
     //   mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }
}
