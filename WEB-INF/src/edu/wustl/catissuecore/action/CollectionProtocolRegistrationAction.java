/**
 * <p>Title: DepartmentAction Class</p>
 * <p>Description:	This class initializes the fields in the Department Add/Edit webpage.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ajay Sharma
 * @version 1.00
 * Created on May 23rd, 2005
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.CollectionProtocolRegistrationForm;
import edu.wustl.catissuecore.bizlogic.AbstractBizLogic;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Variables;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.util.logger.Logger;

/**
 * This class initializes the fields in the User Add/Edit webpage.
 * @author ajay_sharma
 */

public class CollectionProtocolRegistrationAction extends SecureAction
{
	/**
	 * Overrides the execute method of Action class.
	 * Sets the various fields in Participant Registration Add/Edit webpage.
	 * */
	public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//Gets the value of the operation parameter.
		String operation = request.getParameter(Constants.OPERATION);

		//Sets the operation attribute to be used in the Add/Edit User Page. 
		request.setAttribute(Constants.OPERATION, operation);
		
		//Sets the pageOf attribute
        String pageOf  = request.getParameter(Constants.PAGEOF);
        
        request.setAttribute(Constants.PAGEOF,pageOf);

        String reqPath = request.getParameter(Constants.REQ_PATH);
		if (reqPath != null)
			request.setAttribute(Constants.REQ_PATH, reqPath);
		
		Logger.out.debug("PartProtReg redirect :---------- "+ reqPath  );
        
        // ----------------add new end-----
        
		AbstractBizLogic bizLogic = BizLogicFactory.getBizLogic(Constants.COLLECTION_PROTOCOL_REGISTRATION_FORM_ID);

		//get list of Protocol title.
		String sourceObjectName = CollectionProtocol.class.getName();
		String[] displayNameFields = {"title"};
		String valueField = Constants.SYSTEM_IDENTIFIER;
		List list = bizLogic.getList(sourceObjectName, displayNameFields, valueField, true);
		request.setAttribute(Constants.PROTOCOL_LIST, list);

		//get list of Participant's names
		sourceObjectName = Participant.class.getName();
		String[] participantsFields = {"lastName","firstName","birthDate","socialSecurityNumber"};
		String[] whereColumnName = {"lastName","firstName","birthDate","socialSecurityNumber"};
		String[] whereColumnCondition;
		Object[] whereColumnValue;
		
		// get Database name and set conditions 
		if(Variables.databaseName.equals(Constants.MYSQL_DATABASE))
		{
			whereColumnCondition = new String[]{"!=","!=","is not","is not"};
			whereColumnValue = new String[]{"","",null,null};
		}
		else
		{
			// for ORACLE
			whereColumnCondition = new String[]{"is not null","is not null","is not null","is not null"};
			whereColumnValue = new String[]{};
		}
		
		String joinCondition = Constants.OR_JOIN_CONDITION;
		String separatorBetweenFields = ", ";
		
		list = bizLogic.getList(sourceObjectName, participantsFields, valueField, whereColumnName,
	            whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields, false);
		
		
		//get list of Disabled Participants
		String[] participantsFields2 = {Constants.SYSTEM_IDENTIFIER};
		String[] whereColumnName2 = {"activityStatus"};
		String[] whereColumnCondition2 = {"="};
		String[] whereColumnValue2 = {Constants.ACTIVITY_STATUS_DISABLED};
		String joinCondition2 = Constants.AND_JOIN_CONDITION;
		String separatorBetweenFields2 = ",";
		
		List listOfDisabledParticipant = bizLogic.getList(sourceObjectName, participantsFields2, valueField, whereColumnName2,
	            whereColumnCondition2, whereColumnValue2, joinCondition2, separatorBetweenFields2, false);
		
		//removing disabled participants from the list of Participants
		list=removeDisabledParticipant(list, listOfDisabledParticipant);
		
		// Sets the participantList attribute to be used in the Site Add/Edit Page.
		request.setAttribute(Constants.PARTICIPANT_LIST, list);
		
		//Sets the activityStatusList attribute to be used in the Site Add/Edit Page.
        request.setAttribute(Constants.ACTIVITYSTATUSLIST, Constants.ACTIVITY_STATUS_VALUES);
	    
        if(request.getAttribute(Constants.PARTICIPANT_ID)!=null)
        {
            String participantId=(String)request.getAttribute(Constants.PARTICIPANT_ID);
            if((request.getParameter("firstName").trim().length()>0) || (request.getParameter("lastName").trim().length()>0) || (request.getParameter("birthDate").trim().length()>0) ||( (request.getParameter("socialSecurityNumberPartA").trim().length()>0) && (request.getParameter("socialSecurityNumberPartB").trim().length()>0) && (request.getParameter("socialSecurityNumberPartC").trim().length()>0))) 
            {    
                CollectionProtocolRegistrationForm cprForm=(CollectionProtocolRegistrationForm)form;
                cprForm.setParticipantID(Long.parseLong(participantId));
                cprForm.setCheckedButton(true);
            }
        }
                
		return mapping.findForward(pageOf);
	}
	
	private List removeDisabledParticipant(List listOfParticipant, List listOfDisabledParticipant)
	{
	   List listOfActiveParticipant=new ArrayList();
	   
	   Logger.out.debug("No. Of Participants ~~~~~~~~~~~~~~~~~~~~~~~>"+listOfParticipant.size());
	   Logger.out.debug("No. Of Disabled Participants ~~~~~~~~~~~~~~~~~~~~~~~>"+listOfDisabledParticipant.size());
	   
	   listOfActiveParticipant.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
	   for(int i=0; i<listOfParticipant.size(); i++)
	   {
	       NameValueBean participantBean =(NameValueBean)listOfParticipant.get(i);
	       boolean isParticipantDisable=false;
	       
	       if(Long.parseLong(participantBean.getValue()) == -1)
	       {
	           //listOfActiveParticipant.add(listOfParticipant.get(i));
	           continue;
	       }
	       
	       for(int j=0; j<listOfDisabledParticipant.size(); j++)
	       {
	           if(Long.parseLong(((NameValueBean)listOfDisabledParticipant.get(j)).getValue()) == -1)
	               continue;
	          
	           NameValueBean disabledParticipant = (NameValueBean)listOfDisabledParticipant.get(j);
	           if(participantBean.getValue().equals(disabledParticipant.getValue()))
	           {
	               isParticipantDisable=true;
	               break;
	           }
	       }
	       if(isParticipantDisable==false)
	           listOfActiveParticipant.add(listOfParticipant.get(i));
	   }
	   
	   Logger.out.debug("No.Of Active Participants ~~~~~~~~~~~~~~~~~~~~~~~>"+listOfActiveParticipant.size());
	   
	   return listOfActiveParticipant;
	}
}