package com.emiaoqian.express.net;//package com.example.signgg.signgg.net;
//
//import com.example.signgg.signgg.utils.ToastUtil;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
///**
// * Created by xiong on 2017/8/18.
// */
//
//public class retrofitutils  {
//
//    public void retrofit(String url){
//        Retrofit.Builder builder=new Retrofit.Builder();
//        builder.baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
//    }
//
//
//
//    public void Okhttp3(){
//        OkHttpClient okHttpClient=new OkHttpClient.Builder().connectTimeout().build();
//
//        Request reques=new Request.Builder().get().url().build();
//        Call call = okHttpClient.newCall(reques);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//            }
//        });
//
//
//    }
//}
