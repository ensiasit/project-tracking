package ma.ensias_it_club.project_tracker.services;

import ma.ensias_it_club.project_tracker.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Optional<Task> findById(String id);

  Task save(Task payload);

  Optional<Task> save(String id, Task payload);

  void deleteById(String id);

}
