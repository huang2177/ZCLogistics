package com.myplas.l.common.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.utils.ScreenUtils;
import com.myplas.l.source.PopupWindowAdapter;

import java.util.List;

/**
 * @author Huangshuang  2018/3/23 0023
 */

public class XPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener
        , View.OnClickListener
        , AdapterView.OnItemClickListener {

    private View mView;
    private TextView tvTitle;
    private View dissmissView;
    private GridView gridView;

    private int type;
    private OnItemClickListener listener;

    private List<String> list;
    private Context context;
    private PopupWindowAdapter adapter;
    private String title;

    public XPopupWindow(View contentView, Context context) {
        this(contentView
                , ScreenUtils.getScreenWidth(context)
                , ScreenUtils.getScreenHeight(context));
        this.context = context;
    }

    public XPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        this.mView = contentView;
        setFocusable(true);
        setOutsideTouchable(true);
        setOnDismissListener(this);
    }

    @Override
    public void showAsDropDown(View anchor) {
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        initView();
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    private void initView() {
        tvTitle = mView.findViewById(R.id.dialog_title);
        gridView = mView.findViewById(R.id.dialog_grid);
        dissmissView = mView.findViewById(R.id.dialog_dismiss);

        dissmissView.setOnClickListener(this);
        gridView.setOnItemClickListener(this);

        if (adapter == null) {
            adapter = new PopupWindowAdapter(context, list);
        }

        tvTitle.setText(title);
        gridView.setAdapter(adapter);

    }


    @Override
    public void onDismiss() {
        if (listener != null) {
            listener.onPopItemClick(type, -1);
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            dismiss();
            adapter.selectItem(position, true);
            listener.onPopItemClick(type, position);
        }
    }

    public XPopupWindow title(String title) {
        this.title = title;
        return this;
    }

    public XPopupWindow setType(int type) {
        this.type = type;
        return this;
    }

    public XPopupWindow setList(List<String> list) {
        this.list = list;
        return this;
    }

    public XPopupWindow setListener(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnItemClickListener {
        void onPopItemClick(int type, int position);
    }
}

