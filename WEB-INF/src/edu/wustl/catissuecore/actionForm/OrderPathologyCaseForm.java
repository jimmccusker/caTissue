package edu.wustl.catissuecore.actionForm;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.global.Validator;

public class OrderPathologyCaseForm extends AbstractActionForm 
{
	/**
	 * Map containing the key-value pairs
	 */
	private Map values = new HashMap();

	/**
	 * String containing the class of specimen
	 */
	private String specimenClass;

	/**
	 * String containing the type of specimen
	 */
	private String type;

	/**
	 * String containing the tissue site
	 */
	private String tissueSite;

	/**
	 * String containing the pathological status
	 */
	private String pathologicalStatus;

	/**
	 * String containing the type of pathological case
	 */
	private String typeOfCase = "derivative";

	/**
	 * String containing the distribution protocol
	 */
	private String distrbutionProtocol;

	/**
	 * String containing the name of array to which to add the pathological case
	 */
	private String addToArray = "";

	/**
	 * List containing DefineArray objects
	 */
	private List defineArrayObj;

	/**
	 * OrderForm object
	 */
	private OrderForm orderForm;

	/**
	 * String array containing the selected items
	 */
	private String[] selectedItems = null;

	/**
	 * String containing unit
	 */
	private String unit;

	/**
	 * String containing concentration
	 */
	private String concentration;

	/**
	 * String array containing the items to be removed
	 */
	private String[] itemsToRemove = null;

	/**
	 * @return itemsToRemove
	 */
	public String[] getItemsToRemove() 
	{
		return itemsToRemove;
	}

	/**
	 * @param itemsToRemove String array containing the items to be removed
	 */
	public void setItemsToRemove(String[] itemsToRemove) 
	{
		this.itemsToRemove = itemsToRemove;
	}

	/**
	 * @return concentration
	 */
	public String getConcentration() 
	{
		return concentration;
	}

	/**
	 * @param concentration String containing concentration
	 */
	public void setConcentration(String concentration)
	{
		this.concentration = concentration;
	}

	/**
	 * @return String array containing the selected items
	 */
	public String[] getSelectedItems() 
	{
		return selectedItems;
	}

	/**
	 * @param selectedItems String array containing the selected items
	 */
	public void setSelectedItems(String[] selectedItems)
	{
		this.selectedItems = selectedItems;
	}

	/**
	 * @return name of array in which to add the specimen
	 */
	public String getAddToArray() 
	{
		return addToArray;
	}

	/**
	 * @param addToArray String containing the name of array to which to add the pathological case
	 */
	public void setAddToArray(String addToArray)
	{
		this.addToArray = addToArray;
	}

	/**
	 * @return list of define array objects
	 */
	public List getDefineArrayObj()
	{
		return defineArrayObj;
	}

	/**
	 * @param defineArrayObj List containing DefineArray objects
	 */
	public void setDefineArrayObj(List defineArrayObj) 
	{
		this.defineArrayObj = defineArrayObj;
	}

	/**
	 * @return OrderForm object
	 */
	public OrderForm getOrderForm() 
	{
		return orderForm;
	}

	/** 
	 * @param orderForm OrderForm object
	 */
	public void setOrderForm(OrderForm orderForm)
	{
		this.orderForm = orderForm;
	}

	/**
	 * @return pathological status of the specimen
	 */
	public String getPathologicalStatus() 
	{
		return pathologicalStatus;
	}

	/**
	 * @param pathologicalStatus String containing the pathological status
	 */
	public void setPathologicalStatus(String pathologicalStatus)
	{
		this.pathologicalStatus = pathologicalStatus;
	}

	/**
	 * @return class of specimen
	 */
	public String getSpecimenClass() 
	{
		return specimenClass;
	}

	/**
	 * @param specimenClass String containing the class of specimen
	 */
	public void setSpecimenClass(String specimenClass)
	{
		this.specimenClass = specimenClass;
	}

	/**
	 * @return type of specimen
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * @param type String containing the type of specimen
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @return tissue site of specimen
	 */
	public String getTissueSite() 
	{
		return tissueSite;
	}

	/**
	 * @param tissueSite String containing the tissue site
	 */
	public void setTissueSite(String tissueSite) 
	{
		this.tissueSite = tissueSite;
	}

	/**
	 * @return map values
	 */
	public Map getValues() 
	{
		return values;
	}

	/**
	 * @param values Map containing key-value pairs
	 */
	public void setValues(Map values)
	{
		this.values = values;
	}

	/**
	 * reset function
	 */
	protected void reset() 
	{
	}

	/**
	 * @param key String
	 * @param value Object
	 */
	public void setValue(String key, Object value) 
	{
		if (isMutable())
		{
			values.put(key, value);
		}
	}

	/**
	 * @param key String
	 * @return value in map corresponding to the key
	 */
	public Object getValue(String key) 
	{
		return values.get(key);
	}

	/**
	 * @param abstractDomain AbstractDomainObject
	 */
	public void setAllValues(AbstractDomainObject abstractDomain)
	{

	}

	/**
	 * @return Constants.ORDER_PATHOLOGY_FORM_ID
	 */
	public int getFormId() 
	{
		return Constants.ORDER_PATHOLOGY_FORM_ID;
	}

	/**
	 * @return type of case
	 */
	public String getTypeOfCase()
	{
		return typeOfCase;
	}

	/**
	 * @param typeOfCase String containing the type of pathological case
	 */
	public void setTypeOfCase(String typeOfCase) 
	{
		this.typeOfCase = typeOfCase;
	}

	/**
	 * @return name of distribution protocol
	 */
	public String getDistrbutionProtocol() 
	{
		return distrbutionProtocol;
	}

	/**
	 * @param distrbutionProtocol String containing the distribution protocol
	 */
	public void setDistrbutionProtocol(String distrbutionProtocol)
	{
		this.distrbutionProtocol = distrbutionProtocol;
	}

	/**
	 * @return unit
	 */
	public String getUnit() 
	{
		return unit;
	}

	/**
	 * @param unit String containing the unit
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	/**
	 * @param mapping ActionMapping
	 * @param request HttpServletRequest
	 * @return errors ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) 
	{

		ActionErrors errors = new ActionErrors();
		Validator validator = new Validator();

		if (selectedItems != null) 
		{
			boolean isNumber = true;
			if ((values != null) || (values.size() != 0)) 
			{
				String cnt = null;
				int reqQntyError = 0;
				for (int i = 0; i < selectedItems.length; i++)
				{
					cnt = selectedItems[i];
					String key="OrderSpecimenBean:" + cnt
					+ "_requestedQuantity";
					if ((values.get(key)) == null
							|| (values.get(key)).equals("")) 
					{
						reqQntyError = 1;
						break;
					} 
					else
					{
						isNumber = isNumeric(values.get(
								key).toString());
						if (!(isNumber))
						{
							reqQntyError = 2;
							break;
						}
						else 
						{
							Double reqQnty = new Double(values.get(
									key).toString());
							if (reqQnty < 0.0 || reqQnty == 0.0)
							{
								reqQntyError = 1;
								break;
							}
						}
					}
				}

				if (reqQntyError == 1)
				{
					errors.add("values", new ActionError(
							"errors.requestedQuantity.required"));
					values.clear();
				}
				if (reqQntyError == 2) 
				{
					errors.add("values", new ActionError(
							"errors.requestedQuantityBeNumeric.required"));
					values.clear();
				}
			}

			if (typeOfCase.equals("false")) 
			{
				if (specimenClass.equals("-1")
						|| specimenClass.equals("-- Select --")) 
				{
					errors.add("specimenClass", new ActionError(
							"errors.specimenClass.required"));
					values.clear();

				}
				if (type.equals("-1") || type.equals("-- Select --"))
				{
					errors.add("type", new ActionError(
							"errors.specimenType.required"));
					values.clear();
				}
			}
			if (pathologicalStatus.equals("-1")
					|| pathologicalStatus.equals("-- Select --")) 
			{
				errors.add("pathologicalStatus", new ActionError(
						"errors.pathologicalStatus.required"));
				values.clear();
			}
			if (tissueSite.equals("-1") || tissueSite.equals("-- Select --"))
			{
				errors.add("tissueSite", new ActionError(
						"errors.tissueSite.required"));
				values.clear();
			}
		}
		return errors;
	}

	/**
	 * @param sText String containing the text to be checked 
	 * @return boolean isNumber-returns true if given String is in proper number
	 *         format or else returns false
	 */
	private boolean isNumeric(String sText)
	{
		String validChars = "0123456789.";
		boolean isNumber = true;
		Character charTemp;

		for (int i = 0; i < sText.length() && isNumber; i++) 
		{
			charTemp = sText.charAt(i);
			if (validChars.indexOf(charTemp) == -1)
			{
				isNumber = false;
			}
		}
		return isNumber;
	}

	/**
	 * @return boolean true
	 */
	public boolean isAddOperation() 
	{
		return true;
	}

}
