package neo.person.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import neo.person.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
