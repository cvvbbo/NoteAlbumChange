package com.emiaoqian.express.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.emiaoqian.express.R;
import com.emiaoqian.express.adapter.Userinforadapter;
import com.emiaoqian.express.utils.LogUtil;
import com.emiaoqian.express.utils.ToastUtil;
import com.emiaoqian.express.view.ui.CircleImageView;
import com.emiaoqian.express.view.ui.Mydialog;
import com.emiaoqian.express.view.ui.Mydialogheadimage;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiong on 2017/9/4.
 */

public class Userinfor extends AppCompatActivity {


    @BindView(R.id.user_infor_list)
    ListView userInforList;

    TextView changeImage;


    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";

    File tempFile = new File(Environment.getExternalStorageDirectory(),
            PHOTO_FILE_NAME);
    @BindView(R.id.headIcon)
    CircleImageView headIcon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfor_view);
        ButterKnife.bind(this);
        Userinforadapter userinfor = new Userinforadapter();
        userInforList.setAdapter(userinfor);

        changeImage = (TextView) findViewById(R.id.change_image);
//        changeImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Mydialog mydialog=new Mydialog(Userinfor.this);
//
//            }
//        });
    }

    public void changer(View v) {
        LogUtil.e("---我被点击了");
        Mydialogheadimage headimage=new Mydialogheadimage(Userinfor.this);
        headimage.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                //Log.e("图片路径？？", data.getData() + "");
                crop(uri);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                crop(Uri.fromFile(tempFile));
            } else {
                ToastUtil.showToast("无法连接到sd卡");
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                final Bitmap bitmap = data.getParcelableExtra("data");
                headIcon.setImageBitmap(bitmap);

                //都是先设置图片，然后再上传头像！！之前有提示。
                //Bitmap bitmap1 = compressImage(tempFile.getPath());

            }else {
                return;
            }
                // 保存图片到internal storage（内存）
//                FileOutputStream outputStream;
//                try {
//                    ByteArrayOutputStream out = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                    out.flush();
//                    // out.close();
//                    // final byte[] buffer = out.toByteArray();
//                    // outputStream.write(buffer);
//                    outputStream = MainActivity.this.openFileOutput("_head_icon.jpg",
//                            Context.MODE_PRIVATE);
//                    out.writeTo(outputStream);
//                    out.close();
//                    outputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                if (tempFile != null && tempFile.exists())
//                    tempFile.delete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public Bitmap compressImage(String filepath) {
        int height = getWindowManager().getDefaultDisplay().getHeight();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        Point p = new Point();

        getWindowManager().getDefaultDisplay().getSize(p);

        width = p.x;
        height = p.y;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        //下面这个是获取不到大小的，因为加载进内存的大小为0
        Bitmap bitmap = BitmapFactory.decodeFile(filepath);
        BitmapFactory.decodeFile(filepath, options);
        //Log.e("--压缩之前", bitmap.getByteCount() + " ");
        int outHeight = options.outHeight;
        int outWidth = options.outWidth;
        int index = 1;
        if (outHeight > height || outWidth > width) {
            float heightRate = outHeight / height;
            float widthrate = outHeight / width;

            index = (int) Math.max(heightRate, widthrate);
        }
        options.inSampleSize = index;
        options.inJustDecodeBounds = false;
        Bitmap afterbitmap = BitmapFactory.decodeFile(filepath, options);
        //Log.e("--压缩之后", afterbitmap.getByteCount() + " ");
        return afterbitmap;
    }
}
