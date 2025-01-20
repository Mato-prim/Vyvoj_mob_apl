package com.example.skicar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Skicar extends View {

    private Paint paint;
    private Path path;
    private Bitmap bitmap;
    private Canvas canvas;

    public Skicar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // inicializacia vychodiskoveho stavu
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // nakreslenie existujucich ciar
        canvas.drawBitmap(bitmap, 0, 0, null);
        // nakreslenie novej ciary
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // zistenie suradnic
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            // zaznamenanie suradnic pri dotyku s obrazovkou
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            // kreslenie ciar pri pohybe po obrazovke
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            // ukoncenie kreslenia ciar a reset cesty pri preruseni dotyku s obrazovkou
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }

    public void changeColor(int color) {
        // zmena farby (farba zvolena zo zoznamu)
        paint.setColor(color);
    }

    public void addStrokeWidth() {
        // zvysenie hrubky ciari
        float cur_sw = paint.getStrokeWidth();
        paint.setStrokeWidth(cur_sw+1.0f);
    }
    public void subStrokeWidth() {
        // znizenie hrubky ciari
        float cur_sw = paint.getStrokeWidth();
        paint.setStrokeWidth(cur_sw-1.0f);
    }

    public void clear() {
        // vymaz - prefarbenie na bielo
        bitmap.eraseColor(Color.WHITE);
        invalidate();
    }
}