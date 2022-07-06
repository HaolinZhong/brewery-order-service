package hz.spring.breweryorderservice.web.mappers;

import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.web.model.BeerOrderDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDTO BeerOrderToDTO(BeerOrder beerOrder);
    BeerOrder DTOToBeerOrder(BeerOrderDTO beerOrderDTO);
}
