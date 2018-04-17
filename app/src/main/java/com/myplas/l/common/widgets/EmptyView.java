package com.myplas.l.common.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myplas.l.R;
import com.myplas.l.common.utils.ScreenUtils;


/**
 * 作者：  黄双
 * 事件 2017/9/12 0012.
 * 邮箱： 15378412400@163.com
 */

public class EmptyView extends LinearLayout {
    private TextView tvEmptyText;
    private ImageView ivEmptyImage;
    private LinearLayout root;

    public EmptyView(Context context) {
        super(context);
        init();
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.empty_layout, null);
        tvEmptyText = view.findViewById(R.id.empty_text);
        ivEmptyImage = view.findViewById(R.id.empty_image);
        root = view.findViewById(R.id.empty_root);
        addView(view);
    }

    /**
     * 一定要调用该方法
     *
     * @param
     */
    public View mustCallInitWay(View view) {
        if (view != null) {
            ViewGroup.LayoutParams params = root.getLayoutParams();
            params.width = ScreenUtils.getScreenWidth(getContext());
            params.height = ScreenUtils.getScreenHeight(getContext()) - ScreenUtils.dp2px(getContext(), 50);
            ((ViewGroup) view.getParent()).addView(this, params);
        }
        return this;
    }

    public void setEmptyText(CharSequence text) {
        tvEmptyText.setText(text);
        tvEmptyText.setVisibility(View.VISIBLE);
    }


    /********修改文字的颜色**********/
    public void setEmptyTextColor(int colorResId) {
        tvEmptyText.setTextColor(colorResId);
        tvEmptyText.setVisibility(View.VISIBLE);
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setEmptyImage(String showNoTip, int emptyImageResId, int textSize, int colorResId) {
        ivEmptyImage.setImageResource(emptyImageResId);
        tvEmptyText.setTextColor(colorResId);
        tvEmptyText.setText(showNoTip);
        tvEmptyText.setTextSize(textSize);
        tvEmptyText.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setEmptyImage(String showNoTip, int emptyImageResId) {
        ivEmptyImage.setImageResource(emptyImageResId);
        tvEmptyText.setText(showNoTip);
        tvEmptyText.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示及其图片提示
     */
    public View setEmptyImage(int emptyImageResId) {
        ivEmptyImage.setImageResource(emptyImageResId);
        ivEmptyImage.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 显示不同的文字提示
     */
    public View setEmptyText(String showNoTip) {
        tvEmptyText.setText(showNoTip);
        tvEmptyText.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow) {
        if (isShow) {
            tvEmptyText.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * 是否显示文字
     */
    public View isShowTextTipMassager(boolean isShow, String showNoTip) {
        if (isShow) {
            tvEmptyText.setVisibility(View.VISIBLE);
            tvEmptyText.setText(showNoTip);
        } else {
            tvEmptyText.setVisibility(View.GONE);
            tvEmptyText.setText(showNoTip);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow, int showNoIamgeViewResId) {
        if (isShow) {
            ivEmptyImage.setVisibility(View.VISIBLE);
            ivEmptyImage.setImageResource(showNoIamgeViewResId);
        } else {
            ivEmptyImage.setVisibility(View.GONE);
        }
        return this;
    }

    /**
     * 是否显示图片
     */
    public View isShowIamgeMassager(boolean isShow) {
        if (isShow) {
            ivEmptyImage.setVisibility(View.VISIBLE);
        } else {
            ivEmptyImage.setVisibility(View.GONE);
        }
        return this;
    }
}
