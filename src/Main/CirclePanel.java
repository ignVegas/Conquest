package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;


public class CirclePanel extends JPanel {
	
    private int die1, die2;
    private boolean diceRolled = false;
    private int selectedNumber = -1;
    private ActionListener selectionListener;
    private int enemyNumber = -1;
    private boolean enemyChosen = false;

    public CirclePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(500, 750));

        int centerX = 250;
        int centerY = 300;
        int radius = 150;
        int count = 11;  
        double offsetAngle = -Math.PI / 2;

        for (int i = 0; i < count; i++) {
            int value = i + 2;
            double angle = offsetAngle + (2 * Math.PI * i / count);

            int x = centerX + (int) (radius * Math.cos(angle));
            int y = centerY + (int) (radius * Math.sin(angle));

            ImageIcon[] frames = loadAnimationFramesForNumber(value);

            AnimatedImageButton btn = new AnimatedImageButton(frames, String.valueOf(value));
            btn.setBounds(x - 40, y - 40, 80, 80);

            btn.addActionListener(e -> {
                selectedNumber = value;
                diceRolled = false;  
                if (!enemyChosen) {
                    enemyNumber  = 2 + (int)(Math.random() * 11);
                    enemyChosen  = true;
                }
                repaint();
                if (selectionListener != null) {
                    selectionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                }
            });

            add(btn);
        }
    }
    
    public void rerollEnemy() {
        enemyNumber = 2 + (int) (Math.random() * 11);
        enemyChosen = true;
        repaint();
    }
    
    public void clearEnemyChoice() {
        enemyChosen = false;
    }
    
    public int rollDice() {
        die1 = 1 + (int)(Math.random() * 6);
        die2 = 1 + (int)(Math.random() * 6);
        diceRolled = true;
        repaint();
        return die1 + die2;  
    }
    
    public int getSelectedNumber() {
        return selectedNumber;
    }
    
    public int getEnemyNumber()
    {
    	return enemyNumber;
    }
    
    public void setSelectionListener(ActionListener l) {
        this.selectionListener = l;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        int centerX = 250, centerY = 300, radius = 150;


        if (selectedNumber >= 0) {
            String s = "Selected: " + selectedNumber;
            g2.setFont(new Font("Arial",Font.BOLD,16));
            FontMetrics fm = g2.getFontMetrics();
            int sw = fm.stringWidth(s);
            g2.drawString(s, centerX - sw/2, centerY + radius + 70);
        }
        
        if (enemyChosen) {
            String e = "Enemy: " + enemyNumber;
            FontMetrics fm = g2.getFontMetrics();
            int ew = fm.stringWidth(e);
            g2.drawString(e, centerX - ew/2, centerY + radius + 90);
        }

        if (diceRolled) {
            int size = 40;
            int x1 = centerX - size - 10, y1 = centerY - size/2;
            int x2 = centerX + 10, y2 = y1;

            g2.setColor(Color.WHITE);
            g2.fillRect(x1,y1,size,size);
            g2.fillRect(x2,y2,size,size);
            g2.setColor(Color.BLACK);
            g2.drawRect(x1,y1,size,size);
            g2.drawRect(x2,y2,size,size);

            g2.setFont(new Font("Arial",Font.BOLD,16));
            FontMetrics fm = g2.getFontMetrics();
            String d1 = String.valueOf(die1), d2 = String.valueOf(die2);
            int w1 = fm.stringWidth(d1),  h = fm.getAscent();
            int w2 = fm.stringWidth(d2);
            g2.drawString(d1, x1 + (size-w1)/2, y1 + (size+h)/2);
            g2.drawString(d2, x2 + (size-w2)/2, y2 + (size+h)/2);

            int sum = die1 + die2;
            String result = sum == selectedNumber ? "Yikes!" : "Phew!";
            String msg = "Roll: " + sum + "  →  " + result;
            int mw = fm.stringWidth(msg);
            g2.drawString(msg, centerX - mw/2, y2 + size + 30);
        }
    }


    private ImageIcon[] loadAnimationFramesForNumber(int value) {
        ImageIcon[] frames = new ImageIcon[6];
        for (int j = 0; j < 6; j++) {
        	String path = "images/" + value + "_frame" + j + ".png";
            frames[j] = new ImageIcon(path);
        }
        return frames;
    }


    @SuppressWarnings("unused")
	private Image createPlaceholderImage(int value, int frameIndex) {
        int size = 80;

        Color[] baseColors = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.MAGENTA, Color.ORANGE, Color.CYAN,
            Color.PINK, Color.YELLOW, Color.GRAY,
            Color.DARK_GRAY
        };
        Color chosenColor = baseColors[(value + frameIndex) % baseColors.length];

        Image img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) img.getGraphics();
     

        g2.dispose();
        return img;
    }
}