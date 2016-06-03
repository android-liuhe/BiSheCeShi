package com.example.liuhe.bisheceshi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.liuhe.bisheceshi.R;

/**
 * Created by liuhe on 16/5/25.
 */
public class Main_TitleView extends FrameLayout {

    private TextView tv_left;
    private TextView tv_title;

    public Main_TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.main_title, this);

        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_title = (TextView) findViewById(R.id.tv_title);

    }

    public void setTitleText(String text ){
        tv_title.setText(text);
    }

    public void setLeftTextView(OnClickListener listener){
        tv_left.setOnClickListener(listener);
    }

}
