package blog.bocharoviliyav;

import blog.bocharoviliyav.controller.PersonController;

import blog.bocharoviliyav.repo.PersonRepo;
import blog.bocharoviliyav.service.PersonService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Application;

import java.util.Set;


@ApplicationScoped
public class PersonApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(PersonController.class, PersonService.class, PersonRepo.class);
    }
}
