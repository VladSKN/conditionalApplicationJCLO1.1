package ru.netology.conditionalapplicationjclo11;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    private GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);
    @Container
    private GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    @Test
    void prodAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodApp
                .getMappedPort(8081) + "/profile", String.class);
        String expected = "Current profile is production";
        assertEquals(expected, forEntity.getBody());
    }

    @Test
    void devAppTest() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devApp
                .getMappedPort(8080) + "/profile", String.class);
        String expected = "Current profile is dev";
        assertEquals(expected, forEntity.getBody());
    }
}