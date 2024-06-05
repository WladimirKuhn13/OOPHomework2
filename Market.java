import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Market implements MarketBehavoir, QueueBehavoir {
    private List<Actor> actors = new ArrayList<>(); // Список посетителей магазина
    private List<Actor> queueToOrder = new ArrayList<>(); //Очередь из тех, кто готов совершить заказ
    private List<Actor> queueForDelivery = new ArrayList<>(); // Очередь из тех, чей заказ уже готов к получению
    private List<MarketProduct> productsOnMarket = new ArrayList(); // Список продуктов на рынке

    public void acceptToMarker(Actor actor) { // Входит в магазин
        System.out.println("Здравствуйте, " + actor.getName() + "!\n");
        actors.add(actor);
    }

    public void takeInQueue() { // Формируем очередь на создание и выдачу заказа
        if (actors.size() > 0 && productsOnMarket.size() == 0) {
            System.out.println(1);
            for (int i = 0; i < actors.size(); i++) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(actors.get(i).getName() + "," +" готовы совершить заказ?\nДа - 1\nНет - 0");
                int readyToOrder = scanner.nextInt();

                if (readyToOrder == 1) {
                    actors.get(i).setMakeOrder(true);
                }
                else if (readyToOrder == 0) {
                    actors.get(i).setMakeOrder(false);
                }
                else {
                    System.out.println("Ошибка!");
                }

                if (actors.get(i).isMakeOrder() == true && actors.get(i).isTakeOrder() != true) {
                    queueToOrder.add(actors.get(i));
                    System.out.println(actors.get(i).getName() + ", " + "Ваш номер в очереди для совершения заказа: " + ((Integer)queueToOrder.indexOf(actors.get(i)) + 1));
                    System.out.println();
                    if (i == actors.size() - 1) {
                        takeOrders(queueToOrder);
                    }
                }

            }

        }
        if (productsOnMarket.size() > 0) {
            for (int i = 0; i < actors.size(); i ++) {
                if (actors.get(i).isMakeOrder() == true && actors.get(i).isTakeOrder() == true) {
                    queueForDelivery.add(actors.get(i));
                    System.out.println(actors.get(i).getName() + ", " + "Ваш номер в очереди на выдачу заказа: " + ((Integer)queueForDelivery.indexOf(actors.get(i)) + 1));
                    System.out.println();
                    if (i == actors.size() - 1) {
                        giveOrders(queueForDelivery);
                    }
                }
            }
        }
    }

    public void takeOrders(List<Actor> queueActorsToOrder) { // Совершает заказ
        String marker = "add";
        while (queueActorsToOrder.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Заказывайте, " + queueActorsToOrder.get(0).getName() + " :\nУкажите наименование товара");
            String nameOfProduct = scanner.nextLine();
            System.out.println("Укажите количество товара в шт. :");
            int countOfProduct = scanner.nextInt();
            
            if (nameOfProduct.isEmpty() == false && countOfProduct > 0) {
                update(nameOfProduct, countOfProduct, marker);
                queueActorsToOrder.get(0).setTakeOrder(true);
                releaseFromQueue(queueActorsToOrder.get(0));
            }
            else {
                queueActorsToOrder.get(0).setTakeOrder(false);
            }
        }
        if (queueActorsToOrder.size() == 1) {
            takeInQueue();
        }
    }

    public void giveOrders(List<Actor> queueActorsForDelivery) { // Получает заказ
        String marker = "remove";
        int indexForTheExit = 0;
        while (queueActorsForDelivery.size() > 0) {
            System.out.println(queueActorsForDelivery.get(0).getName() + ", Ваш заказ " + productsOnMarket.get(0).getName()
            +" " + productsOnMarket.get(0).getCount() + " шт\n");
            update(productsOnMarket.get(0).getName(), productsOnMarket.get(0).getCount(), marker);
            if (actors.contains(queueActorsForDelivery.get(0))) {
                
                indexForTheExit = actors.indexOf(queueActorsForDelivery.get(0));
            }
            releaseFromQueue(queueActorsForDelivery.get(0));
            releaseFromMarket(actors.get(indexForTheExit));
        }

        while (actors.size() > 0) {
            releaseFromMarket(actors.get(0));
        }

    }

    public void releaseFromQueue(Actor actor) { //Выходит из очереди
        if (queueToOrder.contains(actor) == true && queueForDelivery.contains(actor) == false) {
            System.out.println("Ваш заказ в работе, " + actor.getName() + "!\n");
            queueToOrder.remove(actor);
        }
        if (queueForDelivery.contains(actor) == true) {
            System.out.println("Спасибо, что выбрали нас, " + actor.getName() + "!\n");
            queueForDelivery.remove(actor);
        }
    }

    public void releaseFromMarket(Actor actor) { //Выходит из магазина
        if (actor.isMakeOrder() == true && actor.isTakeOrder()) {
            System.out.println("До свидания, ждем Вас снова, " + actor.getName() + "!\n");
            actors.remove(actors.indexOf(actor));
        }
        else if (actor.isMakeOrder() == false || actor.isTakeOrder() == false) {
            System.out.println("Жаль, что вы ничего не приобрели, " + actor.getName() + ":(\n");
            actors.remove(actors.indexOf(actor));
        }

        
    }

    public void update(String nameOfProduct, int countOfProduct, String  marker) { //Обновление списка товаров в магазине
        if (marker == "add") {
            productsOnMarket.add(new MarketProduct(nameOfProduct, countOfProduct));
            System.out.println("Системное уведомление: Заказ создан!");
        }
        if (marker == "remove") {
            productsOnMarket.remove(0);
            System.out.println("Системное уведомление: Заказ вручен!\n");
        }
    }

}
