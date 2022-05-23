package com.example.servicestation.controller;


import com.example.servicestation.exception.DepartmentDoesNotExist;
import com.example.servicestation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value = "/{id}")
    public ResponseEntity getDepartment(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartment(id));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id не может быть 0");
        } catch (DepartmentDoesNotExist e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity getDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("history/{id}")
    public ResponseEntity getDepartmentHistory(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentWithRepairedAutos(id));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id не может быть 0");
        } catch (DepartmentDoesNotExist e) {
            return ResponseEntity.notFound().build();
        }
    }


}