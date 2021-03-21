package com.demo;

import com.demo.model.DataColumn;
import com.demo.model.DataFile;
import com.demo.model.DataRow;
import com.demo.service.DataFileService;
import com.demo.service.DataRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@RestController
public class DataFileController {

    @Autowired
    private DataFileService fileService;

    @Autowired
    private DataRowService dataRowService;

    @RequestMapping(value = "/data-file/{name}", method = RequestMethod.POST)
    String processFile(@PathVariable String name) {
        DataFile file = new DataFile();
        file.setName(name);
        file.setImportedAt(new Date());
        file.setColumns(getColumns(file));
        file = fileService.save(file);
        processRows(file);
        return "SUCCESS";
    }

    @RequestMapping(value = "/data-file/{id}", method = RequestMethod.GET)
    DataFile getFile(@PathVariable Integer id){
        return  fileService.findById(id).get();
    }

    @RequestMapping(value = "/data-file", method = RequestMethod.DELETE)
    Map<String, String> deleteFile(@RequestParam Integer id){
        Map<String, String> status = new HashMap<>();
        Optional<DataFile> property = fileService.findById(id);
        if(property.isPresent()) {
            fileService.delete(property.get());
            status.put("Status", "File deleted successfully");
        }
        else {
            status.put("Status", "File not exist");
        }
        return status;
    }


    @RequestMapping(value = "/data-files", method = RequestMethod.GET)
    List<DataFile> getAllFiles() {
        return fileService.findAll();
    }

    @RequestMapping(value = "/data-files", method = RequestMethod.DELETE)
    String deleteAllFiles() {
        fileService.deleteAll();
        return "SUCCESS";
    }

    private void processRows(DataFile file) {
        try (Scanner scanner = new Scanner(this.getClass().getResourceAsStream("/data/" + file.getName()), StandardCharsets.UTF_8.name())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                int start = 0;
                for (DataColumn column : file.getColumns()) {
                    int end = start + column.getWidth();
                    String value = line.substring(start, end);
                    if (value.isEmpty()) {
                        throw new RuntimeException("Unable to parse line: " + line);
                    }
                    value = value.trim();
                    start = end;

                    DataRow row = new DataRow();
                    row.setColumn(column);

                    switch (column.getType()) {
                        case "TEXT":
                            row.setValueTxt(value);
                            break;
                        case "INTEGER":
                            row.setValueInt(Integer.parseInt(value));
                            break;
                        case "BOOLEAN":
                            row.setValueBool(Integer.parseInt(value) == 1);
                            break;
                        default:
                            throw new RuntimeException("Unknown type: " + column.getType());

                    }

                    dataRowService.save(row);
                }
            }
        }
    }

    private List<DataColumn> getColumns(DataFile file) {
        String specFile = "/specs/" + file.getName().substring(0, file.getName().indexOf('_')) + ".csv";
        List<DataColumn> columns = new ArrayList<>();
        try (Scanner scanner = new Scanner(this.getClass().getResourceAsStream(specFile), StandardCharsets.UTF_8.name())) {
            scanner.nextLine(); // header
            while (scanner.hasNextLine()) {
                columns.add(getColumn(scanner.nextLine(), file));
            }
        }
        return columns;
    }

    private DataColumn getColumn(String line, DataFile file) {
        try (Scanner scanner = new Scanner(line)) {
            scanner.useDelimiter(",");

            DataColumn column = new DataColumn();
            column.setFile(file);
            column.setName(scanner.next());
            column.setWidth(scanner.nextInt());
            column.setType(scanner.next());

            return column;
        }

    }
}
