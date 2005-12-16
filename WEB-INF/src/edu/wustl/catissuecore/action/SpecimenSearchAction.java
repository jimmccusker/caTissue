/**
 * <p>Title: SpecimenSearchAction Class>
 * <p>Description:	This class initializes the fields of SpecimenSearch.jsp Page</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Oct 26, 2005
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
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

import edu.wustl.catissuecore.actionForm.AdvanceSearchForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.QueryBizLogic;
import edu.wustl.catissuecore.util.SearchUtil;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.cde.CDE;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.cde.PermissibleValue;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.logger.Logger;

public class SpecimenSearchAction extends AdvanceSearchUIAction
{
    /**
     * Overrides the execute method of Action class.
     * Initializes the various fields in SpecimenSearch.jsp Page.
     * */
    protected ActionForward executeAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	//Setting the Sepecimen Type list
    	List specimenTypeList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_SPECIMEN_TYPE,null);
    	request.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
    	
    	//Setting Tissue Site list
    	NameValueBean undefinedVal = new NameValueBean(Constants.UNDEFINED,Constants.UNDEFINED);
    	List tissueSiteList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_TISSUE_SITE,undefinedVal);
    	request.setAttribute(Constants.TISSUE_SITE_LIST, tissueSiteList);
    	
    	//Setting Tissue Side list
    	NameValueBean unknownVal = new NameValueBean(Constants.UNKNOWN,Constants.UNKNOWN);
    	List tissueSideList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_TISSUE_SIDE,unknownVal);
    	request.setAttribute(Constants.TISSUE_SIDE_LIST, tissueSideList);
        
    	//Setting Pathological Status list
    	List pathologicalStatusList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_PATHOLOGICAL_STATUS,null);
    	request.setAttribute(Constants.PATHOLOGICAL_STATUS_LIST, pathologicalStatusList);
    	
    	//Setting Biohazard type list
    	List biohazardList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_BIOHAZARD,null);
    	request.setAttribute(Constants.BIOHAZARD_TYPE_LIST, biohazardList);
    	
    	//Set the selected node from the query tree
    	String nodeCount = request.getParameter("selectedNode");
    	Logger.out.debug("nodecount from getParameter"+nodeCount);
    	AdvanceSearchForm aForm = (AdvanceSearchForm)form;
    	aForm.setSelectedNode(nodeCount);
    	
    	//Setting the operators list in request scope
        request.setAttribute(Constants.STRING_OPERATORS,SearchUtil.getOperatorList(SearchUtil.DATATYPE_STRING));
        request.setAttribute(Constants.DATE_NUMERIC_OPERATORS,SearchUtil.getOperatorList(SearchUtil.DATATYPE_NUMERIC));
        request.setAttribute(Constants.ENUMERATED_OPERATORS,SearchUtil.getOperatorList(SearchUtil.DATATYPE_ENUMERATED));

    	//TO DO : To be moved to common utility class
        try
		{
        	// get the Specimen class and type from the cde
        	CDE specimenClassCDE = CDEManager.getCDEManager().getCDE(Constants.CDE_NAME_SPECIMEN_CLASS);
	    	Set setPV = specimenClassCDE.getPermissibleValues();
	    	Iterator itr = setPV.iterator();
	    
	    	List specimenClassList =  new ArrayList();
	    	Map subTypeMap = new HashMap();
	    	specimenClassList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
	    	
	    	//Creating a map of subtypes against their types (class names)
	    	while(itr.hasNext())
	    	{
	    		List innerList =  new ArrayList();
	    		Object obj = itr.next();
	    		PermissibleValue pv = (PermissibleValue)obj;
	    		String tmpStr = pv.getValue();
	    		Logger.out.debug(tmpStr);
	    		specimenClassList.add(new NameValueBean( tmpStr,tmpStr));
	    		
				Set list1 = pv.getSubPermissibleValues();
	        	Iterator itr1 = list1.iterator();
	        	innerList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
	        	
	        	while(itr1.hasNext())
	        	{
	        		Object obj1 = itr1.next();
	        		PermissibleValue pv1 = (PermissibleValue)obj1;
	        		//Setting the specimen type
	        		String tmpInnerStr = pv1.getValue();
	        		innerList.add(new NameValueBean( tmpInnerStr,tmpInnerStr));  
	        	}
	        	subTypeMap.put(pv.getValue(),innerList);
	    	}
	    	
	    	//Setting the class list
	    	request.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);

	    	//Setting the map of subtypes
	    	request.setAttribute(Constants.SPECIMEN_TYPE_MAP, subTypeMap);
		}
        catch(Exception excp)
		{
        	Logger.out.error(excp.getMessage(),excp);
        	return mapping.findForward(Constants.FAILURE);
		}
       
        //Preparing the data for Specimen Event Parameters
        QueryBizLogic bizLogic = (QueryBizLogic)BizLogicFactory.getBizLogic(Constants.SIMPLE_QUERY_INTERFACE_ID);
        
        List eventParametersTables = SearchUtil.getEventParametersTables(bizLogic);
        Map map = getEventParametersMap(bizLogic,eventParametersTables);
        
        request.setAttribute(Constants.ALIAS_NAME_TABLE_NAME_MAP,eventParametersTables);
        request.setAttribute(Constants.TABLE_COLUMN_DATA_MAP,map);
        
      //  Represents id of checked checkbox
        String str = request.getParameter("itemId");
        
        if(str != null)
        {
        	setMapValue(aForm,str,request);
        	//setEventParameterMap(aForm);
        	aForm.setItemNodeId(str);
        }
        Logger.out.debug("event map***"+aForm.getEventValues());
        if(!aForm.getEventValues().isEmpty())
        {
        	aForm.setEventCounter(aForm.getEventValues().size()/4);
        }
       
    	String pageOf = (String)request.getParameter(Constants.PAGEOF);
    	return mapping.findForward(pageOf);
    }
    
    //This function returns the map of event parameters to their column names
    private Map getEventParametersMap(QueryBizLogic bizLogic, List tableList) throws DAOException,ClassNotFoundException
    {
    	HashMap tableColumnMap = new HashMap();
		
    	//Extracting column names per table name
    	for(int i=1;i<tableList.size();i++)
    	{
    		NameValueBean bean = (NameValueBean)tableList.get(i);
    		String className = bean.getValue();
    		tableColumnMap.put(className,bizLogic.getColumnNames(className));
    	}
    	
    	return tableColumnMap;
    }
}