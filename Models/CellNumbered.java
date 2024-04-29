package Models;

public class CellNumbered extends Cell {
    private int number;

    public CellNumbered(int number) {
        super();
        this.number = number;
    }

    /**
     * Checks if the numbered cell is mined.
     * Numbered cells are never mined.
     *
     * @return Always false for numbered cells
     */
    @Override
    public boolean isMined() {
        return false;
    }

    /**
     * Discovers the numbered cell.
     * Changes the state to discovered when the cell is revealed.
     */
    @Override
    public void discover() {
        stateCell.discover(this);
    }

    /**
     * Generates a string representation of the numbered cell.
     * Shows the number if the cell is discovered.
     * Otherwise, shows different characters based on the state of the cell:
     * - The number if the cell is discovered
     * - "P" if the cell is flagged
     * - "?" if the cell is covered
     *
     * @return The string representation of the numbered cell
     */
    @Override
    public String toString() {
        return (stateCell.isDiscovered()) ? number + " " :
                (stateCell.isFlagged()) ? "P " : "? ";
    }
}