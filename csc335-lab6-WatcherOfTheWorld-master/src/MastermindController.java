import java.util.*;

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
    private List<Character> color = new ArrayList<>(Arrays.asList('r','o','y','g','b','p'));

    /**
     * create a controller with read in model
     * save answer in model into a map
     * 
     * @param model answer that need to be read in
     */
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
     * @throws MastermindIllegalLengthException throws MastermindIllegalLengthException when user's input size is not 4
     * @throws MastermindIllegalColorException throws MastermindIllegalColorException when user'inputed a char that is not a color
     */
    public int getRightColorRightPlace(String guess) {
        guess = guess.toLowerCase();

        // check user input digits, if not 4, throw an exception
        if(guess.length() != 4){
            throw new MastermindIllegalLengthException("Illegal Length Exception: length of input string is not 4");
        }

        int count = 0;
        for(int i = 0; i < SIZE; i++){
            if(!color.contains(guess.charAt(i))){
                //System.out.println(guess.charAt(i)+" "+Arrays.asList(color).contains(guess.charAt(i))+" "+ color[0]);
                throw new MastermindIllegalColorException("Input Color Does Not Exist Exception");
            }
            if(guess.length() >= i+1 &&guess.charAt(i) == model.getColorAt(i)){
                count ++;
            }
        }
        return count;
    }


    /**
     *  return a value to indicate if user's input is how much digits did user guess
     *  is correct but in wrong position  by input string
     *
     * @param guess user's input guess
     * @return user's input correctness
     */
    public int getRightColorWrongPlace(String guess) {
        // found how many digits did user guessed right and in right place
        int rcrp = getRightColorRightPlace(guess);
        int rcwp = 0;

        Map<Character, Integer> currGuess = getDict(guess);

        // looping though keySet of answers, found found how much digits
        // did user guessed right (in right place and wrong place)
        for (Character c : currGuess.keySet()) {
            if(answerMap.containsKey(c)){
                int guessCharQuantity = currGuess.get(c);
                int answerCharQuantity = answerMap.get(c);
                if (guessCharQuantity >= answerCharQuantity){
                    rcwp += answerCharQuantity;
                }else {
                    rcwp += guessCharQuantity;
                }
            }
        }

        // remove RCRP in array RCWP
        rcwp -= rcrp;

        return rcwp;
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

}
