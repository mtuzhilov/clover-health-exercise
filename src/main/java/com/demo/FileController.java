package com.demo;

import com.demo.model.File;
import com.demo.service.FieldService;
import com.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FieldService fieldService;

    /*
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    File getProperty(@PathVariable Integer id){
        return  fileService.findById(id).get();
    }

    @RequestMapping(value = "/property", method = RequestMethod.POST)
    String addProperty(@RequestBody File property){
        File savedProperty = fileService.save(property);
        return "SUCCESS";
    }

    @RequestMapping(value = "/property", method = RequestMethod.PUT)
    File updateProperty(@RequestBody File property){
        File updatedProperty = fileService.save(property);
        return updatedProperty;
    }

    @RequestMapping(value = "/property", method = RequestMethod.DELETE)
    Map<String, String> deleteProperty(@RequestParam Integer id){
        Map<String, String> status = new HashMap<>();
        Optional<File> property = fileService.findById(id);
        if(property.isPresent()) {
            fileService.delete(property.get());
            status.put("Status", "Property deleted successfully");
        }
        else {
            status.put("Status", "Property not exist");
        }
        return status;
    }

    // Select, Insert, Delete for List of Properties
     */
    @RequestMapping(value = "/files", method = RequestMethod.GET)
    List<File> getAllProperty(){
        return fileService.findAll();
    }

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    String addAllPropertys(@RequestBody List<File> propertyList){
        fileService.saveAll(propertyList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/files", method = RequestMethod.DELETE)
    String addAllPropertys(){
        fileService.deleteAll();
        return "SUCCESS";
    }
}
