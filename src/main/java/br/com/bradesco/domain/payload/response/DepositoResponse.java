package br.com.bradesco.domain.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepositoResponse {

    private UUID idDeposito;
    private LocalDateTime dataDeposito;
    private BigDecimal valor;
    private UUID contaOrigem;
    private UUID contaDestino;
}
