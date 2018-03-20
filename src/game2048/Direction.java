package game2048;

public enum Direction {

    left, up, right, down;

    public static enum Orientation {
        Horizontal, Vertical
    }
    
    public Orientation getOrientation() {
        switch (this) {
            case left:
                return Orientation.Horizontal;
            case up:
                return Orientation.Vertical;
            case right:
                return Orientation.Horizontal;
            case down:
                return Orientation.Vertical;
            default:
                throw new AssertionError();
        }
    }
    
    public int getH() {
        switch (this) {
            case left:
                return 1;
            case up:
                return 0;
            case right:
                return -1;
            case down:
                return 0;
            default:
                throw new AssertionError();
        }

    }

    public int getV() {
        switch (this) {
            case left:
                return 0;
            case up:
                return 1;
            case right:
                return 0;
            case down:
                return -1;
            default:
                throw new AssertionError();
        }
    }
    
    public int getAbsH(){
        return Math.abs(this.getH());
    }
    public int getAbsV(){
        return Math.abs(this.getV());
    }
    
    public boolean  isPositive(){
        return this.getValue() > 0;
    }

    public int getValue() {
        return this.getH() + this.getV();
    }


}
