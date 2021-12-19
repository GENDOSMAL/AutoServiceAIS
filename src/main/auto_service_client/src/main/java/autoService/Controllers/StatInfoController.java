package autoService.Controllers;

import autoService.Main;
import autoService.Support.ServerRequestIOperator;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONArray;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StatInfoController
{
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	@FXML
	LineChart<String, Integer> chart = new LineChart(xAxis, yAxis);
	@FXML
	LineChart<String, Integer> timeChart = new LineChart(xAxis, yAxis);
	private Stage stage;
	private Main main;

	public void loadData()
	{
		XYChart.Series<String, Integer> series = loadSections();
		XYChart.Series<String, Integer> series2 = loadProfit();
		if (series == null | series2 == null)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(this.main.getPrimaryStage());
			alert.setHeaderText("Сервер не отвечает");
			alert.setContentText("Не удалось загрузить статистику");
			alert.show();
		} else
		{
			chart.getData().add(series);
			timeChart.getData().add(series2);
			stage.show();
		}
	}

	public void setStage(Stage primaryStage)
	{
		this.stage = primaryStage;
	}

	public void setMain(Main main)
	{
		this.main = main;
	}

	private XYChart.Series<String, Integer> loadSections()
	{
		JSONArray data = ServerRequestIOperator.getJsonOrders();
		HashMap<String, Integer> map = new HashMap<>();
		if (data == null)
		{
			return null;
		} else
		{
			XYChart.Series<String, Integer> series = new XYChart.Series<>();

			for (int i = 0; i < data.length(); i++)
			{
				if (map.containsKey(data.getJSONObject(i).getJSONObject("service").getString("type")))
				{
					map.put(data.getJSONObject(i).getJSONObject("service").getString("type"), map.get(data.getJSONObject(i).getJSONObject("service").getString("type")) + 1);

				} else
				{
					map.put(data.getJSONObject(i).getJSONObject("service").getString("type"), 1);
				}

			}
			Map<String, Integer> treeMap = new TreeMap<>(map);
			for (Map.Entry<String, Integer> entry : treeMap.entrySet())
			{
				series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
			}
			series.setName("Секции");
			return series;
		}
	}

	private XYChart.Series<String, Integer> loadProfit()
	{
		JSONArray data = ServerRequestIOperator.getJsonOrders();
		HashMap<Integer, Integer> map = new HashMap<>();
		if (data == null)
			return null;

		XYChart.Series<String, Integer> series = new XYChart.Series<>();
		for (int i = 0; i < data.length(); i++)
		{
			Integer year = LocalDateTime.parse(data.getJSONObject(i).getString("orderTime")).getDayOfMonth();
			if (map.containsKey(year))
			{
				map.put(year, map.get(year) + data.getJSONObject(i).getInt("price"));
			} else
			{
				map.put(year, data.getJSONObject(i).getInt("price"));
			}

		}
		Map<Integer, Integer> treeMap = new TreeMap<>(map);
		for (Map.Entry<Integer, Integer> entry : treeMap.entrySet())
		{
			series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		}
		
		series.setName("Заработок");
		return series;
	}
}
