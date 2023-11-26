import java.util.ArrayList;
import java.util.HashMap;
/**
 * The {@code Registry} class is a generic registry that associates objects of type {@code T} with unique ID of type {@code ID}.
 * It holds the information in form of {@code HashMap}. (The examples of information stored are student, enquiry, etc.)
 * It provides the method to modify, and retrieving information stored in the System.
 *
 * @param <T> The type of object to be handled in the registry
 * @param <ID> The type of indentifier
 */
public class Registry<T,ID> {
    /**
     * The {@code HashMap} that stores associations between ID and objects.
     */
    private HashMap<ID,T> map;

    /**
     * Constructor for a new empty Registry
     */
    public Registry(){
        map = new HashMap<>();
    }

    /**
     * Adds a new entry to the registry.
     *
     * @param t             The object of type {@code T} to be added to the registry
     * @param id            The unique ID of type {@code ID} of the object
     */
    public void addEntry(T t, ID id){
        map.put(id,t);
    }

    /**
     * Retrieves the object associated with the specified ID from this registry.
     *
     * @param id            The unique ID associated with the object to be retrieved
     * @return The object of type {@code T} associated with the input id
     */
    public T getEntry(ID id){
        return map.get(id);
    }
    /**
     * Removes the entry associated with the specified ID from the registry.
     *
     * @param id            The unique ID of the entry to be removed
     */
    public void removeEntry(ID id){
        map.remove(id);
    }

    /**
     * Gets a list of all unique ID in the registry.
     *
     * @return {@code ArrayList<ID>}
     */
    public ArrayList<ID> getAllIDs(){
        return new ArrayList<>(map.keySet());
    }

    /**
     * Gets a list of all objects stored in the registry.
     *
     * @return {@code ArrayList<T>}
     */
    public ArrayList<T> getAllEntries(){
        return new ArrayList<>(map.values());
    }
}
