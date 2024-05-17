package blog.bocharoviliyav.repo;

import blog.bocharoviliyav.model.Person;
import jakarta.inject.Singleton;
import io.micronaut.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Singleton
public class PersonRepoImpl implements PersonRepo {

  private final EntityManager entityManager;

  public PersonRepoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public Person createOrUpdate(Person person) {
    entityManager.merge(person);
    return person;
  }

  @Override
  @Transactional(readOnly = true)
  public Person findById(Integer id) {
    return entityManager.find(Person.class, id);
  }

}
