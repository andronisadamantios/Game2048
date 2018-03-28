package game2048.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Matrix implements IMatrix {

    public static class Coor {

        private final int row;
        private final int col;

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Coor(int row, int col) {
            this.row = (row < 0 ? 0 : row);
            this.col = (col < 0 ? 0 : col);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.row , this.col);
        }
        
        

    }

    public static class Vector {

        public static Vector get(int drow, int dcol) {
            if (drow == 0 && dcol == 0) {
                return Vector.ZERO;
            }
            return new Vector(drow, dcol);
        }

        public static final Vector ZERO = new Vector((int) 0, (int) 0);

        public static Vector getFromTo(Coor coorStart, Coor coorEnd) {
            return new Vector((int) (coorEnd.row - coorStart.row), (int) (coorEnd.col - coorStart.col));
        }

        private final int dRow; // row
        private final int dCol; // col

        public int getDRow() {
            return dRow;
        }

        public int getDCol() {
            return dCol;
        }

        public Vector(int dRow, int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", this.dRow , this.dCol);
        }
        

    }

    public abstract class RowColumnOperator {

        protected final int index;

        /**
         *
         * @return the 0 based index of the row or column that this objects
         * operates
         */
        public int getIndex() {
            return index;
        }

        public RowColumnOperator(int index) {
            this.index = index;
        }

        abstract int getLength();

        abstract int get(int index);

        abstract void set(int index, int value);

        abstract Coor getCoor(int index);

    }

    class RowOperator extends RowColumnOperator {

        public RowOperator(int index) {
            super(index);
        }

        @Override
        int get(int index) {
            return Matrix.this.array[this.index][index];
        }

        @Override
        void set(int index, int value) {
            Matrix.this.array[this.index][index] = value;
        }

        @Override
        int getLength() {
            return Matrix.this.cols;
        }

        @Override
        Coor getCoor(int index) {
            return new Coor(this.index, index);
        }

    }

    class ColumnOperator extends RowColumnOperator {

        public ColumnOperator(int index) {
            super(index);
        }

        @Override
        int get(int index) {
            return Matrix.this.array[index][this.index];
        }

        @Override
        void set(int index, int value) {
            Matrix.this.array[index][this.index] = value;
        }

        @Override
        int getLength() {
            return Matrix.this.rows;
        }

        @Override
        Coor getCoor(int index) {
            return new Coor(index, this.index);
        }

    }

    public final IArrayExtractorSetter rowExtractorSetter = new IArrayExtractorSetter() {
        @Override
        public int[] get(int index) {
            //return Matrix.this.array[index];
            return Arrays.copyOf(Matrix.this.array[index], Matrix.this.cols);
        }

        @Override
        public void set(int index, int[] newArray) {
            Matrix.this.array[index] = newArray;
        }
    };
    public final IArrayExtractorSetter colExtractorSetter = new IArrayExtractorSetter() {
        @Override
        public int[] get(int index) {
            int[] newArray = new int[Matrix.this.rows];
            for (int i = 0; i < Matrix.this.rows; i++) {
                newArray[i] = Matrix.this.array[i][index];
            }
            return newArray;
        }

        @Override
        public void set(int index, int[] newArray) {
            for (int i = 0; i < Matrix.this.rows; i++) {
                Matrix.this.array[i][index] = newArray[i];
            }
        }
    };

    protected final int rows;
    protected final int cols;
    protected final int[][] array;

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    public int[] getRow(int index) {
        return this.rowExtractorSetter.get(index);
    }

    public int[] getCol(int index) {
        return this.colExtractorSetter.get(index);
    }

    public RowColumnOperator getRowOperator(int index) {
        return new RowOperator(index);
    }

    public RowColumnOperator getColOperator(int index) {
        return new ColumnOperator(index);
    }

    /**
     *
     * @return a new 2 dimensional array of the current values
     */
    public int[][] getArrayDeepCopy() {
        int[][] newArray = new int[rows][cols];
        for (int i = 0; i < this.rows; i++) {
            System.arraycopy(this.array[i], 0, newArray[i], 0, this.cols);
        }
        return newArray;
    }

    @Override
    public int get(int i, int j) {
        return array[i][j];
    }

    @Override
    public void set(int i, int j, int value) {
        this.array[i][j] = value;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.array = new int[this.rows][this.cols];
        for (int[] ar : array) {
            Arrays.fill(ar, (int) 0);
        }
    }

    public List<Coor> getEmptyCoors() {
        ArrayList<Coor> list = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.array[i][j] == 0) {
                    list.add(new Coor(i, j));
                }
            }
        }
        return list;
    }
    public List<Coor> getNonEmptyCoors() {
        ArrayList<Coor> list = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.array[i][j] != 0) {
                    list.add(new Coor(i, j));
                }
            }
        }
        return list;
    }

    /*
    ola se ena array kai find max
     */
    public int findMax2() {
        int[] ar = new int[rows * cols];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = this.array[i / this.cols][i % this.cols];
        }
        return (int) Arrays.stream(ar).boxed().max(Comparator.naturalOrder()).get();
    }

    /*
    to kathe row ginetai map sto megisto tou
    kai apo auto to stream me ta megista vrisketai to megisto
     */
    public int findMax() {
        return Arrays.stream(this.array).map(Arrays::stream).map(IntStream::boxed)
                .map(s -> s.max(Comparator.naturalOrder()).get())
                .max(Comparator.naturalOrder()).get();
    }

    @Override
    public String toString() {
        Iterable<String> iter = () -> Arrays.stream(this.array).map(Arrays::toString).iterator();
        return String.join("\n", iter);
    }

}
