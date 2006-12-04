/*
 * <p>Title: SpecimenArrayForm Class </p>
 * <p>Description:This class initializes the fields of specimen array form which is associated
 * with Specimen Array action & asociated request parameters with form. </p>
 * Copyright: Copyright (c) year 2006
 * Company: Washington University, School of Medicine, St. Louis.
 * @version 1.1
 * Created on August 20,2006
 */
package edu.wustl.catissuecore.actionForm;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.domain.SpecimenArray;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.util.SpecimenArrayUtil;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;


/**
 * <p>This class initializes the fields of specimen array form </p>
 * @author gautam_shetty
 * @author ashwin_gupta
 * @version 1.1 
 */
public class SpecimenArrayForm extends ContainerForm
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Specify the specimenArrayTypeId field 
	 */
	private long specimenArrayTypeId = -1;
	
	/**
	 * Specify the createdBy field
	 */
	private long createdBy;

	/**
	 * Specify the specimenClass field
	 */
	private String specimenClass;

	/**
	 * Specify the specimenType field 
	 */
	private String[] specimenTypes;

	/**
	 * Specify the storageContainerId field 
	 */
	private String storageContainer;
	
	/**
	 * Specify the specArrayContentCollection field 
	 */
	private Collection specArrayContentCollection;
	
	/**
	 * Specify the enterSpecimenBy field 
	 */
	private String enterSpecimenBy = "Label";
	
	/**
	 * Specify the createSpecimenArray field 
	 */
	private String createSpecimenArray = "no";
	
	/**
	 * Radio button to choose dropdown or map to select storage container.
	 */
	private int stContSelection = 1;
	/**
	 * Storage container name selected from map
	 */
	private String selectedContainerName;
	/**
	 * Storage pos1 selected from map
	 */
	private String pos1;
	/**
	 * Storage pos2 selected from map
	 */
	private String pos2;
	/**
	 * Storage Id selected from map
	 */
	private String containerId;
	
	private String subOperation;
	
	//private List specimenArrayGridC  

	/** 
	 * @see edu.wustl.common.actionForm.AbstractActionForm#getFormId()
	 */
	public int getFormId() {
		return Constants.SPECIMEN_ARRAY_FORM_ID;
	}
	
	/**
	 * returns specimen array type
	 * @return specimen array type
	 */
	public long getSpecimenArrayTypeId() {
		return specimenArrayTypeId;
	}

	/**
	 * sets the array type.
	 * @param specimenArrayTypeId array type
	 */
	public void setSpecimenArrayTypeId(long specimenArrayType) {
		this.specimenArrayTypeId = specimenArrayType;
	}
	
    /**
     * @return Returns the createdBy.
     */
    public long getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy The createdBy to set.
     */
    public void setCreatedBy(long createdBy)
    {
        this.createdBy = createdBy;
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
     * @return Returns the specimenType.
     */
    public String[] getSpecimenTypes()
    {
        return specimenTypes;
    }

    /**
     * @param specimenTypes The specimenTypes to set.
     */
    public void setSpecimenTypes(String[] specimenTypes)
    {
        this.specimenTypes = specimenTypes;
    }

	/**
	 * @return Returns the storageContainer.
	 */
	public String getStorageContainer() {
		return storageContainer;
	}

	/**
	 * @param storageContainerId The storageContainerId to set.
	 */
	public void setStorageContainer(String storageContainer) {
		this.storageContainer = storageContainer;
	}
	

	/**
	 * @return Returns the specArrayContentCollection.
	 */
	public Collection getSpecArrayContentCollection() {
		return specArrayContentCollection;
	}

	/**
	 * @param specArrayContentCollection The specArrayContentCollection to set.
	 */
	public void setSpecArrayContentCollection(Collection specimenArrayGridContentList) {
		this.specArrayContentCollection = specimenArrayGridContentList;
	}
    
    /**
     * @see edu.wustl.common.actionForm.AbstractActionForm#setAllValues(edu.wustl.common.domain.AbstractDomainObject)
     */
    public void setAllValues(AbstractDomainObject domainObject) {
    	super.setAllValues(domainObject);
    	SpecimenArray specimenArray = (SpecimenArray) domainObject;
    	this.specimenArrayTypeId = specimenArray.getSpecimenArrayType().getId().longValue();
    	this.createdBy = specimenArray.getCreatedBy().getId().longValue();
        StorageContainer container = specimenArray.getStorageContainer();

        if (container != null)
        {
            this.storageContainer = String.valueOf(container
                    .getId());
            this.positionDimensionOne = specimenArray
                    .getPositionDimensionOne().intValue();
            this.positionDimensionTwo = specimenArray
                    .getPositionDimensionTwo().intValue();
            
            this.positionInStorageContainer = container.getStorageType().getName() + " : " 
			+ container.getId() + " Pos(" + this.positionDimensionOne + ","
			+ this.positionDimensionTwo + ")";
        }
        
    	this.specimenClass = specimenArray.getSpecimenArrayType().getSpecimenClass();
    	this.specimenTypes = SpecimenArrayUtil.getSpecimenTypesFromCollection(specimenArray.getSpecimenArrayType().getSpecimenTypeCollection());
    	//this.specArrayContentCollection = SpecimenArrayUtil.getSpecimenGridContentCollection(specimenArray.getSpecimenArrayContentCollection());
    	this.specArrayContentCollection = specimenArray.getSpecimenArrayContentCollection();
    }

	/**
	 * @return Returns the enterSpecimenBy.
	 */
	public String getEnterSpecimenBy() {
		return enterSpecimenBy;
	}

	/**
	 * @param enterSpecimenBy The enterSpecimenBy to set.
	 */
	public void setEnterSpecimenBy(String enterSpecimenBy) {
		this.enterSpecimenBy = enterSpecimenBy;
	}

	/**
	 * @return Returns the subOperation.
	 */
	public String getSubOperation() {
		return subOperation;
	}

	/**
	 * @param subOperation The subOperation to set.
	 */
	public void setSubOperation(String subOperation) {
		this.subOperation = subOperation;
	}

	/**
	 * @param createSpecimenArray The createSpecimenArray to set.
	 */
	public void setCreateSpecimenArray(String createSpecimenArray) {
		this.createSpecimenArray = createSpecimenArray;
	}

	/**
	 * @return Returns the createSpecimenArray.
	 */
	public String getCreateSpecimenArray() {
		return createSpecimenArray;
	}
	
	/**
	 * validate specimen array form level fields & return appropriate message.
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        Validator validator = new Validator();
        try {
            if (operation.equals(Constants.ADD)
                    || operation.equals(Constants.EDIT))
            {
                if (this.specimenArrayTypeId == -1 )
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("array.arrayType")));
                }            	
//            	validate name of array
                if (validator.isEmpty(name))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("array.arrayLabel")));
                }
                
               // validate storage position				
				if(stContSelection==1)
				{
					if (!validator.isNumeric(String.valueOf(positionDimensionOne), 1)
							|| !validator.isNumeric(String.valueOf(positionDimensionTwo), 1)
							|| !validator.isNumeric(String.valueOf(storageContainer), 1))
					{
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
								ApplicationProperties.getValue("array.positionInStorageContainer")));
					}
				}
				else
				{
					if (!validator.isNumeric(String.valueOf(pos1), 1)
							|| !validator.isNumeric(String.valueOf(pos2), 1)
							|| validator.isEmpty(selectedContainerName))
					{
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
								ApplicationProperties.getValue("array.positionInStorageContainer")));
					}
				}
				
				// validate user 
                if (!validator.isValidOption(String.valueOf(createdBy)))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.item.required", ApplicationProperties
                                    .getValue("array.user")));
                }
            }
		} catch (Exception e) {
			Logger.out.error(e.getMessage());
		}
		return errors;
	}
	
	/**
     * This method sets Identifier of Objects inserted by AddNew activity in Form-Bean which initialized AddNew action
     * @param formBeanId - FormBean ID of the object inserted
     *  @param addObjectIdentifier - Identifier of the Object inserted 
     */
	public void setAddNewObjectIdentifier(String addNewFor, Long addObjectIdentifier)
	 {
	     if(addNewFor.equals("specimenArrayTypeId"))
	     {
	    	 setSpecimenArrayTypeId(addObjectIdentifier.longValue());
	     }
	 }
	
	/**
	 * @return Returns the serialVersionUID.
	 */
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}
	/**
	 * @return Returns the containerId.
	 */
	public String getContainerId()
	{
		return containerId;
	}
	/**
	 * @param containerId The containerId to set.
	 */
	public void setContainerId(String containerId)
	{
		this.containerId = containerId;
	}
	/**
	 * @return Returns the pos1.
	 */
	public String getPos1()
	{
		return pos1;
	}
	/**
	 * @param pos1 The pos1 to set.
	 */
	public void setPos1(String pos1)
	{
		this.pos1 = pos1;
	}
	/**
	 * @return Returns the pos2.
	 */
	public String getPos2()
	{
		return pos2;
	}
	/**
	 * @param pos2 The pos2 to set.
	 */
	public void setPos2(String pos2)
	{
		this.pos2 = pos2;
	}
	/**
	 * @return Returns the selectedContainerName.
	 */
	public String getSelectedContainerName()
	{
		return selectedContainerName;
	}
	/**
	 * @param selectedContainerName The selectedContainerName to set.
	 */
	public void setSelectedContainerName(String selectedContainerName)
	{
		this.selectedContainerName = selectedContainerName;
	}
	/**
	 * @return Returns the stContSelection.
	 */
	public int getStContSelection()
	{
		return stContSelection;
	}
	/**
	 * @param stContSelection The stContSelection to set.
	 */
	public void setStContSelection(int stContSelection)
	{
		this.stContSelection = stContSelection;
	}
}