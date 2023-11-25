import java.util.ArrayList;
/**
 * The {@code CustomPrinter} class is an abstract class for printing object of a any type {@code T}.
 * The method print needs to be implemented in its subclass to control how the object of type {@code T} will be printed.
 * This class provides additional methods for printing {@code ArrayList} of input.
 *
 * @param <T> the type of elements to be printed
 */
public abstract class CustomPrinter<T>{
    /**
     * Prints the object of type {@code T}
     */
    public abstract void print(T t);
    /**
     * Prints the list of object of type {@code T}
     */
    public void print(ArrayList<T> list){
        for(T t:list) print(t);
    }
    /**
     * Prints the list of object of type {@code T} with a header text
     */
    public void print(ArrayList<T> list, String header){
        System.out.println("-------------<"+header+">-------------");
        print(list);
    }
}
