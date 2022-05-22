package org.lilachshop.server;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.lilachshop.entities.*;
import org.lilachshop.requests.ComplaintRequest;

import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 */
public class EntityFactory {
    private static EntityFactory ef = null;    // Singleton, creating a SessionFactory is a heavy operation!
    private final SessionFactory sf;

    private EntityFactory() {
        sf = getSessionFactory();
    }

    public static EntityFactory getEntityFactory() {
        if (ef == null) {
            ef = new EntityFactory();
        }
        return ef;
    }

    public List<Item> getAllItems(){    // should be gotten from a specific catalog,but currently DB has a single table of Items
        return getAllRecords(Item.class);
    }

    public List<Complaint> getAllComplaints(){    // should be gotten from a specific catalog,but currently DB has a single table of Items
        return getAllRecords(Complaint.class);
    }

    public void createCatalog(){
        Catalog catalog = App.generateCatalog();
        createOrUpdateSingleRecord(catalog);
    }


    /*
     *****************************************   Entity Methods   ******************************************************
     */

    // Usage of query API
    public List<ExampleEntity> getAllExampleEntities() {
        return getAllRecords(ExampleEntity.class);
    }


    // Usage of query API
    public ExampleEntity getSingleExampleEntityRecord(int entityID) {
        return getSingleRecord(ExampleEntity.class, "id", entityID);
    }

    // Usage of query API
    public void createExampleEntity(ExampleEntity exampleEntity) {
        createOrUpdateSingleRecord(exampleEntity);
    }

    // Usage of query API
    public void updateExampleEntityEnumByID(int entityID, ExampleEnum enumToSet) {
        updateRecordField(ExampleEntity.class, "exampleEnum", enumToSet, entityID, "id");
    }

    // Overload
    public void updateExampleEntityEnumByID(ExampleEntity toUpdateEntity) {
        updateRecordField(ExampleEntity.class, "exampleEnum", toUpdateEntity.getExampleEnum(), toUpdateEntity.getId(), "entity_id");
    }

    // Usage of query API
    public void deleteExampleEntityByID(int entityID) {
        deleteRecord(ExampleEntity.class, "entity_id", entityID);
    }



    /*
     ******************************************************************************************************************
     */


    /*
     *****************************************   Utilities   **********************************************************
     */


    /**
     * Get a list of all table records of a certain entity
     *
     * @param entityClass Entity metamodel
     * @return List of entities
     */
    private <T> List<T> getAllRecords(Class<T> entityClass) {
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);

        Root<T> root = cq.from(entityClass);
        cq.select(root);

        Query<T> query = session.createQuery(cq);
        List<T> result = new LinkedList<>(query.getResultList());
        session.close();
        return result;
    }

    private <T, S> T getSingleRecord(Class<T> entityClass, String keyColumn, S key) { // todo: test this
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get(keyColumn), key));

        Query<T> query = session.createQuery(cq);
        List<T> record_list = query.getResultList();
        return record_list.isEmpty() ? null : record_list.get(0);
    }


    /**
     * Add a single record.
     *
     * @param entityToCreate Entity record to add
     * @param <T>            Entity type
     */
    <T> void createOrUpdateSingleRecord(T entityToCreate) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(entityToCreate);

        transaction.commit();
        session.close();
    }

    /**
     * Update specific attribute in a record.
     *
     * @param <T>                Entity class type
     * @param <S>                Value type of to be set type
     * @param <K>                Value type of object to be checked
     * @param entityClass        Entity metamodel
     * @param mutateAttribColumn Attribute field column name to update
     * @param valueToSet         The value to be set
     * @param key                Update where key matches
     * @param keyColumn          Key column name to match where to update
     */
    private <T, S, K> void updateRecordField(Class<T> entityClass, String mutateAttribColumn, S valueToSet, K key, String keyColumn) {
        Session session = sf.openSession(); // todo: add exception handling
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<T> cu = cb.createCriteriaUpdate(entityClass);
        Root<T> root = cu.from(entityClass);
        cu.set(root.get(mutateAttribColumn), valueToSet);
        cu.where(cb.equal(root.get(keyColumn), key));

        Transaction transaction = session.beginTransaction();
        session.createQuery(cu).executeUpdate();
        transaction.commit();
        session.close();
    }

    /**
     * Delete specific record.
     *
     * @param entityClass Entity metamodel
     * @param keyColumn   Key column name to match where to delete
     * @param key         Delete where key matches
     * @param <T>         Entity class
     * @param <S>         Value type of object to be checked
     */
    private <T, S> void deleteRecord(Class<T> entityClass, String keyColumn, S key) {   //todo: test this
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
        Root<T> root = cd.from(entityClass);
        cd.where(cb.equal(root.get(keyColumn), key));

        Transaction transaction = session.beginTransaction();
        session.createQuery(cd).executeUpdate();
        transaction.commit();
        session.close();
    }


    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ExampleEntity.class).addAnnotatedClass(ExampleEnum.class).addAnnotatedClass(Item.class).addAnnotatedClass(Catalog.class).addAnnotatedClass(Complaint.class);//.addAnnotatedClass(Item.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
