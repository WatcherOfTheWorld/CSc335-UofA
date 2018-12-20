import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TicTacToeControllerTest {

	@Test
	void testIsGameOver() {
		TicTacToeModel board = new TicTacToeModel();
		TicTacToeController ticTacToe = new TicTacToeController(board);
		
		assertFalse(ticTacToe.isGameOver());
		
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(1,1);
		board.placeO(0, 0);
		assertFalse(ticTacToe.isGameOver());
		
		// test to place item to all places
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(1, 1);
		board.placeX(0,1);
		board.placeX(0, 2);
		board.placeX(1, 2);
		board.placeX(1, 0);
		board.placeX(2, 0);
		board.placeX(2, 1);
		board.placeX(2, 2);
		board.placeO(0, 1);
		assertTrue(ticTacToe.isGameOver());
		
		// col wins
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(0,1);
		board.placeX(0, 2);
		assertTrue(ticTacToe.isGameOver());
		
		// col not win / game not over
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(0,1);
		assertFalse(ticTacToe.isGameOver());
		
		// row wins
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(1, 0);
		board.placeX(2, 0);
		assertTrue(ticTacToe.isGameOver());
		
		// row not wins / game not over
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(1, 0);
		assertFalse(ticTacToe.isGameOver());
		
		// Diagonally wins
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(1, 1);
		board.placeX(2, 2);
		assertTrue(ticTacToe.isGameOver());
		
		// Diagonally not wins / game not over
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 0);
		board.placeX(1, 1);
		assertFalse(ticTacToe.isGameOver());
		
		// Diagonally the other way wins
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 2);
		board.placeX(1, 1);
		board.placeX(2, 0);
		assertTrue(ticTacToe.isGameOver());
		
		// Diagonally the other way not wins / game not over
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 2);
		assertFalse(ticTacToe.isGameOver());
		
		board = new TicTacToeModel();
		ticTacToe = new TicTacToeController(board);
		board.placeX(0, 2);
		board.placeX(1, 1);
		assertFalse(ticTacToe.isGameOver());
	}

}
