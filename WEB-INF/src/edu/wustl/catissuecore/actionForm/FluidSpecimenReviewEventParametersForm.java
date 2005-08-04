/**
 * <p>Title: FluidSpecimenReviewEventParametersForm Class</p>
 * <p>Description:  This Class handles the Fluid Specimen Review event parameters.
 * <p> It extends the EventParametersForm class.    
 * Copyright:    Copyright (c) 2005
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on July 28th, 2005
 */
package edu.wustl.catissuecore.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.domain.AbstractDomainObject;
import edu.wustl.catissuecore.domain.FluidSpecimenReviewEventParameters;
import edu.wustl.catissuecore.util.global.ApplicationProperties;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Validator;
import edu.wustl.common.util.logger.Logger;


/**
 * @author mandar_deshmukh
 *   This Class handles the Fluid Specimen Review event parameters.
 */
public class FluidSpecimenReviewEventParametersForm extends EventParametersForm
{
	/**
     * Cell Count.
     */
	protected double cellCount;

	/**
     * Returns the cell count. 
     * @return The cell count.
     * @see #setCellCount(double)
     */
	public double getCellCount()
	{
		return cellCount;
	}

	/**
     * Sets the cell count.
     * @param cellCount the cell count.
     * @see #getCellCount()
     */
	public void setCellCount(double cellCount)
	{
		this.cellCount = cellCount;
	}

	
//	 ----- SUPERCLASS METHODS
	/* (non-Javadoc)
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#getFormId()
	 */
	public int getFormId()
	{
		return Constants.FLUID_SPECIMEN_REVIEW_EVENT_PARAMETERS_FORM_ID;
	}

	/* (non-Javadoc)
	 * @see edu.wustl.catissuecore.actionForm.AbstractActionForm#setAllValues(edu.wustl.catissuecore.domain.AbstractDomainObject)
	 */
	public void setAllValues(AbstractDomainObject abstractDomain)
	{
		super.setAllValues(abstractDomain);
		FluidSpecimenReviewEventParameters fluidSpecimenReviewEventParametersObject = (FluidSpecimenReviewEventParameters)abstractDomain ;
		this.cellCount = fluidSpecimenReviewEventParametersObject.getCellCount().doubleValue() ; 
	}
	
	/**
     * Overrides the validate method of ActionForm.
     * */
     public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
     {
     	ActionErrors errors = super.validate(mapping, request);
         Validator validator = new Validator();
         
         try
         {
 
//         	// checks the cellCount
           	if (cellCount <= 0  || Double.isNaN(cellCount) )
            {
           		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required",ApplicationProperties.getValue("fluidspecimenrevieweventparameters.cellcount")));
            }
         }
         catch(Exception excp)
         {
             Logger.out.error(excp.getMessage());
         }
         return errors;
      }
	
     /**
      * Resets the values of all the fields.
      * This method defined in ActionForm is overridden in this class.
      */
     public void reset(ActionMapping mapping, HttpServletRequest request)
     {
         reset();
         this.cellCount = 0.0;
     }
     

	
}
