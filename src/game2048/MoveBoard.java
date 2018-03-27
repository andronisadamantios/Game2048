
package game2048;

import java.util.ArrayList;
import java.util.Collection;

public class MoveBoard {
    
    public static final MoveBoard EMPTY = new MoveBoard(true);
    
    private final boolean  positive; // {true, false} -> {up|left, down|right}
    public final Collection<MoveTile> tileMoves = new ArrayList<>();

    public boolean isPositive() {
        return positive;
    }

    public MoveBoard(boolean positive) {
        this.positive = positive;
    }

}
