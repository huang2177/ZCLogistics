package com.huangbrayant.citypicker.adapter;

import com.huangbrayant.citypicker.model.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onLocate();
}
