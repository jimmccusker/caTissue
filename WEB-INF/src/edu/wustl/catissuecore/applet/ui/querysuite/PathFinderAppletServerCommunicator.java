package edu.wustl.catissuecore.applet.ui.querysuite;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.common.dynamicextensions.domaininterface.EntityInterface;
import edu.wustl.cab2b.common.ejb.path.PathFinderBusinessInterface;
import edu.wustl.catissuecore.applet.AppletConstants;
import edu.wustl.catissuecore.applet.AppletServerCommunicator;
import edu.wustl.catissuecore.applet.model.AppletModelInterface;
import edu.wustl.catissuecore.applet.model.BaseAppletModel;
import edu.wustl.common.querysuite.metadata.associations.IInterModelAssociation;
import edu.wustl.common.querysuite.metadata.path.IPath;
/**
 * Returns all the possible paths between the source entity and destination entity.
 * @author deepti_shelar
 *
 */
public class PathFinderAppletServerCommunicator implements PathFinderBusinessInterface
{

	String serverURL = "";
	/**
	 * Constructor which set server url;
	 * @param serverURL
	 */
	PathFinderAppletServerCommunicator (String serverURL)
	{
		this.serverURL = serverURL ;
	}
	/**
	 * Returns all the possible paths between the source entity and destination entity.
	 * @param srcEntity List of EntityInterface
	 * @param destEntity EntityInterface
	 */
	public Map<EntityInterface, List<IPath>> getAllPossiblePaths(List<EntityInterface> srcEntity, EntityInterface destEntity) throws RemoteException
	{
		Map<EntityInterface, List<IPath>> pathsMap = null;
		String urlString = serverURL + AppletConstants.PATH_FINDER + "?" +AppletConstants.APPLET_ACTION_PARAM_NAME + "=initData";
		BaseAppletModel appletModel = new BaseAppletModel();
		Map inputMap = new HashMap(); 
		inputMap.put(AppletConstants.SRC_ENTITY, srcEntity);
		inputMap.put(AppletConstants.DEST_ENTITY, destEntity);
		appletModel.setData(inputMap);
		try
		{
			AppletModelInterface outputModel = AppletServerCommunicator.doAppletServerCommunication(urlString, appletModel);
			pathsMap = outputModel.getData();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return pathsMap;
	}
	/**
	 * retuens Inter Model Associations
	 * @param
	 */
	public List<IInterModelAssociation> getInterModelAssociations(Long arg0) throws RemoteException
	{
		return null;
	}
	

}
