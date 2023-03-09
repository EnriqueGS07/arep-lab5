package com.containerlab.labarep5;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static spark.Spark.get;
import static spark.Spark.port;

public class RoundRobin {

    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> {
            URL url = new URL("http://10.2.67.71:8080");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            String response = "";
            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_ACCEPTED) {
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()) {
                    response += scanner.nextLine();
                }
                scanner.close();
            }
            conn.disconnect();
            return response;
        });
    }

    private static int getPort(){
        if (System.getenv("PORT") !=  null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
