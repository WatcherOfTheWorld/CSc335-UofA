/**
 * this is a MastermindIllegalColorException
 *
 * Throw this Exception when the size of user input string is not 4
 *
 * @author Feiran Yang
 *
 */

public class MastermindIllegalLengthException extends RuntimeException {
    public MastermindIllegalLengthException(String errMag){
        super(errMag);
    }
}
