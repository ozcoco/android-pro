package oz.common.utils.net;


import android.content.Context;
import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;

public class NetAfinal {


	private static FinalBitmap mFinalBitmap;
	
	private static FinalHttp mFinalHttp;

	private static Context mContext;
	
	
	/**
	 * ���FinalBitmapʵ������
	 * */
	public static FinalBitmap getFinalBitmap(Context context)
	{
		
		if(mFinalBitmap == null)
		{
			
			mContext = context;
			
			mFinalBitmap = FinalBitmap.create(mContext);
			
		}
		
		
		return mFinalBitmap;
	}
	
	
	/**
	 * 
	 * ���FinalHttp�������ʵ������
	 * */
	public static FinalHttp getFinalHttp()
	{
		
		if(mFinalHttp == null)
		{
			
			mFinalHttp = new FinalHttp();
			
		}
			
		return mFinalHttp;
		
	}
	
	
	/**
	 * 
	 * �ͷ���������
	 * */
	public static void destroyNetwork()
	{
		
		if(mFinalBitmap != null)mFinalBitmap = null;
		
		if(mFinalHttp != null)mFinalHttp = null;
		
		if(mContext != null)mContext = null;
		
		System.gc();
		
	}
	
	
}