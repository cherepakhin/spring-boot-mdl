package material.design.controller;


import material.design.model.Country;
import material.design.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryControllerMockTest {

    @Mock
    CountryRepository repository;

    CountryController countryController;

    @Before
    public void before(){
        countryController = new CountryController(repository);
    }

    @Test
    public void showModelTest() {
        Model model = new ExtendedModelMap();

        countryController.show(model);

        assertTrue(model.containsAttribute("countries"));
    }

    @Test
    public void addCountriesToModel() {
        Model model = mock(Model.class);
        Country country = new Country();
        country.setId(1L);
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);

        countryController.show(model);

        verify(model).addAttribute("countries", countries);
    }
}