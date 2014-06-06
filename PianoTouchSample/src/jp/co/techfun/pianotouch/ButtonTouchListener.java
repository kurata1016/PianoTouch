package jp.co.techfun.pianotouch;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.media.MediaPlayer;
//import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;

// �^�b�`���X�i�[�N���X
public class ButtonTouchListener implements View.OnTouchListener {
    // GestureDetector����
    private GestureDetector gestureDetector;

    // �^�b�`���ꂽ�L�[�ɑΉ����鉹���t�B�[���h
    private MediaPlayer mp;

    // ������Ԃ̉摜
    private int defaultPic;

    // �^�b�`���̉摜
    private int touchPic;

    // �R���X�g���N�^
    @SuppressWarnings("deprecation")
    public ButtonTouchListener(MediaPlayer tMp, int tDefaultPic, int tTouchPic) {
        gestureDetector = new GestureDetector(onGestureListener);
        defaultPic = tDefaultPic;
        touchPic = tTouchPic;
        mp = tMp;
    }

    // onTouch���\�b�h(�^�b�`�C�x���g)
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // ���\�[�X�̎擾
        Resources res = view.getResources();
        // ���Ղ̉摜���^�b�`���摜�ɐݒ�
        view.setBackgroundDrawable(res.getDrawable(touchPic));
        // ���Ղ̔w�i���f�t�H���g�摜�ɐݒ�
        view.setBackgroundDrawable(res.getDrawable(defaultPic));

        gestureDetector.onTouchEvent(event);

        //
        // switch (event.getActionMasked()) {
        //
        // // �^�b�`�����ꍇ
        // case MotionEvent.ACTION_DOWN:
        // // ���Ղ̉摜���^�b�`���摜�ɐݒ�
        // view.setBackgroundDrawable(res.getDrawable(touchPic));
        // // �������Đ�
        // startPlay();
        //
        // break;
        //
        // // �^�b�`�����ꂽ�ꍇ
        // case MotionEvent.ACTION_UP:
        // // ���Ղ̔w�i���f�t�H���g�摜�ɐݒ�
        // view.setBackgroundDrawable(res.getDrawable(defaultPic));
        // // �������~
        // stopPlay();
        //
        // break;
        //
        // // ��L�ȊO
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

                // log�\��
                // Log.v("onFling", "onFling");

                // ���y�Đ�
                startPlay();

                return super.onFling(event1, event2, velocityX, velocityX);
            }
        };

    // startPlay���\�b�h(�����Đ�����)
    private void startPlay() {
        mp.seekTo(0);
        mp.start();
    }

    // stopPlay���\�b�h(������~����)
    // private void stopPlay() {
    // mp.pause();
    // }
}
