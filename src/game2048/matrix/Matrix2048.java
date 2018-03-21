
package game2048.matrix;

public abstract class Matrix2048  extends  Matrix implements IMatrix2048{

    public Matrix2048(int rows, int cols) {
        super(rows, cols);
    }

    @Override
    public int getMaxPowerOf2() {
        return this.findMax();
    }
    
    

}
