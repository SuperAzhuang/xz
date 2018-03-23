package com.xiaozhao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaozhao.R;
import com.xiaozhao.activity.MainActivity;

import static com.xiaozhao.R.id.tv;

/**
 * Created by Administrator on 2018/3/23.
 */
public class CompanyInfoDetailAdapter extends BaseAdapter {

    private Context context;
    private String[] strings;
    public static int mPosition;

    public CompanyInfoDetailAdapter(Context context, String[] strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        convertView = LayoutInflater.from(context).inflate(R.layout.company_info_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv);
        TextView tvSlary = (TextView) convertView.findViewById(R.id.tvSlary);


        mPosition = position;
        tv.setText(strings[position]);
        if (position == 0) {
            tvSlary.setVisibility(View.GONE);
            //这里的Textview的父layout是ListView，所以要用ListView.LayoutParams
        } else {
            tvSlary.setText("4500-6000");
        }


//        if (position == MainActivity.mPosition) {
//            convertView.setBackgroundResource(R.drawable.tongcheng_all_bg01);
//        } else {
//            convertView.setBackgroundColor(Color.parseColor("#f4f4f4"));
//        }
        return convertView;
    }
}