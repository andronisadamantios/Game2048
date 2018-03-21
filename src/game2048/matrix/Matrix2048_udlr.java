package game2048.matrix;

/*
base matrix for game 2048
παρεχει 4 μεθοδους για υλοποιηση για την κινηση σε καθε direction (up|down|left|right)
matrix for game 2048 that pushes rows and columns in 3 stpes
has 8 methods (2 for every direction (up|down|left|right))
idio me to dry alla might be faster
 */
public abstract class Matrix2048_udlr extends Matrix2048 implements IMatrix2048 {

    public Matrix2048_udlr(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean canMoveUp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMoveDown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMoveLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMoveRight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveUp() {
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            this.moveUpCol(j);
        }
    }

    @Override
    public void moveDown() {
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            this.moveDownCol(j);
        }
    }

    @Override
    public void moveLeft() {
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            this.moveLeftRow(i);
        }
    }

    @Override
    public void moveRight() {
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            this.moveRightRow(i);
        }
    }

    public abstract boolean canMoveUpCol(int col);

    public abstract boolean canMoveDownCol(int col);

    public abstract boolean canMoveLeftRow(int row);

    public abstract boolean canMoveRightRow(int row);

    /**
     * πανω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract void moveUpCol(int col);

    /**
     * κατω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract void moveDownCol(int col);

    /**
     * αριστερη κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract void moveLeftRow(int row);

    /**
     * δεξια κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract void moveRightRow(int row);

}
