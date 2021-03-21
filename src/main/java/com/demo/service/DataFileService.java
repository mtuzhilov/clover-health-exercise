package com.demo.service;

import com.demo.model.DataFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataFileService extends JpaRepository<DataFile, Integer>{
}
