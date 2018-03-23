package game2048.matrix;

import game2048.Direction;
import java.util.stream.IntStream;

/*
base matrix for game 2048
παρεχει 2 μεθοδους για υλοποιηση για την κινηση σε καθε orientation (hor|ver)
 */
public abstract class Matrix2048_hv extends Matrix2048 implements IMatrix2048 {

    public Matrix2048_hv(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMove(Direction direction) {
        switch (direction.getOrientation()) {
            case Vertical:
                return this.canMoveUpDown(direction.getValue());
            case Horizontal:
                return this.canMoveLeftRight(direction.getValue());
            default:
                throw new AssertionError();
    }
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction.getOrientation()) {
            case Vertical:
                return this.moveUpDown(direction.getValue());
            case Horizontal:
                return this.moveLeftRight(direction.getValue());
            default:
                throw new AssertionError();
    }
    }

    public boolean canMoveUpDown(int ud) {
        return IntStream.range(0, cols).anyMatch(i -> canMoveUpDownCol(ud, i));
    }

    public boolean canMoveLeftRight(int lr) {
        return IntStream.range(0, rows).anyMatch(i -> canMoveLeftRightRow(lr, i));
    }

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @return true αν εγινε κινηση
     */
    public boolean moveUpDown(int ud) {
        // gia kathe column
        return IntStream.range(0, cols).mapToObj(i -> this.moveUpDownCol(ud, i))
                .reduce(Boolean::logicalOr).get();
        }

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @return true αν εγινε κινηση
     */
    public boolean moveLeftRight(int lr) {
        // gia kathe row
        return IntStream.range(0, rows).mapToObj(i -> this.moveLeftRightRow(lr, i))
                .reduce(Boolean::logicalOr).get();
        }

    public abstract boolean canMoveUpDownCol(int ud, int col);

    public abstract boolean canMoveLeftRightRow(int lr, int row);

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveUpDownCol(int ud, int col);

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveLeftRightRow(int lr, int row);
}
