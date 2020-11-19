package coffeeshop;

import coffeeshop.models.CatalogItem;
import coffeeshop.models.Order;
import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CoffeeShop {

    private static String CATALOG_ITEMS_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/beverage";
    private static String ORDERS_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/order";
    private static HashMap<String,Double> codePriceMap;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws MalformedURLException {

        // Catalog items response
        String response = readJsonFromWebService(CATALOG_ITEMS_URL);

        // Orders response
        String response2 = readJsonFromWebService(ORDERS_URL);

        // Parsing an array of JSON objects
        Gson g = new Gson();
        CatalogItem[] responseItems = g.fromJson(response.toString(), CatalogItem[].class);
        // Create a list where the catalog items will be saved
        List<CatalogItem> catalogItems = new ArrayList();
        // Create HashMap to save the codes and their prices
         codePriceMap = new HashMap<String,Double>();
        for (CatalogItem item : responseItems) {
            // Save response items in an array
            catalogItems.add(item);
            codePriceMap.put(item.getCode(), item.getCost());
        }

        Order[] responseOrders = g.fromJson(response2.toString(), Order[].class);
        List<Order> orders = new ArrayList<>();
        for (Order order : responseOrders) {
            orders.add(order);
        }

        PrintWriter out = openWriter("orders.txt");


        // Calculates total cost of an order by id
        calculateCostOfOrderById(orders, out);

        // Iterates through all orders
        calculateEachOrder(orders, out);


        out.println("Order Id | Cost");
        for (Order order : orders) {
            writeOrder(order, out);
        }
        out.close();

    }



    static String readJsonFromWebService(String url) throws MalformedURLException {
        try {
            // Web service URL
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void calculateEachOrder(List<Order> orders, PrintWriter out) {
        double totalSumOfAllOrders = 0;
        for (Order order : orders) {
            double orderSum = calculateSingleOrder(order);
            // Prints the total sum of the order with two decimals
            System.out.printf("Order with id %d has total %.2f\n", order.getId(), orderSum);
           // Write code which will write to a txt file
            totalSumOfAllOrders += orderSum;
        }
        System.out.println("*********************");
        System.out.printf("**** TOTAL SUM: %.2f ****\"\n", totalSumOfAllOrders);
        out.printf("TOTAL SUM: %.2f \n", totalSumOfAllOrders);
    }

    public static double charge(boolean selected, String code) {
        if (selected)
            return codePriceMap.get(code);
        return 0;
    }

    public static double calculateSingleOrder(Order order) {
        return (charge(order.isEspresso_classic(), "espresso_classic")
                + charge(order.isEspresso_arabica(), "espresso_arabica")
                + charge(order.isEspresso_decaf(), "espresso_decaf")
                + charge(order.isMilk(), "milk")
                + charge(order.isSoy(), "soy")
                + charge(order.isCaramel_syrop(), "caramel_syrop")
                + charge(order.isAlmond_milk(), "almond_milk"));
    }

    public static void calculateCostOfOrderById(List<Order> orders, PrintWriter out) {
        int selectedOrderId = 0;
        while(true) {
            System.out.println("Please select an order ID");

            if (scanner.hasNextInt() == true) {
                selectedOrderId = scanner.nextInt();
                break;
            }
            scanner.next();
            System.out.println("Invalid input.");
        }
        if (selectedOrderId == 0 || selectedOrderId > orders.size()) {
            System.out.println("WE DON'T HAVE THIS ID....");
            calculateCostOfOrderById(orders, out);
        } else {

            Order selectedOrder = null;
            for (Order order : orders) {
                if (order.getId() == selectedOrderId) {
                    selectedOrder = order;
                }
            }
//            System.out.printf("The cost of order with id: %d is equal to: %.2f \n", selectedOrderId, calculateSingleOrder(selectedOrder));
            out.printf("The cost of order with id: %d is equal to: %.2f \n", selectedOrderId, calculateSingleOrder(selectedOrder));
        }

    }

    private static PrintWriter openWriter(String fileName) {
        try {
            File file = new File("fileName");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);
            return out;
        }
        catch (IOException e) {
            System.out.println("I/O Exception");
            System.exit(0);
        }
        return null;
    }

    private static void writeOrder(Order order, PrintWriter out) {
        String line = String.format("|%6d  | %5.2f eur |", order.getId(), calculateSingleOrder(order));
        out.println(line);
    }



}
