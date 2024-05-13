package blog.bocharoviliyav.service;


import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.repo.PersonRepo;
import jakarta.inject.Singleton;

@Singleton
public class PersonService {

  private final PersonRepo personRepo;

  PersonService(PersonRepo personRepo) {
    this.personRepo = personRepo;
  }

  public Person createOrUpdate(Person person) {
    return personRepo.createOrUpdate(person);
  }

  public Person findById(Integer id) {
    return personRepo.findById(id);
  }


}
