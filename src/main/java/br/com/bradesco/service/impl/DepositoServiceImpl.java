package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Deposito;
import br.com.bradesco.domain.mappers.DepositoMapper;
import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;
import br.com.bradesco.exceptions.DepositoNotFoundException;
import br.com.bradesco.repository.DepositoRepository;
import br.com.bradesco.service.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;
    private final DepositoMapper depositoMapper;

    public DepositoResponse createDeposito(final DepositoRequest depositoRequest) {
        return depositoMapper.toResponse(depositoRepository.save(depositoMapper.toEntity(depositoRequest)));

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
}
