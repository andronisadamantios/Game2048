package game2048.move;

import matrix.Matrix;

/**
 * η πρόσθεση από το παιχνίδι μιας καινούριας τιμής στο πίνακα θεωρείται κίνηση
 * (Move)
 *
 * @author damhs
 */
public class AddTile extends Move {

    private final Matrix.Coor coor;

    public Matrix.Coor getCoor() {
        return coor;
    }

    public AddTile(Matrix.Coor coor, int value) {
        super(value);
        this.coor = coor;
    }

    public Matrix.Vector getVector() {
        return Matrix.Vector.ZERO;
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
