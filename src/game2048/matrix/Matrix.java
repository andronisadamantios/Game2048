package game2048.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Matrix {
    
    public static class Coor {

        private final byte row;
        private final byte col;

        public byte getRow() {
            return row;
        }

        public byte getCol() {
            return col;
        }

        public Coor(byte row, byte col) {
            this.row = (row < 0 ? 0 : row);
            this.col = (col < 0 ? 0 : col);
        }

    }
    
    public static class Vector {

        public static final Vector ZERO = new Vector((byte) 0, (byte) 0);

        public static Vector getFromTo(Coor coorStart, Coor coorEnd) {
            return new Vector((byte) (coorEnd.row - coorStart.row), (byte) (coorEnd.col - coorStart.col));
        }

        private final byte dRow; // row
        private final byte dCol; // col

        public byte getDRow() {
            return dRow;
        }

        public byte getDCol() {
            return dCol;
        }

        public Vector(byte dRow, byte dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
    }

    }

    public abstract class RowColumnOperator {

        protected final int index;

        public RowColumnOperator(int index) {
            this.index = index;
        }

        abstract int getLength();

        abstract int get(int index);

        abstract void set(int index, int value);

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

    }

    public final IArrayExtractorSetter rowExtractorSetter = new IArrayExtractorSetter() {
        @Override
        public int[] get(int index) {
            return Matrix.this.array[index];
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

    public int getRows() {
        return rows;
    }

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

    public int get(int i, int j) {
        return array[i][j];
    }

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
        for (byte i = 0; i < this.rows; i++) {
            for (byte j = 0; j < this.cols; j++) {
                if (this.array[i][j] == 0) {
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
    public int getMax() {
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
