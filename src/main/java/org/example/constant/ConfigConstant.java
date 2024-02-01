package org.example.constant;

import org.example.utils.R;

public class ConfigConstant {

    public static final int PAGE_LOAD_TO =  R.CONFIG.getInt("page_load_timeout");
    public static final int PAGE_ELEMENT_TO =  R.CONFIG.getInt("element_timeout");
    public static final boolean HEADLESS =  R.CONFIG.getBoolean("headless");
    public static final String SELENIUM_URL = R.CONFIG.getString("selenium_url");


}
