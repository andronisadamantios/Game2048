package game2048.move;

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
