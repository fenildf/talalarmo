package trikita.talalarmo.ui;

import android.graphics.Typeface;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.ToggleButton;

public class AmPmSwitch extends ToggleButton {
    private final static String tag = "TimeSwitch";

    private final static float TEXT_SIZE_COEF = 0.3f;	// from view height
    private float TEXT_WIDTH_COEF = 0.3f;
    private float DRAWABLE_PADDING_COEF = 0.1f;
    private float DRAWABLE_WIDTH_COEF = 0.8f;
    private float CIRCLE_RADIUS_COEF = 0.2f;

    private Paint mPaint;

    private int mColorOn;
    private int mColorOff;

    public AmPmSwitch(Context c) {
        super(c);
        Typeface t = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Roboto-Light.ttf");
        mColorOff = Theme.LIGHT.primaryColor;
        mColorOn = Theme.LIGHT.accentColor;

        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setStrokeWidth(4);
        mPaint.setTypeface(t);
        setBackgroundDrawable(null);
    }

    @Override
    public void onDraw(Canvas c) {
        mPaint.setTextSize((int) (getHeight() * TEXT_SIZE_COEF));
        Rect amRect = new Rect();
        mPaint.getTextBounds("AM", 0, "AM".length(), amRect);
        float amW = mPaint.measureText("AM");
        Rect pmRect = new Rect();
        mPaint.getTextBounds("PM", 0, "PM".length(), pmRect);
        float pmW = mPaint.measureText("PM");
        float drawableWidth = getWidth() - amW - pmW;
        float lineWidth = drawableWidth * DRAWABLE_WIDTH_COEF;
        float padding = drawableWidth * DRAWABLE_PADDING_COEF;
        float r = lineWidth * CIRCLE_RADIUS_COEF;

        float startPosX = 0;
        float startPosY = (getHeight() + amRect.height()) / 2.0f;

        mPaint.setColor(mColorOff);
        c.drawText("AM", startPosX, startPosY, mPaint);
        c.drawText("PM", startPosX + amW + drawableWidth, startPosY, mPaint);

        mPaint.setColor(mColorOn);
        if (isChecked()) {
            c.drawCircle(startPosX + amW + padding + r, getHeight()/2.0f, r, mPaint);
        } else {
            c.drawCircle(getWidth() - pmW - padding - r, getHeight()/2.0f, r, mPaint);
        }
        c.drawLine(startPosX + amW + padding,
                getHeight()/2.0f,
                startPosX + amW + padding + lineWidth,
                getHeight()/2.0f,
                mPaint);
    }
}
