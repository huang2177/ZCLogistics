package com.huang.citypicker.adapter;

import com.huang.citypicker.model.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onLocate();
}
