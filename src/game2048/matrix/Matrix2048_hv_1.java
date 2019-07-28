package game2048.matrix;

import matrix.RowColumnOperator;

/**
 * this class uses a column/row operator to edit the elements. η κινηση γινεται
 * με ενα αλγοριθμο για ολα τα direction
 */
public class Matrix2048_hv_1 extends Matrix2048_hv implements IMatrix2048 {

    public Matrix2048_hv_1(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    protected boolean canMoveUpDownCol(int ud, int col) {
        return this.moveUpDownCol(ud, col, false);
    }

    @Override
    protected boolean canMoveLeftRightRow(int lr, int row) {
        return this.moveLeftRightRow(lr, row, false);
    }

    @Override
    protected boolean moveUpDownCol(int ud, int col) {
        return this.moveUpDownCol(ud, col, true);
    }

    @Override
    protected boolean moveLeftRightRow(int lr, int row) {
        return this.moveLeftRightRow(lr, row, true);
    }

    private boolean moveUpDownCol(int ud, int col, boolean move) {
        RowColumnOperator colOperator = this.getColOperator(col);
        return this.moveRowCol(ud, colOperator, move);
    }

    private boolean moveLeftRightRow(int lr, int row, boolean move) {
        RowColumnOperator rowOperator = this.getRowOperator(row);
        return this.moveRowCol(lr, rowOperator, move);
    }

    /**
     * moves a row or column (p = 1) => kinhsh (panw|aristera) (p = -1) =>
     * kinhsh (katw|deksia)
     *
     * @param p {1, -1} -> {start from start, start from end}
     * @param rco the row or column operator
     * @param move if true it operates the row or column (changes its values)
     * @return a boolean if a move is possible
     */
    protected boolean moveRowCol(int p, RowColumnOperator rco, boolean move) {
        boolean result = false;
        final int start = (rco.getLength() - 1) * (1 - p) / 2;
        final int bound = ((rco.getLength() - 1) * p + rco.getLength() + 1) / 2;
        int[] newArray = new int[rco.getLength()];
        int destIndex = start;

        for (int srcIndex = start; bound - p * srcIndex > 0; srcIndex += p) {
            int n = rco.get(srcIndex);
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                result = true;
                if (!move) {
                    return true;
                }
                newArray[destIndex]++;
                destIndex += p;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += p;
                }
                newArray[destIndex] = n;
                if (srcIndex != destIndex) {
                    result = true;
                    if (!move) {
                        return true;
                    }
                }
            }
        }
        if (move) {
            for (int i = 0; i < rco.getLength(); i++) {
                rco.set(i, newArray[i]);
            }
        }
        return result;
    }

}
