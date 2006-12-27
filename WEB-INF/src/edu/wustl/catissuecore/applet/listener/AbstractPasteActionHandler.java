
package edu.wustl.catissuecore.applet.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import edu.wustl.catissuecore.applet.AppletConstants;
import edu.wustl.catissuecore.applet.BaseCopyPasteValidator;
import edu.wustl.catissuecore.applet.CopyPasteOperationValidatorModel;
import edu.wustl.catissuecore.applet.MultipleSpecimenCopyPasteValidator;
import edu.wustl.catissuecore.applet.SpecimenArrayCopyPasteValidator;
import edu.wustl.catissuecore.applet.model.MultipleSpecimenTableModel;
import edu.wustl.catissuecore.applet.model.SpecimenArrayTableModel;
import edu.wustl.catissuecore.applet.model.SpecimenColumnModel;
import edu.wustl.catissuecore.applet.util.CommonAppletUtil;
import edu.wustl.common.action.CommonAddEditAction;

/**
 * <p>This class initializes the fields of AbstractPasteActionHandler.java</p>
 * @author Ashwin Gupta
 * @version 1.1
 */
public abstract class AbstractPasteActionHandler implements ActionListener
{

	/**
	 * Table component used in applet 
	 */
	protected JTable table;

	protected boolean isValidateSuccess = true;

	protected boolean populateValidatorModel = true;

	/**
	 * Default constructor 
	 */
	public AbstractPasteActionHandler()
	{
	}

	/**
	 * constructor with table to persist table
	 * @param table table used in applet
	 */
	public AbstractPasteActionHandler(JTable table)
	{
		this.table = table;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		preActionPerformed(e);
		doActionPerformed(e);
		postActionPerformed(e);
	}

	/**
	 * Pre action performed method for paste operation 
	 */
	protected void preActionPerformed(ActionEvent e)
	{

	}

	/**
	 * do action performed method for paste operation.
	 * Other paste listeners should override this method. 
	 */
	protected void doActionPerformed(ActionEvent e)
	{
		//super.handleAction(event);
		System.out.println("\n<<<<<<<<<<<          PASTE         <<<<<<<<<<<<<<<<<<<<<<\n");
		System.out.println("Inside AbstractPasteActionHandler");

		/**
		 *  Following code is added for checkbox
		 */
		int[] intSelectedRows = null;
		int[] intSelectedCols = null;
		populateValidatorModel = true;
		if (table.getModel() instanceof MultipleSpecimenTableModel)
		{
			MultipleSpecimenTableModel multipleSpecimenTableModel = CommonAppletUtil.getMultipleSpecimenTableModel(table);
			Map checkBoxMap = multipleSpecimenTableModel.getSpecimenCheckBoxMap();
			int columnsPerPage = multipleSpecimenTableModel.getColumnsPerPage();
			if (checkBoxMap != null && checkBoxMap.size() > 0)
			{
				List selectedRows = new ArrayList();
				List selectedCols = new ArrayList();
				Iterator itr = checkBoxMap.keySet().iterator();
				while (itr.hasNext())
				{
					String key = (String) itr.next();
					if (checkBoxMap.get(key) != null && ((Boolean) checkBoxMap.get(key)).booleanValue() == true)
					{
						int colNo = Integer.parseInt(key) - 1;
						selectedCols.add(new Integer(colNo % columnsPerPage));
						checkBoxMap.put(key, new Boolean(false));
						populateValidatorModel = false;
					}
				}
				if (populateValidatorModel == false)
				{
					for (int i = AppletConstants.SPECIMEN_COLLECTION_GROUP_ROW_NO; i <= AppletConstants.SPECIMEN_DERIVE_ROW_NO; i++)
					{
						selectedRows.add(new Integer(i));
					}
					Collections.sort(selectedCols);

					intSelectedRows = new int[selectedRows.size()];
					for (int i = 0; i < selectedRows.size(); i++)
					{
						intSelectedRows[i] = ((Integer) selectedRows.get(i)).intValue();
					}
					intSelectedCols = new int[selectedCols.size()];
					for (int i = 0; i < selectedCols.size(); i++)
					{
						intSelectedCols[i] = ((Integer) selectedCols.get(i)).intValue();
					}

				}

			}

		}

		CopyPasteOperationValidatorModel validatorModel = CommonAppletUtil.getBaseTableModel(table).getCopyPasteOperationValidatorModel();
		validatorModel.setOperation(AppletConstants.PASTE_OPERATION);
		if (populateValidatorModel)
		{
			int[] selectedColumns = table.getSelectedColumns();
			int[] selectedRows = table.getSelectedRows();
			populateValidatorModel(validatorModel, selectedRows, selectedColumns);
		}
		else
		{
			populateValidatorModel(validatorModel, intSelectedRows, intSelectedCols);
			List selectedPastedColumns = validatorModel.getSelectedPastedCols();
			TableColumnModel columnModel = table.getColumnModel();
			for (int i = 0; i < selectedPastedColumns.size(); i++)
			{
				int pastedColumn = ((Integer)selectedPastedColumns.get(i)).intValue();
				SpecimenColumnModel scm = (SpecimenColumnModel) columnModel.getColumn(pastedColumn).getCellEditor();
				scm.updateComponentValue(AppletConstants.SPECIMEN_CHECKBOX_ROW_NO, "false");
				SpecimenColumnModel scmRenderer = (SpecimenColumnModel) columnModel.getColumn(pastedColumn).getCellRenderer();
				scmRenderer.updateComponent(AppletConstants.SPECIMEN_CHECKBOX_ROW_NO);
			}
			SwingUtilities.updateComponentTreeUI(table);
		}

		BaseCopyPasteValidator validator = null;
		String validationMessage = null;

		if (table != null)
		{
			if (table.getModel() instanceof SpecimenArrayTableModel)
			{
				validator = new SpecimenArrayCopyPasteValidator(validatorModel);
			}
			else if (table.getModel() instanceof MultipleSpecimenTableModel)
			{
				validator = new MultipleSpecimenCopyPasteValidator(table, validatorModel);
			}
		}

		if (validator != null)
		{
			validationMessage = validator.validate();
			if (validationMessage.equals(""))
			{
				isValidateSuccess = true;
				updateUI(validatorModel);
			}
			else
			{
				isValidateSuccess = false;
				System.out.println(" validationMessage:: " + validationMessage);
				Object[] paramArray = {validationMessage};
				CommonAppletUtil.callJavaScriptFunction(table, getJSMethodName(), paramArray);
			}
		}
	}

	private void populateValidatorModel(CopyPasteOperationValidatorModel validatorModel, int[] selectedRows, int[] selectedColumns)
	{
		validatorModel.setSelectedPastedRows(CommonAppletUtil.createListFromArray(selectedRows));
		validatorModel.setSelectedPastedCols(CommonAppletUtil.createListFromArray(selectedColumns));
		validatorModel.setOperation(AppletConstants.PASTE_OPERATION);
		validatorModel.setRowCount(table.getRowCount());
		validatorModel.setColumnCount(getColumnCount());
	}

	private void updateUI(CopyPasteOperationValidatorModel validatorModel)
	{
		HashMap dataMap = validatorModel.getCopiedData();
		System.out.println("Copied Data : " + dataMap);
		int selectedRow = table.getSelectedRow();
		int selectedCol = table.getSelectedColumn();
		/**
		 *  In case of checkbox selection, selected row and selected column 
		 *  should come from selected pasted rows and selected pasted columns respectively.
		 */
		if (!populateValidatorModel)
		{
			selectedRow = ((Integer) validatorModel.getSelectedPastedRows().get(0)).intValue();
			selectedCol = ((Integer) validatorModel.getSelectedPastedCols().get(0)).intValue();
		}
		setUI(validatorModel, dataMap, selectedRow, selectedCol);
	}

	private void setUI(CopyPasteOperationValidatorModel validatorModel, HashMap dataMap, int selectedRow, int selectedCol)
	{
		// Row at which data is to be set cant be checkbox
		if (selectedRow == 0)
		{
			selectedRow = 1;
		}
		int tmpSelectedRow = selectedRow;
		List copiedRows = validatorModel.getSelectedCopiedRows();
		List copiedCols = validatorModel.getSelectedCopiedCols();
		Collections.sort(copiedRows);
		Collections.sort(copiedCols);
		int selectedColumnIndex = selectedCol;

		for (int copiedColumnCount = 0; copiedColumnCount < copiedCols.size(); copiedColumnCount++)
		{
			int copiedCol = ((Integer) (copiedCols.get(copiedColumnCount))).intValue();
			selectedRow = tmpSelectedRow;
			for (int count = 0; count < copiedRows.size(); count++)
			{
				int copiedRow = ((Integer) (copiedRows.get(count))).intValue();
				//check for disabled rows
				if (!isDisabledRow(selectedRow))
				{
					String key = CommonAppletUtil.getDataKey(copiedRow, copiedCol);
					List valueList = (List) dataMap.get(key);
					doPasteData(selectedRow, selectedColumnIndex, valueList);
				}
				selectedRow = selectedRow + 1;
			}
			selectedColumnIndex++;
		}
		SwingUtilities.updateComponentTreeUI(table);
	}

	/**
	 * @param selectedRow selected row
	 * @param selectedCol selected col
	 * @param value value
	 */
	protected abstract void doPasteData(int selectedRow, int selectedCol, List valueList);

	/**
	 * get total no. of columns
	 * @return total coumn count
	 */
	protected abstract int getColumnCount();

	/**
	 * @param rowNo boolean
	 * @return boolean value
	 */
	protected boolean isDisabledRow(int rowNo)
	{
		return false;
	}

	/**
	 * Post action performed method for paste operation 
	 */
	protected void postActionPerformed(ActionEvent e)
	{
		CopyPasteOperationValidatorModel validatorModel = CommonAppletUtil.getBaseTableModel(table).getCopyPasteOperationValidatorModel();
		validatorModel.setOperation("");
		System.out.println("CopyPasteOperationValidatorModel operation set to : " + validatorModel.getOperation());
		System.out.println("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
	}

	/**
	 * @return JS method name for this button.
	 */
	protected String getJSMethodName()
	{
		return "showErrorMessage";
	}

}