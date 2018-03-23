package game2048.matrix;

/*
this class extracts a whole row or column
calculates its next after a move
η κινηση γινεται με ενα αλγοριθμο για ολα τα direction
and replaces it as a whole
 */
public class Matrix2048_hv_2 extends Matrix2048_hv {

    public Matrix2048_hv_2(int rows, int cols) {
        super(rows, cols);
    }

    protected boolean canMoveArray(int[] array, int startFrom) {
        final int start = (array.length - 1) * (1 - startFrom) / 2;
        final int bound = ((array.length - 1) * startFrom + array.length + 1) / 2;
        int[] newArray = new int[array.length];
        int destIndex = start;
        for (int srcIndex = start; bound - startFrom * srcIndex > 0; srcIndex += startFrom) {
            int n = array[srcIndex];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex]++;
                destIndex += startFrom;
                return true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += startFrom;
                }
                newArray[destIndex] = n;
                if (srcIndex != destIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param array
     * @param startFrom {1, -1} -> {start from start, start from end}
     * @return
     */
    protected int[] calculateArray(int[] array, int startFrom) {
        boolean result = false;
        final int start = (array.length - 1) * (1 - startFrom) / 2;
        final int bound = ((array.length - 1) * startFrom + array.length + 1) / 2;
        int[] newArray = new int[array.length];
        int destIndex = start;
        for (int srcIndex = start; bound - startFrom * srcIndex > 0; srcIndex += startFrom) {
            int n = array[srcIndex];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex]++;
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
        return (result ? newArray : null);
    }

    @Override
    public boolean canMoveUpDownCol(int ud, int col) {
        return this.canMoveArray(this.colExtractorSetter.get(col), ud);
    }

    @Override
    public boolean canMoveLeftRightRow(int lr, int row) {
        return this.canMoveArray(this.rowExtractorSetter.get(row), lr);
    }

    @Override
    public boolean moveUpDownCol(int ud, int col) {
        int[] newArray = this.calculateArray(this.colExtractorSetter.get(col), ud);
        if (newArray != null) {
            this.colExtractorSetter.set(col, newArray);
            return true;
        }
        return false;
    }

    @Override
    public boolean moveLeftRightRow(int lr, int row) {
        int[] newArray = this.calculateArray(this.rowExtractorSetter.get(row), lr);
        if (newArray != null) {
            this.rowExtractorSetter.set(row, newArray);
            return true;
        }
        return false;
    }

}
