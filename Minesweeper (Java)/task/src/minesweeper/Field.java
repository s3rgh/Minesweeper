package minesweeper;

import java.util.Random;

public class Field {
    private final int ROWS = 9;
    private final int COLUMNS = 9;
    private final String MARKED_CELL = "*";
    private final String MINE = "X";
    private final String UNEXPLORED_CELL = ".";
    private int numberOfBombs;
    private String[][] playField;
    private String[][] realField;

    public int getNumberOfCells() {
        return ROWS * COLUMNS;
    }

    public String getExploredCell() {
        return "/";
    }

    public String getUnexploredCell() {
        return UNEXPLORED_CELL;
    }

    public String getNumbers() {
        return "[1-8]";
    }

    public int getNumberOfMines() {
        return numberOfBombs;
    }

    public String getMarked() {
        return MARKED_CELL;
    }

    public String getMine() {
        return MINE;
    }

    public String[][] getPlayField() {
        return playField;
    }

    public String[][] getRealField() {
        return realField;
    }

    public void setMarkedPlayField(int x, int y) {
        this.playField[x][y] = MARKED_CELL;
    }

    public void setUnmarkedPlayField(int x, int y) {
        this.playField[x][y] = UNEXPLORED_CELL;
    }

    public void initializeFields(int numberOfBombs) {
        setNumberOfBombs(numberOfBombs);
        seedBombs();
        setNumbersOfMinesToRealField();
        fillPlayField();
    }

    void drawRealField() {
        System.out.println();
        System.out.println(" |123456789|\n-|---------|");
        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(realField[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    int countFreeCells() {
        var counter = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (!playField[i][j].equals(UNEXPLORED_CELL) && !playField[i][j].equals(MARKED_CELL)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    void drawPlayField() {
        System.out.println();
        System.out.println(" |123456789|\n-|---------|");
        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(this.playField[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("-|---------|");
    }

    private void setNumberOfBombs(int numberOfBombs) {
        this.numberOfBombs = numberOfBombs;
    }

    private void seedBombs() {
        this.realField = new String[ROWS][COLUMNS];
        var mines = this.numberOfBombs;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                realField[i][j] = UNEXPLORED_CELL;
            }
        }

        while (mines > 0) {
            int row = new Random().nextInt(ROWS);
            int col = new Random().nextInt(COLUMNS);
            if (this.realField[row][col].equals(UNEXPLORED_CELL)) {
                this.realField[row][col] = MINE;
                mines--;
            }
        }
    }

    private void setNumbersOfMinesToRealField() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                var number = countNumbersOfBombs(this.realField, i, j);
                if (number > 0 && !this.realField[i][j].equals(MINE)) {
                    this.realField[i][j] = String.valueOf(number);
                }
            }
        }
    }

    private int countNumbersOfBombs(String[][] array, int x, int y) {
        var count = 0;
        for (int k = x - 1; k <= x + 1; k++) {
            for (int l = y - 1; l <= y + 1; l++) {
                if (k >= 0 && k < ROWS && l >= 0 && l < COLUMNS) {
                    if (!(k == x & l == y)) {
                        if (array[k][l].equals(MINE)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private void fillPlayField() {
        this.playField = new String[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                playField[i][j] = UNEXPLORED_CELL;
            }
        }
    }
}
