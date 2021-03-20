package com.demo.service;

import com.demo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileService extends JpaRepository<File, Integer>{
}
