package com.example.park.gameframework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {

    //추상메소드
    public void Init();
    //상태가 생성되었을 때
    public void Destroy();
    //소멸
    public void Update();
    //지속적으로 수행할 것
    public void Render(Canvas canvas);
    //그려야 할것
    public boolean onKeyDown(int keyCode, KeyEvent event);
    //키 처리
    public boolean onTouchEvent(MotionEvent event);
    //터치 처리
}
