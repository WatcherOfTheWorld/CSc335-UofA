/**
 * main class of cryptograms Program
 *
 * this class will start a tread for game
 * this class will first create controller, model and view
 *
 * this class will then call view to ask user input two char for play the game
 * continue ask until the user guessed the right string
 *
 * after user get the right answer, print a msg shows that he wins
 *
 * @author Feiran Yang
 *
 */
public class Cryptograms {
    public static void main(String[] args) {
        CryptogramsView view = new CryptogramsView();
        CryptogramsController controller = new CryptogramsController(new CryptogramsModel());
        while(!controller.isGameOver()){
            makeInput(view, controller);
        }
        // print out the result
        view.printResult(controller.getGuess(), controller.getWords());
    }

    /**
     * this method calls view to get user input, if there is nothing wrong with the user input
     * call controller to make changes to model.
     * if an exception has been throw in above process, call view to print an err msg
     * @param view CryptogramsView 
     * @param controller CryptogramsController
     */
    private static void makeInput(CryptogramsView view, CryptogramsController controller){
        try {
            char[] input = view.getInput(controller.getGuess(), controller.getWords());
            controller.replaceChar(input[0], input[1]);
        // catch exception
        }catch (IllegalArgumentException e){
            view.printErr(e.getMessage());
            if(e.getMessage().equals("EOF, exit with code 1")){
                System.exit(1);
            }
            // try to read in other input
            makeInput(view, controller);
        }
    }
}
