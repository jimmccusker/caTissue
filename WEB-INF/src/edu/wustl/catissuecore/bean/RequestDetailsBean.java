/**
 * <p>Title: RequestDetailsBean Class>
 * <p>Description:	This class contains attributes to display on RequestDetails.jsp Page</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ashish Gupta
 * @version 1.00
 * Created on Oct 10,2006
 */
package edu.wustl.catissuecore.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



public class RequestDetailsBean implements Serializable
{	
	/**
	 * The requested Item - Specimen, Array or Pathological case.
	 */
	private String requestedItem ;
	/**
	 * The item requested for.
	 */
	private String requestFor ;
	/**
	 * The requested quantity.
	 */
	private String requestedQty ;
	/**
	 * The available quantity.
	 */
	private String availableQty ;
	/**
	 * The assigned status.
	 */
	private String assignedStatus;
	/**
	 * The possible status List.
	 */
	private List itemsStatusList = null;
	/**
	 * The requested item class.
	 */
	private String className ;
	/**
	 * The requested item type.
	 */
	private String type ;
	/**
	 * The description of the order item.
	 */
	private String description = "";
	/**
	 * Whether orderItem object is instanceof ExistingSpecimen or Derived Specimen or PathologicalCase.
	 */
	private String instanceOf;
	/**
	 * List of specimens(both parent and derivative specimens).
	 */
	private Collection specimenList = null;
	/**
	 * The order item id.
	 */
	private String orderItemId;
	/**
	 * The assigned quantity.
	 */
	private String assignedQty ;
	/**
	 * The specimen associated with the order item.
	 */
	private String specimenId = "";
	/**
	 * Specimen Coll group id associated with the Specimen Collection Group. 
	 */
	private String specimenCollGroupId = "";
	
	/**
	 * @return the className
	 */	
	public String getClassName()
	{
		return className;
	}
	
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className)
	{
		this.className = className;
	}


	
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}


	
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}


	/**
	 * @return the itemsStatusList
	 */
	public List getItemsStatusList()
	{
		return itemsStatusList;
	}

	
	/**
	 * @param itemsStatusList the itemsStatusList to set
	 */
	public void setItemsStatusList(List itemsStatusList)
	{
		this.itemsStatusList = itemsStatusList;
	}

	/**
	 * @return the assignedStatus
	 */
	public String getAssignedStatus()
	{
		return assignedStatus;
	}
	
	/**
	 * @param assignedStatus the assignedStatus to set
	 */
	public void setAssignedStatus(String assignedStatus)
	{
		this.assignedStatus = assignedStatus;
	}
	
	/**
	 * @return the availableQty
	 */
	public String getAvailableQty()
	{
		return availableQty;
	}
	
	/**
	 * @param availableQty the availableQty to set
	 */
	public void setAvailableQty(String availableQty)
	{
		this.availableQty = availableQty;
	}
	
	/**
	 * @return the requestedItem
	 */
	public String getRequestedItem()
	{
		return requestedItem;
	}
	
	/**
	 * @param requestedItem the requestedItem to set
	 */
	public void setRequestedItem(String requestedItem)
	{
		this.requestedItem = requestedItem;
	}
	
	/**
	 * @return the requestedQty
	 */
	public String getRequestedQty()
	{
		return requestedQty;
	}
	
	/**
	 * @param requestedQty the requestedQty to set
	 */
	public void setRequestedQty(String requestedQty)
	{
		this.requestedQty = requestedQty;
	}
	
	/**
	 * @return the requestFor
	 */
	public String getRequestFor()
	{
		return requestFor;
	}
	
	/**
	 * @param requestFor the requestFor to set
	 */
	public void setRequestFor(String requestFor)
	{
		this.requestFor = requestFor;
	}

	/**
	 * Return the Specimen list
	 * @return List
	 */
	public Collection getSpecimenList() 
	{		
		return specimenList;
	}

	/**
	 * Sets the specimen List.
	 * @param specimenList List containing existing and derived specimens is set in the bean
	 */
	public void setSpecimenList(Collection specimenList) 
	{
		this.specimenList = specimenList;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	
	/**
	 * @return the instanceOf
	 */
	public String getInstanceOf()
	{
		return instanceOf;
	}

	
	/**
	 * @param instanceOf the instanceOf to set
	 */
	public void setInstanceOf(String instanceOf)
	{
		this.instanceOf = instanceOf;
	}

	
	/**
	 * @return the orderItemId
	 */
	public String getOrderItemId()
	{
		return orderItemId;
	}

	
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(String orderItemId)
	{
		this.orderItemId = orderItemId;
	}

	
	/**
	 * @return the assignedQty
	 */
	public String getAssignedQty()
	{
		return assignedQty;
	}

	
	/**
	 * @param assignedQty the assignedQty to set
	 */
	public void setAssignedQty(String assignedQty)
	{
		this.assignedQty = assignedQty;
	}

	
	/**
	 * @return the specimenId
	 */
	public String getSpecimenId()
	{
		return specimenId;
	}

	
	/**
	 * @param specimenId the specimenId to set
	 */
	public void setSpecimenId(String specimenId)
	{
		this.specimenId = specimenId;
	}

	/**
	 * @return SpecimenCollGroupId
	 */
	public String getSpecimenCollGroupId()
	{
		return specimenCollGroupId;
	}
	
	/**
	 * @param specimenCollGroupId the specimen collection group.
	 */
	public void setSpecimenCollGroupId(String specimenCollGroupId) 
	{
		this.specimenCollGroupId = specimenCollGroupId;
	}
	
}
