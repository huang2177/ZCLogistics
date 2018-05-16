package com.huang.citypicker.model;

public class LocatedCity extends City {

    public LocatedCity(String name, String province, String code) {
        super(name, province, "常用城市", code);
    }
}
