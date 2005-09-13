/**
 * <p>Title: SpecimenCollectionGroupAction Class>
 * <p>Description:	SpecimenCollectionGroupAction initializes the fields in the 
 * New Specimen Collection Group page.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ajay Sharma
 * @version 1.00
 */

package edu.wustl.catissuecore.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.SpecimenCollectionGroupForm;
import edu.wustl.catissuecore.bizlogic.AbstractBizLogic;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.util.logger.Logger;


/**
 * SpecimenCollectionGroupAction initializes the fields in the 
 * New Specimen Collection Group page.
 * @author ajay_sharma
 */
public class SpecimenCollectionGroupAction  extends SecureAction
{   
    /**
     * Overrides the execute method of Action class.
     */
	public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	SpecimenCollectionGroupForm  specimenCollectionGroupForm = (SpecimenCollectionGroupForm)form;
    	
		try
		{
			boolean isOnChange = false; 
			String str = request.getParameter("isOnChange");
			if(str!=null)
			{
				if(str.equals("true"))
					isOnChange = true; 
			}
			
			// get list of Protocol title.
			AbstractBizLogic bizLogic = BizLogicFactory.getBizLogic(Constants.SPECIMEN_COLLECTION_GROUP_FORM_ID);

		    //populating protocolist bean.
			String sourceObjectName = CollectionProtocol.class.getName();
			String [] displayNameFields = {"title"};
			String valueField = Constants.SYSTEM_IDENTIFIER;
		  	List list = bizLogic.getList(sourceObjectName,displayNameFields,valueField);
			request.setAttribute(Constants.PROTOCOL_LIST, list);
		
           	//Populating the Site Type bean
		   	sourceObjectName = Site.class.getName();
		   	String siteDisplaySiteFields[] = {"name"};
		   	list = bizLogic.getList(sourceObjectName,siteDisplaySiteFields,valueField);
		   	request.setAttribute(Constants.SITELIST, list);

		   	//Populating the participants registered to a given protocol
			loadPaticipants(specimenCollectionGroupForm.getCollectionProtocolId() , bizLogic, request);
			
			//Populating the protocol participants id registered to a given protocol
			loadPaticipantNumberList(specimenCollectionGroupForm.getCollectionProtocolId(),bizLogic,request);
			
			//Populating the Collection Protocol Events
			loadCollectionProtocolEvent(specimenCollectionGroupForm.getCollectionProtocolId(),bizLogic,request);
			
			//Populating the participants Medical Identifier for a given participant
			loadParticipantMedicalIdentifier(specimenCollectionGroupForm.getParticipantId(),bizLogic, request);
			
			//Load Clinical status for a given study calander event point
			List calendarEventPointList = bizLogic.retrieve(CollectionProtocolEvent.class.getName(),
											Constants.SYSTEM_IDENTIFIER,
											new Long(specimenCollectionGroupForm.getCollectionProtocolEventId()));
			if(isOnChange && !calendarEventPointList.isEmpty())
			{
				CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)calendarEventPointList.get(0);
				specimenCollectionGroupForm.setClinicalStatus(collectionProtocolEvent.getClinicalStatus());
			}
			
			// populating clinical Diagnosis field 
			List clinicalDiagnosisList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_CLINICAL_DIAGNOSIS);
			request.setAttribute(Constants.CLINICAL_DIAGNOSIS_LIST, clinicalDiagnosisList);

			// populating clinical Status field 
	        List clinicalStatusList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_CLINICAL_STATUS);
	    	request.setAttribute(Constants.CLINICAL_STATUS_LIST, clinicalStatusList);
	    	
	    	//Sets the activityStatusList attribute to be used in the Site Add/Edit Page.
	        request.setAttribute(Constants.ACTIVITYSTATUSLIST, Constants.ACTIVITY_STATUS_VALUES);
		}
		catch(Exception exc)
		{
			Logger.out.error(exc.getMessage(),exc);
			mapping.findForward(request.getParameter(Constants.FAILURE));
		}
        return mapping.findForward(request.getParameter(Constants.PAGEOF));
    }
    
	private void loadPaticipants(long protocolID, AbstractBizLogic bizLogic, HttpServletRequest request) throws Exception
	{
		//get list of Participant's names
		String sourceObjectName = CollectionProtocolRegistration.class.getName();
	  	String [] displayParticipantFields = {"participant.lastName" , "participant.firstName"};
	  	String valueField = "participant."+Constants.SYSTEM_IDENTIFIER;
	  	String whereColumnName[] = {"collectionProtocol."+Constants.SYSTEM_IDENTIFIER};
	  	String whereColumnCondition[] = {"="};
	  	Object[] whereColumnValue = {new Long(protocolID)};
	  	String joinCondition = Constants.AND_JOIN_CONDITION;
	  	String separatorBetweenFields = ", ";
	  	
	  	List list = bizLogic.getList(sourceObjectName, displayParticipantFields, valueField, whereColumnName,
				  whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields);
	
	  	Logger.out.debug("Paticipants List"+list);
	  	request.setAttribute(Constants.PARTICIPANT_LIST, list);
	}
    
    
	private void loadPaticipantNumberList(long protocolID, AbstractBizLogic bizLogic, HttpServletRequest request) throws Exception
	{
		//get list of Participant's names
		String sourceObjectName = CollectionProtocolRegistration.class.getName();
		String displayParticipantNumberFields[] = {"protocolParticipantIdentifier"};
		String valueField = "protocolParticipantIdentifier";
		String whereColumnName[] = {"collectionProtocol."+Constants.SYSTEM_IDENTIFIER, "protocolParticipantIdentifier"};
		String whereColumnCondition[] = {"=","!="};
		Object[] whereColumnValue = {new Long(protocolID),"null"};
		String joinCondition = Constants.AND_JOIN_CONDITION;
		String separatorBetweenFields = "";
			
		List list = bizLogic.getList(sourceObjectName, displayParticipantNumberFields, valueField, whereColumnName,
					whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields);
		
		Logger.out.debug("Paticipant Number List"+list);
		request.setAttribute(Constants.PROTOCOL_PARTICIPANT_NUMBER_LIST, list);
	}    
    
	private void loadCollectionProtocolEvent(long protocolID, AbstractBizLogic bizLogic, HttpServletRequest request) throws Exception
	{
		String sourceObjectName = CollectionProtocolEvent.class.getName();
		String displayEventFields[] = {"studyCalendarEventPoint"};
		String valueField = "systemIdentifier";
		String whereColumnName[] = {"collectionProtocol."+Constants.SYSTEM_IDENTIFIER};
		String whereColumnCondition[] = {"="};
		Object[] whereColumnValue = {new Long(protocolID)};
		String joinCondition = Constants.AND_JOIN_CONDITION;
		String separatorBetweenFields = "";
					
		List list = bizLogic.getList(sourceObjectName, displayEventFields, valueField, whereColumnName,
					whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields);
		
		Logger.out.debug("Collection Protocol Event List"+list);
		request.setAttribute(Constants.STUDY_CALENDAR_EVENT_POINT_LIST, list);
	}

	private void loadParticipantMedicalIdentifier(long participantID, AbstractBizLogic bizLogic, HttpServletRequest request) throws Exception
	{
		//get list of Participant's names
		String sourceObjectName = ParticipantMedicalIdentifier.class.getName();
		String displayEventFields[] = {"medicalRecordNumber"};
		String valueField = Constants.SYSTEM_IDENTIFIER;
		String whereColumnName[] = {"participant."+Constants.SYSTEM_IDENTIFIER, "medicalRecordNumber"};
		String whereColumnCondition[] = {"=","!="};
		Object[] whereColumnValue = {new Long(participantID),"null"};
		String joinCondition = Constants.AND_JOIN_CONDITION;
		String separatorBetweenFields = "";
						
		List list = bizLogic.getList(sourceObjectName, displayEventFields, valueField, whereColumnName,
					whereColumnCondition, whereColumnValue, joinCondition, separatorBetweenFields);
						
		request.setAttribute(Constants.PARTICIPANT_MEDICAL_IDNETIFIER_LIST, list);
	}
}