package com.xiaozhao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiaozhao.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TagAdapter extends BaseAdapter {

    private ArrayList<String> mDatas;
    private Context mContext;

    public TagAdapter(Context context, ArrayList<String> mDatas) {
        this.mDatas = mDatas;
        mContext = context;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pop_gridview, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvName.setText(mDatas.get(position));
//        vh.ivv.setText(StringUtils.getNormalTime(Long.parseLong(mNewBean.getPubdate())*1000));

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
