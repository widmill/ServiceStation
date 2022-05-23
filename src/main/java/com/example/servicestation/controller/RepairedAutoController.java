package com.example.servicestation.controller;

import com.example.servicestation.entity.RepairedAuto;
import com.example.servicestation.exception.AutoNotFoundException;
import com.example.servicestation.exception.DepartmentDoesNotExist;
import com.example.servicestation.exception.NonValidPhoneNumberException;
import com.example.servicestation.exception.NonValidPlateNumberException;
import com.example.servicestation.service.RepairedAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autos")
public class RepairedAutoController {

    @Autowired
    RepairedAutoService repairedAutoService;

    @GetMapping("/{id}")
    public ResponseEntity getAutoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(repairedAutoService.getRepairedAuto(id));
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Id не может быть 0");
        } catch (AutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity getAllAutos() {
        return ResponseEntity.ok(repairedAutoService.getAllAutos());
    }

    @PostMapping
    public ResponseEntity addAuto(@RequestBody RepairedAuto repairedAuto) {
        try {
            return ResponseEntity.ok(repairedAutoService.addAuto(repairedAuto));
        } catch (NonValidPhoneNumberException e) {
            return ResponseEntity.badRequest().body("Телефон должен быть формата +7**********");
        } catch (NonValidPlateNumberException e) {
            return ResponseEntity.badRequest().body("Номер машины должен быть формата А000АА00");
        } catch (DepartmentDoesNotExist e) {
            return ResponseEntity.badRequest().body("Номер отдела должен быть 1 или 2");
        }
    }
}
