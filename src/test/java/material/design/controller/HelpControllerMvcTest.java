package material.design.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelpControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHelpOk() throws Exception {
        this.mockMvc.perform(get("/help")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getHelpContent() throws Exception {
        this.mockMvc.perform(get("/help")).andDo(print())
                .andExpect(view().name("help"));
    }
}
