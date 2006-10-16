package edu.wustl.catissuecore.applet.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.table.TableModel;

import edu.wustl.catissuecore.applet.AppletConstants;
import edu.wustl.catissuecore.applet.component.SpecimenArrayTable;
import edu.wustl.catissuecore.applet.model.SpecimenArrayTableModel;
import edu.wustl.catissuecore.applet.ui.SpecimenArrayApplet;
import edu.wustl.catissuecore.applet.util.CommonAppletUtil;
import edu.wustl.catissuecore.applet.util.SpecimenArrayAppletUtil;

/**
 * <p>This class initializes the fields of SpecimenArrayTableMouseHandler.java</p>
 * @author Ashwin Gupta
 * @version 1.1
 */
public class SpecimenArrayTableMouseHandler extends MouseAdapter {

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		SpecimenArrayTable arrayTable = (SpecimenArrayTable) e.getSource();
		TableModel model = arrayTable.getModel();
		SpecimenArrayTableModel tableModel = null;
		
		if (model instanceof SpecimenArrayTableModel) {
			tableModel = (SpecimenArrayTableModel) model;
			SpecimenArrayApplet arrayApplet = (SpecimenArrayApplet) CommonAppletUtil.getBaseApplet(arrayTable);
			
			// enable if molecular specimen 
			if ((tableModel.getSpecimenClass() != null) && SpecimenArrayAppletUtil.isMolecularSpecimen(tableModel.getSpecimenClass()))
			{
				int column = arrayTable.getSelectedColumn();
				int row = arrayTable.getSelectedRow();
				String concentration = (String) tableModel.getSpecimenArrayModelMap().get(SpecimenArrayAppletUtil.getArrayMapKey(row,column,tableModel.getColumnCount(),AppletConstants.ARRAY_CONTENT_ATTR_CONC_INDEX));
				String quantity = (String) tableModel.getSpecimenArrayModelMap().get(SpecimenArrayAppletUtil.getArrayMapKey(row,column,tableModel.getColumnCount(),AppletConstants.ARRAY_CONTENT_ATTR_QUANTITY_INDEX));
				arrayApplet.getConcentrationTextField().setText(concentration);
				arrayApplet.getConcentrationTextField().setEnabled(true);
				arrayApplet.getQuantityTextField().setText(quantity);
				arrayApplet.getQuantityTextField().setEnabled(true);
				arrayApplet.getApplyButton().setEnabled(true);
			}
			arrayApplet.getCopyButton().setEnabled(true);
		}
		//System.out.println(e.getSource());
	}
}
