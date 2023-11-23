import java.util.ArrayList;
import java.util.Map;

public class Filters {
    //function to filter camps based on what student can see
    static ArrayList<Camp> filterStudentCamps(String faculty){
        ArrayList<Camp> list = new ArrayList<>();
        for (Map.Entry<Integer, Camp> map : Registry.campMap.entrySet()){
            Camp i = map.getValue();
            if(i.isVisible() && i.isVisible(faculty) ){
                list.add(i);
            }
        }
        return list;
    }


}
