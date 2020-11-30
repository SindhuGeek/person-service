package neo.person.utils;



import neo.person.dto.request.PersonDTO;
import neo.person.entity.Person;

public class PersonUtils {
    private static final String FIRST_NAME = "Sindhu";
    private static final String LAST_NAME = "Gummalla";


    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                 .build();
    }

}
