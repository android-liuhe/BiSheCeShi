package com.example.liuhe.bisheceshi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.liuhe.bisheceshi.BitMapCache;
import com.example.liuhe.bisheceshi.MyAppliction;
import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.model.ContentValuesItem;
import com.example.liuhe.bisheceshi.model.MenuContentValues;

import java.util.List;

/**
 * Created by liuhe on 16/6/1.
 */
public class MenuContentAdapter extends BaseAdapter{

    private Context context;
    private List<MenuContentValues> items;
    private LayoutInflater inflater;

    public MenuContentAdapter(Context context, List<MenuContentValues> items) {
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

            convertView = inflater.inflate(R.layout.menu_list_item, parent, false);
            holder.tv = (TextView) convertView.findViewById(R.id.menu_list_item_tv);
            convertView.setTag(holder);

        }else{
            holder = (ContextViewHolder) convertView.getTag();

        }

        holder.tv.setText(items.get(position).getTitle());

        return convertView;
    }

    class ContextViewHolder{
        private TextView tv;
    }
}
