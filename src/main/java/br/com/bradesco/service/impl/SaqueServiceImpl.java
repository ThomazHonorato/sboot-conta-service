package br.com.bradesco.service.impl;

import br.com.bradesco.domain.entity.Saque;
import br.com.bradesco.domain.mappers.SaqueMapper;
import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;
import br.com.bradesco.exceptions.NotEnoughSaldoException;
import br.com.bradesco.exceptions.SaqueNotFoundException;
import br.com.bradesco.repository.SaqueRepository;
import br.com.bradesco.service.SaqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaqueServiceImpl implements SaqueService {

    private final SaqueRepository saqueRepository;
    private final SaqueMapper saqueMapper;

    public SaqueResponse createSaque(final SaqueRequest saqueRequest) {
        return saqueMapper.toResponse(saqueRepository.save(saqueMapper.toEntity(saqueRequest)));

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

}
