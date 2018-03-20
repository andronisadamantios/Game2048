package game2048.matrix;

import game2048.Direction;

/*
base matrix for game 2048
παρεχει 2 μεθοδους για υλοποιηση για την κινηση σε καθε orientation (hor|ver)
 */
public abstract class Matrix2048_hv extends Matrix2048_base {

    public Matrix2048_hv(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean up() {
        return this.upDown(Direction.up.getValue());
    }

    @Override
    public boolean down() {
        return this.upDown(Direction.down.getValue());
    }

    @Override
    public boolean left() {
        return this.leftRight(Direction.left.getValue());
    }

    @Override
    public boolean right() {
        return this.leftRight(Direction.right.getValue());
    }

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @return true αν εγινε κινηση
     */
    public boolean upDown(int ud) {
        boolean b = false;
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            b |= this.upDownCol(ud, j);
        }
        return b;
    }

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @return true αν εγινε κινηση
     */
    public boolean leftRight(int lr) {
        boolean b = false;
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            b |= this.leftRightRow(lr, i);
        }
        return b;
    }

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean upDownCol(int ud, int col);

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean leftRightRow(int lr, int row);
}
