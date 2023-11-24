import java.util.ArrayList;
import java.util.HashMap;

public class RegistryTest<T,ID> {
    private HashMap<ID,T> map;
    public RegistryTest(){
        map = new HashMap<>();
    }
    public void addEntry(T t, ID id){
        map.put(id,t);
    }
    public T getEntry(ID id){
        return map.get(id);
    }
    public void removeEntry(ID id){
        map.remove(id);
    }
    ArrayList<ID> getAllIDs(){
        return new ArrayList<>(map.keySet());
    }
    ArrayList<T> getAllEntries(){
        return new ArrayList<>(map.values());
    }
}
