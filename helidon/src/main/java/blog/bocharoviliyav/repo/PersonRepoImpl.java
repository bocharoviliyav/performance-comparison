package blog.bocharoviliyav.repo;

import blog.bocharoviliyav.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PersonRepoImpl implements PersonRepo {

  @PersistenceContext
  EntityManager entityManager;

  @Transactional
  public Person createOrUpdate(Person person) {
    if (person.getId() == null) {
      this.entityManager.persist(person);
      return person;
    } else {
      return this.entityManager.merge(person);
    }
  }

  public Person findById(Integer id) {
    return this.entityManager.find(Person.class, id);
  }

}
