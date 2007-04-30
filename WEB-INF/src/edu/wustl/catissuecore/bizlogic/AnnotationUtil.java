/**
 *<p>Title: </p>
 *<p>Description:  </p>
 *<p>Copyright:TODO</p>
 *@author 
 *@version 1.0
 */

package edu.wustl.catissuecore.bizlogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import edu.common.dynamicextensions.domain.DomainObjectFactory;
import edu.common.dynamicextensions.domaininterface.AssociationInterface;
import edu.common.dynamicextensions.domaininterface.EntityGroupInterface;
import edu.common.dynamicextensions.domaininterface.EntityInterface;
import edu.common.dynamicextensions.domaininterface.RoleInterface;
import edu.common.dynamicextensions.domaininterface.databaseproperties.ConstraintPropertiesInterface;
import edu.common.dynamicextensions.entitymanager.EntityManager;
import edu.common.dynamicextensions.entitymanager.EntityManagerInterface;
import edu.common.dynamicextensions.exception.DynamicExtensionsApplicationException;
import edu.common.dynamicextensions.exception.DynamicExtensionsSystemException;
import edu.common.dynamicextensions.util.global.Constants.AssociationDirection;
import edu.common.dynamicextensions.util.global.Constants.AssociationType;
import edu.common.dynamicextensions.util.global.Constants.Cardinality;
import edu.wustl.cab2b.server.cache.EntityCache;
import edu.wustl.cab2b.server.path.PathConstants;
import edu.wustl.cab2b.server.path.PathFinder;
import edu.wustl.catissuecore.util.querysuite.EntityCacheFactory;
import edu.wustl.common.dao.DAOFactory;
import edu.wustl.common.dao.HibernateDAO;
import edu.wustl.common.util.dbManager.DBUtil;
import edu.wustl.common.util.global.Constants;

/**
 * @author vishvesh_mulay
 *
 */
public class AnnotationUtil
{

    /**
     * @param staticEntityId
     * @param dynamicEntityId
     * @return
     * @throws DynamicExtensionsSystemException
     * @throws DynamicExtensionsApplicationException
     */
    public static synchronized Long addAssociation(Long staticEntityId, Long dynamicEntityId)
            throws DynamicExtensionsSystemException,
            DynamicExtensionsApplicationException
    {
        EntityManagerInterface entityManager = EntityManager.getInstance();
        EntityInterface staticEntity = EntityCacheFactory.getInstance()
                .getEntityById(staticEntityId);
        EntityInterface dynamicEntity = (entityManager
                .getContainerByIdentifier(dynamicEntityId.toString()))
                .getEntity();
        dynamicEntity
                .addEntityGroupInterface((EntityGroupInterface) staticEntity
                        .getEntityGroupCollection().iterator().next());
        Collection<AssociationInterface> associationCollection = dynamicEntity
        .getAssociationCollection();
        for (AssociationInterface associationInteface : associationCollection)
        {
            associationInteface.getTargetEntity().addEntityGroupInterface((EntityGroupInterface) staticEntity
                        .getEntityGroupCollection().iterator().next());
        }
        String roleName = staticEntityId.toString().concat("_").concat(
                dynamicEntityId.toString());
        RoleInterface sourceRole = getRole(AssociationType.ASSOCIATION,
                roleName, Cardinality.ZERO, Cardinality.ONE);
        RoleInterface targetRole = getRole(AssociationType.ASSOCIATION,
                roleName, Cardinality.ZERO, Cardinality.MANY);
        AssociationInterface association = getAssociation(dynamicEntity,
                AssociationDirection.SRC_DESTINATION, roleName, sourceRole,
                targetRole);
        ConstraintPropertiesInterface constraintProperties = getConstraintProperties(
                staticEntity, dynamicEntity);
        association.setConstraintProperties(constraintProperties);
        staticEntity.addAssociation(association);
        Long start = new Long(System.currentTimeMillis());
        staticEntity = EntityManager.getInstance().persistEntityMetadata(
                staticEntity, true);
        Long end = new Long(System.currentTimeMillis());
        System.out.println("Time required to persist one entity is "
                + (end - start) / 1000 + "seconds");
        EntityManager.getInstance().addAssociationColumn(association);
//        Collection collection = EntityManager.getInstance().getAssociation(
//                staticEntity.getName(), roleName);
//        association = (AssociationInterface) collection.iterator().next();
        Collection<AssociationInterface> staticEntityAssociation = staticEntity.getAssociationCollection();
        for (AssociationInterface tempAssociation : staticEntityAssociation)
        {
            if (tempAssociation.getName().equals(association.getName()))
            {
                association = tempAssociation;
                break;
            }
        }
        
        start = new Long(System.currentTimeMillis());
        AnnotationUtil.addPathsForQuery(staticEntityId, dynamicEntity.getId(),
                association.getId());
        associationCollection = dynamicEntity
                .getAssociationCollection();
        for (AssociationInterface associationInteface : associationCollection)
        {
            AnnotationUtil.addPathsForQuery(dynamicEntity.getId(),
                    associationInteface.getTargetEntity().getId(),
                    associationInteface.getId());
        }
        end = new Long(System.currentTimeMillis());
        System.out.println("Time required to add complete paths is"
                + (end - start) / 1000 + "seconds");
        start = new Long(System.currentTimeMillis());
        EntityCache.getInstance().refreshCache();
        PathFinder.getInstance().refreshCache();
        end = new Long(System.currentTimeMillis());
        System.out.println("Time required to refresh cache is "
                + (end - start) / 1000 + "seconds");
        return association.getId();
    }

    /**
     * @param staticEntity
     * @param dynamicEntity
     * @return
     */
    private static ConstraintPropertiesInterface getConstraintProperties(
            EntityInterface staticEntity, EntityInterface dynamicEntity)
    {
        ConstraintPropertiesInterface cp = DomainObjectFactory.getInstance()
                .createConstraintProperties();
        cp.setName(dynamicEntity.getTableProperties().getName());
        cp.setTargetEntityKey("DYEXTN_AS_" + staticEntity.getId().toString()
                + "_" + dynamicEntity.getId().toString());
        return cp;
    }

    /**
     * @param targetEntity
     * @param associationDirection
     * @param assoName
     * @param sourceRole
     * @param targetRole
     * @return
     */
    private static AssociationInterface getAssociation(
            EntityInterface targetEntity,
            AssociationDirection associationDirection, String assoName,
            RoleInterface sourceRole, RoleInterface targetRole)
    {
        AssociationInterface association = DomainObjectFactory.getInstance()
                .createAssociation();
        association.setTargetEntity(targetEntity);
        association.setAssociationDirection(associationDirection);
        association.setName(assoName);
        association.setSourceRole(sourceRole);
        association.setTargetRole(targetRole);
        return association;
    }

    /**
     * @param associationType associationType
     * @param name name
     * @param minCard  minCard
     * @param maxCard maxCard
     * @return  RoleInterface
     */
    private static RoleInterface getRole(AssociationType associationType,
            String name, Cardinality minCard, Cardinality maxCard)
    {
        RoleInterface role = DomainObjectFactory.getInstance().createRole();
        role.setAssociationsType(associationType);
        role.setName(name);
        role.setMinimumCardinality(minCard);
        role.setMaximumCardinality(maxCard);
        return role;
    }

    /**
     * @param staticEntityId
     * @param dynamicEntityId
     * @param deAssociationID
     */
    private static void addPathsForQuery(Long staticEntityId,
            Long dynamicEntityId, Long deAssociationID)
    {
        Long maxPathId = getMaxId("path_id", "path");
        maxPathId += 1;
        insertNewPaths(maxPathId, staticEntityId, dynamicEntityId,
                deAssociationID);
    }

    /**
     * @param maxPathId
     * @param staticEntityId
     * @param dynamicEntityId
     * @param deAssociationID
     */
    private static void insertNewPaths(Long maxPathId, Long staticEntityId,
            Long dynamicEntityId, Long deAssociationID)
    {
        StringBuffer query = new StringBuffer();
        Long intraModelAssociationId = getMaxId("ASSOCIATION_ID", "ASSOCIATION");
        intraModelAssociationId += 1;
        try
        {
            Connection conn = DBUtil.getConnection();

            String associationQuery = "insert into ASSOCIATION (ASSOCIATION_ID, ASSOCIATION_TYPE) values ("
                    + intraModelAssociationId
                    + ","
                    + PathConstants.INTRA_MODEL_ASSOCIATION_TYPE + ")";
            String intraModelQuery = "insert into INTRA_MODEL_ASSOCIATION (ASSOCIATION_ID, DE_ASSOCIATION_ID) values ("
                    + intraModelAssociationId + "," + deAssociationID + ")";
            String directPathQuery = "insert into PATH (PATH_ID, FIRST_ENTITY_ID,INTERMEDIATE_PATH, LAST_ENTITY_ID) values ("
                    + maxPathId
                    + ","
                    + staticEntityId
                    + ","
                    + intraModelAssociationId + "," + dynamicEntityId + ")";

            List<String> list = new ArrayList<String>();
            list.add(associationQuery);
            list.add(intraModelQuery);
            list.add(directPathQuery);

            executeQuery(conn, list);
            maxPathId += 1;
            addIndirectPaths(maxPathId, staticEntityId, dynamicEntityId,
                    intraModelAssociationId, conn);
            conn.commit();
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * @param maxPathId
     * @param staticEntityId
     * @param dynamicEntityId
     * @param intraModelAssociationId
     * @param conn
     * @throws SQLException
     */
    private static void addIndirectPaths(Long maxPathId, Long staticEntityId,
            Long dynamicEntityId, Long intraModelAssociationId, Connection conn)
            throws SQLException
    {
        ResultSet resultSet = getIndirectPaths(conn, staticEntityId);
        String query = "insert into path (PATH_ID, FIRST_ENTITY_ID,INTERMEDIATE_PATH, LAST_ENTITY_ID) values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        while (resultSet.next())
        {

            Long firstEntityId = resultSet.getLong(1);
            String path = resultSet.getString(2);
            path = path.concat("_").concat(intraModelAssociationId.toString());

            statement.setLong(1, maxPathId);
            maxPathId++;
            statement.setLong(2, firstEntityId);
            statement.setString(3, path);
            statement.setLong(4, dynamicEntityId);
            statement.execute();
            statement.clearParameters();
        }
        resultSet.close();
        statement.close();

    }

    /**
     * @param conn
     * @param staticEntityId
     * @return
     * @throws SQLException
     */
    private static ResultSet getIndirectPaths(Connection conn,
            Long staticEntityId) throws SQLException
    {
        String query = "select FIRST_ENTITY_ID,INTERMEDIATE_PATH from path where LAST_ENTITY_ID="
                + staticEntityId;
        java.sql.PreparedStatement statement = conn.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return resultSet;
    }

    /**
     * @param conn
     * @param queryList
     * @throws SQLException
     */
    private static void executeQuery(Connection conn, List<String> queryList)
            throws SQLException
    {
        Statement statement = conn.createStatement();
        for (String query : queryList)
        {
            statement.execute(query);
        }
        statement.close();
    }

    /**
     * @param columnName
     * @param tableName
     * @return
     */
    private static Long getMaxId(String columnName, String tableName)
    {
        String query = "select max(" + columnName + ") from " + tableName;
        HibernateDAO hibernateDAO = (HibernateDAO) DAOFactory.getInstance()
                .getDAO(Constants.HIBERNATE_DAO);
        try
        {
            Connection conn = DBUtil.getConnection();
            java.sql.PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long maxId = resultSet.getLong(1);
            return maxId;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
