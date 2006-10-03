/*
 * Created on Oct 3, 2006
 * 
 */
package edu.wustl.catissuecore.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.AliquotForm;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;


/**
 * @author jitendra_agrawal
 */
public class AliquotSummaryAction extends BaseAction
{

	/**
	 * This method will get call from Aliquot Summary page. 
	 * Overrides the execute method of Action class.
	 */
	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{		
		AliquotForm aliquotForm = (AliquotForm) form;
		String noOfAliquouts = aliquotForm.getNoOfAliquots();
		Map aliquotMap = aliquotForm.getAliquotMap();
		aliquotMap.put("noOfAliquots",noOfAliquouts);
		request.setAttribute("forwardToHashMap", aliquotMap);
		return mapping.findForward(Constants.SUCCESS);
	}
}
