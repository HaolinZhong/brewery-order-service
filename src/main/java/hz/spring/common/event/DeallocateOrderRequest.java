package hz.spring.common.event;

import hz.spring.common.model.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeallocateOrderRequest {
    private BeerOrderDTO beerOrderDTO;
}
