package game2048.matrix;

import game2048.Direction;
import java.util.Arrays;

/*
internal values are in this case the exponents of 2
represented values are the power of 2 to the internal value
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
        return this.canMove(Direction.up);
    }

    @Override
    public boolean canMoveDown() {
        return this.canMove(Direction.down);
    }

    @Override
    public boolean canMoveLeft() {
        return this.canMove(Direction.left);
    }

    @Override
    public boolean canMoveRight() {
        return this.canMove(Direction.right);
    }

    @Override
    public boolean moveUp() {
        return this.move(Direction.up);
    }

    @Override
    public boolean moveDown() {
        return this.move(Direction.down);
    }

    @Override
    public boolean moveLeft() {
        return this.move(Direction.left);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    @Override
    public boolean moveRight() {
        return this.move(Direction.right);
    }

    @Override
    public boolean canMove() {
        return Arrays.stream(Direction.values()).anyMatch(this::canMove);
    }

    @Override
    public abstract boolean canMove(Direction direction);

    @Override
    public abstract boolean move(Direction direction);

}
