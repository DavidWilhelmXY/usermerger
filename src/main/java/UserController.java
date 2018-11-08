import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User user(@PathVariable("id") long id) {
        return buildUser(id); //TODO
    }

    private User buildUser(long id) {
        return null; //TODO
    }

}
