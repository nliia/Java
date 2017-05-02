package ru.kpfu.itis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.kpfu.itis.controller.MainController;
import ru.kpfu.itis.service.DepartmentService;
import ru.kpfu.itis.service.GroupService;
import ru.kpfu.itis.service.HighSchoolService;
import ru.kpfu.itis.service.SubjectService;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author Liia
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @Mock
    private DepartmentService departmentService;
    @Mock
    private GroupService groupService;
    @Mock
    private HighSchoolService highSchoolService;
    @Mock
    private SubjectService subjectService;
    @InjectMocks
    private MainController mainController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        when(highSchoolService.getAllCities()).thenReturn(new HashSet<String>());
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testGetPageController() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

}
