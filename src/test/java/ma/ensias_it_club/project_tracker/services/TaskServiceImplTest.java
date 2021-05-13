package ma.ensias_it_club.project_tracker.services;

import ma.ensias_it_club.project_tracker.models.Task;
import ma.ensias_it_club.project_tracker.respositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

  @Mock
  private TaskRepository taskRepository;

  @InjectMocks
  private TaskServiceImpl taskServiceImpl;

  @Test
  void given_tasks_should_return_all_when_find_all() {
    // Given
    when(taskRepository.findAll()).thenReturn(List.of(new Task(), new Task()));

    // When
    var tasks = taskServiceImpl.findAll();

    // Then
    verify(taskRepository).findAll();
    assertEquals(2, tasks.size());
  }

  @Test
  void given_not_found_task_should_return_empty_optional_when_find_by_id() {
    // Given
    var id = "id";

    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    // When
    var task = taskServiceImpl.findById(id);

    // Then
    verify(taskRepository).findById(id);
    assertTrue(task.isEmpty());
  }

  @Test
  void given_found_task_should_return_present_optional_when_find_by_id() {
    // Given
    var id = "id";
    var originalTask = new Task();
    originalTask.setId(id);

    when(taskRepository.findById(id)).thenReturn(Optional.of(originalTask));

    // When
    var task = taskServiceImpl.findById(id);

    // Then
    verify(taskRepository).findById(id);
    assertTrue(task.isPresent());
    assertEquals(id, task.get().getId());
  }

  @Test
  void given_valid_task_should_save_and_return_it_when_save() {
    // Given
    var id = "id";
    var payload = new Task();
    payload.setId("id");

    when(taskRepository.save(payload)).thenReturn(payload);

    // When
    var task = taskServiceImpl.save(payload);

    // Then
    verify(taskRepository).save(payload);
    assertEquals(id, task.getId());
  }

  @Test
  void given_not_found_task_should_return_empty_optional_when_save() {
    // Given
    var id = "id";
    var payload = new Task();

    when(taskRepository.findById(id)).thenReturn(Optional.empty());

    // When
    var task = taskServiceImpl.save(id, payload);

    // Then
    verify(taskRepository, never()).save(payload);
    assertTrue(task.isEmpty());
  }

  @Test
  void given_found_task_should_return_it_when_save() {
    // Given
    var id = "id";
    var payload = new Task();
    payload.setId(id);

    when(taskRepository.findById(id)).thenReturn(Optional.of(payload));
    when(taskRepository.save(payload)).thenReturn(payload);

    // When
    var task = taskServiceImpl.save(id, payload);

    // Then
    verify(taskRepository).save(payload);
    assertTrue(task.isPresent());
    assertEquals(id, task.get().getId());
  }

  @Test
  void given_task_id_should_call_repository_delete() {
    // Given
    var id = "id";

    // When
    taskServiceImpl.deleteById(id);

    // Then
    verify(taskRepository).deleteById(id);
  }

}