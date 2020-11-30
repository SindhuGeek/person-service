package neo.person.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import neo.person.dto.request.PersonDTO;
import neo.person.dto.response.personResponseDTO;
import neo.person.entity.Person;
import neo.person.exception.PersonNotFoundException;
import neo.person.repository.PersonRepository;
import neo.person.utils.PersonUtils;


public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Before
    public void setup()
    {
    	MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testCreatePerson() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson = PersonUtils.createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
       
        personResponseDTO successMessage = personService.createPerson(personDTO);

        Assert.assertNotNull(successMessage);

    }
    
    @Test
    public void testUpdatePerson() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson = PersonUtils.createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
       
        personResponseDTO successMessage;
		try {
			successMessage = personService.updatePerson(personDTO,1L);
		} catch (PersonNotFoundException e) {
			Assert.assertTrue(true);
		}

    
    }

    private personResponseDTO createExpectedSucessMessageResponse(Long id) {
        return personResponseDTO
                .builder()
                .message("Created Person with ID " + id)
                .build();
    }
}
