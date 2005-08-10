/**
 * <p>Title: CollectionProtocolBizLogic Class>
 * <p>Description:	CollectionProtocolBizLogic is used to add CollectionProtocol information into the database using Hibernate.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Mandar Deshmukh
 * @version 1.00
 * Created on Aug 09, 2005
 */

package edu.wustl.catissuecore.bizlogic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import edu.wustl.catissuecore.dao.DAO;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.SpecimenRequirement;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.common.util.dbManager.DAOException;

/**
 * CollectionProtocolBizLogic is used to add CollectionProtocol information into the database using Hibernate.
 * @author Mandar Deshmukh
 */
public class CollectionProtocolBizLogic extends DefaultBizLogic
{
	/**
     * Saves the CollectionProtocol object in the database.
     * @param session The session in which the object is saved.
     * @param obj The CollectionProtocol object to be saved.
     * @throws HibernateException Exception thrown during hibernate operations.
     * @throws DAOException 
     */
	protected void insert(DAO dao, Object obj) throws DAOException
	{
		CollectionProtocol collectionProtocol = (CollectionProtocol)obj;
		
		List list = dao.retrieve(User.class.getName(), "systemIdentifier", collectionProtocol.getPrincipalInvestigator().getSystemIdentifier());
		if (list.size() != 0)
		{
			User pi = (User) list.get(0);
			collectionProtocol.setPrincipalInvestigator(pi);
		}
		
		Collection coordinatorColl = new HashSet();
		Iterator it = collectionProtocol.getUserCollection().iterator();
		while(it.hasNext())
		{
			User aUser  =(User)it.next();
			list = dao.retrieve(User.class.getName(), "systemIdentifier", aUser.getSystemIdentifier());
			if (list.size() != 0)
			{
				User coordinator = (User) list.get(0);
				coordinatorColl.add(coordinator);
				coordinator.getCollectionProtocolCollection().add(collectionProtocol);
				dao.update(coordinator);
			}
		}
		collectionProtocol.setUserCollection(coordinatorColl);
		
		dao.insert(collectionProtocol);
		it = collectionProtocol.getCollectionProtocolEventCollection().iterator();
		while(it.hasNext())
		{
			CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)it.next();
			collectionProtocolEvent.setCollectionProtocol(collectionProtocol);
			dao.insert(collectionProtocolEvent);
			
			Iterator srIt = collectionProtocolEvent.getSpecimenRequirementCollection().iterator();
			while(srIt.hasNext())
			{
				SpecimenRequirement specimenRequirement = (SpecimenRequirement)srIt.next();
				specimenRequirement.getCollectionProtocolEventCollection().add(collectionProtocolEvent);
				dao.insert(specimenRequirement);
			}
		}
	}
	
	/**
     * Updates the persistent object in the database.
     * @param session The session in which the object is saved.
     * @param obj The object to be updated.
     * @throws HibernateException Exception thrown during hibernate operations.
     * @throws DAOException 
     */
    protected void update(DAO dao,Object obj) throws DAOException
    {
    	
    }
}