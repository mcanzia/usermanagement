package com.service.usermanagement.controller_tests;

import com.service.usermanagement.controllers.UserController;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @Test
    public void getAllShouldReturnAllUsersFromService() throws Exception {
        User userOne = new User(1L, "David", "Test", "david.test@gmail.com","ADMIN",1L,"Test Group");
        User userTwo = new User(2L, "Perry", "Poppins", "perry.poppins@gmail.com","BASIC",1L,"Test Group");
        List<User> allUsers = Arrays.asList(userOne, userTwo);

        when(service.listAll()).thenReturn(allUsers);

        this.mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].email", is(userOne.getEmail())));

    }
}
