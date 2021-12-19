package autoService.controller;

import autoService.Main;
import autoService.utils.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Optional;

public class NewServiceController
{
	@FXML
	public TextField serviceName;

	@FXML
	public TextField type;

	@FXML
	public TextField length;

	private Main main;
	private Stage stage;

	public void setMain(Main main)
	{
		this.main = main;
	}

	public void setStage(Stage primaryStage)
	{
		this.stage = primaryStage;
	}

	@FXML
	public void create()
	{
		try
		{

			if (serviceName.getText().equals(""))
			{
				showAlert("Ошибка заполнения данных", "В поле имени сервиса должно быть заполнено не пустыми данными");
				return;
			}

			if (type.getText().equals(""))
			{
				showAlert("Ошибка заполнения данных", "В поле тип сервиса должно быть заполнено не пустыми данными");
				return;
			}

			if (length.getText().equals(""))
			{
				showAlert("Ошибка заполнения данных", "В поле длительность  должно быть заполнено не пустыми данными целочисленного типа!");
				return;
			}

			var lengthInt = parseInt(length.getText());
			if (lengthInt.isEmpty())
			{
				showAlert("Ошибка заполнения данных", "В поле длительность  должно быть заполнено не пустыми данными целочисленного типа!");
				return;
			}
			var serviceJsonObject = new JSONObject();
			serviceJsonObject.put("duration", lengthInt.get());
			serviceJsonObject.put("type", type.getText());
			serviceJsonObject.put("category", serviceName.getText());
			var res = Connection.postService(serviceJsonObject);
			if (res.equals(""))
			{
				throw new IOException();
			}
			stage.close();			
		} 
		catch (Exception ex)
		{
			showAlert("Ошибка сохранения новой услуги", "При попытке сохранить данных произошла ошибка ["+ex.getMessage()+"]!");
		}
	}
	
	private void showAlert(String header, String message)
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(main.getPrimaryStage());
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.show();
	}

	public static Optional<Integer> parseInt(String toParse)
	{
		try
		{
			return Optional.of(Integer.parseInt(toParse));
		} catch (NumberFormatException e)
		{
			return Optional.empty();
		}
	}
}
