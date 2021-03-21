package com.demo;

import com.demo.model.DataColumn;
import com.demo.model.DataFile;
import com.demo.model.DataRow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DataFileControllerIntegrationTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testDataFile() {
        assertEquals("SUCCESS", this.restTemplate.postForObject("http://localhost:" + port + "/data-file/testformat1_2015-06-28.txt", "", String.class));

        DataFile dataFile = this.restTemplate.getForObject("http://localhost:" + port + "/data-file/1", DataFile.class);
        assertEquals(1, dataFile.getId());
        assertEquals("testformat1_2015-06-28.txt", dataFile.getName());
        assertNotNull(dataFile.getImportedAt());
        List<DataColumn> columns = dataFile.getColumns();
        assertNotNull(columns);
        assertEquals(3, columns.size());
        assertNotNull(columns.get(0).getId());
        assertEquals("name", columns.get(0).getName());
        assertEquals(10, columns.get(0).getWidth());
        assertEquals("TEXT", columns.get(0).getType());

        List<DataRow> rows1 = columns.get(0).getRows();
        assertNotNull(rows1);
        assertEquals(3, rows1.size());
        assertEquals("Foonyor", rows1.get(0).getValueTxt());
        assertEquals("Barzane", rows1.get(1).getValueTxt());
        assertEquals("Quuxitude", rows1.get(2).getValueTxt());

        assertNotNull(columns.get(1).getId());
        assertEquals("valid", columns.get(1).getName());
        assertEquals(1, columns.get(1).getWidth());
        assertEquals("BOOLEAN", columns.get(1).getType());

        List<DataRow> rows2 = columns.get(1).getRows();
        assertNotNull(rows2);
        assertEquals(3, rows2.size());
        assertTrue(rows2.get(0).getValueBool());
        assertFalse(rows2.get(1).getValueBool());
        assertTrue(rows2.get(2).getValueBool());

        assertNotNull(columns.get(2).getId());
        assertEquals("count", columns.get(2).getName());
        assertEquals(3, columns.get(2).getWidth());
        assertEquals("INTEGER", columns.get(2).getType());

        List<DataRow> rows3 = columns.get(2).getRows();
        assertNotNull(rows3);
        assertEquals(3, rows3.size());
        assertEquals(1, rows3.get(0).getValueInt());
        assertEquals(-12, rows3.get(1).getValueInt());
        assertEquals(103, rows3.get(2).getValueInt());

    }
}

