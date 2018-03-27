package com.xiaozhao.im.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.xiaozhao.R;
import com.xiaozhao.base.BaseApplication;
import com.xiaozhao.im.view.CircleImageView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gabriel on 2017/2/28.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<HashMap<String, String>> mFriendList;

    private Activity mActivity;

    public ContactAdapter(Activity activity, List<HashMap<String, String>> friendList) {
        mFriendList = friendList;
        mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.
                from(BaseApplication.getContext()).inflate(R.layout.item_friend_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HashMap<String, String> hashMap = mFriendList.get(position);
        if (TextUtils.isEmpty(hashMap.get("name"))) {
            holder.name.setText(hashMap.get("id"));
        } else {
            holder.name.setText(hashMap.get("name"));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mActivity, ChatActivity.class);
//                    HashMap<String, String> hashMap = mFriendList.get(getAdapterPosition());
//                    intent.putExtra(ConstantValues.FRIEND_IDENTIFIER, hashMap.get("id"));
//                    intent.putExtra(ConstantValues.FRIEND_NAME, hashMap.get("name"));
//                    mActivity.startActivity(intent);
                }
            });
        }
    }

}
