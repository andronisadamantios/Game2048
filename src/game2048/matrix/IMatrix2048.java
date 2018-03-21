package game2048.matrix;

/*
base matrix for game 2048
yparxoun 4 kinhseis
 */
public interface IMatrix2048 {

    /**
     * κινηση πανω
     *
     * @return true αν εγινε κινηση
     */
    void moveUp();

    /**
     * κινηση κατω
     *
     * @return true αν εγινε κινηση
     */
    void moveDown();

    /**
     * κινηση αριστερα
     *
     * @return true αν εγινε κινηση
     */
    void moveLeft();

    /**
     * κινηση δεξια
     *
     * @return true αν εγινε κινηση
     */
    void moveRight();

    boolean canMoveUp();

    boolean canMoveDown();

    boolean canMoveLeft();

    boolean canMoveRight();
    
    int getMaxPowerOf2();

}
