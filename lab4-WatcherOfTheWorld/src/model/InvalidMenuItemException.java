package model;

public class InvalidMenuItemException extends RuntimeException {
    public InvalidMenuItemException(String string){
        super(string);
    }
}
