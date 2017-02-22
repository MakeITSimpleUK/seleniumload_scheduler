package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by onTy on 2017-02-19.
 */
public class ControlUtil
{

	private File lockFile;
	private File nextIdFile;
	private String myName;

	public ControlUtil( String myName )
	{
		lockFile   = new File( ConfigManager.LOCK_FILE_PATH );
		nextIdFile = new File( ConfigManager.NEXT_ID_FILE_PATH );

		this.myName = myName;
	}

	public int getMyId() throws Exception
	{
		int     myId   = -1;
		boolean haveId = false;

		// determine my id
		do
		{
			MyLogger.debug("beginning of loop");
			if ( isLockFilePresent() )
			{
				MyLogger.debug("lock file exists, sleeping and starting over");
				SleepUtil.sleep(1.0);
				continue;
			}

			MyLogger.debug("trying to create lock file");
			createLockFile( myName );

			MyLogger.debug("checking who is in control");

			String isInControl = readLockFileContents();
			MyLogger.debug("'"+isInControl+"' is in control");

			if ( ! isInControl.equalsIgnoreCase(myName) )
			{
				MyLogger.debug("sleeping and starting over");
				SleepUtil.sleep(1.0);
				continue;
			}

			MyLogger.debug("OK we are good! reading nextid file");
			myId = readNextIdFile();
			MyLogger.debug("found nextid="+myId);

			MyLogger.debug("about to create or overwrite nextid file with" + (myId+1) );
			createOrOverwriteNextIdFile( myId+1 );

			MyLogger.debug("about to remove lock file");
			removeLockFile();

			MyLogger.debug("end of loop");
			break;
		}
		while( !haveId );

		// now read assignments file
		MyLogger.debug( "My id is " + myId );

		return myId;
	}


	private boolean isLockFilePresent()
	{
		return lockFile.exists();
	}


	private boolean createLockFile( String myName )
	{
		try
		{
			lockFile.createNewFile();
			FileWriter fw = new FileWriter(lockFile);
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(myName);
			bw.close();
			fw.close();
			return true;
		}
		catch ( IOException e )
		{
			return false;
		}
	}

	private String readLockFileContents() throws IOException
	{
		FileReader fr = new FileReader(lockFile);
		BufferedReader br = new BufferedReader( fr );
		String contents = br.readLine();
		br.close();
		fr.close();
		return contents;
	}

	private int readNextIdFile() throws IOException
	{
		try
		{
			BufferedReader r = new BufferedReader(new FileReader(nextIdFile));
			return Integer.parseInt(r.readLine());
		}
		catch ( IOException ex )
		{
			return 1;
		}
	}

	private void createOrOverwriteNextIdFile( int nextId ) throws IOException
	{
		nextIdFile.delete();
		nextIdFile.createNewFile();
		BufferedWriter w = new BufferedWriter( new FileWriter(nextIdFile) );
		w.write( Integer.toString(nextId) );
		w.close();
	}

	private void removeLockFile() throws IOException
	{
		Files.delete( lockFile.toPath() );
		//if ( ! lockFile.delete() )
		//	throw new Exception("Could not delete lock file");
	}

}
