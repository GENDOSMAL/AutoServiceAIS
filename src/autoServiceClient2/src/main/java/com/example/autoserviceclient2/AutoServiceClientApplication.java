package com.genderfrender.autoserviceclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class AutoServiceClientApplication extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{
		FXMLLoader fxmlLoader = new FXMLLoader(AutoServiceClientApplication.class.getResource("MainWindow.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 1200, 950);
		stage.setTitle("Автосервис клиентское приложение!");
		stage.setScene(scene);
		stage.getIcons().add(new Image(Objects.requireNonNull(AutoServiceClientApplication.class.getResource("free-icon-car-service-1820721.png")).toString()));
		stage.setResizable(false);
		stage.show();
	}
}
