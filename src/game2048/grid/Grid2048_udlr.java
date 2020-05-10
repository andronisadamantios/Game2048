package game2048.grid;

import grid.Grid;
import java.util.stream.IntStream;
import grid.GridVector;

/**
 * base matrix for game 2048. παρεχει 4 μεθοδους για υλοποιηση της κινησης σε
 * καθε direction (up|down|left|right) it pushes rows and columns in 3 steps has
 * 8 methods (2 for every direction (up|down|left|right)) idio me to dry alla
 * might be faster
 */
public abstract class Grid2048_udlr extends Grid2048 implements IGrid2048 {

    public Grid2048_udlr(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMove(grid.GridVector direction) {
        if (direction.equals(GridVector.UP)) {
            return IntStream.range(0, cols).anyMatch(i -> this.canMoveUpCol(i));
        } else if (direction.equals(GridVector.DOWN)) {
            return IntStream.range(0, cols).anyMatch(i -> this.canMoveDownCol(i));
        } else if (direction.equals(GridVector.LEFT)) {
            return IntStream.range(0, rows).anyMatch(i -> this.canMoveLeftRow(i));
        } else if (direction.equals(GridVector.RIGHT)) {
            return IntStream.range(0, rows).anyMatch(i -> this.canMoveRightRow(i));
        } else {
            throw new AssertionError();
        }
    }

    @Override
    public boolean move(GridVector direction) {
        if (direction.equals(GridVector.UP)) {
            return IntStream.range(0, cols).mapToObj(i -> this.moveUpCol(i))
                    .reduce(Boolean::logicalOr).get();
        } else if (direction.equals(GridVector.DOWN)) {
            return IntStream.range(0, cols).mapToObj(i -> this.moveDownCol(i))
                    .reduce(Boolean::logicalOr).get();
        } else if (direction.equals(GridVector.LEFT)) {
            return IntStream.range(0, rows).mapToObj(i -> this.moveLeftRow(i))
                    .reduce(Boolean::logicalOr).get();
        } else if (direction.equals(GridVector.RIGHT)) {
            return IntStream.range(0, rows).mapToObj(i -> this.moveRightRow(i))
                    .reduce(Boolean::logicalOr).get();
        } else {
            throw new AssertionError();
        }
    }

    protected abstract boolean canMoveUpCol(int col);

    protected abstract boolean canMoveDownCol(int col);

    protected abstract boolean canMoveLeftRow(int row);

    protected abstract boolean canMoveRightRow(int row);

    /**
     * πανω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveUpCol(int col);

    /**
     * κατω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveDownCol(int col);

    /**
     * αριστερη κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveLeftRow(int row);

    /**
     * δεξια κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    protected abstract boolean moveRightRow(int row);

}
