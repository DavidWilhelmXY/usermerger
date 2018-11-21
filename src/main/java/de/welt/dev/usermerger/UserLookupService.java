package de.welt.dev.usermerger;

import de.welt.dev.usermerger.data.Comment;
import de.welt.dev.usermerger.data.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserLookupService {

    private final WebClient webClient;

    public UserLookupService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    Mono<User> findUser(long id) {
        return webClient.get().uri("/users/" + id).retrieve().bodyToMono(User.class);
    }

    Mono<Comment[]> findComments(long userId) {
        return webClient.get().uri("/posts?userId=" + userId).retrieve().bodyToMono(Comment[].class);
    }

}
