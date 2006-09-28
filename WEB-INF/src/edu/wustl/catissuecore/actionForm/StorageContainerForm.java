/**
 * <p>Title: StorageContainerForm Class>
 * <p>Description:  This Class is used to encapsulate all the request parameters passed 
 * from StorageContainer.jsp page. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Jul 15, 2005
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

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.SpecimenArrayType;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.domain.StorageType;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 * This Class is used to encapsulate all the request parameters passed from StorageType.jsp page.
 * @author aniruddha_phadnis
 * */
public class StorageContainerForm extends AbstractActionForm
{

	private static final long serialVersionUID = 1234567890L;
	/**
	 * An id which refers to the type of the storage.
	 */
	private long typeId = -1;

	/**
	 * An name which refers to the type of the storage.
	 */
	private String typeName;

	/**
	 * An id which refers to Parent Container of this container.
	 */
	private long parentContainerId;

	/**
	 * Position label shown after selecting from storage container map viewer.
	 */
	private String positionInParentContainer;

	/**
	 * An id which refers to the site of the container if it is parent container.
	 */
	private long siteId;
	/**
	 * An name which refers to the site of the container if it is parent container.
	 */
	private String siteName;

	/**
	 * A default temperature of the storage container.
	 */
	private String defaultTemperature;

	/**
	 * Capacity in dimension one.
	 */
	private int oneDimensionCapacity;

	/**
	 * Capacity in dimension two.
	 */
	private int twoDimensionCapacity = 1;

	/**
	 * Text label for dimension one.
	 */
	private String oneDimensionLabel;

	/**
	 * Text label for dimension two.
	 */
	private String twoDimensionLabel;

	/**
	 * Starting Number.
	 */
	private String startNumber = "";

	/** newly added by vaishali on 20th June 4.04 pm
	 * 
	 */
	private String containerName = "";
	/**
	 * No. of containers.
	 */
	private int noOfContainers = 1;

	/**
	 * Text label for dimension two.
	 */
	private String barcode;

	/**
	 * Key.
	 */
	private String key;

	/**
	 * Radio button to choose site/parentContainer.
	 */
	private int checkedButton = 1;

	/**
	 * Tells whether this container is full or not.
	 */
	private String isFull = "False";

	/**
	 * Map to handle values of all the CollectionProtocol Events
	 */
	protected Map values = new HashMap();

	/** 
	 * Positon for dimension 1
	 */
	private int positionDimensionOne;

	/**
	 * Position for dimension 2
	 */
	private int positionDimensionTwo;

	/**
	 * site name for particular parent container
	 */
	private String siteForParentContainer;
	/**
	 * collectionIds contains Ids of collection Protocols that this container can hold
	 */
	protected long collectionIds[] = new long[]{-1};

	/**
	 * holdStorageTypeIds contains Ids of Storage Types that this container can hold
	 */
	protected long holdsStorageTypeIds[];

	private long holdsSpecimenArrTypeIds[];
	/**
	 * holdSpecimenClassTypeIds contains Ids of Specimen Types that this container can hold
	 */
	protected String holdsSpecimenClassTypes[];

	/**
	 * A map that contains distinguished fields (container name,barcode,parent location) per container.
	 */
	private Map similarContainersMap = new HashMap();

	private String specimenOrArrayType;
	/**
	 * No argument constructor for StorageTypeForm class 
	 */
	public StorageContainerForm()
	{
		//reset();
	}

	/**
	 * This function Copies the data from an storage type object to a StorageTypeForm object.
	 * @param storageType A StorageType object containing the information about storage type of the container.  
	 */
	public void setAllValues(AbstractDomainObject abstractDomain)
	{

		StorageContainer container = (StorageContainer) abstractDomain;

		this.id = container.getId().longValue();
		this.activityStatus = Utility.toString(container.getActivityStatus());
		this.containerName = container.getName();
		isFull = Utility.initCap(Utility.toString(container.isFull()));
		Logger.out.debug("isFULL />/>/> " + isFull);

		this.typeId = container.getStorageType().getId().longValue();
		this.typeName = container.getStorageType().getName();
		

		if (container.getParent() != null)
		{
			this.parentContainerId = container.getParent().getId().longValue();
			this.checkedButton = 2;
			StorageContainer parentContainer = (StorageContainer)container.getParent(); 
			this.positionInParentContainer = parentContainer.getStorageType().getName()
					+ " : "
					+ container.getParent().getId()
					+ " Pos("
					+ container.getPositionDimensionOne()
					+ ","
					+ container.getPositionDimensionTwo() + ")";

			//Sri: Fix for bug #
			this.positionDimensionOne = container.getPositionDimensionOne().intValue();
			this.positionDimensionTwo = container.getPositionDimensionTwo().intValue();
			
			this.siteName = ((StorageContainer)container.getParent()).getSite().getName();
		}

		if (container.getSite() != null)
		{
			this.siteId = container.getSite().getId().longValue();
			this.siteName = container.getSite().getName();
		}

		this.defaultTemperature = Utility.toString(container.getTempratureInCentigrade());
		this.oneDimensionCapacity = container.getCapacity()
				.getOneDimensionCapacity().intValue();
		this.twoDimensionCapacity = container.getCapacity()
				.getTwoDimensionCapacity().intValue();
		this.oneDimensionLabel = container.getStorageType().getOneDimensionLabel();
		this.twoDimensionLabel = Utility
				.toString(container.getStorageType().getTwoDimensionLabel());

		if (container.getNoOfContainers() != null)
			this.noOfContainers = container.getNoOfContainers().intValue();

		if (container.getStartNo() != null)
			this.startNumber = String.valueOf(container.getStartNo().intValue());

		this.barcode = Utility.toString(container.getBarcode());

		//Populating the collection protocol id array
		Collection collectionProtocolCollection = container.getCollectionProtocolCollection();

		if (collectionProtocolCollection != null && collectionProtocolCollection.size() > 0)
		{
			this.collectionIds = new long[collectionProtocolCollection.size()];
			int i = 0;

			Iterator it = collectionProtocolCollection.iterator();
			while (it.hasNext())
			{
				CollectionProtocol cp = (CollectionProtocol) it.next();
				this.collectionIds[i] = cp.getId().longValue();
				i++;
			}

		}

		//Populating the storage type-id array
		Collection storageTypeCollection = container.getHoldsStorageTypeCollection();

		if (storageTypeCollection != null)
		{
			this.holdsStorageTypeIds = new long[storageTypeCollection.size()];
			int i = 0;

			Iterator it = storageTypeCollection.iterator();
			while (it.hasNext())
			{
				StorageType storageType = (StorageType) it.next();
				this.holdsStorageTypeIds[i] = storageType.getId().longValue();
				i++;
			}
		}

		//Populating the specimen class type-id array
		Collection specimenClassCollection = container.getHoldsSpecimenClassCollection();

		if (specimenClassCollection != null)
		{
			if(specimenClassCollection.size() == Utility.getSpecimenClassTypes().size())
			{
				holdsSpecimenClassTypes = new String[1];
				holdsSpecimenClassTypes[0] = "-1";
				this.specimenOrArrayType = "Specimen";
			}
			else
			{
				this.holdsSpecimenClassTypes = new String[specimenClassCollection.size()];
				int i = 0;

				Iterator it = specimenClassCollection.iterator();
				while (it.hasNext())
				{
					String specimenClass=(String)it.next();
					this.holdsSpecimenClassTypes[i]=specimenClass;
					i++;
					this.specimenOrArrayType = "Specimen";
				}
			}
		}
//      Populating the specimen array type-id array
		Collection specimenArrayTypeCollection = container.getHoldsSpArrayTypeCollection();
		
		if(specimenArrayTypeCollection != null)
		{
			holdsSpecimenArrTypeIds = new long[specimenArrayTypeCollection.size()];
			int i=0;

			Iterator it = specimenArrayTypeCollection.iterator();
			while(it.hasNext())
			{
				SpecimenArrayType holdSpArrayType = (SpecimenArrayType)it.next();
				holdsSpecimenArrTypeIds[i] = holdSpArrayType.getId().longValue();
				i++;
				this.specimenOrArrayType = "SpecimenArray";
			}
		}

		
	}

	/**
	 * Returns an id which refers to the type of the storage.
	 * @return An id which refers to the type of the storage.
	 * @see #setTypeId(long)
	 */
	public long getTypeId()
	{
		return this.typeId;
	}

	/**
	 * Sets an id which refers to the type of the storage.
	 * @param typeId An id which refers to the type of the storage.
	 * @see #getTypeId()
	 */
	public void setTypeId(long typeId)
	{
		this.typeId = typeId;
	}


	/**
	 * Sets an name which refers to the type of the storage.
	 * @param typeName An id which refers to the type of the storage.
	 * @see #getTypeName()
	 */
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}
	
	/**
	 * Returns an name which refers to the type of the storage.
	 * @return An name which refers to the type of the storage.
	 * @see #setTypeName(String)
	 */
	public String getTypeName()
	{
		return this.typeName;
	}

		/**
	 * Returns the default temperature of the storage container.
	 * @return double the default temperature of the storage container to be set.
	 * @see #setDefaultTemperature(double)
	 */
	public String getDefaultTemperature()
	{
		return this.defaultTemperature;
	}

	/**
	 * Sets the default temperature of the storage container.
	 * @param defaultTemperature the default temperature of the storage container to be set.
	 * @see #getDefaultTemperature()
	 */
	public void setDefaultTemperature(String defaultTemperature)
	{
		this.defaultTemperature = defaultTemperature;
	}

	/**
	 * Returns the capacity of dimension one.
	 * @return int the capacity of dimension one.
	 * @see #setOneDimensionCapacity(int)
	 */
	public int getOneDimensionCapacity()
	{
		return this.oneDimensionCapacity;
	}

	/**
	 * Sets the capacity of dimension one.
	 * @param oneDimensionCapacity the capacity of dimension one to be set.
	 * @see #getOneDimensionCapacity()
	 */
	public void setOneDimensionCapacity(int oneDimensionCapacity)
	{
		this.oneDimensionCapacity = oneDimensionCapacity;
	}

	/**
	 * Returns the capacity of dimension two.
	 * @return int the capacity of dimension two.
	 * @see #setTwoDimensionCapacity(int)
	 */
	public int getTwoDimensionCapacity()
	{
		return this.twoDimensionCapacity;
	}

	/**
	 * Sets the capacity of dimension two.
	 * @param twoDimensionCapacity the capacity of dimension two to be set.
	 * @see #getTwoDimensionCapacity()
	 */
	public void setTwoDimensionCapacity(int twoDimensionCapacity)
	{
		this.twoDimensionCapacity = twoDimensionCapacity;
	}

	/**
	 * Returns the label of dimension one.
	 * @return String the label of dimension one.
	 * @see #setOneDimensionLabel(String)
	 */
	public String getOneDimensionLabel()
	{
		return this.oneDimensionLabel;
	}

	/**
	 * Sets the label of dimension one.
	 * @param oneDimensionLabel the label of dimension one to be set.
	 * @see #getOneDimensionLabel()
	 */
	public void setOneDimensionLabel(String oneDimensionLabel)
	{
		this.oneDimensionLabel = oneDimensionLabel;
	}

	/**
	 * Returns the label of dimension two.
	 * @return String the label of dimension two.
	 * @see #setTwoDimensionLabel(String)
	 */
	public String getTwoDimensionLabel()
	{
		return this.twoDimensionLabel;
	}

	/**
	 * Sets the label of dimension two.
	 * @param twoDimensionLabel the label of dimension two to be set.
	 * @see #getTwoDimensionLabel()
	 */
	public void setTwoDimensionLabel(String twoDimensionLabel)
	{
		this.twoDimensionLabel = twoDimensionLabel;
	}

	/**
	 * Returns an id which refers to Parent Container of this container.
	 * @return long An id which refers to Parent Container of this container.
	 * @see #setParentContainerId(long)
	 */
	public long getParentContainerId()
	{
		return parentContainerId;
	}

	/**
	 * Sets an id which refers to Parent Container of this container.
	 * @param parentContainerId An id which refers to Parent Container of this container.
	 * @see #getParentContainerId()
	 */
	public void setParentContainerId(long parentContainerId)
	{
		this.parentContainerId = parentContainerId;
	}

	/**
	 * @return Returns the positionInParentContainer.
	 */
	public String getPositionInParentContainer()
	{
		return positionInParentContainer;
	}

	/**
	 * @param positionInParentContainer The positionInParentContainer to set.
	 */
	public void setPositionInParentContainer(String positionInParentContainer)
	{
		this.positionInParentContainer = positionInParentContainer;
	}

	/**
	 * Returns an id which refers to the site of the container if it is parent container.
	 * @return long An id which refers to the site of the container if it is parent container.
	 * @see #setSiteId(long)
	 */
	public long getSiteId()
	{
		return siteId;
	}

	/**
	 * Sets an id which refers to the site of the container if it is parent container.
	 * @param siteId An id which refers to the site of the container if it is parent container.
	 * @see #getSiteId()
	 */
	public void setSiteId(long siteId)
	{
		this.siteId = siteId;
	}


	/**
	 * Returns an name which refers to the site of the container if it is parent container.
	 * @return String An name which refers to the site of the container if it is parent container.
	 * @see #setSiteName(String)
	 */
	public String getSiteName()
	{
		return siteName;
	}

	/**
	 * Sets an name which refers to the site of the container if it is parent container.
	 * @param siteName An name which refers to the site of the container if it is parent container.
	 * @see #getSiteName()
	 */
	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}


	/**
	 * Returns the id assigned to form bean
	 */
	public int getFormId()
	{
		return Constants.STORAGE_CONTAINER_FORM_ID;
	}

	/**
	 * Resets the values of all the fields.
	 * Is called by the overridden reset method defined in ActionForm.  
	 * */
	protected void reset()
	{

	}

	/**
	 * @return Returns the noOfContainers.
	 */
	public int getNoOfContainers()
	{
		return noOfContainers;
	}

	/**
	 * @param noOfContainers The noOfContainers to set.
	 */
	public void setNoOfContainers(int noOfContainers)
	{
		this.noOfContainers = noOfContainers;
	}

	/**
	 * @return Returns the startNumber.
	 */
	public String getStartNumber()
	{
		return startNumber;
	}

	/**
	 * @param startNumber The startNumber to set.
	 */
	public void setStartNumber(String startNumber)
	{
		this.startNumber = startNumber;
	}

	/**
	 * @return Returns the barcode.
	 */
	public String getBarcode()
	{
		return barcode;
	}

	/**
	 * @param barcode The barcode to set.
	 */
	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	/**
	 * @return Returns the key.
	 */
	public String getKey()
	{
		return key;
	}

	/**
	 * @param key The key to set.
	 */
	public void setKey(String key)
	{
		this.key = key;
	}

	/**
	 * @return Returns the checkedButton.
	 */
	public int getCheckedButton()
	{
		return checkedButton;
	}

	/**
	 * @param checkedButton The checkedButton to set.
	 */
	public void setCheckedButton(int checkedButton)
	{
		this.checkedButton = checkedButton;
	}

	/**
	 * @return Returns the isFull.
	 */
	public String getIsFull()
	{
		return isFull;
	}

	/**
	 * @param isFull The isFull to set.
	 */
	public void setIsFull(String isFull)
	{
		this.isFull = isFull;
	}

	/**
	 * Gets the Container Name
	 * @return container Name
	 */
	public String getContainerName()
	{
		return this.containerName;
	}

	/**
	 * sets the name of the container
	 * @param containerName container Name to set
	 */
	public void setContainerName(String containerName)
	{
		this.containerName = containerName;
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
	 * @param key the required key.
	 * @return the object to which this map maps the specified key.
	 */
	public Object getValue(String key)
	{
		return values.get(key);
	}

	/**
	 * @return Returns the values.
	 */
	public Collection getAllValues()
	{
		return values.values();
	}

	/**
	 * @param values
	 *            The values to set.
	 */
	public void setValues(Map values)
	{
		this.values = values;
	}

	/**
	 * @param values
	 *            The values to set.
	 */
	public Map getValues()
	{
		return this.values;
	}

	/**
	 * @return Returns the positionDimensionOne.
	 */
	public int getPositionDimensionOne()
	{
		return positionDimensionOne;
	}

	/**
	 * @param positionDimensionOne The positionDimensionOne to set.
	 */
	public void setPositionDimensionOne(int positionDimensionOne)
	{
		this.positionDimensionOne = positionDimensionOne;
	}

	/**
	 * @return Returns the positionDimensionTwo.
	 */
	public int getPositionDimensionTwo()
	{
		return positionDimensionTwo;
	}

	/**
	 * @param positionDimensionTwo The positionDimensionTwo to set.
	 */
	public void setPositionDimensionTwo(int positionDimensionTwo)
	{
		this.positionDimensionTwo = positionDimensionTwo;
	}

	/**
	 * getitng collection Ids that this container can hold
	 * @return collection Id's array
	 */
	public long[] getCollectionIds()
	{
		return this.collectionIds;
	}

	/**
	 * setitng the Collection Id array
	 * @param collectionIds - array of collection Ids to set
	 */
	public void setCollectionIds(long[] collectionIds)
	{
		this.collectionIds = collectionIds;
	}

	/**
	 * getting Specimen class Type Ids that this container can hold 
	 * @return specimenClassType Id's array
	 */
	public String[] getHoldsSpecimenClassTypes()
	{
		return holdsSpecimenClassTypes;
	}

	/**
	 * setitng the SpecimenClassType Id array
	 * @param holdsSpecimenClassTypeIds - array of SpecimenClassType Id's to set
	 */
	public void setHoldsSpecimenClassTypes(String[] holdsSpecimenClassTypes)
	{
		this.holdsSpecimenClassTypes = holdsSpecimenClassTypes;
	}

	/**
	 * getitng StorageType Id's that this container can hold
	 * @return StorageType Id' array
	 */
	public long[] getHoldsStorageTypeIds()
	{
		return holdsStorageTypeIds;
	}

	/**
	 * setting the StorageType Id array
	 * @param holdsStorageTypeIds - array of StorageType id's to set
	 */
	public void setHoldsStorageTypeIds(long[] holdsStorageTypeIds)
	{
		this.holdsStorageTypeIds = holdsStorageTypeIds;
	}

	
	/**
     * Sets the Specimen Array Type Holds List.
     * @param holdsSpecimenArrTypeIds the list of specimen array type Ids to be set.
     * @see #getHoldsSpecimenArrTypeIds()
     */
	public void setHoldsSpecimenArrTypeIds(long[] holdsSpecimenArrTypeIds)
	{
		this.holdsSpecimenArrTypeIds = holdsSpecimenArrTypeIds;
	}

	 /**
     * Returns the list of specimen array type Ids that this Storage Type can hold.
     * @return long[] the list of specimen array type Ids.
     * @see #setHoldsSpecimenArrTypeIds(long[])
     */
    public long[] getHoldsSpecimenArrTypeIds()
	{
		return holdsSpecimenArrTypeIds;
	}
	/**
	 * Returns the map that contains distinguished fields per aliquots.
	 * @return The map that contains distinguished fields per aliquots.
	 * @see #setAliquotMap(Map)
	 */
	public Map getSimilarContainersMap()
	{
		//System.out.println("AliquotForm : getAliquotMap "+similarContainersMap);
		return similarContainersMap;
	}

	/**
	 * Sets the map of distinguished fields of aliquots.
	 * @param aliquotMap A map of distinguished fields of aliquots.
	 * @see #getAliquotMap()
	 */
	public void setSimilarContainersMap(Map similarContainersMap)
	{
		//System.out.println("AliquotForm : setAliquotMap "+similarContainersMap);
		this.similarContainersMap = similarContainersMap;
	}

	/**
	 * Associates the specified object with the specified key in the map.
	 * @param key the key to which the object is mapped.
	 * @param value the object which is to be mapped.
	 */
	public void setSimilarContainerMapValue(String key, Object value)
	{
		//System.out.println("simCont: setValue -> "+key+" "+value);
		similarContainersMap.put(key, value);
	}

	/**
	 * Returns the object to which this map maps the specified key.
	 * @param key the required key.
	 * @return the object to which this map maps the specified key.
	 */
	public Object getSimilarContainerMapValue(String key)
	{
		//System.out.println("simCont: getValue <- "+key+" "+similarContainersMap.get(key));
		return similarContainersMap.get(key);
	}

	/**
	 * This method sets Identifier of Objects inserted by AddNew activity in Form-Bean which initialized AddNew action
	 * @param formBeanId - FormBean ID of the object inserted
	 *  @param addObjectIdentifier - Identifier of the Object inserted 
	 */
	public void setAddNewObjectIdentifier(String addNewFor, Long addObjectIdentifier)
	{
		if (addNewFor.equals("storageType"))
		{
			setTypeId(addObjectIdentifier.longValue());
		}
		else if (addNewFor.equals("site"))
		{
			setSiteId(addObjectIdentifier.longValue());
		}
	}

	/**
	 * Overrides the validate method of ActionForm.
	 * */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		Validator validator = new Validator();

		try
		{
			Logger.out.info("No of containers---------in validate::" + this.noOfContainers);
			//if (this.noOfContainers == 1)
			//{
			if (this.typeId == -1)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",
						ApplicationProperties.getValue("storageContainer.type")));
			}
			if (!validator.isValidOption(isFull) && this.noOfContainers == 1)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.selected",
						ApplicationProperties.getValue("storageContainer.isContainerFull")));
			}

			if (checkedButton == 1 && siteId == -1 && this.noOfContainers == 1)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",
						ApplicationProperties.getValue("storageContainer.site")));
			}
			else if (checkedButton == 2 && this.noOfContainers == 1)
			{
				if (!validator.isNumeric(String.valueOf(positionDimensionOne), 1)
						|| !validator.isNumeric(String.valueOf(positionDimensionTwo), 1)
						|| !validator.isNumeric(String.valueOf(parentContainerId), 1))
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
							ApplicationProperties.getValue("storageContainer.parentContainer")));
				}
			}

			if (this.noOfContainers == 1)
			{
				checkValidNumber(String.valueOf(noOfContainers), "storageContainer.noOfContainers",
						errors, validator);
			}
			//validations for Container name
			if (validator.isEmpty(containerName) && this.noOfContainers == 1)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",
						ApplicationProperties.getValue("storageContainer.name")));
			}

			//validation for collection protocol
			if (collectionIds.length > 1)
			{
				for (int i = 0; i < collectionIds.length; i++)
				{
					if (collectionIds[i] == -1)
					{
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
								ApplicationProperties
										.getValue("storageContainer.collectionProtocolTitle")));
					}

				}
			}

			//validation for holds storage type
			checkValidSelectionForAny(holdsStorageTypeIds, "storageContainer.containerType", errors);
			//validation for holds specimen class
			/*new chnage checkValidSelectionForAny(holdsSpecimenClassTypeIds, "storageContainer.specimenType",
					errors);*/

			if (operation.equals(Constants.EDIT) && !validator.isValidOption(activityStatus))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",
						ApplicationProperties.getValue("site.activityStatus")));
			}
			// validations for temperature
			if (!validator.isEmpty(defaultTemperature)
					&& (!validator.isDouble(defaultTemperature, false)))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
						ApplicationProperties.getValue("storageContainer.temperature")));
			}

			//VALIDATIONS FOR DIMENSION 1.
			if (validator.isEmpty(String.valueOf(oneDimensionCapacity)))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",
						ApplicationProperties.getValue("storageContainer.oneDimension")));
			}
			else
			{
				if (!validator.isNumeric(String.valueOf(oneDimensionCapacity)))
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
							ApplicationProperties.getValue("storageContainer.oneDimension")));
				}
			}

			//Validations for dimension 2
			if (!validator.isEmpty(String.valueOf(twoDimensionCapacity))
					&& (!validator.isNumeric(String.valueOf(twoDimensionCapacity))))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
						ApplicationProperties.getValue("storageContainer.twoDimension")));
			}

			if (this.noOfContainers > 1 && this.getSimilarContainersMap().size()>0)
			{
				for (int i = 1; i <= this.noOfContainers; i++)
				{
					String iBarcode = (String) this.getSimilarContainerMapValue("simCont:" + i + "_barcode"); //simCont:1_barcode
					if (iBarcode != null && iBarcode.equals("")) // this is done because barcode is empty string set by struts
					{ // but barcode in DB is unique but can be null.
						this.setSimilarContainerMapValue("simCont:" + i + "_barcode", null);
					}
					Logger.out.info("Similar Container Map in SImilarCOntainerForm:"+similarContainersMap);
					Logger.out.info("Checked button:"+getSimilarContainerMapValue("checkedButton"));
					int checkedButtonStatus = Integer.parseInt((String) getSimilarContainerMapValue("checkedButton"));
					String siteId = (String) getSimilarContainerMapValue("simCont:" + i + "_siteId");
					if (checkedButtonStatus == 2 || siteId == null)
					{
						String parentContId = (String) getSimilarContainerMapValue("simCont:" + i
								+ "_parentContainerId");
						String positionDimensionOne = (String) getSimilarContainerMapValue("simCont:" + i
								+ "_positionDimensionOne");
						String positionDimensionTwo = (String) getSimilarContainerMapValue("simCont:" + i
								+ "_positionDimensionTwo");
						Logger.out.debug(i + " parentContId " + parentContId
								+ " positionDimensionOne " + positionDimensionOne
								+ " positionDimensionTwo " + positionDimensionOne);
						if (parentContId.equals("-1") || positionDimensionOne.equals("-1")
								|| positionDimensionTwo.equals("-1"))
						{
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
									"errors.item.required", ApplicationProperties
											.getValue("similarcontainers.location")));
							this.setSimilarContainerMapValue("checkedButton", "2");
						}
					}
					else
					{
						if (siteId.equals("-1"))
						{
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
									"errors.item.required", ApplicationProperties
											.getValue("storageContainer.site")));
						}
					}
				}
			}
			//}

		}
		catch (Exception excp)
		{
			System.out.println("\n\n*******Error*********\n\n");
			Logger.out.error(excp.getMessage(), excp);
		}
		return errors;
	}

	//This function if 'any' option is selected then no other option should be selected */
	void checkValidSelectionForAny(long[] Ids, String message, ActionErrors errors)
	{
		if (Ids != null && Ids.length > 1)
		{
			for (int i = 0; i < Ids.length; i++)
			{
				if (Ids[i] == 1)
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format",
							ApplicationProperties.getValue(message)));
					break;
				}
			}
		}
	}

	
	public String getSiteForParentContainer()
	{
		return siteForParentContainer;
	}

	
	public void setSiteForParentContainer(String siteForParentContainer)
	{
		this.siteForParentContainer = siteForParentContainer;
	}

	
	public String getSpecimenOrArrayType()
	{
		return specimenOrArrayType;
	}

	
	public void setSpecimenOrArrayType(String specimenOrArrayType)
	{
		this.specimenOrArrayType = specimenOrArrayType;
	}

}