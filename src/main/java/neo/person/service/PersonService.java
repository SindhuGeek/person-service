package neo.person.service;

import lombok.AllArgsConstructor;
import neo.person.dto.request.PersonDTO;
import neo.person.dto.response.personResponseDTO;
import neo.person.entity.Person;
import neo.person.exception.PersonNotFoundException;
import neo.person.mapper.PersonMapper;
import neo.person.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    public final PersonMapper personMapper = PersonMapper.INSTANCE;

    public personResponseDTO createPerson(PersonDTO personDTO) {
            return this.save(personDTO, "Person created with ID ");
    }

    public personResponseDTO updatePerson(PersonDTO personDTO, Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        personDTO.setId(id);
        return this.save(personDTO, "Person updated with ID ");
    }

    public List<PersonDTO> listAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toDTO) //.map(person -> personMapper.toDTO(person))
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        
        Person person = this.verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        this.verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private personResponseDTO save(PersonDTO personDTO, String message) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return personResponseDTO
                .builder()
                .message(message + savedPerson.getId())
                .build();
    }

}
