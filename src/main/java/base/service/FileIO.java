package base.service;

import java.util.List;

public interface FileIO<T> {

    void saveToFile(List<T> tList , String fileName);

    List<T> loadFromFile(String fileName);
}
