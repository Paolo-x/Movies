package com.example.Moviesdemo.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        //error 400: datos obligatorios vacios, valores fuera de rango (@Valid falla)
        StringBuilder detalle = new StringBuilder();
        for (FieldError campo : ex.getBindingResult().getFieldErrors()) {
            detalle.append(campo.getField())
                   .append(": ")
                   .append(campo.getDefaultMessage())
                   .append(", ");
        }
        ApiError error = new ApiError(400, "Error de validación", detalle.toString());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        //error 400: el cliente envio JSON mal formado (ej: {mal} en vez de {"campo":"valor"})
        ApiError error = new ApiError(400, "Solicitud mal formada", "El cuerpo de la solicitud no es un JSON válido");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        //error 400: el cliente envio un tipo incorrecto en la URL (ej: texto donde va un numero)
        ApiError error = new ApiError(400, "Tipo de dato inválido",
                "El parámetro '" + ex.getName() + "' debería ser " + ex.getRequiredType().getSimpleName());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParams(MissingServletRequestParameterException ex) {
        //error 400: el cliente no envio un parametro obligatorio (ej: ?query= en tmdb/buscar)
        ApiError error = new ApiError(400, "Parámetro requerido faltante",
                "El parámetro '" + ex.getParameterName() + "' es obligatorio");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        //error 405: el cliente uso un metodo HTTP incorrecto (ej: DELETE en lugar de GET)
        ApiError error = new ApiError(405, "Método no permitido",
                "El método " + ex.getMethod() + " no está soportado para esta ruta");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        //error 409: el cliente intenta crear un duplicado (ej: mismo username o correo)
        String mensaje = ex.getMostSpecificCause().getMessage();
        if (mensaje != null && mensaje.contains("username")) {
            mensaje = "El nombre de usuario ya existe";
        } else if (mensaje != null && mensaje.contains("correo")) {
            mensaje = "El correo ya está registrado";
        } else {
            mensaje = "Violación de integridad de datos";
        }
        ApiError error = new ApiError(409, "Conflicto", mensaje);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericError(Exception ex) {
        //error 500: cualquier error inesperado que no tenga un handler especifico arriba
        ApiError error = new ApiError(500, "Error interno del servidor", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ApiError> handleWebClientError(WebClientResponseException ex) {
        //error 404: la API externa (TMDB) no encontro el recurso solicitado
        //error 502: la API externa (TMDB) respondio con otro error
        if (ex.getStatusCode().value() == 404) {
            ApiError error = new ApiError(404, "Recurso no encontrado en API externa", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        ApiError error = new ApiError(502, "Error al consultar API externa", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
    }
}
