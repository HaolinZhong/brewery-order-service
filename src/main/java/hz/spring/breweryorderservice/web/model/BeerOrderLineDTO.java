package hz.spring.breweryorderservice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDTO extends BaseItem {

    @Builder
    public BeerOrderLineDTO(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, String upc, String beerName, String beerStyle, UUID beerId, Integer orderQuantity, BigDecimal price) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    private String upc;
    private String beerName;
    private String beerStyle;
    private UUID beerId;
    private Integer orderQuantity = 0;
    private BigDecimal price;
}
