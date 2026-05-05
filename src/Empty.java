public class Empty extends Cell implements Transferable{

    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isTransferable() {
        return false;
    }
}
