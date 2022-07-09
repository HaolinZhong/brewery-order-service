package hz.spring.breweryorderservice.web.mappers;

import hz.spring.breweryorderservice.domain.BeerOrderLine;
import hz.spring.breweryorderservice.web.model.BeerOrderLineDTO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDTO beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDTO dto);
}
