package ma.ensias_it_club.project_tracker.controllers;

import lombok.RequiredArgsConstructor;
import ma.ensias_it_club.project_tracker.exceptions.DocumentNotFoundException;
import ma.ensias_it_club.project_tracker.models.Task;
import ma.ensias_it_club.project_tracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping("/tasks")
  public List<Task> findAll() {
    return taskService.findAll();
  }

  @GetMapping("/tasks/{id}")
  public Task findById(@PathVariable String id) {
    return taskService
      .findById(id)
      .orElseThrow(() -> new DocumentNotFoundException(String.format("No task with id={%s} is found.", id)));
  }

  @PostMapping("/tasks")
  public Task save(@RequestBody Task payload) {
    return taskService.save(payload);
  }

  @PutMapping("/tasks/{id}")
  public Task save(@PathVariable String id, @RequestBody Task payload) {
    return taskService
      .save(id, payload)
      .orElseThrow(() -> new DocumentNotFoundException(String.format("No task with id={%s} is found.", id)));
  }

  @DeleteMapping("/tasks/{id}")
  public void deleteById(@PathVariable String id) {
    taskService.deleteById(id);
  }

}
