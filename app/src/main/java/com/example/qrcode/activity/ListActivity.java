package com.example.qrcode.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.alibaba.fastjson.JSON;
import com.example.qrcode.BASE64.sun.misc.BASE64Decoder;
import com.example.qrcode.R;
import com.example.qrcode.adapter.ListAdapter;
import com.example.qrcode.entity.Info;
import com.example.qrcode.network.Api;
import com.example.qrcode.network.ApiConfig;
import com.example.qrcode.utils.ImgUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class ListActivity extends AppCompatActivity {
    private Api api =new Api();

    private List<Info> listnews=new ArrayList<>();
    private Integer userId;
    ImageButton user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //初始化数据
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView=findViewById(R.id.list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //实现纵向瀑布流
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter listAdapter = new ListAdapter(listnews,ListActivity.this);
        recyclerView.setAdapter(listAdapter);

    }

    private void initData() throws IOException {

        //取出用户id
        SharedPreferences sharedPre=getSharedPreferences(FILENAME, MODE_PRIVATE);
        userId= Integer.parseInt(sharedPre.getString("userId", ""));
        System.out.println("取出的用户id："+userId);

        Map<String,Object> params=new HashMap<>();
        params.put("id",userId);

        Response response = Api.config(ApiConfig.INFO_LIST, params).requestSyn();
        String res=response.body().string();

        //从json中转换数据成listnews
        Map<String,Object> map = JSON.parseObject(res, Map.class);
        Map<String,Object> data = (Map<String, Object>) map.get("data");
        List<Info> list = (List<Info>) data.get("list");

        listnews = JSON.parseArray(JSON.toJSONString(list), Info.class);
    }

}
