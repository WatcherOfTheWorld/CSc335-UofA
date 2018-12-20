/**
 * this is a MastermindIllegalColorException
 *
 * Throw this Exception when user inputed a char that is not a color
 *
 * @author Feiran Yang
 *
 */

public class MastermindIllegalColorException extends RuntimeException {
    public MastermindIllegalColorException(String errMsg){
        super(errMsg);
    }

}
