package com.huang.citypicker;

/**
 * @author Huangshuang  2018/4/3 0003
 */

public class CitySave {
    public String startCity, endCity;
    private static final CitySave ourInstance = new CitySave();

    public static CitySave getInstance() {
        return ourInstance;
    }

    private CitySave() {
        endCity = "全国";
        startCity = "上海";
    }
}
