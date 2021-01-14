package com.example.qrcode.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.example.qrcode.BASE64.sun.misc.BASE64Decoder;
import com.example.qrcode.R;
import com.example.qrcode.network.ApiConfig;
import com.example.qrcode.network.Api;
import com.example.qrcode.utils.ImgUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {

    private Api api =new Api();

    ImageView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        code = findViewById(R.id.qrcode);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        System.out.println("我来到详情页面了");
        System.out.println(id);

        //初始化页面数据
        try {
            initData(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initData(int id) throws IOException {
        Map<String,Object> params=new HashMap<>();
        params.put("id",id);
        Response response = Api.config(ApiConfig.SHOW_QRCODE,params).requestSyn();
        String res=response.body().string();

        //从json中转换数据成listnews
        Map<String,Object> map = JSON.parseObject(res, Map.class);
        Map<String,Object> data = (Map<String, Object>) map.get("data");
        System.out.println(data);

        String scode= (String) data.get("code");
        System.out.println(scode);

        byte[] bytes = new BASE64Decoder().decodeBuffer(scode);
        Bitmap picFromBytes = ImgUtil.getPicFromBytes(bytes);
//        System.out.println(picFromBytes);
        code.setImageBitmap(picFromBytes);

    }
}
