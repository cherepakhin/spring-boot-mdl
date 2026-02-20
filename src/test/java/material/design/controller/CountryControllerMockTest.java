package material.design.controller;


import material.design.model.Country;
import material.design.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CountryControllerMockTest {

    @Mock
    CountryRepository repository;

    CountryController countryController;

    @Before
    public void before() {
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

    // Проверять Model ТАК!
    @Test
    public void addCountriesToModelCheckTimes() {
        Model model = mock(Model.class);
        Country country = new Country();
        country.setId(1L);
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);

        countryController.show(model);

        verify(model, times(1)).addAttribute("countries", countries);
    }

    @Test
    public void addCountriesToModelCheckModel() {
        Model model = mock(Model.class);
        Country country = new Country();
        country.setId(1L);
        List<Country> countries = new ArrayList<>();
        countries.add(country);
        when(repository.findAll()).thenReturn(countries);

        countryController.show(model);

        verify(model, times(1)).addAttribute("countries", countries);
        verify(model, times(1)).addAttribute("edit", false);
        verify(model, times(1)).addAttribute("message", "Countries List");
    }
}