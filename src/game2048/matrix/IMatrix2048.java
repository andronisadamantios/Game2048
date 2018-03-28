package game2048.matrix;

import game2048.Direction;
import game2048.move.MoveBoard;

/*
base matrix for game 2048
yparxoun 4 kinhseis
 */
public interface IMatrix2048 extends IMatrix {

    default boolean canMoveUp() {
        return this.canMove(Direction.up);
    }

    default boolean canMoveDown() {
        return this.canMove(Direction.down);
    }

    default boolean canMoveLeft() {
        return this.canMove(Direction.left);
    }

    default boolean canMoveRight() {
        return this.canMove(Direction.right);
    }

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveUp() {
        return this.move(Direction.up);
    }

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveDown() {
        return this.move(Direction.down);
    }

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveLeft() {
        return this.move(Direction.left);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveRight() {
        return this.move(Direction.right);
    }

    boolean canMove();

    boolean canMove(Direction direction);

    boolean move(Direction direction);

    byte getMaxInternalValue();

    int getMaxRepresentedValue();

    byte getInternalValue(int row, int col);

    int getRepresentedValue(int row, int col);

    /**
     *
     * @return a new 2 dimensional array of the current values (powers of 2)
     */
    int[][] getAllRepresentedValues();

    /**
     *
     * @return a new 2 dimensional array of the current exponents (of the powers
     * of 2)
     */
    byte[][] getAllInternalValues();

    default MoveBoard getLastMove() {
        throw new UnsupportedOperationException();
    }

}
