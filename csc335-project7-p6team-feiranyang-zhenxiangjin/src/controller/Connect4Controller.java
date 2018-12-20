package controller;

import model.*;
import java.util.Random;

/**
 *  This is controller class, and it will control and check how is Connect4 game going
 * @author Shawn Jin, Feiran Yang
 */
public class Connect4Controller {
    private Connect4Model model;
    private final int NUMBER_OF_WIN = 4;
    public Connect4Controller(Connect4Model model) { this.model = model; }

    /**
     * set a new model for the game
     * @param model a new Connect4Model
     */
    public void setModel(Connect4Model model){
        this.model = model;
    }

    /**
     * check if game is over, and return numbers to represent who is winner
     * @return a number to represent who is winner (0 -- computer; 1 -- user;
     * -1 ---tie;2--keep going)
     */
    public int isGameOver(){

        // check in col
        for (int i=0; i<model.getRow(); i++) {
            int numberOfSameInRow = 1;
            for (int j = 0; j < model.getCol() - 1; j++) {
                // check in col
                if (model.getLocation(i,j) != model.getBLANK()
                        && model.getLocation(i,j) == model.getLocation(i,j+1)) {
                    numberOfSameInRow++;
                } else { numberOfSameInRow = 1; }
                if (numberOfSameInRow == NUMBER_OF_WIN) {
                    // check if is user win or computer win
                    if (model.getLocation(i,j) == model.getO()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        }

        // check in row
        for (int j = 0; j < model.getCol(); j++) {
            int numberOfSameInCol = 1;
            for (int i=0; i<model.getRow()-1; i++) {
                if (model.getLocation(i,j) != model.getBLANK()
                        && model.getLocation(i, j) == model.getLocation(i+1, j)) {
                    numberOfSameInCol++;
                } else { numberOfSameInCol = 1; }
                if (numberOfSameInCol == NUMBER_OF_WIN) {
                    if (model.getLocation(i,j) == model.getO()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }

        }


        int endInRow = model.getRow() - NUMBER_OF_WIN;
        // -2
        int endInCol = model.getCol() - NUMBER_OF_WIN;
        // row: 0 - endInRow
        // col: 0 - end

        // check up2down upper
        for (int row = 0; row <= endInRow; row++) {
            int col = 0;
            int consequence = checkDiagonallyUp2Down(row, col);
            if (consequence != -1){ return consequence;}
        }
        // check up2down nether
        for (int col = 0; col <= endInCol; col++) {
            int row = 0;
            int consequence = checkDiagonallyUp2Down(row, col);
            if (consequence != -1){ return consequence;}
        }
        // check down2up upper
        for (int row = model.getRow() - 1; row > 0; row--) {
            int col = 0;
            int consequence = checkDiagonallyDown2Up(row, col);
            if (consequence != -1) { return consequence; }
        }
        // check down2up nether
        for (int col = model.getCol() - 1; col > 0; col--) {
            int row = model.getRow() - 1;
            int consequence = checkDiagonallyDown2Up(row, col);
            if (consequence != -1) { return consequence; }
        }

        // check if the board is full
        for (int col = 0; col < model.getCol(); col++){
            if (model.getLocation(0,col) == model.getBLANK()){
                return 2;
            }
        }
        // 2 means no body win and not game over
        return -1;
    }

    /**
     * check diagonally up to down according to the location
     * @param row the location -- row
     * @param col the location -- col
     * @return a number to represent who is winner(0 -- computer; 1 -- user;
     * -1 -- nobody win)
     */
    private int checkDiagonallyUp2Down(int row, int col) {
//        System.out.printf("1checked (%d,%d)\n", row, col)
        int numberOfSameInDia = 1;
        while (row + 1 < model.getRow() && col + 1 < model.getCol()) {

            if (model.getLocation(row, col) != model.getBLANK()
                    && model.getLocation(row,col) == model.getLocation(row+1,col+1)) {
                //  System.out.printf("checked (%d,%d) = (%d,%d)\n",row, col, row+1, col+1)
                numberOfSameInDia++;
            } else { numberOfSameInDia = 1; }
            if (numberOfSameInDia == NUMBER_OF_WIN) {
                if (model.getLocation(row,col) == model.getO()) {
                    return 0;
                } else {
                    return 1;
                }
            }
            col++;
            row++;
        }
        // nobody win
        return -1;
    }

    /**d
     * check diagonally down to up according to the location
     * @param row the location -- row
     * @param col the location -- col
     * @return a number to represent who is winner(0 -- computer; 1 -- user;
     * -1 -- nobody win)
     */
    private  int checkDiagonallyDown2Up(int row, int col) {
        // row--, col++
//        System.out.printf("2checked (%d,%d)\n", row, col)
        int numberOfSameInDia = 1;
        while(row - 1 >= 0 && col + 1 < model.getCol()) {

            if (model.getLocation(row, col) != model.getBLANK()
                    && model.getLocation(row,col) == model.getLocation(row-1,col+1)) {
//                System.out.printf("checked (%d,%d) = (%d,%d)\n",row, col, row-1, col+1)
                numberOfSameInDia++;
            } else { numberOfSameInDia = 1; }
            if (numberOfSameInDia == NUMBER_OF_WIN) {
                if (model.getLocation(row,col) == model.getO()) {
                    return 0;
                } else {
                    return 1;
                }
            }
            row--;
            col++;
        }
        return -1;
    }


    /**
     * let user input
     * @param col the col that the user want to put
     * @return a boolean represent if it is successful to put
     */
    public boolean humanTurn(int col) {
        try {
            model.put("X", col);
            return true;
        } catch (WrongLocationException ex) {
            //System.out.println("Invalid col, please try again!");
            return false;
        }
    }

    public Connect4MoveMessage turn(int col, int color){
        String player;
        if(color == 1){
            player = "X";
        }else{
            player = "O";
        }
        Connect4MoveMessage message;
        try{
            int row = model.put(player, col);
            message = new Connect4MoveMessage(row, col, color);
        }catch (WrongLocationException e){
            return null;
        }
        return message;
    }


    public boolean computerTurn(int color) {
        String player;
        if(color == 1){
            player = "X";
        }else {
            player = "O";
        }
        Random random = new Random();
        int nextCol = random.nextInt(model.getCol());
        // find a valid location and put O in
        while (this.isGameOver() == 2) {
            try {
                model.put(player, nextCol);
                return true;
            } catch (WrongLocationException ex) {
                nextCol = random.nextInt(model.getCol());
            }
        }
        return false;
    }

    /**
     * let computer input
     * @return a boolean represent if it is successful to put
     */
    public boolean computerTurn() {
        Random random = new Random();
        int nextCol = random.nextInt(model.getCol());
        // find a valid location and put O in
        while (this.isGameOver() == 2) {
            try {
                model.put("O", nextCol);
                return true;
            } catch (WrongLocationException ex) {
                nextCol = random.nextInt(model.getCol());
            }
        }
        return false;
    }

    public boolean smartComputerTurn(int color) {
        String player, opp;
        if(color == 1){
            player = "X";
            opp = "O";
        }else{
            player = "O";
            opp = "X";
        }

        // check if the user will win
        for (int i = 0; i < model.getCol(); i++) {
            // human's sign is "X"
            try {
                model.put(player, i);
            } catch (WrongLocationException e) {
                continue;
            }
            int consequence1 = isGameOver();
            // clear the location
            model.remove(i);
            // computer's sign is "O"
            try {
                model.put(opp, i);
            } catch (WrongLocationException e) {
                continue;
            }
            int consequence2 = isGameOver();
            // clear the location
            model.remove(i);
            // if someone is going to win
            if (consequence1 == 1 || consequence2 == 0) {
                try {
                    model.put(opp, i);
                } catch (WrongLocationException e) {
                    continue;
                }
                return true;
            }
        }
        return computerTurn(color);
    }

    /**
     * let computer input, but the computer would check if the user is going
     * to win. if the answer is yes, the computer will deter the user winning
     * @return a boolean represent if put successfully
     */
    public boolean smartComputerTurn() {
        return smartComputerTurn(2);
    }

    /**
     * return the game board's row
     * @return the game board's row
     */
    public int getRow(){
        return model.getRow();
    }

    /**
     * return the game board's col
     * @return the game board's col
     */
    public int getCol(){
        return  model.getCol();
    }

    /**
     * get the char at that location
     * @param x the row of that location
     * @param y the col of that location
     * @return the char at that location
     */
    public char getLoc(int x, int y){
        return model.getLocation(x,y);
    }

    /**
     * convert the class to visualize
     * @return a string show this class
     */
    @Override
    public String toString() {
        return this.model.toString();
    }


}
