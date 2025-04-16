package Main;

import javax.swing.*;
import java.awt.*;

/**
 * Displays player and enemy stats in two vertical sections.
 */
public class StatsPanel extends JPanel {

    public StatsPanel() {
        setPreferredSize(new Dimension(250, 750));
        setLayout(new GridLayout(2, 1));

        // Top: Player
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        JLabel playerTitle = new JLabel("Player Stats", SwingConstants.CENTER);
        playerTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel playerHealthLabel = new JLabel("Health: 100", SwingConstants.CENTER);
        JLabel playerPointsLabel = new JLabel("Points: 0", SwingConstants.CENTER);

        playerPanel.add(Box.createVerticalGlue());
        playerPanel.add(playerTitle);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerHealthLabel);
        playerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        playerPanel.add(playerPointsLabel);
        playerPanel.add(Box.createVerticalGlue());

        // Bottom: Enemy
        JPanel enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
        JLabel enemyTitle = new JLabel("Enemy Stats", SwingConstants.CENTER);
        enemyTitle.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel enemyHealthLabel = new JLabel("Health: 80", SwingConstants.CENTER);
        JLabel enemyPointsLabel = new JLabel("Points: 0", SwingConstants.CENTER);

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
}
