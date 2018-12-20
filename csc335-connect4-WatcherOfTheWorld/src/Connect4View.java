import java.util.Scanner;

/**
 * File: Connect4View
 *
 * this class print out msg to console and read in input from user input
 *
 * @author Feiran Yang
 */
public class Connect4View {

    private Scanner scanner;
    public Connect4View(char[][] board){
        firstRun(board);
    }

    /**
     * print an welcome msg and board
     * @param board char 2d array for represent board
     */
    private void firstRun(char[][] board){
        System.out.println("\nWelcome to Connect 4\n");
        printBoard(board);
        System.out.println("\nYOU are X\n");
        scanner = new Scanner(System.in);
    }

    /**
     * get a input int to main
     * @return user input init
     */
    public int getInput(){
        System.out.println("What column would you like to place your token in?");
        if(scanner.hasNextLine()){
            String nextMove = scanner.nextLine().trim();
            return Integer.parseInt(nextMove);
        }else{
            System.out.println("Reaches EOF, Exiting with code 1.");
            System.exit(1);
        }
        return -1;
    }

    /**
     * print result
     * when arg is 1, print user wins, -1 print computer wins
     * otherwise is draw
     * @param result result of who wins
     */
    public void printResult(int result){
        if(result == 1){
            System.out.println("YOU win!");
        }else if(result == -1){
            System.out.println("Computer wins!");
        }else{
            System.out.println("Draw!");
        }
        System.out.println();
    }

    /**
     * print out the board based on input 2d array
     * @param board 2d array board
     */
    public void printBoard(char[][] board){
        String string = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                string+= board[i][j];
                string+= " ";
            }
            string += "\n";
        }
        // add index at the end of string
        string+="0 1 2 3 4 5 6\n";
        System.out.println(string);
    }

    /**
     * print out of bound err msg to console
     */
    public void printOutBoundErr(){
        System.out.println("Out of bound, try another");
    }

    /**
     * print an Number format err to console
     */
    public void printNumberErr(){
        System.out.println("this is not a number, try another");
    }

}
