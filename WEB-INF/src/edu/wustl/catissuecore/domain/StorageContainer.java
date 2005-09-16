/**
 * <p>Title: StorageContainer Class>
 * <p>Description:  A class that models storage container's information. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Apr 7, 2005
 */
package edu.wustl.catissuecore.domain;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import edu.wustl.catissuecore.actionForm.AbstractActionForm;
import edu.wustl.catissuecore.actionForm.StorageContainerForm;
import edu.wustl.common.util.MapDataParser;
import edu.wustl.common.util.logger.Logger;

/**
 * A class that models storage container's information.
 * @hibernate.class table="CATISSUE_STORAGE_CONTAINER"
 * @author Aniruddha Phadnis
 */
public class StorageContainer extends AbstractDomainObject implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;

	/**
     * System generated unique systemIdentifier.
     */
	protected Long systemIdentifier;

	/**
     * Name assigned to storage container.
     */
	protected Integer number;
	
	/**
     * Intended temperature of the storage container.
     */
	protected Double tempratureInCentigrade;
	
	/**
	 * Is no space available in the container to store more specimens?
	 */
	protected Boolean isFull;
	
	/**
	 * Defines whether this Storage Container record can be queried (ACTIVE) or not queried (INACTIVE) by any actor.
	 */
	protected String activityStatus;
	
	/**
	 * Barcode assigned to container.
	 */
	protected String barcode;
	
	/**
	 * Type of the storage container e.g. Freezer, Box etc.
	 */
	protected StorageType storageType;
	
	/**
	 * A physical location of storage container
	 */
	protected Site site;
	
	/**
	 * Parent container of this container.
	 */
	protected StorageContainer parentContainer = null;
	
	/**
	 * A collection of storage container details
	 */
	protected Collection storageContainerDetailsCollection = new HashSet();
	
	/**
	 * A capacity of storage container
	 */
	protected StorageContainerCapacity storageContainerCapacity = new StorageContainerCapacity();
	
	/**
	 * A collection of sub containers under this container
	 */
	protected Collection childrenContainerCollection = new HashSet();
	
	/**
	 * Number of containers
	 */
	protected transient Integer noOfContainers;
	
	/**
	 * Number of containers
	 */
	protected transient Integer startNo;
	
	protected Integer positionDimensionOne;

	protected Integer positionDimensionTwo;

	//Default Constructor
	public StorageContainer()
	{
	}
	
	public StorageContainer(AbstractActionForm form)
	{
		setAllValues(form);
	}
	
	public StorageContainer(StorageContainer oldContainer)
	{
		this.setSystemIdentifier(oldContainer.getSystemIdentifier());
		this.setActivityStatus(oldContainer.getActivityStatus());
		this.setParentContainer(oldContainer.getParentContainer());
		this.setNumber(oldContainer.getNumber());
		
		if(parentContainer!=null)
		{
			parentContainer.getChildrenContainerCollection().add(this);
		}
		this.setSite(oldContainer.getSite());		
		this.setStorageContainerCapacity(oldContainer.getStorageContainerCapacity());
		this.setStorageType(oldContainer.getStorageType());
		this.setTempratureInCentigrade(oldContainer.getTempratureInCentigrade());
		this.setStorageContainerDetailsCollection(new HashSet(oldContainer.getStorageContainerDetailsCollection()));
	}
	
	/**
     * Returns System generated unique systemIdentifier.
     * @return System generated unique systemIdentifier.
     * @see #setSystemIdentifier(Long)
     * @hibernate.id name="systemIdentifier" column="IDENTIFIER" type="long" length="30"
     * unsaved-value="null" generator-class="native" 
     */
	public Long getSystemIdentifier()
	{
		return systemIdentifier;
	}

	/**
     * Sets unique systemIdentifier.
     * @param systemIdentifier Identifier to be set.
     * @see #getSystemIdentifier()
     */
	public void setSystemIdentifier(Long systemIdentifier)
	{
		this.systemIdentifier = systemIdentifier;
	}

	/**
     * Returns the number assigned to storage container. 
     * @return number assigned to storage container.
     * @see #setNumber(Integer)
     * @hibernate.property name="number" type="int" 
     * column="CONTAINER_NUMBER" length="50"
     */
	public Integer getNumber()
	{
		return number;
	}

	/**
     * Sets the name of the storage container.
     * @param name name of the storage container to be set.
     * @see #getNumber()
     */
	public void setNumber(Integer number)
	{
		this.number = number;
	}

	/**
     * Returns the temperature of the storage container. 
     * @return Temperature of the storage container.
     * @see #setTempratureInCentigrade(Double)
     * @hibernate.property name="tempratureInCentigrade" type="double" 
     * column="TEMPERATURE" length="30"
     */
	public Double getTempratureInCentigrade()
	{
		return tempratureInCentigrade;
	}

	/**
     * Sets the temperature of the storage container.
     * @param tempratureInCentigrade temperature of the storage container to be set.
     * @see #getTempratureInCentigrade()
     */
	public void setTempratureInCentigrade(Double tempratureInCentigrade)
	{
		this.tempratureInCentigrade = tempratureInCentigrade;
	}

	/**
     * Tells whether the space is available in the storage container or not. 
     * @return Availabilty of space in the storage container.
     * @see #setIsFull(Boolean)
     * @hibernate.property name="isFull" type="boolean" 
     * column="IS_CONTAINER_FULL"
     */
	public Boolean getIsFull()
	{
		return isFull;
	}

	/**
     * Sets the availability of the storage container.
     * @param isFull availability of the storage container.
     * @see #getIsFull()
     */
	public void setIsFull(Boolean isFull)
	{
		this.isFull = isFull;
	}

	/**
     * Returns the barcode of the storage container. 
     * @return The barcode of storage container.
     * @see #setBarcode(Boolean)
     * @hibernate.property name="barcode" type="string" 
     * column="BARCODE" length="50" unique="true"
     */
	public String getBarcode()
	{
		return barcode;
	}

	/**
     * Sets the barcode of the storage container.
     * @param barcode the barcode of the storage container.
     * @see #getBarcode()
     */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
     * Returns the activity status of the storage container. 
     * @return The activity status of storage container.
     * @see #setActivityStatus(String)
     * @hibernate.property name="activityStatus" type="string" 
     * column="ACTIVITY_STATUS" length="30"
     */
	public String getActivityStatus()
	{
		return activityStatus;
	}

	/**
     * Sets the activity status.
     * @param activityStatus the activity status of the storagecontainer to be set.
     * @see #getActivityStatus()
     */
	public void setActivityStatus(String activityStatus)
	{
		this.activityStatus = activityStatus;
	}

	/**
     * Returns the storage type of the container.
     * @hibernate.many-to-one column="STORAGE_TYPE_ID" 
     * class="edu.wustl.catissuecore.domain.StorageType" constrained="true"
     * @return The storage type of the container.
     * @see #setStorageType(StorageType)
     */
	public StorageType getStorageType()
	{
		return storageType;
	}

	/**
     * Sets the storage type of the container.
     * @param storageType the storage type of the container to be set.
     * @see #getStorageType()
     */
	public void setStorageType(StorageType storageType)
	{
		this.storageType = storageType;
	}

	/**
     * Returns the physical location of the storage container.
     * @hibernate.many-to-one column="SITE_ID" 
     * class="edu.wustl.catissuecore.domain.Site" constrained="true"
     * @return Site the physical location of the storage container.
     * @see #setSite(Site)
     */
	public Site getSite()
	{
		return site;
	}

	/**
     * Sets the physical location of the container.
     * @param site the physical location of the container.
     * @see #getSite()
     */
	public void setSite(Site site)
	{
		this.site = site;
	}

	/**
     * Returns the parent container of this storage container.
     * @hibernate.many-to-one column="PARENT_CONTAINER_ID" 
     * class="edu.wustl.catissuecore.domain.StorageContainer" constrained="true"
     * @return The parent container of this storage container.
     * @see #setParentContainer(StorageContainer)
     */
	public StorageContainer getParentContainer()
	{
		return parentContainer;
	}

	/**
     * Sets the parent container of this storage container.
     * @param parentContainer the parent container of this storage container.
     * @see #getSite()
     */
	public void setParentContainer(StorageContainer parentContainer)
	{
		this.parentContainer = parentContainer;
	}

	/**
	 * Returns collection of storage container details.
	 * @return Collection of storage container details.
	 * @hibernate.set name="storageContainerDetailsCollection" table="CATISSUE_STORAGE_CONTAINER_DETAILS"
	 * @hibernate.collection-key column="STORAGE_CONTAINER_ID"
	 * @hibernate.collection-one-to-many class="edu.wustl.catissuecore.domain.StorageContainerDetails"
	 * @see setStorageContainerDetailsCollection(Collection)
	 */
	public Collection getStorageContainerDetailsCollection()
	{
		return storageContainerDetailsCollection;
	}

	/**
	 * Sets the collection of storage container details.
	 * @param storageContainerDetailsCollection collection of storage container details to be set.
	 * @see #getStorageContainerDetailsCollection()
	 */
	public void setStorageContainerDetailsCollection(Collection storageContainerDetailsCollection)
	{
		this.storageContainerDetailsCollection = storageContainerDetailsCollection;
	}

	/**
     * Returns the capacity of this storage container.
     * @hibernate.many-to-one column="STORAGE_CONTAINER_CAPACITY_ID" 
     * class="edu.wustl.catissuecore.domain.StorageContainerCapacity" constrained="true"
     * @return The capacity of this storage container.
     * @see #setStorageContainerCapacity(StorageContainerCapacity)
     */
	public StorageContainerCapacity getStorageContainerCapacity()
	{
		return storageContainerCapacity;
	}

	/**
     * Sets the capacity of this storage container.
     * @param storageContainerCapacity the capacity of storage container to be set.
     * @see #getStorageContainerCapacity()
     */
	public void setStorageContainerCapacity(StorageContainerCapacity storageContainerCapacity)
	{
		this.storageContainerCapacity = storageContainerCapacity;
	}
	
	
	/**
	 * Returns collection of sub containers under this.
	 * @return collection of sub containers under this.
	 * @hibernate.set name="childrenContainerCollection" table="CATISSUE_STORAGE_CONTAINER"
	 * @hibernate.collection-key column="PARENT_CONTAINER_ID"
	 * @hibernate.collection-one-to-many class="edu.wustl.catissuecore.domain.StorageContainer"
	 * @see setChildrenContainerCollection(Collection)
	 */
	public Collection getChildrenContainerCollection()
	{
		return childrenContainerCollection;
	}

	/**
	 * Sets the collection of sub containers under this.
	 * @param storageContainerDetailsCollection collection of sub containers under this.
	 * @see #getChildrenContainerCollection()
	 */
	public void setChildrenContainerCollection(Collection childrenContainerCollection)
	{
		this.childrenContainerCollection = childrenContainerCollection;
	}
	
	/**
     * Returns the reference to dimensional position one of the specimen in Storage Container.
     * @hibernate.property name="positionDimensionOne" type="int" column="POSITION_DIMENSION_ONE" length="30"  
     * @return the reference to dimensional position one of the specimen in Storage Container.
     * @see #setPositionDimensionOne(Integer)
     */
    public Integer getPositionDimensionOne()
    {
        return positionDimensionOne;
    }

    /**
     * Sets the reference to dimensional position one of the specimen in Storage Container.
     * @param positionDimensionOne the reference to dimensional position one of the specimen 
     * in Storage Container.
     * @see #getPositionDimensionOne()
     */
    public void setPositionDimensionOne(Integer positionDimensionOne)
    {
        this.positionDimensionOne = positionDimensionOne;
    }

    /**
     * Returns the reference to dimensional position two of the specimen in Storage Container.
     * @hibernate.property name="positionDimensionTwo" type="int" column="POSITION_DIMENSION_TWO" length="50"  
     * @return the reference to dimensional position two of the specimen in Storage Container.
     * @see #setPositionDimensionOne(Integer)
     */
    public Integer getPositionDimensionTwo()
    {
        return positionDimensionTwo;
    }

    /**
     * Sets the reference to dimensional position two of the specimen in Storage Container.
     * @param positionDimensionTwo the reference to dimensional position two of the specimen 
     * in Storage Container.
     * @see #getPositionDimensionTwo()
     */
    public void setPositionDimensionTwo(Integer positionDimensionTwo)
    {
        this.positionDimensionTwo = positionDimensionTwo;
    }
	
	/**
	 * This function Copies the data from a StorageTypeForm object to a StorageType object.
	 * @param storageTypeForm A StorageTypeForm object containing the information about the StorageType.  
	 * */
	public void setAllValues(AbstractActionForm abstractForm)
	{
	    try
	    {
	        StorageContainerForm form = (StorageContainerForm) abstractForm;
	        this.systemIdentifier 		= new Long(form.getSystemIdentifier());
	        this.number 				= new Integer(form.getStartNumber());
	        this.startNo				= new Integer(form.getStartNumber());
	        this.tempratureInCentigrade	= new Double(form.getDefaultTemperature());
	        this.barcode				= form.getBarcode();
	        this.isFull					= new Boolean(form.getIsFull());
	        this.activityStatus			= form.getActivityStatus();
	        this.noOfContainers			= new Integer(form.getNoOfContainers());
	        
	        storageType = new StorageType();
	        storageType.systemIdentifier = new Long(form.getTypeId());
	        storageType.setOneDimensionLabel(form.getOneDimensionLabel());
	        storageType.setTwoDimensionLabel(form.getTwoDimensionLabel());
	        
	        storageContainerCapacity.setOneDimensionCapacity(new Integer(form.getOneDimensionCapacity()));
	        storageContainerCapacity.setTwoDimensionCapacity(new Integer(form.getTwoDimensionCapacity()));
	        
	        if(form.getCheckedButton() == 2)
			{
				parentContainer = new StorageContainer();
				parentContainer.setSystemIdentifier(new Long(form.getParentContainerId()));
				this.setPositionDimensionOne(new Integer(form.getPositionDimensionOne()));
		        this.setPositionDimensionTwo(new Integer(form.getPositionDimensionTwo()));
			}
	        else
	        {
		        site = new Site();
		        site.setSystemIdentifier(new Long(form.getSiteId()));
	        }
	        
	        Map map = form.getValues();
	        Logger.out.debug("Map.................. :"+map); 
	        MapDataParser parser = new MapDataParser("edu.wustl.catissuecore.domain");
	        
	        Collection storageContainerDetailsCollectionTemp = parser.generateData(map);
	        this.storageContainerDetailsCollection = storageContainerDetailsCollectionTemp;
	    }
	    catch(Exception excp)
	    {
	        Logger.out.error(excp.getMessage(),excp);
	    }
	}
	/**
	 * @return Returns the noOfContainers.
	 */
	public Integer getNoOfContainers()
	{
		return noOfContainers;
	}
	/**
	 * @param noOfContainers The noOfContainers to set.
	 */
	public void setNoOfContainers(Integer noOfContainers)
	{
		this.noOfContainers = noOfContainers;
	}
	/**
	 * @return Returns the startNo.
	 */
	public Integer getStartNo()
	{
		return startNo;
	}
	/**
	 * @param startNo The startNo to set.
	 */
	public void setStartNo(Integer startNo)
	{
		this.startNo = startNo;
	}
}