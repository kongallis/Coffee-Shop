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

    }

}
