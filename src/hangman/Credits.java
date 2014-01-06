/*************************************************************** 
*  file: Credits.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the credits panel to give credit for the many
*  hours spent not sleeping
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

class Credits extends JPanel{
    private boolean goBack;
    private JButton back;
    
    public Credits() {
        goBack = false;
        back = new JButton("Back");
        back.setToolTipText("Go back to the main menu.");
        
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(null);
        
        // Sets and places Back button
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
        g2.drawString("Credits", 250, 100);
        g2.setColor(Color.black);
        g2.drawString("Andre Supena, #008398429", 150, 200);
        g2.drawString("Andrew Armaneous, #007665242", 150, 250);
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
    
}
