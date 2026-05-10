public class Empty extends Cell{

    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isTransferable() {
        return false;
    }
}
