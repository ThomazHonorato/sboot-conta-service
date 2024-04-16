package br.com.bradesco.service;

import br.com.bradesco.domain.payload.request.SaqueRequest;
import br.com.bradesco.domain.payload.response.SaqueResponse;

import java.util.List;
import java.util.UUID;

public interface SaqueService {

    SaqueResponse createSaque(final SaqueRequest saqueRequest);

    List<SaqueResponse> getAllSaque();

    SaqueResponse getSaqueById(final UUID idSaque);
}
