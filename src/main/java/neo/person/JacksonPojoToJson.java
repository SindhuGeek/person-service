package neo.person;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import neo.person.dto.request.AddressDTO;
import neo.person.dto.request.PersonDTO;

public class JacksonPojoToJson {
   public static void main(String[] args) throws Exception {

      // Create ObjectMapper
      ObjectMapper mapper = new ObjectMapper();
      mapper.enable(SerializationFeature.INDENT_OUTPUT);

      PersonDTO personDTO = new PersonDTO();
      personDTO.setFirstName("John Doe");
      personDTO.setLastName("Koppula");
      AddressDTO addressDTO = new AddressDTO();
      addressDTO.setStreet("Carrigmore Terrace");
      addressDTO.setCity("Dublin");
      addressDTO.setState("Dublin");
      addressDTO.setPostalCode("D24HD89");
      
      List<AddressDTO> addressList = new ArrayList<>();
      addressList.add(addressDTO);
      personDTO.setAddressList(addressList);
        // Convert object to JSON string
      String json = mapper.writeValueAsString(personDTO);
      System.out.println(json);

      // Save JSON string to file
      FileOutputStream fileOutputStream = new FileOutputStream("employee.json");
      mapper.writeValue(fileOutputStream, addressDTO);
      fileOutputStream.close();
   }
}