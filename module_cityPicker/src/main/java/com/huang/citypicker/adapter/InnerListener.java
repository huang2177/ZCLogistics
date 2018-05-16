package com.huang.citypicker.adapter;

import com.huang.citypicker.model.City;

public interface InnerListener {
    void dismiss(int position, City data);
    void locate();
}
