package br.com.bradesco.exceptions;

import br.com.bradesco.domain.payload.dto.GlobalMessageExceptionHandlerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalHandlerException {


    public static final String CONTA_NAO_ENCONTRADA = "CONTA NAO ENCONTRADA";
    public static final String SALDO_INSUFICIENTE = "SALDO INSUFICIENTE";

    @ExceptionHandler(ContaNotFoundException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleContaNotFoundException(ContaNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                CONTA_NAO_ENCONTRADA,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(NotEnoughSaldoException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleNotEnoughSaldoException(NotEnoughSaldoException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                SALDO_INSUFICIENTE,
                HttpStatus.NOT_FOUND
        );
    }
    

    private ResponseEntity<GlobalMessageExceptionHandlerDTO> buildGlobalMessageExceptionHandlerDTO(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(GlobalMessageExceptionHandlerDTO.builder()
                        .message(message)
                        .build());
    }
}
