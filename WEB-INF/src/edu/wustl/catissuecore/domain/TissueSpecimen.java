/**
 * <p>Title: TissueSpecimen Class>
 * <p>Description:  A single unit of tissue specimen 
 * that is collected or created from a Participant.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 */

package edu.wustl.catissuecore.domain;

import java.io.Serializable;

import edu.wustl.catissuecore.actionForm.AbstractActionForm;
import edu.wustl.catissuecore.actionForm.NewSpecimenForm;
import edu.wustl.common.util.logger.Logger;

/**
 * A single unit of tissue specimen 
 * that is collected or created from a Participant.
 * @hibernate.joined-subclass table="CATISSUE_TISSUE_SPECIMEN"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 * @author gautam_shetty
 */
public class TissueSpecimen extends Specimen implements Serializable
{
    private static final long serialVersionUID = 1234567890L;

    /**
     * Initial amount of specimen either directly collected from participant 
     * or created from another specimen.
     */
    protected Double quantityInGram;

    /**
     * Current available quantity of the specimen.
     */
    protected Double availableQuantityInGram;

//  Constructor
    public TissueSpecimen(AbstractActionForm form)
    {
    	super(form);
    	setAllValues(form);
    }
    
    /**
     * Returns the initial amount of specimen either directly collected from participant 
     * or created from another specimen.
     * @hibernate.property name="quantityInGram" type="double" 
     * column="QUANTITY_IN_GRAM" length="50"
     * @return the initial amount of specimen either directly collected from participant 
     * or created from another specimen.
     * @see #setQuantityInGram(Double)
     */
    public Double getQuantityInGram()
    {
        return quantityInGram;
    }

    /**
     * Sets the initial amount of specimen either directly collected from participant 
     * or created from another specimen.
     * @param quantityInGram the initial amount of specimen either directly 
     * collected from participant or created from another specimen.
     * @see #getQuantityInGram()
     */
    public void setQuantityInGram(Double quantityInGram)
    {
        this.quantityInGram = quantityInGram;
    }

    /**
     * Returns the current available quantity of the specimen.
     * @hibernate.property name="availableQuantityInGram" type="double" 
     * column="AVAILABLE_QUANTITY_IN_GRAM" length="50"
     * @return the current available quantity of the specimen.
     * @see #setAvailableQuantityInGram(Double)
     */
    public Double getAvailableQuantityInGram()
    {
        return availableQuantityInGram;
    }

    /**
     * Sets the current available quantity of the specimen. 
     * @param availableQuantityInGram the current available quantity of the specimen.
     * @see #getAvailableQuantityInGram()
     */
    public void setAvailableQuantityInGram(Double availableQuantityInGram)
    {
        this.availableQuantityInGram = availableQuantityInGram;
    }
    
    /**
     * This function Copies the data from an NewSpecimenForm object to a TissueSpecimen object.
     * @param siteForm An SiteForm object containing the information about the site.  
     * */
    public void setAllValues(AbstractActionForm abstractForm)
    {
        try
        {
        	super.setAllValues(abstractForm);
        	NewSpecimenForm form = (NewSpecimenForm) abstractForm;
        	
        	this.quantityInGram = new Double(form.getQuantity());
        	//this.availableQuantityInGram = new Double(form.get)
        }
        catch (Exception excp)
        {
            Logger.out.error(excp.getMessage());
        }
    }
}