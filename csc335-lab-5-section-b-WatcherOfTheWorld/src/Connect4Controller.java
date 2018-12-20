import java.util.Random;

/**
 * File: Connect4Controller.java
 *
 * this controller reads in an model
 * take user input int and make changes to model
 * this controller also returns result from model
 *
 * @author Feiran Yang
 */
public class Connect4Controller {

    private Connect4Model model;

    /**
     * pass in a model to construct this obj
     * @param model Connect4Model
     */
    public Connect4Controller(Connect4Model model){
        this.model = model;
    }

    /**
     * check if is the game is over
     * @return if the game is over
     */
    public boolean isGameOver(){
        char[][] board = getBoard();
        boolean isOver = true;
        for(int i = 0; i<board[0].length; i++){
            if(board[0][i] == '_'){
                isOver = false;
                break;
            }
        }
        if (!isOver && getResult() != 0) {
            isOver = true;
        }
        return isOver;
    }

    /**
     * human's turn, place an x token at nextMove place
     * @param nextMove position that need to be place
     */
    public void humanTurn(int nextMove){
        model.placeX(nextMove);
    }

    /**
     * computer turn, place an O token at random place
     */
    public void computerTurn(){
        try {
            int nextMove = genRandomPlace();
            model.placeO(nextMove);
        // if failed to place, get a new random column
        }catch (IndexOutOfBoundsException e){
            computerTurn();
        }
    }

    /**
     * for testing purpose
     * @param nextMove column need to be put
     */
    public void computerTurn(int nextMove){
        model.placeO(nextMove);
    }

    /**
     * gen an new random number in the range of 0 to 6
     * @return random position
     */
    private int genRandomPlace(){
        Random random = new Random();
        return random.nextInt(7);
    }

    /**
     * return an 2d array to rep an board
     * @return representation of board
     */
    public char[][] getBoard(){
        return model.getBoard();
    }

    /**
     * return who wins
     * @return winner, 0 represents no winner
     */
    public int getResult(){
        if(getResult('X')){
            return 1;
        }else if(getResult('O')){
            return -1;
        }else {
            return 0;
        }
    }


    /**
     * return is one user wins
     * @param user user's token
     * @return if user wins
     */
    private boolean getResult(char user){
        char[][] board = getBoard();
        // Row wins
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j < board[i].length-3;j++){
                if(board[i][j] == user && board[i][j+1] == user && board[i][j+2] == user && board[i][j+3] == user){
                    return true;
                }
            }
        }

        // Col wins
        for(int i = 0; i<board[0].length; i++){
            for(int j = 0; j < board.length-3; j++){
                if(board[j][i] == user && board[j+1][i] == user && board[j+2][i] == user && board[j+3][i] == user){
                    return true;
                }
            }
        }

        // Diagonally
        for(int i = 0; i < board.length-3;i++){
            for(int j = 0; j<board[i].length-3; j++){
                if(board[i][j] == user && board[i+1][j+1] == user && board[i+2][j+2] == user && board[i+3][j+3] ==user){
                    return true;
                }
            }
        }

        // Diagonally the other way
        for(int i = 0 ; i < board.length-3; i++ ){
            for(int j = 3; j < board[i].length; j++){
                if(board[i][j] == user && board[i+1][j-1] == user && board[i+2][j-2] == user && board[i+3][j-3] ==user){
                    return true;
                }
            }
        }

        return false;
    }
}

