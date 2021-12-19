package autoService.Controllers;

import autoService.Main;
import autoService.Support.ClientStr;
import autoService.Support.ServerRequestIOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ClientListController
{

    @FXML private TableColumn<ClientStr, String> firstName;
    @FXML private TableColumn<ClientStr, String>  secondName;
    @FXML private TextField textField;
    @FXML private TableColumn<ClientStr, String>  patronymic;
    @FXML private TableColumn<ClientStr, String>  phoneNumber;
    @FXML private TableView<ClientStr> clientTable;
    private ObservableList<ClientStr> clientData = FXCollections.observableArrayList();
    private FilteredList<ClientStr> filteredData = new FilteredList<>(clientData );
    private SortedList<ClientStr> sortableData = new SortedList<>(filteredData);
    private Main main;
    private Stage dialogStage;

    public void setMain(Main main) {
        this.main = main;
    }
    @FXML
    void initialize(){
        firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
       secondName.setCellValueFactory(cellData -> cellData.getValue().secondNameProperty());
        patronymic.setCellValueFactory(cellData -> cellData.getValue().patronymicProperty());
        phoneNumber.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        textField.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField.getText().toLowerCase()) |
                        p.getPhoneNumber().toLowerCase().contains(textField.getText().toLowerCase()) |
                                p.getSecondName().toLowerCase().contains(textField.getText().toLowerCase())|
                        (p.getFirstName().toLowerCase()+" "+p.getSecondName().toLowerCase()).contains(textField.getText().toLowerCase()))

        );

    }
    public void load() {
        clientData  = ServerRequestIOperator.getClients();
        if(clientData == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(main.getPrimaryStage());
            alert.setHeaderText("Ошибка на сервере");
            alert.setContentText("Не удалось загрузить данные");
            alert.show();
        }
        else{
            this.filteredData = new FilteredList<>(clientData);
            this.sortableData = new SortedList<>(this.filteredData);
            this.clientTable.setItems(this.sortableData);
            this.sortableData.comparatorProperty().bind(this.clientTable.comparatorProperty());
            this.dialogStage.show();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
