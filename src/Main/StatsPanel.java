package Main;

import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
import java.awt.Component;

public class StatsPanel extends JPanel {
    // Player fields
    private int playerHealth = 100, playerPoints = 0;
    private final JLabel playerHealthLabel, playerPointsLabel;

    // Enemy fields
    private int enemyHealth, enemyPoints = 0;  // enemyPoints unused
    private final JLabel enemyHealthLabel, enemyPointsLabel;

    public StatsPanel(int initialEnemyHealth) {
        this.enemyHealth = initialEnemyHealth;

        setPreferredSize(new Dimension(250, 750));
        setLayout(new GridLayout(2, 1));

        // — Player Panel —
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JLabel playerTitle = new JLabel("Player Stats", SwingConstants.CENTER);
        playerTitle.setFont(new Font("Arial", Font.BOLD, 16));
        playerHealthLabel = new JLabel("Health: " + playerHealth, SwingConstants.CENTER);
        playerPointsLabel = new JLabel("Points: " + playerPoints, SwingConstants.CENTER);

        playerPanel.add(Box.createVerticalGlue());
        playerPanel.add(playerTitle);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerHealthLabel);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerPointsLabel);
        playerPanel.add(Box.createVerticalGlue());

        // — Enemy Panel —
        JPanel enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
        JLabel enemyTitle = new JLabel("Enemy Stats", SwingConstants.CENTER);
        enemyTitle.setFont(new Font("Arial", Font.BOLD, 16));
        enemyHealthLabel = new JLabel("Health: " + enemyHealth, SwingConstants.CENTER);
        enemyPointsLabel = new JLabel("Points: " + enemyPoints, SwingConstants.CENTER);

        enemyPanel.add(Box.createVerticalGlue());
        enemyPanel.add(enemyTitle);
        enemyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        enemyPanel.add(enemyHealthLabel);
        enemyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        enemyPanel.add(enemyPointsLabel);
        enemyPanel.add(Box.createVerticalGlue());

        add(playerPanel);
        add(enemyPanel);
    }

    // Player getters/mutators
    public int getPlayerHealth() {
        return playerHealth;
    }

    public void adjustPlayerHealth(int delta) {
        playerHealth = Math.max(0, playerHealth + delta);
        playerHealthLabel.setText("Health: " + playerHealth);
    }

    public void addPlayerPoints(int pts) {
        playerPoints += pts;
        playerPointsLabel.setText("Points: " + playerPoints);
    }

    // Enemy getters/mutators
    public int getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(int hp) {
        enemyHealth -= hp;
        enemyHealthLabel.setText("Health: " + enemyHealth);
    }


    public int getEnemyPoints() {
        return enemyPoints;
    }
    
    public int getPlayerPoints()
    {
    	return playerPoints;
    }
    
    public void addEnemyPoints(int points)
    {
    	enemyPoints += points;
    	enemyPointsLabel.setText("Points: " + enemyPoints);
    }
    
    public boolean checkWin(int points, Component parent) {
        if (points >= 21 || (getEnemyHealth() <= 0)) {
            JOptionPane.showMessageDialog(
                parent,
                "<html><h1 style='color:green'>YOU WIN!</h1></html>",
                "Victory",
                JOptionPane.INFORMATION_MESSAGE
            );
            return true;
        }
        return false;
    }

    public boolean checkEWin(int points, Component parent)
    {
    	if(points >= 21)
    	{
    		 JOptionPane.showMessageDialog(
    	                parent,
    	                "<html><h1 style='color:red'>YOU LOST!</h1></html>",
    	                "Defeat",
    	                JOptionPane.ERROR_MESSAGE
    	            );
    		 return true;
    	}
    	return false;
    }

    public boolean checkLife(int health, Component parent) {
        if (health <= 0) {
            JOptionPane.showMessageDialog(
                parent,
                "<html><h1 style='color:red'>YOU LOST!</h1></html>",
                "Defeat",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }
    
    
}
