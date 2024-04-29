package Models;

public class StateCellCovered implements StateCell {

    /**
     * Discovers the covered cell.
     * Changes the state of the cell to discovered.
     *
     * @param cell The cell to discover
     */
    @Override
    public void discover(Cell cell) {
        cell.setStateCell(new StateCellDiscovered());
    }

    /**
     * Flags the covered cell.
     * Changes the state of the cell to flagged.
     *
     * @param cell The cell to flag
     */
    @Override
    public void flag(Cell cell) {
        cell.setStateCell(new StateCellFlagged());
    }

    /**
     * Checks if the cell is actually covered.
     *
     * @return Always true for covered cells
     */
    @Override
    public boolean isCovered() {
        return true;
    }

    /**
     * Checks if the cell is actually discovered.
     *
     * @return Always false for covered cells
     */
    @Override
    public boolean isDiscovered() {
        return false;
    }

    /**
     * Checks if the cell is actually flagged.
     *
     * @return Always false for covered cells
     */
    @Override
    public boolean isFlagged() {
        return false;
    }
}