/*************************************************************** 
*  file: Main.java 
*  author: A. Supena
*          A. Armaneous
*  class: CS 245 - GUI
* 
*  assignment: project 1
*  date last modified: 2/7/2113 
* 
*  purpose: This is a hangman game where the objective to guess the
*  word before the hangman is complete. The player has 10 tries before
*  the game ends.
* 
****************************************************************/

package hangman;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.KeyEvent;


public class Main extends JPanel{
    private static JFrame frame;
    private static boolean gameOver;
    private static int score = -1;
     
    public static void main(String[] args) {
        frame = new JFrame();
        gameOver = false;
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    
    private static void createAndShowGUI() {
        // Create panels
        final Intro welcome = new Intro();
        final Menu mainMenu = new Menu();
        final Credits cred = new Credits();
        final HighScores hs = new HighScores();
        final Game startGame = new Game();
        final ColorGame cg = new ColorGame();
        final GameOver go = new GameOver();
        
        // Place game window in center of screen for user
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screen = tk.getScreenSize();
        frame.setBounds(screen.width/2, screen.height/2, 600, 400);
        
        // Intro screen followed by Menu
        frame.getContentPane().add(welcome);
        ActionListener timer = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.getContentPane().add(mainMenu);
                frame.pack();
            }
        };
        Timer introTimer = new Timer(3000, timer);
        introTimer.start();
        introTimer.setRepeats(false);
        
        // Find which panel needs to be displayed
        ActionListener actions = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // If menu panel, check menu buttons for actions
                if(frame.getContentPane().getComponents()[0].equals(mainMenu)){
                    if(mainMenu.getPlay()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(startGame);
                        startGame.repaint();
                        frame.pack();
                        mainMenu.setPlay(false);
                    }
                    else if(mainMenu.getScores()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(hs);
                        hs.repaint();
                        frame.pack();
                        mainMenu.setScores(false);
                    }
                    else if(mainMenu.getCredits()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(cred);
                        cred.repaint();
                        frame.pack();
                        mainMenu.setCredits(false);
                    }
                }
                // If high scores panel, check back button for action
                else if(frame.getContentPane().getComponents()[0].equals(hs)){
                    if(hs.getBack()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(mainMenu);
                        mainMenu.repaint();
                        frame.pack();
                        hs.setBack(false);
                    }
                }
                // If credits panel, check back button for action
                else if(frame.getContentPane().getComponents()[0].equals(cred)){
                    if(cred.getBack()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(mainMenu);
                        mainMenu.repaint();
                        frame.pack();
                        cred.setBack(false);
                    }
                }
                // If game panel, check if end of game for action
                else if(frame.getContentPane().getComponents()[0].equals(startGame)){
                    if(startGame.getEndGame()){
                        score = startGame.getPoints();
                        cg.setScore(score);
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(cg);
                        cg.repaint();
                        frame.pack();
                        startGame.reset();
                    }
                }
                
                else if(frame.getContentPane().getComponents()[0].equals(cg)){
                    if(cg.getRounds() == 5){
                        score = cg.getPoints();
                        go.setScore(score);
                        hs.setScore(score);
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(go);
                        go.repaint();
                        frame.pack();
                        cg.reset();
                    }
                }
                
                else if(frame.getContentPane().getComponents()[0].equals(go)){
                    if(go.getBack()){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.getContentPane().add(mainMenu);
                        mainMenu.repaint();
                        frame.pack();
                        go.setBack(false);
                    }
                }
            }
        };
        // Check every 0.1 seconds for which panel to display
        Timer actionsTimer = new Timer(100, actions);
        actionsTimer.start();
        actionsTimer.setRepeats(true);
        
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        frame.setResizable(false);
        
        // Center window in user's screen
        int x = (screen.width - frame.getWidth()) /2;
        int y = (screen.height - frame.getHeight()) /2;
        frame.setLocation(x, y);
        
        Action exit = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        KeyStroke escapeStroke = 
        KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0); 
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeStroke,
                                    "exit");
        frame.getRootPane().getActionMap().put("exit",
                                     exit);
        
        Action help = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Andre Supena, #008398429 \nAndrew Armaneous, #007665242\n Hangman \n Winter 2012");
            }
};
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"),
                                    "help");
        frame.getRootPane().getActionMap().put("help",
                                     help);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}