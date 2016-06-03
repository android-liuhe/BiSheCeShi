package com.example.liuhe.bisheceshi;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by liuhe on 16/5/14.
 */
public class BitMapCache implements ImageLoader.ImageCache {

    public LruCache<String, Bitmap> cache;
    public int max = 10 * 1024 * 1024; //定义缓冲区大小,如果超出大小，则启动自动回收

    public BitMapCache() {
        cache = new LruCache<String, Bitmap>(max){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        cache.put(s , bitmap);
    }
}
