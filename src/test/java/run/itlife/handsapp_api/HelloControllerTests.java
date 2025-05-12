package run.itlife.handsapp_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import run.itlife.handsapp_api.config.RestConfig;
import run.itlife.handsapp_api.controller.HelloController;
import run.itlife.handsapp_api.controller.TokenController;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({HelloController.class, TokenController.class})
@Import(RestConfig.class)
public class HelloControllerTests {
    @Autowired
    MockMvc mvc;

    @Test
    void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        MvcResult result = this.mvc.perform(post("/token")
                        .with(httpBasic("shurrik77", "111")))
                .andExpect(status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mvc.perform(get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().string("Hello, shurrik77!"));

        this.mvc.perform(get("/post/1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(content().json("{\n" +
                        "    \"postId\": 1,\n" +
                        "    \"photo\": \"48bbdedb-8b0f-471f-afd4-5c6061ecdcda.png\",\n" +
                        "    \"extFile\": \"png\",\n" +
                        "    \"content\": \"fghdfgdfg\",\n" +
                        "    \"username\": \"shurrik771111\",\n" +
                        "    \"createdAt\": \"2024-05-13 23:11:58\"\n" +
                        "}"));
    }

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tokenWhenBadCredentialsThen401() throws Exception {
        this.mvc.perform(post("/token"))
                .andExpect(status().isUnauthorized());
    }
}