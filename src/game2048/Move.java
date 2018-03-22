package game2048;

import game2048.matrix.IMatrix2048;
import game2048.matrix.Matrix;
import java.util.HashMap;
import java.util.Map;

public class Move {

    private final boolean valid;
    private final Map<Matrix.Coor, Matrix.Vector> map;
    private IMatrix2048 matrixStart;

    public Matrix.Vector howShouldMove(Matrix.Coor coor) {
        return this.map.getOrDefault(coor, Matrix.Vector.ZERO);
    }

    public Move() {
        this.valid = false;
        this.map = new HashMap<>();
    }


}
