package blog.bocharoviliyav.repo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import blog.bocharoviliyav.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
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
