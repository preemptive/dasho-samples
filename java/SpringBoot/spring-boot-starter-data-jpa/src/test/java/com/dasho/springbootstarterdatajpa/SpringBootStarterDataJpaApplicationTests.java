package com.dasho.springbootstarterdatajpa;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dasho.springbootstarterdatajpa.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class SpringBootStarterDataJpaApplicationTests {

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private List<Person> persons;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // id: 1
        Person p1 = new Person();
        p1.setFirstName("James");
        p1.setLastName("Thomas");

        // id: 2
        Person p2 = new Person();
        p2.setFirstName("Mary");
        p2.setLastName("Scott");

        // id: 3
        Person p3 = new Person();
        p3.setFirstName("Sam");
        p3.setLastName("Smith");

        persons = Arrays.asList(p1, p2, p3);
    }

    @Test
    @Order(1) 
    void homePage() throws Exception {
        String uri = "/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    @Order(2) 
    void getAllPersons() throws Exception {
        mvc.perform(get("/persons").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].lastName", is(persons.get(2).getLastName())));
    }

    @Order(3) 
    void createPerson() throws Exception {
        mvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"firstName\": \"Someone\", \"lastName\": \"Else\" }") 
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                  .andExpect(jsonPath("$.id").value(4)); 
    }

    @Test
    @Order(4) 
    void getPerson() throws Exception {
        mvc.perform(get("/persons/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastName", is(persons.get(0).getLastName())));
    }
    

    @Test
    @Order(5) 
    void updatePerson() throws Exception {
        mvc.perform(put("/persons/1").contentType(MediaType.APPLICATION_JSON)
                .content("{ \"firstName\": \"Someone\", \"lastName\": \"Else\" }").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Someone"));
    }

    @Test
    @Order(6) 
    void deletePerson() throws Exception {
        mvc.perform(delete("/persons/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
