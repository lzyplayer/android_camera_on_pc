package com.example.androidcamera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomCamera extends AppCompatActivity implements SurfaceHolder.Callback {

    private boolean waitFlag = true;

    TCPSender tcpSender = new TCPSender("192.168.1.106", 31845);

    private Button bt;
    private Camera mCamera;
    private SurfaceView mPreview;
    private SurfaceHolder mHolder;
    private ImageView imageView;
    private EditText editText;
    private String cFilePath;
    private File tempFile;
    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback(){

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            try {
                FileOutputStream fos = new FileOutputStream(tempFile);
                try {
                    fos.write(data);
                    fos.close();

                    /////////////////
//                    Intent intent = new Intent(CustomCamera.this, ResultAty.class);
//                    intent.putExtra("picPath", tempFile.getAbsolutePath());
//                    startActivity(intent);
                    ////////////////
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

//            bitmap = BitmapFactory.decodeFile(cFilePath);
//            imageView.setImageBitmap(bitmap);

            try {
                tcpSender.sendImage(cFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

//            fileDelete(cFilePath);

            mCamera.takePicture(null,null, mPictureCallback);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom);
        mPreview = (SurfaceView) findViewById(R.id.preview);
        mHolder = mPreview.getHolder();
        mHolder.addCallback(this);
        cFilePath = Environment.getExternalStorageDirectory().getPath();
        cFilePath = cFilePath + "/temp.png";
        tempFile = new File(cFilePath);
        imageView = (ImageView) findViewById(R.id.pic);
        editText = (EditText) findViewById(R.id.et);

//        bt = (Button) findViewById(R.id.btp);
//        bt.setOnClickListener(new MyListener());

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCamera == null){
            mCamera = getCamera();
            if (mHolder != null){
                setStartPreview(mCamera, mHolder);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    private Camera getCamera(){
        Camera camera;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            camera = null;
            e.printStackTrace();
        }
        return camera;
    }

    private void setStartPreview(Camera camera, SurfaceHolder holder){
        try {
            camera.setPreviewDisplay(holder);
//            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera(){
        if (mCamera != null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    public void capture(View view){

        String inputText=editText.getText().toString();
        if (!inputText.equals("") ){
            tcpSender.setAddress(inputText);
        }

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setPictureSize(1200, 1024);
        parameters.setPreviewSize(1200, 1024);
//        parameters.setPictureSize(648, 480);
//        parameters.setPreviewSize(648, 480);

        mCamera.takePicture(null,null, mPictureCallback);

//        Bitmap bitmap;
//        int i = 100;
//        while(i-->0){
//            mCamera.takePicture(null,null, mPictureCallback);
//
////            try {
////                tcpSender.sendImage(cFilePath);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            bitmap = BitmapFactory.decodeFile(cFilePath);
////            imageView.setImageBitmap(bitmap);
////            fileDelete(cFilePath);
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }



    private void fileDelete(String fileName){
        File file = new File(fileName);
        if (file.exists()){
            if (file.isFile()){
                file.delete();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        setStartPreview(mCamera, mHolder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

//    class MyListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v) {
//            List<String> permissionList = new ArrayList<String>();
//            if(ContextCompat.checkSelfPermission(CustomCamera.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
//            }
//            if(ContextCompat.checkSelfPermission(CustomCamera.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//            }
//            if(ContextCompat.checkSelfPermission(CustomCamera.this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.INTERNET);
//            }
//            if(ContextCompat.checkSelfPermission(CustomCamera.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                permissionList.add(Manifest.permission.CAMERA);
//            }
//            if(!permissionList.isEmpty()) {
//                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
//                ActivityCompat.requestPermissions(CustomCamera.this,permissions,1);
//            } else {
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                Uri photoUri = Uri.fromFile(new File(mFilePath));
////                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
////                startActivityForResult(intent,REQ_2);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 1) {
//            if(grantResults.length > 0) {
//                for(int result : grantResults) {
//                    if(result != PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(CustomCamera.this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
//                        finish();
//                        return;
//                    }
//                }
////                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////                Uri photoUri = Uri.fromFile(new File(mFilePath));
////                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
////                startActivityForResult(intent,REQ_2);
//            } else {
//                Toast.makeText(CustomCamera.this,"发生未知错误",Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }
}
