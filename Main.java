public class Main {
    public static void main(String[] args) {
        Actor ivanov = new Human("Иванов");
        Actor petrov = new Human("Петров");
        Actor andreev = new Human("Андреев");
        Actor fedorov = new Human("Федоров");
        Actor izmaylov = new Human("Измайлов");
        Actor krupin = new Human("Крупин");

        Market myMarket = new Market();

        myMarket.acceptToMarker(ivanov);
        myMarket.acceptToMarker(petrov);
        myMarket.acceptToMarker(andreev);
        myMarket.acceptToMarker(fedorov);
        myMarket.acceptToMarker(izmaylov);
        myMarket.acceptToMarker(krupin);

        myMarket.takeInQueue();

    }
}
