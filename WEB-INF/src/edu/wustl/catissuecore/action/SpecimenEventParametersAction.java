/**
 * <p>Title: FrozenEventParametersAction Class>
 * <p>Description:	This class initializes the fields in the FrozenEventParameters Add/Edit webpage.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on July 28, 2005
 */

package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.UserBizLogic;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.util.logger.Logger;

/**
 * This class initializes the fields in the FrozenEventParameters Add/Edit webpage.
 * @author mandar deshmukh
 */
public class SpecimenEventParametersAction extends Action
{
	protected void setRequestParameters(HttpServletRequest request)
	{
        //Gets the value of the operation parameter.
        String operation = request.getParameter(Constants.OPERATION);

        //Sets the operation attribute to be used in the Add/Edit FrozenEventParameters Page. 
        request.setAttribute(Constants.OPERATION, operation);

        //Sets the minutesList attribute to be used in the Add/Edit FrozenEventParameters Page.
        request.setAttribute(Constants.MINUTESLIST, Constants.MINUTESARRAY);

        //Sets the hourList attribute to be used in the Add/Edit FrozenEventParameters Page.
        request.setAttribute(Constants.HOURLIST, Constants.HOURARRAY);
        
       try
       {
        	
        	UserBizLogic userBizLogic = (UserBizLogic)BizLogicFactory.getBizLogic(Constants.USER_FORM_ID);
        	Collection coll =  userBizLogic.getUsers(Constants.ACTIVITY_STATUS_ACTIVE);
//			Collection coll =  new ArrayList();
//			NameValueBean aNameValueBean = new NameValueBean();
//			aNameValueBean.setName(Constants.SELECT_OPTION);
//			aNameValueBean.setValue("-1");
//			coll.add(aNameValueBean);
//		
        	request.setAttribute(Constants.USERLIST, coll);
        	
        	List qualityList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_RECEIVED_QUALITY);
        	request.setAttribute(Constants.RECEIVED_QUALITY_LIST, qualityList);
            
        }
        catch (Exception exc)
        {
            Logger.out.error(exc.getMessage());
        }
	}
    /**
     * Overrides the execute method of Action class.
     * Sets the various fields in FrozenEventParameters Add/Edit webpage.
     * */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
    	setRequestParameters(request);
        return mapping.findForward(Constants.SUCCESS);
    }
}