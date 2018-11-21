package de.welt.dev.usermerger;

import de.welt.dev.usermerger.data.Comment;
import de.welt.dev.usermerger.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    @Autowired
    private UserLookupService userLookupService;
    private long now;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Mono<User> user(@PathVariable("id") long id) {
        Mono<User> userMono = buildUser(id);
        System.out.println("done in " + (System.nanoTime() - now));
        return userMono;
    }

    private Mono<User> buildUser(long id) {
        try {
            now = System.nanoTime();
            Mono<Comment[]> commentsMono = userLookupService.findComments(id);
            System.out.println("findComments took " + (System.nanoTime() - now));
            Mono<User> userMono = userLookupService.findUser(id);
            System.out.println("findComments and findUser took " + (System.nanoTime() - now));
            return userMono.zipWith(commentsMono)
                    .map(tuple -> {
                        User user = tuple.getT1();
                        Comment[] comments = tuple.getT2();
                        user.setComments(comments);
                        return user;
                    });

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
