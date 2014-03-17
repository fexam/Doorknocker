package com.example.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by nutjung on 3/15/14.
 */
public class VerticalButton extends Button implements View.OnClickListener{
    final boolean topDown;
    private Room room;
    private boolean rotate;
    private LinearLayout ll;
    public Activity c;

    public VerticalButton(Context context, boolean rt){
        super(context);
        rotate = rt;
        final int gravity = getGravity();
        Gravity Gravity;
        if(android.view.Gravity.isVertical(gravity) && (gravity& android.view.Gravity.VERTICAL_GRAVITY_MASK) == android.view.Gravity.BOTTOM) {
            setGravity((gravity& android.view.Gravity.HORIZONTAL_GRAVITY_MASK) | android.view.Gravity.TOP);
            topDown = true;
        }else
            topDown = false;
    }

    public VerticalButton(Context context, Activity a,Room ar,LinearLayout l, boolean rt){
        super(context);
        setOnClickListener(this);
        room =ar;
        rotate =rt;
        ll=l;
        c = a;
        final int gravity = getGravity();
        Gravity Gravity;
        if(android.view.Gravity.isVertical(gravity) && (gravity& android.view.Gravity.VERTICAL_GRAVITY_MASK) == android.view.Gravity.BOTTOM) {
            //setGravity((gravity& android.view.Gravity.HORIZONTAL_GRAVITY_MASK) | android.view.Gravity.TOP);
            this.setGravity(android.view.Gravity.CENTER);
            topDown = true;
        }else
        {
            this.setGravity(android.view.Gravity.CENTER);
            topDown = false;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void onDraw(Canvas canvas){

        TextPaint textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        textPaint.drawableState = getDrawableState();

        canvas.save();
        if(rotate)
        {
            if(topDown){
                canvas.translate(getWidth(), 0);
                canvas.rotate(90);
            }else {
                canvas.translate(getWidth(), 0);
                canvas.rotate(90);
            }
        }

        canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());
        getLayout().draw(canvas);
        canvas.restore();
    }

    @Override
    public void onClick(View view) {

        DetailDialog dd = new DetailDialog(c,view,ll,room);
        dd.show();

    }
}