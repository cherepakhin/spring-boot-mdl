package material.design.controller;

import material.design.model.Country;
import material.design.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CountryRepository repository;

    @WithMockUser(value = "user")
    @Test
    public void getCountryOk() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setId(1L);
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);
        this.mockMvc.perform(get("/country")).andDo(print()).andExpect(status().isOk());
    }

    @WithMockUser(value = "user")
    @Test
    public void getCountryContent() throws Exception {
        this.mockMvc.perform(get("/country")).andDo(print())
                .andExpect(view().name("countries"));
    }
}
