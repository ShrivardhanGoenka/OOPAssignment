public abstract class TXTDB<T,ID> {
    protected T obj;
    public TXTDB(){
        obj = null;
    }
    public TXTDB(T obj){
        this.obj = obj;
    }

    public abstract String getFileName();
    public abstract String getWriteData() throws DBException;
    public abstract T getObjectFromData(ID id,String data) throws DBException;
    public abstract ID getID(String id);

}
