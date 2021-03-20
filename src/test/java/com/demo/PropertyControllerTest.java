package com.demo;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class PropertyControllerTest {

    /*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FileService propertyService;

    @Test
    public void testGetDepartment() throws Exception {
        Property employee = new Property();
        employee.setId(1);

        List<Property> employeeList = new ArrayList<>();
        employeeList.add(employee);

        given(propertyService.findAll()).willReturn(employeeList);

        this.mockMvc.perform(get("/properties")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }
     */

}
