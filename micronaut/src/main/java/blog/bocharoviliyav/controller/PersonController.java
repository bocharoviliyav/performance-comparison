package blog.bocharoviliyav.controller;


import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.service.PersonService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

@Controller("/api/v1/person")
@ExecuteOn(TaskExecutors.IO)
public class PersonController {

  private final PersonService personService;

  PersonController(PersonService personService) {
    this.personService = personService;
  }

  @Get
  public String hello() {
    return "Hello from Micronaut!";
  }

  @Get("/{id}")
  public Person getPerson(@PathVariable Integer id) {
    return personService.findById(id);
  }

  @Post
  public Person createPerson(@Body Person person) {
    return personService.createOrUpdate(person);
  }
}
