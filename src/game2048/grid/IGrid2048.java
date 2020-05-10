package game2048.grid;

import grid.Grid;
import game2048.move.MoveBoard;
import grid.GridVector;
import grid.IGrid;

/**
 * interface for matrix of game 2048 yparxoun 4 kinhseis
 */
public interface IGrid2048 extends IGrid {

    default boolean canMoveUp() {
        return this.canMove(GridVector.UP);
    }

    default boolean canMoveDown() {
        return this.canMove(GridVector.DOWN);
    }

    default boolean canMoveLeft() {
        return this.canMove(GridVector.LEFT);
    }

    default boolean canMoveRight() {
        return this.canMove(GridVector.RIGHT);
    }

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveUp() {
        return this.move(GridVector.UP);
    }

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveDown() {
        return this.move(GridVector.DOWN);
    }

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveLeft() {
        return this.move(GridVector.LEFT);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveRight() {
        return this.move(GridVector.RIGHT);
    }

    boolean canMove();

    boolean canMove(GridVector direction);

    boolean move(GridVector direction);

    /**
     *
     * @return το μεγαλυτερο εκθετη. αν το παιχνιδι νικησε επιστρεφει 11
     */
    byte getMaxInternalValue();

    /**
     *
     * @return τη μεγαλυτερη τιμη. αν το παιχνιδι νικησε επιστρεφει 2048
     */
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
