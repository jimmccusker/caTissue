/*
 * Created on May 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.wustl.catissuecore.dao;

import java.util.List;

import net.sf.hibernate.HibernateException;
import edu.wustl.catissuecore.domain.ActivityStatus;
import edu.wustl.catissuecore.domain.ReportedProblem;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.dbManager.DAOException;


/**
 * @author gautam_shetty
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportedProblemBizLogic extends AbstractBizLogic
{
    
    /* (non-Javadoc)
     * @see edu.wustl.catissuecore.dao.HibernateDAO#add(java.lang.Object)
     */
    public void insert(Object obj) throws HibernateException, DAOException
    {
        ReportedProblem reportedProblem = (ReportedProblem)obj; 
        ActivityStatus activityStatus = new ActivityStatus();
        AbstractDAO dao = DAOFactory.getDAO(Constants.HIBERNATE_DAO);
        dao.openSession();
        
        List list = dao.retrieve(ActivityStatus.class.getName(),"status",reportedProblem.getActivityStatus().getStatus());
	    if (list.size() != 0)
	    {
	        activityStatus = (ActivityStatus) list.get(0);
	    }
	    
	    reportedProblem.setActivityStatus(activityStatus);
        dao.insert(obj);
        
        dao.closeSession();
    }

}
