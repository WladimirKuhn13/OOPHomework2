// Поведение актора

public interface ActorBehavoir {

    void setMakeOrder(boolean flag); // Установить готовность сделать заказ
    void setTakeOrder(boolean flag); // Заказ принят
    boolean isMakeOrder(); //Готов сделать заказ
    boolean isTakeOrder(); //Сделал ли он уже заказ
} 
    

