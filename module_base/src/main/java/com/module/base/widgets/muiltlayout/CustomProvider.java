package com.module.base.widgets.muiltlayout;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.module.base.R;

import java.util.List;

/**
 * @author Huangshuang  2018/3/27 0027
 */

public class CustomProvider implements BaseProvider {

    private Context context;

    private View divider;
    private EditText editText;
    private TextView tvLeft, tvRight;

    private SparseArray<TextView> tvArray;
    private SparseArray<View> viewArray;


    /**
     * 左边文字
     */
    private List<String> leftTexts;
    /**
     * 右边文字
     */
    private List<String> rightTexts;
    /**
     * 当能够输入的时候 的提示语
     */
    private List<String> hints;
    /**
     * 左边图片
     */
    private List<Integer> leftImgs;
    /**
     * 右边图片
     */
    private List<Integer> rightImgs;

    /**
     * 右边 的左边 的图片（有点绕  就是右边TextView的左边drawableLeft）
     */
    private List<Integer> right_LeftImgs;

    private boolean enableInput;

    private int rightTextColor;
    /**
     * 是否显示最后的listView分割线
     */
    private boolean lastDividerEnable;

    private MixedLayoutClickListener listener;

    public CustomProvider(Context context) {
        this.context = context;
        tvArray = new SparseArray<>();
        viewArray = new SparseArray<>();
    }


    @Override
    public View getView(final int position) {
        View view;
//        if (viewArray.get(position) == null) {
        view = View.inflate(context, R.layout.custom_mixed_layout, null);

        editText = view.findViewById(R.id.edit);
        tvLeft = view.findViewById(R.id.tv_left);
        divider = view.findViewById(R.id.divider);
        tvRight = view.findViewById(R.id.tv_right);

        tvArray.put(position, tvRight);
        viewArray.put(position, view);
//        } else {
//            view = viewArray.get(position);
//        }

        if (leftTexts != null) {
            tvLeft.setText(leftTexts.get(position));
        }

        if (leftImgs != null) {
            tvLeft.setCompoundDrawablesWithIntrinsicBounds(leftImgs.get(position), 0, 0, 0);
        }

        if (rightTexts != null) {
            tvRight.setText(rightTexts.get(position));
            if (rightTextColor != 0) {
                tvRight.setTextColor(rightTextColor);
            }
        }

        if (rightImgs != null && right_LeftImgs != null) {
            tvRight.setCompoundDrawablesWithIntrinsicBounds(right_LeftImgs.get(position), 0, rightImgs.get(position), 0);
        } else if (rightImgs != null) {
            tvRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightImgs.get(position), 0);
        } else if (right_LeftImgs != null) {
            tvRight.setCompoundDrawablesWithIntrinsicBounds(right_LeftImgs.get(position), 0, 0, 0);
        }

        if (enableInput) {
            editText.setVisibility(View.VISIBLE);
            editText.setHint(hints.get(position));
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (listener != null) {
                        listener.onTextChanged(position, s.toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        if (position == maxSize() - 1 && !lastDividerEnable) {
            divider.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public int size() {
        return maxSize();
    }


    private int maxSize() {
        if (leftTexts != null) {
            return leftTexts.size();
        } else if (hints != null) {
            return hints.size();
        }
        return 0;
    }


    public CustomProvider leftImgs(List<Integer> leftImgs) {
        this.leftImgs = leftImgs;
        return this;
    }

    public CustomProvider leftTexts(List<String> leftTexts) {
        this.leftTexts = leftTexts;
        return this;
    }

    public CustomProvider rightImgs(List<Integer> rightImgs) {
        this.rightImgs = rightImgs;
        return this;
    }

    public CustomProvider rightTexts(List<String> rightTexts) {
        this.rightTexts = rightTexts;
        return this;
    }

    public CustomProvider centerImgs(List<Integer> right_LeftImgs) {
        this.right_LeftImgs = right_LeftImgs;
        return this;
    }

    public CustomProvider hints(List<String> hints) {
        this.hints = hints;
        return this;
    }

    /**
     * 是否要显示输入框
     *
     * @param enableInput
     * @return
     */
    public CustomProvider enableInput(boolean enableInput) {
        this.enableInput = enableInput;
        return this;
    }

    public CustomProvider rightTextColor(int rightTextColor) {
        this.rightTextColor = ContextCompat.getColor(context, rightTextColor);
        return this;
    }

    public CustomProvider lastDividerEnable(boolean lastDividerEnable) {
        this.lastDividerEnable = lastDividerEnable;
        return this;
    }

    public CustomProvider setMixedLayoutClickListener(MixedLayoutClickListener listener) {
        this.listener = listener;
        return this;
    }
}
