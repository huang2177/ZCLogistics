package com.huangbrayant.citypicker.adapter;

import com.huangbrayant.citypicker.model.City;

public interface InnerListener {
    void dismiss(int position, City data);
    void locate();
}
