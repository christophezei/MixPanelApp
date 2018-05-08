package com.example.christophe.mixpanelapp.util;

import android.content.Context;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class MixPanelHelper {
    private static String projectToken = "24e820d1efb56a4bf30b1390f8f124db";
    private static MixpanelAPI mixpanelAPI;

    public static MixpanelAPI getInstance(Context context) {
        if (mixpanelAPI == null) {
            mixpanelAPI = MixpanelAPI.getInstance(context, projectToken);
        }
        return mixpanelAPI;
    }

}

