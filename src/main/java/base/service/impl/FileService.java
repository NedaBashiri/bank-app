package base.service.impl;

import base.service.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService<T> implements FileIO {


    @Override
    public void saveToFile(List tList, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(tList);
            System.out.println("Accounts data has been saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving accounts data to file: " + e.getMessage());
        }
    }



    @Override
    public List<T> loadFromFile(String fileName) {
        List<T> tList = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            tList = (List<T>) objectIn.readObject();
            System.out.println("Accounts data has been loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading accounts data from file: " + e.getMessage());
            e.printStackTrace();
        }
        return tList;
    }
}

