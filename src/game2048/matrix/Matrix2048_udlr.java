package game2048.matrix;

/*
base matrix for game 2048
παρεχει 4 μεθοδους για υλοποιηση για την κινηση σε καθε direction (up|down|left|right)
matrix for game 2048 that pushes rows and columns in 3 stpes
has 8 methods (2 for every direction (up|down|left|right))
idio me to dry alla might be faster
 */
public abstract class Matrix2048_udlr extends Matrix2048_base {

    public static Matrix2048_base getUDLR(int rows, int cols) {
        return new Matrix2048_wet(rows, cols);
    }

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

class Matrix2048_wet extends Matrix2048_udlr {

    public Matrix2048_wet(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean upCol(int col) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToUp(col);
        b2 |= this.mergePairsToUp(col);
        if (b2) {
            this.nonZerosToUp(col);
        }
        return b1 | b2;
    }

    @Override
    public boolean downCol(int col) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToDown(col);
        b2 |= this.mergePairsToDown(col);
        if (b2) {
            this.nonZerosToDown(col);
        }
        return b1 | b2;
    }

    @Override
    public boolean leftRow(int row) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToLeft(row);
        b2 |= this.mergePairsToLeft(row);
        if (b2) {
            this.nonZerosToLeft(row);
        }
        return b1 | b2;
    }

    @Override
    public boolean rightRow(int row) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToRight(row);
        b2 |= this.mergePairsToRight(row);
        if (b2) {
            this.nonZerosToRight(row);
        }
        return b1 | b2;
    }

    private boolean mergePairsToLeft(int row) {
        boolean result = false;
        for (int i = 0; (i < this.cols - 1) && (array[row][i] != 0); i++) {
            if (array[row][i] == array[row][i + 1]) {
                result = true;
                array[row][i] <<= 1;
                array[row][i + 1] = 0;
                i++;
            }
        }
        return result;
    }

    private boolean mergePairsToRight(int row) {
        boolean result = false;
        for (int i = this.cols - 1; (i > 0) && (array[row][i] != 0); i--) {
            if (array[row][i] == array[row][i - 1]) {
                result = true;
                array[row][i] <<= 1;
                array[row][i - 1] = 0;
                i--;
            }
        }
        return result;
    }

    private boolean mergePairsToUp(int col) {
        boolean result = false;
        for (int i = 0; (i < this.cols - 1) && (array[i][col] != 0); i++) {
            if (array[i][col] == array[i + 1][col]) {
                result = true;
                array[i][col] <<= 1;
                array[i + 1][col] = 0;
                i++;
            }
        }
        return result;
    }

    private boolean mergePairsToDown(int col) {
        boolean result = false;
        for (int i = this.cols - 1; (i > 0) && (array[i][col] != 0); i--) {
            if (array[i][col] == array[i - 1][col]) {
                result = true;
                array[i][col] <<= 1;
                array[i - 1][col] = 0;
                i--;
            }
        }
        return result;
    }

    private boolean nonZerosToLeft(int row) {
        boolean result = false;
        int destIndex = 0;
        int srcIndex = 0;
        do {
            while (srcIndex < this.cols && array[row][srcIndex] != 0) {
                result = result || (srcIndex != destIndex);
                array[row][destIndex++] = array[row][srcIndex++];
            }
            srcIndex++;
        } while (srcIndex < this.cols);

        for (int i = destIndex; i < this.cols; i++) {
            array[row][i] = 0;
        }
        return result;
    }

    private boolean nonZerosToRight(int row) {
        boolean result = false;
        int destIndex = this.cols - 1;
        int srcIndex = this.cols - 1;
        do {
            while (srcIndex > -1 && array[row][srcIndex] != 0) {
                result = result || (srcIndex != destIndex);
                array[row][destIndex--] = array[row][srcIndex--];
            }
            srcIndex--;
        } while (srcIndex > -1);

        for (int i = destIndex; i > -1; i--) {
            array[row][i] = 0;
        }
        return result;
    }

    private boolean nonZerosToUp(int col) {
        boolean result = false;
        int destIndex = 0;
        int srcIndex = 0;
        do {
            while (srcIndex < this.cols && array[srcIndex][col] != 0) {
                result = result || (srcIndex != destIndex);
                array[destIndex++][col] = array[srcIndex++][col];
            }
            srcIndex++;
        } while (srcIndex < this.cols);

        for (int i = destIndex; i < this.cols; i++) {
            array[i][col] = 0;
        }
        return result;
    }

    private boolean nonZerosToDown(int col) {
        boolean result = false;
        int destIndex = this.cols - 1;
        int srcIndex = this.cols - 1;
        do {
            while (srcIndex > -1 && array[srcIndex][col] != 0) {
                result = result || (srcIndex != destIndex);
                array[destIndex--][col] = array[srcIndex--][col];
            }
            srcIndex--;
        } while (srcIndex > -1);

        for (int i = destIndex; i > -1; i--) {
            array[i][col] = 0;
        }
        return result;
    }

}
