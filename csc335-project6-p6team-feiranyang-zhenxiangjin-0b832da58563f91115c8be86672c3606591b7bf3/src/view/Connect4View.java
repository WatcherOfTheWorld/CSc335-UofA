package view;

import controller.Connect4Controller;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Connect4Model;
import model.Connect4MoveMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * @author Shawn Jin, Feiran Yang
 * This view calss will help connect4 run a game in a GUI by using javaFX
 */
public class Connect4View extends Application implements Observer {

    private Connect4Model connect4Model;
    private Connect4Controller connect4Controller;
    private TilePane tilePane;
    private List<List<Circle>> circles;
    private final int ROW = 6;
    private final int COL = 7;
    private MenuItem item;
    private BorderPane pane;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connect4");
        pane = new BorderPane();
        setTiles();
        // try to load game data
        if(!loadData()) {
            // if failed, start a new game
            connect4Model = new Connect4Model();
            connect4Controller = new Connect4Controller(connect4Model);

        }else{
            // restore board from file
            restore();
        }
        connect4Model.addObserver(this);


        //pane.setBottom(tilePane);
        MenuBar bar = new MenuBar();
        Menu menu = new Menu("File");
        item = new MenuItem("New Game");
        menu.getItems().addAll(item);
        bar.getMenus().addAll(menu);
        pane.setTop(bar);
        addEventHandler(primaryStage);

        primaryStage.setScene(new Scene(pane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * restore board from the restored model
     */
    private void restore(){
        for(int i = 0; i < connect4Controller.getRow();i++){
            for(int j = 0; j < connect4Controller.getCol(); j++){
                char player = connect4Controller.getLoc(i,j);
                if(player == '_'){
                    continue;
                }else if(player == 'X'){
                    circles.get(i).get(j).setFill(Color.YELLOW);
                }else{
                    circles.get(i).get(j).setFill(Color.RED);
                }
            }
        }
    }

    /**
     * load saved game data from disk
     * @return if loads success
     */
    private boolean loadData(){
        try{
            // try to load save_game.data
            FileInputStream file = new FileInputStream("save_game.dat");
            ObjectInputStream in = new ObjectInputStream(file);
            connect4Model = (Connect4Model) in.readObject();
            in.close();
            file.close();
            connect4Controller = new Connect4Controller(connect4Model);
            // if success, return true
            return true;
        //otherwise, return false
        }catch (IOException ioe){
            //ioe.printStackTrace();
            return false;
        }catch (ClassNotFoundException nfe){
            //nfe.printStackTrace();
            return false;
        }

    }

    /**
     * this method set up a tile pane on the bottom of BoardPane
     */
    private void setTiles(){
        tilePane  = new TilePane(8, 8);
        tilePane.setPrefSize(344, 296);
        tilePane.setPrefRows(ROW);
        tilePane.setPrefColumns(COL);
        tilePane.setStyle("-fx-background-color: blue");
        tilePane.setAlignment(Pos.CENTER);

        // add circles into the TilePane
        circles = createCircles(ROW, COL);
        for (List<Circle> circle : circles) {
            for (Circle aCircle : circle) {
                tilePane.getChildren().add(aCircle);
            }
        }
        pane.setBottom(tilePane);
    }

    /**
     * this method add a on mouse click event on tile pane (game board)
     */
    private void setTileEvent(){
        tilePane.setOnMouseClicked(event -> {
            boolean consequence = Connect4View.this.put(event.getX());
            boolean isGameOver = Connect4View.this.checkIsGameOver();
            if (!isGameOver & consequence) {
                connect4Controller.smartComputerTurn();
                if (isGameOver = Connect4View.this.checkIsGameOver()) {
                    tilePane.setOnMouseClicked(event1 -> {});
                }

            } else if (isGameOver) {
                tilePane.setOnMouseClicked(event1 -> {});
            }
        });
    }

    /**
     * this method adds event handle to the GUI
     * @param stage primaryStage for the GUI
     */
    private void addEventHandler(Stage stage){
        setTileEvent();

        item.setOnAction(event -> {
            // set a new Controller/Model for the game
            connect4Model = new Connect4Model();
            connect4Model.addObserver(this);
            connect4Controller.setModel(connect4Model);
            // reset the tile and add a event handler for it
            setTiles();
            setTileEvent();
        });

        stage.setOnCloseRequest(event -> {
            // if game finishes, delete save_game.dat
            if(connect4Controller.isGameOver() != 2){
                File file = new File("save_game.dat");
                file.delete();
            }else {
                // otherwise, save model into save_game.dat
                try {
                    FileOutputStream file = new FileOutputStream("save_game.dat");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(connect4Model);
                    out.close();
                    file.close();
                    System.out.println("Game Saved");
                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }
        });
    }
    /**
     * check if the game is over, if game is over, it will alert the user that who is winner
     * @return boolean represent if game is over
     */
    private boolean checkIsGameOver() {
        int consequence = connect4Controller.isGameOver();
        if (consequence == 1) {
            // human win
            new Alert(Alert.AlertType.INFORMATION, "You won!").showAndWait();
            return true;
        } else if (consequence == 0) {
            // computer win
            new Alert(Alert.AlertType.INFORMATION, "Computer won!").showAndWait();
            return true;
        } else if (consequence == -1) {
            // tie
            new Alert(Alert.AlertType.INFORMATION, "Tie!").showAndWait();
            return true;
        }
        return false;
    }


    /**
     * this method will create circles and add them into TilePane
     * @param row the number of row
     * @param col the number of col
     * @return a list of list with Circles
     */
    private List<List<Circle>> createCircles(int row, int col) {
        List<List<Circle>> circleLists = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            List<Circle> circleList = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                Circle circle = new Circle(20);
                circle.setFill(Color.WHITE);
                circleList.add(circle);
            }
            circleLists.add(circleList);
        }
        return circleLists;
    }


    /**
     * according to the location change color
     * @param xLocation the double show the location and change the circle's color
     */
    private boolean put(double xLocation) {
        boolean consequence;
        int x;
        if(xLocation<=4){
            x = 0;
        }else {
            x = ((int) xLocation-4) / 48;
        }

        if (x == 7) {
            consequence = connect4Controller.humanTurn(6);
        } else {
            consequence = connect4Controller.humanTurn(x);

        }
        if (!consequence) {
            new Alert(Alert.AlertType.WARNING, "Invalid Location!").showAndWait();
        }
        return consequence;
    }

    /**
     * Override method
     * @param connect4Model the Model class
     * @param arg parameter show character
     */
    @Override
    public void update(Observable connect4Model, Object arg) {
//        System.out.println("update");
        Connect4MoveMessage connect4MoveMessage = (Connect4MoveMessage)arg;
        if (connect4MoveMessage != null) {
            // set circle
            circles.get(((Connect4MoveMessage) arg).getRow()).get(((Connect4MoveMessage) arg).getCol())
                    .setFill(((Connect4MoveMessage) arg).getColor());
        }
    }
}
