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

		// test
		controllerUnderTest = new MastermindController(new MastermindModel("obbb"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("orbp"),2);

		// different item in opposite position
		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("obbb"),0);

		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorRightPlace("ooob"),2);

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

        // different item in opposite position
        controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorWrongPlace("obbb"),2);

		controllerUnderTest = new MastermindController(new MastermindModel("booo"));
		assertEquals(controllerUnderTest.getRightColorWrongPlace("ooob"),2);
    }


    /**
     * Test method for MastermindIllegalColorException
     */
    @Test
    void testMastermindIllegalColorException(){
        MastermindModel model = new MastermindModel("rrrr");
        //Build the controller from the model
        MastermindController controller = new MastermindController(model);

        // in different char set
        assertThrows(MastermindIllegalColorException.class,()->
            controller.isCorrect("この歌声"));

        // punctuation
        assertThrows(MastermindIllegalColorException.class,()->
            controller.isCorrect("br,o"));

        // char that is not not a color
        assertThrows(MastermindIllegalColorException.class,()->
            controller.isCorrect("aaaa"));
    }

    /**
     * Test method for MastermindIllegalLengthException
     */
    @Test
    void testMastermindIllegalLengthException(){
        MastermindModel model = new MastermindModel("rrrr");
        //Build the controller from the model
        MastermindController controller = new MastermindController(model);

        // the string is empty
        assertThrows(MastermindIllegalLengthException.class,()->
            controller.isCorrect(""));

        // the string size is less than 4
        assertThrows(MastermindIllegalLengthException.class,()->
            controller.isCorrect("ooo"));

        // the string size is more than 4
        assertThrows(MastermindIllegalLengthException.class,()->
            controller.isCorrect("ooooo"));
    }


}
