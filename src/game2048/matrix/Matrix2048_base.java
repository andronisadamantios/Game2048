package game2048.matrix;

/*
base matrix for game 2048
yparxoun 4 kinhseis
 */
public abstract class Matrix2048_base extends Matrix {

    public Matrix2048_base(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    public abstract boolean up();

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    public abstract boolean down();

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    public abstract boolean left();

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    public abstract boolean right();

}
