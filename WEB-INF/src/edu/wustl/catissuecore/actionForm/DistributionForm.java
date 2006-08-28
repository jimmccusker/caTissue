/**
 * <p>Title: DistributionForm Class</p>
 * <p>Description:  This Class handles the Distribution..
 * <p> It extends the EventParametersForm class.    
 * Copyright:    Copyright (c) 2005
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Jyoti Singh
 * @version 1.00
 * Created on Aug 10, 2005
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

import edu.wustl.catissuecore.domain.CellSpecimen;
import edu.wustl.catissuecore.domain.DistributedItem;
import edu.wustl.catissuecore.domain.Distribution;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.MolecularSpecimen;
import edu.wustl.catissuecore.domain.Quantity;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 *
 * Description:  This Class handles the Distribution..
 */
public class DistributionForm extends SpecimenEventParametersForm
{
	
	//private String fromSite;
	private String toSite;
	
	private int counter=1;
	private String distributionProtocolId;
	private boolean idChange = false;
	private int rowNo=0;
	
	
	
	/**
	 * Map to handle values of all Events
	 */
	protected Map values = new HashMap();
	
	public int getFormId()
	{
		return Constants.DISTRIBUTION_FORM_ID;
	}
	
	public void setAllValues(AbstractDomainObject abstractDomain)
	{
		super.setAllValues(abstractDomain);
		Logger.out.debug("setAllValues of DistributionForm"); 
		Distribution distributionObject = (Distribution)abstractDomain ;
		this.distributionProtocolId = String.valueOf(distributionObject.getDistributionProtocol().getId());
		this.toSite = String.valueOf(distributionObject.getToSite().getId());
		this.activityStatus = Utility.toString(distributionObject.getActivityStatus());
		Logger.out.debug("this.activityStatus "+this.activityStatus);
		Collection distributedItemCollection = distributionObject.getDistributedItemCollection();
		
		if(distributedItemCollection != null)
		{
			values = new HashMap();
			
			Iterator it = distributedItemCollection.iterator();
			int i=1;
			
			while(it.hasNext())
			{
				String key1 = "DistributedItem:"+i+"_id";
				String key2 = "DistributedItem:"+i+"_Specimen_id";
				String key3 = "DistributedItem:"+i+"_quantity";
				String key4 = "DistributedItem:"+i+"_unitSpan";
				String key5 = "DistributedItem:"+i+"_tissueSite";
				String key6 = "DistributedItem:"+i+"_tissueSide";
				String key7 = "DistributedItem:"+i+"_pathologicalStatus";
				String key8 = "DistributedItem:"+i+"_Specimen_className";	
				String key9 = "DistributedItem:"+i+"_availableQty";
				String key10 = "DistributedItem:"+i+"_previousQuantity";
				String key11 = "DistributedItem:"+i+"_Specimen_type";
				
				DistributedItem dItem = (DistributedItem)it.next();
				Specimen specimen = dItem.getSpecimen();
				String unit= getUnitSpan(specimen);
				
				Double quantity = dItem.getQuantity();
				//dItem.setPreviousQty(quantity);
				
				values.put(key1,Utility.toString(dItem.getId()));
				values.put(key2,Utility.toString(specimen.getId()));
				values.put(key3,quantity);
				values.put(key4,unit);
				values.put(key5,specimen.getSpecimenCharacteristics().getTissueSite());
				values.put(key6,specimen.getSpecimenCharacteristics().getTissueSide());
				values.put(key7,specimen.getPathologicalStatus());
				values.put(key8,specimen.getClassName());
				values.put(key9,getAvailableQty(specimen));
				values.put(key10,quantity);
				values.put(key11,specimen.getType());
				
				i++;
			}
			Logger.out.debug("Display Map Values"+values); 
			counter = distributedItemCollection.size();
		}
		
		//At least one row should be displayed in ADD MORE therefore
		if(counter == 0)
			counter = 1;
	}
	
	public void setAllVal(Object obj)
    {
	    edu.wustl.catissuecore.domainobject.Distribution distributionObject = (edu.wustl.catissuecore.domainobject.Distribution)obj;
	    
	    super.setAllVal(distributionObject);
	    
		Logger.out.debug("setAllValues of DistributionForm"); 
		
		//Aniruddha : Fix for bug - 1613
		if(distributionObject.getDistributionProtocol() != null
				&& distributionObject.getDistributionProtocol().getId() != null)
		{
			this.distributionProtocolId = String.valueOf(distributionObject.getDistributionProtocol().getId());
		}
		else
		{
			this.distributionProtocolId = "-1";
		}
		
		if(distributionObject.getToSite() != null
				&& distributionObject.getToSite().getId() != null)
		{
			this.toSite = String.valueOf(distributionObject.getToSite().getId());
		}
		else
		{
			this.toSite = "-1";
		}
		
		this.activityStatus = Utility.toString(distributionObject.getActivityStatus());
		Logger.out.debug("this.activityStatus "+this.activityStatus);
		Collection distributedItemCollection = distributionObject.getDistributedItemCollection();
		
		if(distributedItemCollection != null)
		{
			values = new HashMap();
			
			Iterator it = distributedItemCollection.iterator();
			int i=1;
			
			while(it.hasNext())
			{
				
				String key1 = "DistributedItem:"+i+"_id";
				String key2 = "DistributedItem:"+i+"_Specimen_id";
				String key3 = "DistributedItem:"+i+"_quantity";
				String key4 = "DistributedItem:"+i+"_unitSpan";
				String key5 = "DistributedItem:"+i+"_tissueSite";
				String key6 = "DistributedItem:"+i+"_tissueSide";
				String key7 = "DistributedItem:"+i+"_pathologicalStatus";
				String key8 = "DistributedItem:"+i+"_Specimen_className";	
				String key9 = "DistributedItem:"+i+"_availableQty";
				String key10 = "DistributedItem:"+i+"_previousQuantity";
				String key11 = "DistributedItem:"+i+"_Specimen_type";
				
				edu.wustl.catissuecore.domainobject.DistributedItem dItem = (edu.wustl.catissuecore.domainobject.DistributedItem)it.next();
				
				if(dItem != null)
				{
					edu.wustl.catissuecore.domainobject.Specimen specimen =dItem.getSpecimen();
					String unit= getDomainObjectUnitSpan(specimen);
					
					Double quantity = dItem.getQuantity();
					//dItem.setPreviousQty(quantity);
					
					values.put(key1,Utility.toString(dItem.getId()));
					values.put(key3,Utility.toString(quantity));
					values.put(key4,Utility.toString(unit));
					
					if(specimen != null && specimen.getId() != null)
					{
						values.put(key2,Utility.toString(specimen.getId()));
						values.put(key5,specimen.getSpecimenCharacteristics().getTissueSite());
						values.put(key6,specimen.getSpecimenCharacteristics().getTissueSide());
						values.put(key7,specimen.getSpecimenCharacteristics().getPathologicalStatus());
						values.put(key8,getDomainObjectClassName(specimen));
						values.put(key9,Utility.toString(getDomainObjectAvailableQty(specimen)));
						values.put(key11,specimen.getType());
					}
					else
					{
						values.put(key2,"-1");
					}
					
					values.put(key10,Utility.toString(quantity));
				}
				
				i++;
			}
			Logger.out.debug("Display Map Values"+values); 
			counter = distributedItemCollection.size();
		}
		
		//At least one row should be displayed in ADD MORE therefore
		if(counter == 0)
			counter = 1;
    }
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	{
		//ActionErrors errors = super.validate(mapping, request);
		ActionErrors errors = new ActionErrors();
		Validator validator = new Validator();
		Logger.out.debug("Inside validate function");

		// Mandar 10-apr-06 : bugid :353 
    	// Error messages should be in the same sequence as the sequence of fields on the page.
		if(!validator.isValidOption(distributionProtocolId))
		{
			Logger.out.debug("dist prot");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("distribution.protocol")));
		}

		if (!validator.isValidOption(""+userId))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("distribution.distributedBy")));
		}
		
		
		//  date validation according to bug id  722 and 730
		String errorKey = validator.validateDate(dateOfEvent,true );
		if(errorKey.trim().length() >0  )
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(errorKey,ApplicationProperties.getValue("eventparameters.dateofevent")));
		}
		
		
//		if(!validator.isValidOption(fromSite))
//		{
//			Logger.out.debug("from site");
//			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("distribution.fromSite")));
//		}
		
		if(!validator.isValidOption(toSite))
		{
			Logger.out.debug("to site");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("distribution.toSite")));
		}
		
		//Validations for Add-More Block
		if (this.values.keySet().isEmpty())
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.one.item.required",ApplicationProperties.getValue("distribution.distributedItem")));
		}
		
		Iterator it = this.values.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String)it.next();
			String value = (String)values.get(key);
			
			if(key.indexOf("Specimen_id")!=-1 && !validator.isValidOption( value))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("itemrecord.specimenId")));
			}
			
			
			if(key.indexOf("_quantity")!=-1 )
			{
				if((validator.isEmpty(value) ))
				{
					Logger.out.debug("Quantity empty**************");
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("itemrecord.quantity")));
				}
				else if(!validator.isDouble(value))
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",ApplicationProperties.getValue("itemrecord.quantity")));
				}
			}
			
			//}  if  quantity
		}
		
		return errors;
	}
	/**
	 * @return Returns the distributionProtocolId.
	 */
	public String getDistributionProtocolId()
	{
		return distributionProtocolId;
	}
	/**
	 * @param distributionProtocolId The distributionProtocolId to set.
	 */
	public void setDistributionProtocolId(String distributionProtocolId)
	{
		this.distributionProtocolId = distributionProtocolId;
	}
	
//	/**
//	 * @return fromSite
//	 */ 
//	public String getFromSite() {
//		return fromSite;
//	}
//	
//	/**
//	 * @param fromSite
//	 */
//	public void setFromSite(String fromSite) {
//		this.fromSite = fromSite;
//	}
	
	/**
	 * @return
	 */
	public int getCounter()
	{
		return counter;
	}
	
	/**
	 * @param counter The counter to set.
	 */
	public void setCounter(int counter)
	{
		this.counter = counter;
	}
	public String getToSite() {
		return toSite;
	}
	
	/**
	 * @param toSite
	 */
	public void setToSite(String toSite) {
		this.toSite = toSite;
	}
	
	/**
	 * Associates the specified object with the specified key in the map.
	 * @param key the key to which the object is mapped.
	 * @param value the object which is mapped.
	 */
	public void setValue(String key, Object value)
	{
		if (isMutable())
			values.put(key, value);
	}
	
	/**
	 * Returns the object to which this map maps the specified key.
	 * 
	 * @param key the required key.
	 * @return the object to which this map maps the specified key.
	 */
	public Object getValue(String key)
	{
		return values.get(key);
	}		
	
	/**
	 * @param values The values to set.
	 */
	public void setValues(Map values)
	{
		this.values = values;
	}
	
	/**
	 * @return
	 */
	public Map getValues() {
		return values;
	}
	
	protected void reset()
	{
		//        super.reset();
		//        this.distributionProtocolId = null;
		//        this.fromSite = null;
		//        this.toSite = null;
		//        this.counter =1;
		
	}
	/**
	 * @return Returns the idChange.
	 */
	public boolean isIdChange() {
		return idChange;
	}
	/**
	 * @param idChange The idChange to set.
	 */
	public void setIdChange(boolean idChange) {
		this.idChange = idChange;
	}
	/**
	 * @return Returns the rowNo.
	 */
	public int getRowNo() {
		return rowNo;
	}
	/**
	 * @param rowNo The rowNo to set.
	 */
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	private static String getUnitSpan(Specimen specimen)
	{
		
		if(specimen instanceof TissueSpecimen)
		{
			return Constants.UNIT_GM;
			
		}
		else if(specimen instanceof CellSpecimen)
		{
			return Constants.UNIT_CC;
			
		}
		else if(specimen instanceof MolecularSpecimen)
		{
			return Constants.UNIT_MG;
			
		}
		else if(specimen instanceof FluidSpecimen)
		{
			return Constants.UNIT_ML;
		}
		return null;
	}
	
	/**
	 * This method returns UnitSpan for edu.wustl.catissuecore.domainobject.Specimen
	 */
	private static String getDomainObjectUnitSpan(edu.wustl.catissuecore.domainobject.Specimen specimen)
	{
		
		if(specimen instanceof edu.wustl.catissuecore.domainobject.TissueSpecimen)
		{
			return Constants.UNIT_GM;
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.CellSpecimen)
		{
			return Constants.UNIT_CC;
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.MolecularSpecimen)
		{
			return Constants.UNIT_MG;
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.FluidSpecimen)
		{
			return Constants.UNIT_ML;
		}
		return null;
	}
	
	public Object getAvailableQty(Specimen specimen)
	{
		//Retrieve the Available quantity for the particular specimen
		/* Aniruddha : 16/06/2006 -- TO BE DELETED --
		 * if(specimen instanceof TissueSpecimen)
		{
			TissueSpecimen tissueSpecimen = (TissueSpecimen) specimen;
			Logger.out.debug("tissueSpecimenAvailableQuantityInGram "+tissueSpecimen.getAvailableQuantityInGram());
			return tissueSpecimen.getAvailableQuantityInGram();
			
		}
		else if(specimen instanceof CellSpecimen)
		{
			CellSpecimen cellSpecimen = (CellSpecimen) specimen;
			return cellSpecimen.getAvailableQuantityInCellCount();
			
		}
		else if(specimen instanceof MolecularSpecimen)
		{
			MolecularSpecimen molecularSpecimen = (MolecularSpecimen) specimen;
			return molecularSpecimen.getAvailableQuantityInMicrogram();
			
		}
		else if(specimen instanceof FluidSpecimen)
		{
			FluidSpecimen fluidSpecimen = (FluidSpecimen) specimen;
			return fluidSpecimen.getAvailableQuantityInMilliliter();
		}*/
		
		//Aniruddha : NEEDS TO TAKE CARE OFF CALLING METHOD
		Quantity availableQuantity = specimen.getAvailableQuantity();
		return availableQuantity;
		
		//return null;
	}
	
	/**
	 * This method returns AvailableQunatity for edu.wustl.catissuecore.domainobject.Specimen
	 */
	public Object getDomainObjectAvailableQty(edu.wustl.catissuecore.domainobject.Specimen specimen)
	{
		//Retrieve the Available quantity for the particular specimen
		if(specimen instanceof edu.wustl.catissuecore.domainobject.TissueSpecimen)
		{
			
		    edu.wustl.catissuecore.domainobject.TissueSpecimen tissueSpecimen = (edu.wustl.catissuecore.domainobject.TissueSpecimen) specimen;
			Logger.out.debug("tissueSpecimenAvailableQuantityInGram "+tissueSpecimen.getAvailableQuantityInGram());
			return tissueSpecimen.getAvailableQuantityInGram();
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.CellSpecimen)
		{
		    edu.wustl.catissuecore.domainobject.CellSpecimen cellSpecimen = (edu.wustl.catissuecore.domainobject.CellSpecimen) specimen;
			return cellSpecimen.getAvailableQuantityInCellCount();
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.MolecularSpecimen)
		{
		    edu.wustl.catissuecore.domainobject.MolecularSpecimen molecularSpecimen = (edu.wustl.catissuecore.domainobject.MolecularSpecimen) specimen;
			return molecularSpecimen.getAvailableQuantityInMicrogram();
			
		}
		else if(specimen instanceof edu.wustl.catissuecore.domainobject.FluidSpecimen)
		{
		    edu.wustl.catissuecore.domainobject.FluidSpecimen fluidSpecimen = (edu.wustl.catissuecore.domainobject.FluidSpecimen) specimen;
			return fluidSpecimen.getAvailableQuantityInMilliliter();
		}
		return null;
	}
	
	/**
	 * This method returns ClassName for edu.wustl.catissuecore.domainobject.Specimen
	 */
	public final String getDomainObjectClassName(edu.wustl.catissuecore.domainobject.Specimen specimen)
	{
	    String className = null;
    	
    	if(specimen instanceof edu.wustl.catissuecore.domainobject.CellSpecimen)
    	{
    		className = "Cell";
    	}
    	else if(specimen instanceof edu.wustl.catissuecore.domainobject.MolecularSpecimen)
    	{
    		className = "Molecular";
    	}
    	else if(specimen instanceof edu.wustl.catissuecore.domainobject.FluidSpecimen)
    	{
    		className = "Fluid";
    	}
    	else if(specimen instanceof edu.wustl.catissuecore.domainobject.TissueSpecimen)
    	{
    		className = "Tissue";
    	}
    	
    	return className;
	}
	
	/**
     * This method sets Identifier of Objects inserted by AddNew activity in Form-Bean which initialized AddNew action
     * @param formBeanId - FormBean ID of the object inserted
     *  @param addObjectIdentifier - Identifier of the Object inserted 
     */
	public void setAddNewObjectIdentifier(String addNewFor, Long addObjectIdentifier)
    {
        if(addNewFor.equals("distributionProtocolId"))
        {
            setDistributionProtocolId(addObjectIdentifier.toString());
        }
        else if(addNewFor.equals("userId"))
        {
            setUserId(addObjectIdentifier.longValue());
        }
        else if(addNewFor.equals("toSite"))
        {
            setToSite(addObjectIdentifier.toString());
        }
    }
}