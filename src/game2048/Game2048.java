package game2048;

import game2048.matrix.IMatrix2048;
import game2048.matrix.Matrix;
import game2048.matrix.Matrix2048_1;
import game2048.matrix.Matrix2048_2;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Game2048 {

    public final static int ROWS = 4;
    public final static int COLS = 4;

    public static enum Result {
        won, lost
    }

    public static final Supplier<Integer> rg = () -> (int) (Math.random() < 0.9 ? 1 : 2);

    private IMatrix2048 matrix2048;
    private final Map<Direction, Boolean> mapValidNextDirections = new HashMap<>();
    private final Map<Direction, Move> mapValidNextMoves = new HashMap<>();
    private int score;
    private int moves;
    private Result result;

    public int getValue(int i, int j) {
        int value = ((Matrix) this.matrix2048).get(i, j);
        if (value > 0) {
            return (int) Math.pow(2, value);
        }
        return 0;
    }

    public int getScore() {
        return score;
    }

    public int getMoves() {
        return moves;
    }

    public int getMax() {
        return this.matrix2048.getMax();
    }

    public Result getResult() {
        return result;
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
        if (this.isFinished() || !this.canMove(dir)) {
            return false;
        }
        boolean b = this.matrix2048.move(dir);
        if (b) {
            this.moves++;
            // an den mporei na mpei kainourio value den peirazei
            // mporei na ginetai merge
            this.addNewValue();
            this.calculateResult();
        }

        return b;
    }

    private void calculateResult() {
        for (Direction v : Direction.values()) {
            this.mapValidNextDirections.compute(v, (d, b1) -> this.matrix2048.canMove(d));
        }
        if (this.matrix2048.getMax() == 11) {
            this.result = Result.won;
        } else if (!this.canMove()) {
            this.result = Result.lost;
        }
    }

    private boolean canMove(Direction direction) {
        return this.mapValidNextDirections.get(direction);
    }

    private boolean canMove() {
        return this.mapValidNextDirections.values().stream().anyMatch(b -> b);
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
        this.matrix2048 = new Matrix2048_2(ROWS, COLS);
        for (Direction dir : Direction.values()) {
            this.mapValidNextDirections.replace(dir, Boolean.FALSE);
            this.mapValidNextDirections.putIfAbsent(dir, Boolean.FALSE);
            this.mapValidNextMoves.replace(dir, null);
            this.mapValidNextMoves.putIfAbsent(dir, null);
        }

        this.addNewValue();
        this.addNewValue();
        this.calculateResult();
    }

}
