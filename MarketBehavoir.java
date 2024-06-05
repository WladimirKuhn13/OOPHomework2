
//Поведение магазина

public interface MarketBehavoir {
    void acceptToMarker(Actor actor); // входит в магазин
    void releaseFromMarket(Actor actor);// выходит из магазина
    void update(String nameOfProduct, int countOfProduct, String  marker); //обновление
}
