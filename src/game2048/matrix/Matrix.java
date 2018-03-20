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

        private final byte y; // row
        private final byte x; // col

        public byte getX() {
            return y;
        }

        public byte getY() {
            return x;
        }

        public Vector(byte row, byte col) {
            this.y = row;
            this.x = col;
        }

    }

    protected final int rows;
    protected final int cols;
    protected final int[][] array;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
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
