package com.example.administrator.myapplication;

import android.app.Application;

import per.scpfoundation.busket.Busket;

public class DemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Busket.initialize(this);
    }
}
