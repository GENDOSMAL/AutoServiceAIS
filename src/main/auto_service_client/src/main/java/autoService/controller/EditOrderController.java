package autoService.controller;

import autoService.Main;
import autoService.utils.Connection;
import autoService.utils.OrderStr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class EditOrderController {
    private Main main;
    private Stage stage;
    private RootController root;
    @FXML
    Label service;
    @FXML
    TextField phoneNumber;
    @FXML
    TextField name;
    @FXML
    TextField secondName;
    @FXML
    TextField patr;
    @FXML Label date;
    @FXML TextField price;
    @FXML
    ComboBox<String> status;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public void setMain(Main main){
        this.main = main;
    }
    public void setRoot(RootController root){
        this.root = root;
    }
    public void load() {

        OrderStr orderStr = this.root.getOrderTable().getSelectionModel().getSelectedItem();
        this.service.setText(orderStr.getServiceName().get());
        this.phoneNumber.setText(orderStr.getClientPhone().get());
        this.name.setText(orderStr.getClient().getString("firstName"));
        this.secondName.setText(orderStr.getClient().getString("secondName"));
        try{
            this.patr.setText(orderStr.getClient().getString("patronymic"));
        }
        catch(JSONException j){
            this.patr.setText(null);
        }
        this.date.setText(orderStr.getOrderTime().toString());
        try{
            this.price.setText(orderStr.getPrice().toString());
        }
       catch(JSONException j){
           this.price.setText(null);
       }
       ObservableList<String> status = FXCollections.observableArrayList();
       status.add("не выполнено");
       status.add("выполнено");
        this.status.setItems(status);
        this.status.setValue(orderStr.getStatus());
        this.stage.show();
    }
    @FXML
    public void change(){
       if(!this.price.getText().equals(String.valueOf(this.root.getOrderTable().getSelectionModel().getSelectedItem().getPrice()))|
       !this.status.getValue().equals(this.root.getOrderTable().getSelectionModel().getSelectedItem().getStatus())){

               JSONObject jsonObject = new JSONObject();
               jsonObject.put("status",this.status.getValue());
               try{
                   jsonObject.put("price",Integer.parseInt(this.price.getText()));
                   jsonObject.put("orderTime",this.root.getOrderTable().getSelectionModel().getSelectedItem().getOrderTime());
                   jsonObject.put("client",this.root.getOrderTable().getSelectionModel().getSelectedItem().getClient());
                   jsonObject.put("service",this.root.getOrderTable().getSelectionModel().getSelectedItem().getService());
                   try{
                       Connection.changeOrder(this.root.getOrderTable().getSelectionModel().getSelectedItem().getId(),jsonObject);
                       this.stage.close();
                       this.root.load();
                   }
                   catch(IOException e){
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.initModality(Modality.APPLICATION_MODAL);
                       alert.initOwner(main.getPrimaryStage());
                       alert.setContentText("Сервер не отвечает");
                       alert.setHeaderText("Данные не были изменены");
                       alert.show();
                   }
               }
               catch(Exception e){
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.initModality(Modality.APPLICATION_MODAL);
                   alert.initOwner(main.getPrimaryStage());
                   alert.setContentText("Цена должна быть числом!");
                   alert.setHeaderText("Данные не были обновлены");
                   alert.show();
               }


       }
       else{
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.initModality(Modality.APPLICATION_MODAL);
           alert.initOwner(main.getPrimaryStage());
           alert.setContentText("Нет изменений");
           alert.setHeaderText("Данные не были обновлены");
           alert.show();
       }
    }
}
