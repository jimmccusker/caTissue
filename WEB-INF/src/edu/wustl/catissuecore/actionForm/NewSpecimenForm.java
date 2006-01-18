/**
 * <p>Title: NewSpecimenForm Class>
 * <p>Description:  NewSpecimenForm Class is used to encapsulate all the request parameters passed 
 * from New Specimen webpage. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 */

package edu.wustl.catissuecore.actionForm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.domain.Biohazard;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 * NewSpecimenForm Class is used to encapsulate all the request parameters passed 
 * from New Specimen webpage.
 * @author aniruddha_phadnis
 */
public class NewSpecimenForm extends SpecimenForm
{
    private String specimenCollectionGroupId;
    
    /**
     * Identifier of the Parent Speciemen if present.
     */
    private String parentSpecimenId;
    
    /**
     * If "True" then Parent is present else Parent is absent.
     */
    private boolean parentPresent;
    
    /**
     * Anatomic site from which the specimen was derived.
     */
    private String tissueSite;

    /**
     * For bilateral sites, left or right.
     */
    private String tissueSide;

    /**
     * Histopathological character of the specimen 
     * e.g. Non-Malignant, Malignant, Non-Malignant Diseased, Pre-Malignant.
     */
    private String pathologicalStatus;
    
    /**
     * Type of the biohazard.
     */
    private String biohazardType;
    
    /**
     * Name of the biohazard.
     */
    private String biohazardName;
    
    /**
     * Number of biohazard rows.
     */
    private int bhCounter=1;
    
    private Map biohazard = new HashMap();
    
    private String specimenEventParameter;

    /**
     * Returns an identifier of the Parent Speciemen.
     * @return String an identifier of the Parent Speciemen.
     * @see #setParentSpecimenId(String)
     * */
    public String getParentSpecimenId()
    {
        return parentSpecimenId;
    }

    /**
     * Sets an identifier of the Parent Speciemen.
     * @param parentSpecimenId an identifier of the Parent Speciemen.
     * @see #getParentSpecimenId()
     * */
    public void setParentSpecimenId(String parentSpecimenId)
    {
        this.parentSpecimenId = parentSpecimenId;
    }
	
    /**
	 * Associates the specified object with the specified key in the map.
	 * @param key the key to which the object is mapped.
	 * @param value the object which is mapped.
	 */
	public void setBiohazardValue(String key, Object value)
	{
		if (isMutable())
			biohazard.put(key, value);
	}

	/**
	 * Returns the object to which this map maps the specified key.
	 * @param key the required key.
	 * @return the object to which this map maps the specified key.
	 */
	public Object getBiohazardValue(String key)
	{
		return biohazard.get(key);
	}

	/**
	 * @return Returns the values.
	 */
	public Collection getAllBiohazards()
	{
		return biohazard.values();
	}

	/**
	 * @param values
	 * The values to set.
	 */
	public void setBiohazard(Map biohazard)
	{
		this.biohazard = biohazard;
	}

	/**
	 * @param values
	 * Returns the map.
	 */
	public Map getBiohazard()
	{
		return this.biohazard;
	}	
    
    /**
     * @return Returns the pathologicalStatus.
     */
    public String getPathologicalStatus()
    {
        return pathologicalStatus;
    }

    /**
     * @param pathologicalStatus The pathologicalStatus to set.
     */
    public void setPathologicalStatus(String pathologicalStatus)
    {
        this.pathologicalStatus = pathologicalStatus;
    }

    /**
     * @return Returns the specimenCollectionGroupId.
     */
    public String getSpecimenCollectionGroupId()
    {
        return specimenCollectionGroupId;
    }

    /**
     * @param specimenCollectionGroupId The specimenCollectionGroupId to set.
     */
    public void setSpecimenCollectionGroupId(String specimenCollectionGroupId)
    {
        this.specimenCollectionGroupId = specimenCollectionGroupId;
    }

    /**
     * @return Returns the tissueSide.
     */
    public String getTissueSide()
    {
        return tissueSide;
    }

    /**
     * @param tissueSide The tissueSide to set.
     */
    public void setTissueSide(String tissueSide)
    {
        this.tissueSide = tissueSide;
    }

    /**
     * @return Returns the tissueSite.
     */
    public String getTissueSite()
    {
        return tissueSite;
    }

    /**
     * @param tissueSite The tissueSite to set.
     */
    public void setTissueSite(String tissueSite)
    {
        this.tissueSite = tissueSite;
    }

    protected void reset()
    {
//        super.reset();
//    	this.tissueSite = null;
//        this.tissueSide = null;
//        this.pathologicalStatus = null;
//        this.biohazard = new HashMap();
 //   	this.parentPresent = false;
    }
    
  
    
    /**
     * Returns the id assigned to form bean.
     */
    public int getFormId()
    {
        return Constants.NEW_SPECIMEN_FORM_ID;
    }
    
    /**
     * This function Copies the data from an site object to a SiteForm object.
     * @param site An object containing the information about site.  
     */
    public void setAllValues(AbstractDomainObject abstractDomain)
    {
        super.setAllValues(abstractDomain);
        
    	Specimen specimen = (Specimen) abstractDomain;
    	
    	this.parentPresent = false;
    	SpecimenCollectionGroup specimenCollectionGroup = specimen.getSpecimenCollectionGroup();
    	if(specimenCollectionGroup!=null)
    		this.specimenCollectionGroupId = Utility.toString(specimenCollectionGroup.getId());
    	
    	if(specimen.getParentSpecimen() != null)
    	{
    		Logger.out.debug("ParentSpecimen : -- "+specimen.getParentSpecimen());
    		this.parentSpecimenId = String.valueOf(specimen.getParentSpecimen().getId());
    		this.parentPresent = true;
    	}
    	
        SpecimenCharacteristics characteristic = specimen.getSpecimenCharacteristics();
        this.pathologicalStatus = characteristic.getPathologicalStatus();
        this.tissueSide = characteristic.getTissueSide();
        this.tissueSite = characteristic.getTissueSite();
        
        Collection biohazardCollection = specimen.getBiohazardCollection();
        bhCounter = 1;
        
        if(biohazardCollection != null && biohazardCollection.size() != 0)
        {
        	biohazard = new HashMap();
        	
        	int i=1;
        	
        	Iterator it = biohazardCollection.iterator();
        	while(it.hasNext())
        	{
        		String key1 = "Biohazard:" + i + "_type";
				String key2 = "Biohazard:" + i + "_systemIdentifier";
				String key3 = "Biohazard:" + i + "_persisted";
				
				Biohazard hazard = (Biohazard)it.next();
				
				biohazard.put(key1,hazard.getType());
				biohazard.put(key2,hazard.getId());
				
				//boolean for showing persisted value
				biohazard.put(key3,"true");
				
				i++;
        	}
        	
        	bhCounter = biohazardCollection.size();
        }
    }
    
    public void setAllVal(Object obj)
    {
        edu.wustl.catissuecore.domainobject.Specimen specimen=(edu.wustl.catissuecore.domainobject.Specimen) obj;
        super.setAllVal(specimen);
        
    	this.parentPresent = false;
    	edu.wustl.catissuecore.domainobject.SpecimenCollectionGroup specimenCollectionGroup = specimen.getSpecimenCollectionGroup();
    	if(specimenCollectionGroup!=null)
    		this.specimenCollectionGroupId = String.valueOf(specimenCollectionGroup.getId());
    	
    	if(specimen.getParentSpecimen() != null)
    	{
    		Logger.out.debug("ParentSpecimen : -- "+specimen.getParentSpecimen());
    		this.parentSpecimenId = String.valueOf(specimen.getParentSpecimen().getId());
    		this.parentPresent = true;
    	}
    	
    	edu.wustl.catissuecore.domainobject.SpecimenCharacteristics characteristic = specimen.getSpecimenCharacteristics();
        this.pathologicalStatus = characteristic.getPathologicalStatus();
        this.tissueSide = characteristic.getTissueSide();
        this.tissueSite = characteristic.getTissueSite();
        
        Collection biohazardCollection = specimen.getBiohazardCollection();
        bhCounter = 1;
        
        if(biohazardCollection != null && biohazardCollection.size() != 0)
        {
        	biohazard = new HashMap();
        	
        	int i=1;
        	
        	Iterator it = biohazardCollection.iterator();
        	while(it.hasNext())
        	{
        		String key1 = "Biohazard:" + i + "_type";
				String key2 = "Biohazard:" + i + "_systemIdentifier";
				String key3 = "Biohazard:" + i + "_persisted";
				
				edu.wustl.catissuecore.domainobject.Biohazard hazard = (edu.wustl.catissuecore.domainobject.Biohazard)it.next();
				biohazard.put(key1,hazard.getType());
				biohazard.put(key2,hazard.getId());
				
				//boolean for showing persisted value
				biohazard.put(key3,"true");
				
				i++;
        	}
        	
        	bhCounter = biohazardCollection.size();
        }
    }
    
	/**
	 * @return Returns the biohazardType.
	 */
	public String getBiohazardType()
	{
		return biohazardType;
	}
	/**
	 * @param biohazardType The biohazardType to set.
	 */
	public void setBiohazardType(String biohazardType)
	{
		this.biohazardType = biohazardType;
	}
	
	/**
	 * @return Returns the biohazardName.
	 */
	public String getBiohazardName()
	{
		return biohazardName;
	}
	/**
	 * @param biohazardName The biohazardName to set.
	 */
	public void setBiohazardName(String biohazardName)
	{
		this.biohazardName = biohazardName;
	}	
	
	/**
     * Overrides the validate method of ActionForm.
     * */
     public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
     {
         ActionErrors errors = super.validate(mapping,request);
         Validator validator = new Validator();

         try
         {
             if (operation.equals(Constants.ADD) || operation.equals(Constants.EDIT))
             {
             	if (specimenCollectionGroupId.equals("-1"))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("specimen.specimenCollectionGroupId")));
                }
             	
             	if(parentPresent && !validator.isValidOption(parentSpecimenId))
             	{
             		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("createSpecimen.parent")));
             	}
             	
             	if (tissueSite.equals("-1"))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("specimen.tissueSite")));
                }
             	
             	if (tissueSide.equals("-1"))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("specimen.tissueSide")));
                }
             	
             	if (pathologicalStatus.equals("-1"))
                {
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("specimen.pathologicalStatus")));
                }

             	//Validations for Biohazard Add-More Block
                String className = "Biohazard:";
                String key1 = "_type";
                String key2 = "_" + Constants.SYSTEM_IDENTIFIER;
                String key3 = "_persisted";
                int index = 1;
                
                while(true)
                {
                	String keyOne = className + index + key1;
					String keyTwo = className + index + key2;
					String keyThree = className + index + key3;
					
                	String value1 = (String)biohazard.get(keyOne);
                	String value2 = (String)biohazard.get(keyTwo);
                	String value3 = (String)biohazard.get(keyThree);
                	
                	if(value1 == null || value2 == null || value3 == null)
                	{
                		break;
                	}
                	else if(!validator.isValidOption(value1) && !validator.isValidOption(value2))
                	{
                		biohazard.remove(keyOne);
                		biohazard.remove(keyTwo);
                		biohazard.remove(keyThree);
                	}
                	else if((validator.isValidOption(value1) && !validator.isValidOption(value2)) 
                			|| (!validator.isValidOption(value1) && validator.isValidOption( value2)))   		
                	{
                		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.newSpecimen.biohazard.missing",ApplicationProperties.getValue("newSpecimen.msg")));
                		break;
                	}
                	index++;
                }
             }
         }
         catch(Exception excp)
         {
             Logger.out.error(excp.getMessage());
         }
         return errors;
      }
     
	/**
	 * @return Returns the bhCounter.
	 */
	public int getBhCounter()
	{
		return bhCounter;
	}
	
	/**
	 * @param bhCounter The bhCounter to set.
	 */
	public void setBhCounter(int bhCounter)
	{
		this.bhCounter = bhCounter;
	}
	
	
	/**
	 * @return Returns the parentPresent.
	 */
	public boolean isParentPresent()
	{
		return parentPresent;
	}
	/**
	 * @param parentPresent The parentPresent to set.
	 */
	public void setParentPresent(boolean parentPresent)
	{
		this.parentPresent = parentPresent;
	}
	
	/**
	 * @return Returns the specimenEventParameter.
	 */
	public String getSpecimenEventParameter()
	{
		return specimenEventParameter;
	}
	
	/**
	 * @param specimenEventParameter The specimenEventParameter to set.
	 */
	public void setSpecimenEventParameter(String specimenEventParameter)
	{
		this.specimenEventParameter = specimenEventParameter;
	}
	

}