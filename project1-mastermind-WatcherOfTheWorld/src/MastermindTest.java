import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class collects all of the test methods for our controller.
 * 
 * In eclipse, running it should run it under JUnit. Running the Mastermind class (since
 * it is our main class) will actually run the program. If you're not using eclipse,
 * you'll need to run this under JUnit 5. 
 * 
 * @author Feiran Yang
 *
 *
 */
class MastermindTest {

	/**
	 * Test method for {@link MastermindController#isCorrect(java.lang.String)}.
	 */
	@Test
	void testIsCorrect() {
		//Build a model with a known answer, using our special testing constructor
		MastermindModel answer = new MastermindModel("rrrr");
		//Build the controller from the model
		MastermindController controllerUnderTest = new MastermindController(answer);

		//For a properly working controller, this should return true
		assertTrue(controllerUnderTest.isCorrect("rrrr"));
        assertFalse(controllerUnderTest.isCorrect("oooo"));
        // test guess is in upper case
        assertTrue(controllerUnderTest.isCorrect("RRRR"));

        //Make as many more assertions as you feel you need to test the MastermindController.isCorrect method

        // the string is empty
        assertFalse(controllerUnderTest.isCorrect(""));

        // the string is in different character set
        assertFalse(controllerUnderTest.isCorrect("この歌声は　UNION"));

        // the string is longer than 4
        assertTrue(controllerUnderTest.isCorrect("rrrro"));
        assertFalse(controllerUnderTest.isCorrect("orrrr"));


        // test when answer is upper case
        answer = new MastermindModel("RRRR");
        //Build the controller from the model
        controllerUnderTest = new MastermindController(answer);

        //For a properly working controller, this should return true
        assertTrue(controllerUnderTest.isCorrect("rrrr"));
	}

	/**
	 * Test method for {@link MastermindController#getRightColorRightPlace(java.lang.String)}.
	 */
	@Test
	void testGetRightColorRightPlace() {
		//Build a model with a known answer, using our special testing constructor
		MastermindModel answer = new MastermindModel("rrrr");
		//Build the controller from the model
		MastermindController controllerUnderTest = new MastermindController(answer);
		
		//For a properly working controller, this should return 4
		assertEquals(controllerUnderTest.getRightColorRightPlace("rrrr"), 4);
		
		//For a properly working controller, this should return 0
		assertEquals(controllerUnderTest.getRightColorRightPlace("oooo"), 0);
		
		//You'll need lots more of these to convince yourself your implementation is right
		assertEquals(controllerUnderTest.getRightColorRightPlace("この歌声は　UNION"), 0);

		// test
		controllerUnderTest = new MastermindController(new MastermindModel("obbb"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("orbp"),2);

		// different item in opposite position
		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("obbb"),0);

		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("ooob"),2);

		// least then 4 char
		assertEquals(controllerUnderTest.getRightColorRightPlace("ooo"),2);

	}

	/**
	 * Test method for {@link MastermindController#getRightColorWrongPlace(java.lang.String)}.
	 */
	@Test
	void testGetRightColorWrongPlace() {

	    // random answer
		MastermindModel answer = new MastermindModel("brgo");
		MastermindController controllerUnderTest = new MastermindController(answer);
		assertEquals(controllerUnderTest.getRightColorWrongPlace("brgb"),0);

		// different char set
		assertEquals(controllerUnderTest.getRightColorWrongPlace("この歌声は　UNION"), 0);

        // different item in opposite position

        controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorWrongPlace("obbb"),2);

		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorWrongPlace("ooob"),2);

		// least then 3 item
		assertEquals(controllerUnderTest.getRightColorWrongPlace("ooo"),1);

	}

}
