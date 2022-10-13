package poc.ddd.demo.shared.infrastructure;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import poc.ddd.demo.DemoApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = DemoApplication.class,
        //webEnvironment = SpringBootTest.WebEnvironment.MOCK
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@ActiveProfiles("it")
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @Container
    public static MySQLContainer<?> mySqlDB = new MySQLContainer<>
            ("mysql:8.0")
            .withDatabaseName("my-app-test")
            .withUsername("admin")
            .withPassword("admin");

    @Container
    public static RabbitMQContainer rabbitmq = new RabbitMQContainer(DockerImageName.parse("rabbitmq:3-management"));
    /*@DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlDB::getUsername);
        registry.add("spring.datasource.password", mySqlDB::getPassword);

    }*/

}