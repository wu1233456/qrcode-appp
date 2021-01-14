package com.example.qrcode.network;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {

    private static String requestUrl;
    private static Map<String,Object> mParams;
    public static Api api=new Api();

    public static Api config(String url, Map<String, Object> params) {
        requestUrl=url;
        mParams=params;
        return api;
    }

    /**
     * 同步请求方法
     * @return
     */
    public Response requestSyn() {
        OkHttpClient okHttpClient = new OkHttpClient();

        //2.创建 FormBody 添加需要的键值对
        FormBody.Builder builder = new FormBody.Builder();
        if (mParams!=null){
            for (Map.Entry<String, Object> entry : mParams.entrySet()) {
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        FormBody formBody  = builder.build();
        // 3.构造Request
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(formBody)//键值对
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        Response res = null;
        try {
            res = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *异步请求方法
     * @param callback
     * @return
     * @throws IOException
     */
    public void requestAsy(Callback callback) {

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        System.out.println(mParams);
        //2.创建 FormBody 添加需要的键值对
        FormBody.Builder builder = new FormBody.Builder();
        if (mParams!=null){
            for (Map.Entry<String, Object> entry : mParams.entrySet()) {
                builder.add(entry.getKey(),String.valueOf(entry.getValue()));
            }
        }
        FormBody formBody  = builder.build();
        System.out.println(formBody);
        // 3.构造Request
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(formBody)//键值对
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);

        //5.异步请求enqueue(Callback)
        call.enqueue(callback);
    }


}
