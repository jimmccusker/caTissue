/**
 * <p>Title: ShowStorageGridViewAction Class>
 * <p>Description:	ShowStorageGridViewAction shows the grid view of the map 
 * according to the storage container selected from the tree view.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 */

package edu.wustl.catissuecore.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.catissuecore.bizlogic.BizLogicFactory;
import edu.wustl.catissuecore.bizlogic.NewSpecimenBizLogic;
import edu.wustl.catissuecore.bizlogic.StorageContainerBizLogic;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.storage.StorageContainerGridObject;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.logger.Logger;

/**
 * ShowStorageGridViewAction shows the grid view of the map 
 * according to the storage container selected from the tree view.
 * @author gautam_shetty
 */
public class ShowStorageGridViewAction  extends SecureAction
{

    /**
     * Overrides the execute method of Action class.
     */
    public ActionForward executeSecureAction(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String systemIdentifier = request.getParameter(Constants.IDENTIFIER);
        String pageOf = request.getParameter(Constants.PAGEOF);
        StorageContainerBizLogic bizLogic = (StorageContainerBizLogic)BizLogicFactory
                .getBizLogic(Constants.STORAGE_CONTAINER_FORM_ID);
        List list = bizLogic.retrieve(StorageContainer.class.getName(),
                "systemIdentifier", systemIdentifier);
        StorageContainerGridObject storageContainerGridObject
        		= new StorageContainerGridObject();
        int [][]fullStatus = null;
        int [][] childContainerSystemIdentifiers = null;
        
        if (list != null)
        {
            StorageContainer storageContainer = (StorageContainer) list.get(0);
            
            storageContainerGridObject.setSystemIdentifier(storageContainer.getSystemIdentifier().longValue());
            storageContainerGridObject.setType(storageContainer.getStorageType().getType());
            
            Integer oneDimensionCapacity = storageContainer
            				.getStorageContainerCapacity().getOneDimensionCapacity();
            Integer twoDimensionCapacity = storageContainer
 							.getStorageContainerCapacity().getTwoDimensionCapacity();
            
            childContainerSystemIdentifiers = new int[oneDimensionCapacity.intValue()][twoDimensionCapacity.intValue()];
            storageContainerGridObject.setOneDimensionCapacity(oneDimensionCapacity);
            storageContainerGridObject.setTwoDimensionCapacity(storageContainer
                    		.getStorageContainerCapacity().getTwoDimensionCapacity());
            
            fullStatus = new int[oneDimensionCapacity.intValue()][twoDimensionCapacity.intValue()];
            
            if (storageContainer.getChildrenContainerCollection() != null)
            {
                Iterator iterator = storageContainer.getChildrenContainerCollection().iterator();
                while(iterator.hasNext())
                {
                    StorageContainer childStorageContainer = (StorageContainer)iterator.next();
                    Integer positionDimensionOne = childStorageContainer.getPositionDimensionOne();
                    Integer positionDimensionTwo = childStorageContainer.getPositionDimensionTwo();
                    fullStatus[positionDimensionOne.intValue()][positionDimensionTwo.intValue()] = 1;
                    childContainerSystemIdentifiers[positionDimensionOne.intValue()][positionDimensionTwo.intValue()]
                                                   = childStorageContainer.getSystemIdentifier().intValue();
                }
            }          
            
            NewSpecimenBizLogic specimenBizLogic = (NewSpecimenBizLogic)BizLogicFactory
            							.getBizLogic(Constants.NEW_SPECIMEN_FORM_ID);
            list = specimenBizLogic.retrieve(Specimen.class.getName(),
                    "storageContainer.systemIdentifier", systemIdentifier);
            
            if (list != null)
            {
                Iterator iterator = list.iterator();
                while(iterator.hasNext())
                {
                    Specimen specimen = (Specimen)iterator.next();
                    Integer positionDimensionOne = specimen.getPositionDimensionOne();
                    Integer positionDimensionTwo = specimen.getPositionDimensionTwo();
                    fullStatus[positionDimensionOne.intValue()][positionDimensionTwo.intValue()] = 2;
                    childContainerSystemIdentifiers[positionDimensionOne.intValue()][positionDimensionTwo.intValue()]
                                                   = specimen.getSystemIdentifier().intValue();
                }
            }
        }
        
        if (pageOf.equals(Constants.PAGEOF_STORAGE_LOCATION))
        {
        	String storageContainerType = request.getParameter(Constants.STORAGE_CONTAINER_TYPE);
            int startNumber = bizLogic.getNextContainerNumber(Long.parseLong(systemIdentifier),
                    Long.parseLong(storageContainerType),false);
            
            request.setAttribute(Constants.STORAGE_CONTAINER_TYPE,storageContainerType);
            request.setAttribute(Constants.START_NUMBER,new Integer(startNumber));
            Logger.out.debug("startNumber "+startNumber);
        }
         
        request.setAttribute(Constants.PAGEOF, pageOf);
        request.setAttribute(Constants.CHILD_CONTAINER_SYSTEM_IDENTIFIERS, childContainerSystemIdentifiers);
        request.setAttribute(Constants.STORAGE_CONTAINER_CHILDREN_STATUS,fullStatus);
        request.setAttribute(Constants.STORAGE_CONTAINER_GRID_OBJECT,
                storageContainerGridObject);
        
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * @param fullStatus
     * @param childContainerSystemIdentifiers
     * @param storageContainer
     */
    private void setStorageContainerStatus(boolean[][] fullStatus, int[][] childContainerSystemIdentifiers, Collection collection)
    {
        Iterator iterator = collection.iterator();
        while(iterator.hasNext())
        {
            StorageContainer childStorageContainer = (StorageContainer)iterator.next();
            Integer positionDimensionOne = childStorageContainer.getPositionDimensionOne();
            Integer positionDimensionTwo = childStorageContainer.getPositionDimensionTwo();
            fullStatus[positionDimensionOne.intValue()][positionDimensionTwo.intValue()] = true;
            childContainerSystemIdentifiers[positionDimensionOne.intValue()][positionDimensionTwo.intValue()]
                                           = childStorageContainer.getSystemIdentifier().intValue();
        }
    }

}