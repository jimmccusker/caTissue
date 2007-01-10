package edu.wustl.catissuecore.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.bizlogic.IBizLogic;
import edu.wustl.catissuecore.actionForm.DefineArrayForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.SpecimenArrayBizLogic;
import edu.wustl.catissuecore.domain.Capacity;
import edu.wustl.catissuecore.domain.ContainerType;
import edu.wustl.catissuecore.domain.SpecimenArrayType;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.logger.Logger;

public class DefineArrayAction extends BaseAction
{
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	DefineArrayForm defineArray=(DefineArrayForm) form;
    	HttpSession session = request.getSession();
        if(session.getAttribute("OrderForm")!=null)
        {
	    	try
	    	{
	    		String[] arrayTypeLabelProperty = {"name"};
	            String  arrayTypeProperty = "id";
	            SpecimenArrayBizLogic specimenArrayBizLogic = (SpecimenArrayBizLogic)BizLogicFactory.getInstance().getBizLogic(Constants.SPECIMEN_ARRAY_FORM_ID);
	            List specimenArrayTypeList = new ArrayList();
	
	   	        specimenArrayTypeList = specimenArrayBizLogic.getList(SpecimenArrayType.class.getName(),arrayTypeLabelProperty, arrayTypeProperty, true);
		        for (Iterator iter = specimenArrayTypeList.iterator(); iter.hasNext();) 
		        {
					NameValueBean nameValueBean = (NameValueBean) iter.next();
					// remove ANY entry from array type list
					if (nameValueBean.getValue().equals(Constants.ARRAY_TYPE_ANY_VALUE) && nameValueBean.getName().equalsIgnoreCase(Constants.ARRAY_TYPE_ANY_NAME))
					{
						iter.remove();
						break;
					}
				}
		        request.setAttribute(Constants.SPECIMEN_ARRAY_TYPE_LIST, specimenArrayTypeList);
	    	}
	    	catch(Exception e)
	    	{
	    		Logger.out.error(e.getMessage(), e);
	    		return null;
	    	}
	    	
	
	    	if(request.getParameter("arrayType")!=null)
	    	{
	    		try
	        	{
	        		IBizLogic bizLogic = BizLogicFactory.getInstance().getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
	
	        		String sourceObjectName = SpecimenArrayType.class.getName();
	    	    	String columnName="id";
	    			List containerType = bizLogic.retrieve(sourceObjectName, columnName, request.getParameter("arrayType"));
	    			SpecimenArrayType containerTyperow=(SpecimenArrayType)containerType.get(0);
	    			//SpecimenArrayType spec=(SpecimenArrayType)containerType.get(0);
	    			
	    			Capacity capacityobj=(Capacity)containerTyperow.getCapacity();
	    			
	    			defineArray.setDimenmsionX(new Integer(capacityobj.getOneDimensionCapacity()).toString());
	    			defineArray.setDimenmsionY(new Integer(capacityobj.getTwoDimensionCapacity()).toString());
	    			defineArray.setArrayClass(containerTyperow.getSpecimenClass());
	
	    			String dimen=new Integer(capacityobj.getOneDimensionCapacity()).toString()+":"+new Integer(capacityobj.getTwoDimensionCapacity()).toString()+":"+defineArray.getArrayClass();
	    			
	    			PrintWriter out = response.getWriter();
	    			response.setContentType("text/html");
	    			out.write(dimen);
	    			return null;
	        	}
	        	catch(DAOException e)
	        	{
	        		Logger.out.error(e.getMessage(), e);
	        		return null;
	        	}
	    	}
	    	String typeOf=null;
	    	typeOf=request.getParameter("typeOf");
	    	
	    	if(typeOf==null)
	    		typeOf=request.getAttribute("typeOf").toString();
	    	
	    	request.setAttribute("typeOf",typeOf);
	    	
	    	request.setAttribute("DefineArrayForm", defineArray);
	    	return mapping.findForward("success");
        }
        else
        	return mapping.findForward("failure");
    }
}
