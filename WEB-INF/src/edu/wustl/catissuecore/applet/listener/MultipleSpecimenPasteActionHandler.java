
package edu.wustl.catissuecore.applet.listener;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import edu.wustl.catissuecore.applet.CopyPasteOperationValidatorModel;
import edu.wustl.catissuecore.applet.model.SpecimenColumnModel;
import edu.wustl.catissuecore.applet.util.CommonAppletUtil;

/**
 * <p>This class initializes the fields of MultipleSpecimenPasteActionHandler.java</p>
 * @author Ashwin Gupta
 * @version 1.1
 */
public class MultipleSpecimenPasteActionHandler extends AbstractPasteActionHandler
{

	/**
	 * constructor with table to persist table
	 * @param table table used in applet
	 */
	public MultipleSpecimenPasteActionHandler(JTable table)
	{
		super(table);
	}

	/**
	 * @see edu.wustl.catissuecore.applet.listener.AbstractPasteActionHandler#doActionPerformed(java.awt.event.ActionEvent)
	 */
	protected void doActionPerformed(ActionEvent e)
	{
			//super.handleAction(event);
			System.out.println("\n<<<<<<<<<<<          PASTE         <<<<<<<<<<<<<<<<<<<<<<\n");
			System.out.println("Inside MultipleSpecimenPasteActionHandler");
			
			CopyPasteOperationValidatorModel validatorModel = CommonAppletUtil.getMultipleSpecimenTableModel(table).getCopyPasteOperationValidatorModel();
			
			int[] selectedColumns = table.getSelectedColumns();
			int[] selectedRows = table.getSelectedRows();
			System.out.println("selectedRows : ");
			CommonAppletUtil.printArray(selectedRows );
			System.out.println(" \n selectedColumns : ");
			CommonAppletUtil.printArray(selectedColumns );		  

			validatorModel.setSelectedPastedRows(CommonAppletUtil.createListFromArray(selectedRows));
			validatorModel.setSelectedPastedCols(CommonAppletUtil.createListFromArray(selectedColumns));
			System.out.println(" Cell info set for Pasting ");
			
			if (CommonAppletUtil.validateCell())
			{
				updateTableModel();
				
				updateUI(validatorModel);
			}
		System.out.println("\n >>>>>>>>>>>>>> DONE >>>>>>>>>>>>");
	}
	
	private void updateTableModel()
	{
		System.out.println("This method updates the model.");
	}
	
	private void updateUI(CopyPasteOperationValidatorModel validatorModel)
	{
		System.out.println("This method updates the table UI.");
		HashMap dataMap = validatorModel.getCopiedData();
		System.out.println("\n Received Copied Data Map >>>>>>>>>>> \n");
		System.out.println(dataMap);
		System.out.println("\n-------------------------\n");

		System.out.println("Rows : "+ validatorModel.getSelectedPastedRows());
		System.out.println("Cols : " + validatorModel.getSelectedPastedCols());
		
		System.out.println("\n----- Setting value --------------------\n");
		//commenting to test multiple cell pasting
//		int selectedRow = table.getSelectedRow();
//		int selectedCol = table.getSelectedColumn ();
//		
//		int r = ((Integer)(validatorModel.getSelectedPastedRows().get(0))).intValue() ;
//		int c = ((Integer)(validatorModel.getSelectedPastedCols().get(0))).intValue() ;
//		
//		String key = CommonAppletUtil.getDataKey(((Integer)(validatorModel.getSelectedCopiedRows().get(0))).intValue(),((Integer)(validatorModel.getSelectedCopiedCols().get(0))).intValue()); 
//			
//		System.out.println("Key : "+ key);
//		Object value = dataMap.get(key );
//		System.out.println("Value : "+ value);
//		TableColumnModel columnModel = table.getColumnModel();
//		SpecimenColumnModel scm = (SpecimenColumnModel)columnModel.getColumn(table.getSelectedColumn()).getCellEditor();
//		scm.updateComponentValue(selectedRow,value.toString() );
		System.out.println("Setting values to multiple cells\n-------------------------------\n");

		int selectedRow = table.getSelectedRow();
		int selectedCol = table.getSelectedColumn ();

		setUI(validatorModel, dataMap, selectedRow, selectedCol);
		System.out.println("\n>>>>>>>>>>>>>>>> UI data Set. <<<<<<<<<<<<<<<<<<<<<  \n");
	}
	
	private void setUI(CopyPasteOperationValidatorModel validatorModel, HashMap dataMap, int selectedRow, int selectedCol)
	{
		
		// copeid rows, columns
		List copiedRows = validatorModel.getSelectedCopiedRows();
		List copiedCols = validatorModel.getSelectedCopiedCols();
		Collections.sort(copiedRows);
		Collections.sort(copiedCols);
		
//		//paste rows, columns
//		List pasteRows = validatorModel.getSelectedPastedRows();
//		List pasteCols = validatorModel.getSelectedPastedCols();
//		Collections.sort(pasteRows);
//		Collections.sort(pasteRows);
		
		System.out.println("CopiedRows Size: "+ copiedRows.size()+" | "+copiedRows);
		System.out.println("CopiedColumns Size: "+ copiedCols.size()+" | "+ copiedCols);
//		int selectedColumnIndex = table.getSelectedColumn();
		int selectedColumnIndex = selectedCol;

		for(int copiedColumnCount=0; copiedColumnCount<copiedCols.size(); copiedColumnCount++)
		{
			int copiedCol = ((Integer)(copiedCols.get(copiedColumnCount))).intValue();
			for(int count=0;count<copiedRows.size();count++ )
			{
				int copiedRow = ((Integer)(copiedRows.get(count))).intValue();
				System.out.println("Count : "+count+ " ,copiedRow: "+ copiedRow + " , copiedCol: "+ copiedCol);
				String key = CommonAppletUtil.getDataKey(copiedRow, copiedCol);
				System.out.println("Key : "+ key);
				Object value = dataMap.get(key );
				System.out.println("Value : "+ value);

				TableColumnModel columnModel = table.getColumnModel();
				SpecimenColumnModel scm = (SpecimenColumnModel)columnModel.getColumn(selectedColumnIndex).getCellEditor();
				scm.updateComponentValue(selectedRow,value.toString() );
//				SpecimenColumnModel scmRenderer = (SpecimenColumnModel)columnModel.getColumn(selectedColumnIndex).getCellRenderer();
//				scmRenderer.updateComponent(selectedRow );
				System.out.println("Row updated : " + selectedRow );
				selectedRow = selectedRow + 1;
			}
			selectedColumnIndex++;
		}
		SwingUtilities.updateComponentTreeUI(table);
	}
	
}