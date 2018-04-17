package com.myplas.l.common.widgets.muiltlayout;

import android.content.Context;
import android.view.View;

/**
 * 提供布局
 *
 * @author Huangshuang  2018/3/27 0027
 */

public interface BaseProvider {
    View getView(int position);

    int size();
}
