package com.enphoneh.drawboard;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.SurfaceView;

public class MyView  extends SurfaceView  implements Callback,OnTouchListener{

	class Point{
		float x;
		float y;
	}
	
	Paint mPaint = new Paint();
	Path mPath = new Path();
	List<Point> mPathList = new ArrayList<Point>();
	Point point = new Point();
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
		mPaint.setColor(Color.BLUE);
		mPaint.setTextSize(15);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(10);
		mPaint.setShadowLayer(20, 0.0f, 0.0f, Color.BLUE);
		mPaint.setAntiAlias(true);
		mPaint.setAlpha(150);
		setOnTouchListener(this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		draw();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	
	public void draw(){
		Canvas canvas = getHolder().lockCanvas();
		canvas.drawColor(Color.WHITE);
		canvas.drawPath(mPath, mPaint);
		getHolder().unlockCanvasAndPost(canvas);
	}
	
	public void clear(){
		mPath.reset();
		draw();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPath.moveTo(event.getX(), event.getY());
			point.x = event.getX();
			point.y = event.getY();
			mPathList.add(point);
			draw();
			break;
		case MotionEvent.ACTION_MOVE:
			mPath.lineTo(event.getX(), event.getY());
			point.x = event.getX();
			point.y = event.getY();
			mPathList.add(point);
			draw();
			break;
		default:
			break;
		}
		
		return true;
	}


}
