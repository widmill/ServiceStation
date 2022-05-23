package com.example.servicestation.controller;

import com.example.servicestation.entity.Department;
import com.example.servicestation.repository.DepartmentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DepartmentTest {
    private MockMvc mockMvc;
    @MockBean
    private DepartmentRepo departmentRepo;
    @Autowired
    ObjectMapper mapper;

    Department department1 = new Department("Ivan Petrov");
    Department department2 = new Department("Petr Ivanov");

    @Test
    public void getAllDepartments_success() throws Exception {
        List departments = new ArrayList<>(Arrays.asList(department1, department2));

        Mockito.when(departmentRepo.findAll()).thenReturn(departments);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].ownerName", is("Petr Ivanov")));
    }

    @Test
    public void getDepartmentById_success() throws Exception {
        Mockito.when(departmentRepo.findById(department1.getDepartment_id())).thenReturn(java.util.Optional.of(department1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.ownerName", is("Ivan Petrov")));
    }
    @Test
    public void getDepartmentByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/departments/0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAutoByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/departments/test"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getAutoByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/departments/1000"))
                .andExpect(status().isNotFound());
    }

}
