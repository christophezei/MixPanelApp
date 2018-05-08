package com.example.christophe.mixpanelapp.util;

import android.content.Context;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONObject;

public class MixPanelHelper {
    private static String projectToken = "24e820d1efb56a4bf30b1390f8f124db";

    //MixpanelAPI contains context which can result in a memory leak on Android, if you highlight the "static" word in
    //this field declaration, it will tell you to avoid using static for classes with Context fields.

//    private static MixpanelAPI mixpanelAPI;

    //Instead we use a singleton for MixPanelHelper:
    private static MixPanelHelper mixPanelHelper;
    private MixpanelAPI mixpanelAPI;

    private MixPanelHelper(MixpanelAPI mixpanelAPI) {
        this.mixpanelAPI = mixpanelAPI;
    }

    public static MixPanelHelper getInstance(Context context) {
        if (mixPanelHelper == null) {
            mixPanelHelper = new MixPanelHelper(MixpanelAPI.getInstance(context, projectToken));
        }
        return mixPanelHelper;
    }

    public MixpanelAPI getMixpanelAPI() {
        return mixpanelAPI;
    }
}

