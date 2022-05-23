package com.example.servicestation.repository;

import com.example.servicestation.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<Department, Long> {

}
