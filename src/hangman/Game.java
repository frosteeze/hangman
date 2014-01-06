/*************************************************************** 
*  file: Game.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is the main game method that holds the mechanics
*  of the overall game
* 
****************************************************************/
package hangman;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;


class Game extends JPanel{
    private String[] keys = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private JButton[] buttonLetters;
    private JButton skip;
    private JLabel clock;
    
    // Count wrong guesses and check if end of game
    private boolean end;
    private int wrong;
    
    // Initial x position and y position for alphabet buttons.
    private int xPos;
    private int yPos;
    
    // Variables for handling word and letters
    private String word;
    private String[] wordList;
    private Random r;
    private FileRead file;
    private int score;
    private boolean[] letters;
    private String[] blanks;
    private String guess;
    
    // To be used for generating hangman images
    private BufferedImage picture;
    private Graphics2D g2D;
    
    public Game(){
        
        buttonLetters = new JButton[26];
        skip = new JButton("Skip");
        clock = new JLabel();
        skip.setToolTipText("Skip this game");
        clock.setToolTipText("It's a clock...");

        // Count wrong guesses and check if end of game
        end = false;
        wrong = 0;

        // Initial x position and y position for alphabet buttons and set score
        xPos = 19;
        yPos = 330;
        score = 100;
        
        // Random instance for choosing word randomly and file to read from
        r = new Random();
        file = new FileRead("words.txt");
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(null);
        
        // Set initial image
        try{
            picture = ImageIO.read(new File("src\\images\\0.jpg"));
            g2D = picture.createGraphics();
        } catch(Exception e){
            System.out.println("Stage image does not exist!");
        }
        
        // Create timer
        ActionListener updateClock = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Assumes clock is a JLabel
                clock.setText(new Date().toString());
            }
        };
        Timer t = new Timer(1000, updateClock);
        t.start();
        
        // Place timer and skip button
        clock.setBounds(400, 10, 200, 10);
        skip.setBounds(520, 30, 50, 25);
        skip.setBorder(null);
        skip.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                score = 0;
                setEndGame(true);
            }
        });
        this.add(clock);
        this.add(skip);
        
        // Choose mystery word
        mysteryWord();
        letters = new boolean[word.length()];
        
        // Initialize buttons and set button placement.
        for(int i = 0; i < keys.length; i++){
            final int j = i;
            buttonLetters[i] = new JButton(keys[i].toUpperCase());
            buttonLetters[i].setBorder(null);
            buttonLetters[i].setToolTipText("Check if this letter is correct.");
            buttonLetters[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    // Disable button on press
                    buttonLetters[j].setEnabled(false);
                    
                    // When guess is wrong, increment image squence and reduce score
                    if(!isLetterInWord(keys[j])){
                        wrong++;
                        score -= 10;
                        try{
                            picture = ImageIO.read(new File("src\\images\\" + wrong + ".jpg"));
                            g2D = picture.createGraphics();
                        } catch(Exception ex){
                            System.out.println("Image does not exist!");
                        }
                        repaint();
                    }
                    // If word is guessed or score reaches 0, end game
                    if(wordGuessed() || score == 0) setEndGame(true);
                }
            });
            if(i < 13) buttonLetters[i].setBounds(xPos, yPos, 40, 25);
            else buttonLetters[i].setBounds(xPos, yPos, 40, 25);
            
            this.add(buttonLetters[i]);
            
            // Reset x position when first row is filled and increment y
            if(i == 12){
                xPos = 19;
                yPos += 30;
            }
            else xPos += 44;
        }
    }
    
    /**
     * Randomly chooses word from array of scanned words
     */
    private void mysteryWord(){
        wordList = file.list();
        word = wordList[r.nextInt(wordList.length)];
        createBlanks(word);
    }
    
    /**
     * Check if letter is in word and set boolean letter index true
     * @param letter Individual letters passed in as String variables
     */
    private boolean isLetterInWord(String letter){
        int letterCount = 0;
        for(int i = 0; i < word.length(); i++){
            if(letter.charAt(0) == word.charAt(i)){
                letters[i] = true;
                blanks[i] = letter;
                letterCount++;
            }
        }
        
        if(letterCount > 0){
            // Regenerate blanks
            guess = "";
            for(int i = 0; i < blanks.length; i ++){
                if(blanks[i] == null)
                    guess += "_ ";
                else
                    guess += blanks[i].toUpperCase() + " ";
            }
            repaint();
            return true;
        }
        return false;
    }
    
    /**
     * Checks to see if the word is guessed correctly or not
     * @return 
     */
    private boolean wordGuessed(){
        for(boolean b : letters)
            if(!b) return false;
        return true;
    }
    
    /**
     * Generate blanks based on word chosen
     * @param word used to determine number of blanks
     */
    private void createBlanks(String word){
        blanks = new String[word.length()];
        guess = "";
        for(String s : blanks){
            s = "_";
            guess += s + " ";
        }
    }
    
    /**
     * Reset game settings for multiple plays
     */
    public void reset(){
        for(JButton j : buttonLetters)
            j.setEnabled(true);
        mysteryWord();
        wrong = 0;
        letters = new boolean[word.length()];
        score = 100;
        end = false;
        try{
            picture = ImageIO.read(new File("src\\images\\0.jpg"));
            g2D = picture.createGraphics();
        } catch(Exception e){
            System.out.println("Stage image does not exist!");
        }
        repaint();
    }
    
    public Dimension getPreferredSize(){ return new Dimension(600, 400); }
    
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 600, 400);
        g.setColor(Color.BLACK);
        
        g.drawImage(picture, 100, 30, 400, 300, Color.WHITE, this);
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
        g2.setColor(Color.RED);
        g2.drawString("Hangman", 19, 30);
        g2.setColor(Color.BLACK);
        g2.drawString(guess, 19, 100);
    }
    
    /**
     * Sets whether the game has ended or not
     * @param endGame parameter used for setting the game to end
     */
    private void setEndGame(boolean endGame){ end = endGame; }
    /**
     * Returns whether or not the game has ended
     * @return 
     */
    public boolean getEndGame(){ return end; }
    /**
     * Returns total points the user has achieved
     * @return 
     */
    public int getPoints(){ return score; }
}
