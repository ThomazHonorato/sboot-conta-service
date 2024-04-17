package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Conta;
import br.com.bradesco.domain.entity.Saque;
import br.com.bradesco.domain.mappers.SaqueMapper;
import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;
import br.com.bradesco.exceptions.ContaNotFoundException;
import br.com.bradesco.exceptions.NotEnoughSaldoException;
import br.com.bradesco.exceptions.SaqueNotFoundException;
import br.com.bradesco.repository.ContaRepository;
import br.com.bradesco.repository.SaqueRepository;
import br.com.bradesco.service.SaqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaqueServiceImpl implements SaqueService {

    private final SaqueRepository saqueRepository;
    private final SaqueMapper saqueMapper;
    private final ContaRepository contaRepository;
    private final ContaServiceImpl contaServiceImpl;

    public SaqueResponse createSaque(final SaqueRequest saqueRequest) {

        try {
            Conta conta = getConta(saqueRequest.getConta());
            BigDecimal saldo = getSaldo(saqueRequest.getConta());

            if (saldo.compareTo(saqueRequest.getValor()) < 0) {
                throw new NotEnoughSaldoException();
            } else {
                BigDecimal saldoAtualizado;
                saldoAtualizado = saldo.subtract(saqueRequest.getValor());
                contaServiceImpl.updateSaldoConta(saqueRequest.getConta(), saldoAtualizado);
                return saqueMapper.toResponse(saqueRepository.save(saqueMapper.toEntity(saqueRequest)));
            }

        } catch (NotEnoughSaldoException e) {
            throw new NotEnoughSaldoException();
        }
    }

    public List<SaqueResponse> getAllSaque() {
        List<Saque> saques = saqueRepository.findAll();
        return saques.stream().map(saqueMapper::toResponse).toList();

    }

    public SaqueResponse getSaqueById(final UUID idSaque) {
        return saqueMapper.toResponse(getSaque(idSaque));

    }

    private Saque getSaque(UUID idSaque) {
        return saqueRepository.findById(idSaque).orElseThrow(SaqueNotFoundException::new);
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
