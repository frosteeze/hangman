/*************************************************************** 
*  file: Intro.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the panel that has the welcome screen for the game
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.io.File;
import javax.swing.*;


class Intro extends JPanel{
    
    public Intro() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600,400); }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 400);
        g.setColor(Color.black);
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
        g2.drawString("CS 245 Quarter Project", 175, 100);
        g2.drawString("By Team Zer0", 225, 300);        
    }
}
