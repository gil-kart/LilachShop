package org.lilachshop.server;
import org.lilachshop.entities.Item;
import org.lilachshop.entities.Catalog;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

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

    static Catalog generateCatalog() {

        Catalog catalog = new Catalog();
        catalog.setItems(createItemList());
        return catalog;

    }

    public static List<Item> createItemList() {
        Item item;
        List<Item> itemList = new ArrayList<>();
        //    for (int i = 0; i < 5; i++) {
        String base_path = "/images/";
        item = new Item("סחלב קורל", 160, base_path + "sahlav_coral.jpg");
        itemList.add(item);
        item = new Item("ורד ענבר", 120, base_path + "vered_inbar.jpg");
        itemList.add(item);
        item = new Item("סחלב לבן", 140, base_path + "sahlav_lavan.jpg");
        itemList.add(item);
        item = new Item("נרקיס חצוצרה", 110, base_path + "narkis_hatsostra.jpg");
        itemList.add(item);
        item = new Item("רקפות", 100, base_path + "cyclamen.jpg");
        itemList.add(item);
        item = new Item("קקטוס", 70, base_path + "cactus.jpg");
        itemList.add(item);
        item = new Item("תורמוס", 200, base_path + "lupins.jpg");
        itemList.add(item);
        item = new Item("חמניות", 170, base_path + "heilanthus.jpg");
        itemList.add(item);
        item = new Item("חינניות", 125, base_path + "daisy.jpg");
        itemList.add(item);
        item = new Item("אדמוניות", 190, base_path + "peonybouquet.jpg");
        itemList.add(item);
        item = new Item("צבעוני", 175, base_path + "orange_tulips.jpg");
        itemList.add(item);
        item = new Item("פרג", 180, base_path + "poppy.jpg");
        itemList.add(item);
        item = new Item("סוקולנטים", 100, base_path + "succulents.jpg");
        itemList.add(item);
        item = new Item("שושן", 90, base_path + "lily.jpg");
        itemList.add(item);

        // }
        return itemList;
    }
}
