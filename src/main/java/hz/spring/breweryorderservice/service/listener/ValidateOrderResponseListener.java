package hz.spring.breweryorderservice.service.listener;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.service.BeerOrderManager;
import hz.spring.common.event.ValidateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidateOrderResponseListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateBeerOrderResult result) {

        final UUID beerOrderId = result.getOrderId();

        log.debug("Validation result received for order: " + beerOrderId);

        beerOrderManager.processValidationResult(beerOrderId, result.getIsValid());
    }
}
