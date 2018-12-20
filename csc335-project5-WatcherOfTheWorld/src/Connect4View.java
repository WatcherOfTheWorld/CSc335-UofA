import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * File: Connect4ViewOld
 *
 * this class is a GUI replacement of the original text-based view
 * this class implements observer so it make changes to GUI when model has been changed
 * this GUI should be look like the simple GUI show in spec
 *
 * @author Feiran Yang
 */

public class Connect4View extends Application implements Observer {
    private TilePane pane;
    @Override
    public void start(Stage primaryStage) {
        // set UI
        primaryStage.setTitle("Connect4 GUI");
        setUI(primaryStage);
        primaryStage.setResizable(false);
        // set event handler
        setOnAction();
        primaryStage.show();
    }

    /**
     * setting up ui
     * @param primaryStage stage
     */
    public void setUI(Stage primaryStage){
        pane = new TilePane();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);

        // set tile pane
        pane.setMinHeight(296);
        pane.setMinWidth(344);
        pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setHgap(8);
        pane.setVgap(8);
        pane.setPrefTileHeight(40);
        pane.setPrefTileWidth(40);
        pane.setPrefRows(7);


        pane.setPadding(new Insets(8));
        // set Tiles in TilePane, 7 by 6
        for(int i = 0; i<7;i++){
            for(int j = 0; j< 6; j++){
                // put a circle in ever tile
                Circle circle = new Circle();
                circle.setRadius(20);
                circle.setFill(Color.WHITE);

                pane.getChildren().add(circle);
            }
        }
    }

    /**
     * setting up on action
     */
    public void setOnAction(){
        Connect4Model model = new Connect4Model();
        model.addObserver(this);
        Connect4Controller controller = new Connect4Controller(model);

        // set mouse clicked event
        pane.setOnMouseClicked(event -> {
            // find position
            int x = (int)event.getX();
            int col = x/48;
            if(col ==7){
                col = 6;
            }
            for(int i = 0; i<2; i++){
                // make turns
                if(i == 0){
                    try {
                        controller.humanTurn(col);
                    }catch (IndexOutOfBoundsException e){
                        new Alert(Alert.AlertType.WARNING,e.getMessage()).showAndWait();
                        return;
                    }
                }else{
                    controller.computerTurn();
                }
                // pop up result
                if(controller.isGameOver()){
                    String result;
                    if(controller.getResult() == 0){
                        result = "Draw";
                    }else if(controller.getResult() == 1){
                        result = "You Win";
                    }else{
                        result = "You lose";
                    }
                    new Alert(Alert.AlertType.INFORMATION, result).showAndWait();
                    pane.setOnMouseClicked(event1 -> {});
                    return;
                }
            }
        });
    }


    @Override
    /**
     * update UI when change made in model
     */
    public void update(Observable o, Object arg) {
        int[] array = (int[]) arg;
        ObservableList list =pane.getChildren();
        Circle circle = (Circle)list.get(array[0]+7*array[1]);

        if(array[2] == 88){
            circle.setFill(Color.YELLOW);
        }else{
            circle.setFill(Color.RED);
        }
    }
}
