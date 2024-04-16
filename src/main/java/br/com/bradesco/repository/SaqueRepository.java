package br.com.bradesco.repository;

import br.com.bradesco.domain.entity.Saque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaqueRepository extends JpaRepository<Saque, UUID> {
}
