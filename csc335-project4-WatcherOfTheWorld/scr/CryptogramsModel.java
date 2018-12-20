import java.util.*;
/**
 * CryptogramsModel
 *
 * this maintain an string as the answer to the game
 *
 * @author Feiran Yang
 *
 */

public class CryptogramsModel {
    public static String[] QUOTES = {"Talk is cheap. Show me the code. - Linus Torvalds ",
    "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live - John Woods",
    "Everyone knows that debugging is twice as hard as writing a program in the first place. So if you're as clever as you can be when you write it, how will you ever debug it? - Brian Kernighan",
    "A language that doesn't affect the way you think about programming is not worth knowing. - Alan J. Perlis",
    "The big optimizations come from refining the high-level design, not the individual routines. - Steve McConnell, Code Complete "};

    private String cyphertext = "";
    private String plaintext;
    private Map<Character, Character> encryptionMap = new ArrayMap<>();
    private Map<Character, Character> answerMap = new ArrayMap<>();
    static public List<Character> RANDOMALPHABET = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'
            ,'R','S','T','U','V','W','X','Y','Z');
    static public List<Character> ALPHABET = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q'
            ,'R','S','T','U','V','W','X','Y','Z');


    /**
     * get an random string as answer, and encrypt the string
     */
    public CryptogramsModel(){
        plaintext = getRandomString();
        Collections.shuffle(ALPHABET);
        replaceChar();

    }

    /**
     * return an string as user's input guess
     * @return user's guess in a whole string
     */
    public String getAnswer(){
        String answer = "";
        for(int i = 0; i< cyphertext.length(); i++){
            if(!ALPHABET.contains(cyphertext.charAt(i))){
                answer +=cyphertext.charAt(i);
            }else if(!answerMap.containsKey(cyphertext.charAt(i))){
                answer+= " ";
            }else{
                answer+=answerMap.get(cyphertext.charAt(i));
            }
        }
        return answer;
    }

    /**
     * returns the encrypted text
     * @return encrypted string
     */
    public String getCyphertext(){
        return cyphertext;
    }

    /**
     * return the original text
     * @return original string
     */
    public String getPlaintext(){
        return plaintext;
    }

    /**
     * put user's input char need to be replace and replacement char to
     * @param origin the char need be replace
     * @param answer the replacement char
     * @throws IllegalFormatCodePointException when the replacement has been used more than once, throw this exception
     */
    public void setAnswer(char origin, char answer){
        if(answerMap.containsValue(answer)){
            throw new IllegalArgumentException("Replacement char has been used one than once");
        }
        answerMap.put(origin,answer);
    }

    /**
     * replace all alphabet char with an other random char
     */
    private void replaceChar(){
        for(int i = 0; i < plaintext.length(); i++){
            if(!ALPHABET.contains(plaintext.charAt(i))){
                cyphertext += plaintext.charAt(i);
            }else if(encryptionMap.containsKey(plaintext.charAt(i))){
                cyphertext += encryptionMap.get(plaintext.charAt(i));
                continue;
            }else{
                encryptionMap.put(plaintext.charAt(i), getRandomChar(plaintext.charAt(i)));
                cyphertext += encryptionMap.get(plaintext.charAt(i));
            }
        }
    }

    /**
     * return a random char
     * @param charAt index of the original char in alphabet
     * @return the new random char
     */
    private char getRandomChar(char charAt){
        int index = ALPHABET.indexOf(charAt);
        return RANDOMALPHABET.get(index);
    }

    /**
     * return a random string stored in class in all upper chase
     * @return random string
     */
    private String getRandomString(){
        return QUOTES[genRandomNumber(QUOTES.length)].toUpperCase();
    }

    /**
     * return an random number in bound
     * @param bound the range
     * @return a random int
     */
    private int genRandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }

}
