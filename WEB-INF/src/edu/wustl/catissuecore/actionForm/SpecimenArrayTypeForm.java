/*
 * <p>Title: SpecimenArrayTypeForm Class </p>
 * <p>Description:This class initializes the fields of ArrayType form which is associated with Array Type Action
 *  & asociated request parameters with form. </p>
 * Copyright: Copyright (c) year 2006
 * Company: Washington University, School of Medicine, St. Louis.
 * @version 1.1
 * Created on July 24,2006
 */

package edu.wustl.catissuecore.actionForm;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.domain.SpecimenArrayType;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 * This class initializes the fields of ArrayType form which is associated with Array Type Action
 * & asociated request parameters with form.
 * @author Ashwin Gupta 
 * @author gautam_shetty
 * @see edu.wustl.common.actionForm.AbstractActionForm
 */
public class SpecimenArrayTypeForm extends AbstractActionForm
{
	
	/**
	 * default serial version ID 
	 */
	private static final long serialVersionUID = -4486179745074687647L;

	/**
	 * name of array type  
	 */
	protected String name;

    /**
     * Specimen class to which array type be associated 
     */
	protected String specimenClass;

    /**
     * Specimen type to which array type be associated 
     */
	protected String[] specimenTypes;

    /**
     * one dimension capacity for array type 
     */
	protected int oneDimensionCapacity;

    /**
     * two dimension capacity for array type 
     */
	protected int twoDimensionCapacity;

    /**
     * comments to be put for array type 
     */
	protected String comment;

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the oneDimensionCapacity.
     */
    public int getOneDimensionCapacity()
    {
        return oneDimensionCapacity;
    }

    /**
     * @param oneDimensionCapacity The oneDimensionCapacity to set.
     */
    public void setOneDimensionCapacity(int oneDimensionCapacity)
    {
        this.oneDimensionCapacity = oneDimensionCapacity;
    }

    /**
     * @return Returns the specimenClass.
     */
    public String getSpecimenClass()
    {
        return specimenClass;
    }

    /**
     * @param specimenClass The specimenClass to set.
     */
    public void setSpecimenClass(String specimenClass)
    {
        this.specimenClass = specimenClass;
    }

    /**
     * @return Returns the specimenTypes.
     */
    public String[] getSpecimenTypes()
    {
        return specimenTypes;
    }

    /**
     * @param specimenTypes The specimenTypes to set.
     */
    public void setSpecimenTypes(String specimenType[])
    {
        this.specimenTypes = specimenType;
    }

    /**
     * @return Returns the twoDimensionCapacity.
     */
    public int getTwoDimensionCapacity()
    {
        return twoDimensionCapacity;
    }

    /**
     * @param twoDimensionCapacity The twoDimensionCapacity to set.
     */
    public void setTwoDimensionCapacity(int twoDimensionCapacity)
    {
        this.twoDimensionCapacity = twoDimensionCapacity;
    }

    /**
     * @return Returns the comment.
     */
    public String getComment()
    {
        return comment;
    }

    /**
     * @param comment The comments to set.
     */
    public void setComment(String comment)
    {
        this.comment = comment;
    }

	/* (non-Javadoc)
	 * @see edu.wustl.common.actionForm.AbstractActionForm#getFormId()
	 */
	public int getFormId() {
		return Constants.SPECIMEN_ARRAY_TYPE_FORM_ID;
	}

	/* (non-Javadoc)
	 * @see edu.wustl.common.actionForm.AbstractActionForm#setAllValues(edu.wustl.common.domain.AbstractDomainObject)
	 */
	public void setAllValues(AbstractDomainObject domainObject) {
		if (domainObject instanceof SpecimenArrayType) {
			SpecimenArrayType arrayType = (SpecimenArrayType) domainObject;
	        this.id = arrayType.getId().longValue();
	        this.name = arrayType.getName();
	        this.specimenClass = arrayType.getSpecimenClass();
	        this.oneDimensionCapacity = arrayType.getCapacity().getOneDimensionCapacity().intValue();
	        this.twoDimensionCapacity = arrayType.getCapacity().getTwoDimensionCapacity().intValue();
	        this.comment = arrayType.getComment();
	        Collection specimenTypeCollection = arrayType.getSpecimenTypeCollection();
	        
	        if ((specimenTypeCollection != null) && (!specimenTypeCollection.isEmpty())) {
	        	this.specimenTypes = new String[specimenTypeCollection.size()];
	        	String specimenTypeStr = null;
	        	int i = 0;
	        	for (Iterator iter = specimenTypeCollection.iterator(); iter.hasNext();i++) {
					specimenTypeStr = (String) iter.next();
					specimenTypes[i] = specimenTypeStr;
				}
	        }
		}
	}
	
	/**
	 * @param domainObject domain object
	 */
	private void setAllVa(Object domainObject) {
		SpecimenArrayType arrayType = (SpecimenArrayType) domainObject;
        this.systemIdentifier = arrayType.getId().longValue();
        this.name = arrayType.getName();
        this.specimenClass = arrayType.getSpecimenClass();
        this.oneDimensionCapacity = arrayType.getCapacity().getOneDimensionCapacity().intValue();
        this.twoDimensionCapacity = arrayType.getCapacity().getTwoDimensionCapacity().intValue();
        this.comment = arrayType.getComment();
        Collection specimenTypeCollection = arrayType.getSpecimenTypeCollection();
        
        if ((specimenTypeCollection != null) && (!specimenTypeCollection.isEmpty())) {
        	this.specimenTypes = new String[specimenTypeCollection.size()];
        	String specimenTypeStr = null;
        	int i = 0;
        	for (Iterator iter = specimenTypeCollection.iterator(); iter.hasNext();i++) {
				specimenTypeStr = (String) iter.next();
				specimenTypes[i] = specimenTypeStr;
			}
        }
	}

	/* (non-Javadoc)
	 * @see edu.wustl.common.actionForm.AbstractActionForm#reset()
	 */
	protected void reset() {
	}
	
	/**
	 * validate specimen array type form level fields & return appropriate message.
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        Validator validator = new Validator();
        try {
            if (operation.equals(Constants.ADD)
                    || operation.equals(Constants.EDIT))
            {
//            	validate name of array type
                if (validator.isEmpty(name))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("arrayType.name")));
                }
//            	 validate specimen class of array type
                if (!validator.isValidOption(specimenClass))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("arrayType.specimenClass")));
                }
//              validate specimen type in array type
    	        if ((specimenTypes != null) && (specimenTypes.length > 0)) {
    	        	for (int i = 0; i < specimenTypes.length; i++) {
    	        		if (specimenTypes[i] != null) {
    	                    if (!validator.isValidOption(specimenTypes[i]))
    	                    {
    	                        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
    	                                "errors.item.selected", ApplicationProperties
    	                                        .getValue("arrayType.specimenType")));
    	                        break;
    	                    }
    	        		}
    				}
    	        } else {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("arrayType.specimenType")));
    	        }
                
//              validate one dimension capacity of array type is empty or not numeric
/*                if(validator.isEmpty(oneDimensionCapacity))
                {
	                  errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
	                  "errors.item.required", ApplicationProperties
	                          .getValue("arrayType.oneDimensionCapacity")));
                } else
*/                
                if(!validator.isNumeric(String.valueOf(oneDimensionCapacity),1) || !validator.isNumeric(String.valueOf(twoDimensionCapacity),1)) {
	                  errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
	                  "errors.item.format", ApplicationProperties
	                          .getValue("arrayType.capacity")));
                }
            }
		} catch (Exception e) {
			Logger.out.error(e.getMessage());
		}
		return errors;
	}
	
	
}