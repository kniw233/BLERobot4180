package com.example.BLERobot4180;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

public class MapView extends View {

    private Paint bluePaint;
    private Paint whitePaint;

    public MapView(Context context) {
        super(context);

        init(null, 0);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs, 0);
    }

    public MapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        canvas.drawCircle(centerX, centerY, 10, bluePaint);
        for (Obstacle obstacle : Update.obstacles) {
            float x = obstacle.getStartX() - Update.robotPosition[0];
            float y = obstacle.getStartY() - Update.robotPosition[1];
            if (Math.abs(x) < 300 &&  Math.abs(y) < 300) {
                canvas.drawLine(centerX + x, centerY - y,  centerX + x, centerY - (y + 10), whitePaint);
            }
        }
    }
}
