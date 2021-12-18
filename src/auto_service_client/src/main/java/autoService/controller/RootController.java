package autoService.controller;


import autoService.Main;
import autoService.utils.ClientStr;
import autoService.utils.Connection;
import autoService.utils.OrderStr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class RootController {
    private Main main;
    @FXML private TableView<OrderStr> orderTable;
    @FXML private TableColumn<OrderStr, String> orderTime;
    @FXML private TableColumn<OrderStr, String> orderDate;
    private ObservableList<OrderStr> orderData = FXCollections.observableArrayList();
    @FXML private TableColumn<OrderStr, String> client;
    @FXML private TableColumn<OrderStr, String> service;
    @FXML private TableColumn<OrderStr, String> status;
    @FXML private TableColumn<OrderStr, Integer> price;
    private Stage stage;

    @FXML
    void initialize(){
        orderTime.setCellValueFactory(cellData -> cellData.getValue().getTime());
        orderDate.setCellValueFactory(cellData -> cellData.getValue().getDate());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        status.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        service.setCellValueFactory(cellData -> cellData.getValue().getServiceName());
        client.setCellValueFactory(cellData -> cellData.getValue().getClientInfo());
    }

    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    public void getAuthor(){
        this.main.initAbout();
    }
    @FXML
    public void getServices(){
        this.main.initServices();
    }
    @FXML
    public void getClients(){
        this.main.initClients();
    }

    public void load() {
        orderData  = Connection.getOrders();
        stage.show();
        if(orderData == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setHeaderText("Ошибка на сервере");
            alert.setContentText("Не удалось загрузить договора");
            alert.show();
        }
        else{
            this.orderTable.setItems(orderData);
        }
    }
    @FXML
    public void create(){
        this.main.initNewOrder(this);
    }
    @FXML
    public void change(){
        int selectedIndex = this.orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.main.initEditOrder(this);
        }
       else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setHeaderText("Не выбран договор");
            alert.setContentText("Выберите договор");
            alert.show();
        }
    }

    public TableView<OrderStr> getOrderTable() {
        return orderTable;
    }

    @FXML
    private void delete() {

        int selectedIndex = this.orderTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(main.getPrimaryStage());
            alert.setHeaderText("Согласие на действие");
            alert.setContentText("Вы уверены?");
            alert.initModality(Modality.APPLICATION_MODAL);
            Optional<ButtonType> option = alert.showAndWait();
            OrderStr order = this.orderTable.getSelectionModel().getSelectedItem();
            if (option.get() == ButtonType.OK){
                try{
                    Connection.deleteOrder(order.getId());
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.initOwner(main.getPrimaryStage());
                    a.setHeaderText("Успешно!");
                    a.setContentText("Успешное удаление договора");
                    a.show();

                }
               catch(IOException e){
                   Alert a = new Alert(Alert.AlertType.WARNING);
                   a.initOwner(main.getPrimaryStage());
                   a.setContentText("Не удалось удалить договор");
                   a.setHeaderText("Сервер не отвечает");
                   a.show();
               }
                this.load();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setHeaderText("Не выбран договор");
            alert.setContentText("Выберите договор");
            alert.show();
        }
    }

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    @FXML public void getStat(){
      this.main.initStat();
    }
}
