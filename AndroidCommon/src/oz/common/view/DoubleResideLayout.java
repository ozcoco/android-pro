/**  
 * Project Name:HelloAndroid  
 * File Name:DoubleResideLayout.java  
 * Package Name:com.oz.custom.view  
 * Date:2015年1月29日上午8:51:02  
 * Copyright (c) 2015, ozcomcn@foxmail.com All Rights Reserved.  
 *  
 */

package oz.common.view;



import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;


/**
 * ClassName:DoubleResideLayout <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年1月29日 上午8:51:02 <br/>
 * 
 * @author Administrator
 * @version
 * @since JDK 1.6
 * @see
 */
public class DoubleResideLayout extends FrameLayout implements
		OnGestureListener
{
	
	private void init(Context context)
	{
		
		WindowManager manager = (WindowManager) context 
	            .getSystemService(Context.WINDOW_SERVICE); 	
		
		LEFT_DRAWER_WIDTH		= (int) (manager.getDefaultDisplay().getWidth() * 0.8);
		
		RIGHT_DRAWER_WIDTH		= (int) (manager.getDefaultDisplay().getWidth() * 0.2);
		
		CONTENT_PANEL_WIDTH		= manager.getDefaultDisplay().getWidth();
			
		mScroller = new Scroller(context);
		
		mDetector = new GestureDetectorCompat(context, this);
		
		TOUCH_SLOP = ViewConfiguration.get(getContext()).getScaledTouchSlop();
		
	}
	
	public DoubleResideLayout(Context context)
	{
		
		super(context);
		
		init(context);
		
	}
	
	public DoubleResideLayout(Context context, AttributeSet attrs)
	{
		
		super(context, attrs);
		
		init(context);
		
	}
	
	public DoubleResideLayout(Context context, AttributeSet attrs, int defStyle)
	{
		
		super(context, attrs, defStyle);
		
		init(context);
		
	}
	

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom)
	{
		
		int childCount = getChildCount();
		
		for (int i = 0; i < childCount; i++)
		{
			
			View view = getChildAt(i);
			
			switch (i)
			{
			
				case 0:
				{
					
					view.layout(-LEFT_DRAWER_WIDTH, top, 0, bottom);
					
				}
					break;
				
				case 1:
				{
					
					view.layout(left, top, right, bottom);
					
				}
					break;
				
				case 2:
				{
					
					view.layout(CONTENT_PANEL_WIDTH, top, CONTENT_PANEL_WIDTH
							+ RIGHT_DRAWER_WIDTH, bottom);
					
				}
					break;
			}
			
		}
		
	}
	
	@Override
	public boolean onDown(MotionEvent e)
	{
		
		return true;
	}
	
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY)
	{
		
		if (!mScroller.isFinished() || mScroller.computeScrollOffset())
		{
			
			mScroller.abortAnimation();
			
			mScroller.forceFinished(true);
			
			mScroller = null;
			
			mScroller = new Scroller(getContext());
		}
		
		final int distance = (int) (e2.getX() - e1.getX());
		
		if (distance > TOUCH_SLOP && velocityX > MIN_SCROLL_VELOCITY)
		{
			
			if (isOpenLeftDrawer == false && isOpenRightDrawer == false)
			{
				
				int dx = -LEFT_DRAWER_WIDTH - getScrollX();
				
				isOpenLeftDrawer = true;
				
				mScroller.startScroll(getScrollX(), 0, dx, 0, 100);
				
			}
			else if (isOpenLeftDrawer == false && isOpenRightDrawer == true)
			{
				
				int dx = -getScrollX();
				
				isOpenRightDrawer = false;
				
				mScroller.startScroll(getScrollX(), 0, dx, 0, 100);
				
			}
			
		}
		
		if (distance < -TOUCH_SLOP && velocityX > MIN_SCROLL_VELOCITY)
		{
			
			if (isOpenLeftDrawer == false && isOpenRightDrawer == false)
			{
				
				int dx = RIGHT_DRAWER_WIDTH - getScrollX();
				
				isOpenRightDrawer = true;
				
				mScroller.startScroll(getScrollX(), 0, dx, 0, 100);
				
			}
			else if (isOpenLeftDrawer == true && isOpenRightDrawer == false)
			{
				
				int dx = Math.abs(getScrollX());
				
				isOpenLeftDrawer = false;
				
				mScroller.startScroll(getScrollX(), 0, dx, 0, 100);
				
			}
			
		}
		
		return true;
		
	}
	
	@Override
	public void onLongPress(MotionEvent arg0)
	{
		
	}
	
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		
		if (!mScroller.isFinished() || mScroller.computeScrollOffset())
		{
			
			mScroller.abortAnimation();
			
			mScroller.forceFinished(true);
			
			mScroller = null;
			
			mScroller = new Scroller(getContext());
		}
		
		scrollBy((int) distanceX, 0);
		
		return true;
	}
	
	@Override
	public void onShowPress(MotionEvent e)
	{
		
	}
	
	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		mDetector.onTouchEvent(event);
		
		switch (event.getAction())
		{
		
			case MotionEvent.ACTION_UP:
				
				switchScreen(getScrollX());
				
				break;
		
		}
		
		return true;
		
	}
	
	private boolean isOverflow(final int scrollX)
	{
		
		if (scrollX >= -LEFT_DRAWER_WIDTH && scrollX <= RIGHT_DRAWER_WIDTH)
		{
			
			return true;
			
		}
		else
		{
			
			if (scrollX > RIGHT_DRAWER_WIDTH)
			{
				
				setScrollX(RIGHT_DRAWER_WIDTH);
				
			}
			else if (scrollX < -LEFT_DRAWER_WIDTH)
			{
				
				setScrollX(-LEFT_DRAWER_WIDTH);
				
			}
			
			return false;
			
		}
		
	}
	
	@Override
	public void computeScroll()
	{
		super.computeScroll();
		
		boolean blnOverflow = isOverflow(getScrollX());
		
		if (blnOverflow && mScroller.computeScrollOffset())
		{
			
			scrollTo(mScroller.getCurrX(), 0);
			
		}
		else
		{
			
		}
		
		postInvalidate();
		
	}
	
	private void switchScreen(final int scrollX)
	{
		
		if (scrollX < 0)
		{
			
			if (scrollX <= -LEFT_DRAWER_WIDTH / 2)
			{
				
				int dx = -LEFT_DRAWER_WIDTH - scrollX;
				
				openLeftDrawer(scrollX, dx);
				
			}
			else
			{
				
				int dx = Math.abs(scrollX);
				
				closeLeftDrawer(scrollX, dx);
				
			}
			
		}
		else if (scrollX > 0)
		{
			
			if (scrollX >= RIGHT_DRAWER_WIDTH / 2)
			{
				
				int dx = RIGHT_DRAWER_WIDTH - scrollX;
				
				openRightDrawer(scrollX, dx);
				
			}
			else
			{
				
				int dx = -scrollX;
				
				closeRightDrawer(scrollX, dx);
				
			}
			
		}
	}
	
	private boolean	isOpenLeftDrawer	= false;
	
	public boolean isOpenLeftDrawer()
	{
		
		return isOpenLeftDrawer;
		
	}
	
	private boolean	isOpenRightDrawer	= false;
	
	public boolean isOpenRightDrawer()
	{
		
		return isOpenRightDrawer;
		
	}
	
	public void openLeftDrawer(final int startX, final int dx)
	{
		
		isOpenLeftDrawer = true;
		
		mScroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 2);
		
	}
	
	public void closeLeftDrawer(final int startX, final int dx)
	{
		
		isOpenLeftDrawer = false;
		
		mScroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 2);
		
	}
	
	public void closeLeftDrawer()
	{
		
		isOpenLeftDrawer = false;
		
		int dx = LEFT_DRAWER_WIDTH;
		
		mScroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);
		
	}
	
	
	public void openRightDrawer(final int startX, final int dx)
	{
		
		isOpenRightDrawer = true;
		
		mScroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 2);
		
	}
	
	public void closeRightDrawer(final int startX, final int dx)
	{
		
		isOpenRightDrawer = false;
		
		mScroller.startScroll(startX, 0, dx, 0, Math.abs(dx) * 2);
				
	}
	
	public void closeRightDrawer()
	{
		
		isOpenRightDrawer = false;
		
		int dx = - RIGHT_DRAWER_WIDTH;
		
		mScroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx) * 2);
				
	}
	
	private static int				LEFT_DRAWER_WIDTH	;
	
	private static int				LEFT_DRAWER_HEIGHT	;
	
	private static int				RIGHT_DRAWER_WIDTH	;
	
	private static int				RIGHT_DRAWER_HEIGHT	;
	
	private static int				CONTENT_PANEL_WIDTH	;
	
	private static int				CONTENT_PANEL_HEIGHT;
	
	private static int				MIN_SCROLL_VELOCITY		= 600;
	
	private static int				TOUCH_SLOP				= 0;
	
	private Scroller				mScroller;
	
	private GestureDetectorCompat	mDetector;
	
}







/**
 * 
 * P.s
 * 

//@Override
//protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//{
//	
//	int width = MeasureSpec.getSize(widthMeasureSpec);
//	
//	int height = MeasureSpec.getSize(heightMeasureSpec);
//	
//	setMeasuredDimension(width, height);
//	
//	int childCount = getChildCount();
//	
//	for (int i = 0; i < childCount; i++)
//	{
//		
//		View view = getChildAt(i);
//		
//		switch (i)
//		{
//		
//			case 0:
//			{
//				
//				view.measure(LEFT_DRAWER_WIDTH, LEFT_DRAWER_HEIGHT);
//				
//			}
//				break;
//			
//			case 1:
//			{
//				
//				view.measure(CONTENT_PANEL_WIDTH, CONTENT_PANEL_HEIGHT);
//				
//			}
//				break;
//			
//			case 2:
//			{
//				
//				view.measure(RIGHT_DRAWER_WIDTH, RIGHT_DRAWER_HEIGHT);
//				
//			}
//				break;
//		}
//		
//	}
//	
//}


 * 
 * 
 */



