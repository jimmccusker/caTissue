/**
 * <p>Title: CommonAddEditAction Class>
 * <p>Description:	This Class is used to Add/Edit the data in the database.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 * Created on Apr 21, 2005
 */

package edu.wustl.catissuecore.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.actionForm.AbstractActionForm;
import edu.wustl.catissuecore.dao.AbstractBizLogic;
import edu.wustl.catissuecore.dao.BizLogicFactory;
import edu.wustl.catissuecore.dataModel.DomainObjectFactory;
import edu.wustl.catissuecore.domain.AbstractDomainObject;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.logger.Logger;

/**
 * This Class is used to Add/Edit data in the database.
 * @author gautam_shetty
 */
public class CommonAddEditAction extends Action
{

    /**
     * Overrides the execute method of Action class.
     * Adds / Updates the data in the database.
     * */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String target = null;

        try
        {
            AbstractActionForm abstractForm = (AbstractActionForm) form;
            AbstractBizLogic dao = BizLogicFactory.getDAO(abstractForm.getFormId());

            if (abstractForm.isAddOperation())
            {
                //If operation is add, add the data in the database.
                AbstractDomainObject abstractDomain = DomainObjectFactory.getDomainObject(
                        abstractForm.getFormId(), abstractForm);
                dao.insert(abstractDomain);
                target = new String(Constants.SUCCESS);
            }
            else
            {
                //If operation is edit, update the data in the database.
            	String objName = AbstractDomainObject.getDomainObjectName(abstractForm.getFormId());
            	
                List list = dao.retrieve( objName, Constants.IDENTIFIER, 
										  new Long(abstractForm.getIdentifier()));

                if (list.size() != 0)
                {
                	AbstractDomainObject abstractDomain = (AbstractDomainObject) list.get(0);
                    abstractDomain.setAllValues(abstractForm);
                    dao.update(abstractDomain);
                    target = new String(Constants.SUCCESS);
                }
                else
                {
                    target = new String(Constants.FAILURE);
                }
            }
            
            //Status message key.
            String statusMessageKey = String.valueOf(abstractForm.getFormId()+
					"."+String.valueOf(abstractForm.isAddOperation()));

            request.setAttribute(Constants.STATUS_MESSAGE_KEY,statusMessageKey);
        }
        catch (DAOException excp)
        {
            target = new String(Constants.FAILURE);
            Logger.out.error(excp.getMessage(), excp);
        }
        catch (HibernateException hibExp)
        {
            target = new String(Constants.FAILURE);
            Logger.out.error(hibExp.getMessage(),hibExp);
        }
        return (mapping.findForward(target));
    }
}