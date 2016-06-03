package com.example.liuhe.bisheceshi.fragment;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.activity.ArticleValues;
import com.example.liuhe.bisheceshi.activity.ContentActivity;
import com.example.liuhe.bisheceshi.activity.MainActivity;
import com.example.liuhe.bisheceshi.adapter.ContentAdapter;
import com.example.liuhe.bisheceshi.adapter.MenuAdapter;
import com.example.liuhe.bisheceshi.model.ContentValuesItem;
import com.example.liuhe.bisheceshi.model.Kannerdata;
import com.example.liuhe.bisheceshi.model.NewsListItem;
import com.example.liuhe.bisheceshi.untils.HttpUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import krelve.view.Kanner;

/**
 * Created by liuhe on 16/5/25.
 */
public class MainFragment extends Fragment {

    private MainActivity mainActivity;
    private Kanner kanner;
    private Kannerdata data;
    private RequestQueue mQueue;
    private List<ContentValuesItem> item;
    private ListView list;
    private ContentAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_frg, container, false);

        initData(); //kanner 资源
        initNews(); //热门消息资源

        kanner = (Kanner) view.findViewById(R.id.kanner);
        list = (ListView) view.findViewById(R.id.main_list);

        kanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void initData() {

        String url = "http://news-at.zhihu.com/api/4/news/latest";
        mQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray array = response.getJSONArray("top_stories");
                    String[] img = new String[array.length()];
                    for (int i = 0; i < array.length(); i ++){
                        data = new Kannerdata();

                        JSONObject object = array.getJSONObject(i);
                        String newimg = (object.getString("image")).replace("\\", "");
                        img[i] = newimg;

                        data.setTitle( new String((object.getString("title")).getBytes("iso-8859-1"), "utf-8"));
                    }

                    kanner.setImagesUrl(img);
                    kanner.setKan_Title(data.getTitle());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }
        }, null);

        mQueue.add(request);

    }

    private void initNews() {

        String newurl = "http://news-at.zhihu.com/api/3/news/hot";
        mQueue = Volley.newRequestQueue(getContext());
        item = new ArrayList<>();

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, newurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray array = response.getJSONArray("recent");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        ContentValuesItem convalues = new ContentValuesItem();
                        convalues.setTitle(new String((object.getString("title")).getBytes("iso-8859-1"), "utf-8"));
                        convalues.setImage(object.getString("thumbnail"));
                        convalues.setId(object.getString("news_id"));
                        item.add(convalues);

                    }

                    adapter = new ContentAdapter(getContext(), item);
                    list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getContext(), ArticleValues.class);
                            ContentValuesItem conitem = item.get(position);
                            intent.putExtra("id", conitem.getId());

                            startActivity(intent);
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
