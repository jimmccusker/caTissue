/**
 * <p>Title: OrderingSystemUtil Class>
 * <p>Description:	Utility class for Ordering System</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ashish Gupta
 * @version 1.00
 * Created on Oct 09,2006
 */
package edu.wustl.catissuecore.util;

import java.util.ArrayList;
import java.util.List;

import edu.wustl.catissuecore.bean.RequestViewBean;
import edu.wustl.catissuecore.domain.OrderDetails;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.beans.NameValueBean;



public class OrderingSystemUtil
{

	/**
	 * @param initialStatus The initial status from Db.
	 * @return possibleStatusList
	 */
	public static List getPossibleStatusList(String initialStatus)
	{
		List possibleStatusList = new ArrayList();
		if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_NEW))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_NEW,Constants.ORDER_REQUEST_STATUS_NEW));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_PROTOCOL_REVIEW,Constants.ORDER_REQUEST_STATUS_PENDING_PROTOCOL_REVIEW));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION,Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION,Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST,Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE,Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE,Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED,Constants.ORDER_REQUEST_STATUS_DISTRIBUTED));
			
		}
		if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_PENDING_PROTOCOL_REVIEW))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_PROTOCOL_REVIEW,Constants.ORDER_REQUEST_STATUS_PENDING_PROTOCOL_REVIEW));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION,Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION,Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST,Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE,Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE,Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED,Constants.ORDER_REQUEST_STATUS_DISTRIBUTED));
			
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION,Constants.ORDER_REQUEST_STATUS_PENDING_SPECIMEN_PREPARATION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION,Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST,Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE,Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE,Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED,Constants.ORDER_REQUEST_STATUS_DISTRIBUTED));
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION,Constants.ORDER_REQUEST_STATUS_PENDING_FOR_DISTRIBUTION));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST,Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE,Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE,Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE));
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED,Constants.ORDER_REQUEST_STATUS_DISTRIBUTED));
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED,Constants.ORDER_REQUEST_STATUS_DISTRIBUTED));
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST,Constants.ORDER_REQUEST_STATUS_REJECTED_INAPPROPRIATE_REQUEST));
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE,Constants.ORDER_REQUEST_STATUS_REJECTED_SPECIMEN_UNAVAILABLE));
		}
		else if(initialStatus.trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE))
		{
			possibleStatusList.add(new NameValueBean(Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE,Constants.ORDER_REQUEST_STATUS_REJECTED_UNABLE_TO_CREATE));
		}
		return possibleStatusList;
	}
	/**
	 * @param order OrderDetails
	 * @return RequestViewBean object.
	 */
	public static RequestViewBean getRequestViewBeanToDisplay(OrderDetails order)
	{
		RequestViewBean requestViewBean = new RequestViewBean();
		requestViewBean.setDistributionProtocol(order.getDistributionProtocol().getTitle());
		requestViewBean.setDistributionProtocolId(order.getDistributionProtocol().getId().toString());
		requestViewBean.setOrderName(order.getName());
		requestViewBean.setRequestedBy(order.getDistributionProtocol().getPrincipalInvestigator().getLastName()+","+order.getDistributionProtocol().getPrincipalInvestigator().getFirstName());
		requestViewBean.setRequestedDate(Utility.parseDateToString(order.getRequestedDate(), Constants.DATE_PATTERN_MM_DD_YYYY));
		requestViewBean.setEmail(order.getDistributionProtocol().getPrincipalInvestigator().getEmailAddress());
		return requestViewBean;
	}
}
