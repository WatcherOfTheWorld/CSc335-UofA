/**
 * this is a model class
 *
 * by creating a instance of this class, it generating a string
 * with SIZE of random chars in it.
 *
 * by using getColorAt(INT), developer can get the char at computer
 * generated random string at position INT.
 * 
 * @author Feiran Yang
 *
 */
public class MastermindModel {
	//private variable(s) to store the answer

	// Only these methods may be public - you may not create any additional 
	// public methods (and NO public fields)

    private int SIZE = 4;
    private char[] color = {'r','o','y','g','b','p'};
    private char[] answer = new char[SIZE];

    /**
     * This method is a constructor to allow user to create a random SIZE length string set
     */
    public MastermindModel() { 
    	// Make the answer
        for(int i = 0; i< SIZE; i++){
            final double d = Math.random();
            final int random = (int)(d*color.length);
            answer[i] = color[ random ];
            System.out.print(answer[i]);
        }
        System.out.println();

    }
    
    /**
     * This method is a special constructor to allow us to use JUnit to test our model.
     * 
     * Instead of creating a random solution, it allows us to set the solution from a 
     * String parameter.
     * 
     * 
     * @param answer A string that represents the four color solution
     */
    public MastermindModel(String answer){
        // Take answer and somehow store it as your answer. Make sure the getColorAt method
        // still works
        answer = answer.toLowerCase();

    	for (int i = 0; i < SIZE; i++){
    	    this.answer[i] = answer.charAt(i);
        }
    }


    /**
     * This method returns a char to represents index's color
     *
     * @param index of an char 
     * @return answer[index] a char that represents it's color
     */
    public char getColorAt(int index){
          /* Return color at position index as a char
           (first converted if stored as a number) */
    	return answer[index];
    }
    

}
