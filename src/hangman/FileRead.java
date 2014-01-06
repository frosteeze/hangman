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
import java.util.ArrayList;


class FileRead {
    private ArrayList<String> storeWordList = new ArrayList();
    private String[] words;
    private String strLine;
    private FileInputStream fstream;
    private BufferedReader br;
    
    public FileRead(String name){
        try {
            fstream = new FileInputStream("src\\files\\" + name);
            
            br = new BufferedReader(new InputStreamReader(fstream));
            //Read File Line By Line  
            while ((strLine = br.readLine()) != null) {  
                storeWordList.add(strLine);
            }
            //Close the input stream  
            fstream.close();  

            System.out.println("Words have been read successfully");  

        } catch (Exception e) {//Catch exception if any  
            System.out.println("File does not exist!"); 
            System.exit(0);
        }
    }
    
    /**
     * Returns String array of words scanned from external file
     * @return 
     */
    public String[] list(){
        words = new String[storeWordList.size()];
        for(int i = 0; i < words.length; i++)
            words[i] = storeWordList.get(i);
        return words;
    }
}  