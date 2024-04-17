package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.entity.Transferencia;
import br.com.bradesco.domain.mappers.TransferenciaMapper;
import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;
import br.com.bradesco.exceptions.ContaNotFoundException;
import br.com.bradesco.exceptions.FalhaTransferenciaException;
import br.com.bradesco.exceptions.NotEnoughSaldoException;
import br.com.bradesco.exceptions.TransferenciaNotFoundException;
import br.com.bradesco.repository.ContaRepository;
import br.com.bradesco.repository.TransferenciaRepository;
import br.com.bradesco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;
    private final ContaRepository contaRepository;
    private final ContaServiceImpl contaServiceImpl;

    @Transactional
    public TransferenciaResponse createTransferencia(final TransferenciaRequest transferenciaRequest) {

        try{
            Conta contaOrigem = getConta(transferenciaRequest.getContaOrigem());
            Conta contaDestino = getConta(transferenciaRequest.getContaDestino());

            transferenciaRequest.setContaDestino(contaDestino.getIdConta());
            transferenciaRequest.setContaOrigem(contaOrigem.getIdConta());

            if (getSaldo(transferenciaRequest.getContaOrigem()).compareTo(transferenciaRequest.getValor()) < 0) {
                throw new NotEnoughSaldoException();
            } else {
                BigDecimal saldoS = getSaldo(transferenciaRequest.getContaOrigem());
                BigDecimal saldoE = getSaldo(transferenciaRequest.getContaDestino());
                BigDecimal valor = transferenciaRequest.getValor();
                BigDecimal saldoSaida = saldoS.subtract(valor);
                BigDecimal saldoEntrada = saldoE.add(valor);
                contaServiceImpl.updateSaldoConta(transferenciaRequest.getContaDestino(), saldoEntrada);
                contaServiceImpl.updateSaldoConta(transferenciaRequest.getContaOrigem(), saldoSaida);
                return transferenciaMapper.toResponse(transferenciaRepository.save(transferenciaMapper.toEntity(transferenciaRequest)));
            }
        }catch (FalhaTransferenciaException e){
            throw new FalhaTransferenciaException();
        }
    }

    public List<TransferenciaResponse> getAllTransferencia() {

        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return transferencias.stream().map(transferenciaMapper::toResponse).toList();
    }

    public TransferenciaResponse getTransferenciaById(final UUID idTransferencia) {
        return transferenciaMapper.toResponse(getTransferencia(idTransferencia));
    }

    private Transferencia getTransferencia(UUID idTransferencia) {
        return transferenciaRepository.findById(idTransferencia).orElseThrow(TransferenciaNotFoundException::new);
    }

    private Conta getConta(final UUID idConta) {
        return contaRepository.findById(idConta).orElseThrow(ContaNotFoundException::new);
    }

    private BigDecimal getSaldo(final UUID idConta) {
        BigDecimal saldo = getConta(idConta).getSaldo();
        if (saldo == null) {
            throw new NotEnoughSaldoException();
        } else {
            return saldo;
        }
    }
}


