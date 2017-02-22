package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

/**
 * Created by onTy on 2017-02-19.
 */
public class SleepUtil
{
	public static void sleep( double sleepS )
	{
		try
		{
			Thread.sleep( ((long)(sleepS*1000)) );
		}
		catch (InterruptedException e)
		{ }
	}

}
