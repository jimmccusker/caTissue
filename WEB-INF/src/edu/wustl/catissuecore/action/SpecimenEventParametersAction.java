/**
 * <p>Title: SpecimenEventParametersAction Class>
 * <p>Description:	This class initializes the fields in the SpecimenEventParametersAction Add/Edit webpage.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on July 28, 2005
 */

package edu.wustl.catissuecore.action;

import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.EventParametersForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.UserBizLogic;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.util.logger.Logger;

/**
 * This class initializes the fields in the SpecimenEventParameters Add/Edit webpage.
 * It is a parent class for some Event Parameter Classes.
 * @author mandar deshmukh
 */

public class SpecimenEventParametersAction  extends SecureAction
{
	/**
	 * This method sets all the common parameters for the SpecimenEventParameter pages
	 * @param request HttpServletRequest instance in which the data will be set. 
	 * @throws Exception Throws Exception. Helps in handling exceptions at one common point.
	 */
	private void setCommonRequestParameters(HttpServletRequest request) throws Exception
	{
        //Gets the value of the operation parameter.
        String operation = request.getParameter(Constants.OPERATION);

        //Sets the operation attribute to be used in the Add/Edit FrozenEventParameters Page. 
        request.setAttribute(Constants.OPERATION, operation);
        
        //Sets the minutesList attribute to be used in the Add/Edit FrozenEventParameters Page.
        request.setAttribute(Constants.MINUTESLIST, Constants.MINUTESARRAY);

        //Sets the hourList attribute to be used in the Add/Edit FrozenEventParameters Page.
        request.setAttribute(Constants.HOURLIST, Constants.HOURARRAY);
        
        //The id of specimen of this event.
        String specimenId = request.getParameter(Constants.SPECIMEN_ID); 
        request.setAttribute(Constants.SPECIMEN_ID, specimenId);
        Logger.out.debug("\t\t************************************ : "+specimenId );
        String isRNA = request.getParameter(Constants.IS_RNA); 
        request.setAttribute(Constants.IS_RNA, isRNA);
        
        //The Add/Edit status message
        request.setAttribute(Constants.STATUS_MESSAGE_KEY, request.getAttribute(Constants.STATUS_MESSAGE_KEY));
   
       	UserBizLogic userBizLogic = (UserBizLogic)BizLogicFactory.getBizLogic(Constants.USER_FORM_ID);
    	Collection coll =  userBizLogic.getUsers();
    	
    	request.setAttribute(Constants.USERLIST, coll);
    	
    	// This method will be overridden by the sub classes
    	setRequestParameters( request);
        	
	}
    /**
     * Overrides the executeSecureAction method of SecureAction class.
     * */
    protected ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    	setCommonRequestParameters(request);

    	EventParametersForm eventParametersForm = (EventParametersForm)form;
    	
    	//	 if operation is add
    	if(eventParametersForm.isAddOperation())
    	{
	    	SessionDataBean sessionData = getSessionData(request);
	    	if(sessionData!=null && sessionData.getUserId()!=null)
	    	{
	    		long userId = sessionData.getUserId().longValue();
	    		eventParametersForm.setUserId(userId);
	    	}

	    	// set the current Date and Time for the event.
			Calendar cal = Calendar.getInstance();
			eventParametersForm.setDateOfEvent(Utility.parseDateToString(cal.getTime(), Constants.DATE_PATTERN_MM_DD_YYYY));
			eventParametersForm.setTimeInHours(Integer.toString(cal.get(Calendar.HOUR_OF_DAY)));
			eventParametersForm.setTimeInMinutes(Integer.toString(cal.get(Calendar.MINUTE)));
    	}
    	
    	return mapping.findForward((String)request.getParameter(Constants.PAGEOF));
    }
    
    //  This method will be overridden by the sub classes
    // It is called from setCommonRequestParameters().
    // It will be used to set the SubClass specific parameters. 
    protected void setRequestParameters(HttpServletRequest request) throws Exception
	{
	}
}