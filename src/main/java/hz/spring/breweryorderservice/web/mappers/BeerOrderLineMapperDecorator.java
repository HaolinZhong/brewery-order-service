package hz.spring.breweryorderservice.web.mappers;

import hz.spring.breweryorderservice.domain.BeerOrderLine;
import hz.spring.breweryorderservice.service.beer.BeerService;
import hz.spring.breweryorderservice.web.model.BeerDTO;
import hz.spring.breweryorderservice.web.model.BeerOrderLineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {

    private BeerService beerService;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

    @Override
    public BeerOrderLineDTO beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDTO orderLineDTO = beerOrderLineMapper.beerOrderLineToDto(line);
        Optional<BeerDTO> beerDTOOptional = beerService.getBeerByUpc(line.getUpc());

        beerDTOOptional.ifPresent(beerDTO -> {
            orderLineDTO.setBeerName(beerDTO.getBeerName());
            orderLineDTO.setBeerStyle(beerDTO.getBeerStyle());
            orderLineDTO.setPrice(beerDTO.getPrice());
            orderLineDTO.setBeerId(beerDTO.getId());
        });

        return orderLineDTO;
    }


}
