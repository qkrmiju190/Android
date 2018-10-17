package com.example.park.gameframework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppManager {
    private static AppManager s_instance;
    private GameView m_gameview;
    private Resources m_resources;

    public static AppManager getInstance() {
        if (s_instance == null) { //객체가 하나도 안만들어져 있으면
            s_instance = new AppManager(); //객체를 하나 만들어라
        }
        return s_instance;
    }

    void setGameView(GameView _gameview) {
        m_gameview = _gameview;
    }

    void setResources(Resources _resources) {
        m_resources = _resources;
    }

    public GameView getGameView() {
        return m_gameview;
    }

    public Resources getrsources() {
        return m_resources;
    }

    public Bitmap getBitmap(int r) {
      return  BitmapFactory.decodeResource(m_resources, r);
    }
}

