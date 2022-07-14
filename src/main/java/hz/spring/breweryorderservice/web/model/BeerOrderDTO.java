package hz.spring.breweryorderservice.web.model;

import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderDTO extends BaseItem {

    @Builder
    public BeerOrderDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, UUID customerId, String customerRef, List<BeerOrderLineDTO> beerOrderLines, BeerOrderStatusEnum orderStatus, String orderStatusCallbackUrl) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.customerRef = customerRef;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
    }

    private UUID customerId;
    private String customerRef;
    private List<BeerOrderLineDTO> beerOrderLines;
    private BeerOrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;
}
