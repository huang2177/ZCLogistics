package com.myplas.l.base;

/**
 * @author Huangshuang
 *         2018/3/8 0008
 */

public abstract class BasePresenter<V> {
    public abstract void attachView(V v);

    public abstract void fetch();
}
