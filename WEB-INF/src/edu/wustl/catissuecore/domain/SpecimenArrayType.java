
package edu.wustl.catissuecore.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import edu.wustl.catissuecore.actionForm.SpecimenArrayTypeForm;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.exception.AssignDataException;

/**
 * @author gautam_shetty
 * @author Ashwin Gupta 
 * @hibernate.joined-subclass table="CATISSUE_SPECIMEN_ARRAY_TYPE"
 * @hibernate.joined-subclass-key column="IDENTIFIER"
 */
public class SpecimenArrayType extends ContainerType
{
    
    protected String specimenClass;
    
    protected Collection specimenTypeCollection = new HashSet();
    
    public SpecimenArrayType()
    {
    }
    
    /**
     * Constructor with action form.
     * @param actionForm abstract action form
     * @throws AssignDataException 
     */
    public SpecimenArrayType(AbstractActionForm actionForm) throws AssignDataException {
    	setAllValues(actionForm);
    }
    
    
    /**
     * @return Returns the specimenClass.
     * @hibernate.property name="specimenClass" type="string" column="SPECIMEN_CLASS" length="50"
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
    
//    /**
//     * @return Returns the holdsSpecimenClassCollection.
//     * @hibernate.set name="holdsSpecimenClassCollection" table="CATISSUE_SPEC_ARY_TYPE_SPEC_CLASS"
//	 * cascade="save-update" inverse="false" lazy="false"
//	 * @hibernate.collection-key column="SPECIMEN_ARRAY_TYPE_ID"
//	 * @hibernate.element type="string" column="NAME" length="30"
//     */
//    public Collection getHoldsSpecimenClassCollection()
//    {
//        return holdsSpecimenClassCollection;
//    }
//    
//    /**
//     * @param holdsSpecimenClassCollection The holdsSpecimenClassCollection to set.
//     */
//    public void setHoldsSpecimenClassCollection(
//            Collection holdsSpecimenClassCollection)
//    {
//        this.holdsSpecimenClassCollection = holdsSpecimenClassCollection;
//    }
    
//    /**
//     * @return Returns the specimenClass.
//     * @hibernate.many-to-one column="SPECIMEN_CLASS_ID" class="edu.wustl.catissuecore.domain.SpecimenClass"
//     * constrained="true"
//     */
//    public SpecimenClass getSpecimenClass()
//    {
//        return specimenClass;
//    }
//
//    /**
//     * @param specimenClass The specimenClass to set.
//     */
//    public void setSpecimenClass(SpecimenClass specimenClass)
//    {
//        this.specimenClass = specimenClass;
//    }

	/**
     * @return Returns the specimenTypeCollection.
     * @hibernate.set name="specimenTypeCollection" table="CATISSUE_SPECIMEN_TYPE"
	 * cascade="save-update" inverse="false" lazy="false"
	 * @hibernate.collection-key column="SPECIMEN_ARRAY_TYPE_ID"
	 * @hibernate.element type="string" column="SPECIMEN_TYPE" length="50"
     */
    public Collection getSpecimenTypeCollection()
    {
        return specimenTypeCollection;
    }

    /**
     * @param specimenTypeCollection The specimenTypeCollection to set.
     */
    public void setSpecimenTypeCollection(Collection specimenTypeCollection)
    {
        this.specimenTypeCollection = specimenTypeCollection;
    }
    
    /**
     * Sets values from actionform to domain object.
     * @param actionForm action form to be used.  
     * @see edu.wustl.common.domain.AbstractDomainObject#setAllValues(edu.wustl.common.actionForm.AbstractActionForm)
     */
    public void setAllValues(AbstractActionForm actionForm) throws AssignDataException {
    		SpecimenArrayTypeForm specimenArrayTypeForm = (SpecimenArrayTypeForm) actionForm;
    		
    		this.id = new Long(specimenArrayTypeForm.getId());
	        this.name = specimenArrayTypeForm.getName();
	        capacity.setOneDimensionCapacity(new Integer(specimenArrayTypeForm.getOneDimensionCapacity()));
	        capacity.setTwoDimensionCapacity(new Integer(specimenArrayTypeForm.getTwoDimensionCapacity()));
	        this.comment = specimenArrayTypeForm.getComment();
	        
	        this.specimenClass = specimenArrayTypeForm.getSpecimenClass();
	        String[] specimenTypes = specimenArrayTypeForm.getSpecimenTypes();
	        if ((specimenTypes != null) && (specimenTypes.length > 0)) {
	        	for (int i = 0; i < specimenTypes.length; i++) {
	        		if (specimenTypes[i] != null) {
	        			specimenTypeCollection.add(specimenTypes[i]);
	        		}
				}
	        }
	}
}