package uk.co.makeitsimple.infinitemp.selenium.scheduler;


import uk.co.makeitsimple.infinitemp.selenium.scheduler.domainObjects.*;
import uk.co.makeitsimple.infinitemp.selenium.scheduler.utils.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.UnknownHostException;


public class Scheduler
{

	public static void main( String[] args )
	{
		try
		{
			MyLogger.debug("Starting");

			String myName = getMyName();

			MyLogger.debug("My name = " + myName);

			ControlUtil control = new ControlUtil( myName );
			int myId = control.getMyId();

			MyLogger.debug("My id = " + myId);

			AssignmentUtil assignmentUtil = new AssignmentUtil();
			assignmentUtil.dumpAssignments(System.out);


			Assignment a = assignmentUtil.getMyAssignment( myId );

			if ( a == null )
			{
				MyLogger.debug("I have no assignment");
				return;
			}

			MyLogger.debug("My assignment is: " + a.getRun() );

			performAssignment( a, myId, myName );

			MyLogger.debug( "Assignment performed." );
		}
		catch ( JAXBException e )
		{
			System.err.println("There was a problem reading assignments file");
			e.printStackTrace();
			return;
		}
		catch ( Exception ex )
		{
			System.err.println("There was a problem");
			ex.printStackTrace();
			return;
		}

	}

	private static void performAssignment(Assignment a, int myId, String myName ) throws IOException
	{
		StatusUtil status = new StatusUtil( myId, myName );
		status.setStatus("About to run assignment: " + a);

		Runtime rt = Runtime.getRuntime();
		Process process = rt.exec( a.getRun() );

		while ( process.isAlive() )
			SleepUtil.sleep(1.0);

		int exitStatus = process.exitValue();

		status.setStatus("Assignment finished with exit status=" + exitStatus);
	}

	public static String getMyName() throws UnknownHostException
	{
		return SysInfoUtil.getHostname();
	}

}
