package com.example.androidcamera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static int REQ_1 = 1;
    private static int REQ_2 = 2;
    private ImageView mImageView;
    private Button bt;
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.iv);
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath + "/" + "temp.png";

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        bt = (Button) findViewById(R.id.bt2);
        bt.setOnClickListener(new MyListener());
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            List<String> permissionList = new ArrayList<String>();
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.INTERNET);
            }
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CAMERA);
            }
            if(!permissionList.isEmpty()) {
                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
            } else {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Uri photoUri = Uri.fromFile(new File(mFilePath));
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
//                startActivityForResult(intent,REQ_2);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if(grantResults.length > 0) {
                for(int result : grantResults) {
                    if(result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Uri photoUri = Uri.fromFile(new File(mFilePath));
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
//                startActivityForResult(intent,REQ_2);
            } else {
                Toast.makeText(MainActivity.this,"发生未知错误",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


//    public void startCamera1(View view){
//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQ_1);
//    }

//    public void startCamera2(View view){
//        Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri photoUri = Uri.fromFile(new File(mFilePath));
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//        startActivityForResult(intent, REQ_2);
//    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK){
//            if (requestCode == REQ_1){
//                Bundle bundle = data.getExtras();
//                Bitmap bitmap = (Bitmap) bundle.get("data");
//                mImageView.setImageBitmap(bitmap);
//            }
//            else if (requestCode == REQ_2){
//                FileInputStream fis = null;
//                try {
//                    fis = new FileInputStream(mFilePath);
//                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
//                    mImageView.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }finally {
//                    try {
//                        fis.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }

    public void customCamera(View view){
        startActivity(new Intent(this, CustomCamera.class));
    }
}
