package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.ViewSurgicalPathologyReportForm;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.pathology.DeidentifiedSurgicalPathologyReport;
import edu.wustl.catissuecore.domain.pathology.IdentifiedSurgicalPathologyReport;
import edu.wustl.catissuecore.reportloader.ReportLoaderUtil;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.security.SecurityManager;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.logger.Logger;
import gov.nih.nci.security.authorization.domainobjects.Role;

/**
 * @author vijay_pande
 * Action class to show Surgical Pathology  Report
 */
public class ViewSurgicalPathologyReportAction extends BaseAction
{

	private ViewSurgicalPathologyReportForm viewSPR;
	/**
	 * @see edu.wustl.common.action.BaseAction#executeAction(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		viewSPR=(ViewSurgicalPathologyReportForm)form;
		String pageOf = viewSPR.getPageOf();
		String operation = viewSPR.getOperation();
		String submittedFor=viewSPR.getSubmittedFor();
		String forwardTo=viewSPR.getForwardTo();
		Long id=new Long((String)request.getParameter(Constants.SYSTEM_IDENTIFIER));
		viewSPR.setId(id);
		boolean isAuthorized;
		if(id!=0 && operation.equalsIgnoreCase(Constants.VIEW_SURGICAL_PATHOLOGY_REPORT))
		{
			isAuthorized=isAuthorized(getSessionBean(request));
			retrieveAndSetObject(pageOf,id,isAuthorized, request);
		}
		viewSPR.setPageOf(pageOf);
		viewSPR.setOperation(operation);
		viewSPR.setSubmittedFor(submittedFor);
		viewSPR.setForwardTo(forwardTo);
		return mapping.findForward(Constants.SUCCESS);
	}
	
	/**
	 * @param pageOf pageOf variable to find out domain object 
	 * @param id Identifier of the domain object
	 * @param request HttpServletRequest object
	 * @throws DAOException exception occured while DB handling
	 * This method retrives the appropriate SurgicalPathologyReport object and set values of ViewSurgicalPathologyReportForm object
	 */
	private void retrieveAndSetObject(String pageOf,long id,boolean isAuthorized, HttpServletRequest request) throws DAOException
	{
		String className;
		String colName=new String(Constants.SYSTEM_IDENTIFIER);
		long colValue=id;	
		DefaultBizLogic defaultBizLogic=new DefaultBizLogic();		
		//if page is of Specimen Collection group then the domain object is SpecimenCollectionGroup
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_SPECIMEN_COLLECTION_GROUP))
		{
			className=SpecimenCollectionGroup.class.getName();
			List scgList=defaultBizLogic.retrieve(className, colName, colValue);
			SpecimenCollectionGroup scg=(SpecimenCollectionGroup)scgList.get(0);
			if(isAuthorized)
			{
				viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				viewSPR.setParticipant(scg.getCollectionProtocolRegistration().getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
			else
			{
				viewSPR.setIdentifiedReportTextContent("You are not authorized to view this report");
				viewSPR.setParticipant(scg.getCollectionProtocolRegistration().getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
		}
		//if page is of Specimen then the domain object is Specimen
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_SPECIMEN))
		{
			className=Specimen.class.getName();
			List specimenList=defaultBizLogic.retrieve(className, colName, colValue);
			Specimen specimen=(Specimen)specimenList.get(0);
			SpecimenCollectionGroup scg=specimen.getSpecimenCollectionGroup();
			viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
			if(isAuthorized)
			{
				viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				viewSPR.setParticipant(scg.getCollectionProtocolRegistration().getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
			else
			{
				viewSPR.setParticipant(scg.getCollectionProtocolRegistration().getParticipant());
				viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
			}
		}
		// if page is of Participant then the domain object is Participant
		// Also needs to retrieve a list of SurgicalPathologyReport objects (One-to-Many relationship)
		if(pageOf.equalsIgnoreCase(Constants.PAGEOF_PARTICIPANT))
		{
			className=Participant.class.getName();
			List participantList=defaultBizLogic.retrieve(className, colName, colValue);
			Participant participant=(Participant)participantList.get(0);
			viewSPR.setParticipant(participant);
			List scgList=ReportLoaderUtil.getSCGList(participant);
			if(scgList.size()>0)
			{
				SpecimenCollectionGroup scg=(SpecimenCollectionGroup)scgList.get(0);
				if(isAuthorized)
				{
					viewSPR.setAllValues(scg.getIdentifiedSurgicalPathologyReport());
				}
				else
				{
					viewSPR.setParticipant(scg.getCollectionProtocolRegistration().getParticipant());
					viewSPR.setDeIdentifiedReport(scg.getDeIdentifiedSurgicalPathologyReport());
				}
			}
			else
			{
				viewSPR.setIdentifiedReport(new IdentifiedSurgicalPathologyReport());
				viewSPR.setDeIdentifiedReport(new DeidentifiedSurgicalPathologyReport());
			}
			viewSPR.setReportIdList(getReportIdList(scgList));
		}
	}
	
	/**
	 * @param scgCollection A collection of SpecimenCollectionGroup Id
	 * @return List of SurgicalPathologyReport Id
	 * @throws DAOException Exception occured while handling DB
	 */
	private List getReportIdList(List scgCollection)throws DAOException
	{
		
		List reportIDList=new ArrayList();		
		if(scgCollection!=null)
		{
			Iterator iter=scgCollection.iterator();
			SpecimenCollectionGroup scg;
			while(iter.hasNext())
			{
				scg=(SpecimenCollectionGroup)iter.next();
				if(scg.getIdentifiedSurgicalPathologyReport()!=null)
				{
					NameValueBean nb=new NameValueBean(scg.getIdentifiedSurgicalPathologyReport().getAccessionNumber(),scg.getIdentifiedSurgicalPathologyReport().getId().toString());
					reportIDList.add(nb);
				}
			}		
		}
		return reportIDList;
	}
	
	/**
	 * This method is to retrieve sessionDataBean from request object
	 * @param request HttpServletRequest object
	 * @return sessionBean SessionDataBean object
	 */
	private SessionDataBean getSessionBean(HttpServletRequest request)
	{
		try
		{
			SessionDataBean sessionBean = (SessionDataBean) request.getSession().getAttribute(Constants.SESSION_DATA);
			return sessionBean;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	/**
	 * This method verifies wthere the user is 
	 * @param sessionBean
	 * @return
	 * @throws Exception
	 */
	private boolean isAuthorized(SessionDataBean sessionBean) throws Exception
	{
		SecurityManager sm=SecurityManager.getInstance(this.getClass());
		try
		{
			Role role=sm.getUserRole(sessionBean.getUserId());
			if(role.getName().equalsIgnoreCase(Constants.ROLE_ADMINISTRATOR))
			{
				return true;
			}		
		}
		catch(SMException ex)
		{
			Logger.out.info("Reviewer's Role not found!");
		}
		return false;
	}
}


