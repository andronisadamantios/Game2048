package game2048;

import grid.Grid;
import static game2048.grid.Grid2048.mapInternalToRepresented;
import game2048.grid.Grid2048_hv_1_move;
import game2048.move.AddTile;
import java.util.List;
import java.util.function.Supplier;
import grid.GridVector;
import grid.IGridCoordinates;
import game2048.grid.IGrid2048;

public class Game2048 {

    public final static int ROWS = 4;
    public final static int COLS = 4;

    public static enum Result {
        won, lost
    }

    public static final Supplier<Integer> rg = () -> (int) (Math.random() < 0.9 ? 1 : 2);

    private IGrid2048 matrix2048;
    private int score;
    private int moves;
    private Result result;

    public IGrid2048 getGrid() {
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
        return this.move(GridVector.UP);
    }

    public boolean down() {
        return this.move(GridVector.DOWN);
    }

    public boolean left() {
        return this.move(GridVector.LEFT);
    }

    public boolean right() {
        return this.move(GridVector.RIGHT);
    }

    public boolean move(GridVector dir) {
        if (this.isGameOver() || !this.matrix2048.canMove(dir)) {
            return false;
        }
        this.getGrid().getLastMove().clearTileAds();
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
        List<IGridCoordinates> emptyCoors = ((Grid) this.matrix2048).getEmptyCoors();
        if (!emptyCoors.isEmpty()) {
            IGridCoordinates c = emptyCoors.get((int) (Math.random() * emptyCoors.size()));
            int newValue = rg.get();
            this.matrix2048.set(c.getRow(), c.getCol(), newValue);
            this.matrix2048.getLastMove().addTileAdd(c, mapInternalToRepresented(newValue));
            return true;
        }
        return false;
    }

    public void reset() {
        this.score = 0;
        this.moves = 0;
        this.result = null;
        this.matrix2048 = new Grid2048_hv_1_move(ROWS, COLS);
        this.addNewValue();
        this.addNewValue();
        this.calculateResult();
    }

}
