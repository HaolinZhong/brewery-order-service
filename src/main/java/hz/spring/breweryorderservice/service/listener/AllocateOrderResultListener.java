package hz.spring.breweryorderservice.service.listener;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.service.BeerOrderManager;
import hz.spring.common.event.AllocateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateOrderResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE)
    public void listen(AllocateBeerOrderResult result) {
        if (!result.getAllocationError() && !result.getPendingInventory()) {
            // allocation success
            beerOrderManager.beerOrderAllocationPassed(result.getBeerOrderDTO());
        } else if (!result.getAllocationError() && result.getPendingInventory()) {
            // pending inventory
            beerOrderManager.beerOrderAllocationPendingInventory(result.getBeerOrderDTO());
        } else if (result.getAllocationError()) {
            // allocation error
            beerOrderManager.beerOrderAllocationFailed(result.getBeerOrderDTO());
        }
    }
}
