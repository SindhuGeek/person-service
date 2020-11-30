package neo.person.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import neo.person.dto.request.PersonDTO;
import neo.person.dto.response.PersonResponseDTO;
import neo.person.exception.PersonNotFoundException;
import neo.person.service.PersonService;

 
/**
 * The Class PersonController.
 */
@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    
    /** The person service. */
    private PersonService personService;

    
    /**
     * Creates the person.
     *
     * @param personDTO the person DTO
     * @return the message response DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
    	   return personService.createPerson(personDTO);
    }

    
    /**
     * Update person.
     *
     * @param personDTO the person DTO
     * @param id the id
     * @return the message response DTO
     * @throws PersonNotFoundException the person not found exception
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonResponseDTO updatePerson(@RequestBody @Valid PersonDTO personDTO, @PathVariable Long id)
            throws PersonNotFoundException {
        return personService.updatePerson(personDTO, id);
    }

    
    /**
     * List all.
     *
     * @return the list
     */
    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }
    
    /**
     * List all.
     *
     * @return the list
     */
    @GetMapping("/personCount")
    public int personCount() {
        return personService.listAll().size();
    }

    
    /**
     * Find by id.
     *
     * @param id the id
     * @return the person DTO
     * @throws PersonNotFoundException the person not found exception
     */
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    
    /**
     * Delete by id.
     *
     * @param id the id
     * @throws PersonNotFoundException the person not found exception
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
