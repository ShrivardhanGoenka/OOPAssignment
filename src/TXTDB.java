import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public abstract class TXTDB<T,ID> {
    protected T obj;
    public TXTDB(T obj){
        this.obj = obj;
    }
    public abstract String getFileName();
    public abstract String getWriteData() throws DBException;
    public abstract T getObjectFromData(ID id,String data) throws DBException;
}
