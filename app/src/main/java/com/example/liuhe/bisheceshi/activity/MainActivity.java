package com.example.liuhe.bisheceshi.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.adapter.MenuAdapter;
import com.example.liuhe.bisheceshi.fragment.MainFragment;
import com.example.liuhe.bisheceshi.fragment.MenuContent;
import com.example.liuhe.bisheceshi.model.ContentValuesItem;
import com.example.liuhe.bisheceshi.model.NewsListItem;
import com.example.liuhe.bisheceshi.untils.Constant;
import com.example.liuhe.bisheceshi.untils.HttpUtils;
import com.example.liuhe.bisheceshi.untils.PreUtils;
import com.example.liuhe.bisheceshi.view.Main_TitleView;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private TextView tv_main;
    private ListView lv_item;
    private List<NewsListItem> items;
    private MenuAdapter adapter;
    private Toolbar toolbar;
    private Main_TitleView titleView;
    private SlidingMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        titleView = (Main_TitleView) findViewById(R.id.title);

        initMenu();

        initFragment();

    }

    private void initFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, new MainFragment())
        .commit();
    }

    private void initMenu() {

        final SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);

        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);

        titleView.setLeftTextView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.menu);

        tv_main = (TextView) findViewById(R.id.tv_main);
        lv_item = (ListView) findViewById(R.id.lv_item);

        tv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggle();
            }
        });

        lv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, MenuContent.class);
                NewsListItem item = items.get(position);
                intent.putExtra("id", item.getId());
                startActivity(intent);
            }
        });

        items = new ArrayList<NewsListItem>();

        if (HttpUtils.isNetworkConnected(MainActivity.this)){
            HttpUtils.get(Constant.THEMES, new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    String json = response.toString();
                    PreUtils.putStringToDefault(MainActivity.this, Constant.THEMES, json);
                    parseJson(response);
                }
            });
        }else{
            String json = PreUtils.getStringFromDefault(MainActivity.this, Constant.THEMES, "");
            try {
                JSONObject jsonObject = new JSONObject(json);
                parseJson(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void parseJson(JSONObject response) {

        try {

            JSONArray itemsArray = response.getJSONArray("others");
            for (int i = 0; i < itemsArray.length(); i++){
                JSONObject object = itemsArray.getJSONObject(i);
                NewsListItem newsListItem = new NewsListItem();
                newsListItem.setTitle(object.getString("name"));
                newsListItem.setId(object.getString("id"));
                items.add(newsListItem);
            }
            adapter = new MenuAdapter(MainActivity.this, items);
            lv_item.setAdapter(adapter);
            adapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
