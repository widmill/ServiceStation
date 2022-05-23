package com.example.servicestation.controller;


import com.example.servicestation.config.WebSecurityConfig;
import com.example.servicestation.controller.RepairedAutoController;
import com.example.servicestation.entity.RepairedAuto;
import com.example.servicestation.repository.RepairedAutoRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class RepairedAutoControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private RepairedAutoRepo repairedAutoRepo;
    @Autowired
    ObjectMapper mapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date;

    {
        try {
            date = sdf.parse("2022-01-25");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    RepairedAuto auto1 = new RepairedAuto(1L, "Hyundai Solaris", date, "Ivanov", "Ivan", "+71234567891",
            "Н812УС22", 3000, "поломка", 1L);


    RepairedAuto auto2 = new RepairedAuto(2L, "Lada Kalina", date, "Belyaev", "Evgeniy", "+71234522291",
            "Р1222УГ22", 2000, "поломка", 2L);

    RepairedAuto auto3 = new RepairedAuto(3L, "Toyota Camry", date, "Petrov", "Nikolay", "+71232167891",
            "К265ТЕ122", 4000, "поломка", 1L);


        @Test
    public void getAllAutos_success() throws Exception {
        List autos = new ArrayList<>(Arrays.asList(auto1, auto2, auto3));

        Mockito.when(repairedAutoRepo.findAll()).thenReturn(autos);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/autos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].model", is("Toyota Camry")));
    }

    @Test
    public void getAutoById_success() throws Exception {
        Mockito.when(repairedAutoRepo.findById(auto1.getId())).thenReturn(java.util.Optional.of(auto1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/autos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.model", is("Hyundai Solaris")));
    }

    @Test
    public void createAuto_success() throws Exception {
        RepairedAuto auto4 = new RepairedAuto(4L, "Volkswagen Golf", date, "Romanov", "Denis", "+71232167654",
                "К273ТМ122", 2000, "поломка", 1L);
        Mockito.when(repairedAutoRepo.save(auto4)).thenReturn(auto4);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/autos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(auto4));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.model", is("Volkswagen Golf")));
    }



    @Test
    public void getAutoByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/autos/0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAutoByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/autos/test"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getAutoByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/autos/1000"))
                .andExpect(status().isNotFound());
    }

}


