import java.util.Scanner;

/**
 * main class of Mastermind Program
 *
 * this class will start a tread for game
 * this class will first ask if user want to play
 * if user says yes, it create a model for storing answer data
 * and a controller to computer result
 *
 * this class play every term for at most 10 times.
 * if user guessed the right answer within 10 times, print a you win in screen
 * otherwise print you lose
 *
 * after each term, program will ask user again to see if user want to play one more times
 *
 * @author Feiran Yang
 *
 */
public class Mastermind {

	public static void main(String[] args) {
		// This class represents the view, it should be how uses play the game
		System.out.println("Welcome to Mastermind!");

        // while the user wants to play:
        System.out.print("Would you like to play?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            if(!scanner.nextLine().trim().toLowerCase().equals("yes")){
                return;
            }else{
                play(scanner);
                // after each play, ask if want play one more time.
                System.out.print("\nWould you like to play?");
            }
        }
	}
	
	/**
	 * run the game for one time.
	 * 
	 * @param scanner : for read in user input
	 */
	public static void play(Scanner scanner){
	    // Construct the model (whose constructor builds the secret answer)
        MastermindModel model = new MastermindModel();

        // Construct the controller, passing in the model
        MastermindController controller = new MastermindController(model);

        boolean win = false;

        System.out.println();
        // Read up to ten user inputs
        for(int i = 1; i <= 10 ;i ++){
            System.out.print("Enter guess number " + i + ":");
            if(scanner.hasNextLine()) {
                String guess = scanner.nextLine().trim();
                // Check whether or not the input is correct (by asking the controller)
                boolean correct = false;
                try {
                     correct = controller.isCorrect(guess);
                } catch (MastermindIllegalColorException e){
                    System.err.println("Wrong Color, Pleas try again.");
                    i --;
                    continue;
                } catch (MastermindIllegalLengthException e){
                    System.err.println("You must enter 4 char.");
                    i --;
                    continue;
                }
                if (!correct) {
                    // If not, display the relevant statistics  (by asking the controller)
                    System.out.println("Colors in the correct place: " + controller.getRightColorRightPlace(guess));
                    System.out.println("Colors correct but in wrong position: " + controller.getRightColorWrongPlace(guess));
                    System.out.println();
                } else {
                    win = true;
                    break;
                }
            }
        }

        // Determine win or loss
        if(win){
            System.out.println("You Win");
        }else {
            System.out.println("You Lose");
        }
    }

}
