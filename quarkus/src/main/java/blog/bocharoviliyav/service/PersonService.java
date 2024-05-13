package blog.bocharoviliyav.service;

import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.repo.PersonRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@ApplicationScoped
public class PersonService {

  @Inject
  PersonRepo personRepo;

  public PersonService() {
  }

  public PersonService(PersonRepo personRepo) {
    this.personRepo = personRepo;
  }

  @Transactional(TxType.REQUIRED)
  public Person createOrUpdate(Person person) {
    return this.personRepo.createOrUpdate(person);
  }

  public Person findById(Integer id) {
    return this.personRepo.findById(id);
  }



}
