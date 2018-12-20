import javafx.application.Application;

/**
 * File: Connect4.java
 *
 * this class starts the connect 4 game.
 * while game is not over, let computer or human continue to play
 * otherwise print out result and exit
 *
 * @author Feiran Yang
 */
public class Connect4 {
    public static void main(String[] args){
        Application.launch(Connect4View.class, args);
//
//        //Connect4View newVeiw = new Connect4View();
//        boolean isComputer = false;
//        Connect4Model model = new Connect4Model();
//        //model.addObserver(newVeiw);
//        Connect4Controller controller = new Connect4Controller(model);
//        Connect4ViewOld view = new Connect4ViewOld(controller.getBoard());
//        while(!controller.isGameOver()){
//            if(isComputer){
//                controller.computerTurn();
//                isComputer = false;
//            }else {
//                try {
//                    placeToken(view, controller);
//                }catch (NumberFormatException e){
//                    view.printNumberErr();
//                    placeToken(view, controller);
//                }
//                isComputer = true;
//            }
//            view.printBoard(controller.getBoard());
//        }
//        // print result when game is over
//        view.printResult(controller.getResult());

    }

    /**
     * get a user input int from view and try to place a token in board
     * if failed, call view to let user input a new number
     * @param view
     * @param controller
     */
    private static void placeToken(Connect4ViewOld view, Connect4Controller controller){
        int nextMove = view.getInput();
        try {
            controller.humanTurn(nextMove);
        }catch(IndexOutOfBoundsException e){
            // print an err msg and re-ask user input number
            view.printOutBoundErr();
            placeToken(view,controller);
        }
    }

}
