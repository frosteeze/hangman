/*************************************************************** 
*  file: HighScores.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the high scores panel for the game
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;


class HighScores extends JPanel{
    private boolean goBack;
    private JButton back;
    
    // For reading/writing values in high scores
    private String[] scoreLines;
    private LinkedList<Integer> scores = new LinkedList();
    private LinkedList<String> users = new LinkedList();
    
    // File to hold scores
    private String hscores = "scores.txt";
    private FileWrite fw;
    private FileRead fr;
    private int score;
    
    public HighScores() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        
        // Initially false so that the user can see high scores before going "back"
        goBack = false;
        
        
        // Create and set back button
        back = new JButton("Back");
        back.setToolTipText("Go back to the main menu.");
        back.setBounds(10, 340, 80, 50);
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setBack(true);
            }
        });
        this.add(back);
        
        readScores();
    }
    
    /**
     * Read scores from file and store them into scores and users lists
     */
    private void readScores(){
        fr = new FileRead(hscores);
        
        // Read scores from file
        scoreLines = fr.list();
        
        // Store scores into map
        String[] temp;
        for(String s : scoreLines){
            temp = s.split("\\s+");
            scores.add(Integer.valueOf(temp[1]));
            users.add(temp[0]);
        }
    }

    /**
     * For every user in users list, check if new score is higher than highest
     * one in scores list, then save score if it is and write to scores file
     */
    private void saveScore() {
        for(int i = 0; i < users.size(); i++){
            if(score >= scores.get(i)){
                //setNewHighScore(score, i);
                writeScores();
                break;
            }
        }
    }
    
    /**
     * Sets the new high score by prompting the user to enter a name then
     * storing the score and name at the specified indexes in the scores and
     * users lists
     * @param s
     * @param index 
     */
    private void setNewHighScore(int s, int index){
        String usr = (String)JOptionPane.showInputDialog("Congratulations!\n"
                + "You made it to the high scores list, enter up to 3 characters"
                + "to save your score:");
        scores.add(index, s);
        scores.removeLast();
        users.add(index, usr);
        users.removeLast();
    }
    
    /**
     * Writes users and scores to scores file
     */
    private void writeScores(){
        fw = new FileWrite(hscores);
        fw.write(users, scores);
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600,400); }
    
    public void paintComponent(Graphics g){
        saveScore();
        
        // Initial y-component for displaying scores
        int y = 180;
        
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 400);
        Font avanteGarde, f;
        Graphics2D g2 = (Graphics2D) g;
        try{
            avanteGarde = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\ag.ttf"));
            f = new Font(avanteGarde.getFamily(),Font.TRUETYPE_FONT,20);
            g2.setFont(f);
        } catch(Exception e){
            System.out.println("Font doesn't exist!");
        }
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        g2.drawString("High Scores", 250, 100);
        try{
            avanteGarde = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\ag.ttf"));
            f = new Font(avanteGarde.getFamily(),Font.TRUETYPE_FONT,14);
            g2.setFont(f);
        } catch(Exception e){
            System.out.println("Font doesn't exist!");
        }
        g2.setColor(Color.black);
        
        // Write scores to screen
        for(int i = 0; i < users.size(); i++){
            g2.drawString(users.get(i) + String.format("%03d",scores.get(i)), 260, y);
            y += 20;
        }
    }
    
    /**
     * Sets the score from the game
     * @param s 
     */
    public void setScore(int s){ score = s; }
    
    /**
     * Setter for understanding whether to go "back" to main menu
     * or not
     * @param back 
     */
    public void setBack(boolean back){ goBack = back; }
    /**
     * Returns whether or not to go back to main menu
     * @return 
     */
    public boolean getBack(){ return goBack; }
}
