package ma.ensias_it_club.project_tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocumentNotFoundException extends RuntimeException {

  public DocumentNotFoundException(String message) {
    super(message);
  }

}
