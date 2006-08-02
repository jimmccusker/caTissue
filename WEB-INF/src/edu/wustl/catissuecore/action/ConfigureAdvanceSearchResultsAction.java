/*
 * Created on Nov 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.AdvanceSearchForm;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.dao.DAOFactory;
import edu.wustl.common.dao.JDBCDAO;
import edu.wustl.common.util.logger.Logger;

/**
 * @author poornima_govindrao
 *
 * This action filters the data from the Advance Search Results according to the
 * configured columns.
 */
public class ConfigureAdvanceSearchResultsAction extends BaseAction
{
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		AdvanceSearchForm advForm = (AdvanceSearchForm)form;
		//get the columns selected in configureResultView
		String []configuredColumns = advForm.getSelectedColumnNames();
		HttpSession session = request.getSession();
		//Get the column ids for the selected columns to search in the temporary table
		Map columnIdsMap = (Map)session.getAttribute(Constants.COLUMN_ID_MAP);
		String[] selectColumns = new String[configuredColumns.length];
		List columnDisplayNames = new ArrayList();
		for(int i=0;i<configuredColumns.length;i++)
		{
			//Split the columns which is in the form tableAlias.columnName.columnDisplayNames
			StringTokenizer selectedColumnsTokens = new StringTokenizer(configuredColumns[i],".");
			//get the column Id of the selected column to search in the temporary table
			int columnId = ((Integer)columnIdsMap.get(selectedColumnsTokens.nextToken()+"."+selectedColumnsTokens.nextToken())).intValue()-1;
			Logger.out.debug("column id of configured column"+columnId);
			columnDisplayNames.add(selectedColumnsTokens.nextToken());
			selectColumns[i] = Constants.COLUMN+columnId;
    		/*if(selectedColumnsTokens.hasMoreTokens())
    			selectedColumnsTokens.nextToken();*/

		}
		SessionDataBean sessionData = getSessionData(request);
		//temporary table name
		String tableName = Constants.QUERY_RESULTS_TABLE+"_"+sessionData.getUserId();
        JDBCDAO jdbcDAO = (JDBCDAO)DAOFactory.getInstance().getDAO(Constants.JDBC_DAO);
        jdbcDAO.openSession(sessionData);
        String [] selectColumnNames = selectColumns;
        Logger.out.debug(" Selected Columns:"+selectColumns);
        
        //Bug#2003: For having unique records in result view
        List list = jdbcDAO.retrieve(tableName, selectColumnNames, true); 
        //end Bug#2003
        
        jdbcDAO.closeSession();
        
        session.setAttribute(Constants.CONFIGURED_SELECT_COLUMN_LIST,selectColumnNames);
        session.setAttribute(Constants.CONFIGURED_COLUMN_DISPLAY_NAMES,columnDisplayNames);
        session.setAttribute(Constants.CONFIGURED_COLUMN_NAMES,configuredColumns);
        
        request.setAttribute(Constants.SPREADSHEET_DATA_LIST,list);
        
    	
        request.setAttribute(Constants.SPREADSHEET_COLUMN_LIST,columnDisplayNames);
        request.setAttribute(Constants.PAGEOF,Constants.PAGEOF_QUERY_RESULTS);
		return mapping.findForward(Constants.SUCCESS);
	}

}
