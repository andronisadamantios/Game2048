package game2048.matrix;

/**
 * an object that operates on a row or column of a Matrix (Matrix.java) object.
 * it does not replace its row or column, only changes or gets its values
 */
public abstract class RowColumnOperator {

    protected final int index;
    protected final Matrix m;

    /**
     *
     * @return the 0 based index of the row or column that this objects operates
     */
    public int getIndex() {
        return index;
    }

    public RowColumnOperator(Matrix m, int index) {
        this.m = m;
        this.index = index;
    }

    abstract int getLength();

    abstract int get(int index);

    abstract void set(int index, int value);

    abstract Matrix.Coor getCoor(int index);

}

class RowOperator extends RowColumnOperator {

    public RowOperator(Matrix m, int index) {
        super(m, index);
    }

    @Override
    int get(int index) {
        return m.array[this.index][index];
    }

    @Override
    void set(int index, int value) {
        m.array[this.index][index] = value;
    }

    @Override
    int getLength() {
        return m.cols;
    }

    @Override
    Matrix.Coor getCoor(int index) {
        return new Matrix.Coor(this.index, index);
    }

}

class ColumnOperator extends RowColumnOperator {

    public ColumnOperator(Matrix m, int index) {
        super(m, index);
    }

    @Override
    int get(int index) {
        return m.array[index][this.index];
    }

    @Override
    void set(int index, int value) {
        m.array[index][this.index] = value;
    }

    @Override
    int getLength() {
        return m.rows;
    }

    @Override
    Matrix.Coor getCoor(int index) {
        return new Matrix.Coor(index, this.index);
    }

}
