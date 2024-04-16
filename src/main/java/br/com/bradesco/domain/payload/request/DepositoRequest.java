package br.com.bradesco.domain.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositoRequest {

    @NotNull(message = "Data não pode estar vazio")
    private LocalDateTime dataDeposito;

    @NotNull(message = "Valor não pode estar vazio")
    private BigDecimal valor;

    @NotNull(message = "Conta de Origem não pode estar vazio")
    private UUID contaOrigem;

    @NotNull(message = "Conta de Destino não pode estar vazio")
    private UUID contaDestino;

}
