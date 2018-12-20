import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MastermindTextGUI extends Application {
    boolean win = false;
    TextArea textArea;
    TextField textField;
    String text = "";
    int count = 0;
    MastermindController controller = new MastermindController(new MastermindModel());
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("摸了¿");
        BorderPane p = new BorderPane();
        textArea = new TextArea();
        textField = new TextField();
        p.setTop(textArea);
        p.setBottom(textField);
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        textField.setPromptText("Type your guess (Enter to submit)");
        textField.setOnKeyPressed((event -> {
            if(event.getCode() == KeyCode.ENTER) {
                play(textField.getText());
                textField.clear();
            }
        }));
        textArea.appendText("Welcome to Mastermind GUI\n");
        primaryStage.show();
    }

    public void play(String guess){
        // Check whether or not the input is correct (by asking the controller)
        boolean correct = false;
        try {
            correct = controller.isCorrect(guess);
        } catch (MastermindIllegalColorException e){
            System.err.println("Wrong Color, Pleas try again.");
            //textArea.appendText("Wrong Color, Pleas try again.");
            new Alert(Alert.AlertType.ERROR, "Wrong Color, Pleas try again.").showAndWait();
            return;
        } catch (MastermindIllegalLengthException e){
            System.err.println("You must enter 4 char.");
            //textArea.appendText("You must enter 4 char.");
            new Alert(Alert.AlertType.ERROR, "You must enter 4 char.").showAndWait();
            return;
        }
        if (!correct) {
            // If not, display the relevant statistics  (by asking the controller)
            System.out.println("Colors in the correct place: " + controller.getRightColorRightPlace(guess));
            System.out.println("Colors correct but in wrong position: " + controller.getRightColorWrongPlace(guess));
            textArea.appendText("Guess "+count+": "+guess+"   ");
            textArea.appendText("RCRP: " + controller.getRightColorRightPlace(guess)+"   ");
            textArea.appendText("PCWP: " + controller.getRightColorWrongPlace(guess)+"\n");
            System.out.println();
            count ++;
        } else {
            win = true;
            textArea.appendText("You Win");
            textField.setEditable(false);
            return;
        }
        System.out.println(count);
        if(count > 9){
            textArea.appendText("\nYou lose");
            textField.setEditable(false);
            System.out.println("?????");
        }
    }


}
