package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    public void testShouldGetTasks() throws Exception {
        //Given
        List<Task> controlTasks = new ArrayList<>();
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L, "2", "333"));
        when(taskMapper.mapToTaskDtoList(controlTasks)).thenReturn(tasks);
        //When&&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.[0].title", is("2")))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testShouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(555L, "Title", "Content");
        Optional<Task> task = Optional.of(new Task(555L, "Title after", "Content after"));
        when(dbService.getTask(anyLong())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        //When&&Then
        mockMvc.perform(get("/v1/task/getTask/333")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Title after")));
    }

    @Test
    public void testShouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Optional<Task> task = Optional.of(new Task(1L, "Title after", "Content after"));
        when(dbService.getTask(any(Long.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        //When&&Then
        mockMvc.perform(get("/v1/task/getTask/1"))
                .andExpect(status().is(200));
        mockMvc.perform(delete("/v1/task/deleteTask/1"));
        mockMvc.perform(get("/v1/task/getTask/1"))
                .andExpect(status().is(404));
    }

    @Test
    public void testShouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Optional<Task> task = Optional.of(new Task(1L, "Title after", "Content after"));
        when(dbService.getTask(any(Long.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        //When&&Then
        mockMvc.perform(put("/v1/task/updateTask/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void testShouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Optional<Task> task = Optional.of(new Task(1L, "Title after", "Content after"));

        when(dbService.getTask(any(Long.class))).thenReturn(task);
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&&Then
        System.out.println(jsonContent);
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.title", is("Title after"))));
    }
}