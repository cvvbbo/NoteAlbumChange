package com.emiaoqian.express.view.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.emiaoqian.express.R;

import java.io.File;


public class Mydialog extends Dialog{

	private Button album;
	private Button makephoto;
	private Button btexit;

	public Activity a;

	public  Context mcontext;

	public Mydialog(Context context, boolean cancelable,
                    OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}

	public Mydialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	//��ʽ�Ļ������������á�
	public Mydialog(Context context) {
		super(context, R.style.Mydialog);
		a= (Activity) context;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_choose_dialog);
		Window window = getWindow();
		LayoutParams attributes = window.getAttributes();
		attributes.gravity=Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
		attributes.height=ViewGroup.LayoutParams.WRAP_CONTENT;
		attributes.width=ViewGroup.LayoutParams.MATCH_PARENT;
		window.setAttributes(attributes);
		initialize();
	}


	private void initialize() {

		//相册
		album = (Button) findViewById(R.id.album);
		//照片
		makephoto = (Button) findViewById(R.id.makephoto);
		//取消按钮
		btexit = (Button) findViewById(R.id.btexit);


		btexit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();

			}
		});

		makephoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

                doTakePhoto();
				dismiss();
			}
		});


		//相册
		album.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getimagefromalbun();

				dismiss();
			}
		});
	}


	//拍照中获取
    private void doTakePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //调用系统相机
        Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        a.startActivityForResult(intent, 100);  //用户点击了从相机获取
    }


    //从相册中获取
    public void getimagefromalbun() {
        Intent intent = new Intent();
        intent.setType("image/*");  // 开启Pictures画面Type设定为image
        intent.setAction(Intent.ACTION_GET_CONTENT); //使用Intent.ACTION_GET_CONTENT这个Action
        a.startActivityForResult(intent, 102); //取得相片后返回到本画面

    }
}
