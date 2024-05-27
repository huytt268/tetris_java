package com.mia_princz.tetris;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the leaderboard data for the Tetris game.
 */
public class LeaderboardData extends AbstractTableModel {

    private final String[] COLUMNS_NAME = {"Name", "Highscore"};

    private Map<String,Integer> leaderboardDataContainer = new TreeMap<>();

    private final String LEADERBOARD_DATA_FILENAME = "leaderboard.txt";

    /**
     * Constructs a new instance of the LeaderboardData class and loads the leaderboard data.
     */
    public LeaderboardData() {
        loadLeaderboard();
    }

    @Override
    public int getRowCount() {
        return leaderboardDataContainer.keySet().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_NAME.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS_NAME[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] keys = leaderboardDataContainer.keySet().toArray(new String[0]);
        return switch (columnIndex) {
            case 0  ->  keys[rowIndex];
            case 1  ->  leaderboardDataContainer.get(keys[rowIndex]);
            default ->  null;
        };
    }

    /**
     * Adds a player's score to the leaderboard data.
     *
     * @param playerName The name of the player.
     * @param playerScore The player's score.
     */
    protected void addPlayerToMap(String playerName, int playerScore) {
        if (leaderboardDataContainer.isEmpty()) {
            leaderboardDataContainer.put(playerName, playerScore);
            fireTableDataChanged();
            return;
        }

        if (!leaderboardDataContainer.containsKey(playerName)) {
            leaderboardDataContainer.put(playerName, playerScore);
            fireTableDataChanged();
            return;
        }

        for(Map.Entry<String,Integer> entry : leaderboardDataContainer.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (key.equals(playerName) && value < playerScore) {
                leaderboardDataContainer.put(playerName, playerScore);
            }
        }

        fireTableDataChanged();
    }
    /**
     * Saves the leaderboard data to a file.
     */
    public void saveLeaderboard() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(LEADERBOARD_DATA_FILENAME);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(leaderboardDataContainer);
            objectOutputStream.close();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    /**
     * Loads the leaderboard data from a file.
     */
    public void loadLeaderboard() {
        try {
            // If the file does not exist then create it.
            if (!new File(LEADERBOARD_DATA_FILENAME).exists()) {
                File newFile = new File(LEADERBOARD_DATA_FILENAME);
                newFile.createNewFile();
                // If it exists then load the data from it.
            } else if (new File(LEADERBOARD_DATA_FILENAME).length() != 0) {
                FileInputStream fileInputStream = new FileInputStream(LEADERBOARD_DATA_FILENAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                leaderboardDataContainer = (Map<String, Integer>) objectInputStream.readObject();
                objectInputStream.close();
            }
        } catch (IOException | ClassNotFoundException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}

