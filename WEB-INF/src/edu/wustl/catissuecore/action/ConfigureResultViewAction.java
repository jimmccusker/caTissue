package edu.wustl.catissuecore.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.bizlogic.AbstractBizLogic;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.QueryBizLogic;
import edu.wustl.catissuecore.domain.QueryTableData;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.util.logger.Logger;


/**
 * This is the action class for configuring columns in result view 
 * @author Poornima Govindrao
 *  
 */
public class ConfigureResultViewAction extends BaseAction  {

	protected ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			//String target = new String();
			AbstractBizLogic dao = BizLogicFactory.getBizLogic(Constants.CONFIGURE_RESULT_VIEW_ID);
			String pageOf = (String) request.getAttribute(Constants.PAGEOF);
			if(pageOf == null)
				pageOf = (String) request.getParameter(Constants.PAGEOF);
			//String []tables = (String [])request.getAttribute(Constants.TABLE_ALIAS_NAME);
			HttpSession session =request.getSession();
    		
			//String []tables = (String[])session.getAttribute(Constants.TABLE_ALIAS_NAME);
			Object []tables = (Object[])session.getAttribute(Constants.TABLE_ALIAS_NAME);
			String sourceObjectName = QueryTableData.class.getName();
	        String[] displayNameField = {"displayName"};
	        String valueField = "aliasName";
	        
	        String[] whereColumnNames = {"aliasName"};
	        String [] whereCondition = {"in"};
	        Object [] whereColumnValues = {tables};
	        //List of objects containing TableNames and aliasName
	        List tableList = dao.getList(sourceObjectName, displayNameField, valueField, 
    				whereColumnNames, whereCondition, whereColumnValues,null,null,false);
     
	        //List of Column data corresponding to table names.
	        /*sourceObjectName = QueryColumnData.class.getName();
	        String valueField1 = "columnName";
	        String [] whereCondition1 = {"="};
	        whereColumnNames[0] = "tableData.identifier";*/
	       
	      
	        Map tableColumnDataMap = new HashMap();
	        
	        Iterator itr = tableList.iterator();
	        while(itr.hasNext())
	        {
	        	NameValueBean tableData = (NameValueBean)itr.next();
	        	if(!tableData.getName().equals(Constants.SELECT_OPTION))
	        	{
	        		QueryBizLogic bizLogic = (QueryBizLogic)BizLogicFactory.getBizLogic(Constants.SIMPLE_QUERY_INTERFACE_ID);
	        		List columnList =  bizLogic.setColumnNames(tableData.getValue());
	        		tableColumnDataMap.put(tableData,columnList);
	        		
	        	}
	        	Logger.out.debug("Table Name"+ tableData.getValue());
		        //Logger.out.debug("Column List"+ columnList);
		        
	        }
	        
	        Logger.out.debug("Table Map"+tableColumnDataMap);
	        request.setAttribute(Constants.TABLE_COLUMN_DATA_MAP,tableColumnDataMap);
	        request.setAttribute(Constants.PAGEOF,pageOf);
	        Logger.out.debug("pageOf in configure result view:"+pageOf);
	        /*if(pageOf.equals(Constants.PAGEOF_SIMPLE_QUERY_INTERFACE))
	        	target = Constants.PAGEOF_SIMPLE_QUERY_INTERFACE;
			else if(pageOf.equals(Constants.PAGEOF_QUERY_RESULTS))
				target = Constants.PAGEOF_QUERY_RESULTS;*/
	        
			return mapping.findForward(pageOf);
	        //return mapping.findForward("success");
	}

}