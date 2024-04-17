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
@AllArgsConstructor
@NoArgsConstructor
public class SaqueRequest {

    @NotNull(message = "Valor não pode estar vazio")
    private BigDecimal valor;

    @NotNull(message = "Data não pode estar vazio")
    private LocalDateTime dataSaque;

    private UUID conta;

}
