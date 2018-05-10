package com.example.christophe.mixpanelapp.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.christophe.mixpanelapp.R;
import com.example.christophe.mixpanelapp.adapter.PageAdapter;
import com.example.christophe.mixpanelapp.service.MyFireBaseMessagingService;
import com.example.christophe.mixpanelapp.util.ActionListener;
import com.example.christophe.mixpanelapp.util.MixPanelHelper;

import io.fabric.sdk.android.Fabric;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ActionListener {

    @BindView(R.id.toolbar)
    Toolbar mtoolbar;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SharedPreferences sharedPreferences;
    private BroadcastReceiver receiver;
    private boolean showingOptionsMenu = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mtoolbar);

        sharedPreferences = getApplication().getSharedPreferences("Save", MODE_PRIVATE);
        String token = sharedPreferences.getString("Value", "0");
        Log.d("Firebase", "token " + token);


        // Instead of putting big blocks of code in onCreate, we put them in different methods and call them from
        // onCreate, making the code more readable
        this.initViewPager();
        this.initMixPanel();
        this.initBroadCast();


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

    public void initBroadCast() {
        IntentFilter filter = new IntentFilter(MyFireBaseMessagingService.KEY_NOTIF);
        receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String value = intent.getExtras().getString("value");
                Log.e("key", value);
                switch (value) {
                    case "1":
                        viewPager.setCurrentItem(3);
                        break;
                    case "3":
                        throw new RuntimeException();
                    case "4":
                        showingOptionsMenu = true;
                        invalidateOptionsMenu();
                        break;
                    default:
                        break;

                }


            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, filter);

    }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        menu.getItem(0).setVisible(showingOptionsMenu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Congratulations, you enabled a premium feature !", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        MixPanelHelper.getInstance(this).getMixpanelAPI().flush();
        if (receiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
