package jp.co.techfun.pianotouch;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

// タッチリスナークラス
public class ButtonTouchListener implements View.OnTouchListener {
    // GestureDetector生成
    private GestureDetector gestureDetector;

    // タッチされたキーに対応する音声フィールド
    private MediaPlayer mp;

    // 初期状態の画像
    private int defaultPic;

    // タッチ時の画像
    private int touchPic;

    // コンストラクタ
    @SuppressWarnings("deprecation")
    public ButtonTouchListener(MediaPlayer tMp, int tDefaultPic, int tTouchPic) {
        gestureDetector = new GestureDetector(onGestureListener);
        defaultPic = tDefaultPic;
        touchPic = tTouchPic;
        mp = tMp;
    }

    // onTouchメソッド(タッチイベント)
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // リソースの取得
        Resources res = view.getResources();
        // 鍵盤の画像をタッチ時画像に設定
        view.setBackgroundDrawable(res.getDrawable(touchPic));
        // 鍵盤の背景をデフォルト画像に設定
        view.setBackgroundDrawable(res.getDrawable(defaultPic));

        gestureDetector.onTouchEvent(event);

        //
        // switch (event.getActionMasked()) {
        //
        // // タッチした場合
        // case MotionEvent.ACTION_DOWN:
        // // 鍵盤の画像をタッチ時画像に設定
        // view.setBackgroundDrawable(res.getDrawable(touchPic));
        // // 音声を再生
        // startPlay();
        //
        // break;
        //
        // // タッチが離れた場合
        // case MotionEvent.ACTION_UP:
        // // 鍵盤の背景をデフォルト画像に設定
        // view.setBackgroundDrawable(res.getDrawable(defaultPic));
        // // 音声を停止
        // stopPlay();
        //
        // break;
        //
        // // 上記以外
        // default:
        //
        // break;
        // }

        return true;
    }

    private final SimpleOnGestureListener onGestureListener =
        new SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent event1, MotionEvent event2,
                float velocityX, float velocityY) {

                // log表示
                // Log.v("onFling", "onFling");

                // 音楽再生
                startPlay();

                return super.onFling(event1, event2, velocityX, velocityX);
            }
        };

    // startPlayメソッド(音声再生処理)
    private void startPlay() {
        mp.seekTo(0);
        mp.start();
    }

    // stopPlayメソッド(音声停止処理)
    // private void stopPlay() {
    // mp.pause();
    // }
}
