
package edu.wustl.catissuecore.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import net.sf.ehcache.CacheException;

import edu.wustl.catissuecore.domain.Container;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.util.dbManager.DAOException;

public class StorageContainerUtil
{

	/**
	 * This functions updates the storage container map when a new position is to be added in map
	 * @param storageContainer - Storage container whose position is freed
	 * @param continerMap - map of storage container
	 * @param x - x position which is updated
	 * @param y - y position which is updated
	 */
	public static synchronized void insertSinglePositionInContainerMap(StorageContainer storageContainer, Map containerMap, int x, int y)
	{
		NameValueBean storageContainerId = new NameValueBean(storageContainer.getName(), (storageContainer.getId()));
		TreeMap storageContainerMap = (TreeMap) containerMap.get(storageContainerId);
		Integer xObj = new Integer(x);

		NameValueBean nvb = new NameValueBean(xObj, xObj);
		if (storageContainerMap == null)
		{
			storageContainerMap = new TreeMap();
			containerMap.put(storageContainerId, storageContainerMap);

		}
		List yPosList = (List) storageContainerMap.get(nvb);
		if (yPosList == null || yPosList.size() == 0)
		{
			yPosList = new ArrayList();
			yPosList.add(new NameValueBean(new Integer(y), new Integer(y)));
		}
		else
		{
			Collections.sort(yPosList);
			int size = yPosList.size();
			boolean insertFlag = true;
			for (int i = 0; i < size; i++)
			{
				NameValueBean yPosNvb = (NameValueBean) yPosList.get(i);
				if (Integer.parseInt(yPosNvb.getName()) == y)
				{
					insertFlag = false;
					break;
				}
			}
			if (insertFlag)
			{
				yPosList.add(new NameValueBean(new Integer(y), new Integer(y)));
				Collections.sort(yPosList);
			}

		}
		storageContainerMap.put(nvb, yPosList);
	}

	/**
	 * This functions updates the storage container map when a new position is to be deleted in map
	 * @param storageContainer - Storage container whose position is occupied
	 * @param continerMap - map of storage container
	 * @param x - x position which is updated
	 * @param y - y position which is updated
	 */
	public static synchronized void deleteSinglePositionInContainerMap(StorageContainer storageContainer, Map continersMap, int x, int y)
	{
		NameValueBean storageContainerId = new NameValueBean(storageContainer.getName(), storageContainer.getId());
		TreeMap storageContainerMap = (TreeMap) continersMap.get(storageContainerId);

		Integer xObj = new Integer(x);

		NameValueBean nvb = new NameValueBean(xObj, xObj);

		List yPosList = (List) storageContainerMap.get(nvb);
		if (yPosList != null)
		{

			//Logger.out.debug("deleteSinglePositionInContainerMap method :----yPosList :" + yPosList);
			for (int i = 0; i < yPosList.size(); i++)
			{
				NameValueBean yPosnvb = (NameValueBean) yPosList.get(i);
				if (yPosnvb.getValue().equals("" + y))
				{
					//Logger.out.debug("Removing value:" + y);
					yPosList.remove(i);
					break;
				}

				//yPosList.remove(new NameValueBean(new Integer(y), new Integer(y)));

			}
		}
		//Logger.out.debug("deleteSinglePositionInContainerMap method after deleting :----yPosList :" + yPosList);

		if (yPosList == null || yPosList.size() == 0)
		{
			storageContainerMap.remove(nvb);
		}
		if (storageContainerMap == null || storageContainerMap.size() == 0)
		{
			continersMap.remove(storageContainerId);

		}

	}

	/**
	 * This functions updates the storage container map when a new container is added
	 * @param storageContainer - Storage container which is added
	 * @param continerMap - map of storage container
	 * @param x - x position of parent storage container
	 * @param y - y position of parent storage container
	 */
	public static synchronized void addStorageContainerInContainerMap(StorageContainer storageContainer, Map continersMap) throws DAOException
	{
		NameValueBean storageContainerId = new NameValueBean(storageContainer.getName(), storageContainer.getId());
		TreeMap availabelPositionsMap = (TreeMap) getAvailablePositionMapForNewContainer(storageContainer);
		if (availabelPositionsMap != null && availabelPositionsMap.size() != 0)
		{
			continersMap.put(storageContainerId, availabelPositionsMap);
		}

		if (storageContainer.getParent() != null)
		{
			Container parentContainer = storageContainer.getParent();
			int x = storageContainer.getPositionDimensionOne().intValue();
			int y = storageContainer.getPositionDimensionTwo().intValue();
			deleteSinglePositionInContainerMap((StorageContainer) parentContainer, continersMap, x, y);
		}

	}

	/**
	 * This functions updates the storage container map when a new container is deleted or disabled
	 * @param storageContainer - Storage container which is deleted or disabled
	 * @param continerMap - map of storage container
	 * @param x - x position of parent storage container
	 * @param y - y position of parent storage container
	 */
	public static synchronized void removeStorageContainerInContainerMap(StorageContainer storageContainer, Map continersMap) throws DAOException
	{
		NameValueBean storageContainerId = new NameValueBean(storageContainer.getName(), storageContainer.getId());
		continersMap.remove(storageContainerId);

		if (storageContainer.getParent() != null)
		{
			Container parentContainer = storageContainer.getParent();
			int x = storageContainer.getPositionDimensionOne().intValue();
			int y = storageContainer.getPositionDimensionTwo().intValue();
			insertSinglePositionInContainerMap((StorageContainer) parentContainer, continersMap, x, y);
		}

	}

	/**
	 * This functions returns a map of containers from the cache.
	 * @return Returns a map of allocated containers vs. their respective free locations.
	 * @throws DAOException
	 */
	public static Map getContainerMapFromCache() throws CacheException
	{
		// TODO if map is null
		// TODO move all code to common utility

		// getting instance of catissueCoreCacheManager and getting participantMap from cache
		CatissueCoreCacheManager catissueCoreCacheManager = CatissueCoreCacheManager.getInstance();
		Map containerMap = (TreeMap) catissueCoreCacheManager.getObjectFromCache(Constants.MAP_OF_STORAGE_CONTAINERS);
		return containerMap;
	}

	/**
	 * This functions returns a list of disabled containers from the cache.
	 * @return Returns a list of disabled.
	 * @throws DAOException
	 */
	public static List getListOfDisabledContainersFromCache() throws Exception
	{
		// TODO if map is null
		// TODO move all code to common utility

		// getting instance of catissueCoreCacheManager and getting participantMap from cache
		CatissueCoreCacheManager catissueCoreCacheManager = CatissueCoreCacheManager.getInstance();
		List disabledconts = (ArrayList) catissueCoreCacheManager.getObjectFromCache(Constants.MAP_OF_DISABLED_CONTAINERS);
		return disabledconts;
	}

	/**
	 * This functions returns a map of available rows vs. available columns.
	 * @param container The container.
	 * @return Returns a map of available rows vs. available columns.
	 * @throws DAOException
	 */

	public static Map getAvailablePositionMapForNewContainer(StorageContainer container) throws DAOException
	{
		Map map = new TreeMap();

		if (!container.isFull().booleanValue())
		{
			final int dimX = container.getCapacity().getOneDimensionCapacity().intValue() + 1;
			final int dimY = container.getCapacity().getTwoDimensionCapacity().intValue() + 1;

			for (int x = 1; x < dimX; x++)
			{

				List list = new ArrayList();

				for (int y = 1; y < dimY; y++)
				{
					list.add(new NameValueBean(new Integer(y), new Integer(y)));

				}

				if (!list.isEmpty())
				{
					Integer xObj = new Integer(x);
					NameValueBean nvb = new NameValueBean(xObj, xObj);
					map.put(nvb, list);

				}
			}
		}

		return map;
	}

	public static synchronized void insertIncreasedCapacities(Map containerMap, StorageContainer currentContainer, StorageContainer oldContainer)
	{
		int xOld = oldContainer.getCapacity().getOneDimensionCapacity().intValue();
		int xNew = currentContainer.getCapacity().getOneDimensionCapacity().intValue();
		int yOld = oldContainer.getCapacity().getTwoDimensionCapacity().intValue();
		int yNew = currentContainer.getCapacity().getTwoDimensionCapacity().intValue();
		NameValueBean storageContainerId = new NameValueBean(currentContainer.getName(), (currentContainer.getId()));
		TreeMap storageContainerMap = (TreeMap) containerMap.get(storageContainerId);
		if (storageContainerMap == null)
		{
			storageContainerMap = new TreeMap();
			containerMap.put(storageContainerId, storageContainerMap);
		}

		if (xNew > xOld)
		{
			for (int i = xOld + 1; i <= xNew; i++)
			{
				NameValueBean xNvb = new NameValueBean(new Integer(i), new Integer(i));

				List yPosList = new ArrayList();
				for (int j = 1; j <= yOld; j++)
				{
					NameValueBean yNvb = new NameValueBean(new Integer(j), new Integer(j));
					yPosList.add(yNvb);

				}
				if (yPosList.size() > 0)
				{
					storageContainerMap.put(xNvb, yPosList);
				}

			}
		}
		if (yNew > yOld)
		{
			for (int i = 1; i <= xNew; i++)
			{
				NameValueBean xNvb = new NameValueBean(new Integer(i), new Integer(i));
				List yPosList = new ArrayList();
				for (int j = yOld + 1; j <= yNew; j++)
				{
					NameValueBean yNvb = new NameValueBean(new Integer(j), new Integer(j));

					yPosList.add(yNvb);
				}
				List yList = (ArrayList) storageContainerMap.get(xNvb);
				if (yList == null)
				{
					storageContainerMap.put(xNvb, yPosList);
				}
				else
				{
					yList.addAll(yPosList);
				}

			}
		}

	}

	public static synchronized void updateNameInCache(Map containerMap, StorageContainer currentContainer, StorageContainer oldContainer)
	{
		//Using treeMap , so can't directly update the key contents.
		Map positionMap = new TreeMap();
		boolean keyRemoved = false;
		Set keySet = containerMap.keySet();
		Iterator itr = keySet.iterator();
		while (itr.hasNext())
		{
			NameValueBean nvb = (NameValueBean) itr.next();
			if (nvb.getValue().equals(oldContainer.getId().toString()) && nvb.getName().equals(oldContainer.getName().toString()))
			{
				positionMap = (Map) containerMap.get(nvb);
				containerMap.remove(nvb);
				keyRemoved = true;
				break;
			}
		}
		if (keyRemoved)
		{
			NameValueBean nvbUpdated = new NameValueBean(currentContainer.getName(), currentContainer.getId());
			containerMap.put(nvbUpdated, positionMap);
		}

	}

	public static boolean chkContainerFull(String storageContainerId, String storageContainerName) throws Exception
	{
		Map containerMap = getContainerMapFromCache();

		NameValueBean nvb = new NameValueBean(storageContainerName, storageContainerId);
		if (containerMap.containsKey(nvb))
			return false;
		else
			return true;

	}
	public static boolean isPostionAvaialble(String storageContainerId , String storageContainerName , String x , String y) throws CacheException
	{
		Map containerMap = getContainerMapFromCache();
		NameValueBean nvb = new NameValueBean(storageContainerName, storageContainerId);
		Map positionMap = (Map)containerMap.get(nvb);
		
		if(positionMap != null && !positionMap.isEmpty())
		{
			NameValueBean xNvb = new NameValueBean(x,x);
			List yList = (List) positionMap.get(xNvb);
			if(yList != null && !yList.isEmpty())
			{
				NameValueBean yNvb = new NameValueBean(y ,y);
				if(!yList.contains(yNvb))
					return false;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		return true;
		
	}
}
