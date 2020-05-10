package game2048.move;

import grid.Grid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import grid.IGridCoordinates;

public class MoveBoard {

    private final Collection<MoveTile> tileMoves = new ArrayList<>();
    private final Collection<AddTile> tileAdds = new ArrayList<>();

    public void addTileAdd(IGridCoordinates coor, int value) {
        this.tileAdds.add(new AddTile(coor, value));
    }

    public void addTileMove(IGridCoordinates start, IGridCoordinates end, int value) {
        this.tileMoves.add(new MoveTile(start, end, value));
    }

    public void clearTileAds() {
        this.tileAdds.clear();
    }

    public Stream<AddTile> StreamAddTiles() {
        return this.tileAdds.stream();
    }

    public Stream<MoveTile> StreamTileMoves() {
        return this.tileMoves.stream();
    }
}
