package edu.wustl.catissuecore.applet.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;


/**
 * This is base Handler class for all of the component in tissuecore.
 * 
 * @author Mandar Deshmukh
 * @author Rahul Ner
 *
 */
public class BaseActionHandler implements ActionListener
{
	/**
	 * 
	 */
	protected JTable table;
	
	public BaseActionHandler(JTable table)
	{
		this.table = table;
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event)
	{
		preActionPerformed();		
		handleAction(event);
		postActionPerformed();
	}

	/**
	 * This method provides a hook to specific Listener classes that needs to do some functionality before action executed. 
	 */
	protected void preActionPerformed()
	{
	
	}

	/**
	 * This method provides a hook to specific Listener classes that needs to do some functionality after action executed. 
	 */
	protected void postActionPerformed()
	{
		//fireEditingStopped();
		table.getModel().setValueAt(getSelectedValue(),table.getSelectedRow(),table.getSelectedColumn());

	}

	protected Object getSelectedValue() {
		return null;
	}
	
	/**
	 * This method handles specific action. Each subclass can provide custom handling.
	 */
	protected void handleAction(ActionEvent event)
	{
		
	}
}