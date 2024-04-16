package br.com.bradesco.service;

import br.com.bradesco.domain.payload.request.DepositoRequest;
import br.com.bradesco.domain.payload.response.DepositoResponse;

import java.util.List;
import java.util.UUID;

public interface DepositoService {

    DepositoResponse createDeposito(final DepositoRequest depositoRequest);

    List<DepositoResponse> getAllDeposito();

    DepositoResponse getDepositoById(final UUID idDeposito);
}
