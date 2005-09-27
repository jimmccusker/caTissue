/**
 * <p>Title: AssignPrivilegePageAction Class>
 * <p>Description:	This class initializes the fields of AssignPrivileges.jsp Page</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Sep 20, 2005
 */

package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.AssignPrivilegesForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.UserBizLogic;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.security.SecurityManager;
import edu.wustl.common.util.logger.Logger;
import gov.nih.nci.security.authorization.domainobjects.Role;

public class AssignPrivilegePageAction extends BaseAction
{
    /**
     * Overrides the execute method of Action class.
     * Initializes the various fields in AssignPrivileges.jsp Page.
     * */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        AssignPrivilegesForm privilegesForm = (AssignPrivilegesForm)form;
        
        //Constants that are required to populate lists in AssignPrivileges.jsp
        String [] assignOperation = {"Allow","Disallow"};
        //String [] privileges = {Constants.ANY,"Add","Edit","Read","Use"};
        
        Vector objectTypes = new Vector();
        objectTypes.add(new NameValueBean(Constants.ANY,Constants.ANY));
        objectTypes.add(new NameValueBean("Participant","edu.wustl.catissuecore.domain.Participant"));
        objectTypes.add(new NameValueBean("Collection Protocol","edu.wustl.catissuecore.domain.CollectionProtocol"));
        objectTypes.add(new NameValueBean("Distribution Protocol","edu.wustl.catissuecore.domain.DistributionProtocol"));
        objectTypes.add(new NameValueBean("Specimen Collection","edu.wustl.catissuecore.domain.SpecimenCollectionGroup"));
        objectTypes.add(new NameValueBean("Specimen","edu.wustl.catissuecore.domain.Specimen"));
        objectTypes.add(new NameValueBean("Specimen Events","edu.wustl.catissuecore.domain.SpecimenEventParameters"));
        objectTypes.add(new NameValueBean("Storage","edu.wustl.catissuecore.domain.StorageContainer"));
        objectTypes.add(new NameValueBean("Site","edu.wustl.catissuecore.domain.Site"));
        objectTypes.add(new NameValueBean("Distribution","edu.wustl.catissuecore.domain.Distribution"));
        //objectTypes.add(new NameValueBean("User","User"));
        
        //String [] recordIds = {Constants.ANY};
        
        String [] attributes = {Constants.ANY,"De-Id"};
        
        try
		{
        	//SETTING THE USER LIST
       		UserBizLogic userBizLogic = (UserBizLogic)BizLogicFactory.getBizLogic(Constants.USER_FORM_ID);
        	Collection userCollection =  userBizLogic.getUsers(Constants.ACTIVITY_STATUS_ACTIVE);
        	
        	if(userCollection != null && userCollection.size() !=0)
        	{
        		((Vector) userCollection).remove(0);//Removing SELECT Option
        		
        		//Extracting RoleNames & their Ids
        		Vector roles = SecurityManager.getInstance(AssignPrivilegePageAction.class).getRoles();
        		
        		if(roles != null && roles.size() != 0)
        		{
        			for(int i=0;i<roles.size();i++)
        			{
        				Role role = (Role)roles.get(i);
        				String id = "Role_" + role.getId();
        				((Vector) userCollection).add(i,new NameValueBean(role.getName(),id));
        			}
        		}
        		        		
        		request.setAttribute(Constants.GROUPS,userCollection);
        	}
        	
        	request.setAttribute(Constants.ASSIGN,assignOperation);        
            
            SessionDataBean bean = getSessionData(request);
            
            //SETTING THE PRIVILEGES LIST
            Vector privileges = SecurityManager.getInstance(AssignPrivilegePageAction.class).getPrivilegesForAssignPrivilege(bean.getUserName());		
            request.setAttribute(Constants.PRIVILEGES,privileges);
            
            //SETTING THE OBJECT TYPES LIST            
            request.setAttribute(Constants.OBJECT_TYPES,objectTypes);
            
            //SETTING THE RECORD IDS AS PER THE OBJECT TYPES
            String [] privilegeName = {privilegesForm.getPrivilege()};
            String [] objects = {privilegesForm.getObjectType()};
            
            Set recordIds = SecurityManager.getInstance(AssignPrivilegePageAction.class).getObjectsForAssignPrivilege(String.valueOf(bean.getUserId()),objects,privilegeName);
        	            
            request.setAttribute(Constants.RECORD_IDS,recordIds);
        	
            request.setAttribute(Constants.ATTRIBUTES,attributes);
		}
        catch(Exception e)
		{
        	System.out.println(e);
        	Logger.out.error(e.getMessage(),e);
		}
        
        return mapping.findForward((String)request.getParameter(Constants.PAGEOF));
    }
}