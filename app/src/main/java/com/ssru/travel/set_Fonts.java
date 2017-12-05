package com.ssru.travel;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class set_Fonts extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/charm.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
