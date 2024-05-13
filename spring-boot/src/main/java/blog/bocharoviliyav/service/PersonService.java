package blog.bocharoviliyav.service;

import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepo personRepo;

  public PersonService(@Autowired PersonRepo personRepo) {
    this.personRepo = personRepo;
  }

  public Person createOrUpdate(Person person) {
    return this.personRepo.createOrUpdate(person);
  }

  public Person findById(Integer id) {
    return this.personRepo.findById(id);
  }

}
