import java.util.HashMap;
import java.util.Map;

/**
 * this is a class to control the model class
 *
 * this controller reads in a model.
 * give user input string, this controller compere user input with
 * answer and return if user input is right and how much user input was right
 *
 * @author Feiran Yang
 *
 */
public class MastermindController {
	
	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)

    private int SIZE = 4;
    private Map<Character, Integer> answerMap;
    private MastermindModel model;


	public MastermindController(MastermindModel model) {
	    this.model = model;
        String answer = "";
        for(int i = 0; i < SIZE; i++){
            // add random color by SIZE times
            answer += model.getColorAt(i);
        }
        answerMap = getDict(answer);
	
	}

    /**
     *  return a boolean value to indicate if user's input is correct by input string
     *
     * @param guess user's input guess
     * @return user's input correctness
     */
    public boolean isCorrect(String guess) {
    	return getRightColorRightPlace(guess) == 4;
    }

    /**
     *  return a value to indicate if user's input is how much digits did user guess
     *  is correct and in the right position  by input string
     *
     * @param guess user's input guess
     * @return user's input correctness
     */
    public int getRightColorRightPlace(String guess) { 
        return checkAnswer(guess)[0];
    }


    /**
     *  return a value to indicate if user's input is how much digits did user guess
     *  is correct but in wrong position  by input string
     *
     * @param guess user's input guess
     * @return user's input correctness
     */
    public int getRightColorWrongPlace(String guess) {
	    return checkAnswer(guess)[1];
    }

    // Create as many private methods as you like

    /**
     * give a string, return a map contains char as key, int as number of
     * char happens in the string
     *
     * @param chars string of guess or answer
     * @return Map<char, numbers>
     */
    private Map<Character, Integer> getDict(String chars){
	    chars = chars.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < SIZE; i++){
            char curr =chars.charAt(i);
            if(map.containsKey(curr)){
                int value = map.get(curr);
                // add one if key is already exist
                map.put(curr, value+1);
            }else{
                map.put(curr, 1);
            }
        }
        return map;
    }

    /**
     * give a string input, return how many digits did user guess is
     * right in the right place and how many digits did user guess is
     * right but in wrong place
     * return the value in a array
     *
     * the first element in the array is RCRP, the second is RCWP
     *
     * @param guess user guessed string
     * @return array{RCRP value, RCWP value}
     */
    private int[] checkAnswer(String guess){
        guess = guess.toLowerCase();

        // if user entered less than SIZE digits,
        // add some place holder digits at the end to avoid force quit
	    if(guess.length() <SIZE){
	        guess+="0000";
        }

        int[] checks = new int[2];

        Map<Character, Integer> currGuess = getDict(guess);

        // looping though keySet of answers, found found how much digits
        // did user guessed right (in right place and wrong place)
        for (Character c : currGuess.keySet()) {
            if(answerMap.containsKey(c)){
                int guessCharQuantity = currGuess.get(c);
                int answerCharQuantity = answerMap.get(c);
                if (guessCharQuantity >= answerCharQuantity){
                    checks[1] += answerCharQuantity;
                }else {
                    checks[1] += guessCharQuantity;
                }
            }
        }

        // found how many digits did user guessed right and in right place
        for(int i = 0; i < SIZE; i++){
            if(guess.length() >= i+1 &&guess.charAt(i) == model.getColorAt(i)){
                checks[0] ++;
            }
        }

        // remove RCRP in array[1]
        // so that array[1] become RCWP
        checks[1] -= checks[0];

        return checks;
    }

}
