package lab.game;

import static lab.game.Cell.CellState.ALIVE;
import static lab.game.Cell.CellState.DEAD;

import org.junit.Assert;
import org.junit.Test;

public class CellTest {

    @Test
    public void should_have_state_live_when_alive() {
        Cell cell = new Cell(ALIVE);
        Assert.assertEquals(ALIVE, cell.getCellSate());
    }

    @Test
    public void should_have_dead_state_when_dead() {
        Cell cell = new Cell(DEAD);
        Assert.assertEquals(DEAD, cell.getCellSate());
    }

    @Test
    //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    public void should_be_dead_by_underpopulation_when_live_cell_has_less_than_two_alive_neighbours() {
        Cell cell = new Cell(ALIVE);
        int numberOfAliveNeigbours = 1;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(DEAD, cell.getCellSate());
    }

    @Test
    //Any live cell with two live neighbours dies, as if by underpopulation.
    public void should_be_alive_when_live_cell_has_greater_than_two_alive_neighbours() {
        Cell cell = new Cell(ALIVE);
        int numberOfAliveNeigbours = 2;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(ALIVE, cell.getCellSate());
    }

    @Test
    //Any live cell with two or three live neighbours dies, as if by underpopulation.
    public void should_be_alive_when_live_cell_has_exactly_two_or_three_alive_neighbours() {
        Cell cell = new Cell(ALIVE);
        int numberOfAliveNeigbours = 3;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(ALIVE, cell.getCellSate());
    }

    @Test
//Any live cell with more than three live neighbours dies, as if by overpopulation.
    public void should_be_dead_by_overpopulation_when_live_cell_has_more_than_three_alive_neighbours() {
        Cell cell = new Cell(ALIVE);
        int numberOfAliveNeigbours = 4;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(DEAD, cell.getCellSate());
    }

    @Test
    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    public void should_be_alive_by_reproduction_when_dead_cell_has_three_alive_neighbours() {
        Cell cell = new Cell(DEAD);
        int numberOfAliveNeigbours = 3;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(ALIVE, cell.getCellSate());

    }

    @Test
    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    public void should_be_still_dead_when_dead_cell_has_more_than_three_alive_neighbours() {
        Cell cell = new Cell(DEAD);
        int numberOfAliveNeigbours = 4;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(DEAD, cell.getCellSate());
    }

    @Test
    //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    public void should_be_still_dead_when_dead_cell_has_less_than_three_alive_neighbours() {
        Cell cell = new Cell(DEAD);
        int numberOfAliveNeigbours = 2;
        cell.getCellNextStateHavingAliveNeighbours(numberOfAliveNeigbours);
        Assert.assertEquals(DEAD, cell.getCellSate());
    }

}
