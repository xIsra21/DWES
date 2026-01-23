package goya.daw2.H2Tuto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class PeliculaNotFoundAdvice {

  @ExceptionHandler(PeliculaNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String peliculaNotFoundHandler(PeliculaNotFoundException ex) {
    return ex.getMessage();
  }
}