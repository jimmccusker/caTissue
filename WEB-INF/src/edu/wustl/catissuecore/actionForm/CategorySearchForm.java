package edu.wustl.catissuecore.actionForm;


import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * FormBean representing the QueryModule's properties.
 * @author Mandar Shidhore
 * @author deepti_shelar
 * @version 1.0
 * @created 06-Nov-2006 10.40.04 AM
 */

public class CategorySearchForm extends ActionForm
{
	private String textField = null;
	private String classChecked = null;
	private String attributeChecked = null;
	private String permissibleValuesChecked = null;
	private String selected = null;
	private String entityName = null;
	private String stringToCreateQueryObject = null;
	private String tempStr = null;
	private List errors = null;
	private String searchButton = null;
	private String nextOperation = null;

	

	/**
	 * @return the nextOperation
	 */
	public String getNextOperation()
	{
		return nextOperation;
	}

	/**
	 * @param nextOperation the nextOperation to set
	 */
	public void setNextOperation(String nextOperation)
	{
		this.nextOperation = nextOperation;
	}

	/**
	 * @return the searchButton
	 */
	public String getSearchButton()
	{
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(String searchButton)
	{
		this.searchButton = searchButton;
	}

	/**
	 * @return the errors
	 */
	public List getErrors()
	{
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List errors)
	{
		this.errors = errors;
	}

	/**
	 * @return the stringToCreateQueryObject
	 */
	public String getStringToCreateQueryObject()
	{
		return stringToCreateQueryObject;
	}

	/**
	 * @param stringToCreateQueryObject the stringToCreateQueryObject to set
	 */
	public void setStringToCreateQueryObject(String stringToCreateQueryObject)
	{
		this.stringToCreateQueryObject = stringToCreateQueryObject;
	}

	/**
	 * @return the selected
	 */
	public String getSelected()
	{
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(String selected)
	{
		this.selected = selected;
	}

	/**
	 * @return the textField
	 */
	public String getTextField()
	{
		return textField;
	}

	/**
	 * @param textField the textField to set
	 */
	public void setTextField(String textField)
	{
		this.textField = textField;
	}

	/**
	 * @return the attributeChecked
	 */
	public String getAttributeChecked()
	{
		return attributeChecked;
	}

	/**
	 * @param attributeChecked the attributeChecked to set
	 */
	public void setAttributeChecked(String attributeChecked)
	{
		this.attributeChecked = attributeChecked;
	}

	/**
	 * @return the classChecked
	 */
	public String getClassChecked()
	{
		return classChecked;
	}

	/**
	 * @param classChecked the classChecked to set
	 */
	public void setClassChecked(String classChecked)
	{
		this.classChecked = classChecked;
	}


	/**
	 * @return the permissibleValuesChecked
	 */
	public String getPermissibleValuesChecked()
	{
		return permissibleValuesChecked;
	}

	/**
	 * @param permissibleValuesChecked the permissibleValuesChecked to set
	 */
	public void setPermissibleValuesChecked(String permissibleValuesChecked)
	{
		this.permissibleValuesChecked = permissibleValuesChecked;
	}


	/**
	 * @return the entityName
	 */
	public String getEntityName()
	{
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName)
	{
		this.entityName = entityName;
	}

	/**
	 * @return the tempStr
	 */
	public String getTempStr()
	{
		return tempStr;
	}

	/**
	 * @param tempStr the tempStr to set
	 */
	public void setTempStr(String tempStr)
	{
		this.tempStr = tempStr;
	}
}