/**
 * <p>Title: ProcedureEventParameters Class</p>
 * <p>Description: Attributes associated with a customized procedure that is applied on a specimen to process it.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 */

package edu.wustl.catissuecore.domain;

/**
 * Attributes associated with a customized procedure that is applied on a specimen to process it.
 * @hibernate.joined-subclass table="CATISSUE_PROCEDURE_EVENT_PARAMETERS"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 */
public class ProcedureEventParameters extends SpecimenEventParameters
		implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	/**
	 * URL to the document that describes detail information of customized process.
	 */
	protected String url;
	
	/**
	 * Name of the customized procedure.
	 */
	protected String name;

	/**
	 * Returns the url of the procedure document.
	 * @hibernate.property name="url" type="string" column="URL" length="200"
	 * not-null="true"
	 * @return url of the procedure document.
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Sets the url of the procedure document.
	 * @param url url of the procedure document.
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * Returns the name of the procedure.
	 * @hibernate.property name="name" type="string" column="NAME" length="50"
	 * not-null="true"
	 * @return name of the procedure.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the name of the procedure document.
	 * @param name name of the procedure.
	 */
	public void setName(String name)
	{
		this.name = name;
	}
}