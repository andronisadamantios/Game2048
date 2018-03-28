package game2048.move;

import java.util.Collection;
import java.util.Collections;

public abstract class MoveBoard {

    public static final MoveBoard EMPTY = new MoveBoard() {
        @Override
        public Collection<MoveTile> getTileMoves() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public Collection<AddTile> getTileAdds() {
            return Collections.EMPTY_LIST;
        }

        @Override
        public int getDirectionPositivity() {
            return 0;
        }
    };

    public abstract Collection<MoveTile> getTileMoves();

    public abstract Collection<AddTile> getTileAdds();

    public abstract int getDirectionPositivity();

}
