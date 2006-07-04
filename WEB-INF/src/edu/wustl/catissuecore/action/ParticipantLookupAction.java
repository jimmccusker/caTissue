/**
 * <p>Title: ParticipantLookupAction Class>
 * <p>Description:	This Action Class invokes the Participant Lookup Algorithm and gets matching participants</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author vaishali_khandelwal
 * @Created on May 19, 2006
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.ParticipantBizLogic;
import edu.wustl.catissuecore.domain.DomainObjectFactory;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.factory.AbstractDomainObjectFactory;
import edu.wustl.common.factory.MasterFactory;
import edu.wustl.common.lookup.DefaultLookupResult;
import edu.wustl.common.util.logger.Logger;

public class ParticipantLookupAction extends BaseAction
{
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
	{
		AbstractDomainObject abstractDomain = null;
		ActionMessages messages=null;
		String target=null;
		
		AbstractActionForm abstractForm = (AbstractActionForm) form;
		
		AbstractDomainObjectFactory abstractDomainObjectFactory = (AbstractDomainObjectFactory) MasterFactory
				.getFactory(DomainObjectFactory.class.getName());
		
		
		abstractDomain = abstractDomainObjectFactory.getDomainObject(abstractForm.getFormId(),
				abstractForm);
		Participant participant = (Participant) abstractDomain;
		ParticipantBizLogic bizlogic = (ParticipantBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.PARTICIPANT_FORM_ID);
		Logger.out.debug("Participant Id :"+request.getParameter("participantId"));
		//checks weather participant is selected from the list and so forwarding to next action instead of participant lookup.
		if(request.getParameter("participantId")!=null &&!request.getParameter("participantId").equals("null")&&!request.getParameter("participantId").equals("")&&!request.getParameter("participantId").equals("0"))
		{
			Logger.out.info("inside the participant mapping");
			return mapping.findForward("participantSelect");
		}
		List participantList=bizlogic.getParticipantLookupData(participant);
		Logger.out.debug("Participant List Size:"+participantList.size());
		//if any matching participants are there then show the participants otherwise add the participant
		if (participantList.size() > 0)
		{
			messages=new ActionMessages();
			messages.add(ActionErrors.GLOBAL_MESSAGE,new ActionMessage("participant.lookup.success","Submit was not successful because some matching participants found."));
   			//Creating the column headings for Data Grid
			List columnList = getColumnHeadingList(bizlogic);
			request.setAttribute(Constants.SPREADSHEET_COLUMN_LIST, columnList);
			
			//Getitng the Participant List in Data Grid Format
			List participantDisplayList=getParticipantDisplayList(participantList);
			request.setAttribute(Constants.SPREADSHEET_DATA_LIST, participantDisplayList);
			target=Constants.PARTICIPANT_LOOKUP_SUCCESS;
		}
		//	if no participant match found then add the participant in system
		else
		{
			target=Constants.PARTICIPANT_ADD_FORWARD;
		}
		
		//setting the Submitted_for and Forward_to variable in request
		if(request.getParameter(Constants.SUBMITTED_FOR)!=null && !request.getParameter(Constants.SUBMITTED_FOR).equals(""))
		{
			request.setAttribute(Constants.SUBMITTED_FOR,request.getParameter(Constants.SUBMITTED_FOR));
		}
		if(request.getParameter(Constants.FORWARD_TO)!=null && !request.getParameter(Constants.FORWARD_TO).equals(""))
		{
			request.setAttribute(Constants.FORWARD_TO,request.getParameter(Constants.FORWARD_TO));
			
		}
		
		request.setAttribute("participantId","");
		if (messages != null)
        {
            saveMessages(request,messages);
        }
		Logger.out.debug("target:"+target);
		return (mapping.findForward(target));
	}
	/**
	 * This Function creates the Column Headings for Data Grid
	 * @return List Column List
	 */
	private List getColumnHeadingList(ParticipantBizLogic bizlogic) throws Exception
	{
		//Creating the column list which is used in Data grid to display column headings
		String[] columnHeaderList = new String[]{Constants.PARTICIPANT_SYSTEM_IDENTIFIER, Constants.PARTICIPANT_LAST_NAME,Constants.PARTICIPANT_FIRST_NAME,Constants.PARTICIPANT_MIDDLE_NAME,Constants.PARTICIPANT_BIRTH_DATE,Constants.PARTICIPANT_DEATH_DATE,Constants.PARTICIPANT_VITAL_STATUS,Constants.PARTICIPANT_GENDER,Constants.PARTICIPANT_SOCIAL_SECURITY_NUMBER};
		List columnList = new ArrayList();
		Logger.out.info("column List header size ;"+columnHeaderList.length);	
		for (int i = 0; i < columnHeaderList.length; i++)
		{
			columnList.add(columnHeaderList[i]);
		}
		Logger.out.info("column List size ;"+columnList.size());
		List displayList=bizlogic.getColumnList(columnList);
		displayList.add(Constants.PARTICIPANT_PROBABLITY_MATCH);
		return displayList;
	}
	
	/**
	 * This functions creates Particpant List with each participant informaton  with the match probablity 
	 * @param ParticipantList
	 * @return List of Participant Information  List
	 */
	private List getParticipantDisplayList(List ParticipantList)
	{
		List participantDisplayList=new ArrayList();
		Iterator itr=ParticipantList.iterator();
		while(itr.hasNext())
		{
			DefaultLookupResult result=(DefaultLookupResult)itr.next();
			Participant participant=(Participant)result.getObject();
			
			List participantInfo=new ArrayList();
			participantInfo.add(participant.getSystemIdentifier());
			
			if(participant.getLastName()!=null)
			{
				participantInfo.add(participant.getLastName());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getFirstName()!=null)
			{
				participantInfo.add(participant.getFirstName());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getMiddleName()!=null)
			{
				participantInfo.add(participant.getMiddleName());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getBirthDate()!=null)
			{
				participantInfo.add(participant.getBirthDate());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getDeathDate()!=null)
			{
				participantInfo.add(participant.getDeathDate());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getVitalStatus()!=null)
			{
				participantInfo.add(participant.getVitalStatus());
			}
			else
			{
				participantInfo.add("");
			}
			
			if(participant.getGender()!=null)
			{
				participantInfo.add(participant.getGender());
			}
			else
			{
				participantInfo.add("");
			}
			
			/*if(participant.getSexGenotype()!=null)
			{
				participantInfo.add(participant.getSexGenotype());
			}
			else
			{
				participantInfo.add("");
			}*/
			
			/*if(participant.getRace()!=null)
			{
				participantInfo.add(participant.getRace());
			}
			else
			{
				participantInfo.add("");
			}
			*/
			/*if(participant.getEthnicity()!=null)
			{
				participantInfo.add(participant.getEthnicity());
			}
			else
			{
				participantInfo.add("");
			}
			*/
			if(participant.getSocialSecurityNumber()!=null)
			{
				participantInfo.add(participant.getSocialSecurityNumber());
			}
			else
			{
				participantInfo.add("");
			}
			
			participantInfo.add(result.getProbablity().toString()+" %");
			
			participantDisplayList.add(participantInfo);
			
		}
		return participantDisplayList;
	}
}