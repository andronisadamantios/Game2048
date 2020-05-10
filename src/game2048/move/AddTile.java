package game2048.move;

import grid.Grid;
import grid.GridVector;
import grid.IGridCoordinates;

/**
 * η πρόσθεση από το παιχνίδι μιας καινούριας τιμής στο πίνακα θεωρείται κίνηση
 * (Move)
 *
 * @author damhs
 */
public class AddTile extends Move {

    private final IGridCoordinates coor;

    public IGridCoordinates getCoor() {
        return coor;
    }

    public AddTile(IGridCoordinates coor, int value) {
        super(value);
        this.coor = coor;
    }

    public GridVector getVector() {
        return GridVector.ZERO;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", this.coor, super.toString());
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
