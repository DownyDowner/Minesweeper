package Models;

public class StateCellDiscovered implements StateCell {

    /**
     * Discovers the discovered cell.
     * This method does nothing for an already discovered cell.
     *
     * @param cell The cell to discover (already discovered)
     */
    @Override
    public void discover(Cell cell) {

    }

    /**
     * Flags the discovered cell.
     * This method does nothing for an already discovered cell.
     *
     * @param cell The cell to flag (already discovered)
     */
    @Override
    public void flag(Cell cell) {

    }

    /**
     * Checks if the cell is actually covered.
     *
     * @return Always false for discovered cells
     */
    @Override
    public boolean isCovered() {
        return false;
    }

    /**
     * Checks if the cell is actually discovered.
     *
     * @return Always true for discovered cells
     */
    @Override
    public boolean isDiscovered() {
        return true;
    }

    /**
     * Checks if the cell is actually flagged.
     *
     * @return Always false for discovered cells
     */
    @Override
    public boolean isFlagged() {
        return false;
    }
}