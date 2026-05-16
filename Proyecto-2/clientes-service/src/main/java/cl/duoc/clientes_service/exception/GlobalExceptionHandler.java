package cl.duoc.clientes_service.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorDeValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage()).toList();
        ErrorResponse response = new ErrorResponse
                (String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        "Validacion fallida",
                        errores.toString(),
                        LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> errorBaseDeDatos(DataIntegrityViolationException ex) {

        String error = ex.getMostSpecificCause().getMessage();
        String mensaje = "Error de persistencia: Error de restriccion en la base de datos";
        if(error.contains("Duplicate entry")) {
            mensaje = "No se puede guardar: El RUT o el Correo ingresado ya existen en el sistema.";
        }
        ErrorResponse response = new ErrorResponse(
                String.valueOf(HttpStatus.CONFLICT.value()),
                "Registro Duplicado",
                mensaje,
                LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
