/*************************************************************** 
*  file: FileRead.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: Implements the color choosing game after the main game.
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import java.util.Random;

public class ColorGame extends JPanel {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int x3;
    private int y3;
    private int x4;
    private int y4;
    private int x5;
    private int y5;
    private int score;
    private int round;
    private int randomizer;
    private int correctX;
    private int correctY;
    private JLabel clock;
    private boolean highlight1;
    private boolean highlight2;
    private boolean highlight3;
    private boolean highlight4;
    private boolean highlight5;
    private String color;
    
    public ColorGame(){
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(null);
        
        clock = new JLabel();
        clock.setToolTipText("It's a clock...");
        score = 0;
        Random generator = new Random();
        
        //Generates the location of the circle.
        x1 = generator.nextInt(550);
        x2 = generator.nextInt(550);
        x3 = generator.nextInt(550);
        x4 = generator.nextInt(550);
        x5 = generator.nextInt(550);
        y1 = generator.nextInt(350);
        y2 = generator.nextInt(350);
        y3 = generator.nextInt(350);
        y4 = generator.nextInt(350);
        y5 = generator.nextInt(350);
        
        
        clock.setBounds(400, 10, 200, 10);
        
        ActionListener updateClock = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Assumes clock is a JLabel
                clock.setText(new Date().toString());
            }
        };
        Timer t = new Timer(1000, updateClock);
        t.start();
        this.add(clock);
        
        //Randomizes the text and correct color.
        stringRandom();
        
        //Actions to be performed if the user clicks on a circle.
        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e) {
                if(e.getX() <= (correctX+30) && e.getY() <= (correctY+30) &&
                        e.getX() >= (correctX-30) && e.getY() >= (correctY-30)){
                    score = score + 100;
                    System.out.println("test");
                }
                
                //Generates new locations
                Random generator = new Random();
        
                x1 = generator.nextInt(550);
                x2 = generator.nextInt(550);
                x3 = generator.nextInt(550);
                x4 = generator.nextInt(550);
                x5 = generator.nextInt(550);
                y1 = generator.nextInt(350);
                y2 = generator.nextInt(350);
                y3 = generator.nextInt(350);
                y4 = generator.nextInt(350);
                y5 = generator.nextInt(350);
                stringRandom();
                round++;
                repaint();
            }
            
        }); 
        
        //Determines if a circle is selected or not.
        addMouseMotionListener(new MouseAdapter(){
            public void mouseMoved(MouseEvent e) {
                if(e.getX() <= (x1+30) && e.getY() <= (y1+30) 
                        && e.getX() >= (x1-30) && e.getY() >= (y1-30)){
                    highlight1 = true;
                    highlight2 = false;
                    highlight3 = false;
                    highlight4 = false;
                    highlight5 = false;
                }
                
                else if(e.getX() <= (x2+30) && e.getY() <= 
                        (y2+30) && e.getX() >= (x2-30) && e.getY() >= (y2-30)) {
                    
                    highlight1 = false;
                    highlight3 = false;
                    highlight2= true;
                    highlight4 = false;
                    highlight5 = false;
                }
                else if(e.getX() <= (x3+30) && e.getY() <= (y3+30)
                        && e.getX() >= (x3-30) && e.getY() >= (y3-30)) {
                    highlight2 = false;
                    highlight1 = false;
                    highlight3= true;
                    highlight4 = false;
                    highlight5 = false;
                }
                else if(e.getX() <= (x4+30) && e.getY() <= 
                        (y4+30) && e.getX() >= (x4-30) && e.getY() >= (y4-30)) {
                    
                    highlight1 = false;
                    highlight3 = false;
                    highlight2=  false;
                    highlight4 = true;
                    highlight5 = false;
                }
                else if(e.getX() <= (x5+30) && e.getY() <= 
                        (y5+30) && e.getX() >= (x5-30) && e.getY() >= (y5-30)) {
                    
                    highlight1 = false;
                    highlight3 = false;
                    highlight2 = false;
                    highlight4 = false;
                    highlight5 = true;
                }
                else{
                    highlight1 = false;
                    highlight2 = false;
                    highlight3 = false;
                    highlight4 = false;
                    highlight5 = false;
                }
                repaint();
            }
        });
        
        
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600, 400); }
    
    public void reset() {
        Random generator = new Random();
        
        score = 0;
        round = 0;
        
        //Generates the locations of circles for the next playthrough.
        x1 = generator.nextInt(550);
        x2 = generator.nextInt(550);
        x3 = generator.nextInt(550);
        x4 = generator.nextInt(550);
        x5 = generator.nextInt(550);
        y1 = generator.nextInt(350);
        y2 = generator.nextInt(350);
        y3 = generator.nextInt(350);
        y4 = generator.nextInt(350);
        y5 = generator.nextInt(350);
        stringRandom();
    }
    
    //Generates the random color text and which circle will be the correct one.
    public void stringRandom(){
        Random generator = new Random();
        
        int n = generator.nextInt(5);
        
        //Decides what color NAME the text will display.
        if(n == 0){
            color = "RED";
        }
        else if(n == 1){
            color = "BLUE";
        }
        else if(n == 1){
            color = "GREEN";
        }
        else if(n == 1){
            color = "PINK";
        }
        else color = "ORANGE";
        
        //Determines what COLOR the text will have.
        //Randomizer number guide:
        // 0 is Red
        // 1 is Blue
        // 2 is Pink
        // 3 is Orange
        // 4 is Green
        
        randomizer = generator.nextInt(4);
        if(randomizer == 0){
            correctX = x1;
            correctY = y1;
        }
        else if (randomizer == 1){
            correctX = x2;
            correctY = y2;
        }
        else if (randomizer == 2){
            correctX = x4;
            correctY = y4;
        }
        else if (randomizer == 3){
            correctX = x5;
            correctY = y5;
        }
        else{
            correctX = x3;
            correctY = y3;
        }
    }
    
    public void paintComponent(Graphics g){
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 400);
        if(randomizer == 0){
            g.setColor(Color.RED);
        }
        else if (randomizer == 1){
            g.setColor(Color.BLUE);
        }
        else if (randomizer == 2){
            g.setColor(Color.PINK);
        }
        else if (randomizer == 3){
            g.setColor(Color.ORANGE);
        }
        else g.setColor(Color.GREEN);
        g.drawString(color, 300, 25);
        g.setColor(Color.RED);
        g.fillOval(x1, y1, 30, 30);
        g.setColor(Color.BLUE);
        g.fillOval(x2, y2, 30, 30);
        g.setColor(Color.GREEN);
        g.fillOval(x3, y3, 30, 30);
        g.setColor(Color.PINK);
        g.fillOval(x4, y4, 30, 30);
        g.setColor(Color.ORANGE);
        g.fillOval(x5, y5, 30, 30);
        
        //Draws the highlights on the circle.
        g.setColor(Color.BLACK);
        if(highlight1)
            g.drawOval(x1-5, y1-5, 40, 40);
        else if(highlight2)
            g.drawOval(x2-5, y2-5, 40, 40);
        else if(highlight4)
            g.drawOval(x4-5, y4-5, 40, 40);
        else if(highlight5)
            g.drawOval(x5-5, y5-5, 40, 40);
        else if(highlight3)
            g.drawOval(x3-5, y3-5, 40, 40);
    }
    
    public void setScore(int points){
        score = points;
    }
    public int getPoints(){
        
        return score;
    }
    public int getRounds(){
        return round;
    }
}
