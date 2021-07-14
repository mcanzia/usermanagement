package com.service.usermanagement.controller_tests;

import com.service.usermanagement.controllers.AuthController;
import com.service.usermanagement.controllers.GroupController;
import com.service.usermanagement.models.AuthRequest;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.GroupService;
import com.service.usermanagement.services.SecurityDetailsService;
import com.service.usermanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityDetailsService securityDetailsService;

    @MockBean
    private UserService userService;

    @Test
    public void registerUserShouldReturnSuccessfulResponseFromService() throws Exception {
        User userTest = new User(1L, "John", "Test", "test@gmail.com","ADMIN",1L,"Test Group");

        AuthRequest authRequestTest = new AuthRequest();
        authRequestTest.setEmail("test@gmail.com");
        authRequestTest.setPassword("cherryblossom");

        when(userService.getByUsername("test@gmail.com")).thenReturn(userTest);

        this.mockMvc.perform(get("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void loginUserShouldReturnSuccessfulAuthResponse() throws Exception {
        User userTest = new User(1L, "John", "Test", "test@gmail.com","ADMIN",1L,"Test Group");

        AuthRequest authRequestTest = new AuthRequest();
        authRequestTest.setEmail("test@gmail.com");
        authRequestTest.setPassword("cherryblossom");

        when(userService.getByUsername("test@gmail.com")).thenReturn(userTest);

        this.mockMvc.perform(get("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }



}
