package br.com.bradesco.service;

import br.com.bradesco.domain.entity.Transferencia;
import br.com.bradesco.domain.payload.request.TransferenciaRequest;
import br.com.bradesco.domain.payload.response.TransferenciaResponse;

import java.util.List;
import java.util.UUID;

public interface TransferenciaService {

    TransferenciaResponse createTransferencia(final TransferenciaRequest transferenciaRequest);

    List<TransferenciaResponse> getAllTransferencia();

    TransferenciaResponse getTransferenciaById(final UUID idTransferencia);
}
