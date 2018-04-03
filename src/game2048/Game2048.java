package game2048;

import game2048.matrix.IMatrix2048;
import game2048.matrix.Matrix;
import static game2048.matrix.Matrix2048.mapInternalToRepresented;
import game2048.matrix.Matrix2048_hv_1_move;
import game2048.move.AddTile;
import java.util.List;
import java.util.function.Supplier;

public class Game2048 {

    public final static int ROWS = 4;
    public final static int COLS = 4;

    public static enum Result {
        won, lost
    }

    public static final Supplier<Integer> rg = () -> (int) (Math.random() < 0.9 ? 1 : 2);

    private IMatrix2048 matrix2048;
    private int score;
    private int moves;
    private Result result;

    public IMatrix2048 getMatrix() {
        return this.matrix2048;
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public int getMax() {
        return this.matrix2048.getMaxInternalValue();
    }

    public Result getResult() {
        return result;
    }

    public boolean isFinished() {
        return this.result != null;
    }

    public boolean isGameOver() {
        return this.result == Result.lost;
    }

    public Game2048() {
        this.reset();
    }

    // up, down, left, right ===> move     OXI     move ===> up, down, left, right 
    // giati prepei na ginei meta apo kathe move addNewValues
    public boolean up() {
        return this.move(Matrix.Vector.UP);
    }

    public boolean down() {
        return this.move(Matrix.Vector.DOWN);
    }

    public boolean left() {
        return this.move(Matrix.Vector.LEFT);
    }

    public boolean right() {
        return this.move(Matrix.Vector.RIGHT);
    }

    public boolean move(Matrix.Vector dir) {
        if (this.isGameOver() || !this.matrix2048.canMove(dir)) {
            return false;
        }
        this.getMatrix().getLastMove().getTileAdds().clear();
        if (this.matrix2048.move(dir)) {
            this.moves++;
            // an den mporei na mpei kainourio value den peirazei
            // mporei na ginetai merge
            this.addNewValue();
            this.calculateResult();
            return true;
        }

        return false;
    }

    private void calculateResult() {
        if (this.matrix2048.getMaxInternalValue() == 11) {
            this.result = Result.won;
        } else if (!this.matrix2048.canMove()) {
            this.result = Result.lost;
        }
    }

    private boolean addNewValue() {
        List<Matrix.Coor> emptyCoors = ((Matrix) this.matrix2048).getEmptyCoors();
        if (!emptyCoors.isEmpty()) {
            Matrix.Coor c = emptyCoors.get((int) (Math.random() * emptyCoors.size()));
            int newValue = rg.get();
            this.matrix2048.set(c.getRow(), c.getCol(), newValue);
            this.matrix2048.getLastMove().getTileAdds().add(new AddTile(c, mapInternalToRepresented(newValue)));
            return true;
        }
        return false;
    }

    public void reset() {
        this.score = 0;
        this.moves = 0;
        this.result = null;
        this.matrix2048 = new Matrix2048_hv_1_move(ROWS, COLS);
        this.addNewValue();
        this.addNewValue();
        this.calculateResult();
    }

}
