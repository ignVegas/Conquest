package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimatedImageButton extends JButton {
    private static final long serialVersionUID = 1L;

    private ImageIcon[] frames;
    private int frameIndex;
    private Timer animTimer;

    public AnimatedImageButton(ImageIcon[] frames, String text) {
        super(text);
        this.frames = frames;
        this.frameIndex = 0;

      
        if (frames != null && frames.length > 0) {
            setIcon(frames[0]);
        }


        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);


        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 14));

        // Animate every 200 ms
        animTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame();
            }
        });
        animTimer.start();
    }

    private void updateFrame() {
        if (frames == null || frames.length == 0) return;
        frameIndex = (frameIndex + 1) % frames.length;
        setIcon(frames[frameIndex]);
        repaint();
    }

    public void stopAnimation() {
        animTimer.stop();
    }

    public void startAnimation() {
        animTimer.start();
    }
}
