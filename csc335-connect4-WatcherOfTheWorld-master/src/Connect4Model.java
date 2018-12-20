/**
 * File: Connect4Model.java
 *
 * this class represent an connect4 board
 * caller can place token, get game status, get result or get 2d array representation of broad from this class
 *
 * @author Feiran Yang
 */

public class Connect4Model {
    private char[][] board = {{'_','_','_','_','_','_','_'},{'_','_','_','_','_','_','_'},
            {'_','_','_','_','_','_','_'},{'_','_','_','_','_','_','_'},{'_','_','_','_','_','_','_'},
            {'_','_','_','_','_','_','_'}};

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
        if(x > 6 || x < 0){
            throw new IndexOutOfBoundsException("column out of bound");
        }

        boolean set = false;
        // try to set an token in inputted column
        for(int i =  5; i >= 0; i--){
            if(board[i][x] == '_'){
                board[i][x] = player;
                set = true;
                break;
            }
        }

        // if failed, the column is fall, throw an exception
        if(!set){
            throw new IndexOutOfBoundsException("stack is full");
        }
    }
}
