package blog.bocharoviliyav.controller;

import blog.bocharoviliyav.model.Person;
import blog.bocharoviliyav.service.PersonService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/v1/person")
@RequestScoped
public class PersonController {

    @Inject
    PersonService personService;

    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") final Integer id) {
        return personService.findById(id);
    }

    @POST
    public Person createPerson(Person person) {
        return personService.createOrUpdate(person);
    }

    @GET
    public String getHello() {
        return "Hello from Quarkus!";
    }
}
