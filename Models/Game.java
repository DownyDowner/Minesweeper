package Models;

import Constants.GameConstants;

public class Game {
    private final Board board;
    private final Player player;
    private final Stopwatch stopwatch;
    private boolean isFinished;
    private boolean isWon;

    public Game(String playerName) {
        this.board = new Board();
        this.player = new Player(playerName);
        this.stopwatch = new Stopwatch();
        this.isFinished = false;
        this.isWon = false;
    }

    /**
     * Flags a cell at the given row and column.
     * If the stopwatch is not running, it starts.
     * Checks if the game is won after flagging.
     *
     * @param row The row of the cell
     * @param col The column of the cell
     */
    public void flag(int row, int col) {
        if (!stopwatch.isRunning())
            stopwatch.start();
        board.flag(row, col);
        checkGameWon();
    }

    /**
     * Generates a string representation of the game.
     * Includes player name, elapsed time, and the game board.
     *
     * @param showAll If true, reveals all cells on the board
     * @return A string representation of the game
     */
    public String showGame(boolean showAll) {
        StringBuilder str = new StringBuilder();
        str.append("Partie de " + player.getName() + "\n");
        str.append("Chrono : " + stopwatch.getElapsedTimeInSeconds() + "\n");
        str.append(board.showBoard(showAll));

        return str.toString();
    }

    /**
     * Discovers a cell at the given row and column.
     * If the stopwatch is not running, it starts.
     * Checks if the game is won after discovering.
     *
     * @param row The row of the cell
     * @param col The column of the cell
     */
    public void discover(int row, int col) {
        if (!stopwatch.isRunning())
            stopwatch.start();
        board.discover(row, col);
        Cell cell = board.getCell(row, col);
        isFinished = cell.isMined();
        if (isFinished)
            stopwatch.stop();
        else
            checkGameWon();
    }

    /**
     * Checks if the game is finished.
     *
     * @return True if the game is finished, otherwise false
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * Checks if the game is won.
     *
     * @return True if the game is won, otherwise false
     */
    public boolean isWon() {
        return isWon;
    }

    /**
     * Checks if all non-mined cells are discovered, indicating the game is won.
     * Updates isWon, isFinished accordingly and stop the stopwatch.
     */
    private void checkGameWon() {
        int nonMinedCellsToDiscover = 0;
        for (int i = 0; i < GameConstants.ROWS; i++) {
            for (int j = 0; j < GameConstants.COLS; j++) {
                if (!board.getCell(i, j).isMined() && !board.getCell(i, j).isDiscovered()) {
                    nonMinedCellsToDiscover++;
                }
            }
        }

        if (nonMinedCellsToDiscover == 0) {
            isWon = true;
            isFinished = true;
            stopwatch.stop();
        }
    }
}
