package game2048.matrix;

import game2048.Direction;
import java.util.Arrays;

public abstract class Matrix2048 extends Matrix implements IMatrix2048 {

    public Matrix2048(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public int getMax() {
        return super.getMax();
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
