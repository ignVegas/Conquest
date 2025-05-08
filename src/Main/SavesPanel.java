package Main;

import javax.swing.*;
import java.awt.*;

public class SavesPanel extends JPanel {
    private Game parentFrame;
    private JComboBox<String> savesBox;
    private GameSave loader;

    public SavesPanel(Game parentFrame) {
        this.parentFrame = parentFrame;
        this.loader = new GameSave();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select Save");
        savesBox = new JComboBox<>();
        populateSavesBox();

   
        Dimension pref = savesBox.getPreferredSize();
        savesBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, pref.height));

        JButton loadButton = new JButton("Load Save");
        loadButton.addActionListener(e -> {
            String selected = (String) savesBox.getSelectedItem();
            if (selected != null) {
                loader.loadSave(selected, parentFrame);
            }
        });

        add(label);
        add(Box.createVerticalStrut(5));
        add(savesBox);
        add(Box.createVerticalStrut(10));
        add(loadButton);
    }

    private void populateSavesBox() {
        savesBox.removeAllItems();
        String[] saves = loader.savesArray();
        for (String name : saves) {
            savesBox.addItem(name);
        }
    }
}
