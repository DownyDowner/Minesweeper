package Models;

import java.util.ArrayList;
import java.util.List;

public class CellEmpty extends Cell {
    private List<Cell> neighbours;

    public CellEmpty() {
        super();
        neighbours = new ArrayList<>();
    }

    /**
     * Adds a neighboring cell to this empty cell.
     *
     * @param neighbour The neighboring cell to add
     */
    @Override
    public void addNeighbour(Cell neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * Checks if the empty cell is mined.
     * Empty cells are never mined.
     *
     * @return Always false for empty cells
     */
    @Override
    public boolean isMined() {
        return false;
    }

    /**
     * Discovers the empty cell.
     * Changes the state to discovered and recursively discovers neighboring cells if they are also empty.
     */
    @Override
    public void discover() {
        stateCell.discover(this);
        for(Cell cell : neighbours)
            if (!cell.isMined() && cell.stateCell.isCovered())
                cell.discover();
    }

    /**
     * Generates a string representation of the empty cell.
     * Shows different characters based on the state of the cell:
     * - "." if the cell is discovered
     * - "P" if the cell is flagged
     * - "?" if the cell is covered
     *
     * @return The string representation of the empty cell
     */
    @Override
    public String toString() {
        return (stateCell.isDiscovered()) ? ". " :
                (stateCell.isFlagged()) ? "P " : "? ";
    }

}