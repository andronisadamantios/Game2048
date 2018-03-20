package game2048.matrix;

import java.util.Arrays;
import java.util.function.Supplier;


/*
den einai to 2048, einai paromoio
prepei na dw tous kanones
 */
public class MatrixKati extends Matrix {
    protected Supplier<Integer> rg;
    public MatrixKati() {
        super(4,4);
       for (int[] ar : array) {
            Arrays.fill(ar, (int)1);
        }
        this.rg = () -> (int) Math.pow(2, (int) (5 * Math.random()));
    }


    public void up() {
        this.updown(1);
    }

    public void down() {
        this.updown(-1);
    }


    public void left() {
        this.leftRight(1);
    }

    public void right() {
        this.leftRight(-1);
    }

    public void updown(int a) {
        int b = 3 * (1 - a) / 2;
        int c = 2 * (1 - a) / 2;
        int d = 3 * (a + 1) / 2;
        int e = -3 * (a + 1) / 2;
        for (int x = 0; x < 4; x++) {
            array[b][x] *= array[c][x];
            for (int y = c; (e + y) * a < 0; y += a) {
                array[y][x] = array[y + a][x];
            }
            array[d][x] = this.rg.get();
        }
    }

    public void leftRight(int a) {
        int b = 3 * (1 - a) / 2;
        int c = 2 * (1 - a) / 2;
        int d = 3 * (a + 1) / 2;
        int e = -3 * (a + 1) / 2;
        for (int x = 0; x < 4; x++) {
            array[x][b] *= array[x][c];
            for (int y = c; (e + y) * a < 0; y += a) {
                array[x][y] = array[x][y + a];
            }
            array[x][d] = this.rg.get();
        }
    }

}
