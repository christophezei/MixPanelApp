package com.example.christophe.mixpanelapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.adapter.PageAdapter;
import com.example.christophe.mixpanelapp.fragment.Tab1Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab2Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab3Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab4Fragment;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;

import io.fabric.sdk.android.Fabric;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Tab1Fragment.ActionListener,Tab2Fragment.ActionListener,
        Tab3Fragment.ActionListener,Tab4Fragment.ActionListener{

    @BindView(R.id.toolbar) Toolbar mtoolbar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String uniqueId = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mtoolbar);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);
        uniqueId = UUID.randomUUID().toString();
        MixPanelHelper.getInstance(this).identify(uniqueId);



    }
    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }



    @Override
    public void onActionNext() {
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onActionNext2() {
        viewPager.setCurrentItem(2);
    }

    @Override
    public void onActionNext3() {
        viewPager.setCurrentItem(3);
    }

    @Override
    public void onActionDone() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        MixPanelHelper.getInstance(this).flush();
        super.onDestroy();
    }

}
