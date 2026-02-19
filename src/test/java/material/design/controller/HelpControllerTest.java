package material.design.controller;

import org.junit.Test;
import org.springframework.ui.Model;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class HelpControllerTest {

    @Test
    public void help() {
        HelpController helpController = new HelpController();
        Model model = mock(Model.class);

        assertEquals("help", helpController.help(model));
    }
}