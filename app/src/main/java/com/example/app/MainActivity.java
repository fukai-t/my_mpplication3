package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements LocationListener {

    /*
    layout objects
     */
    private static TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }
    private static LocationManager locationManager;

    private static void showLocation() {
        Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(l != null){
            String str = "";
            str += Double.toString(l.getLatitude());
            str += ", ";
            str += Double.toString(l.getLatitude());
            tv.setText(str);
        }else{
            tv.setText("Location info is nothing");
        }
    }


    @Override
    public void onStart(){
        super.onStart();
        // ロケーションマネージャーのインスタンスを取得
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        final boolean gpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnable){
            enableLocationSetting();
        }

        // 位置情報の更新を受け取るように設定
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    @Override
    public void onStop(){
        super.onStop();
        locationManager.removeUpdates(this);
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


    /*
    implementation for LocationLister.
     */

    @Override
    public void onLocationChanged(Location location) {
        String str = "";
        str += Double.toString(location.getLatitude());
        str += ", ";
        str += Double.toString(location.getLatitude());
        tv.setText(str);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            assert rootView != null;
            tv = (TextView)rootView.findViewById(R.id.textView);
            Button btn = (Button)rootView.findViewById(R.id.button);

            btn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                    showLocation();
                }
            });
            return rootView;
        }
    }


    private void enableLocationSetting(){
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

}
