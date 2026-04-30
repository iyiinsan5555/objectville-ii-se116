public abstract class Resource {
    private int amount;

    //Setters
    public void setAmount(int amount) {this.amount = amount;}

    //Getters
    public int getAmount() {return this.amount;}

    //Abstract methods
    public abstract String getType();
}
