package game2048.matrix;

/**
 * η κινηση γινεται με ενα αλγοριθμο
 */
public class Matrix2048_3in1 extends Matrix2048_hv {

    public Matrix2048_3in1(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public boolean upDownCol(int ud, int col) {
        boolean result = false;
        int start = (this.rows - 1) * (1 - ud) / 2;
        int bound = ((this.rows - 1) * ud + this.rows + 1) / 2;
        int[] newArray = new int[this.rows];
        int destIndex = start;
        for (int srcIndex = start; bound - ud * srcIndex > 0; srcIndex += ud) {
            int n = this.array[srcIndex][col];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex] <<= 1;
                destIndex += ud;
                result = true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += ud;
                }
                newArray[destIndex] = n;
                result = result || srcIndex != destIndex;
            }
        }
        for (int i = 0; i < this.rows; i++) {
            this.array[i][col] = newArray[i];
        }
        return result;
    }

    @Override
    public boolean leftRightRow(int lr, int row) {
        boolean result = false;
        int start = (this.cols - 1) * (1 - lr) / 2;
        int bound = ((this.cols - 1) * lr + this.cols + 1) / 2;
        int[] newArray = new int[this.cols];
        int destIndex = start;
        for (int srcIndex = start; bound - lr * srcIndex > 0; srcIndex += lr) {
            int n = this.array[row][srcIndex];
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                newArray[destIndex] <<= 1;
                destIndex += lr;
                result = true;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += lr;
                }
                newArray[destIndex] = n;
                result = result || srcIndex != destIndex;
            }
        }
        for (int i = 0; i < this.cols; i++) {
            this.array[row][i] = newArray[i];
        }
        return result;
    }

}
