
package edu.wustl.catissuecore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import edu.wustl.catissuecore.actionForm.SpecimenArrayAliquotForm;
import edu.wustl.catissuecore.actionForm.SpecimenArrayForm;
import edu.wustl.catissuecore.util.SearchUtil;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.exception.AssignDataException;

/**
 * @author gautam_shetty
 * @author Ashwin Gupta 
 * @hibernate.joined-subclass table="CATISSUE_SPECIMEN_ARRAY"
 * @hibernate.joined-subclass-key column="IDENTIFIER"
 */
public class SpecimenArray extends Container
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Change for API Search   --- Ashwin 04/10/2006
    protected SpecimenArrayType specimenArrayType;

    //Change for API Search   --- Ashwin 04/10/2006
    protected User createdBy;

    //Change for API Search   --- Ashwin 04/10/2006
    protected StorageContainer storageContainer;

    protected Collection specimenArrayContentCollection = new HashSet();
    
    //Change for API Search   --- Ashwin 04/10/2006
    protected Boolean available;
    
    private transient boolean aliquot = false;
    
    private transient int aliquotCount;

	private transient Map aliqoutMap = new HashMap();

    /**
     * Default Constructor 
     */
    public SpecimenArray()
    {
    }

    /**
     * Constructor with action form.
     * @param actionForm abstract action form
     * @throws AssignDataException 
     */
    public SpecimenArray(AbstractActionForm actionForm) throws AssignDataException {
    	setAllValues(actionForm);
    }
    
    /**
     * @return Returns the createdBy.
     * @hibernate.many-to-one column="CREATED_BY_ID" class="edu.wustl.catissuecore.domain.User" 
     * constrained="true"
     */
    public User getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy The createdBy to set.
     */
    public void setCreatedBy(User createdBy)
    {
        this.createdBy = createdBy;
    }

    /**
     * @return Returns the specimenArrayContentCollection.
     * @hibernate.set name="specimenArrayContentCollection" table="CATISSUE_SPECI_ARRAY_CONTENT"
     * cascade="none" inverse="false" lazy="false"
     * @hibernate.collection-key column="SPECIMEN_ARRAY_ID"
     * @hibernate.collection-one-to-many class="edu.wustl.catissuecore.domain.SpecimenArrayContent"
     */
    public Collection getSpecimenArrayContentCollection()
    {
        return specimenArrayContentCollection;
    }

    /**
     * @param specimenArrayContentCollection The specimenArrayContentCollection to set.
     */
    public void setSpecimenArrayContentCollection(Collection specimenArrayContentCollection)
    {
        this.specimenArrayContentCollection = specimenArrayContentCollection;
    }

    /**
     * @return Returns the specimenArrayType.
     * @hibernate.many-to-one column="SPECIMEN_ARRAY_TYPE_ID" class="edu.wustl.catissuecore.domain.SpecimenArrayType" 
     * constrained="true"
     */
    public SpecimenArrayType getSpecimenArrayType()
    {
        return specimenArrayType;
    }

    /**
     * @param specimenArrayType The specimenArrayType to set.
     */
    public void setSpecimenArrayType(SpecimenArrayType specimenArrayType)
    {
        this.specimenArrayType = specimenArrayType;
    }

    /**
     * @return Returns the storageContainer.
     * @hibernate.many-to-one column="STORAGE_CONTAINER_ID" class="edu.wustl.catissuecore.domain.StorageContainer" 
     * constrained="true"
     */
    public StorageContainer getStorageContainer()
    {
        return storageContainer;
    }

    /**
     * @param storageContainer The storageContainer to set.
     */
    public void setStorageContainer(StorageContainer storageContainer)
    {
        this.storageContainer = storageContainer;
    }

	/**
	 * @return Returns the available.
	 * @hibernate.property name="available" type="boolean" column="AVAILABLE"
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * @param available The available to set.
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
    /**
     * @see edu.wustl.common.domain.AbstractDomainObject#setAllValues(edu.wustl.common.actionForm.AbstractActionForm)
     */
    public void setAllValues(AbstractActionForm actionForm) throws AssignDataException 
    {
    	//Change for API Search   --- Ashwin 04/10/2006
    	if (SearchUtil.isNullobject(specimenArrayType))
    	{
    		specimenArrayType = new SpecimenArrayType();
    	}
    	//Change for API Search   --- Ashwin 04/10/2006
    	if (SearchUtil.isNullobject(createdBy))
    	{
    		createdBy = new User();
    	}
    	//Change for API Search   --- Ashwin 04/10/2006
    	if (SearchUtil.isNullobject(storageContainer))
    	{
    		storageContainer = new StorageContainer();
    	}
    	//Change for API Search   --- Ashwin 04/10/2006
    	if (SearchUtil.isNullobject(available))
    	{
    		available = new Boolean(true);
    	}
    	
    	
    	if (actionForm instanceof SpecimenArrayAliquotForm) 
    	{
    		SpecimenArrayAliquotForm form = (SpecimenArrayAliquotForm) actionForm;			
			this.aliqoutMap = form.getSpecimenArrayAliquotMap();
			this.aliquotCount = Integer.parseInt(form.getAliquotCount());	
			this.id = new Long(form.getSpecimenArrayId());
    	}
    	else
    	{
	    	super.setAllValues(actionForm);
	    	SpecimenArrayForm specimenArrayForm = (SpecimenArrayForm) actionForm;
	    	specimenArrayType.setId(new Long(specimenArrayForm.getSpecimenArrayTypeId()));
	    	
	    	storageContainer.setId(new Long(specimenArrayForm.getStorageContainer()));
	    	if (createdBy == null) {
	    		createdBy = new User();
	    	}
	    	createdBy.setId(new Long(specimenArrayForm.getCreatedBy()));
	    	// done in Container class
	    	/*
	    	capacity.setOneDimensionCapacity(new Integer(specimenArrayForm.getOneDimensionCapacity()));
	    	capacity.setTwoDimensionCapacity(new Integer(specimenArrayForm.getTwoDimensionCapacity()));
	    	*/
	    	specimenArrayContentCollection = specimenArrayForm.getSpecArrayContentCollection();
	    	//SpecimenArrayUtil.getSpecimenContentCollection(specimenArrayForm.getSpecimenArrayGridContentList());
    	}
    }
    
	/**
	 * @return Returns the aliqoutMap.
	 */
	public Map getAliqoutMap()
	{
		return aliqoutMap;
	}
	
	/**
	 * @param aliqoutMap The aliqoutMap to set.
	 */
	public void setAliqoutMap(Map aliqoutMap)
	{
		this.aliqoutMap = aliqoutMap;
	}
	
	/**
	 * @return Returns the aliquotCount.
	 */
	public int getAliquotCount()
	{
		return aliquotCount;
	}
	
	/**
	 * @param aliquotCount The aliquotCount to set.
	 */
	public void setAliquotCount(int aliquotCount)
	{
		this.aliquotCount = aliquotCount;
	}
	
	
	/**
	 * @return Returns the aliquot.
	 */
	public boolean isAliquot()
	{
		return aliquot;
	}
	
	/**
	 * @param aliquot The aliquot to set.
	 */
	public void setAliquot(boolean aliquot)
	{
		this.aliquot = aliquot;
	}
}