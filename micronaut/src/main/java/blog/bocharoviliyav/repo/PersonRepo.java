package blog.bocharoviliyav.repo;

import blog.bocharoviliyav.model.Person;

public interface PersonRepo {

  Person createOrUpdate(Person person);

  Person findById(Integer id);

}
