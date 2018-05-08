package com.example.christophe.mixpanelapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.base.AnalyticsAction;
import com.example.christophe.mixpanelapp.base.AnalyticsView;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Tab4Fragment extends Fragment {

    private ActionListener actionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.actionListener = (ActionListener) context;
    }

    public interface ActionListener {
        void onActionDone();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tab4, container, false);
        ButterKnife.bind(this,view);
        AnalyticsView view4=new AnalyticsView(getContext());
        view4.getView4();
        return view;
    }
    @BindView(R.id.editTextEmail) EditText email;


    @OnClick(R.id.buttonDone)
    void submit() {
        this.actionListener.onActionDone();
        if(email!=null){
            MixPanelHelper.getInstance(getContext()).alias(email.getText().toString(),"0");
        }
        MixPanelHelper.getInstance(getContext()).identify(email.getText().toString());
        AnalyticsAction actionDone=new AnalyticsAction(getContext());
        actionDone.actionDone();
    }

}
