import java.util.ArrayList;

public abstract class ICustomPrinter<T>{
    public abstract void print(T t);
    public void print(ArrayList<T> list){
        for(T t:list) print(t);
    }
    public void print(ArrayList<T> list, String header){
        System.out.println("-------------<"+header+">-------------");
        print(list);
    }
}
