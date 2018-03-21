package game2048.matrix;

public class Matrix2048_2 extends Matrix2048_hv {

    public Matrix2048_2(int rows, int cols) {
        super(rows, cols);
    }

    protected boolean canMoveArray(int[] array, int startFrom) {

    }

    /**
     *
     * @param array
     * @param startFrom {1, -1} -> {start from start, start from end}
     * @return
     */
    protected int[] calculateArray(int[] array, int startFrom) {
        boolean result = false;
        final int start = (this.cols - 1) * (1 - startFrom) / 2;
        final int bound = ((this.cols - 1) * startFrom + this.cols + 1) / 2;
        int[] newArray = new int[this.cols];
        int destIndex = start;
        for (int srcIndex = start; bound - startFrom * srcIndex > 0; srcIndex += startFrom) {
            int n = array[srcIndex];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex] <<= 1;
                destIndex += startFrom;
                result = true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += startFrom;
                }
                newArray[destIndex] = n;
                result = result || srcIndex != destIndex;
            }
        }
        return newArray;
    }

    @Override
    public boolean canMoveUpDownCol(int ud, int col) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean canMoveLeftRightRow(int lr, int row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveUpDownCol(int ud, int col) {
        this.colExtractorSetter.set(col, this.calculateArray(this.colExtractorSetter.get(col), ud));
    }

    @Override
    public void moveLeftRightRow(int lr, int row) {
        this.rowExtractorSetter.set(row, this.calculateArray(this.rowExtractorSetter.get(row), lr));
    }

}
