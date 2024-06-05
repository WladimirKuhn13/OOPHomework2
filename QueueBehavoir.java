// Поведение очереди

import java.util.List;

public interface QueueBehavoir {

    void takeInQueue(); // Совершает действие - встает в очередь
    void takeOrders(List<Actor> queueActorsToOrder); //Сделать заказ
    void giveOrders(List<Actor> queueActorsForDelivery); // Забрать заказы
    void releaseFromQueue(Actor actor); //Покинуть очередь
    
}
