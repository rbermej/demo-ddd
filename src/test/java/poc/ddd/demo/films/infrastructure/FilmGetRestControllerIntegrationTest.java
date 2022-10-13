package poc.ddd.demo.films.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.shaded.com.github.dockerjava.core.MediaType;
import poc.ddd.demo.shared.infrastructure.BaseIntegrationTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FilmGetRestControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @Sql({"/data/createTables.sql", "/data/populateTables.sql"})
    public void getAllTests_success() throws Exception {
        var id = "2502f27f-bf5a-4989-94fe-1304a40dfffa";
        mockMvc.perform(
                        get("/films/" + id)
                                .accept(MediaType.APPLICATION_JSON.getMediaType()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("2502f27f-bf5a-4989-94fe-1304a40dfffa"))
                .andExpect(jsonPath("$.name").value("Lord of the Rings"))
                .andExpect(jsonPath("$.duration").value(152))
                .andExpect(jsonPath("$.score").value(9.25d));
    }

}