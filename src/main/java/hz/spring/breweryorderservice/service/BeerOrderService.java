package hz.spring.breweryorderservice.service;

import hz.spring.common.model.BeerOrderDTO;
import hz.spring.common.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDTO placeOrder(UUID customerId, BeerOrderDTO beerOrderDTO);

    BeerOrderDTO getOrderById(UUID customerId, UUID orderId);

    void pickupOrder(UUID customerId, UUID orderId);
}
