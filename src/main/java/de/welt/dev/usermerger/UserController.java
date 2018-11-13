package de.welt.dev.usermerger;

import de.welt.dev.usermerger.data.Comment;
import de.welt.dev.usermerger.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class UserController {

    @Autowired
    private UserLookupService userLookupService;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User user(@PathVariable("id") long id) {
        return buildUser(id);
    }

    private User buildUser(long id) {
        try {
            CompletableFuture<User> userFuture = userLookupService.findUser(id);
            CompletableFuture<Comment[]> commentsFuture = userLookupService.findComments(id);
            User user = userFuture.get();
            user.setComments(commentsFuture.get());
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
