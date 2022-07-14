package hz.spring.common.event;

import hz.spring.common.model.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateBeerOrderRequest implements Serializable {

    static final long serialVersionUID = 6450686537486433530L;

    private BeerOrderDTO beerOrderDTO;
}
