package com.example.app;

import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import java.io.IOException;

/*
 * Created by fukaitakaaki on 2014/07/20.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
    Camera mCam;

    public CameraPreview(Context context, Camera cam) throws AssertionError {
        super(context);
        mCam = cam;

        SurfaceHolder holder = getHolder();
        if (holder == null) {
            throw new AssertionError();
        }
        else {
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            mCam.setPreviewDisplay(holder);
            mCam.startPreview();
        }catch (IOException e){
            //
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
