package br.com.bradesco.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Conta")
public class  Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idConta;
    private String numero;
    private String agencia;
    private String banco;

    @Column(nullable = false, columnDefinition = "0")
    private BigDecimal saldo = BigDecimal.ZERO;
    //aqui vai idUsuario.
}
