package game2048;

import game2048.matrix.Matrix;

public class MoveTile {

    private final Matrix.Coor start;
    private final Matrix.Coor end;

    public Matrix.Coor getStart() {
        return start;
    }

    public Matrix.Coor getEnd() {
        return end;
    }

    public Matrix.Vector getVector(){
        return Matrix.Vector.getFromTo(start, end);
    }

    public MoveTile(Matrix.Coor start, Matrix.Coor end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", this.start, this.end);
    }


}
