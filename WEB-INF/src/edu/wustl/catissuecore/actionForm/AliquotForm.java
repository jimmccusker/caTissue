/**
 * <p>Title: AliquotForm Class>
 * <p>Description:  This Class is used to encapsulate all the request parameters passed 
 * from Aliquot.jsp page. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on May 11, 2006
 */
package edu.wustl.catissuecore.actionForm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;


/**
 * This Class is used to encapsulate all the request parameters passed from Aliquot.jsp page.
 * @author aniruddha_phadnis
 * */
public class AliquotForm extends AbstractActionForm
{
	/**
     * An label of a specimen.
     */
	private String specimenLabel = "";
	
	/**
     * An label of a specimen.
     */
	private String specimenID;
    
    /**
     * A number that tells how many aliquots to be created.
     */
    private String noOfAliquots = "";
    
    /**
     * An identifier of Specimen Collection Group.
     */
    private long spCollectionGroupId;
    
    /**
     * A class of the specimen. e.g. Tissue, Molecular, Cell, Fluid
     */
    private String specimenClass;
    
    /**
     * A type of the specimen.
     */
    private String type;
    
    /**
     * Anatomic site from which the specimen was derived.
     */
    private String tissueSite;
    
    /**
     * A bilateral site. e.g. Left or Right.
     */
    private String tissueSide;
    
    /**
     * Histopathological character of the specimen.
     */
    private String pathologicalStatus;
    
    /**
     * Available quantity of the parent specimen.
     */
    private String initialAvailableQuantity;
    
    /**
     * Available quantity of the parent specimen after creating aliquots.
     */
    private String availableQuantity;
    
    /**
     * Concentration of the molecular specimen.
     */
    private String concentration;
    
    /**
     * Initial quantity per aliquot.
     */
    private String quantityPerAliquot = "";
    
    /**
     * Barcode assigned of the parent specimen.
     */
    private String barcode = "";
    
    /**
	 * Radio button to choose barcode/specimen identifier
	 */
	private String checkedButton = "1";
    
    /**
     * A map that contains distinguished fields (quantity,barcode,location) per aliquot.
     */
    private Map aliquotMap = new HashMap();
    
    /**
     * decides whether to store all aliquotes in the same container
     */
    private boolean aliqoutInSameContainer = false;
    
    /**
     * identifies the button clicked
     */
    private String buttonClicked = "";
    /**
	 * Returns the map that contains distinguished fields per aliquots.
	 * @return The map that contains distinguished fields per aliquots.
	 * @see #setAliquotMap(Map)
	 */
	public Map getAliquotMap()
	{
		return aliquotMap;
	}
	
	/**
     * Sets the map of distinguished fields of aliquots.
     * @param aliquotMap A map of distinguished fields of aliquots.
     * @see #getAliquotMap()
     */
	public void setAliquotMap(Map aliquotMap)
	{
		this.aliquotMap = aliquotMap;
	}
	
	/**
	 * Associates the specified object with the specified key in the map.
	 * @param key the key to which the object is mapped.
	 * @param value the object which is to be mapped.
	 */
	public void setValue(String key, Object value)
	{
		aliquotMap.put(key, value);
	}

	/**
	 * Returns the object to which this map maps the specified key.
	 * @param key the required key.
	 * @return the object to which this map maps the specified key.
	 */
	public Object getValue(String key)
	{
		return aliquotMap.get(key);
	}
	
	/**
	 * Returns the available quantity of parent specimen after creating aliquots.
	 * @return The available quantity of parent specimen after creating aliquots.
	 * @see #setAvailableQuantity(String)
	 */
	public String getAvailableQuantity()
	{
		return availableQuantity;
	}
	
	/**
     * Sets the available quantity of parent specimen after creating aliquots.
     * @param availableQuantity The available quantity of parent specimen after creating aliquots.
     * @see #getAvailableQuantity()
     */
	public void setAvailableQuantity(String availableQuantity)
	{
		this.availableQuantity = availableQuantity;
	}
	
	/**
	 * Returns the concentration of parent specimen.
	 * @return The concentration of parent specimen.
	 * @see #setConcentration(String)
	 */
	public String getConcentration()
	{
		return concentration;
	}
	
	/**
     * Sets the concentration of parent specimen.
     * @param concentration The concentration of parent specimen.
     * @see #getConcentration()
     */
	public void setConcentration(String concentration)
	{
		this.concentration = concentration;
	}
	
	/**
	 * Returns the no. of aliquots to be created.
	 * @return The no. of aliquots to be created.
	 * @see #setNoOfAliquots(String)
	 */
	public String getNoOfAliquots()
	{
		return noOfAliquots;
	}
	
	/**
     * Sets the no. of aliquots to be created.
     * @param noOfAliquots The no. of aliquots to be created.
     * @see #getNoOfAliquots()
     */
	public void setNoOfAliquots(String noOfAliquots)
	{
		this.noOfAliquots = noOfAliquots;
	}
	
	/**
	 * Returns the pathological status of parent specimen.
	 * @return The pathological status of parent specimen.
	 * @see #setPathologicalStatus(String)
	 */
	public String getPathologicalStatus()
	{
		return pathologicalStatus;
	}
	
	/**
     * Sets the pathological status of parent specimen.
     * @param pathologicalStatus The pathological status of parent specimen.
     * @see #getPathologicalStatus()
     */
	public void setPathologicalStatus(String pathologicalStatus)
	{
		this.pathologicalStatus = pathologicalStatus;
	}
	
	/**
	 * Returns the specimen collection group identifier of parent specimen.
	 * @return The specimen collection group identifier of parent specimen.
	 * @see #setSpCollectionGroupId(long)
	 */
	public long getSpCollectionGroupId()
	{
		return spCollectionGroupId;
	}
	
	/**
     * Sets the specimen collection group identifier of parent specimen.
     * @param spCollectionGroupId The specimen collection group identifier of parent specimen.
     * @see #getSpCollectionGroupId()
     */
	public void setSpCollectionGroupId(long spCollectionGroupId)
	{
		this.spCollectionGroupId = spCollectionGroupId;
	}
	
	/**
	 * Returns the specimen class of parent specimen.
	 * @return The specimen class of parent specimen.
	 * @see #setSpecimenClass(String)
	 */
	public String getSpecimenClass()
	{
		return specimenClass;
	}
	
	/**
     * Sets the specimen class of parent specimen.
     * @param specimenClass The specimen class of parent specimen.
     * @see #getSpecimenClass()
     */
	public void setSpecimenClass(String specimenClass)
	{
		this.specimenClass = specimenClass;
	}
	
	/**
	 * Returns the specimen label of parent specimen.
	 * @return The specimen label of parent specimen.
	 * @see #setSpecimenLabel(String)
	 */
	public String getSpecimenLabel()
	{
		return specimenLabel;
	}
	
	/**
     * Sets the specimen label of parent specimen.
     * @param specimenLabel The specimen label of parent specimen.
     * @see #getSpecimenLabel()
     */
	public void setSpecimenLabel(String specimenLabel)
	{
		this.specimenLabel = specimenLabel;
	}
	
	/**
	 * Returns the tissue side of parent specimen.
	 * @return The tissue side of parent specimen.
	 * @see #setTissueSide(String)
	 */
	public String getTissueSide()
	{
		return tissueSide;
	}
	
	/**
     * Sets the tissue side of parent specimen.
     * @param tissueSide The tissue side of parent specimen.
     * @see #getTissueSide()
     */
	public void setTissueSide(String tissueSide)
	{
		this.tissueSide = tissueSide;
	}
	
	/**
	 * Returns the tissue site of parent specimen.
	 * @return The tissue site of parent specimen.
	 * @see #setTissueSite(String)
	 */
	public String getTissueSite()
	{
		return tissueSite;
	}
	
	/**
     * Sets the tissue site of parent specimen.
     * @param tissueSite The tissue site of parent specimen.
     * @see #getTissueSite()
     */
	public void setTissueSite(String tissueSite)
	{
		this.tissueSite = tissueSite;
	}
	
	/**
	 * Returns the type of parent specimen.
	 * @return The type of parent specimen.
	 * @see #setType(String)
	 */
	public String getType()
	{
		return type;
	}
	
	/**
     * Sets the type of parent specimen.
     * @param type The type of parent specimen.
     * @see #getTissueSite()
     */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
     * Returns the identifier assigned to form bean.
     * @return The identifier assigned to form bean.
     */
    public int getFormId()
    {
        return Constants.ALIQUOT_FORM_ID;
    }
    
    /**
     * This method resets the form fields.
     */
    public void reset()
    {
    }
    
    /**
     * This method Copies the data from an Specimen object to a AliquotForm object.
     * @param abstractDomain An object of Specimen class.  
     */
    public void setAllValues(AbstractDomainObject abstractDomain)
    {
    }
    
    /**
     * Overrides the validate method of ActionForm.
     */
     public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
     {
         ActionErrors errors = new ActionErrors();
         
         if(Constants.PAGEOF_ALIQUOT_SUMMARY.equals(request.getParameter(Constants.PAGEOF)))
         {
         	Iterator keyIterator = aliquotMap.keySet().iterator();
         	
         	while(keyIterator.hasNext())
         	{
         		Validator validator = new Validator();
         		String key = (String)keyIterator.next();
         		
         		if(key.endsWith("_quantity"))
         		{
         			String value = (String)aliquotMap.get(key);
         			try
					{
	         			value = new BigDecimal(value).toPlainString();
	         			
	         			if(Utility.isQuantityDouble(specimenClass,type))
	        			{
	        		        if(!validator.isDouble(value,true))
	        		        {
	        		        	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.format",ApplicationProperties.getValue("specimen.quantity")));
	        		        	break;
	        		        }
	        			}
	        			else
	        			{
	        				if(!validator.isNumeric(value,0))
	        		        {
	        		        	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.format",ApplicationProperties.getValue("specimen.quantity")));
	        		        	break;
	        		        }
	        			}
					}
         			catch (NumberFormatException exp)
			        {    		  
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.format",ApplicationProperties.getValue("specimen.quantity")));
					}
         		}
         		else if(key.endsWith("_label"))
         		{
         			String value = (String)aliquotMap.get(key);
         			
         			if(validator.isEmpty(value))
         			{
         				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.required",ApplicationProperties.getValue("specimen.label")));
         			}
         		}
         		else if(key.indexOf("_positionDimension") != -1)
         		{
         			String value = (String)aliquotMap.get(key);
         			 if(value!=null && !value.trim().equals("") && !validator.isDouble(value))
     		        {
     		        	errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.format",ApplicationProperties.getValue("specimen.positionInStorageContainer")));
     		            break;
     		        }
         		}
         	}
         }
         
                  
         return errors;
     }
     
     /**
 	 * Returns the initial quantity per aliquot.
 	 * @return The initial quantity per aliquot.
 	 * @see #setQuantityPerAliquot(String)
 	 */
	public String getQuantityPerAliquot()
	{
		return quantityPerAliquot;
	}
	
	/**
     * Sets the initial quantity per aliquot.
     * @param quantityPerAliquot The initial quantity per aliquot.
     * @see #getQuantityPerAliquot()
     */
	public void setQuantityPerAliquot(String quantityPerAliquot)
	{
		this.quantityPerAliquot = quantityPerAliquot;
	}
	
	/**
	 * Returns the available quantity of parent specimen.
	 * @return The available quantity of parent specimen.
	 * @see #setInitialAvailableQuantity(String)
	 */
	public String getInitialAvailableQuantity()
	{
		return initialAvailableQuantity;
	}
	
	/**
     * Sets the available quantity of parent specimen.
     * @param initialAvailableQuantity The available quantity of parent specimen.
     * @see #getInitialAvailableQuantity()
     */
	public void setInitialAvailableQuantity(String initialAvailableQuantity)
	{
		this.initialAvailableQuantity = initialAvailableQuantity;
	}
	
	/**
     * Returns the barcode of the parent specimen. 
     * @return String the barcode of the parent specimen.
     * @see #setBarcode(String)
     */
    public String getBarcode()
    {
        return barcode;
    }
    
    /**
     * Sets the barcode of the parent specimen.
     * @param barcode The barcode of the parent specimen.
     * @see #getBarcode()
     */
    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }
    
    /**
     * Returns the value of selected radio button. 
     * @return String the value of selected radio button.
     * @see #setCheckedButton(String)
     */
	public String getCheckedButton()
	{
		return checkedButton;
	}

	/**
     * Returns the value of selected radio button. 
     * @param checkedButton The value of selected radio button.
     * @see #getCheckedButton()
     */
	public void setCheckedButton(String checkedButton)
	{
		this.checkedButton = checkedButton;
	}
	/**
	 * @return Returns the specimenID.
	 */
	public String getSpecimenID()
	{
		return specimenID;
	}
	/**
	 * @param specimenID The specimenID to set.
	 */
	public void setSpecimenID(String specimenID)
	{
		this.specimenID = specimenID;
	}
	/**
	 * @return Returns the aliqoutInSameContainer.
	 */
	public boolean isAliqoutInSameContainer()
	{
		return aliqoutInSameContainer;
	}
	/**
	 * @param aliqoutInSameContainer The aliqoutInSameContainer to set.
	 */
	public void setAliqoutInSameContainer(boolean aliqoutInSameContainer)
	{
		this.aliqoutInSameContainer = aliqoutInSameContainer;
	}
	/**
	 * @return Returns the buttonClicked.
	 */
	public String getButtonClicked()
	{
		return buttonClicked;
	}
	/**
	 * @param buttonClicked The buttonClicked to set.
	 */
	public void setButtonClicked(String buttonClicked)
	{
		this.buttonClicked = buttonClicked;
	}
}
