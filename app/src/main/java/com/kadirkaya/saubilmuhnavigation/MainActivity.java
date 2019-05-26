package com.kadirkaya.saubilmuhnavigation;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , BeaconConsumer {

    private static final String TAG =   "beaconManager" ;
    private BeaconManager beaconManager= null;
    int countkat0=0,countkat1=0,countkat2=0;
    String katToplam;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    beaconManager = BeaconManager.getInstanceForApplication(this);
    beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT));
    beaconManager.bind(this);


// Write a message to the database

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
       ImageView image = (ImageView) findViewById(R.id.imageView);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item1) {
            image.setImageResource(R.drawable.kat0);
            countkat0++;

        } else if (id == R.id.item2) {
            countkat1++;
            image.setImageResource(R.drawable.kat1);

        } else if (id == R.id.item3) {
            countkat1++;
            image.setImageResource(R.drawable.kat2);

        } else if (id == R.id.item4) {
            Intent intent = new Intent(this, MonitoringActivity.class);
            startActivity(intent);
        } else if (id == R.id.item5) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        katToplam= countkat0 + " kez kat 0" + countkat1 + " kez kat 1" +  countkat2 + " kez kat 2 erişimi yapıldı." ;
        return true;
    }


    private void startBeaconMonitoring(){

    }
    private void stopBeaconMonitoring(){

    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllMonitorNotifiers();
        beaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }
        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {    }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
