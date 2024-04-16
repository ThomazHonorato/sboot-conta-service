package br.com.bradesco.domain.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaRequest {

    @NotBlank(message = "Número não pode estar vazio")
    private String numero;

    @NotBlank(message = "Agência não pode estar vazio")
    private String agencia;

    @NotBlank(message = "Banco não pode estar vazio")
    private String banco;
}
