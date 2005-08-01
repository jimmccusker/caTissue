/**
 * <p>Title: UserDetailsAction Class>
 * <p>Description:	UserDetailsAction is used to display details of user 
 * whose membership is to be approved/Rejected.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 * Created on Apr 29, 2005
 */
package edu.wustl.catissuecore.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.AbstractActionForm;
import edu.wustl.catissuecore.domain.AbstractDomainObject;
import edu.wustl.catissuecore.util.global.Constants;

/**
 * UserDetailsAction is used to display details of user whose membership is to be approved/Rejected.
 * @author gautam_shetty
 */
public class DomainObjectDetailsAction extends Action
{
    
    /**
     * Overrides the execute method in Action.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        HttpSession session = request.getSession();
        List list = (List)session.getAttribute(Constants.ORIGINAL_DOMAIN_OBJECT_LIST);
        
        long identifier = Long.parseLong(request.getParameter(Constants.IDENTIFIER));
        Iterator iterator = list.iterator();
        
        AbstractDomainObject currentDomainObject = null;
        Long prevIdentifier = null,nextIdentifier = null;
        
        while (iterator.hasNext())
        {
            currentDomainObject = (AbstractDomainObject)iterator.next();
            if (identifier == currentDomainObject.getSystemIdentifier().longValue())
            {
                break;
            }
            prevIdentifier = currentDomainObject.getSystemIdentifier();
        }
        
        if (iterator.hasNext())
        {
            AbstractDomainObject nextDomainObject = (AbstractDomainObject)iterator.next();
            nextIdentifier = nextDomainObject.getSystemIdentifier();
        }
        
        AbstractActionForm abstractActionForm = (AbstractActionForm)form;
        abstractActionForm.setAllValues(currentDomainObject);
        
        request.setAttribute(Constants.PREVIOUS_PAGE,prevIdentifier);
        request.setAttribute(Constants.NEXT_PAGE,nextIdentifier);
        
        return mapping.findForward(Constants.SUCCESS);
    }
}
