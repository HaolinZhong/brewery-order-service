package hz.spring.breweryorderservice.statemachine.actions;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;
import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import hz.spring.breweryorderservice.service.BeerOrderManagerImpl;
import hz.spring.common.event.AllocationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {

        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_FAILURE_QUEUE,
                AllocationFailureEvent.builder()
                        .orderId(UUID.fromString(beerOrderId))
                        .build());

        log.debug("Sent Allocation Failure Message to queue for order id " + beerOrderId);
    }
}
