/**
 * this is a class to control the model class
 *
 * this controller reads in a model.
 * read in user input char from main, add the combination to the model
 * compare if user taked the right guess
 *
 * @author Feiran Yang
 */
public class CryptogramsController {
    CryptogramsModel model;

    /**
     * read in a model for store game data
     * @param model CryptogramsModel
     */
    public CryptogramsController(CryptogramsModel model){
        this.model = model;
    }

    /**
     * return if user has the right answer
     * @return is game over
     */
    public boolean isGameOver(){
        return model.getAnswer().equals(model.getPlaintext());
    }

    /**
     * return the cyphertext
     * @return cypher text
     */
    public String getWords(){
        return model.getCyphertext();
    }

    /** return the user's guess in a string
     * @return return the user guess string
     */
    public String getGuess(){
        return  model.getAnswer();
    }

    /**
     * mapping the replacement char to the char that user want to replace
     * @param original the char user want to be replaced
     * @param replacement the replacement char
     */
    public void replaceChar(char original, char replacement){
        model.setAnswer(original, replacement);
    }


}
