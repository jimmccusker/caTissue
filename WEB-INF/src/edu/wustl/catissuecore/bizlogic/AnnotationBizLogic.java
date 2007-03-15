/**
 *<p>Title: </p>
 *<p>Description:  </p>
 *<p>Copyright:TODO</p>
 *@author 
 *@version 1.0
 */ 
package edu.wustl.catissuecore.bizlogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import edu.wustl.catissuecore.domain.EntityMap;
import edu.wustl.catissuecore.domain.EntityMapCondition;
import edu.wustl.catissuecore.domain.EntityMapRecord;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.exception.BizLogicException;
import edu.wustl.common.security.exceptions.UserNotAuthorizedException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.Constants;


/**
 * @author sandeep_chinta
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class AnnotationBizLogic extends DefaultBizLogic
{
    
    /**
     * 
     * @param staticEntityId
     * @return List of all dynamic entities id from a given static entity
     * eg: returns all dynamic entity id from a Participant,Specimen etc
     */
    public List getListOfDynamicEntitiesIds(long staticEntityId) {
        List<EntityMap> dynamicList = new ArrayList<EntityMap>();
        
        String[] selectColumnName = {"id"};
    	String[] whereColumnName ={"staticEntityId"};;
    	String[] whereColumnCondition = {"="};
    	Object[] whereColumnValue = {new Long(staticEntityId)};
    	String joinCondition = null;
    	List list = new ArrayList();
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),"staticEntityId",new Long(staticEntityId));
            if (dynamicList != null && !dynamicList.isEmpty())
            {
                for (EntityMap entityMap : dynamicList)
                {
                    list.add(entityMap.getContainerId());
                }
            }
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return list;
    }
    
    
    /**
     * 
     * @param staticEntityId
     * @return List of all dynamic entities Objects from a given static entity
     * eg: returns all dynamic entity objects from a Participant,Specimen etc
     */
    public List getListOfDynamicEntities(long staticEntityId) {
        List dynamicList = new ArrayList();
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),"staticEntityId",new Long(staticEntityId));
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dynamicList;
    }
    
    /**
     * 
     * @param staticEntityId
     * @param typeId
     * @param staticRecordId
     * @return List of all Entity Map Objects from a given static entity based on its protocol linkage
     * eg: returns all Entity Map Objects from a Participant,Specimen etc which is linked to Protocol 1, Protocol 2 etc
     */
    public List getListOfDynamicEntities(long staticEntityId,long typeId,long staticRecordId) {
        List dynamicList = new ArrayList();
        
        String[] selectColumnName = null;
    	String[] whereColumnName ={"staticEntityId","typeId","staticRecordId"};;
    	String[] whereColumnCondition = {"=","=","="};
    	Object[] whereColumnValue = {new Long(staticEntityId),new Long(typeId),new Long(staticRecordId)};
    	String joinCondition =  Constants.AND_JOIN_CONDITION;
        
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),selectColumnName,whereColumnName,whereColumnCondition,whereColumnValue,joinCondition);
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dynamicList;
    }
    
    
    /**
     * 
     * @param staticEntityId
     * @param typeId
     * @param staticRecordId
     * @return List of all dynamic entities id from a given static entity based on its protocol linkage
     * eg: returns all dynamic entity id from a Participant,Specimen etc which is linked to Protocol 1, Protocol 2 etc
     */
    public List getListOfDynamicEntitiesIds(long staticEntityId,long typeId,long staticRecordId) {
        List dynamicList = new ArrayList();
        
        String[] selectColumnName = {"containerId"};
    	String[] whereColumnName ={"staticEntityId","typeId","staticRecordId"};;
    	String[] whereColumnCondition = {"=","=","="};
    	Object[] whereColumnValue = {new Long(staticEntityId),new Long(typeId),new Long(staticRecordId)};
    	String joinCondition =  Constants.AND_JOIN_CONDITION;
        
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),selectColumnName,whereColumnName,whereColumnCondition,whereColumnValue,joinCondition);
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dynamicList;
    }
    
    
    /**
     * 
     * @param entityRecord
     * Updates the Entity Record object in database
     */
    public void updateEntityRecord(EntityMapRecord entityRecord) {
        
        try
        {
            update(entityRecord,Constants.HIBERNATE_DAO);
        }
        catch (BizLogicException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UserNotAuthorizedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param entityRecord
     * Inserts a new EntityRecord record in Database
     */
    public void insertEntityRecord(EntityMapRecord entityRecord) {
        try
        {
            insert(entityRecord,Constants.HIBERNATE_DAO);
        }
        catch (BizLogicException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UserNotAuthorizedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param entityMap
     * Updates the Entity Map object in database
     */
    public void updateEntityMap(EntityMap entityMap) {
        
        try
        {
            update(entityMap,Constants.HIBERNATE_DAO);
        }
        catch (BizLogicException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UserNotAuthorizedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param entityMap
     * Inserts a new EntityMap record in Database
     */
    public void insertEntityMap(EntityMap entityMap) {
        try
        {
            insert(entityMap,Constants.HIBERNATE_DAO);
        }
        catch (BizLogicException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (UserNotAuthorizedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param dynamicEntityContainerId
     * @return List of Static Entity Id from a given Dynamic Entity Id
     * 
     */
    public List getListOfStaticEntitiesIds(long dynamicEntityContainerId) {
        List dynamicList = new ArrayList();
        
        String[] selectColumnName = {"staticEntityId"};
    	String[] whereColumnName ={"containerId"};;
    	String[] whereColumnCondition = {"="};
    	Object[] whereColumnValue = {new Long(dynamicEntityContainerId)};
    	String joinCondition = null;
    	
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),selectColumnName,whereColumnName,whereColumnCondition,whereColumnValue,joinCondition);
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dynamicList;
    }
    
    /**
     * 
     * @param dynamicEntityContainerId
     * @return List of Static Entity Objects from a given Dynamic Entity Id
     * 
     */
    public List getListOfStaticEntities(long dynamicEntityContainerId) {
        List dynamicList = new ArrayList();
      
        try
        {
            dynamicList= retrieve(EntityMap.class.getName(),"containerId",new Long(dynamicEntityContainerId));
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return dynamicList;
    }
    
    
    
    /**
     * 
     * @param entityMapId
     * @return EntityMap object for its given id
     */
    public List getEntityMap(long entityMapId) {
        List dynamicList = new ArrayList();
        
          try
          {
              dynamicList= retrieve(EntityMap.class.getName(),"id",new Long(entityMapId));
          }
          catch (DAOException e)
          {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          
          return dynamicList;
    }
    
    public List getEntityMapRecordList(List entityMapids, long staticRecordId) {
        List dynamicList = new ArrayList();
        
        String[] selectColumnName = null;
    	String[] whereColumnName ={"staticEntityRecordId","entityMapId"};;
    	String[] whereColumnCondition = {"=","="};
    	String joinCondition =  Constants.AND_JOIN_CONDITION;
    	
    	Iterator iter = entityMapids.iterator();
    	while (iter.hasNext()) {
    	    Long entityMapId = (Long)iter.next();
    	    if(entityMapId != null) {
		    	Object[] whereColumnValue = {new Long(staticRecordId),entityMapId};
		        try
		        {
		          List list= retrieve(EntityMapRecord.class.getName(),selectColumnName,whereColumnName,whereColumnCondition,whereColumnValue,joinCondition);
		          if (list != null) {
			            dynamicList.addAll(list);
			        }
		        }
		        catch (DAOException e)
		        {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
    	    }
	        
    	}
        
        return dynamicList;
    }
    
    public void deleteEntityMapRecord(long entityMapId,long dynamicEntityRecordId) {
        List dynamicList = new ArrayList();
        
        String[] selectColumnName = null;
    	String[] whereColumnName ={"entityMapId","dynamicEntityRecordId"};;
    	String[] whereColumnCondition = {"=","="};
    	Object[] whereColumnValue = {new Long(entityMapId),new Long(dynamicEntityRecordId)};
    	String joinCondition =  Constants.AND_JOIN_CONDITION;
        
        try
        {
            dynamicList= retrieve(EntityMapRecord.class.getName(),selectColumnName,whereColumnName,whereColumnCondition,whereColumnValue,joinCondition);
        }
        catch (DAOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (dynamicList != null) {
            EntityMapRecord entityRecord = (EntityMapRecord) dynamicList.get(0);
            try
            {
                entityRecord.setLinkStatus(Constants.ACTIVITY_STATUS_DISABLED);
                update(entityRecord,Constants.HIBERNATE_DAO);
            }
            catch (UserNotAuthorizedException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            catch (BizLogicException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    
    
    public List getAnnotationIdsBasedOnCondition(List dynEntitiesList,List cpIdList)
    {
        List dynEntitiesIdList = new ArrayList();        
        if(dynEntitiesList!=null && !dynEntitiesList.isEmpty())
        {
            Iterator dynEntitiesIterator = dynEntitiesList.iterator();            
            while(dynEntitiesIterator.hasNext())
            {
                EntityMap entityMap= (EntityMap)dynEntitiesIterator.next();
                if(entityMap.getEntityMapConditionCollection() != null && !entityMap.getEntityMapConditionCollection().isEmpty())
                {
                    boolean check = checkStaticRecId(entityMap.getEntityMapConditionCollection(),cpIdList);
                    if(check)
                        dynEntitiesIdList.add(entityMap.getContainerId());               
                }
                else
                    dynEntitiesIdList.add(entityMap.getContainerId());     
            }            
        }       
        return dynEntitiesIdList;      
    }
    
    private boolean  checkStaticRecId(Collection entityMapConditionCollection,List cpIdList)
    {        
        Iterator entityMapCondIterator = entityMapConditionCollection.iterator();
        
        if(cpIdList!=null && !cpIdList.isEmpty())
        while(entityMapCondIterator.hasNext())
        {
            EntityMapCondition entityMapCond= (EntityMapCondition)entityMapCondIterator.next();
            if(cpIdList.contains(entityMapCond.getStaticRecordId()))
                 return true;
        }
                    
        
        return false;
    }
    
    
}
