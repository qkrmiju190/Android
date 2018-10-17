package com.example.park.gameframework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private GameViewThread m_thread;
    private IState m_state;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        m_thread = new GameViewThread(getHolder(),this);
        
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());
        ChangeGameState(new IntroState());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        m_state.Render(canvas);
    }

    public void Update(){
        m_state.Update();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode,event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        AppManager.getInstance().getGameView().ChangeGameState(new CreditState());
        return super.onTouchEvent(event);
    }

    public void ChangeGameState(IState _state){
        if(m_state != null)
            m_state.Destroy();
        _state.Init();
        m_state = _state;
    }

    private void ChangeGameState(IntroState introState) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while (retry){
            try{
                m_thread.join();
                retry = false;
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
