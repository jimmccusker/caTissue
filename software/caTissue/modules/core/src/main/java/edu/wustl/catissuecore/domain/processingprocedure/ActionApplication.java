package edu.wustl.catissuecore.domain.processingprocedure;

import edu.wustl.catissuecore.domain.deintegration.ActionApplicationRecordEntry;
import edu.wustl.catissuecore.domain.Specimen;

import java.io.Serializable;
/**
	*
	**/

public class ActionApplication extends AbstractApplication implements Serializable
{
	/**
	* An attribute to allow serialization of the domain objects
	*/
	private static final long serialVersionUID = 1234567890L;


	/**
	* An associated edu.wustl.catissuecore.domain.processingprocedure.SpecimenProcessingProcedureApplication object
	**/

	private SpecimenProcessingProcedureApplication sppApplication;
	/**
	* Retrieves the value of the sppApplication attribute
	* @return sppApplication
	**/

	public SpecimenProcessingProcedureApplication getSppApplication(){
		return sppApplication;
	}
	/**
	* Sets the value of sppApplication attribute
	**/

	public void setSppApplication(SpecimenProcessingProcedureApplication sppApplication){
		this.sppApplication = sppApplication;
	}

	/**
	* An associated edu.wustl.catissuecore.domain.deintegration.ActionApplicationRecordEntry object
	**/

	private ActionApplicationRecordEntry applicationRecordEntry;
	/**
	* Retrieves the value of the applicationRecordEntry attribute
	* @return applicationRecordEntry
	**/

	public ActionApplicationRecordEntry getApplicationRecordEntry(){
		return applicationRecordEntry;
	}
	/**
	* Sets the value of applicationRecordEntry attribute
	**/

	public void setApplicationRecordEntry(ActionApplicationRecordEntry applicationRecordEntry){
		this.applicationRecordEntry = applicationRecordEntry;
	}


	private Specimen specimen;

	public Specimen getSpecimen() {
		return specimen;
	}
	public void setSpecimen(Specimen specimen) {
		this.specimen = specimen;
	}
	/**
	* Compares <code>obj</code> to it self and returns true if they both are same
	*
	* @param obj
	**/
	public boolean equals(Object obj)
	{
		if(obj instanceof ActionApplication)
		{
			ActionApplication c =(ActionApplication)obj;
			if(getId() != null && getId().equals(c.getId()))
				return true;
		}
		return false;
	}

	/**
	* Returns hash code for the primary key of the object
	**/
	public int hashCode()
	{
		if(getId() != null)
			return getId().hashCode();
		return 0;
	}

}