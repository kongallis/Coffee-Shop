package coffeeshop;

import coffeeshop.models.CatalogItem;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;

public class CoffeeShop {
    
    private static String CATALOG_ITMES_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/beverage";
    private static String ORDERS_URL = "http://5e28257b120f820014bf415a.mockapi.io/api/v1/order";

    public static void main(String[] args) throws MalformedURLException {

        // Step 1: Getting JSON response into String in Java
        String response = readJsonFromWebService(CATALOG_ITMES_URL);

        // Step 2: Parsing an array of JSON objctes
        Gson g2 = new Gson();
        CatalogItem[] responseItems = g2.fromJson(response.toString(), CatalogItem[].class);
        List<CatalogItem> catalogItems = new ArrayList();
        for (CatalogItem item : responseItems) {
            // Save response items in an array
            catalogItems.add(item);
        }

        for (CatalogItem item : catalogItems) {
            System.out.println(item.getCost());
        }

        

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
            // TODO
        }
        return "";
    }

}
