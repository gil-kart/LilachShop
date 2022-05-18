package org.lilachshop.server;

import org.junit.FixMethodOrder;

import org.junit.runners.MethodSorters;
import org.lilachshop.entities.ExampleEntity;
import org.lilachshop.entities.ExampleEnum;
import org.lilachshop.server.ocsf_client_test.AbstractClient;
import org.lilachshop.requests.DebugRequest;
import org.lilachshop.commonUtils.Utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {
    static int port_addition = 1;

    static private class TempClient extends AbstractClient {
        private String result = null;
        private List<ExampleEntity> result_entities;

        public TempClient(int port_addition) {
            super("localhost", 3000 + port_addition);
            System.out.println("Client: Client setup complete.");
        }

        @Override
        protected void handleMessageFromServer(Object msg) {
            if (Objects.equals(Utilities.getClassType(msg), "List<ExampleEntity>"))
                result_entities = (List<ExampleEntity>) msg;    // todo: should be sent through eventbus to PANEL. not like this!
            result = "ok";
        }

        public String getResult() {
            if (result == null)
                throw new RuntimeException("Server failed to respond");
            return result;
        }
    }

    static private class ServerBooter implements Runnable {
        private final int port_addition;

        public ServerBooter(int port_addition) {
            super();
            this.port_addition = port_addition;
        }

        @Override
        public void run() {
            System.out.println("Server: Setting up server...");
            LilachServer lilachServer = new LilachServer(3000 + port_addition);
            System.out.println("Server: Done.");
            try {
                System.out.println("Server: Listening!");
                lilachServer.listen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1_shouldAnswerWithTrue() {
        assertTrue(true);
    }


    @Test
    public void test2_shouldSendRequestAndBeAnswered() throws IOException, InterruptedException {
        Thread thread = new Thread(new ServerBooter(port_addition), "Server");
        thread.start(); // run server

        Thread.sleep(5000); // Hibernate setup is long...


        TempClient tempClient = new TempClient(port_addition++);
        System.out.println("Client: Connecting to server...");
        tempClient.openConnection();
        System.out.println("Client: Connected to server.");
        System.out.println("Client: Sending request to server...");

        tempClient.sendToServer(new DebugRequest("example message"));

        System.out.println("Client: Done.");
        Thread.sleep(1000);
        tempClient.closeConnection();
        assertEquals("ok", tempClient.getResult());
    }

    @Test
    public void test3_shouldCreateExampleEntity() throws IOException, InterruptedException {
        Thread thread = new Thread(new ServerBooter(port_addition), "Server" + port_addition);
        thread.start(); // run server

        Thread.sleep(5000); // Hibernate setup is long...


        TempClient tempClient = new TempClient(port_addition++);
        System.out.println("Client: Connecting to server...");
        tempClient.openConnection();
        System.out.println("Client: Connected to server.");
        System.out.println("Client: Sending request to server...");

        tempClient.sendToServer(new DebugRequest("write entity"));

        System.out.println("Client: Done.");
        Thread.sleep(1000);
        tempClient.closeConnection();
        assertEquals("ok", tempClient.getResult());
        System.out.println("Note: Check debugdb for changes manually!");
    }


    @Test
    public void test4_shouldGetAllEntities() throws IOException, InterruptedException {
        Thread thread = new Thread(new ServerBooter(port_addition), "Server" + port_addition);
        thread.start(); // run server

        Thread.sleep(5000); // Hibernate setup is long...


        TempClient tempClient = new TempClient(port_addition++);
        System.out.println("Client: Connecting to server...");
        tempClient.openConnection();
        System.out.println("Client: Connected to server.");
        System.out.println("Client: Sending request to server...");

        tempClient.sendToServer(new DebugRequest("get all entities"));

        System.out.println("Client: Done.");
        Thread.sleep(1000); // wait for result - in real case scenario should be sent by EventBus to destination.
        tempClient.closeConnection();
        System.out.println("Printing table contents:");
        for (ExampleEntity ee :
                tempClient.result_entities) {
            System.out.println(ee);
        }
    }

    @Test
    public void test5_shouldUpdateEntityWithID1ToEnumType3() throws IOException, InterruptedException {
        Thread thread = new Thread(new ServerBooter(port_addition), "Server" + port_addition);
        thread.start(); // run server

        Thread.sleep(5000); // Hibernate setup is long...


        TempClient tempClient = new TempClient(port_addition++);
        System.out.println("Client: Connecting to server...");
        tempClient.openConnection();
        System.out.println("Client: Connected to server.");
        System.out.println("Client: Sending request to server...");

        DebugRequest updateRequest = new DebugRequest("update entity1", 1, ExampleEnum.TYPE3);

        tempClient.sendToServer(updateRequest);

        System.out.println("Client: Done.");
        Thread.sleep(3000);
        tempClient.closeConnection();
        assertEquals("ok", tempClient.getResult());
        System.out.println("Note: Check debugdb for changes manually!");
    }

    @Test
    public void test6_shouldShowTYPE3() throws IOException, InterruptedException {
        test4_shouldGetAllEntities();
    }
}
