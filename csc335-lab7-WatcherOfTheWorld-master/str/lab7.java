import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class lab7 extends Application {
    Color color;
    GraphicsContext gc;
    double preX;
    double preY;
    public static void main(String[] argv){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setMinWidth(400);
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        Canvas canvas = new Canvas(400,400);
        borderPane.setTop(canvas);
        borderPane.setBottom(flowPane);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        Text text = new Text("Pen Color: ");
        Button button = new Button("Clear Screen");
        color = Color.BLACK;
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        flowPane.getChildren().addAll(button, text,colorPicker);
        setOnAction(canvas,button,colorPicker);
        primaryStage.show();
    }

    public void setOnAction(Canvas canvas, Button button, ColorPicker colorPicker){
        gc = canvas.getGraphicsContext2D();
        button.setOnAction(event -> gc.clearRect(0,0,400,400));
        colorPicker.setOnAction(event -> {
            color = colorPicker.getValue();
            gc.setStroke(color);
        });

        canvas.setOnMousePressed(event -> {
            preX = event.getX();
            preY = event.getY();
        });

        canvas.setOnMouseDragged(event -> {
            gc.strokeLine(preX,preY, event.getX(),event.getY());
            preY = event.getY();
            preX = event.getX();
        });
    }
}
