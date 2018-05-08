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


public class Tab3Fragment extends Fragment {

    private ActionListener actionListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.actionListener = (ActionListener) context;
    }

    public interface ActionListener {
        void onActionNext3();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_tab3, container, false);
        ButterKnife.bind(this,view);
        AnalyticsView view3=new AnalyticsView(getContext());
        view3.getView3();
       return view;

    }
    @OnClick(R.id.buttonView3)
    void submit() {
        this.actionListener.onActionNext3();
    }

}
