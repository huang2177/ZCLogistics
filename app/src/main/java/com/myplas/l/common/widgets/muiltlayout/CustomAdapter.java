package com.myplas.l.common.widgets.muiltlayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Arrays;

/**
 * @author Huangshuang  2018/3/27 0027
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private BaseProvider provider;

    public CustomAdapter(BaseProvider provider) {
        this.provider = provider;
    }

    @Override
    public int getCount() {
        return provider.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return provider.getView(position);
    }
}
