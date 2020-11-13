package coffeeshop;

import coffeeshop.models.CatalogItem;
import coffeeshop.models.Order;
import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CoffeeShop {

    private static String CATALOG_ITEMS_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/beverage";
    private static String ORDERS_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/order";
    private static HashMap<String,Double> codePriceMap;

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

        // Iterates through all orders
        calculateEachOrder(orders);

        System.out.println("*********************");
        // Calculates total cost of an order by id
        System.out.println("Order id: " + orders.get(1).getId() + " has total cost: " + calculateSingleOrder(orders.get(1)));





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

    public static void calculateEachOrder(List<Order> orders) {
        double totalSumOfAllOrders = 0;
        for (Order order : orders) {
            double orderSum = calculateSingleOrder(order);
            // Prints the total sum of the order with two decimals
            System.out.printf("Order with id %d has total %.2f\n", order.getId(), orderSum);
            totalSumOfAllOrders += orderSum;
        }
        System.out.printf("**** TOTAL SUM: %.2f ****\"\n", totalSumOfAllOrders);
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



}
