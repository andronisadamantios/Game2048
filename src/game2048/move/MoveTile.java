package game2048.move;

import game2048.matrix.Matrix;

/**
 * η μετακίνηση μιας τιμής ενός κελιού σε ένα άλλο
 *
 * @author damhs
 */
public class MoveTile extends Move {

    private final Matrix.Coor start;
    private final Matrix.Coor end;

    public Matrix.Coor getStart() {
        return start;
    }

    public Matrix.Coor getEnd() {
        return end;
    }

    public MoveTile(Matrix.Coor start, Matrix.Coor end, int value) {
        super(value);
        this.start = start;
        this.end = end;
    }

    public Matrix.Vector getVector() {
        return Matrix.Vector.getFromTo(start, end);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s : %s", this.start, this.end, super.toString());
    }

}
