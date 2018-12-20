package model;

//import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Shawm jin
 */
class Connect4ModelTest {

    @Test
    void modelTest(){
        Connect4Model model = new Connect4Model();
        // getO & getX & getBlank
        assertEquals('X',model.getX());
        assertEquals('O',model.getO());
        assertEquals('_',model.getBLANK());
        // put & getLocation
        try {
            model.put("X", 0);
            model.put("O", 6);
        } catch (WrongLocationException ex) {
            ex.printStackTrace();
        }
        assertEquals(model.getX(), model.getLocation(5,0));
        assertEquals(model.getO(), model.getLocation(5,6));

        assertThrows(WrongLocationException.class, () -> model.put("X",-2));
        // only this time
        assertEquals(7,model.getCol());
        // toString && clear
        model.clear();
        for (int i = 0; i < model.getRow();i++) {
            for (int j = 0; j < model.getCol();j++){
                assertEquals('_',model.getLocation(i,j));
            }
        }

        Connect4Model model2 = new Connect4Model();
        assertEquals(model.toString(), model2.toString());


    }
}
