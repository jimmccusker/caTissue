
package edu.wustl.catissuecore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheException;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.common.util.logger.Logger;

/**
 * This class handles all participantCache Related operations
 * @author vaishali_khandelwal
 *
 */
public class ParticipantCache
{

	//	participantsMap is map which contains all participants information.
	Map participantsMap;

	public ParticipantCache()
	{
		this.participantsMap = getParticipantMapFromCache();
	}

	/**
	 * This function returns the participantMap from the cache
	 * @return Map
	 */
	private Map getParticipantMapFromCache()
	{
		HashMap participantMap = new HashMap();
		try
		{
			//getting instance of catissueCoreCacheManager and getting participantMap from cache
			CatissueCoreCacheManager catissueCoreCacheManager = CatissueCoreCacheManager.getInstance();
			participantMap = (HashMap) catissueCoreCacheManager.getObjectFromCache(Constants.MAP_OF_PARTICIPANTS);
		}
		catch (IllegalStateException e)
		{
			e.printStackTrace();
			Logger.out.info("Error while accessing cache");
		}
		catch (CacheException e)
		{
			e.printStackTrace();
			Logger.out.info("Error while accessing cache");
		}
		return participantMap;
	}

	/**
	 * This method returns the participant object form particpantsMap where
	 * key = participantId
	 * @param participantId
	 * @return
	 */
	public Participant getParticipantInfo(Long participantId)
	{
		//This method returns the participant object form particpantsMap where
		//key = participantId
		return (Participant)participantsMap.get(participantId);
	}

	/**
	 * This method returns the particpantList for a given ID List
	 * @param participantIdList - Participant Id List
	 * @return List which contains participant objects
	 */
	public List getParticipantsList(List participantIdList)
	{
		List participantList = new ArrayList();
		Iterator itr = participantIdList.iterator();
		while (itr.hasNext())
		{
			Long participantId = (Long) itr.next();
			Participant participant = (Participant) participantsMap.get(participantId);
			if (participant != null)
				participantList.add(participant);
		}
		return participantList;
	}

	/**
	 * This method returs participant names for given ID List
	 * @param participantIdList Participant ID List
	 * @return List which contains Participant Names in format ID:lastName:firstName
	 */
	public List getParticpantNamesWithID(List participantIdList)
	{
		//This method returns the participant names with ID
		List participantList = new ArrayList();
		Iterator itr = participantIdList.iterator();
		while (itr.hasNext())
		{
			Long participantId = (Long) itr.next();
			Participant participant = (Participant) participantsMap.get(participantId);
			if (participant != null)
			{
				String info = participant.getId().toString();
				if (participant.getLastName() != null && !participant.getLastName().equals(""))
				{
					info = info + ":" + participant.getLastName();
				}
				if (participant.getFirstName() != null && !participant.getFirstName().equals(""))
				{
					info = info + " , " + participant.getFirstName();
				}
				participantList.add(info);
			}
		}
		return participantList;
	}
	
	/**
	 * This method adds the participant in Map
	 * @param participant the object which is to be added in map
	 */
	public void addParticipant(Participant participant)
	{
		if(participant != null && participant.getId() != null)
		{
			participantsMap.put(participant.getId() , participant);
		}
	}
	
	/**
	 * This method removes the participant from the map
	 * @param participantId - Participant Id which is to be removed from ParticipantMap
	 */
	public void removeParticipant(Long participantId)
	{
		participantsMap.remove(participantId);
	}
}
