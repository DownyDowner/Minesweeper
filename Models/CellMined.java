package Models;

public class CellMined extends Cell {
    public CellMined() {
        super();
    }

    /**
     * Checks if the mined cell is actually mined.
     * Mined cells are always mined.
     *
     * @return Always true for mined cells
     */
    @Override
    public boolean isMined() {
        return true;
    }

    /**
     * Discovers the mined cell.
     * Changes the state to discovered when the cell is revealed.
     */
    @Override
    public void discover() {
        stateCell.discover(this);
    }

    /**
     * Generates a string representation of the mined cell.
     * Shows different characters based on the state of the cell:
     * - "X" if the cell is discovered (mine revealed)
     * - "P" if the cell is flagged
     * - "?" if the cell is covered
     *
     * @return The string representation of the mined cell
     */
    @Override
    public String toString() {
        return (stateCell.isDiscovered()) ? "X " :
                (stateCell.isFlagged()) ? "P " : "? ";
    }

}
