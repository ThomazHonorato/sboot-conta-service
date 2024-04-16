package br.com.bradesco.service;

import br.com.bradesco.domain.payload.request.ContaRequest;
import br.com.bradesco.domain.payload.response.ContaResponse;

import java.util.List;
import java.util.UUID;

public interface ContaService {

    ContaResponse createConta(final ContaRequest contaRequest);

    List<ContaResponse> getAllConta();

    ContaResponse getContaById(final UUID idConta);
}
