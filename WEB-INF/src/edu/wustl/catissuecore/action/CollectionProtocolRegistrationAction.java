/**
 * <p>Title: DepartmentAction Class</p>
 * <p>Description:	This class initializes the fields in the Department Add/Edit webpage.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ajay Sharma
 * @version 1.00
 * Created on May 23rd, 2005
 */

package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.bizlogic.AbstractBizLogic;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.util.global.Constants;

/**
 * This class initializes the fields in the User Add/Edit webpage.
 * @author ajay_sharma
 */

public class CollectionProtocolRegistrationAction extends Action
{

	/**
	 * Overrides the execute method of Action class.
	 * Sets the various fields in Participant Registration Add/Edit webpage.
	 * */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		//Gets the value of the operation parameter.
		String operation = request.getParameter(Constants.OPERATION);

		//Sets the operation attribute to be used in the Add/Edit User Page. 
		request.setAttribute(Constants.OPERATION, operation);

		try
		{
			AbstractBizLogic bizLogic = BizLogicFactory
					.getBizLogic(Constants.COLLECTION_PROTOCOL_REGISTRATION_FORM_ID);

			//get list of Protocol title.
			String sourceObjectName = CollectionProtocol.class.getName();
			String[] displayNameFields = {"title"};
			String valueField = "systemIdentifier";
			List list = bizLogic.getList(sourceObjectName, displayNameFields, valueField);
			request.setAttribute(Constants.PROTOCOL_LIST, list);

			//get list of Participant's names
			sourceObjectName = CollectionProtocol.class.getName();
			String[] participantsFields = {"title"};
			list = bizLogic.getList(sourceObjectName, participantsFields, valueField);
			request.setAttribute(Constants.PARTICIPANT_LIST, list);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}

		return mapping.findForward(Constants.SUCCESS);
	}
}