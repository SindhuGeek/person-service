package neo.person.dto.request;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	private int id;

    @Size( max = 20)
    private String street;
    

    @Size( max = 20)
    private String city;
    

    @Size( max = 20)
    private String state;
    

    @Size( max = 8)
    private String postalCode;
}
