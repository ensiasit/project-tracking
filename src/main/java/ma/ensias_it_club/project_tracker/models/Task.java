package ma.ensias_it_club.project_tracker.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class Task {

  @Id
  private String id;

  private String content;

  private LocalDateTime deadline;

}
