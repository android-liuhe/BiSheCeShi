package com.example.liuhe.bisheceshi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liuhe.bisheceshi.R;
import com.example.liuhe.bisheceshi.model.NewsListItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by liuhe on 16/5/23.
 */
public class MenuAdapter extends BaseAdapter {

    private Context context;
    private List<NewsListItem> items;
    private LayoutInflater inflater;

    public MenuAdapter(Context context, List<NewsListItem> items) {
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

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null){

            convertView = inflater.inflate(R.layout.menu_item, parent, false);
            viewHolder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_item.setText(items.get(position).getTitle());

        return convertView;
    }

    class ViewHolder{
        private TextView tv_item;
    }
}
