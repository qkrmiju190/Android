package com.example.park.gameframework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread{

    private  SurfaceHolder m_surfaceHolder;
    private GameView m_gameview;

    private boolean m_run  = false;
    private boolean run;


    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameView) {
        m_surfaceHolder = surfaceHolder;
        m_gameview = gameView;
    }


    public void setRunning(boolean run) {
        this.run = run;
    }
    @Override
    public void run() {
        super.run();

        Canvas _canvas;
        while (m_run){
            _canvas = null;
            try {
                m_gameview.Update();
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder) {
                    m_gameview.onDraw(_canvas);
                } //synchronized = 동기화
            } finally{
                if (_canvas != null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);


                }
            }
        }


}


