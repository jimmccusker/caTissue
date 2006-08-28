/**
 * <p>Title: UserHDAO Class>
 * <p>Description:	UserHDAO is used to add user information into the database using Hibernate.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ajay Sharma
 * @version 1.00
 * Created on Apr 13, 2005
 */

package edu.wustl.catissuecore.bizlogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wustl.catissuecore.domain.ClinicalReport;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.integration.IntegrationManager;
import edu.wustl.catissuecore.integration.IntegrationManagerFactory;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.dao.AbstractDAO;
import edu.wustl.common.dao.DAO;
import edu.wustl.common.dao.DAOFactory;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.security.SecurityManager;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.security.exceptions.UserNotAuthorizedException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 * UserHDAO is used to add user information into the database using Hibernate.
 * @author kapil_kaveeshwar
 */
public class SpecimenCollectionGroupBizLogic extends IntegrationBizLogic
{
	/**
	 * Saves the user object in the database.
	 * @param obj The user object to be saved.
	 * @param session The session in which the object is saved.
	 * @throws DAOException 
	 */
	protected void insert(Object obj, DAO dao, SessionDataBean sessionDataBean) throws DAOException, UserNotAuthorizedException
	{
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) obj;
		
		Object siteObj = dao.retrieve(Site.class.getName(), specimenCollectionGroup.getSite().getId());
		if (siteObj != null)
		{
			// check for closed Site
			checkStatus(dao,specimenCollectionGroup.getSite(), "Site" );

			specimenCollectionGroup.setSite((Site)siteObj);
		}
		
		Object  collectionProtocolEventObj =  dao.retrieve(CollectionProtocolEvent.class.getName(), specimenCollectionGroup.getCollectionProtocolEvent().getId());
		if(collectionProtocolEventObj!=null)
		{
			CollectionProtocolEvent cpe = (CollectionProtocolEvent)collectionProtocolEventObj;
			
			//check for closed CollectionProtocol
			checkStatus(dao, cpe.getCollectionProtocol(), "Collection Protocol" );

			specimenCollectionGroup.setCollectionProtocolEvent(cpe);
		}
		
		setClinicalReport(dao, specimenCollectionGroup);
		setCollectionProtocolRegistration(dao, specimenCollectionGroup, null);
		
		dao.insert(specimenCollectionGroup,sessionDataBean, true, true);
		if(specimenCollectionGroup.getClinicalReport()!=null)
			dao.insert(specimenCollectionGroup.getClinicalReport(),sessionDataBean, true, true);
		
		try
        {
            SecurityManager.getInstance(this.getClass()).insertAuthorizationData(
            		null, getProtectionObjects(specimenCollectionGroup), getDynamicGroups(specimenCollectionGroup));
        }
		catch (SMException e)
        {
			throw handleSMException(e);
        }
	}
	
	private Set getProtectionObjects(AbstractDomainObject obj)
    {
        Set protectionObjects = new HashSet();
        
        SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) obj;
        protectionObjects.add(specimenCollectionGroup);
        
        Logger.out.debug(protectionObjects.toString());
        return protectionObjects;
    }

    private String[] getDynamicGroups(AbstractDomainObject obj) throws SMException
    {
        SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) obj;
        String[] dynamicGroups = new String[1];
        
        dynamicGroups[0] = SecurityManager.getInstance(this.getClass()).getProtectionGroupByName(
        			specimenCollectionGroup.getCollectionProtocolRegistration(),
					Constants.getCollectionProtocolPGName(null));
        Logger.out.debug("Dynamic Group name: "+dynamicGroups[0]);
        return dynamicGroups;
        
    }

	/**
	 * Updates the persistent object in the database.
	 * @param obj The object to be updated.
	 * @param session The session in which the object is saved.
	 * @throws DAOException 
	 */
	protected void update(DAO dao, Object obj, Object oldObj,SessionDataBean sessionDataBean) throws DAOException, UserNotAuthorizedException 
	{
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) obj;
		SpecimenCollectionGroup oldspecimenCollectionGroup = (SpecimenCollectionGroup) oldObj;
		
		// Check for different closed site
		if(!specimenCollectionGroup.getSite().getId().equals( oldspecimenCollectionGroup.getSite().getId()))
		{
			checkStatus(dao,specimenCollectionGroup.getSite(), "Site" );
		}
		//site check complete
		
		// -- check for closed CollectionProtocol
		List list  =  dao.retrieve(CollectionProtocolEvent.class.getName(), Constants.SYSTEM_IDENTIFIER, specimenCollectionGroup.getCollectionProtocolEvent().getId());
		if(!list.isEmpty())
		{
			// check for closed CollectionProtocol
			CollectionProtocolEvent cpe = (CollectionProtocolEvent)list.get(0);
			if(!cpe.getCollectionProtocol().getId().equals(oldspecimenCollectionGroup.getCollectionProtocolEvent().getCollectionProtocol().getId()))
				checkStatus(dao,cpe.getCollectionProtocol(), "Collection Protocol" );
			
			specimenCollectionGroup.setCollectionProtocolEvent((CollectionProtocolEvent)list.get(0));
		}
		//CollectionProtocol check complete.
		
		
		setCollectionProtocolRegistration(dao, specimenCollectionGroup, oldspecimenCollectionGroup);
		
		dao.update(specimenCollectionGroup, sessionDataBean, true, true, false);
		dao.update(specimenCollectionGroup.getClinicalReport(), sessionDataBean, true, true, false);
		
		//Audit.
		dao.audit(obj, oldObj, sessionDataBean, true);
		SpecimenCollectionGroup oldSpecimenCollectionGroup = (SpecimenCollectionGroup) oldObj;
		dao.audit(specimenCollectionGroup.getClinicalReport(), 
		        oldspecimenCollectionGroup.getClinicalReport(), sessionDataBean, true);
		
		//Disable the related specimens to this specimen group
		Logger.out.debug("specimenCollectionGroup.getActivityStatus() "+specimenCollectionGroup.getActivityStatus());
		if(specimenCollectionGroup.getActivityStatus().equals(Constants.ACTIVITY_STATUS_DISABLED))
		{
			Logger.out.debug("specimenCollectionGroup.getActivityStatus() "+specimenCollectionGroup.getActivityStatus());
			Long specimenCollectionGroupIDArr[] = {specimenCollectionGroup.getId()};
			
			NewSpecimenBizLogic bizLogic = (NewSpecimenBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
			bizLogic.disableRelatedObjectsForSpecimenCollectionGroup(dao,specimenCollectionGroupIDArr);
		}
	}
	
	private void setCollectionProtocolRegistration(DAO dao, SpecimenCollectionGroup specimenCollectionGroup,SpecimenCollectionGroup oldSpecimenCollectionGroup) throws DAOException 
	{
		String sourceObjectName = CollectionProtocolRegistration.class.getName();
		String[] selectColumnName = null;
		String[] whereColumnName = new String[2];
		String[] whereColumnCondition = {"=","="};
		Object[] whereColumnValue = new Object[2];
		String joinCondition = Constants.AND_JOIN_CONDITION;
			
		whereColumnName[0]="collectionProtocol."+Constants.SYSTEM_IDENTIFIER;
		whereColumnValue[0]=specimenCollectionGroup.getCollectionProtocolRegistration().getCollectionProtocol().getId();
		
		if(specimenCollectionGroup.getCollectionProtocolRegistration().getParticipant()!=null)
		{
			// check for closed Participant
			Participant participantObject = (Participant)specimenCollectionGroup.getCollectionProtocolRegistration().getParticipant() ;
			
			if(oldSpecimenCollectionGroup!=null)
			{
				Participant participantObjectOld =oldSpecimenCollectionGroup.getCollectionProtocolRegistration().getParticipant();
				if(!participantObject.getId().equals(participantObjectOld.getId()))
					checkStatus(dao,participantObject, "Participant" );
			}
			else
				checkStatus(dao,participantObject, "Participant" );

			whereColumnName[1]="participant."+Constants.SYSTEM_IDENTIFIER;
			whereColumnValue[1]=specimenCollectionGroup.getCollectionProtocolRegistration().getParticipant().getId();
		}
		else
		{
			whereColumnName[1] = "protocolParticipantIdentifier";
			whereColumnValue[1] = specimenCollectionGroup.getCollectionProtocolRegistration().getProtocolParticipantIdentifier();
			Logger.out.debug("Value returned:"+whereColumnValue[1]);
		}
		
		
		List list = dao.retrieve( sourceObjectName, selectColumnName, whereColumnName, 
							 whereColumnCondition, whereColumnValue, joinCondition);
		if(!list.isEmpty())
		{
			//check for closed CollectionProtocolRegistration
			CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration)list.get(0);
			
			if(oldSpecimenCollectionGroup!=null)
			{
				CollectionProtocolRegistration collectionProtocolRegistrationOld =oldSpecimenCollectionGroup.getCollectionProtocolRegistration();
				if(!collectionProtocolRegistration.getId().equals(collectionProtocolRegistrationOld.getId()))
					checkStatus(dao,collectionProtocolRegistration, "Collection Protocol Registration" );
			}
			else
				checkStatus(dao,collectionProtocolRegistration, "Collection Protocol Registration" );
			
		   specimenCollectionGroup.setCollectionProtocolRegistration((CollectionProtocolRegistration)list.get(0));
		}
	}
	
	private void setClinicalReport(DAO dao, SpecimenCollectionGroup specimenCollectionGroup) throws DAOException
	{
		ClinicalReport clinicalReport = specimenCollectionGroup.getClinicalReport();
		ParticipantMedicalIdentifier participantMedicalIdentifier = clinicalReport.getParticipantMedicalIdentifier();
		if(participantMedicalIdentifier!=null)
		{
			List list  =  dao.retrieve(ParticipantMedicalIdentifier.class.getName(), Constants.SYSTEM_IDENTIFIER, participantMedicalIdentifier.getId());
			if(!list.isEmpty())
			{
			   specimenCollectionGroup.getClinicalReport().setParticipantMedicalIdentifier((ParticipantMedicalIdentifier)list.get(0));
			}
		}
	}
	
	public void disableRelatedObjects(DAO dao, Long collProtRegIDArr[])throws DAOException 
    {
    	List listOfSubElement = super.disableObjects(dao, SpecimenCollectionGroup.class, "collectionProtocolRegistration", 
    			"CATISSUE_SPECIMEN_COLL_GROUP", "COLLECTION_PROTOCOL_REG_ID", collProtRegIDArr);
    	if(!listOfSubElement.isEmpty())
    	{
	    	NewSpecimenBizLogic bizLogic = (NewSpecimenBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
			bizLogic.disableRelatedObjectsForSpecimenCollectionGroup(dao,Utility.toLongArray(listOfSubElement));
    	}
    }

    /**
     * @param dao
     * @param privilegeName
     * @param objectIds
     * @param assignToUser
     * @param roleId
     * @param longs
     * @throws DAOException
     * @throws SMException
     */
    public void assignPrivilegeToRelatedObjects(DAO dao, String privilegeName, Long[] objectIds, Long userId, String roleId, boolean assignToUser, boolean assignOperation) throws SMException, DAOException
    {
        List listOfSubElement = super.getRelatedObjects(dao, SpecimenCollectionGroup.class,"collectionProtocolRegistration", objectIds);
		if(!listOfSubElement.isEmpty())
		{
		    super.setPrivilege(dao,privilegeName,SpecimenCollectionGroup.class,Utility.toLongArray(listOfSubElement),userId, roleId, assignToUser, assignOperation);
	    	NewSpecimenBizLogic bizLogic = (NewSpecimenBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
			bizLogic.assignPrivilegeToRelatedObjectsForSCG(dao,privilegeName,Utility.toLongArray(listOfSubElement),userId, roleId, assignToUser, assignOperation);
		}
        
    }  
    
    /**
	 * @see edu.wustl.common.bizlogic.IBizLogic#setPrivilege(DAO, String, Class, Long[], Long, String, boolean)
	 */
    protected void setPrivilege(DAO dao, String privilegeName, Class objectType, Long[] objectIds, Long userId, String roleId, boolean assignToUser, boolean assignOperation) throws SMException, DAOException
    {
	    super.setPrivilege(dao,privilegeName,objectType,objectIds,userId, roleId, assignToUser, assignOperation);
	    
	    NewSpecimenBizLogic bizLogic = (NewSpecimenBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
	    bizLogic.assignPrivilegeToRelatedObjectsForSCG(dao,privilegeName,objectIds,userId, roleId, assignToUser, assignOperation);
    }
    
    /**
     * Overriding the parent class's method to validate the enumerated attribute values
     */
	protected boolean validate(Object obj, DAO dao, String operation) throws DAOException
    {
		SpecimenCollectionGroup group = (SpecimenCollectionGroup)obj;

		List clinicalDiagnosisList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_CLINICAL_DIAGNOSIS,null);
		if(!Validator.isEnumeratedValue(clinicalDiagnosisList,group.getClinicalDiagnosis()))
		{
			throw new DAOException(ApplicationProperties.getValue("spg.clinicalDiagnosis.errMsg"));
		}

//		NameValueBean undefinedVal = new NameValueBean(Constants.UNDEFINED,Constants.UNDEFINED);
        List clinicalStatusList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_CLINICAL_STATUS,null);
        if(!Validator.isEnumeratedValue(clinicalStatusList,group.getClinicalStatus()))
		{
			throw new DAOException(ApplicationProperties.getValue("collectionProtocol.clinicalStatus.errMsg"));
		}
        
        if(operation.equals(Constants.ADD))
		{
			if(!Constants.ACTIVITY_STATUS_ACTIVE.equals(group.getActivityStatus()))
			{
				throw new DAOException(ApplicationProperties.getValue("activityStatus.active.errMsg"));
			}
		}
		else
		{
			if(!Validator.isEnumeratedValue(Constants.ACTIVITY_STATUS_VALUES,group.getActivityStatus()))
			{
				throw new DAOException(ApplicationProperties.getValue("activityStatus.errMsg"));
			}
		}
        
		return true;
    }
	
	/**
     * This method fetches linked data from integrated application i.e. CAE/caTies.
     */
	public List getLinkedAppData(Long id, String applicationID)
	{
	    Logger.out.debug("In getIntegrationData() of SCGBizLogic ");
	    
	    Logger.out.debug("ApplicationName in getIntegrationData() of SCGBizLogic==>"+applicationID);
	    
	    long identifiedPathologyReportId = 0;
	    
	    try
	    {
		    //JDBC call to get matching identifier from database
		    Class.forName("org.gjt.mm.mysql.Driver");
		    
		    Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/catissuecore","catissue_core","catissue_core");
		    
		    Statement stmt= connection.createStatement();
		    
		    String clinicalReportQuery = "select CLINICAL_REPORT_ID from CATISSUE_SPECIMEN_COLL_GROUP where IDENTIFIER="+id;
		    
		    ResultSet clinicalReportResultSet = stmt.executeQuery(clinicalReportQuery);
		    
		    long clinicalReportId = 0;
		    while(clinicalReportResultSet.next())
		    {
		        clinicalReportId=clinicalReportResultSet.getLong(1);
		        break;
		    }
		    Logger.out.debug("ClinicalReportId==>"+clinicalReportId);
		    clinicalReportResultSet.close();
		    if(clinicalReportId==0)
		    {
		        List exception=new ArrayList();
		        exception.add("ClinicalReportId is not available for SpecimenCollectionGroup");
		        return exception;
		    }
		    
		    String identifiedPathologyReportIdQuery = "select IDENTIFIER from CATISSUE_IDENTIFIED_PATHOLOGY_REPORT where CLINICAL_REPORT_ID="+clinicalReportId;
		    
		    ResultSet identifiedPathologyReportResultSet=stmt.executeQuery(identifiedPathologyReportIdQuery);
		    
		    
		    
		    while(identifiedPathologyReportResultSet.next())
		    {
		        identifiedPathologyReportId = identifiedPathologyReportResultSet.getLong(1);
		        break;
		    }
		    Logger.out.debug("IdentifiedPathologyReportId==>"+identifiedPathologyReportId);
		    identifiedPathologyReportResultSet.close();
		    if(identifiedPathologyReportId==0)
		    {
		        List exception=new ArrayList();
		        exception.add("IdentifiedPathologyReportId is not available for linked ClinicalReportId");
		        return exception;
		    }
		    
		    stmt.close();
		    
		    connection.close();
		    
	    }
	    catch(Exception e)
	    {
	        Logger.out.debug("JDBC Exception==>"+e.getMessage());
	    }
	    
	    
	    IntegrationManager integrationManager=IntegrationManagerFactory.getIntegrationManager(applicationID);

	    return integrationManager.getLinkedAppData(new SpecimenCollectionGroup(), new Long(identifiedPathologyReportId));
	}
	
	public String getPageToShow()
    {
        return new String();
    }
	
	public List getMatchingObjects()
    {
        return new ArrayList();
    }
	public int getNextGroupNumber() throws DAOException
	{
		String sourceObjectName = "CATISSUE_SPECIMEN_COLL_GROUP";
		String[] selectColumnName = {"max(IDENTIFIER) as MAX_IDENTIFIER"};
		AbstractDAO dao = DAOFactory.getInstance().getDAO(Constants.JDBC_DAO);

		dao.openSession(null);

		List list = dao.retrieve(sourceObjectName, selectColumnName);

		dao.closeSession();

		if (!list.isEmpty())
		{
			List columnList = (List) list.get(0);
			if (!columnList.isEmpty())
			{
				String str = (String) columnList.get(0);
				if (!str.equals(""))
				{
					int no = Integer.parseInt(str);
					return no + 1;
				}
			}
		}

		return 1;
	}
	
}