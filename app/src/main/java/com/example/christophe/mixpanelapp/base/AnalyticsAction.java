package com.example.christophe.mixpanelapp.base;

import android.content.Context;
import android.widget.EditText;

import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;

import org.json.JSONObject;

import butterknife.BindView;


public class AnalyticsAction implements ActionAnalytics {
    //You were binding a view in an interface
//
//    @BindView(R.id.editTextEmail)
//    EditText email;


    private Context context;

    public AnalyticsAction(Context context) {
        this.context = context;
    }

    @Override
    public void actionDone() {
        JSONObject props = new JSONObject();
        MixPanelHelper.getInstance(context).getMixpanelAPI().track(Action_Done, props);
    }
}
