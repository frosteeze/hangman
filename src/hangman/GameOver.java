/*************************************************************** 
*  file: GameOver.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the panel displayed at the end of a game
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 *
 * @author Andrew
 */
public class GameOver extends JPanel{
    private boolean goBack;
    private JButton back;
    private int score;
    
    public GameOver() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        
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
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600,400); }
    
    public void paintComponent(Graphics g){
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
        g2.drawString("GAME OVER", 235, 100);
        
        try{
            avanteGarde = Font.createFont(Font.TRUETYPE_FONT, new File("src\\font\\ag.ttf"));
            f = new Font(avanteGarde.getFamily(),Font.TRUETYPE_FONT,14);
            g2.setFont(f);
        } catch(Exception e){
            System.out.println("Font doesn't exist!");
        }
        g2.setColor(Color.black);
        g2.drawString("Your score: " + score, 245, 180);
    }
    
    /**
     * Sets whether or not to go back to main menu
     * @param back 
     */
    public void setBack(boolean back){ goBack = back; }
    /**
     * Returns whether or not to go back to main menu
     * @return 
     */
    public boolean getBack(){ return goBack; }
    /**
     * Sets score to be displayed for user
     * @param points 
     */
    public void setScore(int points){ score = points; }
}
