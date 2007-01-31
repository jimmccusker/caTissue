
/**
 * <p>Title: SpecimenTreeBizLogic Class>
 * <p>Description:	SpecimenTreeBizLogic contains the bizlogic required to display Specimen hierarchy in tree form for ordering system module.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Ramya Nagraj
 * @version 1.00
 */

package edu.wustl.catissuecore.bizlogic;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Vector;

import edu.wustl.catissuecore.domain.ExistingSpecimenOrderItem;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.tree.SpecimenTreeNode;
import edu.wustl.common.tree.TreeDataInterface;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.bizlogic.IBizLogic;
import edu.wustl.common.dao.DAO;

/**
 * SpecimenTreeBizLogic contains the bizlogic required to display Specimen hierarchy in tree form in the ordering system module.
 * @author ramya_nagraj
 */

public class SpecimenTreeBizLogic extends DefaultBizLogic implements TreeDataInterface
{

	/**
	 * Long containing the id of specimen/specimen Collection group Id.
	 */
	private Long id;
	
	private boolean specimenCollGroup;
	/**
	 * No-Args Constructor
	 */
	public SpecimenTreeBizLogic()
	{
		
	}
	
	/**
	 * Parametrized Constructor
	 * @param specimenList
	 */
	public SpecimenTreeBizLogic(Long id,boolean specimenCollGroup)
	{
		this.id = id;
		this.specimenCollGroup = specimenCollGroup;
	}
	
	
	
	/**
	 * This function returns the Specimen and its child nodes in vector form for given specimen.
	 * @param specimenId int
	 * @return Vector Vector of Specimens
	 */
	
	public Vector getTreeViewData() throws DAOException 
	{
		//Retrieve the Specimen instance for that particular id.
		IBizLogic defaultBizLogic = BizLogicFactory.getInstance().getBizLogic(-1);
		
		Vector  allNodes = new Vector(); 
		
		//If specimenId is sent (Incase of biospecimen in orderlist or in defined array)
		if(specimenCollGroup == false) 
		{
			List specimen = defaultBizLogic.retrieve(Specimen.class.getName(),Constants.ID,id);
			
			//The list consists of only one element that is:- specimen instance
			Specimen specimenObj = (Specimen) specimen.get(0);
			allNodes = formTreeNode(specimenObj);
		}
		else //If specimenCollectiongroupId is sent(Incase of pathological case)
		{
			List specimenCollGrp = defaultBizLogic.retrieve(SpecimenCollectionGroup.class.getName(),Constants.ID,id);
			
			//The list consists of only one element that is:- specimencollecitongroup instance
			SpecimenCollectionGroup specimenCollGrpObj = (SpecimenCollectionGroup) specimenCollGrp.get(0);
			
			//Get the list of specimens for this group(SCG)
			Set specimenSet = (Set)specimenCollGrpObj.getSpecimenCollection();
			Iterator specimenSetItr = specimenSet.iterator();
			while(specimenSetItr.hasNext())
			{
				Specimen specimenObj = (Specimen)specimenSetItr.next();				
				allNodes = formTreeNode(specimenObj);
			}
		}
		return allNodes;
	}
	
	/**
	 * This function accepts specimen object and sets treenode for each specimen. 
	 * @param specimenObj
	 * @return rootNode SpecimentreeNode object
	 */
	private Vector formTreeNode(Specimen specimenObj)
	{
		Collection specimenSet = new HashSet();
		Vector specimenTreeVector = new Vector();
		
		SpecimenTreeNode rootNode = new SpecimenTreeNode();
		rootNode.setIdentifier(specimenObj.getId());
		rootNode.setType(specimenObj.getType());
		rootNode.setValue(specimenObj.getLabel());
		rootNode.setSpecimenClass(specimenObj.getClassName());
		
		rootNode.setParentIdentifier("0");
		rootNode.setParentValue("");
		
		specimenTreeVector.add(rootNode);
		//Get the list of specimens derived from this specimen.
		specimenSet = (Set)specimenObj.getChildrenSpecimen();
		
		//formSpecimenTree() constructs tree form of specimens and returns the nodes in vector form.
		Vector allNodes = formSpecimenTree(specimenTreeVector,rootNode,specimenSet);
		
		if(allNodes != null)
			rootNode.setChildNodes(allNodes);
				
		return allNodes;
	}

	/**
	 * This function constructs the tree indicating the parent-child relationship of the given specimen.
	 * @param parent SpecimenTreeNode
	 * @param childNodes Vector
	 * @return Vector
	 */
	private Vector formSpecimenTree(Vector specimenTreeVector,SpecimenTreeNode parent,Collection childNodes)
	{
		//If no childNodes present then tree will contain only the root node.
		if(childNodes == null)
		{
			return null;
		}
		//Otherwise
		Iterator specimenItr = childNodes.iterator();
		while(specimenItr.hasNext())
		{
			Specimen specimen  = (Specimen)specimenItr.next();
			SpecimenTreeNode treeNode = new SpecimenTreeNode();
			
			treeNode.setIdentifier(specimen.getId());
			treeNode.setType(specimen.getType());
			treeNode.setValue(specimen.getLabel());
			treeNode.setSpecimenClass(specimen.getClassName());
			
			treeNode.setParentIdentifier(parent.getIdentifier().toString());
			treeNode.setParentValue(parent.getValue());
			
			specimenTreeVector.add(treeNode);
			
			Vector subChildNodesVector = formSpecimenTree(specimenTreeVector,treeNode,specimen.getChildrenSpecimen());
			
			if(subChildNodesVector != null)
				treeNode.setChildNodes(subChildNodesVector);
			
		}
		return specimenTreeVector;
	}

	public Vector getTreeViewData(SessionDataBean arg0, Map arg1, List arg2) throws DAOException, ClassNotFoundException {
		
		return null;
	}
	
}
