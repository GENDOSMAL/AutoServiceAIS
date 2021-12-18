package autoService.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Connection {
    public static String getBase(){
        return "http://localhost:8080/auto_service/";
    }
    public static Object getServices(boolean blag){
         String url = getBase()+"services";
        ObservableList<ServiceStr> serviceData = FXCollections.observableArrayList();
         String result = get(url);
        if(result!=null){
            if (!result.equals("[]")) {
                JSONArray jsonArray = new JSONArray(result);
                if (blag){
                    for (int i = 0; i < jsonArray.length(); i++) {
                        serviceData.add(new ServiceStr(jsonArray.getJSONObject(i).getString("type"),
                                jsonArray.getJSONObject(i).getInt("duration"),
                                jsonArray.getJSONObject(i).getJSONObject("category").getString("type"))
                        );
                    }
                    return serviceData;
                }
                else{
                    return jsonArray;
                }

            }


        }
        return null;
    }
    public static ObservableList<ClientStr> getClients(){
        String url = getBase()+"clients";
        ObservableList<ClientStr> clientData = FXCollections.observableArrayList();
        String result = get(url);
        if(result!=null){
            if (!result.equals("[]")) {
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    try{
                        clientData.add(new ClientStr(jsonArray.getJSONObject(i).getString("firstName"),
                                jsonArray.getJSONObject(i).getString("secondName"),
                                jsonArray.getJSONObject(i).getString("patronymic"),
                                jsonArray.getJSONObject(i).getString("phoneNumber"))
                        );
                    }
                    catch(JSONException e){
                        clientData.add(new ClientStr(jsonArray.getJSONObject(i).getString("firstName"),
                                jsonArray.getJSONObject(i).getString("secondName"),
                                null,
                                jsonArray.getJSONObject(i).getString("phoneNumber"))
                        );
                    }

                }

            }
            return clientData;

        }
        return null;
    }

    public static String get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            {
                StringBuilder sb = new StringBuilder();
                InputStream is = new BufferedInputStream(conn.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                return sb.toString();
            }

        } catch (IOException e) {
            return null;
        }
    }

    public static ObservableList<OrderStr> getOrders() {
        String url = getBase()+"orders";
        ObservableList<OrderStr> orderData = FXCollections.observableArrayList();
        String result = get(url);
        if(result!=null){
            if (!result.equals("[]")) {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try{
                        orderData.add(new OrderStr(
                                jsonArray.getJSONObject(i).getLong("id"),
                                LocalDateTime.parse(jsonArray.getJSONObject(i).getString("orderTime")),
                                jsonArray.getJSONObject(i).getInt("price"),
                                jsonArray.getJSONObject(i).getString("status"),
                                jsonArray.getJSONObject(i).getJSONObject("client"),
                                jsonArray.getJSONObject(i).getJSONObject("service")
                        ));
                    }
                    catch (JSONException j){
                        orderData.add(new OrderStr(
                                jsonArray.getJSONObject(i).getLong("id"),
                                LocalDateTime.parse(jsonArray.getJSONObject(i).getString("orderTime")),
                                null,
                                jsonArray.getJSONObject(i).getString("status"),
                                jsonArray.getJSONObject(i).getJSONObject("client"),
                                jsonArray.getJSONObject(i).getJSONObject("service")
                        ));
                    }

                }

            }
            return orderData;

        }
        return null;
    }

    public static JSONObject getClientByPhone(String phone) {
        String url = getBase()+"clients"+"/"+phone;
        String result = get(url);
        if(result!=null){
            if (!result.equals("")) {
               return new JSONObject(result);
            }
            return new JSONObject();

        }
        return null;
    }

    public static ObservableList<Integer> getTimes(LocalDate value,Integer duration) {
        String url = getBase()+"time"+"/"+value+"/"+duration;
        ObservableList<Integer> times = FXCollections.observableArrayList();
        String result = get(url);

        if(result!=null){
            String replace1 = result.replace("]","");
           replace1 = replace1.replace("[","");
           List<String> list = List.of(replace1.split(","));
            for(String l: list){
                times.add(Integer.parseInt(l));
            }
            if (times.size() ==0){
                return null;
            }
            return times;
        }
        return null;

    }

    public static String postClient(JSONObject client) throws IOException {
        String url = getBase()+"clients";
        return post(url,client);

    }
    public static String post(String link, JSONObject jsonObject) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return getResponse(httpURLConnection);
    }
    private static String getResponse(HttpURLConnection httpURLConnection) throws IOException {
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    public static boolean postOrder(JSONObject j) throws IOException {
        String url = getBase()+"orders";
        String result = post(url,j);
        return !result.equals("");
    }

    public static void deleteOrder(Long id) throws IOException {
        String url = getBase()+"orders/"+id;
        delete(url);
    }
    public static String delete(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("DELETE");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);
        return getResponse(httpURLConnection);
    }

    public static void changeOrder(Long id, JSONObject jsonObject) throws IOException {
        String url = getBase()+"orders/"+id;
        put(url,jsonObject);
    }
    public static String put(String link, JSONObject jsonObject) throws IOException {
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);
        try(OutputStream os = httpURLConnection.getOutputStream()) {
            byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return getResponse(httpURLConnection);
    }
    public static JSONArray getJsonOrders() {
        String url = getBase()+"orders";
        ObservableList<OrderStr> orderData = FXCollections.observableArrayList();
        String result = get(url);
        if(result!=null){
            if (!result.equals("[]")) {
                return new JSONArray(result);

            }
            return null;

            }


        return null;
    }
}
