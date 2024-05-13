package blog.bocharoviliyav.repo;

import blog.bocharoviliyav.model.Person;

public interface PersonRepo {

  public Person createOrUpdate(Person person);

  public Person findById(Integer id);

}
