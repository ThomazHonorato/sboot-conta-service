package br.com.bradesco.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Transferencia")
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTransferencia;
    private BigDecimal valor;
    private LocalDateTime dataTransferencia;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaOrigem")
    private Conta contaOrigem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContaDestino")
    private Conta contaDestino;
}
