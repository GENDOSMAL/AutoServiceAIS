package autoService.utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class OrderStr {

    private final LocalDateTime orderTime;
    private final SimpleIntegerProperty price;
    private final SimpleStringProperty status;
    private final JSONObject client;
    private final JSONObject service;
    private final Long id;

    public OrderStr(Long id,LocalDateTime orderTime, Integer price, String status, JSONObject client,
                     JSONObject service
                    ){
        this.id = id;
        this.orderTime = orderTime;
        this.price = new SimpleIntegerProperty(price);
        this.status = new SimpleStringProperty(status);
        this.client = client;
        this.service = service;
    }
    public SimpleStringProperty getDate(){
        return new SimpleStringProperty(orderTime.toLocalDate().toString());
    }
    public SimpleStringProperty getTime(){
        return new SimpleStringProperty(String.valueOf(orderTime.toLocalTime()));
    }
    public SimpleStringProperty getServiceName(){
        return new SimpleStringProperty(service.getString("type"));
    }
    public SimpleStringProperty getClientInfo(){
        return new SimpleStringProperty(client.getString("phoneNumber"));
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Integer getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public JSONObject getClient() {
        return client;
    }

    public JSONObject getService() {
        return service;
    }

    public Long getId() {
        return id;
    }
}
