/**
 *<p>Title: </p>
 *<p>Description:  </p>
 *<p>Copyright:TODO</p>
 *@author 
 *@version 1.0
 */

package edu.wustl.catissuecore.annotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.common.bizlogic.DefaultBizLogic;
import edu.wustl.common.util.dbManager.DAOException;

public class ParticipantAnnotationCondition implements ICPCondition
{

    public List getCollectionProtocolList(Long entityInstanceId)
    {
        List<Long> annotationsList = new ArrayList<Long>();
        DefaultBizLogic bizLogic = new DefaultBizLogic();
        List objectList = new ArrayList();
        try
        {
            if (entityInstanceId != null || !entityInstanceId.equals(""))
                objectList = bizLogic.retrieve(Participant.class.getName(),
                        "id", entityInstanceId);
            if (objectList != null && !objectList.isEmpty())
            {

                Participant participant = (Participant) objectList.get(0);
                Iterator it = participant
                        .getCollectionProtocolRegistrationCollection()
                        .iterator();
                while (it.hasNext())
                {
                    CollectionProtocolRegistration cpReg = (CollectionProtocolRegistration) it.next();                    
                    if (cpReg != null && cpReg.getCollectionProtocol() != null
                            && cpReg.getCollectionProtocol().getId() != null)
                        annotationsList.add(cpReg.getCollectionProtocol()
                                .getId());

                }

            }
        }
        catch (DAOException e)
        {
        }

        return annotationsList;
    }

}
