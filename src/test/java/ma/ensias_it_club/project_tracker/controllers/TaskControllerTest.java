package ma.ensias_it_club.project_tracker.controllers;

import ma.ensias_it_club.project_tracker.exceptions.DocumentNotFoundException;
import ma.ensias_it_club.project_tracker.models.Task;
import ma.ensias_it_club.project_tracker.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

  @Mock
  private TaskService taskService;

  @InjectMocks
  private TaskController taskController;

  @Test
  void given_no_tasks_should_return_empty_list_when_find_all() {
    // Given
    when(taskService.findAll()).thenReturn(Collections.emptyList());

    // When
    var tasks = taskController.findAll();

    // Then
    verify(taskService).findAll();
    assertEquals(0, tasks.size());
  }

  @Test
  void given_tasks_should_return_all_when_find_all() {
    // Given
    when(taskService.findAll()).thenReturn(List.of(new Task(), new Task()));

    // When
    var tasks = taskController.findAll();

    // Then
    verify(taskService).findAll();
    assertEquals(2, tasks.size());
  }

  @Test
  void given_not_found_task_should_throw_exception_when_find_by_id() {
    // Given
    var id = "id";

    when(taskService.findById(id)).thenReturn(Optional.empty());

    assertThrows(
      // Then
      DocumentNotFoundException.class,
      // When
      () -> taskController.findById(id)
    );

    verify(taskService).findById(id);
  }

  @Test
  void given_found_task_should_return_it_when_find_by_id() {
    // Given
    var id = "id";
    var originalTask = new Task();
    originalTask.setId(id);

    when(taskService.findById(id)).thenReturn(Optional.of(originalTask));

    // When
    var task = taskController.findById(id);

    // Then
    verify(taskService).findById(id);
    assertEquals(id, task.getId());
  }

  @Test
  void given_valid_task_should_save_and_return_it_when_save() {
    // Given
    var id = "id";
    var payload = new Task();
    payload.setId("id");

    when(taskService.save(payload)).thenReturn(payload);

    // When
    var task = taskController.save(payload);

    // Then
    verify(taskService).save(payload);
    assertEquals(id, task.getId());
  }

  @Test
  void given_not_found_task_should_throw_exception_when_save() {
    // Given
    var id = "id";
    var payload = new Task();

    when(taskService.save(id, payload)).thenReturn(Optional.empty());

    assertThrows(
      // Then
      DocumentNotFoundException.class,
      // When
      () -> taskController.save(id, payload)
    );

    verify(taskService).save(id, payload);
  }

  @Test
  void given_found_task_should_return_it_when_save() {
    // Given
    var id = "id";
    var payload = new Task();
    payload.setId(id);

    when(taskService.save(id, payload)).thenReturn(Optional.of(payload));

    // When
    var task = taskController.save(id, payload);

    // Then
    verify(taskService).save(id, payload);
    assertEquals(id, task.getId());
  }

  @Test
  void given_task_id_should_call_service_delete() {
    // Given
    var id = "id";

    // When
    taskController.deleteById(id);

    // Then
    verify(taskService).deleteById(id);
  }

}