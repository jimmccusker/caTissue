package edu.wustl.catissuecore.applet.listener;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JTable;


/**
 * This class is handler for JComboBox events.
 * 
 * @author Mandar Deshmukh.
 * @author Rahul Ner.
 * 
 *
 */
public class ComboBoxHandler extends BaseActionHandler
{
	

	public ComboBoxHandler(JTable table)
	{
		super(table);
	}

	/**
	 * @see edu.wustl.catissuecore.appletui.listener.BaseActionHandler#handleAction(java.awt.event.ActionEvent)
	 */
	protected void handleAction(ActionEvent event)
	{
		super.handleAction(event);
		System.out.println("Inside ComboBoxHandler");
	}
	
	/** 
	 * This method return the selected value of the combobox.
	 * @see edu.wustl.catissuecore.applet.listener.BaseActionHandler#getSelectedValue(java.awt.event.ActionEvent)
	 */
	protected Object getSelectedValue(ActionEvent event)
	{
		JComboBox selectedField = (JComboBox) event.getSource();
		return selectedField.getSelectedItem();
	}
 
}
