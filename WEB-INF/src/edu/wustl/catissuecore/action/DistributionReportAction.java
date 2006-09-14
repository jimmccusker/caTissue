
package edu.wustl.catissuecore.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.ConfigureResultViewForm;
import edu.wustl.catissuecore.actionForm.DistributionReportForm;
import edu.wustl.catissuecore.domain.Distribution;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.SessionDataBean;

/**
 * This is the action class for displaying the Distribution report
 * @author Poornima Govindrao
 *  
 */

public class DistributionReportAction extends BaseDistributionReportAction
{
	protected ActionForward executeAction(ActionMapping mapping,ActionForm form, HttpServletRequest request,
															HttpServletResponse response) throws Exception
	{
		ConfigureResultViewForm configForm = (ConfigureResultViewForm)form;
		
		//Retrieve the distribution ID which is set in CommonAddEdit Action 
		Long distributionId = (Long)request.getAttribute(Constants.DISTRIBUTION_ID);
		
		//retrieve from configuration form if it is null
		if(distributionId==null)
    		distributionId = configForm.getDistributionId();

		/*Retrieve from request attribute if it null. 
		 */ 
		if(distributionId==null)
		{
			distributionId = (Long) request.getAttribute(Constants.SYSTEM_IDENTIFIER);
		}

		/*Retrieve from request parameter if it null. This request parameter is set in Distribution page incase the Report button 
		 *is clicked from Distribution Edit page
		 */ 
		if(distributionId==null)
		{
			distributionId = new Long(request.getParameter(Constants.SYSTEM_IDENTIFIER));
		}
		
		//Set it in configuration form if it is not null 
		if(distributionId!=null)
    		configForm.setDistributionId(distributionId);
			
    	Distribution dist =  getDistribution(distributionId, getSessionData(request), Constants.CLASS_LEVEL_SECURE_RETRIEVE);
    	
    	//Retrieve the distributed items data
    	DistributionReportForm distributionReportForm = getDistributionReportForm(dist);
    	SessionDataBean sessionData = getSessionData(request);
    	List listOfData = getListOfData(dist, configForm,sessionData) ;
    	
    	//Set the columns for Distribution report
		String action = configForm.getNextAction();
		String selectedColumns[] = getSelectedColumns(action,configForm,false);
		String []columnNames = getColumnNames(selectedColumns);
    	
		//Set the request attributes for the Distribution report data
		request.setAttribute(Constants.DISTRIBUTION_REPORT_FORM, distributionReportForm);
    	request.setAttribute(Constants.COLUMN_NAMES_LIST, columnNames);
    	request.setAttribute(Constants.DISTRIBUTED_ITEMS_DATA, listOfData);
    	setSelectedMenuRequestAttribute(request);
		return (mapping.findForward("Success"));
	}
}