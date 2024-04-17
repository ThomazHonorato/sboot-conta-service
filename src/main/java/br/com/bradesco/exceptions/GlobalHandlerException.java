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
    public static final String DEPOSITO_NAO_ENCONTRADO = "DEPOSITO NAO ENCONTRADO";
    public static final String FALHA_AO_REALIZAR_DEPOSITO = "FALHA_AO_REALIZAR_DEPOSITO";
    public static final String FALHA_AO_REALIZAR_TRANSFERENCIA = "FALHA AO REALIZAR TRANSFERENCIA";
    public static final String SALDO_INSUFICIENTE = "SALDO INSUFICIENTE";
    public static final String SAQUE_NAO_ENCONTRADO = "SAQUE NAO ENCONTRADO";
    public static final String TRANSFERENCIA_NAO_ENCONTRADA = "TRANSFERENCIA NAO ENCONTRADA";
    public static final String USUARIO_NAO_ENCONTRADO = "USUARIO NAO ENCONTRADO";

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

    @ExceptionHandler(FalhaDepositoException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleFalhaDepositoException(FalhaDepositoException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                FALHA_AO_REALIZAR_DEPOSITO,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(FalhaTransferenciaException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleFalhaTransferenciaException(FalhaTransferenciaException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                FALHA_AO_REALIZAR_TRANSFERENCIA,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                USUARIO_NAO_ENCONTRADO,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(TransferenciaNotFoundException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleTransferenciaNotFoundException(TransferenciaNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                TRANSFERENCIA_NAO_ENCONTRADA,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SaqueNotFoundException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleSaqueNotFoundException(SaqueNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                SAQUE_NAO_ENCONTRADO,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DepositoNotFoundException.class)
    public ResponseEntity<GlobalMessageExceptionHandlerDTO> handleDepositoNotFoundException(DepositoNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return buildGlobalMessageExceptionHandlerDTO(
                DEPOSITO_NAO_ENCONTRADO,
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<GlobalMessageExceptionHandlerDTO> buildGlobalMessageExceptionHandlerDTO(String message, HttpStatus status) {
        return ResponseEntity.status(status)
                .body(GlobalMessageExceptionHandlerDTO.builder()
                        .message(message)
                        .build());
    }
}
