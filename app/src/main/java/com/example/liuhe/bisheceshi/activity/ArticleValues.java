package com.example.liuhe.bisheceshi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.liuhe.bisheceshi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by liuhe on 16/6/3.
 */
public class ArticleValues extends Activity {

    private String url = "http://news-at.zhihu.com/api/4/news/";
    private WebView webView;
    private RequestQueue mQueue;

    public ArticleValues() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article);

        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        initData();
    }

    private void initData() {

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        String newUrl = url.concat(id);
        Log.d("aaa", newUrl);

        mQueue = Volley.newRequestQueue(ArticleValues.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String body = new String((response.getString("body")).getBytes("iso-8859-1"), "utf-8");
                    String html = body.replace("<div class=\"img-place-holder\">", "");
                    webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        mQueue.add(request);
    }
}
