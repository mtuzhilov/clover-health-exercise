package com.demo.service;

import com.demo.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldService extends JpaRepository<Field, Integer> {
}
