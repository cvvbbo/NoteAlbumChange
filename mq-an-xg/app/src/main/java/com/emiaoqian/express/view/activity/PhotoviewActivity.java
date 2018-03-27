package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.emiaoqian.express.R;

import java.io.File;
import java.net.URI;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by xiong on 2018/3/26.
 */

public class PhotoviewActivity extends AppCompatActivity {



    private String filename;
    private PhotoView photoview;

    private PhotoViewAttacher attacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view);
        photoview = (PhotoView) findViewById(R.id.im);


        //根目录
        File appDir = new File(Environment.getExternalStorageDirectory(), "emiaoqian");

        Intent intent = getIntent();
        String photoname = intent.getStringExtra("photoname");
        if (photoname!=null){
            if (photoname.equals("stream_idcard_front.jpg")){
                filename=photoname;

            }else if (photoname.equals("stream_idcard_back.jpg")){
                filename=photoname;
            }
        }


        File path=new File(appDir,filename);

        Bitmap bitmap = BitmapFactory.decodeFile(path.getPath());
        photoview.setImageBitmap(bitmap);
        attacher = new PhotoViewAttacher(photoview);
        attacher.update();


    }
}
