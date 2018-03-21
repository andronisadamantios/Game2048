package game2048;

import game2048.matrix.IMatrix2048;
import game2048.matrix.Matrix;
import game2048.matrix.Matrix2048_1;
import java.util.List;
import java.util.function.Supplier;

public class Game2048 {

    public final static int ROWS = 4;
    public final static int COLS = 4;

    public static enum Result {
        won, lost
    }

    public static final Supplier<Integer> rg = () -> (int) Math.pow(2, Math.random() < 0.9 ? 1 : 2);
    private IMatrix2048 matrix2048;
    private int score;
    private int moves;
    private Result result;

    public int getValue(int i, int j) {
        return ((Matrix) this.matrix2048).get(i, j);
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public int getMax() {
        return this.matrix2048.getMaxPowerOf2();
    }

    public boolean isFinished() {
        return this.result != null;
    }

    public Game2048() {
        this.reset();
    }

    // up, down, left, right ===> move     OXI     move ===> up, down, left, right 
    // giati prepei na ginei meta apo kathe move addNewValues
    public boolean up() {
        return this.move(Direction.up);
    }

    public boolean down() {
        return this.move(Direction.down);
    }

    public boolean left() {
        return this.move(Direction.left);
    }

    public boolean right() {
        return this.move(Direction.right);
    }

    public boolean move(Direction dir) {
        boolean b = false;
        switch (dir) {
            case up:
                b = this.matrix2048.canMoveUp();
                this.matrix2048.moveUp();
                break;
            case down:
                b = this.matrix2048.canMoveDown();
                this.matrix2048.moveDown();
                break;
            case left:
                b = this.matrix2048.canMoveLeft();
                this.matrix2048.moveLeft();
                break;
            case right:
                b = this.matrix2048.canMoveRight();
                this.matrix2048.moveRight();
                break;
            default:
                throw new AssertionError();
        }
        if (b) {
            this.moves++;
            b = b && this.addNewValue();

            // calculate result
            if (this.matrix2048.getMaxPowerOf2() == 2048) {
                this.result = Result.won;
            } else if (this.cannotMove()) {
                this.result = Result.lost;
            }
        }

        return b;
    }

    private boolean cannotMove() { // todo
        return false;
    }

    private boolean addNewValue() {
        int newValue = rg.get();
        List<Matrix.Coor> emptyCoors = ((Matrix) this.matrix2048).getEmptyCoors();
        if (!emptyCoors.isEmpty()) {
            Matrix.Coor c = emptyCoors.get((int) (Math.random() * emptyCoors.size()));
            ((Matrix) this.matrix2048).set(c.getRow(), c.getCol(), newValue);
            return true;
        }
        return false;
    }

    public void reset() {
        this.score = 0;
        this.moves = 0;
        this.result = null;
        this.matrix2048 = new Matrix2048_1(ROWS, COLS);
        this.addNewValue();
        this.addNewValue();
    }

}
