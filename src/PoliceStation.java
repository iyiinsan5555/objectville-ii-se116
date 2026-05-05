public class PoliceStation extends ServiceProvider implements Transferable {
    private static final int radius = 5; //fixed size which is designated before

    //Parameterized constructor
    public PoliceStation(int x, int y) {
        super(x, y, radius);
    }


    @Override
    public void distribute() {
        //will get help from ServiceDistributor ex. func. call ServiceDistributor.distribute(this, radius) (will be discussed later)
    }

    @Override
    public boolean isTransferable() {
        return true; //By default, it is transferable
    }
}
