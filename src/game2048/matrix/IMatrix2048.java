package game2048.matrix;

import matrix.IMatrix;
import matrix.Matrix;
import game2048.move.MoveBoard;

/**
 * interface for matrix of game 2048 yparxoun 4 kinhseis
 */
public interface IMatrix2048 extends IMatrix {

    default boolean canMoveUp() {
        return this.canMove(Matrix.Vector.UP);
    }

    default boolean canMoveDown() {
        return this.canMove(Matrix.Vector.DOWN);
    }

    default boolean canMoveLeft() {
        return this.canMove(Matrix.Vector.LEFT);
    }

    default boolean canMoveRight() {
        return this.canMove(Matrix.Vector.RIGHT);
    }

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveUp() {
        return this.move(Matrix.Vector.UP);
    }

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveDown() {
        return this.move(Matrix.Vector.DOWN);
    }

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveLeft() {
        return this.move(Matrix.Vector.LEFT);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveRight() {
        return this.move(Matrix.Vector.RIGHT);
    }

    boolean canMove();

    boolean canMove(matrix.Matrix.Vector direction);

    boolean move(Matrix.Vector direction);

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
