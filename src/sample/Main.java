package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String DEFAULT_POWER = "1";
    public static final String DEFAULT_START = "1";
    public static final String DEFAULT_END = "10";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root2 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Графопостроитель первой модели");
        final LineChart<Number, Number> lineChart = createLineChart();

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 800, 600);

        Label labelPower = new Label("Введите степень многочлена");
        TextField textFieldPower = new TextField(DEFAULT_POWER);
        textFieldPower.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textFieldPower.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Label labelStart = new Label("Введите начало отрезка");
        TextField textFieldStart = new TextField(DEFAULT_START);
        textFieldStart.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textFieldStart.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Label labelEnd = new Label("Введите конец отрезка");
        TextField textFieldEnd = new TextField(DEFAULT_END);
        textFieldEnd.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textFieldEnd.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Button buttonLagrange = new Button("Построить по Лагранжу");
        buttonLagrange.setOnAction(a -> {
            lineChart.getData().add(generateSeriesData(
                    Integer.valueOf(textFieldPower.getText()),
                    Integer.valueOf(textFieldStart.getText()),
                    Integer.valueOf(textFieldEnd.getText())));
            System.out.println("Лагранж");
        });

        Button buttonNewton = new Button("Построить по Ньютону");
        buttonNewton.setOnAction(a -> {
            lineChart.getData().add(generateSeriesData2());
            System.out.println("Ньютон");
        });

        Button buttonClear = new Button("Очистить поле");
        buttonClear.setOnAction(a -> {
            lineChart.getData().clear();
            System.out.println("Очистка графика...");
        });

        HBox hBoxControls = new HBox(10);
        hBoxControls.setAlignment(Pos.BASELINE_CENTER);

        VBox vBoxLabels = new VBox(15);
        vBoxLabels.setAlignment(Pos.BASELINE_RIGHT);
        vBoxLabels.getChildren().addAll(labelPower, labelStart, labelEnd);

        VBox vBoxTextFields = new VBox(10);
        vBoxTextFields.setAlignment(Pos.CENTER_LEFT);
        vBoxTextFields.getChildren().addAll(textFieldPower, textFieldStart, textFieldEnd);

        VBox vBoxButtons = new VBox(10);
        vBoxButtons.getChildren().addAll(buttonLagrange, buttonNewton, buttonClear);

        hBoxControls.getChildren().addAll(vBoxLabels, vBoxTextFields, vBoxButtons);

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBoxControls, lineChart);
        root.getChildren().add(vBox);

        XYChart.Series series = generateSeriesData(
                Integer.valueOf(textFieldPower.getText()),
                Integer.valueOf(textFieldStart.getText()),
                Integer.valueOf(textFieldEnd.getText()));

        lineChart.getData().add(series);
//        Scene scene2 = new Scene(lineChart,800,600);


//        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static XYChart.Series generateSeriesData(Number power, Number start, Number end) {
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Тест 1");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        return series;
    }

    private static XYChart.Series generateSeriesData2() {
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Тест 2");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 45));
        series.getData().add(new XYChart.Data(2, 12));
        series.getData().add(new XYChart.Data(3, 6));
        series.getData().add(new XYChart.Data(4, 14));
        series.getData().add(new XYChart.Data(5, 43));
        series.getData().add(new XYChart.Data(6, 23));
        series.getData().add(new XYChart.Data(7, 54));
        series.getData().add(new XYChart.Data(8, 12));
        series.getData().add(new XYChart.Data(9, 53));
        series.getData().add(new XYChart.Data(10, 26));
        series.getData().add(new XYChart.Data(11, 12));
        series.getData().add(new XYChart.Data(12, 15));
        return series;
    }

    private static LineChart<Number, Number> createLineChart() {
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        return lineChart;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
