package com.xiaozhao.im.model;


import com.xiaozhao.im.ConstantValues;
import com.xiaozhao.im.SpUtil;

import java.util.ArrayList;

/**
 * Created by gabriel on 2017/3/3.
 */

public class FriendListPost {
    public String From_Account = SpUtil.getString(ConstantValues.LOGIN_IDENTIFIER, "");
    public int TimeStamp;
    public int StartIndex = 0;
    public int LastStandardSequence;
    public int GetCount;
    public ArrayList<String> TagList;




//    Tag_SNS_IM_Group;
//    Tag_SNS_IM_Remark;
//    Tag_Profile_IM_Nick;


}
