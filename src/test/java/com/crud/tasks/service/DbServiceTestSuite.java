package com.crud.tasks.service;

import ch.qos.logback.classic.joran.ReconfigureOnChangeTaskListener;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        Task task1 = new Task(1L, "task1", "content1");
        Task task2 = new Task(2L, "task2", "content2");
        Task task3 = new Task(3L, "task3", "content3");

        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> taskLoaded = dbService.getAllTasks();

        //Them
        assertEquals(taskList.size(), taskLoaded.size());
    }

    @Test
    public void getTaskById() {
        //Given
        List<Optional<Task>> taskList = new ArrayList<>();
        Task task = new Task(1L, "title", "content");
        taskList.add(Optional.of(task));

        //When
        when(dbService.getTaskById(1L)).thenReturn(taskList.get(0));
        Task taskLoaded = dbService.getTaskById(1L).get();

        //Then
        assertEquals(task.getTitle(), taskLoaded.getTitle());
        assertEquals(task.getContent(), taskLoaded.getContent());
    }

    @Test
    public void saveTaskTest() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        when(repository.save(task)).thenReturn(task);
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getContent(), savedTask.getContent());
        assertEquals(task.getTitle(), savedTask.getTitle());
    }

    @Test
    public void deleteTask() {
        //Given

        //When
        dbService.deleteTask(1L);

        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}