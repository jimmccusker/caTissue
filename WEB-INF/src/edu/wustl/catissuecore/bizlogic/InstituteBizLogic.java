/**
 * <p>Title: InstituteHDAO Class>
 * <p>Description:	This Class is used to Add institute information into the database using Hibernate.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 * Created on Apr 13, 2005
 */

package edu.wustl.catissuecore.bizlogic;

import edu.wustl.catissuecore.dao.AbstractDAO;
import edu.wustl.catissuecore.dao.DAOFactory;
import edu.wustl.catissuecore.domain.Institution;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.dbManager.DAOException;

/**
 * This Class is used to Add institute information into the database using Hibernate.
 * @author kapil_kaveeshwar
 */
public class InstituteBizLogic extends DefaultBizLogic
{
	/**
	 * Override the add method of HibernateDAO class
	 * */
	public void insert(Object obj) throws DAOException
	{
	    Institution institute = (Institution)obj;
        AbstractDAO dao = DAOFactory.getDAO(Constants.HIBERNATE_DAO);
		dao.openSession();
			
		dao.insert(institute);
		    
        dao.closeSession();
	}
}