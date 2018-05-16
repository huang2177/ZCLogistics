package com.module.mine.waybill;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.module.base.widgets.MyBottomDialog;
import com.module.base.widgets.ProgressImageView;
import com.module.mine.R;
import com.yanzhenjie.album.Album;

/**
 * @author Huangshuang  2018/3/22 0022
 */

public class CertificateDialog extends MyBottomDialog {

    private Button btnCommit;
    private TextView ivClose;
    private ImageView ivUpload;
    private ProgressImageView ivCertificate;

    private WayBillDetailActivity activity;
    private int blue;

    public CertificateDialog(@NonNull Activity activity) {
        super(activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_certificate;
    }

    @Override
    protected void initView() {
        activity = (WayBillDetailActivity) super.activity;

        ivClose = findViewById(R.id.dialog_certificate_close);
        ivUpload = findViewById(R.id.dialog_certificate_upload);
        btnCommit = findViewById(R.id.dialog_certificate_commit);
        ivCertificate = findViewById(R.id.dialog_certificate_img);

        blue = ContextCompat.getColor(activity, R.color.mainColor);
    }

    @Override
    protected void setListener() {
        ivClose.setOnClickListener(this);
        ivUpload.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.dialog_certificate_close) {
            dismiss();

        } else if (i == R.id.dialog_certificate_upload) {
            pickPhoto(activity.REQUESTCODE);

        } else if (i == R.id.dialog_certificate_commit) {
            activity.upload();

        } else {
        }
    }

    /**
     * 选取图片
     */
    private void pickPhoto(int code) {
        Album.albumRadio(activity)
                .toolBarColor(blue)
                .statusBarColor(blue)
                .title("上传凭证")
                .columnCount(3)
                .camera(true)
                .start(code);
    }

    /**
     * 选择照片回调
     */
    public void onPickImageResult(String path) {
        if (TextUtils.isEmpty(path)) {
            btnCommit.setBackgroundResource(R.drawable.shape_login_blue_alpha);
            return;
        }
        ivCertificate.setUseProgress(true);
        Glide.with(activity).load(path).into(ivCertificate);
        btnCommit.setBackgroundResource(R.drawable.shape_login_blue);
    }
}
