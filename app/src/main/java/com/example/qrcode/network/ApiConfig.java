package com.example.qrcode.network;

public class ApiConfig {
    //全部的请求链接
    private static  final String BASE_URL="http://10.0.2.2:8080/api";
    public static  final String LOGIN=BASE_URL+"/user/login";
    public static final String REGISTER=BASE_URL+"/user/register";
    public static  final String INFO_LIST=BASE_URL+"/info/getlist";
    public static  final String SHOW_QRCODE=BASE_URL+"/info/get";

}
