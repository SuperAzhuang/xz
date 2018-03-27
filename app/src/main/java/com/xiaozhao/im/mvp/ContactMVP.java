package com.xiaozhao.im.mvp;


import com.xiaozhao.im.adapter.ContactAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gabriel on 2017/3/3.
 */

public interface ContactMVP {
    interface View {

        void updateRecyclerView(List<HashMap<String, String>> friendList);
    }

    interface Model {
        void getFriendList();
    }

    interface Presenter {

        void updateFriendList(ContactAdapter adapter);
    }
}
