package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by onTy on 2017-02-20.
 */
public class StatusUtil
{

	public int actorId;
	public String actorName;

	private File statusFile;

	public StatusUtil( int actorId, String actorName ) throws IOException
	{
		statusFile = new File( ConfigManager.STATUS_DIR_NAME + File.separatorChar + "runner-" + actorId + ".txt" );
		statusFile.createNewFile();

		setStatus("Actor id: " + actorId);
		setStatus("Actor name: " + actorName);
	}

	public void setStatus( String status ) throws IOException
	{
		appendToStatusFile(status);
	}


	private void appendToStatusFile( String message ) throws IOException
	{
		FileWriter fw = new FileWriter(statusFile, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.append( "[" + SysInfoUtil.getTimeStampString() + "] " + message );
		bw.append( System.lineSeparator() );

		bw.close();
		fw.close();
	}

}
