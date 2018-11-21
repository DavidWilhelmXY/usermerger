package de.welt.dev.usermerger;

import de.welt.dev.usermerger.data.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void userHasComments() throws Exception {
        User user = this.restTemplate.getForObject("http://localhost:" + port + "/users/1", User.class);
        assert user != null;
        assert user.getId() != null;
        assert user.getComments() != null;
        assert user.getComments().length > 0;
    }

}