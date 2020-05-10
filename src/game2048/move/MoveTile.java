package game2048.move;

import grid.Grid;
import grid.GridVector;
import grid.IGridCoordinates;

/**
 * η μετακίνηση μιας τιμής ενός κελιού σε ένα άλλο
 *
 * @author damhs
 */
public class MoveTile extends Move {

    private final IGridCoordinates start;
    private final IGridCoordinates end;

    public IGridCoordinates getStart() {
        return start;
    }

    public IGridCoordinates getEnd() {
        return end;
    }

    public MoveTile(IGridCoordinates start, IGridCoordinates end, int value) {
        super(value);
        this.start = start;
        this.end = end;
    }

    public GridVector getVector() {
        return GridVector.getFromTo(start, end);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : %s", this.start, this.end, super.toString());
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
