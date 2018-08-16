package com.test.presentation.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.test.com.testproject.R;
import android.util.AttributeSet;
import android.view.View;

public class PieView extends View {

    private RectF rectF;
    private RectF rectF2;
    private RectF rectF3;

    private Paint paint;
    private Paint centerCircle;
    private Paint spaseColor;

    private static float[] percent = {25, 25, 25, 25};
    private String[] paintColor = {"#01cb60", "#f6931c", "#637deb", "#cecac7"};

    public static void setPercent(float[] percent) {
        PieView.percent = percent;
    }

    public PieView(Context context) {
        super(context);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        rectF = new RectF();
        rectF2 = new RectF();
        rectF3 = new RectF();

        paint = new Paint();
        paint.setAntiAlias(true); // сглаживание углов

        centerCircle = new Paint();
        centerCircle.setColor(getResources().getColor(R.color.bg_main_item));
        centerCircle.setAntiAlias(true);

        spaseColor = new Paint();
        spaseColor.setColor(getResources().getColor(R.color.bg_main_layout));
        spaseColor.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = getWidth() * 0.03f;
        rectF.top = rectF.left;
        rectF.right = getWidth() * 0.97f;
        rectF.bottom = rectF.right;


        rectF2.left = getWidth() * 0.04f;
        rectF2.top = rectF2.left;
        rectF2.right = getWidth() * 0.96f;
        rectF2.bottom = rectF2.right;


        rectF3.left = getWidth() * 0.07f;
        rectF3.top = rectF3.left;
        rectF3.right = getWidth() * 0.93f;
        rectF3.bottom = rectF3.right;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //считаю сумму чтобы узнать какая она для 100%
        float x = 0;
        for (int i = 0; i != percent.length; i++) {
            x = x + percent[i];
        }

        float c = -90; //исходная точка откуда рисуем
        for (int i = 0; i != percent.length; i++) {
            paint.setColor(Color.parseColor(paintColor[i]));

            float g = (((100 / x) * percent[i]) / 100) * 360; // сколько % круга рисовать
            canvas.drawArc(rectF, c, 1f, true, spaseColor);
            if (g > 4) {
                canvas.drawArc(rectF, c + 2, g - 4, true, paint);
            } else {
                canvas.drawArc(rectF, c + 1, 4-g, true, paint);
            }

            canvas.drawArc(rectF, c + 2, 1f, true, spaseColor);
            c = c + g; //сдвигает точку от куда рисовать

        }

        canvas.drawArc(rectF3, 0, 360, true, centerCircle);
    }
}
