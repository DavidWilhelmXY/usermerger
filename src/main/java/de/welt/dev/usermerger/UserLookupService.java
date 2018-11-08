package de.welt.dev.usermerger;

import de.welt.dev.usermerger.data.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

public class UserLookupService {

    private final RestTemplate restTemplate;

    public UserLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    CompletableFuture<User> findUser(long id) throws InterruptedException {
        String url = String.format("https://jsonplaceholder.typicode.com/users/%d", id);
        User results = restTemplate.getForObject(url, User.class);
        return CompletableFuture.completedFuture(results);
    }

}
