/*************************************************************** 
*  file: FileRead.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is to access an external file with words to guess
*  for the game
* 
****************************************************************/
package hangman;

import java.io.*;
import java.util.LinkedList;

public class FileWrite {
    private FileOutputStream fstream;
    private BufferedWriter br;
    
    public FileWrite(String name){
        try {
            fstream = new FileOutputStream("src\\files\\" + name);
            
            br = new BufferedWriter(new OutputStreamWriter(fstream));
        } catch (Exception e) {//Catch exception if any  
            System.out.println("File does not exist!"); 
            System.exit(0);
        }
    }
    
    public void write(LinkedList<String> users, LinkedList<Integer> scores){
        try {
            // Write scores
            for(int i = 0; i < users.size(); i++)
                br.write(users.get(i) + "....... " + scores.get(i));
            
            //Close the input stream  
            fstream.close();  
        } catch (IOException e) {
            System.out.println("Error closing the file!");
        }

        System.out.println("Scores have been written successfully");
    }
}
