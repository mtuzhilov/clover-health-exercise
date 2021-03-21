package com.demo;

import com.demo.model.DataFile;
import com.demo.service.DataFileService;
import com.demo.service.DataRowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class DataFileControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DataFileService dataFileService;

    @MockBean
    DataRowService dataRowService;

    @Test
    public void testGetAllFiles() throws Exception {
        DataFile file = new DataFile();
        file.setId(1);

        List<DataFile> dataFiles = new ArrayList<>();
        dataFiles.add(file);

        BDDMockito.given(dataFileService.findAll()).willReturn(dataFiles);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/data-files")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }


}
