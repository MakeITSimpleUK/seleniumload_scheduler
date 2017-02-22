package uk.co.makeitsimple.infinitemp.selenium.scheduler.domainObjects;

import javax.xml.bind.annotation.*;

/**
 * Created by onTy on 2017-02-19.
 */

//@XmlAccessorType(XmlAccessType.FIELD)
public class Assignment
{

	private int    count;
	private String run;

	@XmlAttribute(name="actorCount")
	public void setActorCount( int count )
	{
		this.count = count;
	}

	@XmlElement(name="run")
	public void setRun( String run )
	{
		this.run = run;
	}

	public int getActorCount()
	{
		return count;
	}

	public String getRun()
	{
		return run;
	}

	@Override
	public String toString()
	{
		return "Assignment run='" + run + "'";
	}

}
