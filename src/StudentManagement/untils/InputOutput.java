/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement.untils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author My Laptop
 */
public class InputOutput {
    public void readFromFile(File file) throws FileNotFoundException, IOException{
        FileReader fileReader = new FileReader(file);
        int c = fileReader.read();
        while( c != -1){
            System.out.print((char) c);
            c = fileReader.read();
    }
        fileReader.close();
        
        
    }
    public void writeIntoFile(File file, String message) throws IOException{
        FileWriter fileWriter = new FileWriter(file,true);
        fileWriter.write(message + System.lineSeparator()); // Write message and new line
        fileWriter.close();
        
        
    }
}
