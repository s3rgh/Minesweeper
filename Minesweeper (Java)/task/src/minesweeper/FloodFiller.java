package minesweeper;

public class FloodFiller {
    public static void floodFill(String[][] realField, String[][] playField, int x, int y, String newString) {
        String previousString = realField[x][y];
        if (previousString.equals(newString)) {
            return;
        }
        floodFillUtil(realField, playField, x, y, previousString, newString);
    }

    static void floodFillUtil(String[][] realField, String[][] playField, int x, int y, String previousString, String newString) {
        // Base cases
        if (x < 0 || x >= realField.length || y < 0 || y >= realField.length) {
            return;
        }
        if (realField[x][y].matches("[1-8]")) {
            playField[x][y] = realField[x][y];
            return;
        }

        if (!realField[x][y].equals(previousString)) {
            return;
        }
        // Replace string at (x, y)
        realField[x][y] = newString;

        // Recur for north, east, south and west
        floodFillUtil(realField, playField, x + 1, y, previousString, newString);
        floodFillUtil(realField, playField, x + 1, y + 1, previousString, newString);
        floodFillUtil(realField, playField, x, y + 1, previousString, newString);
        floodFillUtil(realField, playField, x - 1, y + 1, previousString, newString);
        floodFillUtil(realField, playField, x - 1, y, previousString, newString);
        floodFillUtil(realField, playField, x - 1, y - 1, previousString, newString);
        floodFillUtil(realField, playField, x, y - 1, previousString, newString);
        floodFillUtil(realField, playField, x + 1, y - 1, previousString, newString);

        for (int i = 0; i < realField.length; i++) {
            for (int j = 0; j < realField.length; j++) {
                if (realField[i][j].equals("/")) {
                    playField[i][j] = realField[i][j];
                }
            }
        }
    }
}
