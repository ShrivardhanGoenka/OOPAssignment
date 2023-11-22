public interface TXTDB<T> {
    public String getFileName();
    public String writeData();
    public T readData(String data);
}
