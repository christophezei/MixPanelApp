package com.example.christophe.mixpanelapp.util;

public interface ActionListener {

    enum Action {
        ACTION_ONE, ACTION_TWO, ACTION_THREE, ACTION_FOUR
    }

    void onAction(Action action);
}
