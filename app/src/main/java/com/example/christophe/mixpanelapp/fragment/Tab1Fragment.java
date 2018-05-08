package com.example.christophe.mixpanelapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.base.AnalyticsView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Tab1Fragment extends Fragment {
    private ActionListener actionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.actionListener = (ActionListener) context;
    }

    public interface ActionListener {
        void onActionNext();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        ButterKnife.bind(this, view);
        AnalyticsView view1=new AnalyticsView(getContext());
        view1.getView1();
        return view;
    }


    @OnClick(R.id.buttonView1)
    void submit() {
        this.actionListener.onActionNext();
    }

}
