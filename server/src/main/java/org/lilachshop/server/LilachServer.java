package org.lilachshop.server;

import org.lilachshop.entities.ExampleEntity;
import org.lilachshop.entities.ExampleEnum;
import org.lilachshop.server.ocsf.AbstractServer;
import org.lilachshop.server.ocsf.ConnectionToClient;
import org.lilachshop.requests.*;

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
