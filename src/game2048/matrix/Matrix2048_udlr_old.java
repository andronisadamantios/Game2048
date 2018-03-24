package game2048.matrix;

/*
auto einai san to Matrix2048_hv_old alla exei 8 mathodous (2 gia kathe directions)
 */
public class Matrix2048_udlr_old extends Matrix2048_udlr {

    public Matrix2048_udlr_old(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    protected boolean canMoveUpCol(int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean canMoveDownCol(int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean canMoveLeftRow(int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean canMoveRightRow(int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean moveUpCol(int col) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToUp(col);
        b2 |= this.mergePairsToUp(col);
        if (b2) {
            this.nonZerosToUp(col);
        }
        return b1 | b2;
    }

    @Override
    protected boolean moveDownCol(int col) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToDown(col);
        b2 |= this.mergePairsToDown(col);
        if (b2) {
            this.nonZerosToDown(col);
        }
        return b1 | b2;
    }

    @Override
    protected boolean moveLeftRow(int row) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToLeft(row);
        b2 |= this.mergePairsToLeft(row);
        if (b2) {
            this.nonZerosToLeft(row);
        }
        return b1 | b2;
    }

    @Override
    protected boolean moveRightRow(int row) {
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
