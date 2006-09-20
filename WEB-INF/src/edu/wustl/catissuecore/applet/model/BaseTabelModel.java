/*
 * <p>Title: BaseTabelModel.java</p>
 * <p>Description:	This class initializes the fields of BaseTabelModel.java</p>
 * Copyright: Copyright (c) year 2006
 * Company: Washington University, School of Medicine, St. Louis.
 * @version 1.1
 * Created on Sep 14, 2006
 */
package edu.wustl.catissuecore.applet.model;

import javax.swing.table.AbstractTableModel;

/**
 * <p> Base Model class is used as base super table model for any table & all
 * table model is supposed to extend this base model to get default functionality.  
 * </p>
 * @author Ashwin Gupta
 * @version 1.1
 * @see javax.swing.table.TableModel
 * @see javax.swing.table.AbstractTableModel
 */

public class BaseTabelModel extends AbstractTableModel {

	/**
	 * Default Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 0; 
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return 0;
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	/**
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return true;
	}


}
