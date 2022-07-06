package hz.spring.breweryorderservice.web.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends BaseItem {
    private String name;
}
