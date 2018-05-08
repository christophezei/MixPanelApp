package com.example.christophe.mixpanelapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.base.AnalyticsView;
import com.example.christophe.mixpanelapp.util.ActionListener;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class Tab2Fragment extends Fragment {

    private ActionListener actionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.actionListener = (ActionListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        ButterKnife.bind(this, view);
        AnalyticsView view2 = new AnalyticsView(getContext());
        view2.getView2();
        return view;
    }

    @OnClick(R.id.buttonView2)
    void submit() {
        this.actionListener.onAction(ActionListener.Action.ACTION_TWO);
    }


}
