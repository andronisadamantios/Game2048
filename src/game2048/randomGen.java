package game2048;

@FunctionalInterface
public interface randomGen {

    public abstract int getNewNumber();
}

class randomGen1 implements randomGen {

    @Override
    public int getNewNumber() {
        return (int) (10 * Math.random() + 1);
    }

}
