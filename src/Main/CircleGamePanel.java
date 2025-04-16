package Main;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that uses a BorderLayout:
 *  - Left: CirclePanel for animated image buttons (2..12)
 *  - Right: StatsPanel for player/enemy
 */
public class CircleGamePanel extends JPanel {

    private CirclePanel circlePanel;
    private StatsPanel statsPanel;

    public CircleGamePanel() {
        setLayout(new BorderLayout());

        circlePanel = new CirclePanel();  
        statsPanel = new StatsPanel();    

        add(circlePanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.EAST);
    }

    public CirclePanel getCirclePanel() {
        return circlePanel;
    }

    public StatsPanel getStatsPanel() {
        return statsPanel;
    }
}
