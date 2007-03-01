/*
 * Created on Jan 5, 2007
 * @author
 *
 */

package edu.wustl.catissuecore.action.annotations;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.common.dynamicextensions.domaininterface.EntityGroupInterface;
import edu.common.dynamicextensions.domaininterface.EntityInterface;
import edu.common.dynamicextensions.domaininterface.userinterface.ContainerInterface;
import edu.common.dynamicextensions.entitymanager.EntityManager;
import edu.common.dynamicextensions.entitymanager.EntityManagerInterface;
import edu.common.dynamicextensions.exception.DataTypeFactoryInitializationException;
import edu.common.dynamicextensions.exception.DynamicExtensionsApplicationException;
import edu.common.dynamicextensions.exception.DynamicExtensionsSystemException;
import edu.common.dynamicextensions.ui.webui.util.WebUIManager;
import edu.common.dynamicextensions.ui.webui.util.WebUIManagerConstants;
import edu.wustl.catissuecore.actionForm.AnnotationForm;
import edu.wustl.catissuecore.bizlogic.AnnotationBizLogic;
import edu.wustl.catissuecore.domain.EntityMap;
import edu.wustl.catissuecore.util.CatissueCoreCacheManager;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.exception.BizLogicException;
import edu.wustl.common.security.exceptions.UserNotAuthorizedException;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.logger.Logger;

/**
 * @author preeti_munot
 *
 */
/**
 * This class is responsible for loading the annotation information
 */
public class LoadAnnotationDefinitionAction extends BaseAction
{

    /* (non-Javadoc)
     * @see edu.wustl.common.action.BaseAction#executeAction(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected ActionForward executeAction(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        ActionForward actionfwd = null;
        try
        {
            AnnotationForm annotationForm = (AnnotationForm) form;
            //Ajax Code
            if (request.getParameter(AnnotationConstants.AJAX_OPERATION) != null)
            {
                //If operation not null -> ajaxOperation
                processAjaxOperation(request, response);
            }
            else if (request.getParameter(WebUIManager
                    .getOperationStatusParameterName()) != null)
            {
                //Return from dynamic extensions
                processResponseFromDynamicExtensions(request);
                loadAnnotations(annotationForm);
                request.setAttribute("operation", "loadIntegrationPage");
                actionfwd = mapping.findForward(Constants.SUCCESS);
            }
            else
            {
                loadAnnotations(annotationForm);
                actionfwd = mapping.findForward(Constants.SUCCESS);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Logger.out.error(e);
        }
        return actionfwd;
    }

    /**
     * @param request
     * @throws UserNotAuthorizedException 
     * @throws BizLogicException 
     */
    private void processResponseFromDynamicExtensions(HttpServletRequest request)
            throws BizLogicException, UserNotAuthorizedException
    {
        String operationStatus = request.getParameter(WebUIManager
                .getOperationStatusParameterName());
        if ((operationStatus != null)
                && (operationStatus.trim()
                        .equals(WebUIManagerConstants.SUCCESS)))
        {
            String dynExtContainerId = request.getParameter(WebUIManager
                    .getContainerIdentifierParameterName());
            String staticEntityId = getStaticEntityIdForLinking();
            Logger.out.info("Need to link static entity [" + staticEntityId
                    + "] to dyn ent [" + dynExtContainerId + "]");
            linkEntities(request, staticEntityId, dynExtContainerId);
        }
    }

    /**
     * @param request 
     * @param staticEntityId
     * @param dynExtContainerId
     * @throws UserNotAuthorizedException 
     * @throws BizLogicException 
     */
    private void linkEntities(HttpServletRequest request,
            String staticEntityId, String dynExtContainerId)
            throws BizLogicException, UserNotAuthorizedException
    {
        if ((staticEntityId != null) && (dynExtContainerId != null))
        {
            EntityMap entityMap = getEntityMap(request, staticEntityId,
                    dynExtContainerId);
            AnnotationBizLogic annotationBizLogic = new AnnotationBizLogic();
            annotationBizLogic.insertEntityMap(entityMap);
        }
    }

    /**
     * @param request 
     * @param staticEntityId
     * @param dynExtContainerId
     * @return
     */
    private EntityMap getEntityMap(HttpServletRequest request,
            String staticEntityId, String dynExtContainerId)
    {
        EntityMap entityMapObj = new EntityMap();
        entityMapObj.setContainerId(Utility.toLong(dynExtContainerId));
        entityMapObj.setStaticEntityId(Utility.toLong(staticEntityId));
        SessionDataBean sessionDataBean = (SessionDataBean) request
                .getSession().getAttribute(Constants.SESSION_DATA);
        if (sessionDataBean != null)
        {
            entityMapObj.setCreatedBy(sessionDataBean.getLastName() + ","
                    + sessionDataBean.getFirstName());
            entityMapObj.setCreatedDate(new Date());
        }
        entityMapObj.setLinkStatus(AnnotationConstants.STATUS_ATTACHED);
        return entityMapObj;
    }

    /**
     * @return
     */
    private String getStaticEntityIdForLinking()
    {
        String staticEntityId = null;
        CatissueCoreCacheManager cacheManager = CatissueCoreCacheManager
                .getInstance();
        if (cacheManager != null)
        {
            staticEntityId = (String) cacheManager
                    .getObjectFromCache(AnnotationConstants.SELECTED_STATIC_ENTITYID);
            cacheManager
                    .removeObjectFromCache(AnnotationConstants.SELECTED_STATIC_ENTITYID);
        }
        return staticEntityId;
    }

    /**
     * @param request
     * @param response
     * @throws IOException 
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     * @throws DAOException 
     */
    private void processAjaxOperation(HttpServletRequest request,
            HttpServletResponse response) throws IOException,
            DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException, DAOException
    {
        String operation = request
                .getParameter(AnnotationConstants.AJAX_OPERATION);
        if ((operation != null)
                && (operation
                        .equalsIgnoreCase(AnnotationConstants.AJAX_OPERATION_SELECT_GROUP)))
        {
            String groupId = request
                    .getParameter(AnnotationConstants.AJAX_OPERATION_SELECTED_GROUPID);
            String entitiesXML = getEntitiesForGroupAsXML(groupId);
            sendResponse(entitiesXML, response);
        }
    }

    /**
     * @param containerId 
     * @return
     */
    private String getDynamicExtentionsEditURL(Long containerId)
    {
        String dynamicExtensionsEditEntityURL = "BuildDynamicEntity.do?containerId="
                + containerId + "^_self";
        return dynamicExtensionsEditEntityURL;
    }

    /**
     * @param groupId
     * @return
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     * @throws DAOException 
     */
    private String getEntitiesForGroupAsXML(String groupId)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException, DAOException
    {
        StringBuffer entitiesXML = new StringBuffer();
        entitiesXML.append("<?xml version='1.0' encoding='UTF-8'?>");
        if (groupId != null)
        {
            Long lGroupId = new Long(groupId);
            Collection<NameValueBean> entityContainerCollection = EntityManager.getInstance()
            .getMainContainer(lGroupId);
            
            if (entityContainerCollection != null)
            {
                entitiesXML.append("<rows>");
                Iterator<NameValueBean> containerCollnIter = entityContainerCollection
                        .iterator();
                int entityIndex = 1;
                while (containerCollnIter.hasNext())
                {
                    NameValueBean container = containerCollnIter.next();
                    entitiesXML.append(getEntityXMLString(container,
                            entityIndex));
                    entityIndex++;
                }
                entitiesXML.append("</rows>");
            }
        }
        return entitiesXML.toString();
    }

    /**
     * @param container
     * @param entityIndex 
     * @return
     * @throws DAOException 
     * @throws DynamicExtensionsSystemException 
     * @throws DynamicExtensionsApplicationException 
     */
    private String getEntityXMLString(NameValueBean container, int entityIndex)
            throws DAOException, DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        StringBuffer entityXML = new StringBuffer();
        if (container != null)
        {
            int index = 1;
            String editDynExtEntityURL = getDynamicExtentionsEditURL(new Long(
                    container.getValue()));
            List<EntityMap> entityMapList = getEntityMapsForDE(new Long(
                    container.getValue()));
            if (entityMapList != null)
            {
                Iterator<EntityMap> entityMapIterator = entityMapList
                        .iterator();
                while (entityMapIterator.hasNext())
                {
                    EntityMap entityMapObj = entityMapIterator.next();
                    entityXML.append(getXMLForEntityMap(container.getName(),
                            entityMapObj, entityIndex + index,
                            editDynExtEntityURL));
                    index++;
                }
            }
        }
        return entityXML.toString();
    }

    /**
     * @param caption
     * @param entityMapObj
     * @param dynExtentionsEditEntityURL 
     * @param i
     * @return
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     */
    private StringBuffer getXMLForEntityMap(String containercaption,
            EntityMap entityMapObj, int rowId, String dynExtentionsEditEntityURL)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        StringBuffer entityMapXML = new StringBuffer();
        entityMapXML.append("<row id='" + rowId + "'>");
        entityMapXML.append("<cell>0</cell>");
        entityMapXML.append("<cell>" + containercaption + "^"
                + dynExtentionsEditEntityURL + "</cell>");
        if (entityMapObj != null)
        {
            String staticEntityName = getEntityName(entityMapObj
                    .getStaticEntityId());
            entityMapXML.append("<cell>" + staticEntityName + "</cell>");
            entityMapXML.append("<cell>"
                    + Utility.parseDateToString(entityMapObj.getCreatedDate(),
                            Constants.DATE_PATTERN_MM_DD_YYYY) + "</cell>");
            entityMapXML.append("<cell>" + entityMapObj.getCreatedBy()
                    + "</cell>");
            entityMapXML.append("<cell>" + entityMapObj.getLinkStatus()
                    + "</cell>");
        }
        entityMapXML.append("</row>");
        return entityMapXML;
    }

    /**
     * @param id
     * @return
     */
    private List<EntityMap> getEntityMapsForDE(Long dynEntityContainerId)
    {
        List<EntityMap> entityMapList = null;
        if (dynEntityContainerId != null)
        {
            AnnotationBizLogic annotationBizLogic = new AnnotationBizLogic();
            entityMapList = annotationBizLogic
                    .getListOfStaticEntities(dynEntityContainerId);
            /*List staticEntityIdsList = annotationBizLogic.getListOfStaticEntities(dynEntityContainerId);
             entityMapList = getEntityMapList(staticEntityIdsList);*/
        }
        return entityMapList;
    }

    /**
     * @param staticEntityIdsList
     * @return
     */
    private List<EntityMap> getEntityMapList(List<Long> staticEntityIdsList)
    {
        List<EntityMap> entityMapList = new ArrayList<EntityMap>();
        EntityMap entityMapObj = null;
        if (staticEntityIdsList != null)
        {
            Iterator<Long> staticEntityIdIterator = staticEntityIdsList
                    .iterator();
            while (staticEntityIdIterator.hasNext())
            {
                entityMapObj = new EntityMap();
                entityMapObj.setStaticEntityId(staticEntityIdIterator.next());
                entityMapList.add(entityMapObj);
            }
        }
        return entityMapList;
    }

    /**
     * @param entityId
     * @return
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     */
    private String getEntityName(Long entityId)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        String entityName = "";
        if (entityId != null)
        {
            EntityManagerInterface entityManager = EntityManager.getInstance();
            CatissueCoreCacheManager cacheManager = CatissueCoreCacheManager
                    .getInstance();
            List staticEntityList = (List) cacheManager
                    .getObjectFromCache(AnnotationConstants.STATIC_ENTITY_LIST);
            if (staticEntityList != null && !staticEntityList.isEmpty())
            {
                Iterator listIterator = staticEntityList.iterator();
                while (listIterator.hasNext())
                {
                      NameValueBean nameValueBean =   (NameValueBean) listIterator.next();
                      if (nameValueBean.getValue().equalsIgnoreCase(entityId.toString()))
                      {
                          entityName =  nameValueBean.getName();
                      }
                }
            }
            
        }
        return entityName;
    }

    /**
     * @param name
     * @return
     */
    private String resolveEntityName(String entityName)
    {
        if (AnnotationConstants.ENTITY_NAME_PARTICIPANT.equals(entityName))
        {
            return AnnotationConstants.PARTICIPANT;
        }
        else if (AnnotationConstants.ENTITY_NAME_SPECIMEN.equals(entityName))
        {
            return AnnotationConstants.SPECIMEN;
        }
        else if (AnnotationConstants.ENTITY_NAME_SPECIMEN_COLLN_GROUP
                .equals(entityName))
        {
            return AnnotationConstants.SPECIMEN_COLLN_GROUP;
        }
     /*   else if (AnnotationConstants.ENTITY_NAME_COLLECTION_PROTOCOL_REGISTRATION
                .equals(entityName))
        {
            return AnnotationConstants.COLLECTION_PROTOCOL_REGISTRATION;
        }*/
        return "";
    }

    /**
     * @throws IOException 
     * 
     */
    private void sendResponse(String responseXML, HttpServletResponse response)
            throws IOException
    {
        PrintWriter out = response.getWriter();
        out.write(responseXML);
    }

    /**
     * @param annotationForm
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     */
    private void loadAnnotations(AnnotationForm annotationForm)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        if (annotationForm != null)
        {
            //load list of system entities
            List systemEntitiesList = getSystemEntityList();
            annotationForm.setSystemEntitiesList(systemEntitiesList);
            //Load list of groups
            loadGroupList(annotationForm);
        }
    }

    /**
     * @param annotationForm 
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     * 
     */
    private List getSystemEntityList() throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        List<NameValueBean> systemEntityList = new ArrayList<NameValueBean>();
        List<NameValueBean> staticEntityInformationList = populateStaticEntityList("StaticEntityInformation.xml");
        CatissueCoreCacheManager cacheManager = CatissueCoreCacheManager
                .getInstance();
        if (cacheManager
                .getObjectFromCache(AnnotationConstants.STATIC_ENTITY_LIST) == null)
        {
            systemEntityList.add(new NameValueBean(Constants.SELECT_OPTION,
                    Constants.SELECT_OPTION_VALUE));
            if (staticEntityInformationList != null && !staticEntityInformationList.isEmpty()) 
            {
                Iterator listIterator = staticEntityInformationList.iterator();
                while (listIterator.hasNext())
                {
                    NameValueBean nameValueBean = (NameValueBean) listIterator.next();
                    systemEntityList
                    .add(new NameValueBean(
                            nameValueBean.getName() ,
                            Utility
                                    .getEntityId(nameValueBean.getValue())));
                }
            }
            
            cacheManager.addObjectToCache(
                    AnnotationConstants.STATIC_ENTITY_LIST,
                    (Serializable) systemEntityList);
        }
        else
        {
            systemEntityList = (List<NameValueBean>) cacheManager
                    .getObjectFromCache(AnnotationConstants.STATIC_ENTITY_LIST);
        }
        return systemEntityList;
    }
    
    /**
     * This method updates module map by parsing xml file
     * @param xmlFileName file to be parsed
     * @return dataType Map
     * @throws DataTypeFactoryInitializationException on Exception
     */
    public final List<NameValueBean> populateStaticEntityList(String xmlFileName)
            throws DataTypeFactoryInitializationException
    {
        List list = new ArrayList();

        SAXReader saxReader = new SAXReader();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);

        Document document = null;

        try
        {
            document = saxReader.read(inputStream);
            Element className = null;
            Element displayName = null;

            Element primitiveAttributesElement = document.getRootElement();
            Iterator primitiveAttributeElementIterator = primitiveAttributesElement
                    .elementIterator("static-entity");

            Element primitiveAttributeElement = null;

            while (primitiveAttributeElementIterator.hasNext())
            {
                primitiveAttributeElement = (Element) primitiveAttributeElementIterator.next();

                className = primitiveAttributeElement.element("name");
                displayName = primitiveAttributeElement.element("displayName");
                list.add(new NameValueBean(displayName.getStringValue(),className.getStringValue()));
               
            }
        }
        catch (DocumentException documentException)
        {
            throw new DataTypeFactoryInitializationException(documentException);
        }

        return list;
    }


    /**
     * @param annotationForm 
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     * 
     */
    private void loadGroupList(AnnotationForm annotationForm)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        //List of groups
        Collection<NameValueBean> annotationGroupsList = getAnnotationGroups();
        String groupsXML = getGroupsXML(annotationGroupsList);
        annotationForm.setAnnotationGroupsXML(groupsXML);
    }

    /**
     * @param annotationGroupsList
     * @return
     */
    private String getGroupsXML(Collection<NameValueBean> annotationGroupsList)
    {
        StringBuffer groupsXML = new StringBuffer();
        if (annotationGroupsList != null)
        {
            groupsXML.append("<?xml version='1.0' encoding='UTF-8'?>");
            groupsXML.append("<rows>");
            NameValueBean groupBean = null;
            Iterator<NameValueBean> iterator = annotationGroupsList.iterator();
            while (iterator.hasNext())
            {
                groupBean = iterator.next();
                if (groupBean != null)
                {
                    groupsXML
                            .append("<row id='" + groupBean.getValue() + "' >");
                    groupsXML.append("<cell>0</cell>");
                    groupsXML
                            .append("<cell>" + groupBean.getName() + "</cell>");
                    groupsXML.append("</row>");
                }
            }
            groupsXML.append("</rows>");
        }
        return groupsXML.toString();
    }

    /**
     * @param annotationForm
     * @throws DynamicExtensionsApplicationException 
     * @throws DynamicExtensionsSystemException 
     */
    private Collection<NameValueBean> getAnnotationGroups()
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        EntityManagerInterface entityManager = EntityManager.getInstance();
        Collection<NameValueBean> entityGroups = entityManager
                .getAllEntityGroupBeans();
        return entityGroups;
    }
}
