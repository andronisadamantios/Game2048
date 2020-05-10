package game2048.grid;

import grid.Grid;
import grid.GridVector;
import java.util.Arrays;

/**
 * subClass of Grid2048 εξειδικευμενο για το παιχνιδι Game2048. notes:
 * internal values are in this case the exponents of 2. represented values are
 * the power of 2 to the internal value
 */
public abstract class Grid2048 extends Grid implements IGrid2048 {

    public static int mapInternalToRepresented(int value) {
        return (int) Math.pow(2, value);
    }

    public Grid2048(int rows, int cols) {
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
        return this.canMove(GridVector.UP);
    }

    @Override
    public boolean canMoveDown() {
        return this.canMove(GridVector.DOWN);
    }

    @Override
    public boolean canMoveLeft() {
        return this.canMove(GridVector.LEFT);
    }

    @Override
    public boolean canMoveRight() {
        return this.canMove(GridVector.RIGHT);
    }

    @Override
    public boolean moveUp() {
        return this.move(GridVector.UP);
    }

    @Override
    public boolean moveDown() {
        return this.move(GridVector.DOWN);
    }

    @Override
    public boolean moveLeft() {
        return this.move(GridVector.LEFT);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    @Override
    public boolean moveRight() {
        return this.move(GridVector.RIGHT);
    }

    @Override
    public boolean canMove() {
        return Arrays.stream(GridVector.DIRECTIONS).anyMatch(this::canMove);
    }

    @Override
    public abstract boolean canMove(grid.GridVector direction);

    @Override
    public abstract boolean move(GridVector direction);

}
