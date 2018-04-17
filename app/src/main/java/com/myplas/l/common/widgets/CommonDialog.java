package com.myplas.l.common.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.myplas.l.R;

import java.util.HashMap;

/**
 * @author Huangshuang  2018/3/14 0014
 */

public class CommonDialog implements View.OnClickListener
        , DialogInterface.OnDismissListener {

    public static final int OUTSIDE = 1, CONFIRM = 0, CANCEL = -1;
    private static final CommonDialog ourInstance = new CommonDialog();
    private int type;
    private int layout;
    private Context context;
    private DialogClickListener listener;
    private HashMap<Context, Dialog> dialogHashMap;
    private View view;
    private TextView tvTitle, tvMessage;
    private Button btnCancel, btnConfirm;

    private CommonDialog() {
        dialogHashMap = new HashMap<>();
    }

    /**
     * 初始化dialog
     *
     * @param builder
     */
    private CommonDialog initDialog(Builder builder) {
        type = builder.type;
        context = builder.context;
        listener = builder.listener;
        layout = builder.layoutRes == 0 ? R.layout.dialog_common : builder.layoutRes;

        view = builder.view == null ? View.inflate(context, layout, null) : builder.view;
        if (dialogHashMap.get(context) == null) {
            Dialog normalDialog = new Dialog(context, R.style.commonDialogStyle);
            normalDialog.setCancelable(builder.cancelable);
            normalDialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);
            normalDialog.setContentView(view);
            normalDialog.setOnDismissListener(this);
            setDialogWindowAttr(context, normalDialog, builder);

            tvTitle = view.findViewById(R.id.dialog_title);
            btnCancel = view.findViewById(R.id.btn_cancle);
            btnConfirm = view.findViewById(R.id.btn_confirm);
            tvMessage = view.findViewById(R.id.dialog_message);

            dialogHashMap.put(context, normalDialog);
        }
        return ourInstance;
    }

    /**
     * 设置dialog属性
     */
    private void setDialogWindowAttr(Context context, Dialog dialog, Builder builder) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Window window = dialog.getWindow();

        if (window != null && wm != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = outMetrics.heightPixels;
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.width = builder.autoWidth ? ViewGroup.LayoutParams.WRAP_CONTENT : (int) ((width * 2) / 3.0);
            lp.height = builder.autoHeight ? ViewGroup.LayoutParams.WRAP_CONTENT : (int) (height / 4.88);
            window.setAttributes(lp);
        }
    }

    /**
     * 初始化dialog标题与内容，设置监听
     *
     * @param builder
     * @return
     */
    private CommonDialog initView(Builder builder) {
        if (layout == 0) {
            setTitle(TextUtils.isEmpty(builder.title)
                    ? "晨运宝"
                    : builder.title);
            setMessage(builder.message);

            btnCancel.setOnClickListener(this);
            btnConfirm.setOnClickListener(this);
        }
        return ourInstance;
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /**
     * 设置显示内容
     *
     * @param message
     */
    public void setMessage(String message) {
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
    }

    public View getView() {
        return view;
    }

    @Override
    public void onClick(View v) {

        disMiss();

        if (listener != null) {
            int flag = v.getId() == R.id.btn_confirm
                    ? CONFIRM
                    : CANCEL;
            listener.dialogClick(flag, type);
        }
    }

    public void disMiss() {
        if (dialogHashMap.get(context) != null
                && dialogHashMap.get(context).isShowing()) {
            dialogHashMap.get(context).dismiss();
        }
    }

    public void show() {
        if (!dialogHashMap.get(context).isShowing()) {
            dialogHashMap.get(context).show();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        disMiss();
        if (listener != null) {
            listener.dialogClick(OUTSIDE, type);
        }
    }

    public interface DialogClickListener {
        /**
         * 点击回调
         *
         * @param flag 是点击确定（0）还是取消（-1）按钮 或者 点击空白（1）
         * @param type 调用者传的类型
         */
        void dialogClick(int flag, int type);
    }

    public static class Builder {
        private int type;
        private int layoutRes;
        private Context context;
        private String title, message;
        private boolean cancelable = true;
        private DialogClickListener listener;
        private boolean canceledOnTouchOutside = true;

        private boolean autoWidth, autoHeight = true;

        private View view;


        public Builder layout(int layout) {
            this.layoutRes = layout;
            return this;
        }

        public Builder view(View view) {
            this.view = view;
            return this;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public Builder listener(DialogClickListener listener) {
            this.listener = listener;
            return this;
        }

        public Builder autoWidth(boolean autoWidth) {
            this.autoWidth = autoWidth;
            return this;
        }

        public Builder autoHeight(boolean autoHeight) {
            this.autoHeight = autoHeight;
            return this;
        }

        public CommonDialog create(Context context) {
            this.context = context;
            return ourInstance.initDialog(this).initView(this);
        }
    }
}
