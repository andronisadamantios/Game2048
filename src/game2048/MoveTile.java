package game2048;

import game2048.matrix.Matrix;

public class MoveTile {

    private final Matrix.Coor start;
    private final Matrix.Coor end;
    private final int newValue;

    public Matrix.Coor getStart() {
        return start;
    }

    public Matrix.Coor getEnd() {
        return end;
    }

    public int getNewValue() {
        return newValue;
    }

    public MoveTile(Matrix.Coor start, Matrix.Coor end, int newValue) {
        this.start = start;
        this.end = end;
        this.newValue = newValue;
    }

    public Matrix.Vector getVector() {
        return Matrix.Vector.getFromTo(start, end);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : %d", this.start, this.end , this.newValue);
    }

}
