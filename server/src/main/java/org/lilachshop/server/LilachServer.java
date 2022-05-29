package org.lilachshop.server;

import org.lilachshop.entities.*;
import org.lilachshop.server.ocsf.AbstractServer;
import org.lilachshop.server.ocsf.ConnectionToClient;
import org.lilachshop.requests.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LilachServer extends AbstractServer {
    private static EntityFactory entityFactory;

    public LilachServer(Integer... port) {
        // default is 3000, otherwise needs to be specified.
        super(port.length > 0 ? port[0] : 3000);
        assert port.length < 2 : "Server should receive only a port.";
        try {
            entityFactory = EntityFactory.getEntityFactory();
        } catch (Exception e) {
            System.out.println("Unable to setup EntityFactory.");
            throw e;
        }
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        // todo: switch to a request class classifying.
        if (msg == null) {
            try {
                client.sendToClient("Exception: Message was null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (msg.getClass().equals(EmployeeLoginRequest.class)) {
            EmployeeLoginRequest request = (EmployeeLoginRequest) msg;
            String userName = request.getUserName();
            String password = request.getPassword();
            List<Employee> employees = entityFactory.getEmployees();
            for (Employee employee : employees) {
                if (employee.getUserName().equals(userName) &&
                        employee.getUserPassword().equals(password)) {
                    try {
                        client.sendToClient(employee);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                client.sendToClient("Incorrect details");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (msg.getClass().equals(SignUpRequest.class)) {
            SignUpRequest request = (SignUpRequest) msg;
            System.out.println("Server: got new signup request...");
            entityFactory.addCustomer(request.getCustomer());
        }

        if (msg.getClass().equals(CustomerLoginRequest.class)) {
            CustomerLoginRequest request = (CustomerLoginRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "customer login request" -> {
                        String requestPassword = request.getUserPassword();
                        String requestUserName = request.getUserName();
                        List<Customer> customers = entityFactory.getCustomers();
                        for (Customer customer : customers) {
                            if ((customer.getUserPassword().equals(requestPassword)) &&
                                    customer.getUserName().equals(requestUserName)) {
                                try {
                                    client.sendToClient(customer);
                                    return;

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        try {
                            client.sendToClient("client not exist");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (msg.getClass().equals(UserComplaintRequest.class)) {
            UserComplaintRequest request = (UserComplaintRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "post new complaint" -> {
                        System.out.println("posting new complaint:");
                        Complaint complaint = request.getComplaint();
                        entityFactory.createOrUpdateSingleRecord(complaint);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (msg.getClass().equals(SupportComplaintRequest.class)) {
            SupportComplaintRequest request = (SupportComplaintRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "get all complaints" -> {
                        List<Complaint> complaints = entityFactory.getAllComplaints();
                        client.sendToClient(complaints);
                    }
                    case "reply to customer complaint" -> {
                        Complaint complaint = request.getComplaint();
                        entityFactory.createOrUpdateSingleRecord(complaint);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        if(msg.getClass().equals(ComplaintRequest.class)){
//            ComplaintRequest request = (ComplaintRequest) msg;
//            String message_from_client = request.getRequest();
//            try{
//                switch (message_from_client){
//                    case "post new complaint" ->{
//                        System.out.println("posting new complaint:");
//                        Complaint complaint = request.getComplaint();
//                        entityFactory.createOrUpdateSingleRecord(complaint);
//                        System.out.println(complaint.getContent());
//                    }
//                    case "get all complaints" ->{
//                        List<Complaint> complaints = entityFactory.getAllComplaints();
//                        client.sendToClient(complaints);
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
        if (msg.getClass().equals(CatalogRequest.class)) {
            CatalogRequest request = (CatalogRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "get catalog" -> {
                        List<Item> items = entityFactory.getAllItems();
                        client.sendToClient(new LinkedList<Item>(items));
                        System.out.println("catalog was sent!");
                        for (Item item : items) {
                            System.out.println(item);
                        }
                    }
                    default -> {
                        client.sendToClient("request does not exist");
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed sending reply to client.");
                e.printStackTrace();
            }
        }
        // debug request
        if (msg.getClass().equals(DebugRequest.class)) {
            DebugRequest request = (DebugRequest) msg;
            String message_from_client = request.getRequest();
            try {
                switch (message_from_client) {
                    case "example message" -> {
                        System.out.println("Server: Received a 'example message' from client.");
                        System.out.println("Server: Sending a reply!");
                        client.sendToClient("This is a reply from LilachServer!");
                        System.out.println("Server: Message sent to client.");
                    }
                    case "write entity" -> {
                        System.out.println("Server: Writing new example entity!");
                        ExampleEntity exampleEntity = new ExampleEntity(ExampleEnum.TYPE1);
                        entityFactory.createExampleEntity(exampleEntity);
                        client.sendToClient("This is a reply from LilachServer!");
                    }
                    case "get all entities" -> client.sendToClient(entityFactory.getAllExampleEntities());

                    case "update entity1" -> {
                        int id_key = request.getIDToUpdate();
                        ExampleEnum exampleEnumToUpdate = request.getUpdateToEnum();
                        entityFactory.updateExampleEntityEnumByID(id_key, exampleEnumToUpdate);
                        client.sendToClient("This is a reply from LilachServer!");
                    }

                    case "write catalog" -> {
//                        entityFactory.createCatalog();
//                        entityFactory.createCatalogFromExistingOne();
//                        entityFactory.fillDataBase();
                        List<Store> stores = entityFactory.getStores();
                        List<Customer> customers = entityFactory.getCustomers();
                        List<Employee> employees = entityFactory.getEmployees();
                        List<Complaint> complaints = entityFactory.getComplaints();
                        List<Order> orders = entityFactory.getOrders();
                        //todo: make successful queries from database!
//                        List<Store> stores = entityFactory.getStores();
//                        Store store1 = stores.get(0);
//                        List<Order> orders = store1.getOrders();
//                        List<Complaint> store1Complaints = store1.getComplaints();
//                        System.out.println(store1Complaints.get(0).getContent());

                        client.sendToClient("Catalog is created!");
                    }

                    case "get all items" -> {
                        entityFactory.getAllItems();
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed sending reply to client.");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void clientConnected(ConnectionToClient client) {
        System.out.println(client.toString() + "connected.");
    }

    @Override
    protected synchronized void clientDisconnected(ConnectionToClient client) {
        System.out.println("Client disconnected.");
    }
}
