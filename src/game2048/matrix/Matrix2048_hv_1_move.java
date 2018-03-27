package game2048.matrix;

import game2048.Direction;
import game2048.MoveBoard;
import game2048.MoveTile;

/*
this class extracts a whole row or column
calculates its next after a move and replaces it as a whole
η κινηση γινεται με ενα αλγοριθμο για ολα τα direction
ypologizontai h kinhsh pou prepei na kanei to kathe tile
 */
public class Matrix2048_hv_1_move extends Matrix2048_hv_1 {

    public Matrix2048_hv_1_move(int rows, int cols) {
        super(rows, cols);
    }

    private MoveBoard mb;

    @Override
    public MoveBoard getLastMove() {
        return this.mb;
    }

    @Override
    public boolean move(Direction direction) {
        this.mb = new MoveBoard(direction.getValue() > 0);
        return super.move(direction);
    }

    @Override
    protected boolean moveRowCol(int p, RowColumnOperator rco, boolean move) {
        boolean result = false;
        final int start = (rco.getLength() - 1) * (1 - p) / 2;
        final int bound = ((rco.getLength() - 1) * p + rco.getLength() + 1) / 2;
        int[] newArray = new int[rco.getLength()];
        int destIndex = start;

        for (int srcIndex = start; bound - p * srcIndex > 0; srcIndex += p) {
            int n = rco.get(srcIndex);
            if (n == 0) {
                continue;
            }
            if (newArray[destIndex] == n) {
                result = true;
                if (!move) {
                    return true;
                }
                newArray[destIndex]++;
                if (move) {
                    this.mb.tileMoves.add(new MoveTile(rco.getCoor(srcIndex), rco.getCoor(destIndex), mapInternalToRepresented(newArray[destIndex])));
                }
                destIndex += p;
            } else {
                if (newArray[destIndex] != 0) {
                    destIndex += p;
                }
                newArray[destIndex] = n;
                if (srcIndex != destIndex) {
                    if (move) {
                        this.mb.tileMoves.add(new MoveTile(rco.getCoor(srcIndex), rco.getCoor(destIndex),mapInternalToRepresented(newArray[destIndex])));
                        result = true;
                    } else {
                        return true;
                    }
                }
            }
        }
        if (move) {
            for (int i = 0; i < rco.getLength(); i++) {
                rco.set(i, newArray[i]);
            }
        }
        return result;
    }

}
