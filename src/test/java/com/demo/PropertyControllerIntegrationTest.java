package com.demo;

import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PropertyControllerIntegrationTest {

    /*
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testEmployee()
    {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/property/1", Property.class)
                        .getId().equals(1));
    }

    @Test
    public void testAllEmployee() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/properties", List.class).size() == 2);
    }

     */
}

