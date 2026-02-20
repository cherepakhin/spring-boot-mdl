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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
По умолчанию в контексте безопасности Spring Security используется пользователь с именем «user»,
паролем «password» и ролью «USER».

Аннотацию @WithMockUser(value = "user") можно применить ко всему тестовому классу, чтобы каждый
тест использовал указанного пользователя.

 */
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
    public void getCountryWithUser() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setId(100L);
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);
        this.mockMvc.perform(get("/country")).andDo(print())
                .andExpect(view().name("countries"));
    }

    @WithMockUser(value = "user")
    @Test
    public void getCountryContentWithUser() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setId(100L);
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);
        ResultActions result = this.mockMvc.perform(get("/country"));
        MvcResult mvcResult = result.andReturn();
        Object countriesResult = mvcResult.getModelAndView().getModel().get("countries");

        if (countriesResult instanceof List) {
            if (!((List) countriesResult).isEmpty()) {
                List<Country> countriesList = (List<Country>) countriesResult;
                assertEquals(1, countriesList.size());

                Country countryResult = countriesList.get(0);
                assertEquals(100, countryResult.getId().longValue());
            } else {
                fail();
            }
        }
    }

    @Test
    @WithMockUser(value = "user")
    public void getCountryContentWithUserFromGigaCode() throws Exception {
        List<Country> countries = new ArrayList<>();
        Country country = new Country();
        country.setId(100L);
        countries.add(country);

        when(repository.findAll()).thenReturn(countries);

        this.mockMvc.perform(get("/country"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("countries"))
                .andExpect(model().attributeExists("countries"))
                .andExpect(model().attribute("countries", hasSize(1)))
                .andExpect(model().attribute("countries", hasItem(
                        allOf(
                                hasProperty("id", is(100L))
                        )
                )));
    }
}
