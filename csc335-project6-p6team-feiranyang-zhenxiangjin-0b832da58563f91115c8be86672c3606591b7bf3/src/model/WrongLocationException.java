package model;
/**
 * This is an exception class for checking invalid col input
 * @author  Shawn Jin
 */
public class WrongLocationException extends Exception {
    public WrongLocationException(String message) {super(message);}

    @Override
    public String toString(){return "This location cannot be put." + getMessage();}

}
