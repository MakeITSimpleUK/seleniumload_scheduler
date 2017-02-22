package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by onTy on 2017-02-19.
 */
public class SysInfoUtil
{

	public static String getHostname() throws UnknownHostException
	{
		return InetAddress.getLocalHost().getHostName();
	}

	public static String getTimeStampString()
	{
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd-HHmmss");
		return formatter.print( getDate() );
	}

	public static DateTime getDate()
	{
		return DateTime.now(DateTimeZone.UTC);
	}

}
