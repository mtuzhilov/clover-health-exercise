package com.demo.service;

import com.demo.model.DataRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRowService extends JpaRepository<DataRow, Integer> {
}

