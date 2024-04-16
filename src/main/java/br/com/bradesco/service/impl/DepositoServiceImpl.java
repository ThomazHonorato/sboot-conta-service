package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.entity.Deposito;
import br.com.bradesco.domain.mappers.DepositoMapper;
import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;
import br.com.bradesco.exceptions.ContaNotFoundException;
import br.com.bradesco.exceptions.DepositoNotFoundException;
import br.com.bradesco.exceptions.NotEnoughSaldoException;
import br.com.bradesco.repository.ContaRepository;
import br.com.bradesco.repository.DepositoRepository;
import br.com.bradesco.service.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;
    private final DepositoMapper depositoMapper;
    private final ContaRepository contaRepository;

    public DepositoResponse createDeposito(final DepositoRequest depositoRequest) {

        Conta contaOrigem = getConta(depositoRequest.getContaOrigem());
        Conta contaDestino = getConta(depositoRequest.getContaDestino());

        depositoRequest.setContaDestino(contaDestino.getIdConta());
        depositoRequest.setContaOrigem(contaOrigem.getIdConta());

        if (getSaldo(depositoRequest.getContaOrigem()).compareTo(depositoRequest.getValor()) < 0) {
            throw new NotEnoughSaldoException();
        } else {
            return depositoMapper.toResponse(depositoRepository.save(depositoMapper.toEntity(depositoRequest)));
        }

    }

    public List<DepositoResponse> getAllDeposito() {
        List<Deposito> depositos = depositoRepository.findAll();
        return depositos.stream().map(depositoMapper::toResponse).toList();

    }

    public DepositoResponse getDepositoById(final UUID idDeposito) {
        return depositoMapper.toResponse(getDeposito(idDeposito));

    }

    private Deposito getDeposito(UUID idDeposito) {
        return depositoRepository.findById(idDeposito).orElseThrow(DepositoNotFoundException::new);
    }

    private Conta getConta(UUID idConta) {
        return contaRepository.findById(idConta).orElseThrow(ContaNotFoundException::new);
    }

    private BigDecimal getSaldo(UUID idConta) {
        BigDecimal saldo = getConta(idConta).getSaldo();
        if (saldo == null) {
            throw new NotEnoughSaldoException();
        } else {
            return saldo;
        }
    }
}
