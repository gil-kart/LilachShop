package org.lilachshop.server;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.lilachshop.entities.*;
import org.lilachshop.entities.Order;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public List<Catalog> getAllCatalogs(){ return getAllRecords(Catalog.class); }

    public List<Complaint> getAllComplaints(){    // should be gotten from a specific catalog,but currently DB has a single table of Items
        return getAllRecords(Complaint.class);
    }

    public void createCatalog(){
        Catalog catalog = App.generateCatalog();
        createOrUpdateSingleRecord(catalog);
    }
    public void createCatalogFromExistingOne(){
        List<Item> items = getAllRecords(Item.class);
        Catalog catalog = new Catalog();
        catalog.setItems(items);
        createOrUpdateSingleRecord(catalog);
    }

    public void fillDataBase(){
        // ---------------- creating 3 catalogs -------------
        Catalog catalog1 = App.generateCatalog();
        createOrUpdateSingleRecord(catalog1);
        Catalog catalog2 = App.generateCatalog();
        createOrUpdateSingleRecord(catalog2);
        Catalog catalog3 = App.generateCatalog();
        createOrUpdateSingleRecord(catalog3);

        Store store1 = new Store( "חיפה, דרך אבא חושי 1", "לילך חיפה", catalog1, new ArrayList<Complaint>(), new ArrayList<Order>());
        Store store2 = new Store( "הרצליה, דרך הים 41", "לילך הרצליה", catalog2, new ArrayList<Complaint>(), new ArrayList<Order>());
        Store store3 = new Store( "תל אביב, דיזינגוף 52", "לילך תל אביב", catalog3, new ArrayList<Complaint>(), new ArrayList<Order>());

        createOrUpdateSingleRecord(store1);
        createOrUpdateSingleRecord(store2);
        createOrUpdateSingleRecord(store3);

        addOredersToStoresStore(store1, store2, store3);
        addComplaintsToStores(store1, store2, store3);
        createOrUpdateSingleRecord(store1);
        createOrUpdateSingleRecord(store2);
        createOrUpdateSingleRecord(store3);


        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_EMPLOYEE, "ronaldo", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.CUSTOMER_SERVICE ,"yossi", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_MANAGER,"yaakov", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.CHAIN_MANAGER,"asaf", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.SYSTEM_MANAGER,"omer", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_EMPLOYEE,"ido", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE,"neta", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE,"ziv", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE,"malcy", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE,"messy", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE,"george", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE,"john", "1234"));

    }


    public void addComplaintsToStores(Store store1, Store store2, Store store3){
        Date dt = new Date();
        String time = dt.toString();

        Complaint complaint1 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בחיפה1", time, "");
        Complaint complaint2 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בחיפה2", time, "");
        Complaint complaint3 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בהרצליה3", time, "");

//        Complaint complaint4 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בהרצליה1", time, "");
//        Complaint complaint5 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בהרצליה2", time, "");
//        Complaint complaint6 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בהרצליה3", time, "");

//        Complaint complaint7 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בתל אביב1", time, "");
//        Complaint complaint8 = new Complaint(time, "פתוח", "אני כועס מאוד על השירות בתל אביב2", time, "");

        complaint1.setOrder(store1.getOrders().get(0));
        complaint1.setStore(store1);
        createOrUpdateSingleRecord(store1);
        complaint2.setOrder(store1.getOrders().get(1));
        complaint2.setStore(store1);
        createOrUpdateSingleRecord(store1);
        complaint3.setOrder(store2.getOrders().get(0));
        complaint3.setStore(store2);
        createOrUpdateSingleRecord(store2);

        createOrUpdateSingleRecord(complaint1);
        createOrUpdateSingleRecord(complaint2);
        createOrUpdateSingleRecord(complaint3);
//        createOrUpdateSingleRecord(complaint4);
//        createOrUpdateSingleRecord(complaint5);
//        createOrUpdateSingleRecord(complaint6);
//        createOrUpdateSingleRecord(complaint7);
//        createOrUpdateSingleRecord(complaint8);


        store1.addComplaint(complaint1);
        store1.addComplaint(complaint2);
        store1.addComplaint(complaint3);
//
//        store2.addComplaint(complaint4);
//        store2.addComplaint(complaint5);
//        store2.addComplaint(complaint6);
//
//        store3.addComplaint(complaint7);
//        store3.addComplaint(complaint8);
    }
    public void addOredersToStoresStore(Store store1, Store store2, Store store3){
        Date dt = new Date();
        String timeNow = dt.toString();
        List<Item> generalItemList = App.createItemList();
        // ---- add items to database ----
        for(Item item: generalItemList){
            createOrUpdateSingleRecord(item);
        }
//        createOrUpdateSingleRecord(generalItemList.get(0));
//        createOrUpdateSingleRecord(generalItemList.get(1));
//        createOrUpdateSingleRecord(generalItemList.get(2));
//        createOrUpdateSingleRecord(generalItemList.get(3));
//        createOrUpdateSingleRecord(generalItemList.get(4));
//        createOrUpdateSingleRecord(generalItemList.get(5));
//        createOrUpdateSingleRecord(generalItemList.get(6));
//        createOrUpdateSingleRecord(generalItemList.get(7));
//        createOrUpdateSingleRecord(generalItemList.get(8));
//        createOrUpdateSingleRecord(generalItemList.get(9));
//        createOrUpdateSingleRecord(generalItemList.get(10));
//        createOrUpdateSingleRecord(generalItemList.get(11));
//        createOrUpdateSingleRecord(generalItemList.get(12));
//        createOrUpdateSingleRecord(generalItemList.get(13));

        //----------- need to generate customers first! ------------
        // ---- create credit cards ----
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard("1234123412341234", timeNow,"123"));
        creditCards.add(new CreditCard("4321123412341234", timeNow,"821"));
        creditCards.add(new CreditCard("1111123412341234", timeNow,"347"));
//        CreditCard creditCard1 = new CreditCard("1234123412341234", timeNow,"123");
//        CreditCard creditCard2 = new CreditCard("4321123412341234", timeNow,"821");
//        CreditCard creditCard3 = new CreditCard("1111123412341234", timeNow,"347");

//        createOrUpdateSingleRecord(creditCard1);
//        createOrUpdateSingleRecord(creditCard2);
//        createOrUpdateSingleRecord(creditCard3);
        // ---- add credit cards to database ----
        // ---- save credit card to db ----

        // same for customers and so on ...

        Account account1 = new Account(timeNow,AccountType.STORE_ACCOUNT);
        Account account2 = new Account(timeNow,AccountType.CHAIN_ACCOUNT);
        Account account3 = new Account(timeNow,AccountType.ANNUAL_SUBSCRIPTION);
//        createOrUpdateSingleRecord(account1);
//        createOrUpdateSingleRecord(account2);
//        createOrUpdateSingleRecord(account3);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("gil", "1234","גיל קרטגינר","חיפה 32","0542494993", false,creditCards.get(0),new ArrayList<Order>(),store1, account1));
        customers.add(new Customer("yaron", "1111","ירון מלמד","חיפה 55","0542493123", false,creditCards.get(1),new ArrayList<Order>(),store1, account2));
        customers.add(new Customer("ziv", "4444","זיו קרטגינר","הרצליה 32","0542453293", false,creditCards.get(2),new ArrayList<Order>(),store2, account3));

//        Customer customer1 = new Customer("gil", "1234","גיל קרטגינר","חיפה 32","0542494993", false,creditCard1,new ArrayList<Order>(),store1);
//        Customer customer2 = new Customer("yaron", "1111","ירון מלמד","חיפה 55","0542493123", false,creditCard2,new ArrayList<Order>(),store1);
//        Customer customer3 = new Customer("ziv", "4444","זיו קרטגינר","הרצליה 32","0542453293", false,creditCard3,new ArrayList<Order>(),store2);

//        createOrUpdateSingleRecord(customer1);
//        createOrUpdateSingleRecord(customer2);
//        createOrUpdateSingleRecord(customer3);
        int i=0;


        for(CreditCard creditCard: creditCards){
            createOrUpdateSingleRecord(creditCard);
        }
        for (Customer customer:customers){
            createOrUpdateSingleRecord(customer);
            creditCards.get(i).setCustomer(customer);
            i++;
        }
        i=0;
        for(CreditCard creditCard: creditCards){
            creditCard.setCustomer(customers.get(i));
            i++;
        }

        // ---------------------------------------------------------
        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(generalItemList.get(0));

        itemList1.add(generalItemList.get(1));
        itemList1.add(generalItemList.get(2));
        itemList1.add(generalItemList.get(3));
        DeliveryDetails deliveryDetails1 = new DeliveryDetails(timeNow, "05429384384", "גיל", "חיפה 42");
        Order order1 = new Order(timeNow, "מזל טוב תתחדשי על הפרחים!", itemList1,100, 4, deliveryDetails1, null, null,customers.get(0));
        deliveryDetails1.setOrder(order1);
        List<Item> itemList2 = new ArrayList<>();
        itemList2.add(generalItemList.get(4));
        itemList2.add(generalItemList.get(5));
        itemList2.add(generalItemList.get(11));
        DeliveryDetails deliveryDetails2 = new DeliveryDetails(timeNow, "05429384384", "זיו", "חיפה, נווה שאנן 42");
        Order order2 = new Order(timeNow, "מזל טוב תתחדשו על הפרחים שלכם, הם יפים!", itemList2,200, 4, deliveryDetails2, null, null, customers.get(1));
        deliveryDetails2.setOrder(order2);
        createOrUpdateSingleRecord(deliveryDetails1);
        createOrUpdateSingleRecord(deliveryDetails2);
        createOrUpdateSingleRecord(order1);
        createOrUpdateSingleRecord(order2);

        store1.addOrder(order1);
        store1.addOrder(order2);
        customers.get(0).addOrderToList(order1);
        customers.get(1).addOrderToList(order2);



        List<Item> itemList3 = new ArrayList<>();
        itemList1.add(generalItemList.get(9));
        itemList1.add(generalItemList.get(8));
        itemList1.add(generalItemList.get(10));
        PickUpDetails pickUpDetails1 = new PickUpDetails(timeNow);
        Order order3 = new Order(timeNow, "", itemList3,400, 4, null, pickUpDetails1, null, customers.get(2));
        pickUpDetails1.setOrder(order3);
        customers.get(2).addOrderToList(order3);
        createOrUpdateSingleRecord(pickUpDetails1);
        createOrUpdateSingleRecord(order3);
        store2.addOrder(order3);

    }

    public List<Store> getStores(){return getAllRecords(Store.class);}
    public List<Employee> getEmployees(){
        return getAllRecords(Employee.class);
    }
    public List<Complaint> getComplaints(){
        return getAllRecords(Complaint.class);
    }
    public List<Customer> getCustomers(){return getAllRecords(Customer.class);}
    public List<Order> getOrders(){return getAllRecords(Order.class);}


    public void addCustomer(Customer customer){
        createOrUpdateSingleRecord(customer);
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
        configuration.addAnnotatedClass(ExampleEntity.class).addAnnotatedClass(ExampleEnum.class).addAnnotatedClass(Item.class).addAnnotatedClass(Catalog.class).addAnnotatedClass(Complaint.class).addAnnotatedClass(DeliveryDetails.class).addAnnotatedClass(PickUpDetails.class).addAnnotatedClass(Order.class).addAnnotatedClass(Store.class).addAnnotatedClass(User.class).addAnnotatedClass(Employee.class).addAnnotatedClass(Customer.class).addAnnotatedClass(CreditCard.class).addAnnotatedClass(Account.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
