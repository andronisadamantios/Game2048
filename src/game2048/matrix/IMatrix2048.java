package game2048.matrix;

import game2048.Direction;
import java.util.Collections;
import java.util.Map;

/*
base matrix for game 2048
yparxoun 4 kinhseis
 */
public interface IMatrix2048 {

    default boolean canMoveUp() {
        return this.canMove(Direction.up);
    }

    default boolean canMoveDown() {
        return this.canMove(Direction.down);
    }

    default boolean canMoveLeft() {
        return this.canMove(Direction.left);
    }

    default boolean canMoveRight() {
        return this.canMove(Direction.right);
    }

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveUp() {
        return this.move(Direction.up);
    }

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveDown() {
        return this.move(Direction.down);
    }

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveLeft() {
        return this.move(Direction.left);
    }

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    default boolean moveRight() {
        return this.move(Direction.right);
    }

    boolean canMove();
    boolean canMove(Direction direction);

    boolean move(Direction direction);

    int getMax();
    
    default Map<Matrix.Coor, Matrix.Coor> getLastMove(){
        return Collections.emptyMap();
    }

}
