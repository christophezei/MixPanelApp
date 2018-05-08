package com.example.christophe.mixpanelapp.base;


import android.content.Context;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;
import org.json.JSONObject;

public class AnalyticsView implements ViewAnalytics {

    private Context context;
    public AnalyticsView(Context context) {
        this.context=context;
    }

    @Override
    public void getView1() {
        JSONObject props = new JSONObject();
        MixPanelHelper.getInstance(context).track(App_View_1,props);
    }

    @Override
    public void getView2() {
        JSONObject props = new JSONObject();
        MixPanelHelper.getInstance(context).track(App_View_2,props);

    }

    @Override
    public void getView3() {
        JSONObject props = new JSONObject();
        MixPanelHelper.getInstance(context).track(App_View_3,props);

    }

    @Override
    public void getView4() {
        JSONObject props = new JSONObject();
        MixPanelHelper.getInstance(context).track(App_View_4,props);

    }
}
