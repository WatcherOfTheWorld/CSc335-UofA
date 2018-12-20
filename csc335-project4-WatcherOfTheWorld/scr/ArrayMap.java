import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ArrayMap
 *
 * this array map extends from a abstract map
 *
 * this map implemented put, size, entrySet method
 *
 * there is an abstract set build in this arrayMap
 *
 * @author Feiran Yang
 *
 */

public class ArrayMap<K, V> extends AbstractMap<K, V> {
    ArrayMapSet set = new ArrayMapSet();

    /**
     * this method take in two parameters, one key and one value
     * this method read in this two parameters and store them into this map.
     *
     * @param key - the key for mapping
     * @param value - the value that key maps to
     * @return old - the original value that key maps to, is there is any
     */
    @Override
    public V put(K key, V value){
        // take out the out value
        V old = set.get(key);
        SimpleEntry entry = new SimpleEntry(key,value);
        set.add(entry);
        return old;
    }

    /**
     * return the size of this map
     * @return size the size of the map
     */
    @Override
    public int size() {
        return set.size();
    }

    /**
     * return the entry set in the map
     * @return entrySet
     */
    @Override
    public Set<Entry<K,V>> entrySet(){
        return set;
    }

    /**
     * ArrayMapSet
     *
     * this ArrayMapSet is use to store element in the map
     * it can be expand when the array is full.
     *
     * @author Feiran Yang
     *
     */
    private class ArrayMapSet extends AbstractSet<Entry<K,V>>{
        private SimpleEntry[] entries = new SimpleEntry[10];

        /**
         * return the size of array, will not count the entry that is null
         * @return size - the size of arrayset
         */
        @Override
        public int size(){
            int count = 0;
            Iterator iterator = iterator();
            while (iterator.hasNext()){
                iterator.next();
                count ++;
            }
            return count;
        }

        /**
         * return a iterator for looping
         * @return iterator
         */
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new ArrayMapIterator<>();
        }

        /**
         * add an entry to the array, if success, return true, otherwise return false
         * @param kvEntry - new entry contains key and value
         * @return the operation result
         */
        @Override
        public boolean add(Entry<K, V> kvEntry) {
            // check if array need to be expand
            reSize();
            Iterator iterator = iterator();
            SimpleEntry curr = null;
            int index = 0;
            // loop though the whole array, to check if key is already exist
            while(iterator.hasNext()){
                index++;
                curr = (SimpleEntry) iterator.next();
                // if is, replace the old value in entry to new value
                if(curr.getKey().equals(kvEntry.getKey())){
                    curr.setValue(kvEntry.getValue());
                    return true;
                }
            }
            // otherwise, append it to the end of the list
            entries[index] = (SimpleEntry) kvEntry;
            return true;
        }

        /**
         * return if set contains object o
         * @param o object
         * @return if the set contains object o
         */
        @Override
        public boolean contains(Object o) {
            Iterator it = iterator();
            if(o == null){
                return false;
            }
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    return true;
                }
            }
            return false;
        }

        /**
         * return the value maps to key
         * @param key - key
         * @return value that maps to key
         */
        public V get(K key){
            Iterator iterator = iterator();
            SimpleEntry curr = null;
            // loop though the map
            while(iterator.hasNext()){
                curr = (SimpleEntry) iterator.next();
                if(curr.getKey().equals(key)){
                    // return some entry's value if the entry has key as key
                    return (V)curr.getValue();
                }
            }
            // if there is no such entry contains key as key, return null
            return null;
        }

        /**
         * expand the array by 2
         */
        private void reSize(){
            if(size()==entries.length){
                SimpleEntry[] newEntries = new SimpleEntry[entries.length*2];
                Iterator iterator = iterator();
                int i = 0;
                while (iterator.hasNext()){
                    newEntries[i] = (SimpleEntry) iterator.next();
                    i++;
                }
                entries = newEntries;
            }
        }

        /**
         * ArrayMapIterator
         *
         * iterator for looping the map.
         *
         * @author Feiran Yang
         *
         */
        private class ArrayMapIterator<T> implements Iterator<T>{
            int index = -1;

            /**
             * @return if the map has the next element
             */
            @Override
            public boolean hasNext() {
                return (entries.length>(index+1) && entries[index+1]!=null);
            }

            /**
             * @return the next element
             */
            @Override
            public T next() {
                index++;
                return (T)entries[index];
            }
        }
    }


}
