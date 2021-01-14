package com.example.qrcode.entity;

import java.io.Serializable;


/**
 * 客户信息表
 */
public class Info {
    private Integer id;
    private String clientname;
    //客户所属地区
    private String location;
    //快递送达的最终地址
    private String address;
    private String phone;

    public Info() {
    }

    public Info(Integer id, String clientname, String location, String address, String phone) {
        this.id = id;
        this.clientname = clientname;
        this.location = location;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", clientname='" + clientname + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

