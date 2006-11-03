/**
 * <p>Title: AliquotAction Class>
 * <p>Description:	AliquotAction initializes all the fields of the page, Aliquots.jsp.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on May 12, 2006
 */

package edu.wustl.catissuecore.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

import edu.wustl.catissuecore.actionForm.AliquotForm;
import edu.wustl.catissuecore.bizlogic.AliquotBizLogic;
import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.StorageContainerBizLogic;
import edu.wustl.catissuecore.domain.MolecularSpecimen;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.util.global.Utility;
import edu.wustl.common.action.SecureAction;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.bizlogic.IBizLogic;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Validator;

/**
 * AliquotAction initializes all the fields of the page, Aliquots.jsp.
 * @author aniruddha_phadnis
 */
public class AliquotAction extends SecureAction
{

	/**
	 * Overrides the execute method of Action class.
	 */
	public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		AliquotForm aliquotForm = (AliquotForm) form;
		//boolean to indicate whether the suitable containers to be shown in dropdown 
		//is exceeding the max limit.
		String exceedingMaxLimit = "false";
		String specimenLabel = aliquotForm.getSpecimenLabel();

		//Extracting the values of the operation & pageOf parameters.
		String operation = request.getParameter(Constants.OPERATION);
		String pageOf = request.getParameter(Constants.PAGEOF);
		StorageContainerBizLogic bizLogic = (StorageContainerBizLogic) BizLogicFactory.getInstance().getBizLogic(Constants.STORAGE_CONTAINER_FORM_ID);

		/**
		 *  Following code ensures that 
		 *  1. Label/Barcode, Aliquot Count, Quantity per Aliquot submitted on click of Submit button 
		 *  and that on click of create button is same. In case they are different show the error 
		 *  'You have to resubmit the page in case you edit any of Parent Specimen Label/Barcode, Aliquot Count, Quantity per Aliquot.'
		 *  2. If checkbox 'Create all Aliquots in the Same Container' is checked(true), populate 
		 *  Aliquot form with only those containers which has sufficient empty space to hold all the aliquots.
		 *  3. If checkbox 'Create all Aliquots in the Same Container' is checked(true), on click of create, 
		 *  check whether all the specimens are in the same container. If they are not in the same container,
		 *  show error 'Create failed because you can not choose different containers to store the Aliquots' 
		 */
		// --------- Start Bug 2309--------------
		/**
		 * Store the values of label/barcode,aliquot count, quantity per aliquot on click of submit 
		 */
		if (aliquotForm.getButtonClicked().equalsIgnoreCase("submit"))
		{
			Map tempAliquotMap = new HashMap();
			if (aliquotForm.getCheckedButton().equals("1"))
			{
				tempAliquotMap.put("label", aliquotForm.getSpecimenLabel());
			}
			else
			{
				tempAliquotMap.put("barcode", aliquotForm.getBarcode());
			}
			tempAliquotMap.put("aliquotcount", aliquotForm.getNoOfAliquots());
			tempAliquotMap.put("quantityperaliquot", aliquotForm.getQuantityPerAliquot());
			aliquotForm.setAliqoutInSameContainer(false);
			request.getSession().setAttribute("tempAliquotMap", tempAliquotMap);
		}
		else if (aliquotForm.getButtonClicked().equalsIgnoreCase("create") || aliquotForm.getButtonClicked().equalsIgnoreCase("checkbox"))
		{
			// arePropertiesChanged is used to identify if any of  label/barcode,aliquot count, quantity per aliquot are changed
			boolean arePropertiesChanged = false;
			Map tempAliquotMap = (HashMap) request.getSession().getAttribute("tempAliquotMap");
			String label = (String) tempAliquotMap.get("label");
			String barcode = (String) tempAliquotMap.get("barcode");
			if (aliquotForm.getCheckedButton().equals("1"))
			{
				if (label == null || !label.trim().equalsIgnoreCase(aliquotForm.getSpecimenLabel().trim()))
				{
					arePropertiesChanged = true;
				}
			}
			else
			{
				if (barcode == null || !barcode.trim().equalsIgnoreCase(aliquotForm.getBarcode().trim()))
				{
					arePropertiesChanged = true;
				}

			}

			String aliquotcount = (String) tempAliquotMap.get("aliquotcount");
			if (!aliquotcount.trim().equalsIgnoreCase(aliquotForm.getNoOfAliquots().trim()))
			{
				arePropertiesChanged = true;
			}

			String quantityperaliquot = (String) tempAliquotMap.get("quantityperaliquot");
			if (!quantityperaliquot.trim().equalsIgnoreCase(aliquotForm.getQuantityPerAliquot().trim()))
			{
				arePropertiesChanged = true;
			}

			/** 
			 * areContainersDifferent checks whether user has selected diffrent containers when 
			 * 'Store all Containers aliquots in same container' is true
			 */
			boolean areContainersDifferent = false;

			if (aliquotForm.isAliqoutInSameContainer() && aliquotForm.getButtonClicked().equalsIgnoreCase("create"))
			{
				Map aliquotMap = aliquotForm.getAliquotMap();
				String specimenKey = "Specimen:";
				int noOfAliquots = Integer.parseInt(aliquotForm.getNoOfAliquots().trim());
				String tempContainerId = null;

				for (int i = 1; i <= noOfAliquots; i++)
				{
					String containerIdKey = specimenKey + i + "_StorageContainer_id";
					String containerId = (String) aliquotMap.get(containerIdKey);
					if (i == 1)
					{
						tempContainerId = containerId;
					}
					else
					{
						// check whether all container IDs are same
						if (!containerId.equals(tempContainerId))
						{
							areContainersDifferent = true;
						}
					}

				}
			}

			/**
			 *  Repopulate the form with available storage container locations in case
			 *  1. User has changed any of label/barcode, aliquot count, quantity per aliquote
			 *  2. User has selected different containers to store the aliquotes when 'Store all aliquots in same container..'
			 *     checkbox is checked(true)
			 *  3. User has changed state of checkbox from checked to unchecked or vice versa    
			 */

			if (arePropertiesChanged == true || areContainersDifferent == true || aliquotForm.getButtonClicked().equalsIgnoreCase("checkbox"))
			{

				aliquotForm.setNoOfAliquots(aliquotcount);
				aliquotForm.setSpecimenLabel(label);
				aliquotForm.setBarcode(barcode);
				aliquotForm.setQuantityPerAliquot(quantityperaliquot);
				Map containerMap = null;
				if (aliquotForm.isAliqoutInSameContainer())
				{

					containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm.getSpecimenClass(),
							Integer.parseInt(aliquotForm.getNoOfAliquots()),exceedingMaxLimit,true);
				}
				else
				{
					containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm.getSpecimenClass(),
							0,exceedingMaxLimit,true);
				}
				populateAliquotsStorageLocations(aliquotForm, containerMap);

				ActionErrors errors = getActionErrors(request);

				if (errors == null)
				{
					errors = new ActionErrors();
				}
				if (arePropertiesChanged == true)
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.aliquots.reSubmit"));
				}
				if (areContainersDifferent == true)
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.aliquots.sameStorageContainer"));
				}

				saveErrors(request, errors);
				request.setAttribute(Constants.EXCEEDS_MAX_LIMIT,exceedingMaxLimit);
				request.setAttribute(Constants.AVAILABLE_CONTAINER_MAP, containerMap);
				request.setAttribute(Constants.PAGEOF, Constants.PAGEOF_CREATE_ALIQUOT);
				return mapping.findForward(Constants.PAGEOF_CREATE_ALIQUOT);

			}

			/**
			 *  Forward to addEditAction in following cases
			 *  1. 'Store all aliquots in same container..' Checkbox is checked and user has selected the same container
			 *  2. 'Store all aliquots in same container..' Checkbox is unchecked
			 */
			if (areContainersDifferent == false)
			{
				Map aliquotMap = aliquotForm.getAliquotMap();
				Iterator keyIterator = aliquotMap.keySet().iterator();
				/**
				 * 	 Bug no. 560 If total quantity entered by user is greater than initial available quantity,
				 *   show the 'System can not distribute available quantity among the aliquots due to insufficient amount' error message  
				 * 
				 */

				double totalQuantity = 0;
				while (keyIterator.hasNext())
				{
					Validator validator = new Validator();
					String key = (String) keyIterator.next();

					if (key.endsWith("_quantity"))
					{
						String value = (String) aliquotMap.get(key);
						totalQuantity += Double.parseDouble(value);
					}

				}

				if (totalQuantity > Double.parseDouble(aliquotForm.getInitialAvailableQuantity()))
				{
					ActionErrors errors = getActionErrors(request);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.qtyInsufficient"));
					saveErrors(request, errors);
					Map containerMap = null;
					if (aliquotForm.isAliqoutInSameContainer())
					{
						containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm
								.getSpecimenClass(), Integer.parseInt(aliquotForm.getNoOfAliquots()),exceedingMaxLimit,true);
					}
					else
					{
						containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm
								.getSpecimenClass(), 0,exceedingMaxLimit,true);
					}

					populateAliquotsStorageLocations(aliquotForm, containerMap);
					request.setAttribute(Constants.EXCEEDS_MAX_LIMIT,exceedingMaxLimit);
					request.setAttribute(Constants.AVAILABLE_CONTAINER_MAP, containerMap);
					request.setAttribute(Constants.PAGEOF, Constants.PAGEOF_CREATE_ALIQUOT);
					return mapping.findForward(Constants.PAGEOF_CREATE_ALIQUOT);
				}
				aliquotForm.setButtonClicked("none");
				return mapping.findForward(Constants.COMMON_ADD_EDIT);
			}

		}
		// --------END Bug 2309----------		

		if (Constants.PAGEOF_ALIQUOT_SUMMARY.equals(pageOf))
		{
			Map map = (Map) request.getAttribute("forwardToHashMap");

			if (map != null)
			{
				aliquotForm.setSpecimenClass(Utility.toString(map.get(Constants.CDE_NAME_SPECIMEN_CLASS)));
				aliquotForm.setType(Utility.toString(map.get(Constants.CDE_NAME_SPECIMEN_TYPE)));
				aliquotForm.setTissueSite(Utility.toString(map.get(Constants.CDE_NAME_TISSUE_SITE)));
				aliquotForm.setTissueSide(Utility.toString(map.get(Constants.CDE_NAME_TISSUE_SIDE)));
				aliquotForm.setPathologicalStatus(Utility.toString(map.get(Constants.CDE_NAME_PATHOLOGICAL_STATUS)));
				aliquotForm.setAvailableQuantity(Utility.toString(map.get(Constants.SPECIMEN_TYPE_QUANTITY)));
				aliquotForm.setConcentration(Utility.toString(map.get("concentration")));
				
				aliquotForm.setAliquotMap(map);
			}

			ActionErrors errors = getActionErrors(request);

			if (errors == null || errors.size() == 0)
			{
				ActionMessages messages = new ActionMessages();
				messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("aliquots.success"));
				saveMessages(request, messages);
			}

			return mapping.findForward(pageOf);

		}

		// this code is required when we come from Specimen page
		if (specimenLabel == null || specimenLabel.trim().equals(""))
		{
			if (request.getAttribute(Constants.SYSTEM_LABEL) != null)
			{
				specimenLabel = String.valueOf(request.getAttribute(Constants.SYSTEM_LABEL));
			}
			else
			{
				specimenLabel = request.getParameter(Constants.SYSTEM_LABEL);
			}
		}
		// this code is used in case we come from Specimen page
		if (specimenLabel != null)
		{
			aliquotForm.setSpecimenLabel(specimenLabel);
			aliquotForm.setCheckedButton("1");
		}

		//Map containerMap = bizLogic.getAllocatedContainerMap();
		Map containerMap = new HashMap();
		if (Constants.PAGEOF_CREATE_ALIQUOT.equals(request.getParameter(Constants.PAGEOF)))
		{
			pageOf = validate(request, aliquotForm);

			if (Constants.PAGEOF_CREATE_ALIQUOT.equals(pageOf))
			{
				pageOf = checkForSpecimen(request, aliquotForm);

				if (Constants.PAGEOF_CREATE_ALIQUOT.equals(pageOf))
				{
					int aliquotCount = Integer.parseInt(aliquotForm.getNoOfAliquots());
					if (aliquotForm.isAliqoutInSameContainer())
					{

						containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm
								.getSpecimenClass(), Integer.parseInt(aliquotForm.getNoOfAliquots()),exceedingMaxLimit,true);
					}
					else
					{
						containerMap = bizLogic.getAllocatedContaienrMapForSpecimen(aliquotForm.getSpCollectionGroupId(), aliquotForm
								.getSpecimenClass(), 0,exceedingMaxLimit,true);
					}
					pageOf = checkForSufficientAvailablePositions(request, containerMap, aliquotCount);

					if (Constants.PAGEOF_CREATE_ALIQUOT.equals(pageOf))
					{
						populateAliquotsStorageLocations(aliquotForm, containerMap);
					}
				}
			}
		}
		request.setAttribute(Constants.EXCEEDS_MAX_LIMIT,exceedingMaxLimit);
		request.setAttribute(Constants.AVAILABLE_CONTAINER_MAP, containerMap);
		request.setAttribute(Constants.PAGEOF, pageOf);

		/*  String [] displayNameField = {Constants.SYSTEM_IDENTIFIER};
		 List specimenIdList = bizLogic.getList(Specimen.class.getName(), displayNameField, Constants.SYSTEM_IDENTIFIER, true);
		 request.setAttribute(Constants.SPECIMEN_ID_LIST,specimenIdList); */

		return mapping.findForward(pageOf);
	}

	//This method checks whether the specimen with given label exists or not.
	private String checkForSpecimen(HttpServletRequest request, AliquotForm form) throws Exception
	{
		IBizLogic bizLogic = BizLogicFactory.getInstance().getBizLogic(Constants.DEFAULT_BIZ_LOGIC);
		String specimenLabel = form.getSpecimenLabel();
		List specimenList = new ArrayList();
		String errorString = "";

		if (form.getCheckedButton().equals("1"))
		{
			specimenList = bizLogic.retrieve(Specimen.class.getName(), Constants.SYSTEM_LABEL, specimenLabel);
			errorString = Constants.SYSTEM_LABEL;
		}
		else
		{
			String barcode = form.getBarcode().trim();
			specimenList = bizLogic.retrieve(Specimen.class.getName(), "barcode", barcode);
			errorString = "barcode";
		}

		if (specimenList.isEmpty())
		{
			ActionErrors errors = getActionErrors(request);

			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("aliquots.specimen.notExists", errorString));
			saveErrors(request, errors);

			return Constants.PAGEOF_ALIQUOT;
		}
		else
		{
			Specimen specimen = (Specimen) specimenList.get(0);
			/**
			 * set the value of aliquotInSameContainer which was set while creation of collection protocol
			 * required for Bug 2309
			 */
			Boolean aliquotInSameContainer = specimen.getSpecimenCollectionGroup().getCollectionProtocolEvent().getCollectionProtocol()
					.getAliqoutInSameContainer();
			if (aliquotInSameContainer != null)
			{
				form.setAliqoutInSameContainer(aliquotInSameContainer.booleanValue());
			}
			populateParentSpecimenData(form, specimen);

			form.setSpecimenID("" + specimen.getId());
			//	request.setAttribute(Constants.SPECIMEN_ID,specimen.getId());

			String pageOf = checkQuantityPerAliquot(request, form);

			if (Constants.PAGEOF_CREATE_ALIQUOT.equals(pageOf))
			{
				boolean isDouble = Utility.isQuantityDouble(form.getSpecimenClass(), form.getType());

				if (!distributeAvailableQuantity(form, isDouble))
				{
					ActionErrors errors = getActionErrors(request);

					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.qtyInsufficient"));
					saveErrors(request, errors);

					return Constants.PAGEOF_ALIQUOT;
				}
				else
				{
					return Constants.PAGEOF_CREATE_ALIQUOT;
				}
			}
			else
			{
				return Constants.PAGEOF_ALIQUOT;
			}
		}
	}

	//This method checks whether there are sufficient storage locations are available or not.
	private String checkForSufficientAvailablePositions(HttpServletRequest request, Map containerMap, int aliquotCount)
	{
		int counter = 0;

		if (containerMap.isEmpty())
		{
			ActionErrors errors = getActionErrors(request);

			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.locations.notSufficient"));
			saveErrors(request, errors);

			return Constants.PAGEOF_ALIQUOT;
		}
		else
		{
			Object[] containerId = containerMap.keySet().toArray();

			for (int i = 0; i < containerId.length; i++)
			{
				Map xDimMap = (Map) containerMap.get(containerId[i]);

				if (!xDimMap.isEmpty())
				{
					Object[] xDim = xDimMap.keySet().toArray();

					for (int j = 0; j < xDim.length; j++)
					{
						List yDimList = (List) xDimMap.get(xDim[j]);
						counter = counter + yDimList.size();

						if (counter >= aliquotCount)
						{
							i = containerId.length;
							break;
						}
					}
				}
			}
		}

		if (counter >= aliquotCount)
		{
			return Constants.PAGEOF_CREATE_ALIQUOT;
		}
		else
		{
			ActionErrors errors = getActionErrors(request);

			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.locations.notSufficient"));
			saveErrors(request, errors);

			return Constants.PAGEOF_ALIQUOT;
		}
	}

	//This method populates parent specimen's data in formbean
	private void populateParentSpecimenData(AliquotForm form, Specimen specimen)
	{
		form.setSpecimenClass(specimen.getClassName());
		form.setType(specimen.getType());
		SpecimenCharacteristics chars = specimen.getSpecimenCharacteristics();
		form.setTissueSite(chars.getTissueSite());
		form.setTissueSide(chars.getTissueSide());
		form.setPathologicalStatus(specimen.getPathologicalStatus());
		form.setInitialAvailableQuantity(getAvailableQuantity(specimen));
		form.setAvailableQuantity(getAvailableQuantity(specimen));
		form.setSpCollectionGroupId(specimen.getSpecimenCollectionGroup().getId().longValue());
		if (specimen instanceof MolecularSpecimen)
		{
			String concentration = Utility.toString(((MolecularSpecimen) specimen).getConcentrationInMicrogramPerMicroliter());
			form.setConcentration(concentration);
		}
	}

	//This method returns the available quantity as per the specimen type
	private String getAvailableQuantity(Specimen specimen)
	{
		/* Aniruddha : 17/06/2006 TO BE DELETED LATER
		 String availableQuantity = "";
		 
		 if(specimen instanceof CellSpecimen)
		 {
		 availableQuantity = String.valueOf(((CellSpecimen)specimen).getAvailableQuantityInCellCount());
		 }
		 else if(specimen instanceof FluidSpecimen)
		 {
		 availableQuantity = String.valueOf(((FluidSpecimen)specimen).getAvailableQuantityInMilliliter());
		 }
		 else if(specimen instanceof MolecularSpecimen)
		 {
		 availableQuantity = String.valueOf(((MolecularSpecimen)specimen).getAvailableQuantityInMicrogram());
		 }
		 else if(specimen instanceof TissueSpecimen)
		 {
		 availableQuantity = String.valueOf(((TissueSpecimen)specimen).getAvailableQuantityInGram());
		 }
		 
		 return availableQuantity;
		 */
		return specimen.getAvailableQuantity().toString();
	}

	/*This method distributes the available quantity among the aliquots. On successful
	 distribution the function return true else false. */
	private boolean distributeAvailableQuantity(AliquotForm form, boolean isDouble) throws Exception
	{
		int aliquotCount = Integer.parseInt(form.getNoOfAliquots());
		String distributedQuantity;

		if (isDouble)
		{
			double availableQuantity = Double.parseDouble(form.getAvailableQuantity());
			// Bug no 560
			if (availableQuantity <= 0)
			{
				return false;
			}
			double dQuantity;
			DecimalFormat dFormat = new DecimalFormat("#.000");

			if (form.getQuantityPerAliquot() == null || form.getQuantityPerAliquot().trim().length() == 0)
			{
				dQuantity = availableQuantity / aliquotCount;
				dQuantity = Double.parseDouble(dFormat.format(dQuantity));
			}
			else
			{
				dQuantity = Double.parseDouble(form.getQuantityPerAliquot());
			}

			distributedQuantity = String.valueOf(dQuantity);
			availableQuantity = availableQuantity - Double.parseDouble(dFormat.format((dQuantity * aliquotCount)));

			if (availableQuantity < 0)
			{
				return false;
			}

			form.setAvailableQuantity(String.valueOf(availableQuantity));
		}
		else
		{
			int availableQuantity = (int) Double.parseDouble(form.getAvailableQuantity());
			// Bug no 560
			if (availableQuantity <= 0)
			{
				return false;
			}
			int iQauntity = (int) (availableQuantity / aliquotCount);

			if (form.getQuantityPerAliquot() == null || form.getQuantityPerAliquot().trim().length() == 0)
			{
				iQauntity = (int) (availableQuantity / aliquotCount);
			}
			else
			{
				iQauntity = (int) Double.parseDouble(form.getQuantityPerAliquot());
			}

			distributedQuantity = String.valueOf(iQauntity);
			availableQuantity = availableQuantity - (iQauntity * aliquotCount);

			if (availableQuantity < 0)
			{
				return false;
			}

			form.setAvailableQuantity(String.valueOf(availableQuantity));
		}

		Map aliquotMap = form.getAliquotMap();
		AliquotBizLogic bizLogic = (AliquotBizLogic) BizLogicFactory.getInstance().getBizLogic(Constants.ALIQUOT_FORM_ID);
		long nextAvailablenumber = bizLogic.getNextAvailableNumber("CATISSUE_SPECIMEN");

		for (int i = 1; i <= aliquotCount; i++)
		{
			String qtyKey = "Specimen:" + i + "_quantity";
			aliquotMap.put(qtyKey, distributedQuantity);
			String labelKey = "Specimen:" + i + "_label";
			String aliquotLabel = form.getSpecimenLabel() + "_" + (nextAvailablenumber + i - 1);
			aliquotMap.put(labelKey, aliquotLabel);
		}

		form.setAliquotMap(aliquotMap);
		return true;
	}

	//This function populates the availability map with available storage locations
	private void populateAliquotsStorageLocations(AliquotForm form, Map containerMap)
	{
		Map aliquotMap = form.getAliquotMap();
		int counter = 1;

		if (!containerMap.isEmpty())
		{
			Object[] containerId = containerMap.keySet().toArray();

			for (int i = 0; i < containerId.length; i++)
			{
				Map xDimMap = (Map) containerMap.get(containerId[i]);

				if (!xDimMap.isEmpty())
				{
					Object[] xDim = xDimMap.keySet().toArray();

					for (int j = 0; j < xDim.length; j++)
					{
						List yDimList = (List) xDimMap.get(xDim[j]);

						for (int k = 0; k < yDimList.size(); k++)
						{
							if (counter <= Integer.parseInt(form.getNoOfAliquots()))
							{
								String containerKey = "Specimen:" + counter + "_StorageContainer_id";
								String pos1Key = "Specimen:" + counter + "_positionDimensionOne";
								String pos2Key = "Specimen:" + counter + "_positionDimensionTwo";

								aliquotMap.put(containerKey, ((NameValueBean) containerId[i]).getValue());
								aliquotMap.put(pos1Key, ((NameValueBean) xDim[j]).getValue());
								aliquotMap.put(pos2Key, ((NameValueBean) yDimList.get(k)).getValue());

								counter++;
							}
							else
							{
								j = xDim.length;
								i = containerId.length;
								break;
							}
						}
					}
				}
			}
		}

		form.setAliquotMap(aliquotMap);
	}

	//This function checks the quantity per aliquot is valid or not
	private String checkQuantityPerAliquot(HttpServletRequest request, AliquotForm form) throws Exception
	{
		Validator validator = new Validator();
		String quantityPerAliquot = form.getQuantityPerAliquot();
		ActionErrors errors = getActionErrors(request);

		if (quantityPerAliquot != null && quantityPerAliquot.trim().length() != 0)
		{
			if (Utility.isQuantityDouble(form.getSpecimenClass(), form.getType()))
			{
				if (!validator.isDouble(quantityPerAliquot.trim()))
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format", ApplicationProperties
							.getValue("aliquots.qtyPerAliquot")));
					saveErrors(request, errors);
					return Constants.PAGEOF_ALIQUOT;
				}
			}
			else
			{
				if (!validator.isNumeric(quantityPerAliquot.trim()))
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format", ApplicationProperties
							.getValue("aliquots.qtyPerAliquot")));
					saveErrors(request, errors);
					return Constants.PAGEOF_ALIQUOT;
				}
			}
		}

		return Constants.PAGEOF_CREATE_ALIQUOT;
	}

	//This method validates the formbean.
	private String validate(HttpServletRequest request, AliquotForm form)
	{
		Validator validator = new Validator();
		ActionErrors errors = getActionErrors(request);

		String pageOf = Constants.PAGEOF_CREATE_ALIQUOT;

		if (form.getCheckedButton().equals("1"))
		{
			if (validator.isEmpty(form.getSpecimenLabel()))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required", ApplicationProperties
						.getValue("createSpecimen.parentLabel")));
				pageOf = Constants.PAGEOF_ALIQUOT;
			}
		}
		else
		{
			if (form.getBarcode() == null || form.getBarcode().trim().length() == 0)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.required", ApplicationProperties.getValue("specimen.barcode")));
				pageOf = Constants.PAGEOF_ALIQUOT;
			}
		}

		if (!validator.isNumeric(form.getNoOfAliquots()))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.item.format", ApplicationProperties.getValue("aliquots.noOfAliquots")));
			pageOf = Constants.PAGEOF_ALIQUOT;
		}

		saveErrors(request, errors);
		return pageOf;
	}

	/* This method returns the ActionErrors object present in the request scope.
	 * If it is absent method creates & returns new ActionErrors object.
	 */
	private ActionErrors getActionErrors(HttpServletRequest request)
	{
		ActionErrors errors = (ActionErrors) request.getAttribute(Globals.ERROR_KEY);

		if (errors == null)
		{
			errors = new ActionErrors();
		}

		return errors;
	}

}