package uk.co.makeitsimple.infinitemp.selenium.scheduler.domainObjects;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by onTy on 2017-02-20.
 */

//@XmlRootElement(name="assignments")
@XmlRootElement
public class Assignments
{
	//@XmlElementWrapper(name="assignments")
	@XmlElement(name="assignment")
	public List<Assignment> assignments;

	public Assignments()
	{
		this.assignments = new ArrayList<>();
	}

	public void addAssignment( int count, String run )
	{
		Assignment a = new Assignment();
		a.setActorCount(count);
		a.setRun(run);

		this.assignments.add(a);
	}

	public Assignment getAssignmentById( int id )
	{
		int current = 0;
		for ( Assignment a : assignments )
		{
			current += a.getActorCount();
			if ( id <= current )
				return a;
		}

		// not found
		return null;
	}

}
