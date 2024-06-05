//Конкретный покупатель
public class Human extends Actor {

    public Human(String name) {
        super(name);
    }

    public void setMakeOrder(boolean flag) {
        this.isMakeOrder = flag;
    }

    public void setTakeOrder(boolean flag) {
        this.isTakeOrder = flag;
    }

    public boolean isMakeOrder() {
        return isMakeOrder;
    }

    public boolean isTakeOrder() {
        return isTakeOrder;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        return name;
    }
}
