/*************************************************************** 
*  file: Menu.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the panel used for the main menu of the game
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

class Menu extends JPanel{
    private BufferedImage pic;
    private JButton play;
    private JButton scores;
    private JButton credits;
    private boolean playGame;
    private boolean highScores;
    private boolean viewCredits;
    
    public Menu() {
        play = new JButton("Play");
        scores = new JButton("High Scores");
        credits = new JButton("Credits");
        playGame = false;
        highScores = false;
        viewCredits = false;
        play.setToolTipText("Play the game");
        scores.setToolTipText("View High Scores");
        credits.setToolTipText("View the most awesome people ever");
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        
        // Set buttons and enable actions
        play.setBounds(450, 200, 140, 40);
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setPlay(true);
            }
        });
        scores.setBounds(450, 250, 140, 40);
        scores.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setScores(true);
            }
        });
        credits.setBounds(450, 300, 140, 40);
        credits.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                setCredits(true);
            }
        });
        this.add(play);
        this.add(scores);
        this.add(credits);
        
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600,400); }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 400);
        g.setColor(Color.black);
        try {
            pic = ImageIO.read(new File("src\\images\\images.jpg"));
        }
        catch(IOException e){
            System.out.println("Menu image not found!");
        }
        g.drawImage(pic, 79, 105, this);
    }
    
    /**
     * Sets whether or not to play a new game
     * @param play 
     */
    public void setPlay(boolean play){ playGame = play; }
    /**
     * Sets whether or not to view high scores
     * @param scores 
     */
    public void setScores(boolean scores){ highScores = scores; }
    /**
     * Sets whether or not to view Credits
     * @param credits 
     */
    public void setCredits(boolean credits){ viewCredits = credits; }
    /**
     * Returns whether or not to start new game
     * @return 
     */
    public boolean getPlay(){ return playGame; }
    /**
     * Returns whether or not to display high scores
     * @return 
     */
    public boolean getScores(){ return highScores; }
    /**
     * Returns whether or not to display credits
     * @return 
     */
    public boolean getCredits(){ return viewCredits; }
}
