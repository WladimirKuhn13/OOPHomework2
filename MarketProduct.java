public class MarketProduct {
    private String name;
    private int count;

    public MarketProduct(String name, int count) {
        this.name = name;
        this.count = count;

    }

    public String getName() {
        return name;
    }
    public int getCount() {
        return count;
    }


    @Override
    public String toString() {

        return name + " " + count;
    }
}
