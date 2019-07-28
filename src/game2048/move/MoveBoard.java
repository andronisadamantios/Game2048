package game2048.move;

import matrix.Matrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class MoveBoard {

    private final Collection<MoveTile> tileMoves = new ArrayList<>();
    private final Collection<AddTile> tileAdds = new ArrayList<>();

    public void addTileAdd(Matrix.Coor coor, int value) {
        this.tileAdds.add(new AddTile(coor, value));
    }

    public void addTileMove(Matrix.Coor start, Matrix.Coor end, int value) {
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
