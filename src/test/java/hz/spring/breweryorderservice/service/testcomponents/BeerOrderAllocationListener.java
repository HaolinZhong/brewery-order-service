package hz.spring.breweryorderservice.service.testcomponents;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.common.event.AllocateBeerOrderRequest;
import hz.spring.common.event.AllocateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderAllocationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
    public void listen(Message msg) {
        AllocateBeerOrderRequest request = (AllocateBeerOrderRequest) msg.getPayload();

        request.getBeerOrderDTO().getBeerOrderLines().forEach(line -> {
            line.setQuantityAllocated(line.getOrderQuantity());
        });

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                AllocateBeerOrderResult.builder()
                .beerOrderDTO(request.getBeerOrderDTO())
                .pendingInventory(false)
                .allocationError(false)
                .build());
    }
}
