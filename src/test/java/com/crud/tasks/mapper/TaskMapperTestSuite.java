package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask(){
        //Given
        TaskDto taskDto = new TaskDto("desc1", "testing mapper1");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals("desc1", task.getTitle());
        Assert.assertEquals("testing mapper1", task.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task("desc1", "testing mapper1");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals("desc1", taskDto.getTitle());
        Assert.assertEquals("testing mapper1", taskDto.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task("desc1", "testing mapper1");
        Task task2 = new Task("desc2", "testing mapper2");
        Task task3 = new Task("desc3", "testing mapper3");
        List <Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        int testSize = taskDtoList.size();
        String testTitle = taskDtoList.get(0).getTitle();
        String testContent = taskDtoList.get(1).getContent();
        //Then
        Assert.assertEquals(3,testSize);
        Assert.assertEquals(testTitle, "desc1");
        Assert.assertEquals(testContent, "testing mapper2");
    }

}