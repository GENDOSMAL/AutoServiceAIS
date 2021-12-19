package autoService;

import autoService.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    public static String title="Информационно автоматизированная система \"Автосервис\"";
    public Image icon;
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void start(Stage stage) {
        primaryStage = stage;
        icon=new Image(Main.class.getResource("/views/icon.png").toString());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle(title);
        initRoot();
    }

    private void initRoot() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/root.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            RootController controller = loader.getController();
            controller.setMain(this);
            controller.setStage(primaryStage);
            controller.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initAbout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/author.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initServices() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/services.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            ServiceController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initClients() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/clients.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            ClientController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage getLoader(FXMLLoader loader) throws IOException {
        AnchorPane rootLayout =  loader.load();
        Stage dialogStage = new Stage();
        dialogStage.initOwner(this.primaryStage);
        dialogStage.setTitle(title);
        dialogStage.getIcons().add(icon);
        dialogStage.setResizable(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(rootLayout);
        dialogStage.setScene(scene);
        return dialogStage;
    }

    public void initNewOrder(RootController root) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/newOrder.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            NewOrderController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setMain(this);
            controller.setRoot(root);
            controller.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initEditOrder(RootController rootController) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/EditOrder.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            EditOrderController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setMain(this);
            controller.setRoot(rootController);
            controller.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initStat()  {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/stat.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            StatController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setMain(this);
            controller.loadData();
        }
       catch(IOException e){
            e.printStackTrace();
       }

    }

    public void initNewServiceCreation()  {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/views/NewService.fxml"));
            Stage dialogStage = getLoader(loader);
            dialogStage.setTitle(title);
            dialogStage.getIcons().add(icon);
            
            NewServiceController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setMain(this);
            dialogStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

}

