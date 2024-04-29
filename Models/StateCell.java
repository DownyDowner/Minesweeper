package Models;

public interface StateCell {
    /**
     * Discovers the cell.
     *
     * @param cell The cell to discover
     */
    void discover(Cell cell);

    /**
     * Flags the cell.
     *
     * @param cell The cell to flag
     */
    void flag(Cell cell);

    /**
     * Checks if the cell is covered.
     *
     * @return True if the cell is covered, false otherwise
     */
    boolean isCovered();

    /**
     * Checks if the cell is discovered.
     *
     * @return True if the cell is discovered, false otherwise
     */
    boolean isDiscovered();

    /**
     * Checks if the cell is flagged.
     *
     * @return True if the cell is flagged, false otherwise
     */
    boolean isFlagged();
}