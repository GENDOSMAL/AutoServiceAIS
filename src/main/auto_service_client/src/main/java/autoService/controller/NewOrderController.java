package autoService.controller;

import autoService.Main;
import autoService.utils.Connection;
import autoService.utils.PhoneValidator;
import autoService.utils.ServiceStr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class NewOrderController
{
	@FXML
	ComboBox<String> serviceCombobox;
	@FXML
	ComboBox<String> catCombobox;
	@FXML
	TextField phoneNumber;
	@FXML
	TextField name;
	@FXML
	TextField secondName;
	@FXML
	TextField patr;
	@FXML
	DatePicker picker;
	@FXML
	ComboBox<Integer> timer;
	ObservableList<ServiceStr> services = FXCollections.observableArrayList();
	JSONArray jsonServices = new JSONArray();
	private Main main;
	private Stage stage;
	private RootController root;

	public void setStage(Stage primaryStage)
	{
		this.stage = primaryStage;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

	public void load()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(main.getPrimaryStage());
		services = (ObservableList<ServiceStr>) Connection.getServices(true);
		jsonServices = (JSONArray) Connection.getServices(false);
		if (services == null)
		{
			alert.setHeaderText("Ошибка на сервере");
			alert.setContentText("Не удалось соединиться");
			alert.show();
		} else
		{
			if (!services.isEmpty())
			{

				ObservableList<String> catData = FXCollections.observableArrayList();
				for (ServiceStr service : services)
				{
					if (!catData.contains(service.getCategory()))
					{
						catData.add(service.getCategory());
					}

				}

				this.catCombobox.setItems(catData);
				this.stage.show();
			} else
			{
				alert.setHeaderText("Ошибка на сервере");
				alert.setContentText("Отсутствуют данные");
				alert.show();
			}
		}
	}


	@FXML
	public void create()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(main.getPrimaryStage());
		
		if (catCombobox.getValue() != null && serviceCombobox.getValue() != null && !catCombobox.getValue().equals("") & !serviceCombobox.getValue().equals(""))
		{
			if (!phoneNumber.getText().equals("") && PhoneValidator.isNormal(phoneNumber.getText()))
			{
				JSONObject client = Connection.getClientByPhone(phoneNumber.getText());
				if (client != null)
				{
					if (client.length() != 0)
					{
						try
						{
							patr.setText(client.getString("patronymic"));
						} catch (JSONException ignored)
						{

						}
						name.setText(client.getString("firstName"));
						secondName.setText(client.getString("secondName"));

					}

					if (name.getText().equals("") | secondName.getText().equals(""))
					{
						alert.setHeaderText("Введите данные человека");
						alert.setContentText("Попробуйте еще раз!");
						alert.show();

					} else
					{
						if (picker.getValue() == null)
						{

							alert.setHeaderText("Выберите дату");
							alert.setContentText("Попробуйте еще раз!");
							alert.show();


						} else
						{
							if (picker.getValue().toString().equals("") ||
									picker.getValue().isBefore(LocalDate.now()))
							{
								alert.setHeaderText("Выберите корректную дату");
								alert.setContentText("Попробуйте еще раз! Учтите: дата должна" +
										"быть не раньше сегодняшнего дня");
								alert.show();

							} else
							{
								;
								if (timer.getValue() != null)
								{
									if (timer.getValue().toString().equals("") &&
											timer.getItems().size() != 0)
									{
										alert.setHeaderText("Выберите дату!");
										alert.show();
									} else
									{

										try
										{
											JSONObject j = new JSONObject();
											if (client.length() != 0)
											{
												j.put("client", client);

											} else
											{
												JSONObject c = new JSONObject();
												c.put("firstName", name.getText());
												c.put("secondName", secondName.getText());
												c.put("patronymic", patr.getText());
												c.put("phoneNumber", phoneNumber.getText());
												String res = Connection.postClient(c);
												if (res.equals(""))
												{
													throw new IOException();
												}
												j.put("client", new JSONObject(res));
											}
											LocalTime time = LocalTime.of(timer.getValue(), 0);
											j.put("orderTime", LocalDateTime.of(picker.getValue(), time));
											j.put("status", "не выполнено");
											for (int i = 0; i < jsonServices.length(); i++)
											{
												if (jsonServices.getJSONObject(i).getString("type").equals(serviceCombobox.getValue()))
												{
													j.put("service", jsonServices.getJSONObject(i));
													break;
												}
											}

											if (!Connection.postOrder(j))
											{
												throw new IOException();
											} else
											{
												Alert a = new Alert(Alert.AlertType.INFORMATION);
												a.initOwner(main.getPrimaryStage());
												a.setHeaderText("Успешно!");
												a.setContentText("Успешное создание заказ!");
												a.show();
												this.stage.close();
												this.root.load();

											}


										} catch (IOException e)
										{
											alert.setHeaderText("Не удалось создать заказ");
											alert.show();
										}

									}
								} else
								{
									alert.setHeaderText("Установите время для даты!");
									alert.show();
								}
							}


						}
					}
				} else
				{
					alert.setHeaderText("Не удалось подключиться к серверу");
					alert.setContentText("Попробуйте еще раз!");
					alert.show();

				}
			} else
			{
				alert.setHeaderText("Неправильно набран номер телефона");
				alert.setContentText("Введите номер телефона!");
				alert.show();
			}
		} else
		{
			alert.setHeaderText("Не выбран сервис");
			alert.setContentText("Выберите сервис!");
			alert.show();
		}
	}

	@FXML
	public void onChangeNumber()
	{
		if (!phoneNumber.getText().equals("") && PhoneValidator.isNormal(phoneNumber.getText()))
		{
			JSONObject client = Connection.getClientByPhone(phoneNumber.getText());
			if (client == null)
				return;
			if (client.length() == 0)
				return;

			try
			{
				patr.setText(client.getString("patronymic"));
				name.setText(client.getString("firstName"));
				secondName.setText(client.getString("secondName"));
			} 
			catch (JSONException ignored)
			{
			}
		}
	}

	@FXML
	public void changeCombobox()
	{
		ObservableList<String> serviceData = FXCollections.observableArrayList();
		for (ServiceStr service : services)
		{
			if (service.getCategory().equals(catCombobox.getValue()))
			{
				serviceData.add(service.getType());
			}
		}
		serviceCombobox.setItems(serviceData);
		serviceCombobox.setDisable(catCombobox.getValue().equals(""));
	}
	
	@FXML
	public void needChange()
	{
		picker.setDisable(serviceCombobox.getValue() == null);
	}


	public void setRoot(RootController root)
	{
		this.root = root;
	}

	@FXML
	public void setTimes()
	{
		if (!(this.serviceCombobox.getValue() == null) & this.picker.getValue() != null)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(main.getPrimaryStage());
			int duration = 0;
			for (ServiceStr o : services)
			{
				if (o.getType().equals(serviceCombobox.getValue()))
				{
					duration = o.durationProperty().get();
					break;
				}
			}
			ObservableList<Integer> times =
					Connection.getTimes(picker.getValue(), duration);
			if (times == null)
			{
				alert.setHeaderText("Нет соединения с сервером");
				alert.setContentText("Попробуйте еще раз!");
				timer.getItems().clear();
				alert.show();

			} else
			{
				if (times.size() == 0)
				{
					alert.setHeaderText("Нет времени для данной даты");
					timer.getItems().clear();
					alert.show();

				} else
				{
					timer.setItems(times);
				}
			}
		}

	}
}
