package game2048.matrix;

/*
base matrix for game 2048
παρεχει 4 μεθοδους για υλοποιηση για την κινηση σε καθε direction (up|down|left|right)
matrix for game 2048 that pushes rows and columns in 3 stpes
has 8 methods (2 for every direction (up|down|left|right))
idio me to dry alla might be faster
 */
public abstract class Matrix2048_udlr extends Matrix2048_base {

    public Matrix2048_udlr(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean up() {
        boolean b = false;
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            b |= this.upCol(j);
        }
        return b;
    }

    @Override
    public boolean down() {
        boolean b = false;
        // gia kathe column
        for (int j = 0; j < this.cols; j++) {
            b |= this.downCol(j);
        }
        return b;
    }

    @Override
    public boolean left() {
        boolean b = false;
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            b |= this.leftRow(i);
        }
        return b;
    }

    @Override
    public boolean right() {
        boolean b = false;
        // gia kathe row
        for (int i = 0; i < this.rows; i++) {
            b |= this.rightRow(i);
        }
        return b;
    }

    /**
     * πανω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean upCol(int col);

    /**
     * κατω κινηση σε μια στηλη
     *
     * @param col το 0 based column
     * @return true αν εγινε κινηση
     */
    public abstract boolean downCol(int col);

    /**
     * αριστερη κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean leftRow(int row);

    /**
     * δεξια κινηση σε μια σειρα
     *
     * @param row το 0 based row
     * @return true αν εγινε κινηση
     */
    public abstract boolean rightRow(int row);

}
