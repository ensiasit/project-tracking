package ma.ensias_it_club.project_tracker.respositories;

import ma.ensias_it_club.project_tracker.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
