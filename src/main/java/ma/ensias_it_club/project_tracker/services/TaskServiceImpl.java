package ma.ensias_it_club.project_tracker.services;

import lombok.RequiredArgsConstructor;
import ma.ensias_it_club.project_tracker.models.Task;
import ma.ensias_it_club.project_tracker.respositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Optional<Task> findById(String id) {
    return taskRepository.findById(id);
  }

  @Override
  public Task save(Task payload) {
    return taskRepository.save(payload);
  }

  @Override
  public Optional<Task> save(String id, Task payload) {
    Optional<Task> optionalTask = findById(id);
    if (optionalTask.isEmpty()) {
      return optionalTask;
    }

    var task = optionalTask.get();
    task.setContent(payload.getContent());
    task.setDeadline(payload.getDeadline());

    save(task);

    return Optional.of(task);
  }

  @Override
  public void deleteById(String id) {
    taskRepository.deleteById(id);
  }

}
