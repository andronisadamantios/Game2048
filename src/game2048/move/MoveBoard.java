package game2048.move;

import java.util.ArrayList;
import java.util.Collection;

public class MoveBoard {

    private final Collection<MoveTile> tileMoves = new ArrayList<>();
    private final Collection<AddTile> tileAdds = new ArrayList<>();

    public Collection<MoveTile> getTileMoves() {
        return this.tileMoves;
    }

    public Collection<AddTile> getTileAdds() {
        return this.tileAdds;
    }

}
