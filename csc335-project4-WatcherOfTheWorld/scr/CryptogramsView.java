import java.util.Scanner;

/**
 * FileName: CryptogramsView
 *
 * this class handles user input and output
 *
 * @author Feiran Yang
 *
 */
public class CryptogramsView {
    Scanner scanner = new Scanner(System.in);
    boolean isFirst = true;

    /**
     * print out the cryptograms string and user's answer to cyptograms string
     * then, ask user to input char to replace and the replacement char
     * @param guess - the user's guess
     * @param words - the cryptogram string
     * @return a array contain char need to be replace and replacement char
     */
    public char[] getInput(String guess, String words){
        char[] replace = new char[2];
        printHit(guess, words);
        System.out.println("Enter the letter to replace: ");
        replace[0] = getNext();
        System.out.println("Enter its replacement: ");
        replace[1] = getNext();
        return replace;
    }

    /**
     * print the answer and a msg that tells user wins
     * @param guess the answer
     * @param words the cypytogram string
     */
    public void printResult(String guess, String words){
        printHit(guess, words);
        System.out.println("You got it!");
    }

    /**
     * print out the err
     * @param string err msg
     */
    public void printErr(String string){
        System.out.println(string);
    }

    /**
     * print the cryptograms string and user's answer
     * if user did not answer anything yet, will not print out user's answer
     * @param guess - user's answer
     * @param words - cryptogram string
     */
    private void printHit(String guess, String words){
        if(!isFirst){
            System.out.println(guess);
        }else{
            // do print guess if user did not input anything before
            isFirst = false;
        }
        System.out.println(words);
    }

    /**
     * return next char that user has been inputed
     * @return next char that user has inputted
     * @throws IllegalArgumentException when the user input format is not correct
     */
    private char getNext(){
        while(scanner.hasNext()){
            String next = scanner.next();
            next = next.trim().toUpperCase();
            if(next.length() == 1){
                // if is not a alpabet char, throw a excption
                if(!CryptogramsModel.ALPHABET.contains(next.charAt(0))){
                    throw new IllegalArgumentException("not a alphabet char, try again");
                }
                return next.charAt(0);
            }else {
                // if more than one char has been inputted
                throw new IllegalArgumentException("more than one char, try again");

            }
        }
        // if reaches the end of file
        throw new IllegalArgumentException("EOF, exit with code 1");
    }
}
