package hz.spring.breweryorderservice.service;

import hz.spring.breweryorderservice.domain.BeerOrder;

public interface BeerOrderManager {
    BeerOrder newBeerOrder(BeerOrder beerOrder);
}
