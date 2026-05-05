public class Road extends Cell implements Transferable{

    public Road(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isTransferable() {
        return true;
    }
}

