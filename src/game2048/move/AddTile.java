package game2048.move;

import game2048.matrix.Matrix;

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

}
