package hz.spring.breweryorderservice.service;

import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;

public interface BeerOrderManager {
    BeerOrder newBeerOrder(BeerOrder beerOrder);
    void sendBeerOrderEvent(BeerOrder beerOrder, BeerOrderEventEnum eventEnum);
}
