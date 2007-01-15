/**
 * <p>Title: RequestDetailsAction Class>
 * <p>Description:	This class initializes the fields of RequestDetails.jsp Page</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ashish Gupta
 * @version 1.00
 * Created on Oct 05,2006
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.RequestDetailsForm;
import edu.wustl.catissuecore.bean.DefinedArrayRequestBean;
import edu.wustl.catissuecore.bean.DefinedArrayDetailsBean;
import edu.wustl.catissuecore.bean.ExistingArrayDetailsBean;
import edu.wustl.catissuecore.bean.RequestDetailsBean;
import edu.wustl.catissuecore.bean.RequestViewBean;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.OrderBizLogic;
import edu.wustl.catissuecore.domain.DerivedSpecimenOrderItem;
import edu.wustl.catissuecore.domain.ExistingSpecimenArrayOrderItem;
import edu.wustl.catissuecore.domain.ExistingSpecimenOrderItem;
import edu.wustl.catissuecore.domain.NewSpecimenArrayOrderItem;
import edu.wustl.catissuecore.domain.OrderDetails;
import edu.wustl.catissuecore.domain.OrderItem;
import edu.wustl.catissuecore.domain.PathologicalCaseOrderItem;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenArray;
import edu.wustl.catissuecore.domain.SpecimenOrderItem;
import edu.wustl.catissuecore.util.OrderingSystemUtil;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.bizlogic.IBizLogic;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.util.dbManager.DAOException;

public class RequestDetailsAction extends BaseAction
{
	

	/**
	 * Overrides the execute method of Action class.
	 * Initializes the various fields in RequestDetails.jsp Page.
	 * @param mapping object
	 * @param form object
	 * @param request object
	 * @param response object
	 * @return ActionForward object
	 * @throws Exception object
	 * */
	
	public ActionForward  executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		RequestDetailsForm requestDetailsForm = (RequestDetailsForm) form;
		// The request Id on which the user has clicked
		String requestId = request.getParameter("id");		
		
		//Setting the order id in the form to retrieve the corresponding orderitems from the db in CommonAddEditAction.
		requestDetailsForm.setId((new Long(requestId)).longValue());
		
		// The request details  corresponding to the request Id
		RequestViewBean requestListBean = getRequestObject(requestId);
		request.setAttribute(Constants.REQUEST_HEADER_OBJECT, requestListBean);
		
		// order items status to display
		List requestedItemsStatusList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_REQUESTED_ITEMS_STATUS, null);
		request.setAttribute(Constants.REQUESTED_ITEMS_STATUS_LIST, requestedItemsStatusList);
		
		//Setting the Site List
		List siteList = getSiteListToDisplay();
		request.setAttribute(Constants.SITE_LIST_OBJECT, siteList);
		
		//The order items list corresponding to the request Id
		getRequestDetailsList(requestId, requestDetailsForm,request);
		
		//setting Item Status List in Request
		request.setAttribute(Constants.ITEM_STATUS_LIST, OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW));
		
		//setting Items status list without "Distributed" option.
		List tempList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
		Iterator tempListIter = tempList.iterator();
		while (tempListIter.hasNext())
		{
			NameValueBean nameValueBean = (NameValueBean)tempListIter.next();
			if(nameValueBean.getValue().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
			{
				tempList.remove(nameValueBean);
				break;
			}
		}
		request.setAttribute(Constants.ITEM_STATUS_LIST_WO_DISTRIBUTE, tempList);
		
		return mapping.findForward("success");
	}
	
	
	/**
	 * This function constructs lists of RequestDetails bean objects,map of definedarrays and ExistingArrayDetails bean instances to display
	 * on RequestDetails.jsp and ArrayRequests.jsp by setting all the lists in HttpServletRequest object.
	 * @param id String containing the requestId
	 * @param requestDetailsForm RequestDetailsForm object
	 * @param request HttpServletRequest object
	 * @throws DAOException object
	 */
	private void getRequestDetailsList(String id, RequestDetailsForm requestDetailsForm,HttpServletRequest request) throws DAOException
	{
		/**
		 * The row number to update available quantity on selecting the required specimen from 'request for' drop down.
		 */
		int finalSpecimenListId = 0;
		
		OrderBizLogic orderBizLogic = (OrderBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.REQUEST_DETAILS_FORM_ID);
		String colName = "id";
		
		List orderList = orderBizLogic.retrieve(OrderDetails.class.getName(), colName, id);
		OrderDetails order = (OrderDetails)orderList.get(0);
		
		//populating Map in the form
	  	requestDetailsForm.setAllValues(order);
	  	
		Collection orderItemsListFromDB = order.getOrderItemCollection(); 
		
		List requestDetailsList = new ArrayList();
		RequestDetailsBean requestDetailsBean = null;
		OrderItem orderItem = null;
		
		//List containing the list of defined arrays and existing arrays  
		List arrayRequestDetailsList = new ArrayList();
		List arrayRequestDetailsMapList = new ArrayList();
		
		if(orderItemsListFromDB !=null && orderItemsListFromDB.isEmpty() == false)
		{
			Iterator iter = orderItemsListFromDB.iterator();
			
			while(iter.hasNext())
			{
				orderItem = (OrderItem)iter.next();
				
				if(orderItem instanceof SpecimenOrderItem)
				{
					SpecimenOrderItem specimenOrderItem = (SpecimenOrderItem)orderItem;
					//Incase of order items in the Order List
					if(specimenOrderItem.getNewSpecimenArrayOrderItem() == null)
					{
						 requestDetailsBean = new RequestDetailsBean();
						 if(orderItem instanceof ExistingSpecimenOrderItem)
						  {								
							  	ExistingSpecimenOrderItem existingSpecimenorderItem = (ExistingSpecimenOrderItem)orderItem;							  	
							  	requestDetailsBean = populateRequestDetailsBeanForExistingSpecimen(requestDetailsBean,existingSpecimenorderItem);					    
							  	requestDetailsList.add(requestDetailsBean);
							  	finalSpecimenListId ++;
						  }
						  else if(orderItem instanceof DerivedSpecimenOrderItem)
						  {
							  	DerivedSpecimenOrderItem derivedSpecimenorderItem = (DerivedSpecimenOrderItem)orderItem;
							  	requestDetailsBean = populateRequestDetailsBeanForDerivedSpecimen(requestDetailsBean,derivedSpecimenorderItem,request,finalSpecimenListId);					    
							  	requestDetailsList.add(requestDetailsBean);
							  	finalSpecimenListId ++;
						  }
						  else if(orderItem instanceof PathologicalCaseOrderItem)
						  {
							  	PathologicalCaseOrderItem pathologicalCaseOrderItem = (PathologicalCaseOrderItem)orderItem;
							  	requestDetailsBean = populateRequestDetailsBeanForPathologicalCase(requestDetailsBean,pathologicalCaseOrderItem,request,finalSpecimenListId); 
							  	requestDetailsList.add(requestDetailsBean);
							  	finalSpecimenListId ++;
						  }
					}
				}
				//In case of Defined Array
				if(orderItem instanceof NewSpecimenArrayOrderItem)
				{
					Map arrayMap = new HashMap();
					NewSpecimenArrayOrderItem newSpecimenArrayOrderItem = (NewSpecimenArrayOrderItem)orderItem;
					arrayMap = populateArrayMap(newSpecimenArrayOrderItem);
					//Add defined-array list into the arrayRequestDetails list 
					arrayRequestDetailsMapList.add(arrayMap);
				}
				//In case of Existing BioSpecimen Array Items
				if(orderItem instanceof ExistingSpecimenArrayOrderItem)
				{
					//List existingArrayItemsList = new ArrayList();
					ExistingArrayDetailsBean existingArrayDetailsBean  = new ExistingArrayDetailsBean (); 
					ExistingSpecimenArrayOrderItem existingSpecimenArrayOrderItem = (ExistingSpecimenArrayOrderItem)orderItem;
					existingArrayDetailsBean = populateExistingArrayItemsList(existingArrayDetailsBean,existingSpecimenArrayOrderItem);
					//Add existingArrayBEan list into arrayRequestDetails list
					arrayRequestDetailsList.add(existingArrayDetailsBean);
				}
			}//End while
		}
		
		//Call to populateItemStatusList() when order items present in OrderList
		if(requestDetailsList.size() > 0)
		{
			populateItemStatusList(requestDetailsList,request,"addToOrderList");
		}
		//Call to populateItemStatusList() when order items are added to Defined Array
		if(arrayRequestDetailsMapList.size()>0)
		{
			populateItemStatusList(arrayRequestDetailsMapList,request,"addToDefinedArrays");
		}
		//Call to populateItemStatusList() when order items are added to Existing Array
		if(arrayRequestDetailsList.size()>0)
		{
			populateItemStatusList(arrayRequestDetailsList,request,"addToExistingArrays");
		}
	}
		
	/**
	 * This function populates existingArrayDetailsList with the existing Bio Speicmen Array Information by fetching the data from
	 * ExistingSpecimenArrayOrderItem domain object.
	 * @param existingArrayDetailsBean ExistingArrayDetailsBean object
	 * @param existingSpecimenArrayOrderItem ExistingSpecimenArrayOrderItem object
	 * @return existingArrayDetailsBean ExistingArrayDetailsBean object
	 */
	private ExistingArrayDetailsBean populateExistingArrayItemsList(ExistingArrayDetailsBean existingArrayDetailsBean,ExistingSpecimenArrayOrderItem existingSpecimenArrayOrderItem)
	{
		existingArrayDetailsBean.setOrderItemId(existingSpecimenArrayOrderItem.getId().toString());
		existingArrayDetailsBean.setBioSpecimenArrayName(existingSpecimenArrayOrderItem.getSpecimenArray().getName());
		existingArrayDetailsBean.setDescription(existingSpecimenArrayOrderItem.getDescription());
		existingArrayDetailsBean.setAssignedStatus(existingSpecimenArrayOrderItem.getStatus());
		existingArrayDetailsBean.setArrayId(existingSpecimenArrayOrderItem.getSpecimenArray().getId().toString());
		existingArrayDetailsBean.setRequestedQuantity(existingSpecimenArrayOrderItem.getRequestedQuantity().getValue().toString());
		
		//Populate status list of individual array //As Per Mark's comment, initial status always "New"
		List existingArrayStatusList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
		existingArrayDetailsBean.setItemStatusList(existingArrayStatusList);
		
		return existingArrayDetailsBean;
	}
	
	/**
	 * This function populates the arrayDetailsList with map objects.Each map contains arrayRequestBean object as teh key and list of order items
	 * (for that defined array) as the value.
	 * @param newSpecimenArrayOrderItem NewSpecimenArrayOrderItem object
	 * @return definedArrayMap Map object
	 * @throws DAOException object
	 */
	private Map populateArrayMap(NewSpecimenArrayOrderItem newSpecimenArrayOrderItem) throws DAOException
	{
		Map definedArrayMap = new HashMap(); 
		
		List arrayItemsList = new ArrayList();
		//Create new instance of ArrayRequestBean to save the name,type,dimensions of the defined array 
		DefinedArrayRequestBean arrayRequestBean = new DefinedArrayRequestBean();
		arrayRequestBean.setArrayName(newSpecimenArrayOrderItem.getName());
		arrayRequestBean.setArrayClass(newSpecimenArrayOrderItem.getArrayType().getSpecimenClass());
		arrayRequestBean.setOneDimensionCapacity((newSpecimenArrayOrderItem.getArrayType().getCapacity().getOneDimensionCapacity()).toString());
		arrayRequestBean.setTwoDimensionCapacity((newSpecimenArrayOrderItem.getArrayType().getCapacity().getTwoDimensionCapacity()).toString());
		arrayRequestBean.setArrayType(newSpecimenArrayOrderItem.getArrayType().getName());
		arrayRequestBean.setArrayTypeId(newSpecimenArrayOrderItem.getArrayType().getId().toString());
		arrayRequestBean.setAssignedStatus(newSpecimenArrayOrderItem.getStatus());
		arrayRequestBean.setOrderItemId(newSpecimenArrayOrderItem.getId().toString());
		
		IBizLogic bizLogic = (DefaultBizLogic)BizLogicFactory.getInstance().getBizLogic(-1);
		String colName = "name";
		String colValue = arrayRequestBean.getArrayName();
		
		//List specimenArrayList = (ArrayList)bizLogic.retrieve(SpecimenArray.class.getName(),colName,colValue);
		SpecimenArray specimenArrayObj = newSpecimenArrayOrderItem.getSpecimenArray();
		if(specimenArrayObj != null)
		{
			arrayRequestBean.setArrayId(specimenArrayObj.getId().toString());
		}
		
		//Populate status list of individual array
		List arrayStatusList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
		//Remove 'Distributed' option from the list if defined array is not created in the system.
		if(specimenArrayObj == null)
		{
			Iterator arrayStatusListItr = arrayStatusList.iterator();
			while (arrayStatusListItr.hasNext())
			{
				NameValueBean nameValueBean = (NameValueBean)arrayStatusListItr.next();
				if(nameValueBean.getValue().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
				{
					arrayStatusList.remove(nameValueBean);
					break;
				}
			}
		}
		arrayRequestBean.setArrayStatusList(arrayStatusList);

		Collection specimenOrderItemCollection = newSpecimenArrayOrderItem.getSpecimenOrderItemCollection();
		Iterator specimenOrderItemCollectionItr = specimenOrderItemCollection.iterator();
		while(specimenOrderItemCollectionItr.hasNext())
		{	
			SpecimenOrderItem specimenOrderItem = (SpecimenOrderItem)specimenOrderItemCollectionItr.next();
			DefinedArrayDetailsBean arrayDetailsBean = new DefinedArrayDetailsBean();
			if(specimenOrderItem instanceof ExistingSpecimenOrderItem)
			{
				ExistingSpecimenOrderItem existingSpecimenOrderItem = (ExistingSpecimenOrderItem)specimenOrderItem;
				arrayDetailsBean.setRequestedItem(existingSpecimenOrderItem.getSpecimen().getLabel());
				//Add empty list since it is the case of existing speicmen
				arrayDetailsBean.setSpecimenList(new ArrayList());
				arrayDetailsBean.setSpeicmenId((existingSpecimenOrderItem.getSpecimen().getId()).toString());
				arrayDetailsBean.setRequestedQuantity(existingSpecimenOrderItem.getRequestedQuantity().getValue().toString());
				arrayDetailsBean.setAvailableQuantity(existingSpecimenOrderItem.getSpecimen().getAvailableQuantity().getValue().toString());
				arrayDetailsBean.setAssignedStatus(existingSpecimenOrderItem.getStatus());
			  	arrayDetailsBean.setClassName(existingSpecimenOrderItem.getSpecimen().getClassName());
			  	arrayDetailsBean.setType(existingSpecimenOrderItem.getSpecimen().getType());
			 	arrayDetailsBean.setDescription(existingSpecimenOrderItem.getDescription());
			 	arrayDetailsBean.setOrderItemId(existingSpecimenOrderItem.getId().toString());
			 	arrayDetailsBean.setInstanceOf("Existing");
			}
			else if(specimenOrderItem instanceof DerivedSpecimenOrderItem)
			{
				DerivedSpecimenOrderItem derivedSpecimenOrderItem = (DerivedSpecimenOrderItem)specimenOrderItem;
				arrayDetailsBean.setRequestedItem(derivedSpecimenOrderItem.getSpecimen().getLabel());
				arrayDetailsBean.setSpeicmenId(derivedSpecimenOrderItem.getSpecimen().getId().toString());
				//Obtain all children specimens
				Collection childrenSpecimenList = OrderingSystemUtil.getAllChildrenSpecimen(derivedSpecimenOrderItem.getSpecimen(),derivedSpecimenOrderItem.getSpecimen().getChildrenSpecimen());
				//Obtain only those specimens of this class and type from the above list
			    List finalChildrenSpecimenList = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childrenSpecimenList,derivedSpecimenOrderItem.getSpecimenClass(),derivedSpecimenOrderItem.getSpecimenType());
			    List childrenSpecimenListToDisplay = getNameValueBeanList(finalChildrenSpecimenList);
			    arrayDetailsBean.setSpecimenList(childrenSpecimenListToDisplay);
			    
				arrayDetailsBean.setRequestedQuantity(derivedSpecimenOrderItem.getRequestedQuantity().getValue().toString());
				arrayDetailsBean.setAvailableQuantity(derivedSpecimenOrderItem.getSpecimen().getAvailableQuantity().getValue().toString());
				arrayDetailsBean.setAssignedStatus(derivedSpecimenOrderItem.getStatus());
				arrayDetailsBean.setClassName(derivedSpecimenOrderItem.getSpecimenClass());
				arrayDetailsBean.setType(derivedSpecimenOrderItem.getSpecimenType());
				arrayDetailsBean.setDescription(derivedSpecimenOrderItem.getDescription());
				arrayDetailsBean.setOrderItemId(derivedSpecimenOrderItem.getId().toString());
				arrayDetailsBean.setInstanceOf("Derived");
			}
			else if(specimenOrderItem instanceof PathologicalCaseOrderItem)
			{
				PathologicalCaseOrderItem pathologicalCaseOrderItem = (PathologicalCaseOrderItem)specimenOrderItem;
				boolean isDerived = false;
				arrayDetailsBean.setRequestedItem(pathologicalCaseOrderItem.getSpecimenCollectionGroup().getIdentifiedSurgicalPathologyReport().getAccessionNumber());
				Collection childrenSpecimenList = pathologicalCaseOrderItem.getSpecimenCollectionGroup().getSpecimenCollection();
				List totalChildrenSpecimenColl = null;
				List childrenSpecimenListToDisplay=null;
		    	Iterator childrenSpecimenListIterator = childrenSpecimenList.iterator();
		    	while (childrenSpecimenListIterator.hasNext())
		    	{
		    		Specimen specimen = (Specimen)childrenSpecimenListIterator.next();
		    		List childSpecimenCollection = OrderingSystemUtil.getAllChildrenSpecimen(specimen,specimen.getChildrenSpecimen());
		    		List finalChildrenSpecimenCollection = null;
		    		if(pathologicalCaseOrderItem.getSpecimenClass() != null && pathologicalCaseOrderItem.getSpecimenType() != null && !pathologicalCaseOrderItem.getSpecimenClass().trim().equalsIgnoreCase("") && !pathologicalCaseOrderItem.getSpecimenType().trim().equalsIgnoreCase(""))
		    	    {
		    			//"Derived"	   
		    			finalChildrenSpecimenCollection = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childSpecimenCollection,pathologicalCaseOrderItem.getSpecimenClass(),pathologicalCaseOrderItem.getSpecimenType());
		    			isDerived = true;
		    	    }	    	
				    else
				    {
				    	//"Block" . Specimen class = "Tissue" , Specimen Type = "Block".
				    	finalChildrenSpecimenCollection = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childSpecimenCollection,"Tissue","Block");
				    }
		    		if(finalChildrenSpecimenCollection!=null)
		    		{
		    			Iterator finalChildrenSpecimenCollectionIterator = finalChildrenSpecimenCollection.iterator();
		    			while(finalChildrenSpecimenCollectionIterator.hasNext())
		    			{	    		
		    				totalChildrenSpecimenColl.add((Specimen)(finalChildrenSpecimenCollectionIterator.next()));
		    			}	    			
		    		}
		    	}
		    	if(totalChildrenSpecimenColl == null)
		    	{
		    		totalChildrenSpecimenColl = new ArrayList();
		    	}
		    	childrenSpecimenListToDisplay = getNameValueBeanList(totalChildrenSpecimenColl);
		    	arrayDetailsBean.setSpecimenList(childrenSpecimenListToDisplay);
		    	arrayDetailsBean.setSpecimenCollGroupId(pathologicalCaseOrderItem.getSpecimenCollectionGroup().getId().toString());
		    	if(isDerived)
			    {
		    		arrayDetailsBean.setInstanceOf("Derived");
			    }
			    else
			    {
			    	arrayDetailsBean.setInstanceOf("Pathological");
			    }
		    	arrayDetailsBean.setRequestedQuantity(pathologicalCaseOrderItem.getRequestedQuantity().getValue().toString());
		    	//Displaying the quantity of the first specimen in the request for drop down.
			  	if(childrenSpecimenListToDisplay.size() != 0)
			  	{
			  		arrayDetailsBean.setAvailableQuantity(((Specimen)totalChildrenSpecimenColl.get(0)).getAvailableQuantity().getValue().toString());
			  	}
			  	else
			  	{
			  		arrayDetailsBean.setAvailableQuantity("-");
			  	}
			  	//Assigned Quantity
			  	if(pathologicalCaseOrderItem.getDistributedItem() != null)
			  	{
			  		arrayDetailsBean.setAssignedQty(pathologicalCaseOrderItem.getDistributedItem().getQuantity().toString());
			  	}
			  	arrayDetailsBean.setAssignedStatus(pathologicalCaseOrderItem.getStatus());
			  	arrayDetailsBean.setClassName(pathologicalCaseOrderItem.getSpecimenClass());
			  	arrayDetailsBean.setType(pathologicalCaseOrderItem.getSpecimenType());
			  	arrayDetailsBean.setDescription(pathologicalCaseOrderItem.getDescription());
			  	arrayDetailsBean.setOrderItemId(pathologicalCaseOrderItem.getId().toString());
			  	//arrayDetailsBean.setSpeicmenId(pathologicalCaseOrderItem.get);
			}
			//arrayDetailsBean.setArrayRequestBean(arrayRequestBean);
			//Add all the arrayDetailsBean in the list
			arrayItemsList.add(arrayDetailsBean);
		}
		definedArrayMap.put(arrayRequestBean,arrayItemsList);
		
		//Return the list containing arrayDetailsBean instances
		return definedArrayMap;
	}

	//	 Populates a list of Requset object to display  as header info on RequestDetails.jsp
	
	/**
	 * @param id String
	 * @return RequestViewBean object
	 * @throws DAOException object
	 */
	private RequestViewBean getRequestObject(String id) throws DAOException
	{
		OrderBizLogic orderBizLogic = (OrderBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.REQUEST_LIST_FILTERATION_FORM_ID);
		String colName = "id";
		
		List orderListFromDB = orderBizLogic.retrieve(OrderDetails.class.getName(), colName, id);
		
		RequestViewBean requestViewBean = null;
		OrderDetails order = null;
		if(orderListFromDB !=null && orderListFromDB.isEmpty() == false)
		{
			Iterator iter = orderListFromDB.iterator();
			while(iter.hasNext())
			{
				order = (OrderDetails)iter.next();
				requestViewBean = OrderingSystemUtil.getRequestViewBeanToDisplay(order);
				requestViewBean.setComments(order.getComment());	
			}			
		}
		return requestViewBean;		
	}
	

	/**
	 * @param listToConvert Collection
	 * @return List. The namevaluebean list of children specimen to display.
	 */
	private List getNameValueBeanList(Collection listToConvert)
	{
		List nameValueBeanList = new ArrayList();
		
		Iterator iter = listToConvert.iterator();		
		while(iter.hasNext())
		{
			Specimen specimen = (Specimen)iter.next();
			nameValueBeanList.add(new NameValueBean(specimen.getLabel(),specimen.getId().toString()));
		}
		
		return nameValueBeanList;
	}
	
	/**
	 * @return List of site objects.
	 * @throws DAOException object
	 */
	private List getSiteListToDisplay() throws DAOException
	{
		OrderBizLogic orderBizLogic = (OrderBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.REQUEST_LIST_FILTERATION_FORM_ID);

		//Sets the Site list.
		String sourceObjectName = Site.class.getName();
		String[] displayNameFields = { "name" };
		String valueField = Constants.SYSTEM_IDENTIFIER;

		List siteList = orderBizLogic.getList(sourceObjectName, displayNameFields,valueField, true);
		
		return siteList;
	}
	/**
	 * @param requestDetailsBean RequestDetailsBean
	 * @param existingSpecimenorderItem ExistingSpecimenOrderItem
	 * @return RequestDetailsBean object
	 */
	private RequestDetailsBean populateRequestDetailsBeanForExistingSpecimen(RequestDetailsBean requestDetailsBean,ExistingSpecimenOrderItem existingSpecimenorderItem)
	{
		requestDetailsBean.setRequestedItem(existingSpecimenorderItem.getSpecimen().getLabel());
	    requestDetailsBean.setSpecimenId(existingSpecimenorderItem.getSpecimen().getId().toString());
	    List childrenSpecimenListToDisplay = new ArrayList();
	    	//getNameValueBeanList(existingSpecimenorderItem.getSpecimen().getChildrenSpecimen());
	    requestDetailsBean.setSpecimenList(childrenSpecimenListToDisplay);	    
	    requestDetailsBean.setInstanceOf("Existing");
	  	requestDetailsBean.setRequestedQty(existingSpecimenorderItem.getRequestedQuantity().getValue().toString());
	  	requestDetailsBean.setAvailableQty(existingSpecimenorderItem.getSpecimen().getAvailableQuantity().getValue().toString());
	  	//TODO remove from action class and put in action Form.
	    requestDetailsBean.setAssignedStatus(existingSpecimenorderItem.getStatus());
	 	requestDetailsBean.setClassName(existingSpecimenorderItem.getSpecimen().getClassName());
	 	requestDetailsBean.setType(existingSpecimenorderItem.getSpecimen().getType());
	  	requestDetailsBean.setDescription(existingSpecimenorderItem.getDescription());
	  	requestDetailsBean.setOrderItemId(existingSpecimenorderItem.getId().toString());
	  	//	  Assigned Quantity
	  	if(existingSpecimenorderItem.getDistributedItem() != null)
	  	{
	  		requestDetailsBean.setAssignedQty(existingSpecimenorderItem.getDistributedItem().getQuantity().toString());
	  	}
	  	
	  	return requestDetailsBean;
	}
	
	/**
	 * This function populates RequestDetailsBean object by fethcing data from DerivedSpecimenOrderItem domain object
	 * @param requestDetailsBean RequestDetailsBean object
	 * @param derivedSpecimenorderItem DerivedSpecimenOrderItem object
	 * @param request HttpServletRequest object
	 * @param finalSpecimenListId primitive integer value
	 * @return RequestDetailsBean object
	 */
	private RequestDetailsBean populateRequestDetailsBeanForDerivedSpecimen(RequestDetailsBean requestDetailsBean,DerivedSpecimenOrderItem derivedSpecimenorderItem,HttpServletRequest request,int finalSpecimenListId)
	{
		requestDetailsBean.setRequestedItem(derivedSpecimenorderItem.getSpecimen().getLabel());
		Long specimenId = derivedSpecimenorderItem.getSpecimen().getId();
		Collection childrenSpecimenList = OrderingSystemUtil.getAllChildrenSpecimen(derivedSpecimenorderItem.getSpecimen(),derivedSpecimenorderItem.getSpecimen().getChildrenSpecimen());
	    List finalChildrenSpecimenList = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childrenSpecimenList,derivedSpecimenorderItem.getSpecimenClass(),derivedSpecimenorderItem.getSpecimenType());
	    //	  removing final specimen List from session
		request.getSession().removeAttribute("finalSpecimenList"+finalSpecimenListId);
	    //To display the available quantity of the selected specimen from RequestFor dropdown.
	    request.getSession().setAttribute("finalSpecimenList"+finalSpecimenListId, finalChildrenSpecimenList);
	    
	    List childrenSpecimenListToDisplay = getNameValueBeanList(finalChildrenSpecimenList);
	    requestDetailsBean.setSpecimenList(childrenSpecimenListToDisplay);
	    requestDetailsBean.setSpecimenId(specimenId.toString());
	    requestDetailsBean.setInstanceOf("Derived");
	  	requestDetailsBean.setRequestedQty(derivedSpecimenorderItem.getRequestedQuantity().getValue().toString());
	  	//Displaying the quantity of the first specimen in the request for drop down.
	  	if(childrenSpecimenListToDisplay.size() != 0)
	  	{
	  		requestDetailsBean.setAvailableQty(((Specimen)finalChildrenSpecimenList.get(0)).getAvailableQuantity().getValue().toString());
	  	}
	  	else
	  	{
	  		requestDetailsBean.setAvailableQty(derivedSpecimenorderItem.getSpecimen().getAvailableQuantity().getValue().toString());
	  	}
	  	//Assigned Quantity
	  	if(derivedSpecimenorderItem.getDistributedItem() != null)
	  	{
	  		requestDetailsBean.setAssignedQty(derivedSpecimenorderItem.getDistributedItem().getQuantity().toString());
	  	}
	  	requestDetailsBean.setAssignedStatus(derivedSpecimenorderItem.getStatus());
	 	requestDetailsBean.setClassName(derivedSpecimenorderItem.getSpecimenClass());
	 	requestDetailsBean.setType(derivedSpecimenorderItem.getSpecimenType());
	  	requestDetailsBean.setDescription(derivedSpecimenorderItem.getDescription());
	  	requestDetailsBean.setOrderItemId(derivedSpecimenorderItem.getId().toString());
	  	
	  	return requestDetailsBean;
	}
	/**
	 * This function populates RequestDetailsBean instances by fetching data from PathologicalCaseOrderItem domain instance
	 * @param requestDetailsBean RequestDetailsBean object
	 * @param pathologicalCaseOrderItem DerivedSpecimenOrderItem object
	 * @param request HttpServletRequest object
	 * @param finalSpecimenListId primitive integer value
	 * @return requestDetailsBean RequestDetailsBean object
	 */
	private RequestDetailsBean populateRequestDetailsBeanForPathologicalCase(RequestDetailsBean requestDetailsBean,PathologicalCaseOrderItem pathologicalCaseOrderItem,HttpServletRequest request,int finalSpecimenListId)
	{
		boolean isDerived = false;
		requestDetailsBean.setRequestedItem(pathologicalCaseOrderItem.getSpecimenCollectionGroup().getIdentifiedSurgicalPathologyReport().getAccessionNumber());
	    
		Collection childrenSpecimenList = pathologicalCaseOrderItem.getSpecimenCollectionGroup().getSpecimenCollection();
		// Removing distributed option if no specimens are present in that SCG. ie. childrenSpecimenList.size() == 0
		if(childrenSpecimenList.size() == 0)
		{
			isDerived = true;
		}
	    	Iterator childrenSpecimenListIterator = childrenSpecimenList.iterator();
	    	List totalChildrenSpecimenColl = null;
	    	List childrenSpecimenListToDisplay=null;
	    	while (childrenSpecimenListIterator.hasNext())
	    	{
	    		Specimen specimen = (Specimen)childrenSpecimenListIterator.next();
	    		List childSpecimenCollection = OrderingSystemUtil.getAllChildrenSpecimen(specimen,specimen.getChildrenSpecimen());
	    		List finalChildrenSpecimenCollection = null;
	    		if(pathologicalCaseOrderItem.getSpecimenClass() != null && pathologicalCaseOrderItem.getSpecimenType() != null && !pathologicalCaseOrderItem.getSpecimenClass().trim().equalsIgnoreCase("") && !pathologicalCaseOrderItem.getSpecimenType().trim().equalsIgnoreCase(""))
	    	    {
	    			//"Derived"	   
	    			finalChildrenSpecimenCollection = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childSpecimenCollection,pathologicalCaseOrderItem.getSpecimenClass(),pathologicalCaseOrderItem.getSpecimenType());
	    			isDerived = true;
	    	    }	    	
			    else
			    {
			    	//"Block" . Specimen class = "Tissue" , Specimen Type = "Block".
			    	finalChildrenSpecimenCollection = OrderingSystemUtil.getChildrenSpecimenForClassAndType(childSpecimenCollection,"Tissue","Block");
			    }
	    		if(finalChildrenSpecimenCollection!=null)
	    		{
	    			Iterator finalChildrenSpecimenCollectionIterator = finalChildrenSpecimenCollection.iterator();
	    			while(finalChildrenSpecimenCollectionIterator.hasNext())
	    			{	    		
	    				totalChildrenSpecimenColl.add((Specimen)(finalChildrenSpecimenCollectionIterator.next()));
	    			}	    			
	    		}
	    	}
	    	if(totalChildrenSpecimenColl == null)
	    	{
	    		totalChildrenSpecimenColl = new ArrayList();
	    	}
	    	//	    	removing final specimen List from session
			request.getSession().removeAttribute("finalSpecimenList"+finalSpecimenListId);
	    	//To display the available quantity of the selected specimen from RequestFor dropdown.
		    request.getSession().setAttribute("finalSpecimenList"+finalSpecimenListId, totalChildrenSpecimenColl);
		    
    		childrenSpecimenListToDisplay = getNameValueBeanList(totalChildrenSpecimenColl);
	    	requestDetailsBean.setSpecimenList(childrenSpecimenListToDisplay);
	    
	    requestDetailsBean.setSpecimenCollGroupId(pathologicalCaseOrderItem.getSpecimenCollectionGroup().getId().toString());
	    if(isDerived)
	    {
	    	 requestDetailsBean.setInstanceOf("Derived");
	    }
	    else
	    {
	    	requestDetailsBean.setInstanceOf("Pathological");
	    }
	  	requestDetailsBean.setRequestedQty(pathologicalCaseOrderItem.getRequestedQuantity().getValue().toString());
	  	//Displaying the quantity of the first specimen in the request for drop down.
	  	if(childrenSpecimenListToDisplay.size() != 0)
	  	{
	  		requestDetailsBean.setAvailableQty(((Specimen)totalChildrenSpecimenColl.get(0)).getAvailableQuantity().getValue().toString());
	  	}
	  	else
	  	{
	  		requestDetailsBean.setAvailableQty("-");
	  	}
	  	//	  Assigned Quantity
	  	if(pathologicalCaseOrderItem.getDistributedItem() != null)
	  	{
	  		requestDetailsBean.setAssignedQty(pathologicalCaseOrderItem.getDistributedItem().getQuantity().toString());
	  	}
	  	requestDetailsBean.setAssignedStatus(pathologicalCaseOrderItem.getStatus());
	 	requestDetailsBean.setClassName(pathologicalCaseOrderItem.getSpecimenClass());
	 	requestDetailsBean.setType(pathologicalCaseOrderItem.getSpecimenType());
	  	requestDetailsBean.setDescription(pathologicalCaseOrderItem.getDescription());
	  	requestDetailsBean.setOrderItemId(pathologicalCaseOrderItem.getId().toString());
	  	
	  	return requestDetailsBean;
	}
	
	/**
	 * This function populates the items status for individual order items in OrderList,DefinedArray List and Existing Array List
	 * and sets each of the list in the request attribute. 
	 * @param requestList ArrayList containing the list of orderitem objects 
	 * @param request HttpServletRequest object
	 * @param itemsAddedToArray String indicating whether items are to be added to defined array,existing array or to the speicmen orderlist
	 */
	void populateItemStatusList(List requestList,HttpServletRequest request,String itemsAddedToArray)
	{
		Iterator requestlistItr = requestList.iterator();
		while(requestlistItr.hasNext())
		{
			Object orderItemObj = requestlistItr.next();

			//In case of define array
			if(orderItemObj instanceof Map)
			{
				Map defineArrayMap = (HashMap)orderItemObj;
				Set keys = defineArrayMap.keySet();
				Iterator keySetItr = keys.iterator();
				while(keySetItr.hasNext())
				{
					DefinedArrayRequestBean definedArrayRequestBean = (DefinedArrayRequestBean)keySetItr.next(); 
					
					//Obtain request list for each defined array 
					List defineArrayDetailsList = (ArrayList)defineArrayMap.get(definedArrayRequestBean);
					Iterator defineArrayDetailsListItr = defineArrayDetailsList.iterator();
					while(defineArrayDetailsListItr.hasNext())
					{
						DefinedArrayDetailsBean definedArrayDetailsBean = (DefinedArrayDetailsBean)defineArrayDetailsListItr.next();
						
						//Get possible next statuses for a given status
						List possibleStatusList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
						int indexToRemove = 0;
						Iterator statusListIter = possibleStatusList.iterator();
						while(statusListIter.hasNext())
						{
							NameValueBean nameValueBeanObj = (NameValueBean)statusListIter.next();
							if(nameValueBeanObj.getName().trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
							{
								indexToRemove = possibleStatusList.indexOf(nameValueBeanObj);
							}
						}
						//Remove 'Distribute' Status for individual order item in Defined Array
						possibleStatusList.remove(indexToRemove);
						
						//Set possibleStatusList in the DefinedArrayDetails bean instance.
						definedArrayDetailsBean.setItemStatusList(possibleStatusList);
					} //End while (while loop to iterate definedArrayList)
				}//End while (While loop to iterate list containing map objects for deifned array)
				
			}//End if(orderItemObj instanceof Map)
			else
			{
				//In case of Existing Array
				if(orderItemObj instanceof ExistingArrayDetailsBean)
				{
						ExistingArrayDetailsBean existingArrayDetailsBean = (ExistingArrayDetailsBean)orderItemObj;
						List possibleStatusList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
						existingArrayDetailsBean.setItemStatusList(possibleStatusList);
				}
				//In case of Request Details List displayed in RequestDetails.jsp
//				if(orderItemObj instanceof RequestDetailsBean)
//				{
//					RequestDetailsBean requestDetailsBean = (RequestDetailsBean) orderItemObj;
//					List possibleStatusList = OrderingSystemUtil.getPossibleStatusList(Constants.ORDER_REQUEST_STATUS_NEW);
//					
//					//Removing the status 'Distributed' if the requested Specimen is not present in the Db.
//					if(requestDetailsBean.getInstanceOf().trim().equalsIgnoreCase("Derived"))
//					{
//						if(requestDetailsBean.getSpecimenList().size() == 0)
//						{
//							int indexToRemove = 0;
//							Iterator statusListIter = possibleStatusList.iterator();
//							while(statusListIter.hasNext())
//							{
//								NameValueBean nameValueBeanObj = (NameValueBean)statusListIter.next();
//								if(nameValueBeanObj.getName().trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
//								{
//									indexToRemove = possibleStatusList.indexOf(nameValueBeanObj);
//								}
//							}
//							possibleStatusList.remove(indexToRemove);
//						}	
//					}
//					requestDetailsBean.setItemsStatusList(possibleStatusList);
//				}
				
			}//End else
			
		}//End OuterMost while
		
			/*Set the requestList in request Attribute*/
		if(itemsAddedToArray.equals("addToDefinedArrays"))
		{
			//Set the request attribute ARRAY_REQUESTSMAP_LIST when items are to be displayed in the DefinedArrays List(ArrayRequests.jsp)
			request.setAttribute(Constants.DEFINEDARRAY_REQUESTS_LIST,requestList);
		}
		
		if(itemsAddedToArray.equals("addToExistingArrays"))
		{
			//Set the request attribute ARRAY_REQUESTSMAP_LIST when items are to be displayed in the Existing Arrays List(ArrayRequests.jsp)
			request.setAttribute(Constants.EXISISTINGARRAY_REQUESTS_LIST,requestList);
		}
		
		if(itemsAddedToArray.equals("addToOrderList"))
		{
			//Set the request attribute REQUEST_DETAILS_LIST when items are to be displayed in the RequestDetails.jsp
			request.setAttribute(Constants.REQUEST_DETAILS_LIST,requestList);
		}
	}
	
}