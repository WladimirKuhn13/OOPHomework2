public abstract class Actor implements ActorBehavoir{
    
    protected String name;
    protected boolean isMakeOrder; //Готов сделать заказ
    protected boolean isTakeOrder; // Уже сделал заказ

    public Actor(String name) {
        this.name = name;
    }

    public abstract String getName();
}
