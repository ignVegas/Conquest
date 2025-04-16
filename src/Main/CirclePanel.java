package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class CirclePanel extends JPanel {

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
                System.out.println("Clicked number: " + value);
            });

            add(btn);
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
