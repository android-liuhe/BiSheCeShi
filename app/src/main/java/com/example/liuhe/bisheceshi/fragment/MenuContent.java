package com.example.liuhe.bisheceshi.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.liuhe.bisheceshi.BitMapCache;
import com.example.liuhe.bisheceshi.MyAppliction;
import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.activity.ArticleValues;
import com.example.liuhe.bisheceshi.adapter.ContentAdapter;
import com.example.liuhe.bisheceshi.adapter.MenuContentAdapter;
import com.example.liuhe.bisheceshi.model.MenuContentValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuhe on 16/6/1.
 */
public class MenuContent extends Activity {

    private String url = "http://news-at.zhihu.com/api/4/theme/";
    private RequestQueue mQueue;
    private  String tureUrl;
    private List<MenuContentValues> item;
    private MenuContentAdapter adapter;
    private ListView listView;
    private NetworkImageView image;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_content);

        initData();


        listView = (ListView) findViewById(R.id.menu_content_list);
        image = (NetworkImageView) findViewById(R.id.menu_content_img);
        tv = (TextView) findViewById(R.id.menu_content_title);
    }

    private void initData() {

        Intent intent = getIntent();
        String urlid = intent.getExtras().getString("id");
        tureUrl = url.concat(urlid);
        Log.d("aaa", tureUrl);

        mQueue = Volley.newRequestQueue(MenuContent.this);
        item = new ArrayList<>();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, tureUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String name = (new String((response.getString("name")).getBytes("iso-8859-1"), "utf-8"));
                    tv.setText(name);
                    String topimageurl = response.getString("image");
                    image.setImageUrl(topimageurl, new ImageLoader(mQueue, new BitMapCache()));

                    JSONArray array = response.getJSONArray("stories");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        MenuContentValues values = new MenuContentValues();
                        values.setTitle(new String((object.getString("title")).getBytes("iso-8859-1"), "utf-8"));
                        values.setId(new String(object.getString("id")));
                        item.add(values);

                    }
                    adapter = new MenuContentAdapter(MenuContent.this, item);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            MenuContentValues cv = item.get(position);
                            Intent newintent = new Intent(MenuContent.this, ArticleValues.class);
                            newintent.putExtra("id", cv.getId());
                            startActivity(newintent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, null);

        mQueue.add(request);
    }
}
