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

        boolean hasError = false;
        boolean isPending = false;

        AllocateBeerOrderRequest request = (AllocateBeerOrderRequest) msg.getPayload();

        if (request.getBeerOrderDTO().getCustomerRef() != null &&
                request.getBeerOrderDTO().getCustomerRef().equals("failed-allocation")) {
            hasError = true;
        }

        if (request.getBeerOrderDTO().getCustomerRef() != null &&
                request.getBeerOrderDTO().getCustomerRef().equals("partial-allocation")) {
            isPending = true;
        }

        boolean isPendingFinal = isPending;

        request.getBeerOrderDTO().getBeerOrderLines().forEach(line -> {
            if (isPendingFinal) {
                line.setQuantityAllocated(line.getOrderQuantity() - 1);
            } else {
                line.setQuantityAllocated(line.getOrderQuantity());
            }
        });


        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                AllocateBeerOrderResult.builder()
                        .beerOrderDTO(request.getBeerOrderDTO())
                        .pendingInventory(isPending)
                        .allocationError(hasError)
                        .build());
    }
}
