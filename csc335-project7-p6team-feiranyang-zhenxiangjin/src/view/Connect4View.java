package view;

import controller.Connect4Controller;
import javafx.application.Application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Connect4Model;
import model.Connect4MoveMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
    private MenuItem item, netWorkButton;
    private BorderPane pane;
    private static String NETWORKTYPE, PLAYERTYPE, SERVER;
    private static int PORT = 4000;
    private int GAME_OVER = 2;
    private boolean SERVER_ON = false;
    private boolean CLIENT_ON = false;
    private Socket socket;
    private int curr = 0;

    public static int YELLOW = 1;
    public static int RED = 2;
    public static int WHITE =0;



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

        // menu part
        MenuBar bar = new MenuBar();
        Menu menu = new Menu("File");
        item = new MenuItem("New Game");
        netWorkButton = new MenuItem("Networked Game");
        menu.getItems().addAll(item);
        menu.getItems().add(netWorkButton);
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

        // add event handler for netWorkButton
        netWorkButton.setOnAction(event -> {
            Dialog dialog = new Dialog();
            dialog.setTitle("Network Setup");
            VBox vbox = new VBox(20);
            // the first line
            HBox createType = new HBox(2);
            Text createText = new Text("Create: ");
            ToggleGroup buttonGroup = new ToggleGroup();
            RadioButton server = new RadioButton("Server");
            server.setToggleGroup(buttonGroup);
            server.setSelected(true);

            RadioButton client = new RadioButton("Client");
            client.setToggleGroup(buttonGroup);
            createType.getChildren().add(createText);
            createType.getChildren().add(server);
            createType.getChildren().add(client);
            vbox.getChildren().add(createType);
            // the second line
            HBox roleType = new HBox();
            Text roleText = new Text("Play as: ");
            roleType.getChildren().add(roleText);
            ToggleGroup roleGroup = new ToggleGroup();
            RadioButton human = new RadioButton("Human");
            human.setToggleGroup(roleGroup);
            human.setSelected(true);
            RadioButton computer = new RadioButton("Computer");
            computer.setToggleGroup(roleGroup);
            roleType.getChildren().addAll(human, computer);
            vbox.getChildren().add(roleType);
            // the third line
            HBox protocol = new HBox(5);
            Text serverText = new Text("Server");
            TextField serverInfo = new TextField("localhost");
            Text portText = new Text("Port");
            TextField portInfo = new TextField("4000");
            // ses the port can only type number
            portInfo.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    portInfo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
            protocol.getChildren().addAll(serverText, serverInfo, portText, portInfo);
            vbox.getChildren().add(protocol);

            // button of confirm and cancel
            HBox buttons = new HBox(5);
            Button confirm = new Button("OK");
            confirm.setDefaultButton(true);
            confirm.setOnAction(event1 -> {
                NETWORKTYPE = ((RadioButton)buttonGroup.getSelectedToggle()).getText();
                PLAYERTYPE = ((RadioButton)roleGroup.getSelectedToggle()).getText();
                SERVER = serverInfo.getText().trim();
                PORT = Integer.valueOf(portInfo.getText().trim());
                System.out.printf("NetWorkType is %s, PlayerType is %s, Server is %s, Port is %s",
                        NETWORKTYPE, PLAYERTYPE, SERVER, PORT);
                dialog.close();
                System.out.println("close dialog");
                // start new game
                setTiles();
                connect4Model = new Connect4Model();
                connect4Model.addObserver(this);
                connect4Controller.setModel(connect4Model);
                // try to start the server or client
                if (NETWORKTYPE.equals("Server")) {
                    if (!SERVER_ON) {
                        // create server
                        ServerSocket serverSocket = null;

                        try {
                            serverSocket = new ServerSocket(PORT);
                            System.out.println("Server started with PORT: " + PORT);
                            socket = serverSocket.accept();
                            System.out.println("Connected");

                        } catch (IOException e) {
                            System.out.println("getting wrong when creating server socket");
                        }

                        if(PLAYERTYPE.equals("Computer")){
                            while(!checkIsGameOver()){
                                connect4Controller.smartComputerTurn(1);
                            }
                        }
                        // run through new thread

                        SERVER_ON = true;
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Already has a server");
                    }
                } else if (NETWORKTYPE.equals("Client")) {
                    // try to connect to the server
                    try {
                        socket = new Socket(SERVER, PORT);
                        System.out.println("Connected to Server");
                    } catch (IOException e) {
                        System.out.println("Cannot connect to the server.");
                    }
                    if(PLAYERTYPE.equals("Computer")) {
                        Thread clientThread = new ClientNetWorkInput();
                        clientThread.run();
                        connect4Controller.smartComputerTurn(2);
                    }
                }
            });

            Button cancel = new Button("Cancel");
            cancel.setCancelButton(true);
            cancel.setOnAction(event1 -> dialog.close());
            buttons.getChildren().addAll(confirm, cancel);
            // add invisible button to get "x" button and cancel button work
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
            closeButton.managedProperty().bind(closeButton.visibleProperty());
            closeButton.setVisible(false);
            vbox.getChildren().add(buttons);

            dialog.getDialogPane().setContent(vbox);
            dialog.showAndWait();

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
     *
     * @param row
     * @param col
     * @param color
     * @return
     */
    private boolean dyeCircle(int row, int col, int color) {
        Color newColor;
        if (color == YELLOW) {
            newColor = Color.YELLOW;
        } else if (color == RED) {
            newColor = Color.RED;
        } else {
            // return false to show the color is not in panel, or using different color,
            // or wrong color
            return false;
        }
        circles.get(row).get(col).setFill(newColor);
        return true;
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
            Color color;
            if(connect4MoveMessage.getColor() == RED){
                color = Color.RED;
            }else if(connect4MoveMessage.getColor() == YELLOW){
                color= Color.YELLOW;
            }else{
                color = Color.WHITE;
            }
            // set circle
            circles.get(((Connect4MoveMessage) arg).getRow()).get(((Connect4MoveMessage) arg).getCol())
                    .setFill(color);
            if(curr == 0) {
                curr = 1;
                Thread serverThread = new ServerNetwork(connect4MoveMessage);
                serverThread.run();
            }else{
                curr = 0;
            }
        }
    }

    private class ServerNetwork extends Thread{
        private Connect4MoveMessage moveMessage;
        private  ServerNetwork(Connect4MoveMessage connect4MoveMessage) {
            moveMessage = connect4MoveMessage;
        }
        @Override
        public void run() {
            System.out.println("in Run method(server) ->");
//            super.run();
            try {
//                ServerSocket server = new ServerSocket(PORT);
//                System.out.println("Server started with PORT: " + PORT);
//                while (GAME_OVER == 2) {

                    System.out.println("Client connected!");
                    // going to handle this connection
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    //Connect4MoveMessage TESTmsg = new Connect4MoveMessage(2,2,1);
                    out.writeObject(moveMessage);
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    //getting information from client
                    Connect4MoveMessage move = (Connect4MoveMessage) in.readObject();
                    System.out.println("sever get: " +move);
                    connect4Controller.turn(move.getCol(), move.getColor());
                    // or else, dye successful
                    // check if game over, if game over, close server and break out
                    GAME_OVER = connect4Controller.isGameOver();

                    // output game info

//                }

            }catch (IOException e) {
                System.out.println("ServerIOError: " + e.getMessage());
            }catch (ClassNotFoundException e){
                System.out.println("ServerClassNotFoundError: " + e.getMessage());
            }

            System.out.println("<- out Run method(server)");
        }
    }

    private class ClientNetWorkInput extends Thread {
        @Override
        public void run() {
            //test run
            System.out.println("in Run method ->");
            try {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Connect4MoveMessage moveMessage = (Connect4MoveMessage) in.readObject();
                System.out.println("client get : " +moveMessage);
                connect4Controller.turn(moveMessage.getCol(), 1);
                System.out.println("in Run method 1->");
                // send server info
//                Connect4MoveMessage TESTmsg = new Connect4MoveMessage(2,2,1);
//                out.writeObject(TESTmsg);
                System.out.println("in Run method 2->");


            } catch (IOException e) {
                System.out.println("ServerIOError: " + e.getMessage());
            }
            catch (ClassNotFoundException e){
                System.out.println("ServerClassNotFoundError: " + e.getMessage());
            }
            System.out.println("<- out Run method.");
        }
    }
}
