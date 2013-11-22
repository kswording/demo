package com.example.leftrightslidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class SlideView extends ScrollView {
	private static final int LeftMove = 0;
	private static final int RightMove = 1;
	private static final int Center = 2;
	private int start_x,start_y;
	private int MaxLeftWidth,MaxRightWidth;
	
	public SlideView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public SlideView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return super.dispatchTouchEvent(ev);
	}
	

	public void setMaxWidth (int lwidth,int rwidth) {
		MaxLeftWidth = lwidth;
		MaxRightWidth = rwidth;
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int x = (int) ev.getRawX();
		int y = (int) ev.getRawY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			start_x = x;
			start_y = y;
			return false;
		case MotionEvent.ACTION_MOVE:
			int m = Math.abs(x - start_x);
			if (m > 25 && m > Math.abs(y - start_y)) {
				return true;
			}
			return false;
		case MotionEvent.ACTION_UP:
			RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) this.getLayoutParams();
			if (param.leftMargin == MaxLeftWidth || param.rightMargin == MaxRightWidth) {
				move(Center);
				return true;
			}
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int x = (int) event.getRawX();
		RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) this.getLayoutParams();
		int left = param.leftMargin;
		int right = param.rightMargin;
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			int m = x - start_x;
			start_x = x;
			param.leftMargin = left + m;
			param.rightMargin = right - m;
			if (param.leftMargin > MaxLeftWidth) {
				param.leftMargin = MaxLeftWidth;
				param.rightMargin = -MaxLeftWidth;
			} else if (param.rightMargin > MaxRightWidth) {
				param.leftMargin = -MaxRightWidth;
				param.rightMargin = MaxRightWidth;
			} 
			this.setLayoutParams(param);
			break;
		case MotionEvent.ACTION_UP:
			if (left >= MaxLeftWidth/2) {
				move(RightMove);
			} else if (right >= MaxRightWidth/2) {
				move(LeftMove);
			} else {
				move(Center);
			}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	public void move(int id) {
		RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) this.getLayoutParams();
		int left = param.leftMargin;
		int right = param.rightMargin;
		TranslateAnimation ta = null;
		if (id == LeftMove) {
			param.rightMargin = MaxRightWidth;
			param.leftMargin = -MaxRightWidth;
			ta = new TranslateAnimation(MaxRightWidth - right, 0.0f, 0.0f, 0.0f);
			ta.setDuration(50L);
			this.setAnimation(ta);
			this.setLayoutParams(param);
		} else if (id == RightMove) {
			param.rightMargin = -MaxLeftWidth;
			param.leftMargin = MaxLeftWidth;
			ta = new TranslateAnimation(left- MaxLeftWidth, 0.0f, 0.0f, 0.0f);
			ta.setDuration(50L);
			this.setAnimation(ta);
			this.setLayoutParams(param);
		} else if (id == Center) {
			param.rightMargin = 0;
			param.leftMargin = 0;
			int start = 0;
			if (right > 0) {
				start = - right;
			} else if (left > 0) {
				start = left;
			}
			ta = new TranslateAnimation(start, 0.0f, 0.0f, 0.0f);
			ta.setDuration(50L);
			this.setAnimation(ta);
			this.setLayoutParams(param);
		}
	}
}

