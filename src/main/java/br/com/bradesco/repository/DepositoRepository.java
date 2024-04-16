package br.com.bradesco.repository;

import br.com.bradesco.domain.entity.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepositoRepository extends JpaRepository<Deposito, UUID> {
}
