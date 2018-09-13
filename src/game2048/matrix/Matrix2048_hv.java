package game2048.matrix;

import java.util.stream.IntStream;

/**
 * base matrix for game 2048. παρεχει 2 μεθοδους για υλοποιηση της κινησης σε
 * καθε orientation (hor|ver). δλδ ειναι dry σε σχεση με το udlr
 */
public abstract class Matrix2048_hv extends Matrix2048 implements IMatrix2048 {

    public Matrix2048_hv(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMove(Matrix.Vector direction) {
        int value = direction.getDCol() + direction.getDRow();
        if (direction.isVertical()) {
            value = -value;
            return this.canMoveUpDown(value);
        } else if (direction.isHorizontal()) {
            return this.canMoveLeftRight(value);
        } else {
            throw new AssertionError();
        }
    }

    @Override
    public boolean move(Matrix.Vector direction) {
        int value = direction.getDCol() + direction.getDRow();
        if (direction.isVertical()) {
            value = -value;
            return this.moveUpDown(value);
        } else if (direction.isHorizontal()) {
            return this.moveLeftRight(value);
        } else {
            throw new AssertionError();
        }
    }

    protected boolean canMoveUpDown(int ud) {
        return IntStream.range(0, cols).anyMatch(i -> canMoveUpDownCol(ud, i));
    }

    protected boolean canMoveLeftRight(int lr) {
        return IntStream.range(0, rows).anyMatch(i -> canMoveLeftRightRow(lr, i));
    }

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @return true αν εγινε κινηση
     */
    protected boolean moveUpDown(int ud) {
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
    protected boolean moveLeftRight(int lr) {
        // gia kathe row
        return IntStream.range(0, rows).mapToObj(i -> this.moveLeftRightRow(lr, i))
                .reduce(Boolean::logicalOr).get();
    }

    protected abstract boolean canMoveUpDownCol(int ud, int col);

    protected abstract boolean canMoveLeftRightRow(int lr, int row);

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveUpDownCol(int ud, int col);

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveLeftRightRow(int lr, int row);
}
