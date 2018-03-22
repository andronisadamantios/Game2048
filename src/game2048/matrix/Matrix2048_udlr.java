package game2048.matrix;

import game2048.Direction;
import java.util.stream.IntStream;

/*
base matrix boolean result = false; for game 2048
παρεχει 4 μεθοδους για υλοποιηση για την κινηση σε καθε direction (up|down|left|right)
matrix boolean result = false; for game 2048 that pushes rows and columns in 3 stpes
has 8 methods (2 boolean result = false; for every direction (up|down|left|right))
idio me to dry alla might be faster
 */
public abstract class Matrix2048_udlr extends Matrix2048 implements IMatrix2048 {

    public Matrix2048_udlr(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMove(Direction direction) {
        switch (direction) {
            case left:
                return IntStream.range(0, rows).anyMatch(i -> this.canMoveLeftRow(i));
            case right:
                return IntStream.range(0, rows).anyMatch(i -> this.canMoveRightRow(i));
            case up:
                return IntStream.range(0, cols).anyMatch(i -> this.canMoveUpCol(i));
            case down:
                return IntStream.range(0, cols).anyMatch(i -> this.canMoveDownCol(i));
            default:
                throw new AssertionError();
        }
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case left:
                return IntStream.range(0, rows).mapToObj(i -> this.moveLeftRow(i))
                        .reduce(Boolean::logicalOr).get();
            case right:
                return IntStream.range(0, rows).mapToObj(i -> this.moveRightRow(i))
                        .reduce(Boolean::logicalOr).get();
            case up:
                return IntStream.range(0, cols).mapToObj(i -> this.moveUpCol(i))
                        .reduce(Boolean::logicalOr).get();
            case down:
                return IntStream.range(0, cols).mapToObj(i -> this.moveDownCol(i))
                        .reduce(Boolean::logicalOr).get();
            default:
                throw new AssertionError();
        }
    }

    public abstract boolean canMoveUpCol(int col);

    public abstract boolean canMoveDownCol(int col);

    public abstract boolean canMoveLeftRow(int row);

    public abstract boolean canMoveRightRow(int row);

    /**
     * πανω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveUpCol(int col);

    /**
     * κατω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveDownCol(int col);

    /**
     * αριστερη κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveLeftRow(int row);

    /**
     * δεξια κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean moveRightRow(int row);

}
