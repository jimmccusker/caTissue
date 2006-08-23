
package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import edu.wustl.catissuecore.actionForm.StorageContainerForm;
import edu.wustl.common.action.CommonAddEditAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.util.logger.Logger;

public class SimilarContainerAddAction extends CommonAddEditAction
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException,
			ServletException
	{
		StorageContainerForm storageContainerForm = (StorageContainerForm) form;
		Logger.out.info("Map in similarContainerAction:"
				+ storageContainerForm.getSimilarContainersMap());
		ActionForward forward = super.execute(mapping, form, request, response);
		Logger.out.info("forward in similar container add action:" + forward.getName());

		List list = new ArrayList();
		ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);

		Logger.out.info("Errors:" + errors);
		if (errors == null || errors.size() == 0)
		{
			ActionMessages messages = null;
			messages = new ActionMessages();
			messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"similarContaienrs.add.success", new Integer(storageContainerForm
							.getNoOfContainers()).toString()));
			if (messages != null)
			{
				saveMessages(request, messages);
			}
			Logger.out.info("Map in similarContainerAction after insert:"
					+ storageContainerForm.getSimilarContainersMap());

			int noOfContainers = storageContainerForm.getNoOfContainers();
			Map simMap = storageContainerForm.getSimilarContainersMap();

			for (int i = 1; i <= noOfContainers; i++)
			{
				String simContPrefix = "simCont:" + i + "_";
				String contName = (String) simMap.get(simContPrefix + "name");
				Logger.out.info("contName:" + contName);
				String Id = new Long(storageContainerForm.getSystemIdentifier()
						- (noOfContainers - i)).toString();
				Logger.out.info("Id:" + Id);
				list.add(new NameValueBean(contName, Id));
			}
			request.setAttribute("similarContainerList", list);

		}
		Logger.out.info("Forward:" + forward.getName());
		return forward;

	}
}
