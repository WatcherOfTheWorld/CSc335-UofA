import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * This class collects all of the test methods array map.
 * @author Feiran Yang
 **/
public class MapTest {


    /**
     * Test method for {@link ArrayMap#put(Object, Object)} (java.lang.String)}.
     *                 {@link ArrayMap#size()}
     *                 {@link ArrayMap#containsKey(Object)}
     *                 {@link ArrayMap#containsValue(Object)}
     *                 {@link ArrayMap#get(Object)}

     */
    @Test
    void testMap(){
        // test put
        Map<Integer, Character> map = new ArrayMap<>();
        assertEquals(map.size(),0);
        map.put(1, 'a');
        assertEquals(map.size(),1);
        map.put(2,'b');
        assertEquals(map.size(),2);
        // put element with same key
        map.put(1, 'A');
        assertEquals(map.size(),2);
        map.put(3,'c');
        map.put(4,'d');
        map.put(5,'e');
        map.put(6,'f');
        map.put(7,'h');
        map.put(8,'i');
        map.put(9,'k');
        map.put(10,'k');

        assertEquals(map.size(),10);
        // put more than 10 element
        map.put(11,'l');
        map.put(12,'m');
        assertEquals(map.size(),12);

        // test contains
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(99));
        assertTrue(map.containsValue('A'));
        assertFalse(map.containsValue('a'));
        assertFalse(map.containsValue('Z'));

        // test get()
        assertEquals(map.get(1), (Character) 'A');

        // test put null as key and value
        map.put(null, 'i');
        assertEquals(map.get(null),(Character) 'i');
        map.put(1,null);
        assertEquals(map.get(1),null);
    }

    /**
     * Test method for {@link ArrayMap.ArrayMapSet#contains(Object)}
     *                 {@link ArrayMap.ArrayMapSet#iterator()}
     *                 {@link ArrayMap.ArrayMapSet#size()}
     */
    @Test
    void testSet(){
        Map<Integer, Integer> map = new ArrayMap<>();
        Set set =map.entrySet();
        assertFalse(set.iterator().hasNext());
        for(int i = 0; i<5; i++){
            map.put(i,i);
        }
        assertEquals(set.size(),5);

        assertFalse(set.contains(null));
        assertTrue(set.contains(new AbstractMap.SimpleEntry(1,1)));
        assertFalse(set.contains(new AbstractMap.SimpleEntry(9,9)));

    }

    /**
     * Test method for {@link ArrayMap.ArrayMapSet.ArrayMapIterator#hasNext()}
     *                 {@link ArrayMap.ArrayMapSet.ArrayMapIterator#next()}
     */
    @Test
    void testIterator(){
        Map<Integer, Integer> map = new ArrayMap<>();
        for(int i = 0; i<5; i++){
            map.put(i,i);
        }
        Iterator it = map.entrySet().iterator();
        for(int i = 0; i<5;i++){
            assertTrue(it.hasNext());
            it.next();
        }
        assertFalse(it.hasNext());
    }
}
