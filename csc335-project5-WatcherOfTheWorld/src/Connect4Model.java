import java.util.Observable;

/**
 * File: Connect4Model.java
 *
 * this class represent an connect4 board
 * caller can place token, get game status, get result or get 2d array representation of broad from this class
 *
 * @author Feiran Yang
 */

public class Connect4Model extends Observable {
    private Boolean[][] board;
    public Connect4Model(){
        Boolean[][] board = new Boolean[6][7];
        this.board = board;
    }

    /**
     * place an 'X' token at column x
     * @param x column
     */
    public void placeX(int x){
        place(x, 'X');
    }

    /**
     * place an 'O' token at column x
     * @param x column
     */
    public void placeO(int x){
        place(x,'O');
    }

    /**
     * return en 2d array board out
     * @return 2d array board
     */
    public char[][] getBoard(){
        char[][] board = new char[this.board.length][this.board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j ++){
                if(this.board[i][j] == null){
                    board[i][j] = '_';
                }else if(this.board[i][j] == false){
                    board[i][j] = 'X';
                }else{
                    board[i][j] = 'O';
                }
            }
        }
        return board;
    }

    /**
     * place an player's token in [x] column
     * @param x column that need to add token
     * @param player player's token
     * @throws IndexOutOfBoundsException column out of bound or stack is full
     */
    private void place(int x, char player){
        // is x large than 6 or smaller than 0, throw an out of bound exception
        if(x > board[0].length || x < 0){
            throw new IndexOutOfBoundsException("column out of bound");
        }

        boolean set = false;
        // try to set an token in inputted column
        int i;
        for(i =  board.length-1; i >= 0; i--){
            if(board[i][x] == null){
                if(player == 'O'){
                    board[i][x] = true;
                }else if(player == 'X'){
                    board[i][x] = false;
                }
                set = true;
                break;
            }
        }

        // if failed, the column is fall, throw an exception
        if(!set){
            throw new IndexOutOfBoundsException("stack is full");
        }

        // send changes to observers, include col, raw and player
        int[] array = {x,i, player};
        setChanged();
        notifyObservers(array);
    }


}
