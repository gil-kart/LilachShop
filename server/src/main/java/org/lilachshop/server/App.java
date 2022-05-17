package org.lilachshop.server;

import java.net.InetAddress;

import org.hibernate.SessionFactory;

/**
 * Hello world!
 */
public class App {
    private static LilachServer server;

    public static void main(String[] args) {
        try {
            int port = 3000;
            try {
                port = args.length > 0 ? Integer.parseInt(args[0]) : port;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            InetAddress IP = InetAddress.getLocalHost();
            String host_name = IP.getHostName();
            System.out.println("RUNNING SERVER ON " + host_name + "...");
            System.out.println("SERVER: INITIALIZING SERVER");
            server = new LilachServer(port);
            System.out.println("SERVER: LISTENING");
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
