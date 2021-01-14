package com.example.qrcode.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImgUtil {
    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     * @param bytes
     * @return Bitmap
     */
    public static Bitmap getPicFromBytes(byte[] bytes) {
        if (bytes != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

}
