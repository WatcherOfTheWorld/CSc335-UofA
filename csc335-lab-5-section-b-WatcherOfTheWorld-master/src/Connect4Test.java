//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
///**
// * This class collects all of the test methods for controller and model.
// * @author Feiran Yang
// **/
//public class Connect4Test {
//
//    @Test
//    /**
//     * test if token can be place
//     * {@link Connect4Controller#humanTurn(int)}
//     */
//    void testPlaceToken(){
//        Connect4Controller controller = new Connect4Controller(new Connect4Model());
//        for(int i = 0; i < 6; i++){
//            for(int j = 0; j < 7; j++){
//                controller.humanTurn(j);
//                assertEquals(controller.getBoard()[5-i][j],'X');
//            }
//        }
//
//        controller = new Connect4Controller(new Connect4Model());
//        for(int i = 0; i < 6; i++){
//            for(int j = 0; j < 7; j++){
//                controller.computerTurn();
//            }
//        }
//        // test when computer wins
//        assertEquals(controller.getResult(), -1);
//
//        for(int i = 0; i < 6; i++){
//            for(int j = 0; j < 7; j++){
//                assertEquals(controller.getBoard()[5-i][j],'O');
//            }
//        }
//    }
//
//    @Test
//    /**
//     * test if can get right result
//     * {@link Connect4Controller#isGameOver()}
//     * {@link Connect4Controller#getResult()}
//     */
//    void testGetResult(){
//        // Row wins
//        Connect4Controller controller = new Connect4Controller(new Connect4Model());
//        assertFalse(controller.isGameOver());
//        assertEquals(controller.getResult(), 0);
//        controller.humanTurn(1);
//        controller.humanTurn(2);
//        controller.humanTurn(3);
//        controller.humanTurn(4);
//        assertTrue(controller.isGameOver());
//        assertEquals(controller.getResult(), 1);
//
//        // Col wins
//        controller = new Connect4Controller(new Connect4Model());
//        assertFalse(controller.isGameOver());
//        controller.humanTurn(1);
//        controller.humanTurn(1);
//        controller.humanTurn(1);
//        controller.humanTurn(1);
//        assertTrue(controller.isGameOver());
//        assertEquals(controller.getResult(), 1);
//
//        // Diagonally
//        controller = new Connect4Controller(new Connect4Model());
//        assertFalse(controller.isGameOver());
//        controller.humanTurn(1);
//        controller.computerTurn(2);
//        controller.humanTurn(2);
//        controller.computerTurn(3);
//        controller.computerTurn(3);
//        controller.humanTurn(3);
//        controller.computerTurn(4);
//        controller.computerTurn(4);
//        controller.computerTurn(4);
//        controller.humanTurn(4);
//        assertTrue(controller.isGameOver());
//        assertEquals(controller.getResult(), 1);
//
//        // Diagonally the other way
//        controller = new Connect4Controller(new Connect4Model());
//        assertFalse(controller.isGameOver());
//        controller.computerTurn(1);
//        controller.computerTurn(1);
//        controller.computerTurn(1);
//        controller.humanTurn(1);
//        controller.computerTurn(2);
//        controller.computerTurn(2);
//        controller.humanTurn(2);
//        controller.computerTurn(3);
//        controller.humanTurn(3);
//        controller.humanTurn(4);
//        assertTrue(controller.isGameOver());
//        assertEquals(controller.getResult(),1);
//    }
//
//    @Test
//    /**
//     * test if exception can be throw correctly
//     * {@link Connect4Controller#humanTurn(int)}
//     */
//    void TestException(){
//        Connect4Controller controller = new Connect4Controller(new Connect4Model());
//
//        assertThrows(IndexOutOfBoundsException.class,()->{
//            for(int i = 0; i < 7; i++){
//                controller.humanTurn(1);
//            }
//        });
//
//        assertThrows(IndexOutOfBoundsException.class, ()->{
//            controller.humanTurn(7);
//        });
//    }
//
//}
