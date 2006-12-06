/*
 * Created on Nov 9, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package edu.wustl.catissuecore.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import edu.wustl.catissuecore.actionForm.MultipleSpecimenStorageLocationForm;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.StorageContainerBizLogic;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.action.BaseAction;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.bizlogic.IBizLogic;
import edu.wustl.common.factory.AbstractBizLogicFactory;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.logger.Logger;

/**
 * @author mandar_deshmukh
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MultipleSpecimenSubmitAction extends BaseAction
{

	private int specimenCounter = 1;

	/**
	 * 
	 */
	public MultipleSpecimenSubmitAction()
	{
		//super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.wustl.common.action.BaseAction#executeAction(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
			
	{
		Logger.out.debug("Inside : MultipleSpecimenSubmitAction");
		MultipleSpecimenStorageLocationForm aForm = (MultipleSpecimenStorageLocationForm) form;
		Logger.out.debug("\naForm.getSpecimenOnUIMap():\n---------------\n" + aForm.getSpecimenOnUIMap() + "\n-----------\n");
		String target = Constants.FAILURE;
		//		if(dataValidate())
		//		{
		specimenCounter = 1;
		Map specimenMap = null;
		try
		{
			specimenMap = setDataInSpecimens(aForm, request);
		}
		catch (DAOException e)
		{
			
			ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);
			if (errors == null || errors.size() == 0)
			{
				errors = new ActionErrors();
			}
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.item.format",ApplicationProperties.getValue("specimen.positionInStorageContainer")));
			saveErrors(request,errors);
			e.printStackTrace();
			return mapping.findForward(target);
		}
		try
		{
			List specimenList = insertSpecimens(request, specimenMap);
			request.setAttribute(Constants.MULTIPLE_SPECIMEN_SUBMIT_SUCCESSFUL, Constants.MULTIPLE_SPECIMEN_SUBMIT_SUCCESSFUL);
			target = Constants.SUCCESS;
			// ----------------- report page
			Collection specimenCollection = (Collection) request.getSession().getAttribute(Constants.SAVED_SPECIMEN_COLLECTION);
			request.getSession().setAttribute(Constants.SAVED_SPECIMEN_COLLECTION, null);

			//to display all inserted specimens Mandar: 16Nov06 
			//				request.setAttribute(Constants.SAVED_SPECIMEN_COLLECTION, specimenCollection);
			request.setAttribute(Constants.SAVED_SPECIMEN_COLLECTION, specimenList);

			ActionMessages msgs = new ActionMessages();
			msgs.add("success", new ActionMessage("multipleSpecimen.add.success", String.valueOf(specimenList.size())));
			saveMessages(request, msgs);

		}
		catch (Exception excp)
		{
			Logger.out.error(excp);
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("empty.message", excp.getMessage()));
			saveErrors(request, errors);
			target = Constants.FAILURE;
		}
		//		}

		return mapping.findForward(target);
	}

	private boolean dataValidate()
	{
		return true;
	}

	private Map setDataInSpecimens(MultipleSpecimenStorageLocationForm aForm, HttpServletRequest request) throws DAOException
	{
		Map specimenOnUIMap = aForm.getSpecimenOnUIMap();
		List specimenOrderList = (List) request.getSession().getAttribute(Constants.MULTIPLE_SPECIMEN_SPECIMEN_ORDER_LIST);
		Map originalSpecimenMap = (HashMap) request.getSession().getAttribute(Constants.SPECIMEN_MAP_KEY);

		//		printMap(originalSpecimenMap);		
		if (specimenOrderList != null && !specimenOrderList.isEmpty())
		{
			for (int count = 0; count < specimenOrderList.size(); count++)
			{
				Specimen specimenFromList = (Specimen) specimenOrderList.get(count);

				Iterator specimenKeys = originalSpecimenMap.keySet().iterator();
				int cnt = 1;
				while (specimenKeys.hasNext())
				{
					Specimen originalSpecimen = (Specimen) specimenKeys.next();

					if (originalSpecimen.equals(specimenFromList))
					{
						String keyPrefix = "Specimen:" + (count + 1) + "_";
						String labelKey = keyPrefix + "Label";
						String barKey = keyPrefix + "Barcode";
						String storageContainerKey = keyPrefix + "StorageContainer";
						String positionOneKey = keyPrefix + "PositionOne";
						String positionTwoKey = keyPrefix + "PositionTwo";
						String virtuallyLocatedKey = keyPrefix + "virtuallyLocated";
						String deriveCountKey = keyPrefix + "DeriveCount";

						setSpecimenValues(specimenOnUIMap, originalSpecimen, labelKey, barKey, storageContainerKey, positionOneKey, positionTwoKey,
								virtuallyLocatedKey);

						String deriveCount = (String) specimenOnUIMap.get(deriveCountKey);
						int deriveCountValue = Integer.parseInt(deriveCount);
						if (deriveCountValue > 0)
						{
							setDerivedSpecimens(originalSpecimenMap, originalSpecimen, keyPrefix, specimenOnUIMap);
						}
					}
				}
			}
			Logger.out.debug(">>>>>>>>>>>>>>>>>>>> Specimen Data Set >>>>>>>>>>>>>>");
		}
		Logger.out.debug("Updated Map : ");
		//		printMap(originalSpecimenMap);
		return originalSpecimenMap;

	}

	private void setDerivedSpecimens(Map originalSpecimenMap, Specimen mainSpecimen, String parentKey, Map specimenOnUIMap) throws DAOException
	{
		List derivedList = (List) originalSpecimenMap.get(mainSpecimen);
		if (derivedList != null && !derivedList.isEmpty())
		{
			for (int cnt = 1; cnt <= derivedList.size(); cnt++)
			{
				String derivedPrefix = parentKey + "DerivedSpecimen:";
				Specimen derivedSpecimen = (Specimen) derivedList.get(cnt - 1);
				String labelKey = derivedPrefix + cnt + "_Label";
				String barKey = derivedPrefix + cnt + "_Barcode";
				String storageContainerKey = derivedPrefix + cnt + "_StorageContainer";
				String positionOneKey = derivedPrefix + cnt + "_PositionOne";
				String positionTwoKey = derivedPrefix + cnt + "_PositionTwo";
				String virtuallyLocatedKey = derivedPrefix + cnt + "_virtuallyLocated";

				setSpecimenValues(specimenOnUIMap, derivedSpecimen, labelKey, barKey, storageContainerKey, positionOneKey, positionTwoKey,
						virtuallyLocatedKey);

			}
		}
	}

	private void setSpecimenValues(Map specimenOnUIMap, Specimen specimen, String labelKey, String barKey, String storageContainerKey,
			String positionOneKey, String positionTwoKey, String virtuallyLocatedKey) throws DAOException
	{
		//fetch from UI map
		String label = (String) specimenOnUIMap.get(labelKey);
		String barcode = (String) specimenOnUIMap.get(barKey);
		String virtuallyLocated = (String) specimenOnUIMap.get(virtuallyLocatedKey);
		String radioButonKey = "radio_" + specimenCounter;

		//setting values in main specimen
		specimen.setLabel(label);
		specimen.setBarcode(barcode);

		//get the container values based on user selection from dropdown or map
		if (specimenOnUIMap.get(radioButonKey).equals("1"))
		{
			specimen.setStorageContainer(null);
			specimen.setPositionDimensionOne(null);
			specimen.setPositionDimensionTwo(null);
		}
		if (specimenOnUIMap.get(radioButonKey).equals("2"))
		{
			String containerId = (String) specimenOnUIMap.get(storageContainerKey);
			String posDim1 = (String) specimenOnUIMap.get(positionOneKey);
			String posDim2 = (String) specimenOnUIMap.get(positionTwoKey);
			if(specimen.getStorageContainer() == null)
			{
				specimen.setStorageContainer(new StorageContainer());
			}
			specimen.getStorageContainer().setId(new Long(containerId));
			specimen.setPositionDimensionOne(new Integer(posDim1));
         	specimen.setPositionDimensionTwo(new Integer(posDim2));
		}
		else if (specimenOnUIMap.get(radioButonKey).equals("3"))
		{
			String containerName = (String) specimenOnUIMap.get("Specimen:" + specimenCounter + "_StorageContainer_name" + "_fromMap");

			String sourceObjectName = StorageContainer.class.getName();
			String[] selectColumnName = {"id"};
			String[] whereColumnName = {"name"};
			String[] whereColumnCondition = {"="};
			Object[] whereColumnValue = {containerName};
			String joinCondition = null;

			StorageContainerBizLogic bizLogic = (StorageContainerBizLogic) BizLogicFactory.getInstance().getBizLogic(
					Constants.STORAGE_CONTAINER_FORM_ID);

			List list = bizLogic.retrieve(sourceObjectName, selectColumnName, whereColumnName, whereColumnCondition, whereColumnValue, joinCondition);
			Long containerId = null;
			if (!list.isEmpty())
			{
				containerId = (Long) list.get(0);
			}
			else
			{
				String message = ApplicationProperties.getValue("specimen.storageContainer");
				throw new DAOException(ApplicationProperties.getValue("errors.invalid", message));
			}
			String posDim1 = (String) specimenOnUIMap.get("Specimen:" + specimenCounter + "_positionDimensionOne" + "_fromMap");
			String posDim2 = (String) specimenOnUIMap.get("Specimen:" + specimenCounter + "_positionDimensionTwo" + "_fromMap");
			specimen.setStorageContainer(new StorageContainer());
			if(specimen.getStorageContainer() == null)
			{
				specimen.setStorageContainer(new StorageContainer());
			}
			specimen.getStorageContainer().setId(containerId);
			specimen.setPositionDimensionOne(new Integer(posDim1));
			specimen.setPositionDimensionTwo(new Integer(posDim2));
			
		}
		specimenCounter++;
	}

	/**
	 * This method saves collection of specimens to the database.
	 *  TODO Error handling. 
	 * @param request
	 * @param specimenCollection
	 */
	private List insertSpecimens(HttpServletRequest request, Map specimenMap) throws Exception
	{
		IBizLogic bizLogic;

		bizLogic = AbstractBizLogicFactory.getBizLogic(ApplicationProperties.getValue("app.bizLogicFactory"), "getBizLogic",
				Constants.NEW_SPECIMEN_FORM_ID);
		SessionDataBean sessionBean = (SessionDataBean) request.getSession().getAttribute(Constants.SESSION_DATA);

		bizLogic.insert(specimenMap, sessionBean, Constants.HIBERNATE_DAO);

		//specimen list to display 
		List specimenList = createList(specimenMap);
		return specimenList;
	}

	/*
	 * This method creates a list of specimen that are inserted.
	 * This list will be used to display specimen labels on the report's page.
	 */
	private List createList(Map specimenMap)
	{
		List specimenList = new ArrayList();

		Iterator specimenIterator = specimenMap.keySet().iterator();
		while (specimenIterator.hasNext())
		{
			Specimen specimen = (Specimen) specimenIterator.next();
			specimenList.add(specimen);
			List derivedSpecimens = (List) specimenMap.get(specimen);

			if (derivedSpecimens != null)
			{
				for (int i = 0; i < derivedSpecimens.size(); i++)
				{

					Specimen derivedSpecimen = (Specimen) derivedSpecimens.get(i);
					specimenList.add(derivedSpecimen);
				}
			}
		}
		return specimenList;
	}
}

