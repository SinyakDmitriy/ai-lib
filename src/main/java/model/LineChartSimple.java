package model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LineChartSimple extends Application {

    private ScheduledExecutorService scheduledExecutorService;
    private static Map<Long, Double> buff;
    public static void launch(Map<Long, Double> buffer, String... args){
        buff = buffer;
        launch(args);
    }

    @Override public void start(Stage stage) {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Errors");
        //populating the series with data
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();

        final int[] i = {0};
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Map<Long, Double> copy = new HashMap<>(buff);
            List<XYChart.Data> data = new ArrayList<>();
            if(copy.isEmpty()) return;
            for (Map.Entry<Long, Double> item : copy.entrySet()) {
                data.add(new XYChart.Data(item.getKey(), item.getValue()));
            }
            Platform.runLater(() -> {
                series.getData().addAll(0, data);
            });
            i[0]++;
            buff.remove(copy);
        }, 0, 3, TimeUnit.SECONDS);
    }
}
