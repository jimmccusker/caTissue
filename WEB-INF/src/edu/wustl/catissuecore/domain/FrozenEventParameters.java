/**
 * <p>Title: FrozenEventParameters Class>
 * <p>Description:  Attributes associated with a freezing event of a specimen. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Apr 7, 2005
 */
package edu.wustl.catissuecore.domain;

import edu.wustl.catissuecore.actionForm.FrozenEventParametersForm;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.util.logger.Logger;

/**
 * Attributes associated with a freezing event of a specimen.
 * @hibernate.joined-subclass table="CATISSUE_FROZEN_EVENT_PARAMETERS"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 * @author Aniruddha Phadnis
 */
public class FrozenEventParameters extends SpecimenEventParameters implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	/**
     * Method applied on specimen to freeze it.
     */
	protected String method;

	/**
     * Returns method applied on specimen to freeze it.
     * @return Method applied on specimen to freeze it.
     * @see #setMethod(String)
     * @hibernate.property name="method" type="string" 
     * column="METHOD" length="50"
     */
	public String getMethod()
	{
		return method;
	}

	/**
     * Sets method applied on specimen to freeze it.
     * @param method method applied on specimen to freeze it.
     * @see #getMethod()
     */
	public void setMethod(String method)
	{
		this.method = method;
	}

	FrozenEventParameters()
	{
		
	}
//	Parameterized constructor
	public FrozenEventParameters(AbstractActionForm abstractForm)
	{
		setAllValues(abstractForm);
	}
	
	/**
     * This function Copies the data from an FrozenEventParametersForm object to a FrozenEventParameters object.
     * @param frozenEventParametersForm An FrozenEventParametersForm object containing the information about the frozenEventParameters.  
     * */
    public void setAllValues(AbstractActionForm abstractForm)
    {
        try
        {
        	FrozenEventParametersForm form 	= (FrozenEventParametersForm) abstractForm;
            this.method = form.getMethod();
           	super.setAllValues(form);
        }
        catch (Exception excp)
        {
            Logger.out.error(excp.getMessage());
        }
    }
	
	
} //class

