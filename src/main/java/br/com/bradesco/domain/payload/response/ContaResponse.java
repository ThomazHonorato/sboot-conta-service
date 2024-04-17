package br.com.bradesco.domain.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaResponse {

    private UUID idConta;
    private String numero;
    private String agencia;
    private String banco;
    private BigDecimal saldo;
    private UUID idUsuario;

}
