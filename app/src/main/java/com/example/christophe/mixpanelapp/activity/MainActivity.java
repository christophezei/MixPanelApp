package com.example.christophe.mixpanelapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.adapter.PageAdapter;
import com.example.christophe.mixpanelapp.fragment.Tab1Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab2Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab3Fragment;
import com.example.christophe.mixpanelapp.fragment.Tab4Fragment;
import com.example.christophe.mixpanelapp.util.ActionListener;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;

import io.fabric.sdk.android.Fabric;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.christophe.mixpanelapp.util.ActionListener.Action.ACTION_ONE;

public class MainActivity extends AppCompatActivity implements ActionListener {

    @BindView(R.id.toolbar)
    Toolbar mtoolbar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mtoolbar);

        // Instead of putting big blocks of code in onCreate, we put them in different methods and call them from
        // onCreate, making the code more readable
        this.initViewPager();
        this.initMixPanel();
    }

    private void initViewPager() {
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initMixPanel() {
        String uniqueId = UUID.randomUUID().toString(); // Can be converted to local variable
        MixPanelHelper.getInstance(this).getMixpanelAPI().identify(uniqueId);
    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }


    // Previously, you had multiple interfaces in each fragment and you were implementing all interfaces.
    // Instead what we can do is use one interface and decide what action to take by using a key.

//    @Override
//    public void onActionNext() {
//        viewPager.setCurrentItem(1);
//    }
//
//    @Override
//    public void onActionNext2() {
//        viewPager.setCurrentItem(2);
//    }
//
//    @Override
//    public void onActionNext3() {
//        viewPager.setCurrentItem(3);
//    }
//
//    @Override
//    public void onActionDone() {
//        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onAction(Action action) {
        switch (action) {
            case ACTION_ONE:
                this.viewPager.setCurrentItem(1);
                break;
            case ACTION_TWO:
                this.viewPager.setCurrentItem(2);
                break;
            case ACTION_THREE:
                this.viewPager.setCurrentItem(3);
                break;
            case ACTION_FOUR:
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                break;
            default:
                //Action is not supported, we disregard
                break;
        }
    }

    @Override
    protected void onDestroy() {
        MixPanelHelper.getInstance(this).getMixpanelAPI().flush();
        super.onDestroy();
    }

}
