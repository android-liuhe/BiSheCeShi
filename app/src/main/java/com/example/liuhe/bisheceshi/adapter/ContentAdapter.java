package com.example.liuhe.bisheceshi.adapter;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.liuhe.bisheceshi.MyAppliction;
import com.example.liuhe.bisheceshi.BitMapCache;
import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.model.ContentValuesItem;

import java.util.List;

/**
 * Created by liuhe on 16/6/1.
 */
public class ContentAdapter extends BaseAdapter{

    private Context context;
    private List<ContentValuesItem> items;
    private LayoutInflater inflater;

    public ContentAdapter(Context context, List<ContentValuesItem> items) {
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContextViewHolder holder = new ContextViewHolder();
        if (convertView == null){

            convertView = inflater.inflate(R.layout.main_list_item, parent, false);
            holder.tv = (TextView) convertView.findViewById(R.id.main_list_item_tv);
            holder.imageView = (NetworkImageView) convertView.findViewById(R.id.main_list_item_image);
            convertView.setTag(holder);

        }else{
            holder = (ContextViewHolder) convertView.getTag();

        }

        holder.tv.setText(items.get(position).getTitle());
        holder.imageView.setImageUrl(items.get(position).getImage(), new ImageLoader(MyAppliction.getHttpQueue(), new BitMapCache()));

        return convertView;
    }

    class ContextViewHolder{
        private TextView tv;
        private NetworkImageView imageView;
    }
}
