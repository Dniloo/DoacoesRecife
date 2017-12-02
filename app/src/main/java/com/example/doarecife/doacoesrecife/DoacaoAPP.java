package com.example.doarecife.doacoesrecife;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by PC GAMER on 02/12/2017.
 */

public class DoacaoAPP extends Application {
    EventBus eventBus;
    @Override
    public void onCreate() {
        super.onCreate();

        eventBus = new EventBus();
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
