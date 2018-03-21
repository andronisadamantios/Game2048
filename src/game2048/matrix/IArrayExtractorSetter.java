
package game2048.matrix;

/*
extracts or sets rows or columns of 2 dimensional matrix
*/
public interface IArrayExtractorSetter {

    int[] get(int index);

    void set(int index, int[] newArray);

}
