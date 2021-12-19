package autoService.controller;

import autoService.Main;
import autoService.utils.Connection;
import autoService.utils.ServiceStr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServiceController {
    @FXML private TextField textField;
    @FXML private TableView<ServiceStr> serviceTable;
    @FXML private TableColumn<ServiceStr, String> service;
    @FXML private TableColumn<ServiceStr, String> category;
    @FXML private TableColumn<ServiceStr, Integer> duration;
    private ObservableList<ServiceStr> serviceData = FXCollections.observableArrayList();
    private FilteredList<ServiceStr> filteredData = new FilteredList<>(serviceData );
    private SortedList<ServiceStr> sortableData = new SortedList<>(filteredData);
    private Main main;
    private Stage dialogStage;

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    void initialize(){
        service.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        category.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        duration.setCellValueFactory(cellData -> cellData.getValue().durationProperty().asObject());
        textField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(p -> p.getCategory().toLowerCase().contains(textField.getText().toLowerCase()) |
                        p.getType().toLowerCase().contains(textField.getText().toLowerCase() )
                )
        );
    }

    public void load() {
        serviceData  = (ObservableList<ServiceStr>) Connection.getServices(true);
        if(serviceData == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(main.getPrimaryStage());
            alert.setHeaderText("Ошибка на сервере");
            alert.setContentText("Не удалось загрузить страницу");
            alert.show();
        }
        else{
            this.filteredData = new FilteredList<>(serviceData);
            this.sortableData = new SortedList<>(this.filteredData);
            this.serviceTable.setItems(this.sortableData);
            this.sortableData.comparatorProperty().bind(this.serviceTable.comparatorProperty());
            this.dialogStage.show();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
