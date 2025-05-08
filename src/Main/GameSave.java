package Main;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class GameSave {
    private static final String SAVE_FILE = "saves.txt";

    public void newSave(String saveName, String difficulty,
                        int pHealth, int pPoints,
                        int eHealth, int ePoints) {
        try (PrintWriter out = new PrintWriter(
                new FileOutputStream(SAVE_FILE, true))) {
            out.printf("%s,%s,%d,%d,%d,%d%n",
                       saveName, difficulty,
                       pHealth, pPoints,
                       eHealth, ePoints);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error writing save file: " + e.getMessage());
        }
    }

    public String[] savesArray() {
        List<String> names = new ArrayList<>();
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            return new String[0];
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
               
                String[] parts = line.split("[,\\s]+\s*");
                if (parts.length < 1) continue;

                names.add(parts[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return names.toArray(new String[0]);
    }

    public void loadSave(String saveName, Game game) {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "No save file found.");
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                // split on comma or whitespace
                String[] parts = line.split("[,\\s]+\s*");
                // Skip malformed lines
                if (parts.length < 6) continue;
                if (parts[0].equals(saveName)) {
                    String diff = parts[1];
                    int pHP = Integer.parseInt(parts[2]);
                    int pP  = Integer.parseInt(parts[3]);
                    int eHP = Integer.parseInt(parts[4]);
                    int eP  = Integer.parseInt(parts[5]);
                    // apply to game
                    game.setDifficulty(diff);
                    StatsPanel stats = game.getCircleGamePanel().getStatsPanel();
                    stats.setPlayerHealth(pHP);
                    stats.setPlayerPoints(pP);
                    stats.setEnemyHealth(eHP);
                    stats.setEnemyPoints(eP);
                    game.showCard(Game.CARD_GAME_PANEL);
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,
                "Save '" + saveName + "' not found in file.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error reading save file: " + e.getMessage());
        }
    }
}
