/*
 * <p>Title: SpecimenArrayTypeAction Class </p>
 * <p>Description:This class performs action level logic. </p>
 * Copyright: Copyright (c) year 2006
 * Company: Washington University, School of Medicine, St. Louis.
 * @version 1.1
 * Created on July 24,2006
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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.SpecimenArrayTypeForm;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.cde.CDE;
import edu.wustl.common.cde.CDEManager;
import edu.wustl.common.cde.PermissibleValue;
import edu.wustl.common.util.logger.Logger;


/**
 * @author Ashwin Gupta
 */
public class SpecimenArrayTypeAction extends Action
{

	/**
	 * Key used in map 
	 */
	private final String specimenClassKey = "SPECIMEN_CLASS";
	
	/**
	 * Key used in map 
	 */
	private final String specimenTypeKey = "SPECIMEN_TYPE";
	
	/** 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String operation = request.getParameter(Constants.OPERATION);
        request.setAttribute(Constants.OPERATION, operation);
        
        //Setting the specimen class list
        List specimenClassList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_SPECIMEN_CLASS,null);
    	request.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);
    	
    	//Setting the specimen type list
    	List specimenTypeList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_SPECIMEN_TYPE,null);
    	request.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
    	
    	Map specimen_Type_Class_Map = getSpecimenClassAndType();
    	request.setAttribute(Constants.SPECIMEN_CLASS_LIST, (List) specimen_Type_Class_Map.get(specimenClassKey));
    	request.setAttribute(Constants.SPECIMEN_TYPE_MAP, (Map) specimen_Type_Class_Map.get(specimenTypeKey));
    	
    	String strMenu = request.getParameter(Constants.MENU_SELECTED);
		if(strMenu != null) {
			request.setAttribute(Constants.MENU_SELECTED ,strMenu);
			//Logger.out.debug(Constants.MENU_SELECTED + " " +strMenu +" set successfully");
		}
    	return mapping.findForward(Constants.SUCCESS);
	}

    /**
     * It will be called when action will be placed like secure action. 
     */
    protected ActionForward executeSecureAction(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	SpecimenArrayTypeForm specimenArrayTypeForm = (SpecimenArrayTypeForm) actionForm;
    	
        String operation = request.getParameter(Constants.OPERATION);
        request.setAttribute(Constants.OPERATION, operation);
        
        //Setting the specimen class list
        List specimenClassList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_SPECIMEN_CLASS,null);
    	request.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);
    	
    	//Setting the specimen type list
    	List specimenTypeList = CDEManager.getCDEManager().getPermissibleValueList(Constants.CDE_NAME_SPECIMEN_TYPE,null);
    	request.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
    	
    	Map specimen_Type_Class_Map = getSpecimenClassAndType();
    	request.setAttribute(Constants.SPECIMEN_CLASS_LIST, (List) specimen_Type_Class_Map.get(specimenClassKey));
    	request.setAttribute(Constants.SPECIMEN_TYPE_MAP, (Map) specimen_Type_Class_Map.get(specimenTypeKey));
    	
	  	if(operation.equals(Constants.ADD))
	  	{
	  		specimenArrayTypeForm.setOneDimensionCapacity(0);
	  		specimenArrayTypeForm.setTwoDimensionCapacity(0);
	  	}
	  	
/*		String strMenu = request.getParameter(Constants.MENU_SELECTED);
		if(strMenu != null) {
			request.setAttribute(Constants.MENU_SELECTED ,strMenu);
			Logger.out.debug(Constants.MENU_SELECTED + " " +strMenu +" set successfully");
		}
*/		
    	return mapping.findForward(Constants.SUCCESS);
    }
    
    /**
     * Returns the specimen class & type for each class.
     * @return map specimen class & type map
     */
    private Map getSpecimenClassAndType() {
		//get the Specimen class and type from the cde
    	CDE specimenClassCDE = CDEManager.getCDEManager().getCDE(Constants.CDE_NAME_SPECIMEN_CLASS);
    	Set setPV = specimenClassCDE.getPermissibleValues();
    	Iterator itr = setPV.iterator();
    
    	List specimenClassList =  new ArrayList();
    	Map subTypeMap = new HashMap();
    	
    	specimenClassList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
    	Object pvObject = null;
    	PermissibleValue pv = null;
    	String pvValue = null;
    	List specimenTypeList = null;
    	while(itr.hasNext())
    	{
    		pvObject = itr.next();
    		pv = (PermissibleValue)pvObject;
    		pvValue = pv.getValue();
    		Logger.out.debug(pvValue);
    		specimenClassList.add(new NameValueBean( pvValue,pvValue));
    		specimenTypeList = getSpecimenTypeList(pv);
        	subTypeMap.put(pv.getValue(),specimenTypeList);
    	} // class and values set
    	
    	Map specimenClass_TypeMap = new HashMap();
    	specimenClass_TypeMap.put(specimenClassKey,specimenClassList);
    	specimenClass_TypeMap.put(specimenTypeKey,subTypeMap);
    	return specimenClass_TypeMap;
    }
    
    /**
     * returns the specimen type list for specific specimen class
     * @param specimenClassPV specimen class permissible value
     * @return list of specimen type for specimen class
     */
    private List getSpecimenTypeList(PermissibleValue specimenClassPV) {
    	List specimenTypeList = new ArrayList();
		Set subPVList = specimenClassPV.getSubPermissibleValues();
		Logger.out.debug("subPVList "+subPVList);
    	Iterator subPVItr = subPVList.iterator();
    	specimenTypeList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
    	Object subPVObj = null;
    	PermissibleValue subPV = null;
    	String subPVValue = null;
    	while(subPVItr.hasNext())
    	{
    		subPVObj = subPVItr.next();
    		subPV = (PermissibleValue)subPVObj;
    		// set specimen type
    		subPVValue = subPV.getValue(); 
    		Logger.out.debug("\t\t"+subPVValue);
    		specimenTypeList.add(new NameValueBean( subPVValue,subPVValue));  
    	}
    	return specimenTypeList;
    }
	
}
