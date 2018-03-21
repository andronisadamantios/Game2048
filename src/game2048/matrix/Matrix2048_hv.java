package game2048.matrix;

import game2048.Direction;

/*
base matrix for game 2048
παρεχει 2 μεθοδους για υλοποιηση για την κινηση σε καθε orientation (hor|ver)
 */
public abstract class Matrix2048_hv extends Matrix2048 implements IMatrix2048 {

    public Matrix2048_hv(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMoveUp() {
        return this.canMoveUpDown(Direction.up.getValue());
    }

    @Override
    public boolean canMoveDown() {
        return this.canMoveUpDown(Direction.down.getValue());
    }

    @Override
    public boolean canMoveLeft() {
        return this.canMoveLeftRight(Direction.left.getValue());
    }

    @Override
    public boolean canMoveRight() {
        return this.canMoveLeftRight(Direction.right.getValue());
    }

    @Override
    public void moveUp() {
        this.moveUpDown(Direction.up.getValue());
    }

    @Override
    public void moveDown() {
        this.moveUpDown(Direction.down.getValue());
    }

    @Override
    public void moveLeft() {
        this.moveLeftRight(Direction.left.getValue());
    }

    @Override
    public void moveRight() {
        this.moveLeftRight(Direction.right.getValue());
    }

    public boolean canMoveUpDown(int ud) {
        throw new UnsupportedOperationException("todo");
    }

    public boolean canMoveLeftRight(int lr) {
        throw new UnsupportedOperationException("todo");
    }

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @return true αν εγινε κινηση
     */
    public void moveUpDown(int ud) {
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            this.moveUpDownCol(ud, j);
        }
    }

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @return true αν εγινε κινηση
     */
    public void moveLeftRight(int lr) {
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            this.moveLeftRightRow(lr, i);
        }
    }

    public abstract boolean canMoveUpDownCol(int ud, int col);

    public abstract boolean canMoveLeftRightRow(int lr, int row);

    /**
     * καθετη κινηση σε μια στηλη
     *
     * @param ud 1, -1 (πανω ή κατω)
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract void moveUpDownCol(int ud, int col);

    /**
     * οριζοντια κινηση σε μια σειρα
     *
     * @param lr 1, -1 (αριστερα ή δεξια)
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract void moveLeftRightRow(int lr, int row);
}
