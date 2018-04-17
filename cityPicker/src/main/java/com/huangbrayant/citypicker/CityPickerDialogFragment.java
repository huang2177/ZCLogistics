package com.huangbrayant.citypicker;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huangbrayant.citypicker.adapter.CityListAdapter;
import com.huangbrayant.citypicker.adapter.InnerListener;
import com.huangbrayant.citypicker.adapter.OnPickListener;
import com.huangbrayant.citypicker.adapter.decoration.DividerItemDecoration;
import com.huangbrayant.citypicker.adapter.decoration.SectionItemDecoration;
import com.huangbrayant.citypicker.db.DBManager;
import com.huangbrayant.citypicker.model.City;
import com.huangbrayant.citypicker.model.HotCity;
import com.huangbrayant.citypicker.model.LocateState;
import com.huangbrayant.citypicker.model.LocatedCity;
import com.huangbrayant.citypicker.utils.StatusUtils;
import com.huangbrayant.citypicker.view.SideIndexBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangshuang
 * @Date: 2018/2/6 20:50
 */
public class CityPickerDialogFragment extends AppCompatDialogFragment implements TextWatcher,
        View.OnClickListener
        , SideIndexBar.OnIndexTouchedChangedListener
        , InnerListener {
    private final String HINT = "终点选择";
    private View mContentView;
    private RecyclerView mRecyclerView;
    private View mEmptyView;
    private TextView mOverlayTextView;
    private TextView tvTitle;
    private SideIndexBar mIndexBar;
    private EditText mSearchBox;
    private ImageView mCancelBtn;
    private LinearLayoutManager mLayoutManager;
    private CityListAdapter mAdapter;
    private List<City> mAllCities;
    private List<City> mHotCities;
    private List<City> mResults;
    private DBManager dbManager;
    private boolean enableAnim = false;
    private int mAnimStyle = R.style.DefaultCityPickerAnimation;
    private List<City> mLocatedCity;
    private int locateState;
    private OnPickListener mOnPickListener;
    private String title;

    /**
     * 获取实例
     *
     * @param enable 是否启用动画效果
     * @return
     */
    public static CityPickerDialogFragment newInstance(boolean enable) {
        final CityPickerDialogFragment fragment = new CityPickerDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean("cp_enable_anim", enable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.CityPickerStyle);

        Bundle args = getArguments();
        if (args != null) {
            enableAnim = args.getBoolean("cp_enable_anim");
        }

        initHotCities();
        initLocatedCity();

        dbManager = new DBManager(getContext());
        mAllCities = dbManager.getAllCities();
        mAllCities.add(0, new LocatedCity("常用城市", "未知", "0"));
        mAllCities.add(1, new HotCity("热门城市", "未知", "0"));
        mResults = mAllCities;
    }

    private void initHotCities() {
        if (mHotCities == null || mHotCities.isEmpty()) {
            mHotCities = new ArrayList<>();
            mHotCities.add(new HotCity("北京", "北京", "101010100"));
            mHotCities.add(new HotCity("上海", "上海", "101020100"));
            mHotCities.add(new HotCity("广州", "广东", "101280101"));
            mHotCities.add(new HotCity("深圳", "广东", "101280601"));
            mHotCities.add(new HotCity("天津", "天津", "101030100"));
            mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
        }
    }

    private void initLocatedCity() {
        if (mLocatedCity == null || mLocatedCity.isEmpty()) {
            mLocatedCity = new ArrayList<>();
            mLocatedCity.add(new LocatedCity("成都", "四川", "101010100"));
            mLocatedCity.add(new LocatedCity("上海", "上海", "101020100"));
            mLocatedCity.add(new LocatedCity("广州", "广东", "101280101"));
            mLocatedCity.add(new LocatedCity("深圳", "广东", "101280601"));
            mLocatedCity.add(new LocatedCity("天津", "天津", "101030100"));
            mLocatedCity.add(new LocatedCity("杭州", "浙江", "101210101"));
        }
    }

    public void setLocatedCity(List<City> location) {
        mLocatedCity = location;
    }

    public void setHotCities(List<City> data) {
        if (data != null && !data.isEmpty()) {
            this.mHotCities = data;
        }
    }

    public void setAnimationStyle(@StyleRes int style) {
        this.mAnimStyle = style <= 0 ? R.style.DefaultCityPickerAnimation : style;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.cp_dialog_city_picker, container, false);

        mRecyclerView = mContentView.findViewById(R.id.cp_city_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SectionItemDecoration(getActivity(), mAllCities), 0);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()), 1);
        mAdapter = new CityListAdapter(getActivity(), mAllCities, mHotCities, mLocatedCity, locateState);
        mAdapter.setInnerListener(this);
        mRecyclerView.setAdapter(mAdapter);

        mEmptyView = mContentView.findViewById(R.id.cp_empty_view);
        mOverlayTextView = mContentView.findViewById(R.id.cp_overlay);

        mIndexBar = mContentView.findViewById(R.id.cp_side_index_bar);
        mIndexBar.setOverlayTextView(mOverlayTextView)
                .setOnIndexChangedListener(this);

        mSearchBox = mContentView.findViewById(R.id.cp_search_box);
        mSearchBox.addTextChangedListener(this);

        if (HINT.equals(title)) {
            mSearchBox.setHint(getResources().getString(R.string.cp_search_hint_text1));
        }

        mCancelBtn = mContentView.findViewById(R.id.cp_cancel);
        mCancelBtn.setOnClickListener(this);

        tvTitle = mContentView.findViewById(R.id.titleBar_title);
        tvTitle.setText(title);

        return mContentView;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);

            StatusUtils.setStatusBar(window, false, false);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            if (enableAnim) {
                window.setWindowAnimations(mAnimStyle);
            }
        }
        return dialog;
    }


    /**
     * 搜索框监听
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        if (TextUtils.isEmpty(keyword)) {
            mEmptyView.setVisibility(View.GONE);
            mResults = mAllCities;
            ((SectionItemDecoration) (mRecyclerView.getItemDecorationAt(0))).setData(mResults);
            mAdapter.updateData(mResults);
        } else {
            //开始数据库查找
            mResults = dbManager.searchCity(keyword);
            ((SectionItemDecoration) (mRecyclerView.getItemDecorationAt(0))).setData(mResults);
            if (mResults == null || mResults.isEmpty()) {
                mEmptyView.setVisibility(View.VISIBLE);
            } else {
                mEmptyView.setVisibility(View.GONE);
                mAdapter.updateData(mResults);
            }
        }
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cp_cancel) {
            dismiss(-1, null);
        }
    }

    @Override
    public void dismiss(int position, City data) {
        dismiss();

        if (mOnPickListener == null || data == null) {
            return;
        }
        if (TextUtils.equals(title, "起点选择")) {
            if (TextUtils.equals(data.getName(), CitySave.getInstance().endCity)) {
                Toast.makeText(getContext(), "起点不能和终点一样", Toast.LENGTH_SHORT).show();
                return;
            }
            CitySave.getInstance().startCity = data.getName();
        } else {
            if (TextUtils.equals(data.getName(), CitySave.getInstance().startCity)) {
                Toast.makeText(getContext(), "终点不能和起点一样", Toast.LENGTH_SHORT).show();
                return;
            }
            CitySave.getInstance().endCity = data.getName();
        }
        mOnPickListener.onPick(position, data);

    }

    @Override
    public void locate() {
        if (mOnPickListener != null) {
            mOnPickListener.onLocate();
        }
    }

    @Override
    public void onIndexChanged(String index, int position) {
        //滚动RecyclerView到索引位置
        if (mResults == null || mResults.isEmpty()) {
            return;
        }
        if (TextUtils.isEmpty(index)) {
            return;
        }
        int size = mResults.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(index.substring(0, 1), mResults.get(i).getSection().substring(0, 1))) {
                if (mLayoutManager != null) {
                    mLayoutManager.scrollToPositionWithOffset(i, 0);
                    return;
                }
            }
        }
    }

    public void locationChanged(LocatedCity location, int state) {
        mAdapter.updateLocateState(location, state);
    }

    public void setOnPickListener(OnPickListener listener) {
        this.mOnPickListener = listener;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
