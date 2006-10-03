/**
 * <p>Title: InstitutionBizLogic Class>
 * <p>Description:	InstitutionBizLogic </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ashish Gupta
 * @version 1.00
 * Created on Sep 19, 2006
 */

package edu.wustl.catissuecore.bizlogic;

import edu.wustl.catissuecore.domain.Institution;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.dao.DAO;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;

public class InstitutionBizLogic extends DefaultBizLogic
{

	protected boolean validate(Object obj, DAO dao, String operation) throws DAOException
	{
		Institution institution = (Institution) obj;
		if (institution == null)
			throw new DAOException("domain.object.null.err.msg", new String[]{"Institution"});

		Validator validate = new Validator();
		if (validate.isEmpty(institution.getName()))
		{
			String message = ApplicationProperties.getValue("Institution.name");
			throw new DAOException("errors.item.required", new String[]{message});
		}
		return true;
	}

}
