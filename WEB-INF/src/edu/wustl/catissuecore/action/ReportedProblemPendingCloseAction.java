/**
 * <p>Title: ReportedProblemPendingCloseAction Class>
 * <p>Description:  ReportedProblemPendingCloseAction Class is used to close and keep pending a reported problem.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 */

package edu.wustl.catissuecore.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.DomainObjectListForm;
import edu.wustl.catissuecore.dao.AbstractBizLogic;
import edu.wustl.catissuecore.dao.BizLogicFactory;
import edu.wustl.catissuecore.domain.AbstractDomainObject;
import edu.wustl.catissuecore.domain.ActivityStatus;
import edu.wustl.catissuecore.domain.ReportedProblem;
import edu.wustl.catissuecore.util.global.Constants;


/**
 * ReportedProblemPendingCloseAction Class is used to close and keep pending a reported problem.
 * @author gautam_shetty
 */
public class ReportedProblemPendingCloseAction extends Action
{
    
    /**
     * Overrides the execute method in Action.
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        DomainObjectListForm domainObjectListForm = (DomainObjectListForm)form;
        
        //Gets the collection of problems to be closed/kept pending.
        Collection col = domainObjectListForm.getAllValues();
        String target = null;
        
        Iterator iterator = col.iterator();

        AbstractBizLogic dao = BizLogicFactory.getDAO(Constants.REPORTEDPROBLEM_FORM_ID);

        List list = null;

        while (iterator.hasNext())
        {
            String identifier = (String) iterator.next();
            String objName = AbstractDomainObject.getDomainObjectName(Constants.REPORTEDPROBLEM_FORM_ID);
            list = dao.retrieve(objName, Constants.IDENTIFIER, new Long(identifier));

            if (list.size() != 0)
            {
                ReportedProblem reportedProblem = (ReportedProblem) list.get(0);

                //Sets the comments given by the resolver.
                if (domainObjectListForm.getComments() != null)
                {
                    reportedProblem.setComments(domainObjectListForm.getComments());
                }

                if (domainObjectListForm.getOperation().equals(Constants.ACTIVITY_STATUS_CLOSED))
                {

                    //Retrieve the Activity Status for closed status.
                    list = dao.retrieve(AbstractDomainObject.getDomainObjectName(Constants.ACTIVITY_STATUS_FORM_ID),
                                    "status", Constants.ACTIVITY_STATUS_CLOSED);

                }
                else
                {
                    //Retrieve the Activity Status for pending status.
                    list = dao.retrieve(AbstractDomainObject.getDomainObjectName(Constants.ACTIVITY_STATUS_FORM_ID),
                                    "status", Constants.ACTIVITY_STATUS_PENDING);
                }

                //Sets the activity status as pending.
                ActivityStatus activityStatus = (ActivityStatus) list.get(0);
                reportedProblem.setActivityStatus(activityStatus);

                //Updates reported problem's information in the database.
                dao.update(reportedProblem);
                
                target = new String(Constants.SUCCESS);
            }
        }
        
        return mapping.findForward(target);
    }
}
