package Main;

import javax.swing.*;
import java.awt.*;

/**
 * The main application window, using a CardLayout to swap between
 * MainMenuPanel and CircleGamePanel.
 */
public class Game extends JFrame {

    public static final String CARD_MAIN_MENU = "MainMenu";
    public static final String CARD_GAME_PANEL = "GamePanel";
    
    private String difficulty = "Easy";

    private JPanel cardsPanel; 
    private MainMenu mainMenuPanel;
    private CircleGamePanel circleGamePanel;

    
    public Game() {
        super("Conquest");

        // Prepare CardLayout container
        cardsPanel = new JPanel(new CardLayout());

        // Build two primary panels
        mainMenuPanel = new MainMenu(this);
        circleGamePanel = new CircleGamePanel(this);

        // Add them
        cardsPanel.add(mainMenuPanel, CARD_MAIN_MENU);
        cardsPanel.add(circleGamePanel, CARD_GAME_PANEL);

        // Frame setup
        add(cardsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 750);
        setLocationRelativeTo(null);
    }

    public void showCard(String cardName) {
        CardLayout cl = (CardLayout) cardsPanel.getLayout();
        cl.show(cardsPanel, cardName);
    }

    public CircleGamePanel getCircleGamePanel() {
        return circleGamePanel;
    }

    public void setDifficulty(String d) {  
        this.difficulty = d;
    }
    public String getDifficulty() {        
        return difficulty;
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game frame = new Game();
            frame.setVisible(true);
        });
    }
}
