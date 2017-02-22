package uk.co.makeitsimple.infinitemp.selenium.scheduler.utils;

import uk.co.makeitsimple.infinitemp.selenium.scheduler.domainObjects.*;

import java.io.File;
import java.io.PrintStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by onTy on 2017-02-19.
 */
public class AssignmentUtil
{


	private static File assignmentFile;
	private Assignments assignments;


	public AssignmentUtil() throws JAXBException
	{
		assignmentFile = new File( ConfigManager.ASSIGNMENTS_FILE_NAME );

		assignments = readAssignmentsFile();
	}

	public Assignment getMyAssignment( int myId ) throws JAXBException
	{
		Assignment a = assignments.getAssignmentById( myId );
		return a;
	}

	private static Assignments readAssignmentsFile() throws JAXBException
	{
		JAXBContext context = JAXBContext.newInstance(Assignments.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Assignments assignments = (Assignments)unmarshaller.unmarshal(assignmentFile);

//		System.out.println( "Got assignments: size()=" + assignments.assignments.size() );

		return assignments;
	}

	private static void writeAssignmentsFile() throws JAXBException
	{
		Assignments as = new Assignments();
		as.addAssignment(1,"one");
		as.addAssignment(2,"two");
		as.addAssignment(3,"three");


		JAXBContext context = JAXBContext.newInstance(Assignments.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		File file = new File("assignments2.xml");

		marshaller.marshal(as, file);
	}

	public void dumpAssignments(PrintStream ps)
	{
		ps.println("Known assignments:");
		ps.println("count=" + assignments.assignments.size() );
		for ( int i=0; i<assignments.assignments.size(); i++ )
		{
			ps.println("Assignment #"+(i+1));
			Assignment a = assignments.assignments.get(i);
			ps.println("  actorCount="+a.getActorCount());
			ps.println("  run="+a.getRun());
		}

	}
}
