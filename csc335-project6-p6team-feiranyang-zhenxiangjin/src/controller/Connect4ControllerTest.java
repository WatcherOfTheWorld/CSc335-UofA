
package controller;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Shawn Jin
 */
class Connect4ControllerTest {

    @Test
    void isGameOverTest(){
        // test O win in row
        Connect4Model board = new Connect4Model();
        Connect4Controller connect4Controller = new Connect4Controller(board);
        try {
            board.put("O", 0);
            board.put("O", 1);
            board.put("O", 2);
            board.put("O", 3);
        } catch (WrongLocationException e){
            System.out.println(e.toString());
        }
        System.out.println(board);
        assertEquals(0, connect4Controller.isGameOver());
        // test X win in row
        board.clear();
        try {
            board.put("X", 0);
            board.put("X", 1);
            board.put("X", 2);
            board.put("X", 3);
        } catch (WrongLocationException e){
            System.out.println(e.toString());
        }
        System.out.println(board);
        assertEquals(1, connect4Controller.isGameOver());
        // test X win in col
        Connect4Model board2 = new Connect4Model();
        connect4Controller = new Connect4Controller(board2);
        try {
            board2.put("X",6);
            board2.put("X",6);
            board2.put("X",6);
            board2.put("X",6);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        System.out.println(board2);
        assertEquals(1, connect4Controller.isGameOver());
        // test O win in col
        board2.clear();
        try {
            board2.put("O",6);
            board2.put("O",6);
            board2.put("O",6);
            board2.put("O",6);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        System.out.println(board2);
        assertEquals(0, connect4Controller.isGameOver());

        // test left-down 2 right-up (part 1 of 2)
        Connect4Model board3 = new Connect4Model();
        connect4Controller = new Connect4Controller(board3);
        try {
            board3.put("O", 0);
            board3.put("X", 0);

            board3.put("X", 1);
            board3.put("O", 1);
            board3.put("X", 1);

            board3.put("X", 2);
            board3.put("O", 2);
            board3.put("O", 2);
            board3.put("X", 2);

            board3.put("X", 3);
            board3.put("O", 3);
            board3.put("O", 3);
            board3.put("X", 3);
            board3.put("X", 3);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        System.out.println(board3);
        assertEquals(1, connect4Controller.isGameOver());

        // clear the board
        board3.clear();

        // test left-down 2 right-up (part 2 of 2)
        connect4Controller = new Connect4Controller(board3);
        try {
            board3.put("O", 3);
            board3.put("X", 3);

            board3.put("X", 4);
            board3.put("O", 4);
            board3.put("X", 4);

            board3.put("X", 5);
            board3.put("O", 5);
            board3.put("O", 5);
            board3.put("X", 5);

            board3.put("X", 6);
            board3.put("X", 6);
            board3.put("O", 6);
            board3.put("X", 6);
            board3.put("X", 6);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        System.out.println(board3);
        assertEquals(1, connect4Controller.isGameOver());

        // TEST computer win
        board3.clear();
        try {
            board3.put("X", 3);
            board3.put("O", 3);

            board3.put("O", 4);
            board3.put("X", 4);
            board3.put("O", 4);

            board3.put("O", 5);
            board3.put("X", 5);
            board3.put("X", 5);
            board3.put("O", 5);

            board3.put("O", 6);
            board3.put("O", 6);
            board3.put("X", 6);
            board3.put("O", 6);
            board3.put("O", 6);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        assertEquals(0, connect4Controller.isGameOver());
        // test left-up 2 right-down (part 1 of 4)
        Connect4Model board4 = new Connect4Model();
        connect4Controller = new Connect4Controller(board4);
        try {
            board4.put("x", 0);
            board4.put("O", 0);
            board4.put("O", 0);
            board4.put("x", 0);

            board4.put("O", 1);
            board4.put("O", 1);
            board4.put("x", 1);

            board4.put("O", 2);
            board4.put("x", 2);

            board4.put("x", 3);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        assertEquals(1, connect4Controller.isGameOver());
        // test
        board4.clear();
        try {
            board4.put("O", 0);
            board4.put("X", 0);
            board4.put("X", 0);
            board4.put("O", 0);

            board4.put("X", 1);
            board4.put("X", 1);
            board4.put("O", 1);

            board4.put("X", 2);
            board4.put("O", 2);

            board4.put("O", 3);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        assertEquals(0, connect4Controller.isGameOver());
        // clear board
        board4.clear();

        // test left-up 2 right-down (part 2 of 4)
        try {
            board4.put("O", 2);
            board4.put("O", 2);
            board4.put("x", 2);
            board4.put("O", 2);
            board4.put("O", 2);
            board4.put("x", 2);

            board4.put("x", 3);
            board4.put("x", 3);
            board4.put("O", 3);
            board4.put("O", 3);
            board4.put("x", 3);

            board4.put("x", 4);
            board4.put("x", 4);
            board4.put("O", 4);
            board4.put("x", 4);

            board4.put("O", 5);
            board4.put("x", 5);
            board4.put("x", 5);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }
        System.out.println(board4);
        assertEquals(1, connect4Controller.isGameOver());

        // clear board
        board4.clear();
        // test left-up 2 right-down (part 3 of 4)
        try {
            board4.put("x", 0);
            board4.put("O", 0);
            board4.put("O", 0);
            board4.put("x", 0);

            board4.put("O", 1);
            board4.put("O", 1);
            board4.put("x", 1);

            board4.put("O", 2);
            board4.put("x", 2);
        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }

        System.out.println(board4);
        assertEquals(2, connect4Controller.isGameOver());

        //clear board
        board4.clear();
        // test tie game consequence
        try {
            //first line
            board4.put("X",0);
            board4.put("O",1);
            board4.put("O",2);
            board4.put("X",3);
            board4.put("O",4);
            board4.put("X",5);
            board4.put("X",6);
            // second line
            board4.put("X",0);
            board4.put("O",1);
            board4.put("O",2);
            board4.put("X",3);
            board4.put("O",4);
            board4.put("O",5);
            board4.put("X",6);
            // third line
            board4.put("O",0);
            board4.put("X",1);
            board4.put("X",2);
            board4.put("O",3);
            board4.put("X",4);
            board4.put("X",5);
            board4.put("O",6);
            // fourth line
            board4.put("X",0);
            board4.put("O",1);
            board4.put("O",2);
            board4.put("X",3);
            board4.put("X",4);
            board4.put("O",5);
            board4.put("X",6);
            //fifth line
            board4.put("O",0);
            board4.put("X",1);
            board4.put("X",2);
            board4.put("O",3);
            board4.put("X",4);
            board4.put("X",5);
            board4.put("O",6);
            //sixth line
            board4.put("X",0);
            board4.put("O",1);
            board4.put("O",2);
            board4.put("X",3);
            board4.put("O",4);
            board4.put("O",5);
            board4.put("X",6);


        } catch (WrongLocationException e) {
            System.out.println(e.toString());
        }

        System.out.println(board4);
        assertEquals(-1, connect4Controller.isGameOver());
    }

    @Test
    void humanTurnTest(){
        Connect4Model model = new Connect4Model();
        Connect4Controller connect4Controller = new Connect4Controller(model);

        assertTrue(connect4Controller.humanTurn(2));
        assertFalse(connect4Controller.humanTurn(7));
        assertFalse(connect4Controller.humanTurn(-2));
    }

    @Test
    void computerTurnTest(){
        Connect4Model board4 = new Connect4Model();
        Connect4Controller connect4Controller = new Connect4Controller(board4);

        try {
            //first line
            board4.put("X", 0);
            board4.put("O", 1);
            board4.put("O", 2);
            board4.put("X", 3);
            board4.put("O", 4);
            board4.put("X", 5);
            board4.put("X", 6);
            // second line
            board4.put("X", 0);
            board4.put("O", 1);
            board4.put("O", 2);
            board4.put("X", 3);
            board4.put("O", 4);
            board4.put("O", 5);
            board4.put("X", 6);
            // third line
            board4.put("O", 0);
            board4.put("X", 1);
            board4.put("X", 2);
            board4.put("O", 3);
            board4.put("X", 4);
            board4.put("X", 5);
            board4.put("O", 6);
            // fourth line
            board4.put("X", 0);
            board4.put("O", 1);
            board4.put("O", 2);
            board4.put("X", 3);
            board4.put("X", 4);
            board4.put("O", 5);
            board4.put("X", 6);
            //fifth line
            board4.put("O", 0);
            board4.put("X", 1);
            board4.put("X", 2);
            board4.put("O", 3);
            board4.put("X", 4);
            board4.put("X", 5);
            board4.put("O", 6);
            //sixth line
            board4.put("X", 0);
            board4.put("O", 1);
            board4.put("O", 2);
            board4.put("X", 3);
            board4.put("O", 4);
            System.out.println(board4.toString());
            board4.put("O", 5);
            assertTrue(connect4Controller.computerTurn());
        } catch (WrongLocationException ex) {
            ex.printStackTrace();
        }
        assertFalse(connect4Controller.computerTurn());

        assertEquals(connect4Controller.toString(), board4.toString());
    }




}
