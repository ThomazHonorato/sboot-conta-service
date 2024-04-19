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
@Table(name="Saque")
public class Saque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idSaque;
    private BigDecimal valor;
    private LocalDateTime dataSaque;

    @ManyToOne()
    @JoinColumn(name = "idConta")
    private Conta conta;
}
