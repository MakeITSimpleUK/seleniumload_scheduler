package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

import java.io.File;

/**
 * Created by onTy on 2017-02-20.
 */
public class ConfigManager
{

	public static final String CONTROL_DIR_NAME  = "control";
	public static final String STATUS_DIR_NAME   = "status";

	public static final String LOCK_FILE_PATH = CONTROL_DIR_NAME + File.separatorChar + "lock.txt";
	public static final String NEXT_ID_FILE_PATH = CONTROL_DIR_NAME + File.separatorChar + "nextid.txt";

	public static final String ASSIGNMENTS_FILE_NAME = "assignments.xml";



}
