package game2048.move;

/**
 * η τιμή (ο εκθέτης) που σχετίζεται με μια κίνηση
 *
 * @author damhs
 */
public class Move {

    private final int value;

    public int getValue() {
        return value;
    }

    public Move(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%d", this.value);
    }

}
