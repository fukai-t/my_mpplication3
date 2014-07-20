package com.example.app;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class CameraActivity extends ActionBarActivity {

    private static Camera mCam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        try{
            mCam = Camera.open();
        }catch (Exception e){
            this.finish();
        }
//        setContentView(new CameraPreview(this, mCam));

        FrameLayout preview = (FrameLayout)findViewById(R.id.cameView);
        CameraPreview mCamPreview = new CameraPreview(this, mCam);
        preview.addView(mCamPreview);
//        mCamPreview.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onPause(){
        super.onPause();
        if(mCam != null){
            mCam.release();
            mCam = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera, menu);
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

}
