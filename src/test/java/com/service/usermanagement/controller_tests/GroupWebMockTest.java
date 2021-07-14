package com.service.usermanagement.controller_tests;

import com.service.usermanagement.controllers.GroupController;
import com.service.usermanagement.controllers.UserController;
import com.service.usermanagement.models.Group;
import com.service.usermanagement.models.User;
import com.service.usermanagement.services.GroupService;
import com.service.usermanagement.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
public class GroupWebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService service;

    @Test
    public void getAllShouldReturnAllGroupsFromService() throws Exception {
        Group groupOne = new Group(1L, "Group one");
        Group groupTwo = new Group(2L, "Group two");
        List<Group> allGroups = Arrays.asList(groupOne, groupTwo);

        when(service.listAll()).thenReturn(allGroups);

        this.mockMvc.perform(get("/groups")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(groupOne.getName())));

    }

    @Test
    public void getGroupByIdShouldReturnGroupFromService() throws Exception {
        Group groupOne = new Group(1L, "Group one");

        when(service.getById(1L)).thenReturn(groupOne);

        this.mockMvc.perform(get("/groups/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is(groupOne.getName())));

    }

    @Test
    public void addGroupShouldReturnSuccessfulResponse() throws Exception {
        Group groupOne = new Group(1L, "Group one");

        this.mockMvc.perform(post("/groups", groupOne)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateGroupShouldReturnSuccessfulResponse() throws Exception {
        Group groupOne = new Group(1L, "Group one");
        Group groupOneUpdate = new Group(1L, "Group one update");

        when(service.getById(1L)).thenReturn(groupOne);

        this.mockMvc.perform(post("/groups", 1L, groupOneUpdate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteGroupShouldReturnSuccessfulResponse() throws Exception {
        Group groupDelete = new Group(1L, "Group one");

        this.mockMvc.perform(post("/groups", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
