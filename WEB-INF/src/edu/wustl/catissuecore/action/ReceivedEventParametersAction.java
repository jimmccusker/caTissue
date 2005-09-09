/**
 * <p>Title: CollectionEventParametersAction Class>
 * <p>Description:	This class initializes the fields in the CollectionEventParameters Add/Edit webpage.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on Aug 04, 2005
 */
package edu.wustl.catissuecore.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.cde.CDEManager;


/**
 * @author mandar_deshmukh
 *
 * This class initializes the fields in the CollectionEventParameters Add/Edit webpage.
 */
public class ReceivedEventParametersAction extends SpecimenEventParametersAction
{
	protected void setRequestParameters(HttpServletRequest request)
	{
		super.setRequestParameters(request);
		
		List qualityList = CDEManager.getCDEManager().getList(Constants.CDE_NAME_RECEIVED_QUALITY);
    	request.setAttribute(Constants.RECEIVED_QUALITY_LIST, qualityList);
	}
}
