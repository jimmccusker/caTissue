/**
 * <p>Title: SiteHDAO Class>
 * <p>Description:	SiteHDAO is used to add site type information into the database using Hibernate.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Jul 21, 2005
 */

package edu.wustl.catissuecore.bizlogic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.wustl.catissuecore.dao.DAO;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.security.SecurityManager;
import edu.wustl.common.security.exceptions.SMException;
import edu.wustl.common.security.exceptions.UserNotAuthorizedException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;
import edu.wustl.common.util.logger.Logger;

/**
 * SiteHDAO is used to add site type information into the database using Hibernate.
 * @author aniruddha_phadnis
 */
public class SiteBizLogic extends DefaultBizLogic
{
	/**
     * Saves the storageType object in the database.
	 * @param obj The storageType object to be saved.
	 * @param session The session in which the object is saved.
	 * @throws DAOException 
     */
	protected void insert(Object obj, DAO dao, SessionDataBean sessionDataBean) throws DAOException, UserNotAuthorizedException
	{
		Site site = (Site)obj;
		
		checkStatus(dao, site.getCoordinator(), "Coordinator");
		
		Set protectionObjects = new HashSet();
		
		setCordinator(dao,site);
		
		dao.insert(site.getAddress(),sessionDataBean, true, true);
	    dao.insert(site,sessionDataBean, true, true);
	    protectionObjects.add(site);
	    protectionObjects.add(site.getAddress());
	    try
        {
            SecurityManager.getInstance(this.getClass()).insertAuthorizationData(
            		null,protectionObjects,null);
        }
	    catch (SMException e)
        {
	    	throw handleSMException(e);
        }
	}
	
	/**
     * Updates the persistent object in the database.
	 * @param obj The object to be updated.
	 * @param session The session in which the object is saved.
	 * @throws DAOException 
     */
	protected void update(DAO dao, Object obj, Object oldObj, SessionDataBean sessionDataBean) throws DAOException, UserNotAuthorizedException
    {
		Site site = (Site)obj;
		Site siteOld = (Site)oldObj;
		
		if(!site.getCoordinator().getSystemIdentifier().equals(siteOld.getCoordinator().getSystemIdentifier()))
			checkStatus(dao, site.getCoordinator(), "Coordinator");
		
		setCordinator(dao,site);
		
		dao.update(site.getAddress(), sessionDataBean, true, true, false);
	    dao.update(site, sessionDataBean, true, true, false);
	    
	    //Audit of update.
	    Site oldSite = (Site) oldObj;
	    dao.audit(site.getAddress(), oldSite.getAddress(), sessionDataBean, true);
	    dao.audit(obj, oldObj, sessionDataBean, true);
    }
	
	// This method sets the cordinator for a particular site.
	private void setCordinator(DAO dao,Site site) throws DAOException
	{
		List list = dao.retrieve(User.class.getName(), "systemIdentifier", site.getCoordinator().getSystemIdentifier());
		
		if (list.size() != 0)
		{
		    User user = (User) list.get(0);
		    site.setCoordinator(user);
		}
	}
	
	protected void setPrivilege(DAO dao, String privilegeName, Class objectType, Long[] objectIds, Long userId, String roleId, boolean assignToUser,boolean assignOperation) throws SMException, DAOException
    {
	    Logger.out.debug(" privilegeName:"+privilegeName+" objectType:"+objectType+" objectIds:"+edu.wustl.common.util.Utility.getArrayString(objectIds)+" userId:"+userId+" roleId:"+roleId+" assignToUser:"+assignToUser);
	    super.setPrivilege(dao,privilegeName,objectType,objectIds,userId, roleId, assignToUser,assignOperation);
	    
	    StorageContainerBizLogic storageContainerBizLogic = (StorageContainerBizLogic) BizLogicFactory.getBizLogic(Constants.STORAGE_CONTAINER_FORM_ID);
	    storageContainerBizLogic.assignPrivilegeToRelatedObjectsForSite(dao, privilegeName, objectIds, userId, roleId, assignToUser, assignOperation);
//	    //Giving privilege on related object ids as well
//	    List relatedAddressObjectsIds = super.getRelatedObjects(dao,Site.class,new String[] {"address."+Constants.SYSTEM_IDENTIFIER},new String[] {Constants.SYSTEM_IDENTIFIER}, objectIds);
//	    super.setPrivilege(dao,privilegeName,Address.class,Utility.toLongArray(relatedAddressObjectsIds),userId, roleId, assignToUser, assignOperation);
    }
	
	/**
     * Overriding the parent class's method to validate the enumerated attribute values
     */
	protected boolean validate(Object obj, DAO dao, String operation) throws DAOException
    {
		Site site = (Site)obj;
		
		List siteList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_SITE_TYPE, null);

		if(!Validator.isEnumeratedValue(siteList,site.getType()))
		{
			throw new DAOException(ApplicationProperties.getValue("type.errMsg"));
		}
		
		if(!Validator.isEnumeratedValue(Constants.STATEARRAY,site.getAddress().getState()))
		{
			throw new DAOException(ApplicationProperties.getValue("state.errMsg"));
		}

		if(!Validator.isEnumeratedValue(Constants.COUNTRYARRAY,site.getAddress().getCountry()))
		{
			throw new DAOException(ApplicationProperties.getValue("country.errMsg"));
		}

		if(operation.equals(Constants.ADD))
		{
			if(!Constants.ACTIVITY_STATUS_ACTIVE.equals(site.getActivityStatus()))
			{
				throw new DAOException(ApplicationProperties.getValue("activityStatus.active.errMsg"));
			}
		}
		else
		{
			if(!Validator.isEnumeratedValue(Constants.SITE_ACTIVITY_STATUS_VALUES,site.getActivityStatus()))
			{
				throw new DAOException(ApplicationProperties.getValue("activityStatus.errMsg"));
			}
		}
		
    	return true;
    }
}