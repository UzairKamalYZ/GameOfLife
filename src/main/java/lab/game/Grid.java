package lab.game;

import static lab.game.Cell.CellState.ALIVE;

import java.util.List;

public class Grid {
    private Cell[][] cells;

    public Grid(Cell.CellState[][] input) {
        cells = new Cell[input.length][];
        for (int row = 0; row < input.length; row++) {
            cells[row] = new Cell[input[row].length];
            for (int col = 0; col < input[row].length; col++) {
                cells[row][col] = new Cell(input[row][col]);
            }
        }
    }

    public Cell.CellState[][] getCellsState() {
        Cell.CellState[][] cellStates = new Cell.CellState[cells.length][];
        for (int row = 0; row < cells.length; row++) {
            cellStates[row] = new Cell.CellState[cells[row].length];
            for (int col = 0; col < cells[row].length; col++) {
                cellStates[row][col] = cells[row][col].getCellSate();
            }
        }
        return cellStates;
    }

    public int getStateSize() {
        return cells.length;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getAliveCount() {
        int count = 0;
        for (int row = 0; row < getStateSize(); row++) {
            for (int col = 0; col < cells[row].length; col++) {
                if (isCellAlive(cells[row][col])) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isCellAlive(Cell cell) {
        return cell.getCellSate() == ALIVE;
    }

    public int getAliveCountOf(List<Coordinate> neighbours) {
        int count = 0;
        for (Coordinate coordinate : neighbours) {
            if (isCellAlive(cells[coordinate.getRow()][coordinate.getCol()])) {
                count++;
            }
        }
        return count;
    }

    public Grid getNextGeneration() {

        Grid newGrid = new Grid(getCellsState());
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = cells[row][col];
                List<Coordinate> neighbours = new Coordinate(row, col).getNeighbours(this);
                int liveNeighbourCount = getAliveCountOf(neighbours);
                cell.getCellNextStateHavingAliveNeighbours(liveNeighbourCount);
                newGrid.getCells()[row][col].setCellSate(cell.getCellSate());
            }
        }
        return newGrid;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (isCellAlive(value)) {
                    buffer.append("*");
                } else {
                    buffer.append(".");
                }
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}