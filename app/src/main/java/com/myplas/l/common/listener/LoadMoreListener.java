package com.myplas.l.common.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

public class LoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "LoadMoreListener";
    private Listener mListener;
    private boolean enableLoadMore = true;
    private boolean isLoading = false;

    public LoadMoreListener(Listener listener) {
        mListener = listener;
    }

    public void setEnableLoadMore(boolean enable) {
        enableLoadMore = enable;
    }

    public void onLoadComplete() {
        Log.i(TAG, "onLoadComplete");
        isLoading = false;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        //mListener.onScrolled(dy < 0);

        if (!enableLoadMore || isLoading) {
            return;
        }

        if (!recyclerView.canScrollVertically(1)) {
            Log.i(TAG, "onLoadMore");
            isLoading = true;
            mListener.onLoadMore();
        }
    }


    public interface Listener {
        void onLoadMore();

        //void onScrolled(boolean scrollUp);
    }
}
