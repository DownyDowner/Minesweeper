package Models;

public abstract class Cell {
    protected StateCell stateCell;

    public Cell() {
        this.stateCell = new StateCellCovered();
    }

    /**
     * Adds a neighboring cell to this cell.
     * This method is intended to be overridden by subclasses.
     *
     * @param neighbour The neighboring cell to add
     */
    public void addNeighbour(Cell neighbour) {

    }

    /**
     * Sets the state of the cell.
     * This method is final to prevent subclasses from overriding it.
     *
     * @param stateCell The state of the cell
     */
    public final void setStateCell(StateCell stateCell) {
        this.stateCell = stateCell;
    }

    /**
     * Checks if the cell is mined.
     *
     * @return True if the cell is mined, otherwise false
     */
    public abstract boolean isMined();

    /**
     * Checks if the cell is discovered.
     *
     * @return True if the cell is discovered, otherwise false
     */
    public boolean isDiscovered() {
        return stateCell.isDiscovered();
    }

    /**
     * Checks if the cell is flagged.
     *
     * @return True if the cell is flagged, otherwise false
     */
    public boolean isFlagged() {
        return stateCell.isFlagged();
    }

    /**
     * Flags the cell.
     * Delegates the flag action to the current state of the cell.
     */
    public final void flag() {
        stateCell.flag(this);
    }

    /**
     * Discovers the cell.
     * This method is abstract and must be implemented by subclasses.
     */
    public abstract void discover();

    /**
     * Generates a string representation of the cell.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return The string representation of the cell
     */
    @Override
    public abstract String toString(); // Mined : "X", Empty : ".", Numbered : "${number}"
}
