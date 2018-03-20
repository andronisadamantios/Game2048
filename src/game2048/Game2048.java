package game2048;

import game2048.matrix.Matrix;
import game2048.matrix.Matrix2048;
import game2048.matrix.Matrix2048_base;
import game2048.matrix.Matrix2048_hv;
import java.util.List;
import java.util.function.Supplier;

public class Game2048 {

    public final static int ROWS = 4;
    public final static int COLS = 4;

    public static enum Result {
        won, lost
    }

    public static final Supplier<Integer> rg = () -> (int) Math.pow(2, Math.random() < 0.9 ? 1 : 2);
    private Matrix2048_base matrix2048;
    private int score;
    private int moves;
    private Result result;

    public int getValue(int i, int j) {
        return this.matrix2048.get(i, j);
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public int getMax() {
        return this.matrix2048.findMax();
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
                b = this.matrix2048.up();
                break;
            case down:
                b = this.matrix2048.down();
                break;
            case left:
                b = this.matrix2048.left();
                break;
            case right:
                b = this.matrix2048.right();
                break;
            default:
                throw new AssertionError();
        }
        if (b) {
            this.moves++;
            b = b && this.addNewValue();

            // calculate result
            if (this.matrix2048.findMax() == 2048) {
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
        List<Matrix.Coor> emptyCoors = this.matrix2048.getEmptyCoors();
        if (!emptyCoors.isEmpty()) {
            Matrix.Coor c = emptyCoors.get((int) (Math.random() * emptyCoors.size()));
            this.matrix2048.set(c.getRow(), c.getCol(), newValue);
            return true;
        }
        return false;
    }

    public void reset() {
        this.score = 0;
        this.moves = 0;
        this.result = null;
//        this.matrix2048 = Matrix2048_hv.getHV(ROWS, COLS);
        this.matrix2048 = new Matrix2048(ROWS, COLS);
        this.addNewValue();
        this.addNewValue();
    }

}
