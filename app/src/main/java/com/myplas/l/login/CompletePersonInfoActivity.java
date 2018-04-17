package com.myplas.l.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.huangbrayant.citypicker.utils.StatusUtils;
import com.myplas.l.R;
import com.myplas.l.base.BaseActivity;
import com.myplas.l.base.BasePresenter;
import com.myplas.l.common.utils.KeyboardHelper;
import com.myplas.l.common.utils.SPUtil;
import com.myplas.l.common.widgets.MyEditText;
import com.myplas.l.common.widgets.ProgressImageView;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄双
 * @date 2017/12/20 0020
 */

public class CompletePersonInfoActivity extends BaseActivity implements MyEditText.OnTextWatcher {
    private Button button;
    private LinearLayout root;
    private ScrollView scrollView;
    private FrameLayout flLicence, flHead;
    private MyEditText editName, editNumber;
    private ProgressImageView ivLicence, ivHead;

    private int color;
    private SPUtil sharedUtils;
    private final int HCODE = 10, LCODE = 20;
    private String name, number, headPath, licencePath, stauts;
    //private UCloudUtils uCloudUtils;
    private String userId;

    private KeyboardHelper mKeyboardHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusUtils.setStatusBar(this, false, false);
        StatusUtils.setStatusTextColor(true, this);

        setContentView(R.layout.activity_complete_personinfo);

        titleBarBackground(R.color.defaultWhite)
                .leftImageRes(R.drawable.ic_arrow_left_black)
                .title("注 册")
                .titleColor(R.color.black);
    }

    @Override
    public void initView() {
        root = findViewById(R.id.root);
        button = findViewById(R.id.btn_next);
        flHead = findViewById(R.id.fl_licence);
        flLicence = findViewById(R.id.fl_head);
        scrollView = findViewById(R.id.store_scrollview);
        ivHead = findViewById(R.id.iv_head);
        ivLicence = findViewById(R.id.iv_license);
        editName = findViewById(R.id.edit_name);
        editNumber = findViewById(R.id.edit_number);

        sharedUtils = SPUtil.getInstance(this);
//        userId = sharedUtils.getData(this, Constant.USERID);


//        uCloudUtils = new UCloudUtils(this, this);

        color = ContextCompat.getColor(this, R.color.mainColor);

        mKeyboardHelper = new KeyboardHelper(this, root);
        mKeyboardHelper.enable();
    }

    @Override
    public void setListener() {
        super.setListener();
        button.setOnClickListener(this);
        flHead.setOnClickListener(this);
        flLicence.setOnClickListener(this);

        editName.addOnTextWatcher(this);
        editNumber.addOnTextWatcher(this);
    }


    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.fl_head:
                pickPhoto("上传照片", HCODE);
                break;
            case R.id.fl_licence:
                pickPhoto("上传照片", LCODE);
                break;
            case R.id.btn_next:
//                commit();
//                button.setClickable(false);
//                button.setBackgroundResource(R.drawable.shape_login_blue_alpha);
                startActivity(new Intent(this, CompleteCarInfoActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 选取图片
     */
    private void pickPhoto(String title, int code) {
        Album.albumRadio(this)
                .toolBarColor(color)
                .statusBarColor(color)
                .title(title)

                .columnCount(3)
                .camera(true)
                .start(code);
    }

    /**
     * 裁剪
     *
     * @param pathList
     * @param code
     */
    private void cutPhoto(ArrayList<String> pathList, int code) {
        Durban.with(this)
                // 裁剪界面的标题。
                .title("裁剪")
                .statusBarColor(color)
                .toolBarColor(color)
                // 图片路径list或者数组。
                .inputImagePaths(pathList)
                // 图片输出文件夹路径。
                // 裁剪图片输出的最大宽高。
                .maxWidthHeight(code == 100 ? 288 : 450, code == 100 ? 288 : 300)
                // 设置裁剪比例
                .aspectRatio(code == 100 ? 1 : 3, code == 100 ? 1 : 2)
                // 图片压缩格式：JPEG、PNG。
                .compressFormat(Durban.COMPRESS_PNG)
                // 图片压缩质量，请参考：Bitmap#compress(Bitmap.CompressFormat, int, OutputStream)
                .compressQuality(100)
                // 裁剪时的手势支持：ROTATE, SCALE, ALL, NONE.
                .gesture(Durban.GESTURE_SCALE)
                .controller(Controller.newBuilder()
                        // 是否开启控制面板。
                        .enable(true)
                        // 是否有旋转按钮。
                        .rotation(true)
                        // 旋转控制按钮上面的标题。
                        .rotationTitle(true)
                        // 是否有缩放按钮。
                        .scale(true)
                        // 缩放控制按钮上面的标题。
                        .scaleTitle(true)
                        // 创建控制面板配置。
                        .build())
                .requestCode(code)
                .start();
    }

    /**
     * 图片上传后回调服务器
     */
    private void uploadNotify(String path, String type) {
        Map<String, String> map = new HashMap(16);
        map.put("type", type);
        map.put("path", path);
//        postAsyn(this, API.UPLOADNOTIFY, map, this, 1, false);
    }

    /**
     * 提交
     */
    private void commit() {
//        if (isWriteInfo()) {
//            Map<String, String> map = new HashMap(16);
//            map.put("company", companyName);
//            map.put("avatar_url", headPath);
//            map.put("business_license", business);
//            map.put("business_license_url", licencePath);
//            map.put("company_description", companyIntroduction);
//            postAsyn(this, API.SHOPS, map, this, 2, false);
//        } else {
//            TextUtils.toast(this, "请先填写完整资料！");
//        }
    }

    /**
     * 获取数据
     */
    public void getNetData() {
//        Map<String, String> map = new HashMap(16);
//        map.put("user_id", userId);
//        getAsyn(this, API.GET_ZONE_FRIEND, map, this, 3, false);
    }

    /**
     * 改变button颜色
     */
    private void changeBtnColor() {
        if (isWriteInfo()) {
            button.setBackgroundResource(R.drawable.shape_login_blue);
        } else {
            button.setBackgroundResource(R.drawable.shape_login_blue_alpha);
        }
    }

    /**
     * 判断是否已经填写数据
     *
     * @return
     */
    private boolean isWriteInfo() {
        name = editName.getText().toString();
        number = editNumber.getText().toString();

        return !TextUtils.isEmpty(name)
                && !TextUtils.isEmpty(headPath)
                && !TextUtils.isEmpty(licencePath)
                && !TextUtils.isEmpty(number);
    }

    @Override
    public void onTextChanged(View v, String s) {
        changeBtnColor();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
//            if (requestCode == LCODE) {
//                ArrayList<String> mList = Album.parseResult(data);
//                if (mList.size() == 0) {
//                    return;
//                }
//                ivLicence.setUseProgress(true);
//                Glide.with(this).load(mList.get(0)).into(ivLicence);
//                // uCloudUtils.putFile(new File(mList.get(0)), 1);
//                changeBtnColor();
//            } else if (requestCode == HCODE) {
//                cutPhoto(Album.parseResult(data), HCODE * 10);
//            } else {
//                ArrayList<String> mList = Durban.parseResult(data);
//                if (mList.size() == 0) {
//                    return;
//                }
//                ivHead.setUseProgress(true);
//
//                Glide.with(this).load(mList.get(0)).into(ivHead);
//                //uCloudUtils.putFile(new File(mList.get(0)), 2);
//
//                changeBtnColor();
//            }
            if (requestCode == LCODE) {
                cutPhoto(Album.parseResult(data), LCODE * 10);
            } else if (requestCode == HCODE) {
                cutPhoto(Album.parseResult(data), HCODE * 10);
            } else {
                if (requestCode == HCODE * 10) {
                    ArrayList<String> mList = Durban.parseResult(data);
                    if (mList.size() == 0) {
                        return;
                    }
                    ivHead.setUseProgress(true);

                    Glide.with(this).load(mList.get(0)).into(ivHead);
                } else {
                    ArrayList<String> mList = Durban.parseResult(data);
                    if (mList.size() == 0) {
                        return;
                    }
                    ivLicence.setUseProgress(true);
                    Glide.with(this).load(mList.get(0)).into(ivLicence);
                }
                changeBtnColor();
            }
        } catch (Exception e) {

        }
    }


//    @Override
//    public void uCloudSucess(int type, String flieName) {
//        if (type == 1) {
//            licencePath = flieName;
//            uploadNotify(flieName, "license");
//        } else {
//            headPath = flieName;
//            uploadNotify(flieName, "thumb");
//        }
//    }
//
//    @Override
//    public void uCloudProcess(int type, int value) {
//        if (type == 1) {
//            ivLicence.setProgress(value);
//        } else {
//            ivHead.setProgress(value);
//        }
//    }
//
//    @Override
//    public void uCloudFail(int type) {
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mKeyboardHelper.disable();
    }
}
