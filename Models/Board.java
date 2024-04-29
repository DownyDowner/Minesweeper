package Models;

import Constants.GameConstants;

import java.util.Random;

public class Board {
    private Cell[][] cells;

    public Board() {
        this.cells = new Cell[GameConstants.ROWS][GameConstants.COLS];
        initializeCells();
        placeMines();
        calculateAdjacentMines();
        setNeighbours();
    }

    /**
     * Initializes all cells on the board as empty cells.
     */
    private void initializeCells() {
        for (int i = 0; i < GameConstants.ROWS; i++) {
            for (int j = 0; j < GameConstants.COLS; j++) {
                cells[i][j] = new CellEmpty();
            }
        }
    }

    /**
     * Randomly places mines on the board until the total number of mines is reached.
     */
    private void placeMines() {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < GameConstants.TOTAL_MINES) {
            int randRow = random.nextInt(GameConstants.ROWS);
            int randCol = random.nextInt(GameConstants.COLS);
            if (!cells[randRow][randCol].isMined()) {
                cells[randRow][randCol] = new CellMined();
                minesPlaced++;
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each non-mine cell on the board.
     */
    private void calculateAdjacentMines() {
        for (int i = 0; i < GameConstants.ROWS; i++) {
            for (int j = 0; j < GameConstants.COLS; j++) {
                if (!cells[i][j].isMined()) {
                    int adjacentMines = countAdjacentMines(i, j);
                    if (adjacentMines > 0)
                        cells[i][j] = new CellNumbered(adjacentMines);
                }
            }
        }
    }

    /**
     * Counts the number of adjacent mines for a given cell.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of adjacent mines for the cell.
     */
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isValidCell(i, j) && cells[i][j].isMined()) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Sets the neighbors for each cell on the board.
     */
    private void setNeighbours() {
        for (int row = 0; row < GameConstants.ROWS; row++) {
            for (int col = 0; col < GameConstants.COLS; col++) {
                Cell currentCell = cells[row][col];

                // Check top left
                if (isValidCell(row - 1, col - 1))
                    currentCell.addNeighbour(cells[row - 1][col - 1]);

                // Check top
                if (isValidCell(row - 1, col))
                    currentCell.addNeighbour(cells[row - 1][col]);

                // Check top right
                if (isValidCell(row - 1, col + 1))
                    currentCell.addNeighbour(cells[row - 1][col + 1]);

                // Check left
                if (isValidCell(row, col - 1))
                    currentCell.addNeighbour(cells[row][col - 1]);

                // Check right
                if (isValidCell(row, col + 1))
                    currentCell.addNeighbour(cells[row][col + 1]);

                // Check bottom left
                if (isValidCell(row + 1, col - 1))
                    currentCell.addNeighbour(cells[row + 1][col - 1]);

                // Check bottom
                if (isValidCell(row + 1, col))
                    currentCell.addNeighbour(cells[row + 1][col]);

                // Check bottom right
                if (isValidCell(row + 1, col + 1))
                    currentCell.addNeighbour(cells[row + 1][col + 1]);

            }
        }
    }

    /**
     * Checks if a given cell position is valid on the board.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return True if the cell position is valid, false otherwise.
     */
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < GameConstants.ROWS && col >= 0 && col < GameConstants.COLS;
    }

    /**
     * Generates a string representation of the board.
     * If showAll is true, all cells are revealed.
     *
     * @param showAll If true, reveals all cells on the board
     * @return A string representation of the board
     */
    public String showBoard(boolean showAll) {
        StringBuilder str = new StringBuilder();
        str.append("   ");
        for (int i = 0; i < GameConstants.COLS; i++) {
            str.append(i).append("  ");
        }
        str.append("\n");

        for (int i = 0; i < GameConstants.ROWS; i++) {
            str.append(i).append("  ");

            for (int j = 0; j < GameConstants.COLS; j++) {
                if (showAll && !cells[i][j].isDiscovered())
                    cells[i][j].discover();
                str.append(cells[i][j].toString()).append(" ");
            }
            str.append("\n");
        }

        return str.toString();
    }


    /**
     * Flags the cell at the given row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public void flag(int row, int col) {
        if (isValidCell(row, col))
            cells[row][col].flag();
    }

    /**
     * Discovers the cell at the given row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public void discover(int row, int col) {
        if (isValidCell(row, col))
            cells[row][col].discover();
    }

    /**
     * Gets the cell at the given row and column.
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The cell at the specified position.
     */
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
