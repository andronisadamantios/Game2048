package game2048.matrix;

public interface IMatrix {

    int getRows();

    int getCols();

    int get(int i, int j);

    void set(int i, int j, int value);

}
