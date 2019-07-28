package game2048.matrix;

import matrix.Matrix;
import java.util.Arrays;

/**
 * subClass of Matrix2048 εξειδικευμενο για το παιχνιδι Game2048. notes:
 * internal values are in this case the exponents of 2. represented values are
 * the power of 2 to the internal value
 */
public abstract class Matrix2048 extends Matrix implements IMatrix2048 {

    public static int mapInternalToRepresented(int value) {
        return (int) Math.pow(2, value);
    }

    public Matrix2048(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public byte[][] getAllInternalValues() {
        byte[][] result = new byte[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = (byte) this.array[i][j];
            }
        }
        return result;
    }

    @Override
    public int[][] getAllRepresentedValues() {
        int[][] result = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = this.getRepresentedValue(i, j);
            }
        }
        return result;
    }

    @Override
    public byte getMaxInternalValue() {
        return (byte) this.findMax();
    }

    @Override
    public int getMaxRepresentedValue() {
        return mapInternalToRepresented(this.getMaxInternalValue());
    }

    @Override
    public byte getInternalValue(int row, int col) {
        return (byte) this.get(row, col);
    }

    @Override
    public int getRepresentedValue(int row, int col) {
        int internal = this.get(row, col);
        if (internal > 0) {
            return mapInternalToRepresented(internal);
        }
        return 0;
    }

    @Override
    public boolean canMoveUp() {
        return this.canMove(Matrix.Vector.UP);
    }

    @Override
    public boolean canMoveDown() {
        return this.canMove(Matrix.Vector.DOWN);
    }

    @Override
    public boolean canMoveLeft() {
        return this.canMove(Matrix.Vector.LEFT);
    }

    @Override
    public boolean canMoveRight() {
        return this.canMove(Matrix.Vector.RIGHT);
    }

    @Override
    public boolean moveUp() {
        return this.move(Matrix.Vector.UP);
    }

    @Override
    public boolean moveDown() {
        return this.move(Matrix.Vector.DOWN);
    }

    @Override
    public boolean moveLeft() {
        return this.move(Matrix.Vector.LEFT);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    @Override
    public boolean moveRight() {
        return this.move(Matrix.Vector.RIGHT);
    }

    @Override
    public boolean canMove() {
        return Arrays.stream(Matrix.Vector.DIRECTIONS).anyMatch(this::canMove);
    }

    @Override
    public abstract boolean canMove(matrix.Matrix.Vector direction);

    @Override
    public abstract boolean move(Matrix.Vector direction);

}
