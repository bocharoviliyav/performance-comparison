package blog.bocharoviliyav.controller;

import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Integer id) {
    return personService.findById(id);
  }

  @PostMapping
  public Person createPerson(Person person) {
    return personService.createOrUpdate(person);
  }

  @GetMapping
  public String hello() {
    return "Hello from Spring Boot!";
  }

}
