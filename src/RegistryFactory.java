/**
 * The {@code RegistryFactory} class is a factory for creating and holding registry needed for the camp management system. 
 * It provides static instances of {@code Registry} for specific types of objects (Enquiry, Student, CampCommittee, Staff, Admin, Suggestion, Camp).
 */
public class RegistryFactory {
    /**
     * A registry for storing {@code Enquiry} objects with integer ID.
     */
    public static final Registry<Enquiry,Integer> enquiryRegistry = new Registry<>();
    /**
     * A registry for storing {@code Student} objects with integer ID.
     */
    public static final Registry<Student,String> studentRegistry = new Registry<>();
    /**
     * A registry for storing {@code Staff} objects with integer ID.
     */
    public static final Registry<Staff,String> staffRegistry = new Registry<>();
    /**
     * A registry for storing {@code Admin} objects with integer ID.
     */
    public static final Registry<Admin,String> adminRegistry = new Registry<>();
    /**
     * A registry for storing {@code CampCommittee} objects with integer ID.
     */
    public static final Registry<CampCommittee,String> committeeRegistry = new Registry<>();
    /**
     * A registry for storing {@code Camp} objects with integer ID.
     */
    public static final Registry<Camp,Integer> campRegistry = new Registry<>();
    /**
     * A registry for storing {@code Suggestion} objects with integer ID.
     */
    public static final Registry<Suggestion,Integer> suggestionRegistry = new Registry<>();
}
