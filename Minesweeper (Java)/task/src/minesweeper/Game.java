package minesweeper;

import java.util.Scanner;

import static minesweeper.FloodFiller.*;

public class Game {
    private final Field field;

    public Game() {
        this.field = new Field();
    }

    public void run(Scanner scanner) {
        System.out.print("How many mines do you want on the field? ");
        this.field.initializeFields(Integer.parseInt(scanner.nextLine()));
        play(scanner);
    }

    private void play(Scanner scanner) {
        var counter = 0;
        var mineCounter = 0;
        var freeCells = 0;
        this.field.drawPlayField();
        this.field.drawRealField();

        while (true) {
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            var move = scanner.nextLine();
            var y = Integer.parseInt(move.split(" ")[0]) - 1; //i
            var x = Integer.parseInt(move.split(" ")[1]) - 1; //j
            var check = move.split(" ")[2];

            String free = "free";
            if (check.equals(free)) {
                if (this.field.getRealField()[x][y].equals(this.field.getMine())) {
                    System.out.println("You stepped on a mine and failed!");
                    break;
                } else if (this.field.getRealField()[x][y].matches(this.field.getNumbers())) {
                    this.field.getPlayField()[x][y] = this.field.getRealField()[x][y];
                    this.field.drawPlayField();
                    this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].equals(this.field.getUnexploredCell())) {
                    floodFill(this.field.getRealField(), this.field.getPlayField(), x, y, this.field.getExploredCell());
                    this.field.drawPlayField();
                    this.field.drawRealField();
                }
            }

            String mine = "mine";
            if (check.equals(mine)) {
                if (this.field.getRealField()[x][y].equals(this.field.getMine()) &&
                        !this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setMarkedPlayField(x, y);
                    counter++;
                    mineCounter++;
                    this.field.drawPlayField();
                    //this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].equals(this.field.getUnexploredCell())
                        &&
                        !this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setMarkedPlayField(x, y);
                    counter++;
                    this.field.drawPlayField();
                    this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].matches(this.field.getNumbers())
                        &&
                        !this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setMarkedPlayField(x, y);
                    counter++;
                    this.field.drawPlayField();
                    this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].equals(this.field.getMine())
                        &&
                        this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setUnmarkedPlayField(x, y);
                    counter--;
                    mineCounter--;
                    this.field.drawPlayField();
                    this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].equals(this.field.getUnexploredCell())
                        &&
                        this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setUnmarkedPlayField(x, y);
                    counter--;
                    this.field.drawPlayField();
                    this.field.drawRealField();
                } else if (this.field.getRealField()[x][y].matches(this.field.getNumbers())
                        &&
                        this.field.getPlayField()[x][y].equals(this.field.getMarked())) {
                    this.field.setUnmarkedPlayField(x, y);
                    counter--;
                    this.field.drawPlayField();
                    this.field.drawRealField();
                }
            }

            if (mineCounter == counter && counter == this.field.getNumberOfMines()) {
                System.out.println("Congratulations! You found all mines!");
                break;
            }

            freeCells = this.field.countFreeCells();

            if (freeCells == this.field.getNumberOfCells() - this.field.getNumberOfMines()) {
                System.out.println("Congratulations! You found all mines!");
                break;
            }
        }
    }
}
