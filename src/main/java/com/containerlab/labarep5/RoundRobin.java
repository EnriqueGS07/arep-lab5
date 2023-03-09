package com.containerlab.labarep5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import spark.Spark;

import static spark.Spark.*;

public class RoundRobin {

    public static String roundrobin(String link){
        String[] ports = {"35001", "35002", "35003"};
        return link + ":" + ports[new Random().nextInt(3)];
    }


    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/");
        get("/", (req, res) -> {
            URL url = new URL(roundrobin("http://192.168.20.41"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            String response = "";

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
        post("/", (req,res) -> {
            URL url = new URL(roundrobin("http://192.168.20.41"));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();
            String request = req.body();
            byte[] data = request.getBytes("UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String input = "";
            StringBuffer response = new StringBuffer();
            while ((input = in.readLine()) != null){
                response.append(input);
            }
            in.close();
            conn.disconnect();
            return response.toString();
        });
    }

    private static int getPort(){
        if (System.getenv("PORT") !=  null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
