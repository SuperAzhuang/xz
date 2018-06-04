package com.xiaozhao.manager;

import android.content.Context;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

/**
 * This class is from the v7 samples of the Android SDK. It's not by me!
 * <p/>
 * See the license above for details.
 */
public class DividerApplerItemDecoration extends Y_DividerItemDecoration {

    public DividerApplerItemDecoration(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;

            switch (itemPosition % 3) {
                case 0:
                    //第二个显示Left和bottom
//                    divider = new Y_DividerBuilder()
//                            .setRightSideLine(true, 0xff666666, 10, 0, 0)
//                            .setBottomSideLine(true, 0xff666666, 20, 0, 0)
//                            .create();
                    divider = new Y_DividerBuilder()
                            .setRightSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setLeftSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setBottomSideLine(true, 0xA3A3A3, 4, 0, 0)
                            .create();

                    break;
                case 1:

//                    每一行第一个显示rignt和bottom
//                    divider = new Y_DividerBuilder()
//                            .setLeftSideLine(true, 0xff666666, 10, 0, 0)
//                            .setBottomSideLine(true, 0xff666666, 20, 0, 0)
//                            .create();
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setRightSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setBottomSideLine(true, 0xA3A3A3, 4, 0, 0)
                            .create();

                    break;
                case 2:

//                    每一行第一个显示rignt和bottom
//                    divider = new Y_DividerBuilder()
//                            .setLeftSideLine(true, 0xff666666, 10, 0, 0)
//                            .setBottomSideLine(true, 0xff666666, 20, 0, 0)
//                            .create();
                    divider = new Y_DividerBuilder()
                            .setLeftSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setRightSideLine(true, 0xA3A3A3, 2, 0, 0)
                            .setBottomSideLine(true, 0xA3A3A3, 4, 0, 0)
                            .create();

                    break;
                default:
                    break;
            }
        return divider;
    }
}
