package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    private final DbService dbService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService dbService, TaskMapper taskMapper) {
        this.dbService = dbService;
        this.taskMapper = taskMapper;
    }




    @RequestMapping(method = RequestMethod.GET, value = "/getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTask/{id}")
    public TaskDto getTask(@PathVariable Long id) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(dbService.getTask(id).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "/deleteTask/{id}")
    public @ResponseBody void deleteTask(@PathVariable Long id) throws TaskNotFoundException {
        dbService.deleteTask(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTask", consumes = APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody TaskDto taskDto) {
        return dbService.saveTask(taskMapper.mapToTask(taskDto));
    }
}
