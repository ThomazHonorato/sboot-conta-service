package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Transferencia;
import br.com.bradesco.domain.mappers.TransferenciaMapper;
import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;
import br.com.bradesco.exceptions.TransferenciaNotFoundException;
import br.com.bradesco.repository.TransferenciaRepository;
import br.com.bradesco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;

    public TransferenciaResponse createTransferencia(final TransferenciaRequest transferenciaRequest) {
        return transferenciaMapper.toResponse(transferenciaRepository.save(transferenciaMapper.toEntity(transferenciaRequest)));
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
}


