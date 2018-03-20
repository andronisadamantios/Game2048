package game2048.matrix;

import game2048.Direction;

/*
base matrix for game 2048
παρεχει 2 μεθοδους για υλοποιηση για την κινηση σε καθε orientation (hor|ver)
 */
public abstract class Matrix2048_hv extends Matrix2048_base {

    public static Matrix2048_base getHV(int rows, int cols) {
        return new Matrix2048_dry(rows, cols);
    }

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

/*
base matrix for game 2048 that pushes rows and columns in 3 stpes
1) ola ta 0 sthn arkh, 2) merge, 3) ola ta mhden pou meinane apo to 0 sthn akrh
it has 2 algorithms: 1) ta mhden sthn akrh, 2) merge
it has only 4 methods (2 for every orientation (hor|ver))
 */
class Matrix2048_dry extends Matrix2048_hv {

    public Matrix2048_dry(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean upDownCol(int ud, int col) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToV(ud, col);
        b2 |= this.mergePairsToV(ud, col);
        if (b2) {
            this.nonZerosToV(ud, col);
        }
        return b1 | b2;
    }

    @Override
    public boolean leftRightRow(int lr, int row) {
        boolean b1 = false, b2 = false;
        b1 |= this.nonZerosToH(lr, row);
        b2 |= this.mergePairsToH(lr, row);
        if (b2) {
            this.nonZerosToH(lr, row);
        }
        return b1 | b2;
    }

    /*
    merge pairs horizontally
    otan yparxoun 2 diadoxika idia to aristera kata thn aristerh kinhsh or to deksia kata thn deksia kinhsh diplasiazetai
    kai to allo ginetai 0
     */
    public boolean mergePairsToH(int lr, int row) {
        boolean result = false;
        final int start = (cols - 1) * (1 - lr) / 2;
        final int bound = (cols - 1) * (1 + lr) / 2;
        for (int j = start; (bound - lr * j > 0) && (array[row][j] != 0); j += lr) {
            if (array[row][j] == array[row][j + lr]) {
                result = true;
                array[row][j] <<= 1;
                array[row][j + lr] = 0;
                j += lr;
            }
        }
        return result;
    }

    /*
    move zeros to akrh for left or right
    when left ta 0 pane right
     */
    public boolean nonZerosToH(int lr, int row) {
        boolean result = false;
        final int start = (cols - 1) * (1 - lr) / 2;
        final int bound = cols - start;
        int srcIndex = start;
        int destIndex = start;

        do {
            while (bound - lr * srcIndex > 0 && array[row][srcIndex] != 0) {
                result = result || (srcIndex != destIndex);
                array[row][destIndex] = array[row][srcIndex];
                destIndex += lr;
                srcIndex += lr;
            }
            srcIndex += lr;
        } while (bound - lr * srcIndex > 0);

        for (int j = destIndex; bound - lr * j > 0; j += lr) {
            array[row][j] = 0;
        }
        return result;
    }

    /*
    merge pairs vertically
    otan yparxoun 2 diadoxika idia to panw kata thn panw kinhsh or to katw kata thn katw kinhsh diplasiazetai
    kai to allo ginetai 0
     */
    public boolean mergePairsToV(int ud, int col) {
        boolean result = false;
        final int start = (rows - 1) * (1 - ud) / 2;
        final int bound = (rows - 1) * (1 + ud) / 2;
        for (int i = start; (bound - ud * i > 0) && (array[i][col] != 0); i += ud) {
            if (array[i][col] == array[i + ud][col]) {
                result = true;
                array[i][col] <<= 1;
                array[i + ud][col] = 0;
                i += ud;
            }
        }
        return result;
    }

    /*
    move zeros to akrh for up or down
    when up ta 0 pane down
     */
    public boolean nonZerosToV(int ud, int col) {
        boolean result = false;
        final int start = (rows - 1) * (1 - ud) / 2;
        final int bound = rows - start;
        int srcIndex = start;
        int destIndex = start;

        do {
            while (bound - ud * srcIndex > 0 && array[srcIndex][col] != 0) {
                result = result || (srcIndex != destIndex);
                array[destIndex][col] = array[srcIndex][col];
                destIndex += ud;
                srcIndex += ud;
            }
            srcIndex += ud;
        } while (bound - ud * srcIndex > 0);

        for (int i = destIndex; bound - ud * i > 0; i += ud) {
            array[i][col] = 0;
        }
        return result;
    }

}
