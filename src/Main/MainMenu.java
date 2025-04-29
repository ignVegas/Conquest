package Main;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;


public class MainMenu extends JPanel {
    private Game parentFrame;
    private ButtonGroup difficultyGroup;

    public MainMenu(Game parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        difficultyGroup = new ButtonGroup();

        JRadioButton easyBtn = new JRadioButton("Easy");
        JRadioButton mediumBtn = new JRadioButton("Medium");
        JRadioButton hardBtn = new JRadioButton("Hard");
        easyBtn.setSelected(true);

        difficultyGroup.add(easyBtn);
        difficultyGroup.add(mediumBtn);
        difficultyGroup.add(hardBtn);

        // Start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            // Print chosen difficulty
            String chosen = getSelectedDifficulty();
            System.out.println("Selected Difficulty: " + chosen);

            parentFrame.setDifficulty(chosen);
            
            // Switch to game panel
            parentFrame.showCard(Game.CARD_GAME_PANEL);
        });

        // Center alignment
        easyBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        mediumBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        hardBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(easyBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(mediumBtn);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(hardBtn);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(startButton);
        add(Box.createVerticalGlue());
    }

    private String getSelectedDifficulty() {
        for (Enumeration<AbstractButton> buttons = difficultyGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton btn = buttons.nextElement();
            if (btn.isSelected()) {
                return btn.getText();
            }
        }
        return "Unknown";
    }
}
