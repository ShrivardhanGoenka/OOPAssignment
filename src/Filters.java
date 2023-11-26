import java.util.ArrayList;
import java.util.Map;
/**
 * The {@code Filters} class provides utility methods for filtering the camp based on faculty of student.
 * It interacts with the Registry to retrieve and filter camps.
 */
public class Filters {
    /**
     * Filters and retrieves a list of camps based on the specified faculty.
     *
     * @param faculty                   The faculty to filter the camp
     * @return {@code ArrayList} of {@code Camp} objects that are visible and open to the specified faculty
     */
    public static ArrayList<Camp> filterStudentCamps(String faculty){
        ArrayList<Camp> list = new ArrayList<>();
        for (Camp i : RegistryFactory.campRegistry.getAllEntries()){
            if(i.isVisible() && i.isVisible(faculty) ){
                list.add(i);
            }
        }
        return list;
    }
}
