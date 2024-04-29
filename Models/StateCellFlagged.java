package Models;

public class StateCellFlagged implements StateCell {

    /**
     * Discovers the flagged cell.
     * This method does nothing for a flagged cell.
     *
     * @param cell The cell to discover (already flagged)
     */
    @Override
    public void discover(Cell cell) {

    }

    /**
     * Flags the flagged cell.
     * Changes the state of the cell back to covered.
     *
     * @param cell The cell to flag (already flagged)
     */
    @Override
    public void flag(Cell cell) {
        cell.setStateCell(new StateCellCovered());
    }

    /**
     * Checks if the cell is actually covered.
     *
     * @return Always false for flagged cells
     */
    @Override
    public boolean isCovered() {
        return false;
    }

    /**
     * Checks if the cell is actually discovered.
     *
     * @return Always false for flagged cells
     */
    @Override
    public boolean isDiscovered() {
        return false;
    }

    /**
     * Checks if the cell is actually flagged.
     *
     * @return Always true for flagged cells
     */
    @Override
    public boolean isFlagged() {
        return true;
    }
}