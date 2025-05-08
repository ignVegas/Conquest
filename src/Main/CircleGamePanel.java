package Main;

import javax.swing.*;
import java.awt.*;

public class CircleGamePanel extends JPanel {

    private CirclePanel circlePanel;
    private StatsPanel statsPanel;

    public CircleGamePanel(Game parentFrame) {
        setLayout(new BorderLayout());

        String diff = parentFrame.getDifficulty();
        int enemyHP = switch (diff) {
            case "Medium" -> 100;
            case "Hard"   -> 150;
            default       ->  80;
        };

        
        circlePanel = new CirclePanel();
        statsPanel  = new StatsPanel(enemyHP);


        JPanel eastContainer = new JPanel(new BorderLayout());
        eastContainer.add(statsPanel, BorderLayout.CENTER);


        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); 


        JButton cont = new JButton("Continue");
        cont.setEnabled(false);
        cont.addActionListener(e -> {
            // Roll and grab the user’s pick
            int sum    = circlePanel.rollDice();
            int choice = circlePanel.getSelectedNumber();
            int enemyNumber = circlePanel.getEnemyNumber();
            int pHealth = statsPanel.getPlayerHealth();
            int pPoints = statsPanel.getPlayerPoints();
            int eHealth = statsPanel.getEnemyHealth();
            int ePoints = statsPanel.getEnemyPoints();

            if (sum == choice) {
                Game frame = (Game) SwingUtilities.getWindowAncestor(this);
                String diffLevel = frame.getDifficulty();
                int baseDamage;
                switch (diffLevel) {
                    case "Medium" -> baseDamage = 15;
                    case "Hard"   -> baseDamage = 25;
                    default       -> baseDamage = 5;
                }


                int extraDamage = 0;
                if (choice >= 5 && choice <= 9) {
                    extraDamage = (int) Math.ceil(statsPanel.getPlayerHealth() * 0.05);
                }

                statsPanel.adjustPlayerHealth(-(baseDamage + extraDamage));
             
            } 
            else if(sum != choice) 
            {
            	 int pts;
                 if(choice >= 6 && choice <= 8) pts = 3;
                 else if (choice == 5 || choice == 9)  pts = 2;
                 else pts = 1;
                 statsPanel.addPlayerPoints(pts);
            }
            
            if(sum == enemyNumber)
            {
            	Game frame = (Game) SwingUtilities.getWindowAncestor(this);
                String diffLevel = frame.getDifficulty();
                int baseDamage;
                switch (diffLevel) {
                    case "Medium" -> baseDamage = 15;
                    case "Hard"   -> baseDamage = 25;
                    default       -> baseDamage = 5;
                }
                int extraDamage = 0;
                if (choice >= 5 && choice <= 9) {
                    extraDamage = (int) Math.ceil(statsPanel.getEnemyHealth() * 0.05);
                }
                statsPanel.updateEnemyHealth(-(baseDamage + extraDamage));
            } 
            else if(sum != enemyNumber)
            {
            	 int pts;
                 if(enemyNumber >= 6 && enemyNumber <= 8) pts = 3;
                 else if (enemyNumber == 5 || enemyNumber == 9)  pts = 2;
                 else pts = 1;
                 
                 statsPanel.addEnemyPoints(pts);
            }

            cont.setEnabled(false);
            
            if(!statsPanel.checkLife(pHealth, this)){ }
            if(statsPanel.checkWin(pPoints, parentFrame)) { }
            if(statsPanel.checkEWin(ePoints, parentFrame)) { }

            
            circlePanel.clearEnemyChoice();
        });
        JButton save = new JButton("Save");
        GameSave saver = new GameSave();
        int enemyNumber = circlePanel.getEnemyNumber();
        int pHealth = statsPanel.getPlayerHealth();
        int pPoints = statsPanel.getPlayerPoints();
        int eHealth = statsPanel.getEnemyHealth();
        int ePoints = statsPanel.getEnemyPoints();
        String saveName = "default";
        save.addActionListener(e -> {
        	String choice = JOptionPane.showInputDialog(saveName, "Enter Save Name:");
        	if (choice != null) {
        		saver.newSave(choice, parentFrame.getDifficulty(), pHealth, pPoints, eHealth, ePoints);
        	}
        	
        });

        JPanel btnHolder = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        btnHolder.setOpaque(false);
        btnHolder.add(cont);
        btnHolder.add(save);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setOpaque(false);

        bottom.setBorder(BorderFactory.createEmptyBorder(2, 2, 0, 0));

        bottom.add(sep);
        bottom.add(Box.createRigidArea(new Dimension(0, 4))); 
        bottom.add(btnHolder);

        eastContainer.add(bottom, BorderLayout.SOUTH);

        add(circlePanel, BorderLayout.CENTER);
        add(eastContainer, BorderLayout.EAST);
        
        circlePanel.setSelectionListener(evt -> cont.setEnabled(true));
    }

    public CirclePanel getCirclePanel() {
        return circlePanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }
    
    
}