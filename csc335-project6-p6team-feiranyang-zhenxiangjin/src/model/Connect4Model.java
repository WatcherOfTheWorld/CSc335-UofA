package model;

import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.Observable;

/**
 * This class is a model class for Connect4 game, controller could use the method
 * to control game going
 * @author Shawn Jin, Feiran Yang
 */
public class Connect4Model extends Observable implements Serializable {

    static final long serialVersionUID = 1;

    private final char BLANK = '_';
    private final char X = 'X';
    private final char O = 'O';

    private final int column = 7;
    private final int row = 6;

    private char[][] board;

    public Connect4Model(){
        board = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j ++) {
                board[i][j] = BLANK;
            }
        }
    }

    /**
     *
     * @param sign the sign that will be input
     * @param col the col that will be put
     * @throws WrongLocationException a exception tells wrong location
     */
    public void put(String sign, int col) throws WrongLocationException {
//        System.out.println("PUT!!!!");
        int temRow = 0;
        if (0 > col || col >= this.column || board[temRow][col] != BLANK) {
            throw new WrongLocationException("");
        }
        switch (sign.toUpperCase()) {
            case "X":
                board[temRow][col] = X;
                break;
            case "O":
                board[temRow][col] = O;
                break;
            default:
        }

        // go deeper until it not empty
        int nextLevel = temRow + 1;
        while (nextLevel < this.row && board[nextLevel][col] == BLANK) {
            board[nextLevel][col] = board[temRow][col];
            board[temRow][col] = BLANK;
            temRow++;
            nextLevel++;
        }
//        System.out.printf("Row is %d, Col is %d\n", temRow, col);
        // set color for human and computer (computer -- Red, human -- Yellow)
        Color color;
        if (sign.toUpperCase().equals("X")) {
            color = Color.YELLOW;
        } else {
            color = Color.RED;
        }
        Connect4MoveMessage connect4MoveMessage = new Connect4MoveMessage(temRow, col, color);
        setChanged();
        notifyObservers(connect4MoveMessage);
    }

    /**
     * this method will remove the top char of the col
     * @param col the number of col
     */
    public void remove(int col) {
        int temRow = 0;
        while (temRow <= getRow()-1 && getLocation(temRow, col) == BLANK) {
            temRow++;
        }
        // if find this row is not empty, clean the location
        if (temRow <= getRow()-1) {
            board[temRow][col] = BLANK;
        }

        // update the view
        Color color = Color.WHITE;
        Connect4MoveMessage connect4MoveMessage = new Connect4MoveMessage(temRow, col, color);
        setChanged();
        notifyObservers(connect4MoveMessage);
    }
    /**
     * get the char at that location
     * @param row the row of that location
     * @param col the col of that location
     * @return the char at that location
     */
    public char getLocation(int row, int col) { return board[row][col]; }

    /**
     * return the game board's col
     * @return the game board's col
     */
    public int getCol() { return this.column; }

    /**
     * return the game board's row
     * @return the game board's row
     */
    public int getRow() {  return this.row; }

    /**
     * return the sign of X in the board
     * @return the sign of X in the board
     */
    public char getX() { return this.X; }

    /**
     * return the sign of O in the board
     * @return the sign of O in the board
     */
    public char getO() { return this.O; }

    /**
     * return the sign of Blank in the board
     * @return the sign of Blank in the board
     */
    public char getBLANK() {return this.BLANK; }

    /**
     * clear the board
     */
    public void clear() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j ++) {
                board[i][j] = BLANK;
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * convert the class to visualize
     * @return a string show this class
     *
     */
    @Override
    public String toString() {
        String content = "";
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j ++) {
                content += board[i][j];
            }
            content += "\n";
        }
        for (int k = 0; k < column; k ++) {
            content += k;
        }
        content += "\n";
        return content;
    }

}
