/**
 * <p>Title: NewSpecimenAction Class>
 * <p>Description:	NewSpecimenAction initializes the fields in the New Specimen page.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.NewSpecimenForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.NewSpecimenBizLogic;
import edu.wustl.catissuecore.bizlogic.StorageContainerBizLogic;
import edu.wustl.catissuecore.bizlogic.UserBizLogic;
import edu.wustl.catissuecore.domain.Biohazard;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.action.SecureAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.cde.CDE;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.cde.PermissibleValue;
import edu.wustl.common.util.MapDataParser;
import edu.wustl.common.util.logger.Logger;

/**
 * NewSpecimenAction initializes the fields in the New Specimen page.
 * @author aniruddha_phadnis
 */
public class NewSpecimenAction extends SecureAction
{

	/**
	 * Overrides the execute method of Action class.
	 */
	public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//Logger.out.debug("NewSpecimenAction start@@@@@@@@@");
		NewSpecimenForm specimenForm = (NewSpecimenForm) form;

		//Gets the value of the operation parameter.
		String operation = (String) request.getParameter(Constants.OPERATION);

		//boolean to indicate whether the suitable containers to be shown in dropdown 
		//is exceeding the max limit.
		String exceedingMaxLimit=new String();
		
		//Sets the operation attribute to be used in the Edit/View Specimen Page in Advance Search Object View. 
		request.setAttribute(Constants.OPERATION, operation);

		if (operation != null && operation.equalsIgnoreCase(Constants.ADD))
		{
			specimenForm.setId(0);
		}
		String virtuallyLocated = request.getParameter("virtualLocated");
		if(virtuallyLocated!=null && virtuallyLocated.equals("true"))
		{
			specimenForm.setVirtuallyLocated(true);
		}
				
//		if (operation != null && operation.equalsIgnoreCase(Constants.EDIT))
//		{
//			Logger.out.debug("virtuallylocated:"+specimenForm.getVirtuallyLocated());
//		}

		
		//Name of button clicked
		String button = request.getParameter("button");
		Map map = null;

		if (button != null)
		{
			if (button.equals("deleteExId"))
			{
				List key = new ArrayList();
				key.add("ExternalIdentifier:i_name");
				key.add("ExternalIdentifier:i_value");

				//Gets the map from ActionForm
				map = specimenForm.getExternalIdentifier();
				MapDataParser.deleteRow(key, map, request.getParameter("status"));
			}
			else
			{
				List key = new ArrayList();
				key.add("Biohazard:i_type");
				key.add("Biohazard:i_id");

				//Gets the map from ActionForm
				map = specimenForm.getBiohazard();
				MapDataParser.deleteRow(key, map, request.getParameter("status"));
			}
		}

		//*************  ForwardTo implementation *************
		HashMap forwardToHashMap = (HashMap) request.getAttribute("forwardToHashMap");

		if (forwardToHashMap != null)
		{
			String specimenCollectionGroupId = (String) forwardToHashMap
					.get("specimenCollectionGroupId");
			Logger.out.debug("SpecimenCollectionGroupId found in forwardToHashMap========>>>>>>"
					+ specimenCollectionGroupId);

			if (specimenCollectionGroupId != null)
			{
				/**
				 *  Retaining properties of specimen when more is clicked.
				 *  Bug no -- 2623
				 */
				specimenForm.setSpecimenCollectionGroupId(specimenCollectionGroupId);
				specimenForm.setParentSpecimenId("");
				specimenForm.setLabel("");
				specimenForm.setBarcode("");
				specimenForm.setPositionInStorageContainer("");
				/*specimenForm.setQuantity("");
				specimenForm.setClassName("");
				specimenForm.setTissueSide("");
				specimenForm.setTissueSite("");
				specimenForm.setPathologicalStatus("");*/
				specimenForm.setPositionDimensionOne("");
				specimenForm.setPositionDimensionTwo("");
				specimenForm.setStorageContainer("");

				/*clearCollectionEvent(specimenForm);
				clearReceivedEvent(specimenForm);*/

			}
		}
		//*************  ForwardTo implementation *************

		// - set the specimen id
		//       	String specimenID = (String)request.getAttribute(Constants.SPECIMEN_ID);
		//       	if(specimenID !=null)
		//       		specimenForm.setId(Long.parseLong(specimenID  )); 
		//    	
		//    	Logger.out.debug("SpecimenID in NewSpecimenAction : " + specimenID  );

		String pageOf = request.getParameter(Constants.PAGEOF);
		request.setAttribute(Constants.PAGEOF, pageOf);

		//Sets the activityStatusList attribute to be used in the Site Add/Edit Page.
		request.setAttribute(Constants.ACTIVITYSTATUSLIST, Constants.ACTIVITY_STATUS_VALUES);

		NewSpecimenBizLogic bizLogic = (NewSpecimenBizLogic) BizLogicFactory.getInstance()
				.getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);

		if (specimenForm.isParentPresent())//If parent specimen is present then
		{
			String[] fields = {Constants.SYSTEM_LABEL};
			List parentSpecimenList = bizLogic.getList(Specimen.class.getName(), fields,
					Constants.SYSTEM_IDENTIFIER, true);
			request.setAttribute(Constants.PARENT_SPECIMEN_ID_LIST, parentSpecimenList);
		}

		String[] bhIdArray = {"-1"};
		String[] bhTypeArray = {Constants.SELECT_OPTION};
		String[] bhNameArray = {Constants.SELECT_OPTION};

		String selectColNames[] = {Constants.SYSTEM_IDENTIFIER, "name", "type"};
		List biohazardList = bizLogic.retrieve(Biohazard.class.getName(), selectColNames);
		Iterator iterator = biohazardList.iterator();

		//Creating & setting the biohazard name, id & type list
		if (biohazardList != null && !biohazardList.isEmpty())
		{
			bhIdArray = new String[biohazardList.size() + 1];
			bhTypeArray = new String[biohazardList.size() + 1];
			bhNameArray = new String[biohazardList.size() + 1];

			bhIdArray[0] = "-1";
			bhTypeArray[0] = "";
			bhNameArray[0] = Constants.SELECT_OPTION;

			int i = 1;

			while (iterator.hasNext())
			{
				Object hazard[] = (Object[]) iterator.next();
				bhIdArray[i] = String.valueOf(hazard[0]);
				bhNameArray[i] = (String) hazard[1];
				bhTypeArray[i] = (String) hazard[2];
				i++;
			}
		}

		request.setAttribute(Constants.BIOHAZARD_NAME_LIST, bhNameArray);
		request.setAttribute(Constants.BIOHAZARD_ID_LIST, bhIdArray);
		request.setAttribute(Constants.BIOHAZARD_TYPES_LIST, bhTypeArray);

		//Setting Secimen Collection Group
		String sourceObjectName = SpecimenCollectionGroup.class.getName();
		String[] displayNameFields = {"name"};
		String valueField = Constants.SYSTEM_IDENTIFIER;

		SessionDataBean sessionData = (SessionDataBean) request.getSession().getAttribute(Constants.SESSION_DATA);
		sessionData.getUserId();
		List specimenCollectionGroupList = bizLogic.getList(sourceObjectName, displayNameFields, valueField, true);
		request.setAttribute(Constants.SPECIMEN_COLLECTION_GROUP_LIST, specimenCollectionGroupList);

		//Setting the specimen class list
		List specimenClassList = CDEManager.getCDEManager().getPermissibleValueList(
				Constants.CDE_NAME_SPECIMEN_CLASS, null);
		request.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);

		if (Constants.ALIQUOT.equals(specimenForm.getLineage()))
		{
			populateListBoxes(specimenForm, request);
		}
		else
		{
			//Setting the specimen type list
 			List specimenTypeList = CDEManager.getCDEManager().getPermissibleValueList(
					Constants.CDE_NAME_SPECIMEN_TYPE, null);
			request.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
						

			//Setting tissue site list
			List tissueSiteList = CDEManager.getCDEManager().getPermissibleValueList(
					Constants.CDE_NAME_TISSUE_SITE, null);
			request.setAttribute(Constants.TISSUE_SITE_LIST, tissueSiteList);
			//Bug- setting the default tissue site
			if(specimenForm.getTissueSite() == null)
			{
				specimenForm.setTissueSite(Constants.NOTSPECIFIED);
			}
			
			//Setting tissue side list
			List tissueSideList = CDEManager.getCDEManager().getPermissibleValueList(
					Constants.CDE_NAME_TISSUE_SIDE, null);
			request.setAttribute(Constants.TISSUE_SIDE_LIST, tissueSideList);
			//Bug- setting the default tissue side
			if(specimenForm.getTissueSide() == null)
			{
				specimenForm.setTissueSide(Constants.NOTSPECIFIED);
			}
			
			//Setting pathological status list
			List pathologicalStatusList = CDEManager.getCDEManager().getPermissibleValueList(
					Constants.CDE_NAME_PATHOLOGICAL_STATUS, null);
			request.setAttribute(Constants.PATHOLOGICAL_STATUS_LIST, pathologicalStatusList);
			//Bug- setting the default pathological status
			if(specimenForm.getPathologicalStatus() == null)
			{
				specimenForm.setPathologicalStatus(Constants.NOTSPECIFIED);
			}
		}

		//get the Specimen class and type from the cde
		CDE specimenClassCDE = CDEManager.getCDEManager().getCDE(Constants.CDE_NAME_SPECIMEN_CLASS);
		Set setPV = specimenClassCDE.getPermissibleValues();
		Iterator itr = setPV.iterator();

		specimenClassList = new ArrayList();
		Map subTypeMap = new HashMap();

		specimenClassList.add(new NameValueBean(Constants.SELECT_OPTION, "-1"));

		while (itr.hasNext())
		{
			List innerList = new ArrayList();
			Object obj = itr.next();
			PermissibleValue pv = (PermissibleValue) obj;
			String tmpStr = pv.getValue();
			Logger.out.debug(tmpStr);
			specimenClassList.add(new NameValueBean(tmpStr, tmpStr));

			Set list1 = pv.getSubPermissibleValues();
			Logger.out.debug("list1 " + list1);
			Iterator itr1 = list1.iterator();
			innerList.add(new NameValueBean(Constants.SELECT_OPTION, "-1"));
			while (itr1.hasNext())
			{
				Object obj1 = itr1.next();
				PermissibleValue pv1 = (PermissibleValue) obj1;
				// set specimen type
				String tmpInnerStr = pv1.getValue();
				Logger.out.debug("\t\t" + tmpInnerStr);
				innerList.add(new NameValueBean(tmpInnerStr, tmpInnerStr));
			}
			Collections.sort(innerList);
			subTypeMap.put(pv.getValue(), innerList);
		} // class and values set
		Logger.out.debug("\n\n\n\n**********MAP DATA************\n");

		// sets the Class list
		request.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);

		// set the map to subtype
		request.setAttribute(Constants.SPECIMEN_TYPE_MAP, subTypeMap);

		//Setting biohazard list
		biohazardList = CDEManager.getCDEManager().getPermissibleValueList(
				Constants.CDE_NAME_BIOHAZARD, null);
		request.setAttribute(Constants.BIOHAZARD_TYPE_LIST, biohazardList);

		//Mandar : 10-July-06 AutoEvents : CollectionEvent
		setCollectionEventRequestParameters(request,specimenForm);

		//Mandar : 11-July-06 AutoEvents : ReceivedEvent
		setReceivedEventRequestParameters(request, specimenForm);

		//Mandar : set default date and time too event fields
		setDateParameters(specimenForm);

		//    	 ---- chetan 15-06-06 ----
		StorageContainerBizLogic scbizLogic = (StorageContainerBizLogic) BizLogicFactory
				.getInstance().getBizLogic(Constants.STORAGE_CONTAINER_FORM_ID);
		TreeMap containerMap = new TreeMap(); 
		Vector initialValues = null;
		if (operation.equals(Constants.ADD))
		{
			if (specimenForm.getSpecimenCollectionGroupId() != null && !specimenForm.getSpecimenCollectionGroupId().equals("") && 
					specimenForm.getClassName()!=null && !specimenForm.getClassName().equals("") && !specimenForm.getClassName().equals("-1"))
			{
				//Logger.out.debug("before retrieval of spCollGroupList inside specimen action ^^^^^^^^^^^");
				String []selectColumnName={"collectionProtocolRegistration.collectionProtocol.id"};
				String []whereColumnName={Constants.SYSTEM_IDENTIFIER};
				String []whereColumnCondition={"="};
				String []whereColumnValue={specimenForm.getSpecimenCollectionGroupId()};
				List spCollGroupList = bizLogic.retrieve(SpecimenCollectionGroup.class.getName(),selectColumnName ,whereColumnName
						,whereColumnCondition,whereColumnValue,null );
				//Logger.out.debug("after retrieval of spCollGroupList inside specimen action ^^^^^^^^^^^");
				if (!spCollGroupList.isEmpty())
				{
//					Object []spCollGroup = (Object[]) spCollGroupList
//							.get(0);
					long cpId = ((Long)spCollGroupList.get(0)).longValue();
					String spClass = specimenForm.getClassName();
					Logger.out.info("cpId :" + cpId + "spClass:" + spClass);
					request.setAttribute(Constants.COLLECTION_PROTOCOL_ID,cpId+"");
					if(virtuallyLocated!=null && virtuallyLocated.equals("false"))
					{
							specimenForm.setVirtuallyLocated(false);
					}
					//Logger.out.debug("calling getAllocatedContaienrMapForSpecimen() function from NewSpecimenAction---");
					containerMap = scbizLogic.getAllocatedContaienrMapForSpecimen(cpId, spClass,0,exceedingMaxLimit,true);
					//Logger.out.debug("exceedingMaxLimit in action for Boolean:"+exceedingMaxLimit);
					Logger.out.debug("finish ---calling getAllocatedContaienrMapForSpecimen() function from NewSpecimenAction---");
					if(containerMap.isEmpty())
					{
						ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
						if (errors == null || errors.size() == 0)
						{
							errors = new ActionErrors();
						}
						errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("storageposition.not.available"));
						saveErrors(request,errors);
					}
					Logger.out.debug("calling checkForInitialValues() function from NewSpecimenAction---");
					initialValues = checkForInitialValues(containerMap);
					Logger.out.debug("finish ---calling checkForInitialValues() function from NewSpecimenAction---");
				} 
			}
		}
		else
		{
			containerMap = new TreeMap();
			String[] startingPoints = new String[]{"-1", "-1", "-1"};
			
			Logger.out.info("--------------container:" + specimenForm.getStorageContainer());
			Logger.out.info("--------------pos1:" + specimenForm.getPositionDimensionOne());
			Logger.out.info("--------------pos2:" + specimenForm.getPositionDimensionTwo());

			if (specimenForm.getStorageContainer() != null
					&& !specimenForm.getStorageContainer().equals(""))
			{
				Integer id = new Integer(specimenForm.getStorageContainer());
				String parentContainerName = "";
				String valueField1 = "id";
				List list = bizLogic.retrieve(StorageContainer.class.getName(), valueField1,
						new Long(specimenForm.getStorageContainer()));
				if (!list.isEmpty())
				{
					StorageContainer container = (StorageContainer) list.get(0);
					parentContainerName = container.getName();

				}
				Integer pos1 = new Integer(specimenForm.getPositionDimensionOne());
				Integer pos2 = new Integer(specimenForm.getPositionDimensionTwo());

				List pos2List = new ArrayList();
				pos2List.add(new NameValueBean(pos2, pos2));

				Map pos1Map = new TreeMap();
				pos1Map.put(new NameValueBean(pos1, pos1), pos2List);
				containerMap.put(new NameValueBean(parentContainerName, id), pos1Map);

				if (specimenForm.getStorageContainer() != null
						&& !specimenForm.getStorageContainer().equals("-1"))
				{
					startingPoints[0] = specimenForm.getStorageContainer();

				}
				if (specimenForm.getPositionDimensionOne() != null
						&& !specimenForm.getPositionDimensionOne().equals("-1"))
				{
					startingPoints[1] = specimenForm.getPositionDimensionOne();
				}
				if (specimenForm.getPositionDimensionTwo() != null
						&& !specimenForm.getPositionDimensionTwo().equals("-1"))
				{
					startingPoints[2] = specimenForm.getPositionDimensionTwo();
				}
			}
			initialValues = new Vector();
			Logger.out.info("Starting points[0]" + startingPoints[0]);
			Logger.out.info("Starting points[1]" + startingPoints[1]);
			Logger.out.info("Starting points[2]" + startingPoints[2]);
			initialValues.add(startingPoints);

		}
		request.setAttribute("initValues", initialValues);
		request.setAttribute(Constants.EXCEEDS_MAX_LIMIT,exceedingMaxLimit);
		request.setAttribute(Constants.AVAILABLE_CONTAINER_MAP, containerMap);
		// -------------------------

		if (specimenForm.isVirtuallyLocated())
		{
			request.setAttribute("disabled", "true");
		}
		//Logger.out.debug("End of specimen action");
		return mapping.findForward(pageOf);
	}

	/* This method populates the list boxes for type, tissue site, tissue side
	 * and pathological status if this specimen is an aliquot.
	 */
	private void populateListBoxes(NewSpecimenForm specimenForm, HttpServletRequest request)
	{
		//Setting the specimen type list
		NameValueBean bean = new NameValueBean(specimenForm.getType(), specimenForm.getType());
		List specimenTypeList = new ArrayList();
		specimenTypeList.add(bean);
		request.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);

		//Setting tissue site list
		bean = new NameValueBean(specimenForm.getTissueSite(), specimenForm.getTissueSite());
		List tissueSiteList = new ArrayList();
		tissueSiteList.add(bean);
		request.setAttribute(Constants.TISSUE_SITE_LIST, tissueSiteList);

		//Setting tissue side list
		bean = new NameValueBean(specimenForm.getTissueSide(), specimenForm.getTissueSide());
		List tissueSideList = new ArrayList();
		tissueSideList.add(bean);
		request.setAttribute(Constants.TISSUE_SIDE_LIST, tissueSideList);

		//Setting pathological status list
		bean = new NameValueBean(specimenForm.getPathologicalStatus(), specimenForm
				.getPathologicalStatus());
		List pathologicalStatusList = new ArrayList();
		pathologicalStatusList.add(bean);
		request.setAttribute(Constants.PATHOLOGICAL_STATUS_LIST, pathologicalStatusList);
	}

	// Mandar AutoEvents CollectionEvent start
	/**
	 * This method sets all the collection event parameters for the SpecimenEventParameter pages
	 * @param request HttpServletRequest instance in which the data will be set. 
	 * @throws Exception Throws Exception. Helps in handling exceptions at one common point.
	 */
	private void setCollectionEventRequestParameters(HttpServletRequest request, NewSpecimenForm specimenForm) throws Exception
	{
		//Gets the value of the operation parameter.
		String operation = request.getParameter(Constants.OPERATION);

		//Sets the operation attribute to be used in the Add/Edit FrozenEventParameters Page. 
		request.setAttribute(Constants.OPERATION, operation);

		//Sets the minutesList attribute to be used in the Add/Edit FrozenEventParameters Page.
		request.setAttribute(Constants.MINUTES_LIST, Constants.MINUTES_ARRAY);

		//Sets the hourList attribute to be used in the Add/Edit FrozenEventParameters Page.
		request.setAttribute(Constants.HOUR_LIST, Constants.HOUR_ARRAY);

		//        //The id of specimen of this event.
		//        String specimenId = request.getParameter(Constants.SPECIMEN_ID); 
		//        request.setAttribute(Constants.SPECIMEN_ID, specimenId);
		//        Logger.out.debug("\t\t SpecimenEventParametersAction************************************ : "+specimenId );
		//        
		UserBizLogic userBizLogic = (UserBizLogic) BizLogicFactory.getInstance().getBizLogic(
				Constants.USER_FORM_ID);
		Collection userCollection = userBizLogic.getUsers(operation);

		request.setAttribute(Constants.USERLIST, userCollection);
		
		SessionDataBean sessionData = getSessionData(request);
		if(sessionData != null)
		{
			String user = sessionData.getLastName()+", "+sessionData.getFirstName();
			long collectionEventUserId = getIdFromCollection(userCollection,user);
			specimenForm.setCollectionEventUserId(collectionEventUserId);
			specimenForm.setReceivedEventUserId(collectionEventUserId);			
		}

		// set the procedure lists
		List procedureList = CDEManager.getCDEManager().getPermissibleValueList(
				Constants.CDE_NAME_COLLECTION_PROCEDURE, null);
		request.setAttribute(Constants.PROCEDURE_LIST, procedureList);
		//Bug- setting the default collection event procedure
		if(specimenForm.getCollectionEventCollectionProcedure() == null)
		{
			specimenForm.setCollectionEventCollectionProcedure("Not Specified");
		}
		
		// set the container lists
		List containerList = CDEManager.getCDEManager().getPermissibleValueList(
				Constants.CDE_NAME_CONTAINER, null);
		request.setAttribute(Constants.CONTAINER_LIST, containerList);
		//Bug- setting the default collection event container
		if(specimenForm.getCollectionEventContainer() == null)
		{
			specimenForm.setCollectionEventContainer("Not Specified");
		}
		
	}

	// Mandar AutoEvents CollectionEvent end

	// Mandar Autoevents ReceivedEvent start
	/**
	 * This method sets all the received event parameters for the SpecimenEventParameter pages
	 * @param request HttpServletRequest instance in which the data will be set. 
	 * @throws Exception Throws Exception. Helps in handling exceptions at one common point.
	 */
	private void setReceivedEventRequestParameters(HttpServletRequest request, NewSpecimenForm specimenForm) throws Exception
	{

		List qualityList = CDEManager.getCDEManager().getPermissibleValueList(
				Constants.CDE_NAME_RECEIVED_QUALITY, null);
		request.setAttribute(Constants.RECEIVED_QUALITY_LIST, qualityList);
		//Bug- setting the default recieved event quality
		if(specimenForm.getReceivedEventReceivedQuality() == null)
		{
			specimenForm.setReceivedEventReceivedQuality("Not Specified");
		}
	}

	private void setDateParameters(NewSpecimenForm specimenForm)
	{
		// set the current Date and Time for the event.
		Calendar cal = Calendar.getInstance();
		//Collection Event fields
		if (specimenForm.getCollectionEventdateOfEvent() == null)
		{
			specimenForm.setCollectionEventdateOfEvent(Utility.parseDateToString(cal.getTime(),
					Constants.DATE_PATTERN_MM_DD_YYYY));
		}
		if (specimenForm.getCollectionEventTimeInHours() == null)
		{
			specimenForm.setCollectionEventTimeInHours(Integer.toString(cal
					.get(Calendar.HOUR_OF_DAY)));
		}
		if (specimenForm.getCollectionEventTimeInMinutes() == null)
		{
			specimenForm
					.setCollectionEventTimeInMinutes(Integer.toString(cal.get(Calendar.MINUTE)));
		}

		//ReceivedEvent Fields
		if (specimenForm.getReceivedEventDateOfEvent() == null)
		{
			specimenForm.setReceivedEventDateOfEvent(Utility.parseDateToString(cal.getTime(),
					Constants.DATE_PATTERN_MM_DD_YYYY));
		}
		if (specimenForm.getReceivedEventTimeInHours() == null)
		{
			specimenForm.setReceivedEventTimeInHours(Integer
					.toString(cal.get(Calendar.HOUR_OF_DAY)));
		}
		if (specimenForm.getReceivedEventTimeInMinutes() == null)
		{
			specimenForm.setReceivedEventTimeInMinutes(Integer.toString(cal.get(Calendar.MINUTE)));
		}

	}

	private void clearCollectionEvent(NewSpecimenForm specimenForm)
	{
		specimenForm.setCollectionEventCollectionProcedure("");
		specimenForm.setCollectionEventComments("");
		specimenForm.setCollectionEventContainer("");
		specimenForm.setCollectionEventdateOfEvent("");
		specimenForm.setCollectionEventTimeInHours("");
		specimenForm.setCollectionEventTimeInMinutes("");
		specimenForm.setCollectionEventUserId(-1);
	}

	private void clearReceivedEvent(NewSpecimenForm specimenForm)
	{
		specimenForm.setReceivedEventComments("");
		specimenForm.setReceivedEventDateOfEvent("");
		specimenForm.setReceivedEventReceivedQuality("");
		specimenForm.setReceivedEventTimeInHours("");
		specimenForm.setReceivedEventTimeInMinutes("");
		specimenForm.setReceivedEventUserId(-1);
	}

	Vector checkForInitialValues(TreeMap containerMap)
	{
		Vector initialValues = null;

		if (containerMap.size() > 0)
		{
			String[] startingPoints = new String[3];

			Set keySet = containerMap.keySet();
			Iterator itr = keySet.iterator();
			NameValueBean nvb = (NameValueBean) itr.next();
			startingPoints[0] = nvb.getValue();

			Map map1 = (Map) containerMap.get(nvb);
			keySet = map1.keySet();
			itr = keySet.iterator();
			nvb = (NameValueBean) itr.next();
			startingPoints[1] = nvb.getValue();

			List list = (List) map1.get(nvb);
			nvb = (NameValueBean) list.get(0);
			startingPoints[2] = nvb.getValue();

			Logger.out.info("Starting points[0]" + startingPoints[0]);
			Logger.out.info("Starting points[1]" + startingPoints[1]);
			Logger.out.info("Starting points[2]" + startingPoints[2]);
			initialValues = new Vector();
			initialValues.add(startingPoints);

		}
		return initialValues;

		//request.setAttribute("initValues", initialValues);
	}
	
	/**
	 * 
	 * @param userList Collection
	 * @param userName userName
	 * @return long
	 */
	private long getIdFromCollection(Collection userList, String userName) 
	{		
		Iterator itr = userList.iterator();
		for(int i=0; itr.hasNext(); i++)
		{
			NameValueBean nameValueBean = (NameValueBean) itr.next();
			if(nameValueBean.getName() != null && nameValueBean.getName().trim().equals(userName))
			{				
				String id = nameValueBean.getValue();
				return Long.valueOf(id).longValue();
			}
		}
		return -1;
	}

}