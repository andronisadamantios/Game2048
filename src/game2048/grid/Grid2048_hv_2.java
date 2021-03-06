package game2048.grid;

/**
 * this class extracts a whole row or column calculates its next after a move η
 * κινηση γινεται με ενα αλγοριθμο για ολα τα direction and replaces it as a
 * whole
 */
public class Grid2048_hv_2 extends Grid2048_hv {

    public Grid2048_hv_2(int rows, int cols) {
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
                return true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += startFrom;
                }
                if (srcIndex != destIndex) {
                    return true;
                }
                newArray[destIndex] = n;
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
                // add move
                destIndex += startFrom;
                result = true;
            } else {
                if (newArray[destIndex] != 0) {
                    // add move
                    destIndex += startFrom;
                }
                newArray[destIndex] = n;
                result = result || srcIndex != destIndex;
            }
        }
        return (result ? newArray : null);
    }

    @Override
    protected boolean canMoveUpDownCol(int ud, int col) {
        return this.canMoveArray(this.getColExtractorSetter().get(col), ud);
    }

    @Override
    protected boolean canMoveLeftRightRow(int lr, int row) {
        return this.canMoveArray(this.GetRowExtractorSetter().get(row), lr);
    }

    @Override
    protected boolean moveUpDownCol(int ud, int col) {
        int[] newArray = this.calculateArray(this.getColExtractorSetter().get(col), ud);
        if (newArray != null) {
            this.getColExtractorSetter().set(col, newArray);
            return true;
        }
        return false;
    }

    @Override
    protected boolean moveLeftRightRow(int lr, int row) {
        int[] newArray = this.calculateArray(this.GetRowExtractorSetter().get(row), lr);
        if (newArray != null) {
            this.GetRowExtractorSetter().set(row, newArray);
            return true;
        }
        return false;
    }

}
